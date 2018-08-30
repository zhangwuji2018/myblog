package com.daibing.myblog.dao;

import com.daibing.myblog.pojo.SysLink;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 链接接口
 * @author: daibing
 * @create: 2018-08-30 16:56
 **/
@Component
public interface LinkDao {

    /**
     * 获取所有链接
     * @return
     */
    List<SysLink> getAllLink();
}