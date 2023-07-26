package com.itplh.xhs.domain.desktop;

import lombok.Data;

/**
 * example:
 * {
 * "commentCount": "736",
 * "shareCount": "1731",
 * "followed": false,
 * "sticky": true,
 * "liked": false,
 * "likedCount": "8228",
 * "collected": false,
 * "collectedCount": "9490"
 * }
 *
 * @author: tanpenggood
 * @date: 2023-07-24 23:28
 */
@Data
public class InteractInfo {

    private String likedCount;
    private String collectedCount;
    private String commentCount;
    private String shareCount;
    private boolean followed;
    private boolean sticky;
    private boolean liked;
    private boolean collected;

}
