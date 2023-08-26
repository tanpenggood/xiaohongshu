package com.itplh.xhs.util;

public class StringBuilderUtil {

    /**
     * 删除字符串最后一个字符
     *
     * @param sb
     * @return
     */
    public static StringBuilder deleteCharAtLast(StringBuilder sb) {
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb;
    }

}
