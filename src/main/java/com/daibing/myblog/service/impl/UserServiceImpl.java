package com.daibing.myblog.service.impl;

import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.dao.UserDao;
import com.daibing.myblog.pojo.SysUser;
import com.daibing.myblog.service.UserService;
import com.daibing.myblog.utils.BlogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

/**
 * @program: myblog
 * @description:
 * @author: daibing
 * @create: 2018-08-10 23:08
 **/
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public SysUser login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new TipException("用户名和密码不能为空");
        }
        long count = userDao.countByUserName(username);
        if (count < 1) {
            throw new TipException("不存在该用户");
        }
        String pwd = BlogUtils.MD5encode(username+password);
        SysUser user = userDao.getUserByNameAndPwd(username, pwd);
        if (user == null) {
            throw new TipException("用户名或密码错误");
        }
        return user;
    }

    @Override
    public SysUser getUserById(Integer userId) {
        SysUser user = null;
        if (userId != null) {
            user = userDao.getUserById(userId);
        }
       return user;
    }

    @Override
    public void updateUserById(SysUser tempUser) {
        if (tempUser == null && tempUser.getId() == null) {
            throw new TipException("更新的用户参数信息为空");
        }

        int result = userDao.updateUserById(tempUser);
        if (result != 1) {
            throw new TipException("更新用户信息出现异常,返回值不等于1");
        }
    }

}
