package com.itplh.xhs.util;

import com.itplh.absengine.util.StringUtils;

import java.util.Optional;

/**
 * @author: tanpenggood
 * @date: 2023-08-05 13:53
 */
public class NumberUtil {

    public static boolean isNumber(String text) {
        if (StringUtils.isBlank(text)) {
            return false;
        }
        return text.matches("\\d+");
    }

    public static Integer valueOf(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        return Optional.ofNullable(text)
                .filter(NumberUtil::isNumber)
                .map(Integer::valueOf)
                .orElse(null);
    }

}
