package com.itplh.xhs.domain.desktop;

import lombok.Data;

/**
 * example:
 * {"type":"follows","name":"关注","count":"1"}
 *
 * @author: tanpenggood
 * @date: 2023-07-26 00:22
 */
@Data
public class Interaction {

    private String type;
    private String name;
    private String count;

}
