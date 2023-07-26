package com.itplh.xhs.domain.desktop;

import lombok.Data;

/**
 * example:
 * {
 * "nickname": "懿安国学",
 * "images": "https:\u002F\u002Fsns-avatar-qc.xhscdn.com\u002Favatar\u002F62c299007212a335c889356d.jpg?imageView2\u002F2\u002Fw\u002F360\u002Fformat\u002Fwebp",
 * "redId": "5671472025",
 * "gender": 0,
 * "ipLocation": "河南",
 * "desc": "赐子千金 不如赐子美名！！！\n宝宝起名 改名\n私❤️必回",
 * "imageb": "https:\u002F\u002Fsns-avatar-qc.xhscdn.com\u002Favatar\u002F62c299007212a335c889356d.jpg?imageView2\u002F2\u002Fw\u002F540\u002Fformat\u002Fwebp"
 * }
 *
 * @author: tanpenggood
 * @date: 2023-07-26 00:31
 */
@Data
public class BasicInfo {

    private String nickname;
    private String images;
    private String redId;
    private int gender;
    private String ipLocation;
    private String desc;
    private String imageb;

}
