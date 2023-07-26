package com.itplh.xhs.domain.common;

import lombok.Data;

/**
 * example:
 * {
 * "fileId": "761f9771-363d-887e-4f72-e68bd2aac8fe",
 * "height": 2048,
 * "width": 1536,
 * "url": "https:\u002F\u002Fsns-img-bd.xhscdn.com\u002F761f9771-363d-887e-4f72-e68bd2aac8fe",
 * "traceId": "1000g0082nopvbhok80005ols5ge6smgp50fbu60"
 * }
 *
 * @author: tanpenggood
 * @date: 2023-07-24 22:36
 */
@Data
public class Image {

    private String fileId;
    private String height;
    private String width;
    private String url;
    private String traceId;

}
