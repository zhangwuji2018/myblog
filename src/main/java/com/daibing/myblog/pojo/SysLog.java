package com.daibing.myblog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志类
 */
@Data
public class SysLog implements Serializable {
    //日志主键
    private Integer id;
    //产生的动作
    private String action;
    //产生的数据
    private String data;
    //用户的ID
    private Integer userId;
    //日志产生的IP地址
    private String ip;
    //创建时间
    private Date createTime;
}