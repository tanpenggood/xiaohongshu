package com.itplh.xhs.ui;

import lombok.Data;

/**
 * @author: tanpenggood
 * @date: 2023-07-26 19:23
 */
@Data
public class User {

    private String nickname;
    private String homeUrl;

    public User(String nickname, String homeUrl) {
        this.nickname = nickname;
        this.homeUrl = homeUrl;
    }

}
