package com.itplh.xhs.parse;

import com.itplh.xhs.domain.UserInfo;

/**
 * @author: tanpenggood
 * @date: 2023-07-21 22:35
 */
public interface HomeJsonParser {

    UserInfo parse(String initialStateJson);

}
