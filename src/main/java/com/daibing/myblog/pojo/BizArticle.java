package com.daibing.myblog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 文章类
 * @author: daibing
 * @create: 2018-07-20 14:44
 **/
@Data
public class BizArticle implements Serializable {
    // 文章id
    private Integer id;
    // 标题
    private String title;
    //用户id
    private Integer userId;
    // 背静图片
    private String coverImage;
    // 位置
    private Integer top;
    // 分类
    private BizType type;
    // 状态 0代表草稿 1代表发布
    private Integer status;
    // 是否推荐
    private Integer recommended;
    // 是否原创
    private Integer original;
    // 文章描述
    private String introduction;
    // 关键词
    private String keywords;
    // 文章内容
    private String content;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;
}
