package com.daibing.myblog.interceptor;

import com.daibing.myblog.dto.Types;
import com.daibing.myblog.pojo.SysUser;
import com.daibing.myblog.service.UserService;
import com.daibing.myblog.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: myblog
 * @description: 自定义拦截器
 * @author: daibing
 * @create: 2018-08-12 17:03
 **/
@Component
public class BaseInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AdminCommons adminCommons;

    private MapCache cache = MapCache.single();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        LOGGER.info("UserAgent:{}", request.getHeader("user-agent"));
        LOGGER.info("用户访问地址:{}, 来路地址:{} ", uri, IpUtils.getIpAddrByRequest(request));

        //请求拦截处理
        //获取session中的user信息
        SysUser loginUser = BlogUtils.getLoginUser(request);
        if (loginUser == null) {
            //从cookie中获取用户id
            Integer userId = BlogUtils.getCookieUserId(request);
            if (userId != null) {
                loginUser = userService.getUserById(userId);
                request.getSession().setAttribute(WebConstant.LOGIN_SESSION_KEY, loginUser);
            }
        }
        if (uri.startsWith("/admin") && !uri.startsWith("/admin/login") && loginUser == null) {
            response.sendRedirect(request.getContextPath()+"/admin/login");
            return false;
        }
        //设置get请求的token
        if (request.getMethod().equals("GET")) {
            String csrf_token = UUID.UU64();
            //默认存储30分钟
            cache.hset(Types.CSRF_TOKEN.getType(), csrf_token, uri, 30*60);
            request.setAttribute("csrf_token",csrf_token);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setAttribute("adminCommons", adminCommons);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
