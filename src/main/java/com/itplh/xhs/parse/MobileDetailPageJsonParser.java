package com.itplh.xhs.parse;

import com.itplh.xhs.constant.JSONPathConstant;
import com.itplh.xhs.domain.common.Image;
import com.itplh.xhs.domain.common.Tag;
import com.itplh.xhs.domain.mobile.NoteData;
import com.itplh.xhs.util.JSONUtil;
import com.itplh.xhs.util.StringBuilderUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: tanpenggood
 * @date: 2023-07-21 22:35
 */
@Slf4j
public class MobileDetailPageJsonParser implements DetailJsonParser {

    @Override
    public void parse(String initialStateJson) {
        log.debug(".");
        // title likes collects shareCount comments desc
        NoteData noteData = JSONUtil.extractBean(initialStateJson, JSONPathConstant.MOBILE_DETAIL_JSON_PATH_NOTE_DATA, NoteData.class);
        System.out.println(String.format("title:%s", noteData.getTitle()));
        System.out.println(String.format("likes:%s collects:%s", noteData.getLikes(), noteData.getCollects()));
        System.out.println(String.format("shareCount:%s comments:%s", noteData.getShareCount(), noteData.getComments()));
        System.out.println(String.format("publish time:%s", noteData.getTime()));
        System.out.println();
        System.out.println(String.format("desc:%s", noteData.getDesc()));
        // topic
        StringBuilder tagBuilder = new StringBuilder();
        for (Tag tag : noteData.getHashTags()) {
            tagBuilder.append("#").append(tag.getName()).append(" ");
        }
        StringBuilderUtil.deleteCharAtLast(tagBuilder);
        System.out.println(tagBuilder);
        // image list
        for (Image image : noteData.getImageList()) {
            System.out.println("http:" + image.getUrl());
        }
    }

}
