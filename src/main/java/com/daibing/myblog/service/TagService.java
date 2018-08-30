package com.daibing.myblog.service;

import com.daibing.myblog.pojo.BizTags;
import java.util.List;

/**
 * @Description: 标签服务接口
 * @Auther: daibing
 * @Date: 2018/8/16 11:03
 */
public interface TagService {
    /**
     * 查询所有标签
     * @return
     */
    List<BizTags> listAllTags();

    /**
     * 查出文章所对应的标签
     * @param id
     * @return
     */
    List<BizTags> getTagsWithArticleId(String id);

    /**
     * 删除标签
     * @param cid
     */
    void delete(Integer cid);

    /**
     * 新增标签
     * @param tname
     */
    void insert(String tname);
}
