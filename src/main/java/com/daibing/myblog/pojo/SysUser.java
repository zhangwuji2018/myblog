package com.daibing.myblog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 */
@Data
public class SysUser implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String email;
    private Byte gender;
    private String avatar;
    private String blog;
    private String regIp;
    private String lastLoginIp;
    private Date lastLoginTime;
    private Integer loginCount;
    private String remark;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}