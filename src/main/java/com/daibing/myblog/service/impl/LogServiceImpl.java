package com.daibing.myblog.service.impl;

import com.daibing.myblog.dao.LogDao;
import com.daibing.myblog.pojo.SysLog;
import com.daibing.myblog.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: myblog
 * @description:
 * @author: daibing
 * @create: 2018-08-11 16:11
 **/
@Service
public class LogServiceImpl implements LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    private LogDao logDao;

    @Override
    public void insertLog(String action, String data, String ip, Integer userId) {
        SysLog log = new SysLog();
        log.setAction(action);
        log.setData(data);
        log.setIp(ip);
        log.setUserId(userId);
        logDao.insert(log);
    }
}
