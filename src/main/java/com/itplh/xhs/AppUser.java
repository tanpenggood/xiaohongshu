package com.itplh.xhs;

import lombok.Data;

/**
 * @author: tanpenggood
 * @date: 2023-07-26 19:23
 */
@Data
public class AppUser {

    private String nickname;
    private String homeUrl;

    public AppUser(String nickname, String homeUrl) {
        this.nickname = nickname;
        this.homeUrl = homeUrl;
    }

}
