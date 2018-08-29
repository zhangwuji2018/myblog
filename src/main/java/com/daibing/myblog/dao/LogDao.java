package com.daibing.myblog.dao;

import com.daibing.myblog.pojo.SysLog;
import org.springframework.stereotype.Component;

/**
 * @program: myblog
 * @description: 日志接口
 * @author: daibing
 * @create: 2018-08-11 16:13
 **/
@Component
public interface LogDao {
    /**
     * 插入日志
     * @param log
     */
    void insert(SysLog log);
}
