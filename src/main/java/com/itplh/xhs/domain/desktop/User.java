package com.itplh.xhs.domain.desktop;

import lombok.Data;

/**
 * example:
 * {
 * "avatar": "https:\u002F\u002Fsns-avatar-qc.xhscdn.com\u002Favatar\u002F62c299007212a335c889356d.jpg",
 * "userId": "62bc2c1c000000001b025a19",
 * "nickname": "懿安国学"
 * }
 *
 * @author: tanpenggood
 * @date: 2023-07-24 22:34
 */
@Data
public class User {

    private String userId;
    private String nickname;
    private String avatar;

}
