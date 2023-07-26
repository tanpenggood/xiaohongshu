package com.itplh.xhs.constant;

import com.alibaba.fastjson2.JSONPath;

/**
 * @author: tanpenggood
 * @date: 2023-07-26 00:27
 */
public class JSONPathConstant {

    /**
     * desktop
     */
    public static final JSONPath DESKTOP_HOME_JSON_PATH_BASIC_INFO = JSONPath.of("$.user.userPageData.basicInfo");

    public static final JSONPath DESKTOP_HOME_JSON_PATH_INTERACTIONS = JSONPath.of("$.user.userPageData.interactions");

    public static final JSONPath DESKTOP_HOME_JSON_PATH_NOTE_CARDS = JSONPath.of("$.user.notes[0][*].noteCard");

    /**
     * mobile
     */
    public static final JSONPath MOBILE_HOME_JSON_PATH_USER_INFO = JSONPath.of("$.profile.userInfo");

    public static final JSONPath MOBILE_HOME_JSON_PATH_NOTE_IDS = JSONPath.of("$.profile.noteData[*].id");

    public static final JSONPath MOBILE_DETAIL_JSON_PATH_NOTE_DATA = JSONPath.of("$.noteData.data.noteData");


}
