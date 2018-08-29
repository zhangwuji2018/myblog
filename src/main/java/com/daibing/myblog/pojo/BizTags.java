package com.daibing.myblog.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章标签表
 */
@Data
public class BizTags implements Serializable {
    // 标签id
    private Long id;
    // 标签名字
    private String name;
    // 描述
    private String description;
    // 创建时间
    private Date createTime;
}