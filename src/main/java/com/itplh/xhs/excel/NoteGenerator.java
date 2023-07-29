package com.itplh.xhs.excel;

import com.alibaba.excel.EasyExcel;
import com.itplh.xhs.constant.URLConstant;
import com.itplh.xhs.domain.UserInfo;

import java.util.List;
import java.util.stream.Collectors;

public class NoteGenerator {

    /**
     * @param userInfo
     */
    private static void writeNotes2Excel(UserInfo userInfo) {
        List<Object> data = userInfo.getNotes().stream()
                .map(n -> {
                    NoteExcel excel = new NoteExcel();
                    excel.setNickname(n.getUser().getNickname());
                    excel.setTitle(n.getTitle());
                    excel.setPublishTime(n.getTime());
                    excel.setNoteUrl(URLConstant.PREFIX_DETAIL + n.getNoteId());
                    excel.setLikedCount(Integer.valueOf(n.getInteractInfo().getLikedCount()));
                    excel.setCollectedCount(Integer.valueOf(n.getInteractInfo().getCollectedCount()));
                    excel.setCommentCount(Integer.valueOf(n.getInteractInfo().getCommentCount()));
                    excel.setShareCount(Integer.valueOf(n.getInteractInfo().getShareCount()));

                    StringBuilder tagBuilder = new StringBuilder();
                    n.getTagList().stream().forEach(t -> tagBuilder.append("#").append(t.getName()).append(" "));
                    tagBuilder.deleteCharAt(tagBuilder.length() - 1);
                    excel.setTags(tagBuilder.toString());
                    excel.setDesc(n.getDesc());

                    StringBuilder imageUrlBuilder = new StringBuilder();
                    n.getImageList().stream().forEach(i -> imageUrlBuilder.append(i.getUrl()).append("?imageView2/2").append("\n"));
                    tagBuilder.deleteCharAt(tagBuilder.length() - 1);
                    tagBuilder.deleteCharAt(tagBuilder.length() - 1);
                    excel.setImageUrls(imageUrlBuilder.toString());
                    return excel;
                }).collect(Collectors.toList());
        String fileName = userInfo.getNickname() + "_" + userInfo.getRedId() + ".xlsx";
        EasyExcel.write(fileName, NoteExcel.class).sheet().doWrite(data);
    }

}
