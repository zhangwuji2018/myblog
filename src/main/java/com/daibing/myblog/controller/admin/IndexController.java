package com.daibing.myblog.controller.admin;

import com.daibing.myblog.bo.RestResponseBo;
import com.daibing.myblog.dto.LogActions;
import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.pojo.SysUser;
import com.daibing.myblog.service.LogService;
import com.daibing.myblog.service.UserService;
import com.daibing.myblog.utils.BlogUtils;
import com.daibing.myblog.utils.WebConstant;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @program: myblog
 * @description: 后台管理首页控制器
 * @author: daibing
 * @create: 2018-08-12 15:40
 **/
@Controller
@RequestMapping("/admin")
public class IndexController {
    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    /**
     * 主页面
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    /**
     * 个人设置界面
     * @return
     */
    @GetMapping("/profile")
    public String profile() {
        return "admin/profile";
    }

    /**
     * admin 退出登录
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(WebConstant.LOGIN_SESSION_KEY);
        return "admin/login";
    }

    /**
     * 保存个人信息
     * @param user
     * @param request
     * @param session
     * @return
     */
    @PostMapping("/profile")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo saveProfile(SysUser user, HttpServletRequest request, HttpSession session) {
        LOGGER.info("请求参数 user----"+user);
        // 获取登录用户信息
        SysUser loginUser = BlogUtils.getLoginUser(request);
        boolean flag = StringUtils.isBlank(user.getNickname()) && StringUtils.isBlank(user.getMobile()) && StringUtils.isBlank(user.getEmail());
        if (flag) {
            return RestResponseBo.fail("用户信息不能为空");
        }
        SysUser tempUser = new SysUser();
        tempUser.setId(loginUser.getId());
        tempUser.setNickname(user.getNickname());
        tempUser.setMobile(user.getMobile());
        tempUser.setEmail(user.getEmail());
        // 修改用户信息
        userService.updateUserById(tempUser);
        // 写入日志
        logService.insertLog(LogActions.UP_INFO.getAction(), BlogUtils.toJsonString(tempUser), request.getRemoteAddr(), loginUser.getId());

        // 更新session中信息
        SysUser attribute = (SysUser) session.getAttribute(WebConstant.LOGIN_SESSION_KEY);
        attribute.setNickname(user.getNickname());
        attribute.setMobile(user.getMobile());
        attribute.setEmail(user.getEmail());
        session.setAttribute(WebConstant.LOGIN_SESSION_KEY,attribute);
        return RestResponseBo.ok();
    }

    @PostMapping("/password")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo updatePassword(String oldPassword, String newPassword, String confirmPassword, HttpServletRequest request, HttpSession session) {
        SysUser loginUser = BlogUtils.getLoginUser(request);
        if (StringUtils.isBlank(oldPassword) && StringUtils.isBlank(newPassword)) {
            return RestResponseBo.fail("请确认信息输入完整");
        }
        // 判断旧密码是否正确
        boolean flag = loginUser.getPassword().equals(BlogUtils.MD5encode(loginUser.getUsername()+oldPassword));
        if (!flag) {
            return RestResponseBo.fail("旧密码输入有误");
        }
        if (newPassword.length() < 6 || newPassword.length() > 14) {
            return RestResponseBo.fail("请输入6-14位密码");
        }
        try {
            SysUser user = new SysUser();
            user.setId(loginUser.getId());
            user.setPassword(BlogUtils.MD5encode(loginUser.getUsername()+newPassword));
            userService.updateUserById(user);

            return RestResponseBo.ok();
        } catch (Exception e) {
            String msg = "密码修改失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            }else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
    }

}
