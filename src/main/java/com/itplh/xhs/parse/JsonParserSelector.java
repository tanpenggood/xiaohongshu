package com.itplh.xhs.parse;

import com.alibaba.fastjson2.JSONPath;
import com.itplh.xhs.domain.UserInfo;
import com.itplh.xhs.util.JSONUtil;
import org.jsoup.nodes.Element;

import java.util.Optional;

/**
 * @author: tanpenggood
 * @date: 2023-07-21 22:35
 */
public class JsonParserSelector {

    public static final String INITIAL_STATE = "__INITIAL_STATE__";

    public static UserInfo parseHomePageJson(Element element) {
        UserInfo[] userInfo = {new UserInfo()};
        parseJsonStrFromScript(INITIAL_STATE, element).ifPresent(json -> {
            HomeJsonParser jsonParser = isDesktop(json) ? new DesktopHomePageJsonParser() : new MobileHomePageJsonParser();
            userInfo[0] = jsonParser.parse(json);
        });
        return userInfo[0];
    }

    public static void parseDetailPageJson(Element element) {
        parseJsonStrFromScript(INITIAL_STATE, element).ifPresent(json -> {
            DetailJsonParser jsonParser = isDesktop(json) ? new DesktopDetailPageJsonParser() : new MobileDetailPageJsonParser();
            jsonParser.parse(json);
        });
    }

    /**
     * @param json
     * @return
     */
    private static boolean isDesktop(String json) {
        Object login = JSONUtil.extract(json, JSONPath.of("$.user"));
        return login != null;
    }

    private static Optional<String> parseJsonStrFromScript(String keywords, Element element) {
        Optional<Element> scriptOptional = element.getElementsByTag("script").stream()
                .filter(e -> e.toString().contains(keywords))
                .findFirst();
        return scriptOptional.map(e -> {
            int beginIndex = e.toString().indexOf(keywords) + keywords.length() + 1;
            int endIndex = e.toString().lastIndexOf("}") + 1;
            String json = e.toString().substring(beginIndex, endIndex);
            return jsonFormat(json);
        });
    }

    /**
     * Example:
     * <p>
     * {"login": {"loginMethod": undefined}} format to {"login": {"loginMethod": “undefined”}}
     *
     * @param json
     * @return
     */
    private static String jsonFormat(String json) {
        StringBuilder jsonBuilder = new StringBuilder();
        String[] jsonChunks = json.split("undefined");
        for (int i = 0; i < jsonChunks.length; i++) {
            String chunk = jsonChunks[i];
            if (i > 0 && !chunk.startsWith("\"")) {
                jsonBuilder.append('"').append("undefined").append('"');
            }
            jsonBuilder.append(chunk);
        }
        return jsonBuilder.toString();
    }

}
