package com.daibing.myblog.service;

import com.daibing.myblog.pojo.SysLink;
import java.util.List;

/**
 * @description: 链接服务接口
 * @auther: daibing
 * @date: 2018/8/30 16:47
 */
public interface LinkService {
    /**
     *
     * @return 获取所有的链接集合
     */
    List<SysLink> getAllLink();

    /**
     * 新增链接
     * @param link
     */
    void addLink(SysLink link);

    /**
     * 修改链接
     * @param link
     */
    void updateLink(SysLink link);

    /**
     * 删除链接
     * @param id
     */
    void deleteLink(Integer id);
}
