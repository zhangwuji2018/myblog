package com.daibing.myblog.service;

import com.daibing.myblog.pojo.BizType;
import java.util.List;

/**
 * @Description: 文章分类
 * @Auther: daibing
 * @Date: 2018/8/16 11:20
 */
public interface CategoryService {

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

    /**
     * 保存一个分类
     * @param cid 分类id
     * @param cname 分类名称
     */
    void saveCategory(Integer cid, String cname);

    /**
     * 删除分类
     * @param cid
     */
    void delete(Integer cid);
}
