package com.itplh.xhs.domain.mobile;

import com.itplh.xhs.domain.common.Cover;
import com.itplh.xhs.domain.common.Image;
import com.itplh.xhs.domain.common.Tag;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author: tanpenggood
 * @date: 2023-07-26 09:37
 */
@Data
public class NoteData {

    private List<String> ats = Collections.emptyList();
    private String likes;
    private String collects;
    private int shareCount;
    private int comments;
    private String title;
    private String desc;
    private String id;
    private List<Image> imageList = Collections.emptyList();
    private Cover cover;
    private boolean isLiked;
    private LocalDateTime time;
    private String type;
    private List<Tag> hashTags = Collections.emptyList();
    private String haveGoodsSeller;
    private List<String> cooperateBinds;
    private boolean isCollected;
    private String generatedTitle;
    private List<String> keywords = Collections.emptyList();
    private List<String> categories = Collections.emptyList();
    private List<String> categoriesIndex = Collections.emptyList();
    private boolean inCensor;
    private String censorTip;
    private List<String> goodsInfo = Collections.emptyList();

}
