package com.itplh.xhs.domain;

import com.itplh.xhs.domain.common.Cover;
import com.itplh.xhs.domain.common.Image;
import com.itplh.xhs.domain.common.Tag;
import com.itplh.xhs.domain.desktop.InteractInfo;
import com.itplh.xhs.domain.desktop.ShareInfo;
import com.itplh.xhs.domain.desktop.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author: tanpenggood
 * @date: 2023-07-23 00:06
 */
@Data
public class Note {

    private String noteId;
    private String title;
    private String displayTitle;
    private String desc;
    private String ipLocation;
    private User user;
    private Cover cover;
    private InteractInfo interactInfo;
    private ShareInfo shareInfo;
    private List<Image> imageList = Collections.emptyList();
    private List<Tag> tagList = Collections.emptyList();
    private List<String> atUserList = Collections.emptyList();
    private String type;
    private LocalDateTime time;
    private LocalDateTime lastUpdateTime;

}

