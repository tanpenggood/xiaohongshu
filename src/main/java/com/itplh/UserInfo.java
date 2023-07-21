package com.itplh;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: tanpenggood
 * @date: 2023-07-21 20:47
 */
@Getter
@Setter
public class UserInfo {

    private String nickname;
    private String fans;
    private String follows;
    private String likeAndCollect;
    private String redId;
    private String desc;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("nickname:").append(nickname).append("\n");
        builder.append("fans:").append(fans).append(" ").append("follows:").append(follows).append("\n");
        builder.append("likeAndCollect:").append(likeAndCollect).append("\n");
        builder.append("redId:").append(redId).append("\n");
        builder.append("desc:").append(desc).append("\n");
        return builder.toString();
    }

}
