package com.itplh;

import com.itplh.absengine.util.FileUtils;
import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author: tanpenggood
 * @date: 2023-07-21 21:13
 */
public class JsonPathTest {

    private String json;

    @Before
    public void before() {
        List<String> list = FileUtils.readAllLinesByClasspath("classpath:detail.json");
        StringBuilder builder = new StringBuilder();
        list.forEach(builder::append);
        json = builder.toString();
    }

    @Test
    public void testGetDeepAttr() {
        List<String> imageList = JsonPath.read(json, "$.noteData.data.noteData.imageList..url");
        Assert.assertEquals(6, imageList.size());

        for (String imageUrl : imageList) {
            System.out.println(imageUrl);
        }
    }

}
