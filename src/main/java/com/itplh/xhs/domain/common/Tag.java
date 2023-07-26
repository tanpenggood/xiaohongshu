package com.itplh.xhs.domain.common;

import lombok.Data;

/**
 * example:
 * {
 * "id": "53d82139b4c4d67596c79774",
 * "name": "母婴",
 * "type": "topic"
 * }
 *
 * @author: tanpenggood
 * @date: 2023-07-24 22:36
 */
@Data
public class Tag {

    private String id;
    private String name;
    private String type;

}
