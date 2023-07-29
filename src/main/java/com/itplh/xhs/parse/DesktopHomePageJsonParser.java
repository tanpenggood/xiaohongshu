package com.itplh.xhs.parse;

import com.itplh.xhs.constant.DeviceEnum;
import com.itplh.xhs.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;

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
        return userInfo;
    }

}
