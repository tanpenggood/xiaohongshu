package com.itplh.xhs;

import com.itplh.absengine.script.DelayVariable;
import com.itplh.absengine.util.CollectionUtils;
import com.itplh.absengine.util.HttpUtils;
import com.itplh.absengine.util.StringUtils;
import com.itplh.xhs.domain.UserInfo;
import com.itplh.xhs.parse.JsonParserSelector;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: tanpenggood
 * @date: 2023-07-21 00:08
 */
public class XhsCrawlab {

    private volatile static XhsCrawlab instance;

    private DelayVariable delay1Second = new DelayVariable(1, TimeUnit.SECONDS);

    private Map<String, String> headers = new HashMap();

    {
        // PC
        this.headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
        // Android
//        headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko)");
    }

    private XhsCrawlab() {
        // 私有构造方法，防止外部实例化
    }

    public static XhsCrawlab getInstance() {
        if (instance == null) {
            synchronized (XhsCrawlab.class) {
                if (instance == null) {
                    instance = new XhsCrawlab();
                }
            }
        }
        return instance;
    }

    public XhsCrawlab setHeaders(Map<String, String> headers) {
        if (CollectionUtils.isEmpty(headers)) {
            return this;
        }
        this.headers.putAll(headers);
        return this;
    }

    public XhsCrawlab setCookie(String cookie) {
        if (StringUtils.isBlank(cookie)) {
            this.headers.put("cookie", "");
            return this;
        }
        if (cookie.startsWith("\"") && cookie.endsWith("\"")) {
            cookie = cookie.substring(1, cookie.length() - 1);
        }
        if (cookie.startsWith("'") && cookie.endsWith("'")) {
            cookie = cookie.substring(1, cookie.length() - 1);
        }
        this.headers.put("cookie", cookie);
        return this;
    }

    public String getCookie() {
        return this.headers.getOrDefault("cookie", "");
    }

    public UserInfo crawlHome(String homeUrl) {
        System.out.println(homeUrl);
        Document document = HttpUtils.requestGet(homeUrl, headers, delay1Second).get();

        UserInfo userInfo = JsonParserSelector.parseHomePageJson(document);
        System.out.println(userInfo.toString());

        if (userInfo.isDesktop()) {
            return userInfo;
        }

        List<String> detailUrls = userInfo.getNoteDetailUrls();
        for (int i = 0; i < detailUrls.size(); i++) {
            int no = i + 1;
            System.out.println(String.format("================================================== NO.%s %s fans:%s notes:%s start", no, userInfo.getNickname(), userInfo.getFans(), detailUrls.size()));
            // crawlDetail url
            String detailUrl = detailUrls.get(i);
            System.out.println("detailUrl: " + detailUrl);
            crawlDetail(detailUrl);
            System.out.println(String.format("================================================== NO.%s %s fans:%s notes:%s end", no, userInfo.getNickname(), userInfo.getFans(), detailUrls.size()));
        }
        return userInfo;
    }

    public void crawlDetail(String detailUrl) {
        Document document = HttpUtils.requestGet(detailUrl, headers, delay1Second).get();
        // parse note data - json
        JsonParserSelector.parseDetailPageJson(document);
    }

}
