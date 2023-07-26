package com.itplh.xhs.util;

import com.alibaba.fastjson2.JSONPath;
import com.itplh.absengine.util.FileUtils;
import com.itplh.xhs.domain.desktop.BasicInfo;
import com.itplh.xhs.domain.desktop.Interaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author: tanpenggood
 * @date: 2023-07-26 08:42
 */
public class JSONUtilTest {

    private String json;

    @Before
    public void before() {
        List<String> list = FileUtils.readAllLinesByClasspath("classpath:/desktop/home.json");
        StringBuilder builder = new StringBuilder();
        list.forEach(builder::append);
        json = builder.toString();
    }

    @Test
    public void extract() {
        Assert.assertNotNull(JSONUtil.extract(json, JSONPath.of("$.user.userPageData")));
        Assert.assertNotNull(JSONUtil.extract(json, JSONPath.of("$.user.userPageData.result.code")));
        Assert.assertNotNull(JSONUtil.extract(json, JSONPath.of("$.user.userPageData.result.success")));
        Assert.assertNotNull(JSONUtil.extract(json, JSONPath.of("$.user.userPageData.result.message")));
        Assert.assertNotNull(JSONUtil.extract(json, JSONPath.of("$.user.userPageData.basicInfo")));
        Assert.assertNotNull(JSONUtil.extract(json, JSONPath.of("$.user.userPageData.interactions")));
    }

    @Test
    public void extractString() {
        String message = JSONUtil.extractString(json, JSONPath.of("$.user.userPageData.result.message"));
        Assert.assertEquals("success", message);
    }

    @Test
    public void extractBool() {
        boolean success = JSONUtil.extractBool(json, JSONPath.of("$.user.userPageData.result.success"));
        Assert.assertEquals(true, success);
    }

    @Test
    public void extractInt() {
        int result = JSONUtil.extractInt(json, JSONPath.of("$.user.userPageData.result.code"));
        Assert.assertEquals(0, result);
    }

    @Test
    public void extractObject() {
        BasicInfo basicInfo = JSONUtil.extractBean(json, JSONPath.of("$.user.userPageData.basicInfo"), BasicInfo.class);
        Assert.assertNotNull(basicInfo);
        Assert.assertEquals("懿安国学", basicInfo.getNickname());
    }

    @Test
    public void extractList() {
        List<Interaction> interactions = JSONUtil.extractList(json, JSONPath.of("$.user.userPageData.interactions"), Interaction.class);
        Assert.assertNotNull(interactions);
        Assert.assertEquals(3, interactions.size());

        List<String> counts = JSONUtil.extractList(json, JSONPath.of("$.user.userPageData.interactions[*].count"), String.class);
        Assert.assertNotNull(counts);
        Assert.assertEquals(3, counts.size());
        Assert.assertEquals("1", counts.get(0));
        Assert.assertEquals("2712", counts.get(1));
        Assert.assertEquals("41753", counts.get(2));
    }

}
