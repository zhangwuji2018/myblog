package com.daibing.myblog.utils;

import com.alibaba.fastjson.JSON;
import com.daibing.myblog.pojo.SysUser;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: myblog
 * @description: 工具类
 * @author: daibing
 * @create: 2018-08-10 23:39
 **/
public class BlogUtils {

    /**
     * 返回当前登陆用户
     * @param request
     * @return
     */
    public static SysUser getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null) {
            return null;
        }
        return (SysUser) session.getAttribute(WebConstant.LOGIN_SESSION_KEY);
    }

    /**
     * 从Cookies中获取指定的cookie
     * @param name
     * @param request
     * @return
     */
    private static Cookie cookieRaw(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(name)) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * 设置记住密码的cookie
     * @param response
     * @param uid
     */
    public static void setCookie(HttpServletResponse response, Integer uid) {
        try {
            String value = Tools.enAes(uid.toString(), WebConstant.AES_SALT);
            boolean isSSL = false;
            Cookie cookie = new Cookie(WebConstant.USER_IN_COOKIE, value);
            cookie.setPath("/");
            cookie.setMaxAge(60*30);
            cookie.setSecure(isSSL);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取cookie中的用户id
     * @param request
     * @return
     */
    public static Integer getCookieUserId(HttpServletRequest request) {
        if (request != null) {
            Cookie cookie = cookieRaw(WebConstant.USER_IN_COOKIE, request);
            if (cookie != null && cookie.getValue() != null) {
                try {
                    String uid = Tools.deAes(cookie.getValue(), WebConstant.AES_SALT);
                    return !StringUtils.isEmpty(uid) && Tools.isNumber(uid) ? Integer.parseInt(uid) : null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * md5加密
     * @param source 数据源
     * @return 加密字符串
     */
    public static String MD5encode(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ignored) {
        }
        byte[] encode = messageDigest.digest(source.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte anEncode : encode) {
            String hex = Integer.toHexString(0xff & anEncode);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 对象转换json字符串
     * @param object
     * @return
     */
    public static String toJsonString(Object object){
        return object == null ? null : JSON.toJSONString(object);
    }

    /**
     * 判断字符串是否为数字和有正确的值
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {

        if (null != str && 0 != str.trim().length() && str.matches("\\d*")) {
            return true;
        }

        return false;
    }

}
