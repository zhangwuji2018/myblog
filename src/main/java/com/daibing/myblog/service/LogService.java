package com.daibing.myblog.service;

/**
 * @program: myblog
 * @description: 日志服务接口
 * @author: daibing
 * @create: 2018-08-11 16:09
 **/
public interface LogService {

    /**
     * 保存操作日志
     * @param action
     * @param data
     * @param ip
     * @param userId
     */
    void insertLog(String action, String data, String ip, Integer userId);
}
