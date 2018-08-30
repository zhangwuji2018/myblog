package com.daibing.myblog.pojo;

import lombok.Data;
import java.util.Date;
/**
 * @description: 链接表
 * @author: daibing
 * @create: 2018-08-30 14:56
 **/
@Data
public class SysLink {
    /**
     * id
     */
    private Integer id;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 链接名称
     */
    private String name;

    /**
     * 链接描述
     */
    private String description;

    /**
     * 图标
     */
    private String favicon;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}