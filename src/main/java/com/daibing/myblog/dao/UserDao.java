package com.daibing.myblog.dao;

import com.daibing.myblog.pojo.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return
     */
    long countByUserName(@Param("username") String username);

    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     * @return
     */
    SysUser getUserByNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户id查出用户
     * @param userId 用户id
     * @return
     */
    SysUser getUserById(@Param("userId") Integer userId);

    /**
     * 更新用户信息
     * @param tempUser
     * @return
     */
    int updateUserById(SysUser tempUser);
}