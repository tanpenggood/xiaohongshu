package com.itplh.xhs;

import com.itplh.absengine.script.DelayVariable;
import com.itplh.absengine.util.HttpUtils;
import com.itplh.xhs.domain.UserInfo;
import com.itplh.xhs.parse.JsonParserSelector;
import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: tanpenggood
 * @date: 2023-07-21 00:08
 */
public class App {

    public static void main(String[] args) {

        List<String> users = Arrays.asList(
//                "https://www.xiaohongshu.com/user/profile/64672d6500000000290173c7", // 栖梧起名
//                "https://www.xiaohongshu.com/user/profile/645de545000000002a00bdd7", // 昱安起名
                "https://www.xiaohongshu.com/user/profile/62bc2c1c000000001b025a19" // 懿安国学
        );

        for (String user : users) {
            spider(user);
        }
    }

    public static void spider(String homeUrl) {
        System.out.println(homeUrl);
        Document document = HttpUtils.requestGet(homeUrl, headers, delay1Second).get();

        UserInfo userInfo = JsonParserSelector.parseHomePageJson(document);
        System.out.println(userInfo.toString());

        if (userInfo.isDesktop()) {
            return;
        }

        List<String> detailUrls = userInfo.getNoteDetailUrls();
        for (int i = 0; i < detailUrls.size(); i++) {
            int no = i + 1;
            System.out.println(String.format("================================================== NO.%s %s fans:%s notes:%s start", no, userInfo.getNickname(), userInfo.getFans(), detailUrls.size()));
            // detail url
            String detailUrl = detailUrls.get(i);
            System.out.println("detailUrl: " + detailUrl);
            detail(detailUrl);
            System.out.println(String.format("================================================== NO.%s %s fans:%s notes:%s end", no, userInfo.getNickname(), userInfo.getFans(), detailUrls.size()));
        }
    }

    public static void detail(String detailUrl) {
        Document document = HttpUtils.requestGet(detailUrl, headers, delay1Second).get();
        // parse note data - json
        JsonParserSelector.parseDetailPageJson(document);
    }

    private static DelayVariable delay1Second = new DelayVariable(1, TimeUnit.SECONDS);

    private static Map<String, String> headers = new HashMap();

    static {
        headers.put("cookie", "a1=189740d9141vtgdxvnnlyxs9snsf3rmeeftx96nv230000320550; webId=1f9f30cf48f276899f57fccc10a18596; gid=yYjW48fjWKd2yYjW48fjyiKI4yh9kfChII1v9hJCMjMIMCq8iqF6dj888qJ82288YWjDJ8Wj; cache_feeds=[]; web_session=040069b23c59f037b9a3da138c364b0dee0d1a; xsecappid=xhs-pc-web; webBuild=2.17.8; websectiga=984412fef754c018e472127b8effd174be8a5d51061c991aadd200c69a2801d6; sec_poison_id=4e6e1799-dc39-49b5-bb3a-241a1fe8dc26");
        // PC
//        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
        // Android
//        headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko)");
    }

}
