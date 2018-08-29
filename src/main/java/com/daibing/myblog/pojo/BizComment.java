package com.daibing.myblog.pojo;

import lombok.Data;
import java.util.Date;

/**
 * 评论表
 */
@Data
public class BizComment {
    // ID
    private Integer id;
    // 文章
    private BizArticle article;
    // 评论人名称
    private String nickname;
    // 头像
    private String avatar;
    //IP地址
    private String ip;
    // 浏览器
    private String browser;
    // 评论内容
    private String content;
    // 备注(审核时添加)
    private String remark;
    // 点赞
    private Integer support;
    // 状态
    private Boolean status;
    // 添加时间
    private Date createTime;


}