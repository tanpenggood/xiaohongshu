package com.itplh.xhs;

import com.itplh.xhs.domain.UserInfo;
import org.junit.Assert;
import org.junit.Test;

public class XhsCrawlabTest {

    @Test
    public void crawlHome() {
        // url带参数时，请求头可不携带cookie
        String url = "https://www.xiaohongshu.com/user/profile/64a91898000000001001e673?xhsshare=CopyLink&appuid=62064cd3000000001000acd1&apptime=1690553952";
        UserInfo userInfo1 = XhsCrawlab.getInstance().crawlHome(url);
        Assert.assertNotNull(userInfo1);
        Assert.assertNotNull(userInfo1.getRedId());
        // url不带参数时，请求头需要携带cookie
        String url2 = "https://www.xiaohongshu.com/user/profile/64a91898000000001001e673";
        UserInfo userInfo2 = XhsCrawlab.getInstance().crawlHome(url2);
        Assert.assertNotNull(userInfo2);
        Assert.assertNotNull(userInfo2.getRedId());
    }

    @Test
    public void crawlDetail() {
    }

    @Test
    public void setHeaders() {
    }

}
