package com.daibing.myblog.controller.admin;

import com.daibing.myblog.bo.RestResponseBo;
import com.daibing.myblog.dto.LogActions;
import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.pojo.SysUser;
import com.daibing.myblog.service.LogService;
import com.daibing.myblog.service.UserService;
import com.daibing.myblog.utils.BlogUtils;
import com.daibing.myblog.utils.IpUtils;
import com.daibing.myblog.utils.MapCache;
import com.daibing.myblog.utils.WebConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: myblog
 * @description: 用户后台登陆和退出,有异常回滚
 * @author: daibing
 * @create: 2018-08-10 21:52
 **/
@Controller
@RequestMapping("/admin")
@Transactional(rollbackFor = TipException.class)
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    //缓存
    private MapCache cache = MapCache.single();

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;


    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public RestResponseBo doLogin(@RequestParam String username,
                                  @RequestParam String password,
                                  @RequestParam(required = false) String remeber_me,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        Integer error_count = cache.get("login_error_count");
        try {
            SysUser user = userService.login(username, password);
            request.getSession().setAttribute(WebConstant.LOGIN_SESSION_KEY,user);
            // 用户id保存到cookie
            if(!StringUtils.isEmpty(remeber_me)) {
                BlogUtils.setCookie(response, user.getId());
            }
            // 插入日志
            logService.insertLog(LogActions.LOGIN.getAction(),null, IpUtils.getIpAddr(request), user.getId());
        } catch (Exception e) {
            error_count = error_count == null ? 1 : error_count + 1;
            if (error_count > 3) {
                return RestResponseBo.fail("您输入密码已经错误超过3次,请1分钟后尝试");
            }
            cache.set("login_error_count",error_count, 1 * 60);
            String msg = "登陆失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            }else {
                logger.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
}
