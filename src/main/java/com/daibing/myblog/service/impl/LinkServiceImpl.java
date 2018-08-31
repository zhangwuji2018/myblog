package com.daibing.myblog.service.impl;

import com.daibing.myblog.dao.LinkDao;
import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.pojo.SysLink;
import com.daibing.myblog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public void addLink(SysLink link) {
        if (link == null) {
            throw new TipException("请求的链接信息为空");
        }
        link.setCreateTime(new Date());
        linkDao.addNewLink(link);
    }

    @Override
    public void updateLink(SysLink link) {
        if (link == null) {
            throw new TipException("请求的链接信息为空");
        }
        linkDao.updateLink(link);
    }

    @Override
    public void deleteLink(Integer id) {
        if (id == null) {
            throw new TipException("请求的链接id为空");
        }
        linkDao.deleteLinkById(id);
    }
}
