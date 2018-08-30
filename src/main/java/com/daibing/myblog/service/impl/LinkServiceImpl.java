package com.daibing.myblog.service.impl;

import com.daibing.myblog.dao.LinkDao;
import com.daibing.myblog.pojo.SysLink;
import com.daibing.myblog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @auther: daibing
 * @date: 2018/8/30 16:48
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;

    @Override
    public List<SysLink> getAllLink() {
        return linkDao.getAllLink();
    }
}
