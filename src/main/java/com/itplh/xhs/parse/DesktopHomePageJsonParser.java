package com.itplh.xhs.parse;

import com.alibaba.excel.EasyExcel;
import com.itplh.xhs.constant.DeviceEnum;
import com.itplh.xhs.constant.URLConstant;
import com.itplh.xhs.domain.NoteExcel;
import com.itplh.xhs.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: tanpenggood
 * @date: 2023-07-21 22:35
 */
@Slf4j
public class DesktopHomePageJsonParser implements HomeJsonParser {

    @Override
    public UserInfo parse(String initialStateJson) {
        log.debug(".");
        UserInfo userInfo = new UserInfo();
        userInfo.setDevice(DeviceEnum.DESKTOP);
        userInfo.populateBasicInfoForDesktop(initialStateJson);
        userInfo.populateInteractInfoForDesktop(initialStateJson);
        userInfo.populateNotesForDesktop(initialStateJson);

        // write data to excel
        writeNotes2Excel(userInfo);

        return userInfo;
    }

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
