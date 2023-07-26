package com.itplh.xhs;

import com.alibaba.fastjson2.JSONPath;
import com.itplh.absengine.util.FileUtils;
import com.itplh.xhs.util.JSONUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author: tanpenggood
 * @date: 2023-07-21 21:13
 */
public class JsonPathSyntaxTest {

    private String json;

    @Before
    public void before() {
        List<String> list = FileUtils.readAllLinesByClasspath("classpath:/mobile/home.json");
        StringBuilder builder = new StringBuilder();
        list.forEach(builder::append);
        json = builder.toString();
    }

    /**
     * 解析id属性，会遍历解析深层子元素的同名属性
     */
    @Test
    public void testSyntax1() {
        List<String> list = JSONUtil.extractList(json, JSONPath.of("$.profile.noteData..id"), String.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(12, list.size());
    }

    /**
     * 解析id属性，不会遍历解析深层子元素的同名属性
     */
    @Test
    public void testSyntax2() {
        List<String> list = JSONUtil.extractList(json, JSONPath.of("$.profile.noteData[*].id"), String.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(6, list.size());
    }

}
