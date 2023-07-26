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

**crawling data range**: only parsed data in ` window.__INITIAL_STATE__` of xiaohongshu page.

# Useage

1. Set some user homepage link of xiaohongshu user that you want to crawl.

2. Set your cookie in headers, it's optional.

3. Run `com.itplh.xhs.App`

# Project Structure

```
xiaohongshu
├── src/main
│   ├── java/com.itplh.xhs       
│   │   ├── constant
│   │   ├── domain
│   │   ├── parse            # parse json data
│   │   ├── util               
│   │   └── App              # main
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
