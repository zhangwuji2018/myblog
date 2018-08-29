package com.daibing.myblog.service;

import com.daibing.myblog.pojo.SysUser;

/**
 * @program: myblog
 * @description: 用户服务接口
 * @author: daibing
 * @create: 2018-08-10 23:06
 **/
public interface UserService {

    /**
     * 用户登陆
     * @param username 用户名
     * @param password 密码
     * @return
     */
    SysUser login(String username, String password);

    /**
     * 根据用户id查找用户
     * @param userId 用户id
     * @return
     */
    SysUser getUserById(Integer userId);

    /**
     * 更新用户信息
     * @param tempUser
     */
    void updateUserById(SysUser tempUser);

}
