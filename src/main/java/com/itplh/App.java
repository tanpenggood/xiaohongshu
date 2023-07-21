package com.itplh;

import com.itplh.absengine.script.DelayVariable;
import com.itplh.absengine.util.HttpUtils;
import com.jayway.jsonpath.JsonPath;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author: tanpenggood
 * @date: 2023-07-21 00:08
 */
public class App {

    static DelayVariable delay1Second = new DelayVariable(1, TimeUnit.SECONDS);

    public static void main(String[] args) {

        List<String> users = Arrays.asList(
                "https://www.xiaohongshu.com/user/profile/64672d6500000000290173c7", // 栖梧起名
                "https://www.xiaohongshu.com/user/profile/645de545000000002a00bdd7", // 昱安起名
                "https://www.xiaohongshu.com/user/profile/62bc2c1c000000001b025a19" // 懿安国学
        );

        for (String user : users) {
            spider(user);
        }
    }

    public static void spider(String homeUrl) {
        System.out.println(homeUrl);
        Document document = HttpUtils.requestGet(homeUrl, delay1Second).get();

        UserInfo userInfo = new UserInfo();
        parseJsonStrFromScript("__INITIAL_STATE__", document).ifPresent(jsonString -> {
            Map<String, String> userInfoMap = JsonPath.read(jsonString, "$.profile.userInfo");
            userInfo.setNickname(userInfoMap.get("nickname"));
            userInfo.setFans(userInfoMap.get("fans"));
            userInfo.setFollows(userInfoMap.get("follows"));
            userInfo.setLikeAndCollect(userInfoMap.get("likeAndCollect"));
            userInfo.setRedId(userInfoMap.get("redId"));
            userInfo.setDesc(userInfoMap.get("desc"));
            System.out.println(userInfo);
        });

        Elements elements = document.getElementsByTag("section");
        for (int i = 0; i < elements.size(); i++) {
            int no = i + 1;
            System.out.println(String.format("NO.%s %s fans:%s start ==================================================", no, userInfo.getNickname(), userInfo.getFans()));
            Element element = elements.get(i);
            // detail url
            String id = element.attr("id");
            String detailUrl = "https://www.xiaohongshu.com/explore/" + id;
            System.out.println("detailUrl: " + detailUrl);
            detail(detailUrl);
            System.out.println(String.format("NO.%s %s fans:%s end ====================================================", no, userInfo.getNickname(), userInfo.getFans()));
        }
    }

    public static void detail(String detailUrl) {
        Document document = HttpUtils.requestGet(detailUrl, delay1Second).get();
        // parse note data - json
        parseJsonStrFromScript("__INITIAL_STATE__", document).ifPresent(jsonString -> {
            // title likes collects shareCount comments desc
            Map noteData = JsonPath.read(jsonString, "$.noteData.data.noteData");
            System.out.println(String.format("title:%s", noteData.get("title")));
            System.out.println(String.format("likes:%s collects:%s", noteData.get("likes"), noteData.get("collects")));
            System.out.println(String.format("shareCount:%s comments:%s", noteData.get("shareCount"), noteData.get("comments")));
            System.out.println(String.format("publish time:%s", noteData.get("time")));
            System.out.println();
            System.out.println(String.format("desc:%s", noteData.get("desc")));
            // topic
            List<Map<String, String>> tags = JsonPath.read(jsonString, "$.noteData.data.noteData.hashTags");
            StringBuilder tagBuilder = new StringBuilder();
            for (Map<String, String> tag : tags) {
                tagBuilder.append("#").append(tag.get("name")).append(" ");
            }
            tagBuilder.deleteCharAt(tagBuilder.length() - 1);
            System.out.println(tagBuilder);
            // image list
            List<String> imageList = JsonPath.read(jsonString, "$.noteData.data.noteData.imageList..url");
            for (String imageUrl : imageList) {
                System.out.println("http:" + imageUrl);
            }
        });
    }

    private static Optional<String> parseJsonStrFromScript(String keywords, Element element) {
        Optional<Element> scriptOptional = element.getElementsByTag("script").stream()
                .filter(e -> e.toString().contains(keywords))
                .findFirst();
        return scriptOptional.map(e -> {
            int beginIndex = e.toString().indexOf(keywords) + keywords.length() + 1;
            int endIndex = e.toString().lastIndexOf("}") + 1;
            return e.toString().substring(beginIndex, endIndex);
        });
    }

}
