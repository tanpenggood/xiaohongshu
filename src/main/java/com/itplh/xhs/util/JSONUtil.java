package com.itplh.xhs.util;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.itplh.absengine.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author: tanpenggood
 * @date: 2023-07-26 08:34
 */
public class JSONUtil {

    public static Object extract(String json, JSONPath path) {
        if (StringUtils.isBlank(json) || Objects.isNull(path)) {
            return null;
        }
        return path.extract(JSONReader.of(json));
    }

    public static String extractString(String json, JSONPath path) {
        return ((String) extract(json, path));
    }

    public static Integer extractInt(String json, JSONPath path) {
        return ((Integer) extract(json, path));
    }

    public static Long extractLong(String json, JSONPath path) {
        return ((Long) extract(json, path));
    }

    public static Boolean extractBool(String json, JSONPath path) {
        return ((Boolean) extract(json, path));
    }

    public static <T> T extractBean(String json, JSONPath path, Class<T> clazz) {
        return ((JSONObject) extract(json, path)).to(clazz);
    }

    public static <T> List<T> extractList(String json, JSONPath path, Class<T> itemClass) {
        return ((JSONArray) extract(json, path)).toList(itemClass);
    }

}
