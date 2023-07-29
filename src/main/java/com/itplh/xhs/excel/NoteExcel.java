package com.itplh.xhs.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: tanpenggood
 * @date: 2023-07-26 13:52
 */
@Data
public class NoteExcel {

    @ExcelProperty("用户")
    @ColumnWidth(20)
    private String nickname;

    @ExcelProperty("标题")
    @ColumnWidth(20)
    private String title;

    @ExcelProperty("发布时间")
    @ColumnWidth(24)
    private LocalDateTime publishTime;

    @ExcelProperty("笔记链接")
    @ColumnWidth(20)
    private String noteUrl;

    @ExcelProperty("点赞")
    private Integer likedCount;

    @ExcelProperty("收藏")
    private Integer collectedCount;

    @ExcelProperty("评论")
    private Integer commentCount;

    @ExcelProperty("分享")
    private Integer shareCount;

    @ExcelProperty("话题")
    private String tags;

    @ExcelProperty("正文")
    private String desc;

    @ExcelProperty("配图链接")
    private String imageUrls;

}
