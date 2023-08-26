package com.itplh.xhs.excel;

import com.alibaba.excel.EasyExcel;
import com.itplh.xhs.constant.URLConstant;
import com.itplh.xhs.domain.UserInfo;
import com.itplh.xhs.util.NumberUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelGenerator {

    /**
     * @param userInfo
     */
    public static void writeNotes2Excel(UserInfo userInfo) {
        List<Object> data = userInfo.getNotes().stream()
                .map(n -> {
                    NoteExcel excel = new NoteExcel();
                    excel.setNickname(n.getUser().getNickname());
                    excel.setTitle(n.getTitle());
                    excel.setPublishTime(n.getTime());
                    excel.setNoteUrl(URLConstant.PREFIX_DETAIL + n.getNoteId());
                    excel.setLikedCount(NumberUtil.valueOf(n.getInteractInfo().getLikedCount()));
                    excel.setCollectedCount(NumberUtil.valueOf(n.getInteractInfo().getCollectedCount()));
                    excel.setCommentCount(NumberUtil.valueOf(n.getInteractInfo().getCommentCount()));
                    excel.setShareCount(NumberUtil.valueOf(n.getInteractInfo().getShareCount()));

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
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(userInfo.getNickname()).append("_").append(userInfo.getRedId())
                .append("_").append(timestamp()).append(".xlsx");
        EasyExcel.write(fileNameBuilder.toString(), NoteExcel.class).sheet().doWrite(data);
    }

    private static String timestamp() {
        LocalDateTime now = LocalDateTime.now();
        String ymd = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(now);
        int microsecond = now.getNano() / 1_000_000;
        return ymd + microsecond;
    }

}
