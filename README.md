<div align="center">

<h1 align="center">
🕷️xiaohongshu
</h1>

</div>

> **Warning**

> The primary purpose of this repository is to learn. It is important to note that web crawling may
> be considered illegal, and therefore, it is crucial to refrain from exerting any pressure or engaging in unauthorized
> activities on websites.


# Introduction

[tanpenggood/xiaohongshu](https://github.com/tanpenggood/xiaohongshu) is a crawling application designed to extract data from [xiaohongshu](https://www.xiaohongshu.com/explore) page.

**crawling data range**: only parsed data in `window.__INITIAL_STATE__` of xiaohongshu page.

# Development Environment

- windows 11
- jdk 1.8
- maven 3.6.0

# Useage

## Use UI

Run `com.itplh.xhs.XhsCrawlabUI`

See:

![home.png](home.png)

## Use API

reference test class: `com.itplh.xhs.XhsCrawlabTest`

```java
public class XhsCrawlabTest {

    @Test
    public void crawlHome() {
        // url带参数时，请求头可不携带cookie
        String url = "https://www.xiaohongshu.com/user/profile/64a91898000000001001e673?xhsshare=CopyLink&appuid=62064cd3000000001000acd1&apptime=1690553952";
        UserInfo userInfo1 = XhsCrawlab.crawlHome(url);
        Assert.assertNotNull(userInfo1);
        Assert.assertNotNull(userInfo1.getRedId());
        // url不带参数时，请求头需要携带cookie
        String url2 = "https://www.xiaohongshu.com/user/profile/64a91898000000001001e673";
        UserInfo userInfo2 = XhsCrawlab.crawlHome(url2);
        Assert.assertNotNull(userInfo2);
        Assert.assertNotNull(userInfo2.getRedId());
    }
}
```

# Project Structure

```
xiaohongshu
├── src/main
│   ├── java/com.itplh.xhs       
│   │   ├── constant
│   │   ├── domain
│   │   ├── excel            # generate excel, use easyexcel
│   │   ├── parse            # parse json data (parse window.__INITIAL_STATE__)
│   │   ├── ui               # build ui, use javafx    
│   │   ├── util               
│   │   ├── XhsCrawlab       # core api   
│   │   └── XhsCrawlabUI     # ui
│   └── resources
│       ├── desktop          # response data of desktop access xiaohongshu
│       ├── mobile           # response data of mobile access xiaohongshu
│       └── logback.xml      # log config
├── src/test/java            # unit test
├── pom.xml
└── README.md
```

# Technology Stack

- Java:1.8
- [auto-browser-script-engine:1.1.2](https://github.com/tanpenggood/auto-browser-script-engine)
- jsoup:1.15.2
- fastjson2:2.0.15
- lombok:1.18.12
- logback-classic:1.2.3
- junit:4.13

# Build

```bash
mvn clean package -Dmaven.test.skip=true
```
