package com.daibing.myblog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章和标签对应类
 */
@Data
public class BizArticleTags implements Serializable {

    // 标签id
    private Integer tagId;

    // 文章id
    private Integer articleId;

    private Date createTime;

}