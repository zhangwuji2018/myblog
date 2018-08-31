package com.daibing.myblog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: 文章分类
 * @author: daibing
 * @create: 2018-07-26 14:55
 **/
@Data
public class BizType implements Serializable {
    // 分类id
    private Integer id;
    // 名称
    private String name;
    // 描述
    private String description;
    // 图标
    private String icon;
    // 是否可用
    private Integer available;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;
    // 文章集合
    private List<BizArticle> articles;
}
