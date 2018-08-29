package com.daibing.myblog.service;

import com.daibing.myblog.pojo.BizType;
import java.util.List;

/**
 * @Description: 文章分类
 * @Auther: daibing
 * @Date: 2018/8/16 11:20
 */
public interface TypeService {

    /**
     * 获取所有文章分类
     * @return
     */
    List<BizType> listAllType();

    /**
     * 通过id查询type信息
     * @param typeId
     * @return type
     */
    BizType getTypeById(Integer typeId);
}
