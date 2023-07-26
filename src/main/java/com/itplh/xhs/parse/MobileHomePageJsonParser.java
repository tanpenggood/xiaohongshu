package com.itplh.xhs.parse;

import com.itplh.xhs.constant.DeviceEnum;
import com.itplh.xhs.constant.JSONPathConstant;
import com.itplh.xhs.constant.URLConstant;
import com.itplh.xhs.domain.UserInfo;
import com.itplh.xhs.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: tanpenggood
 * @date: 2023-07-21 22:35
 */
@Slf4j
public class MobileHomePageJsonParser implements HomeJsonParser {

    @Override
    public UserInfo parse(String initialStateJson) {
        log.debug(".");
        UserInfo userInfo = JSONUtil.extractBean(initialStateJson, JSONPathConstant.MOBILE_HOME_JSON_PATH_USER_INFO, UserInfo.class);
        userInfo.setDevice(DeviceEnum.MOBILE);
        userInfo.setIpLocation("地球的某一片红薯地");
        // note id list
        List<String> noteIds = JSONUtil.extractList(initialStateJson, JSONPathConstant.MOBILE_HOME_JSON_PATH_NOTE_IDS, String.class);
        List<String> noteDetailUrls = noteIds.stream()
                .map(id -> URLConstant.PREFIX_DETAIL + id)
                .collect(Collectors.toList());
        userInfo.setNoteDetailUrls(noteDetailUrls);
        return userInfo;
    }

}
