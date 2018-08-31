package com.daibing.myblog.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @auther: daibing
 * @date: 2018/8/31 10:45
 */
@Data
public class BizTagsDto implements Serializable {
    // 标签id
    private Long id;
    // 标签名字
    private String name;
    // 描述
    private String description;
    // 创建时间
    private Date createTime;
    // 文章的数量
    private Integer articleNum;
}
