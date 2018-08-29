package com.daibing.myblog.service;

import com.daibing.myblog.pojo.BizArticle;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @description: 文章服务类接口
 * @author: daibing
 * @create: 2018-07-20 14:31
 **/
public interface ArticleService {


    /**
     * 新增文章
     * @param article
     * @param tagId
     */
    void publishArticle(BizArticle article, Integer[] tagId);

    /**
     * 分页查询文章集合
     * @param page 查询第几页数
     * @param limit 每页个数
     * @return
     */
    PageInfo<BizArticle> getArticleWithPage(int page, int limit);

    /**
     * 通过id获得文章信息
     * @param id
     * @return 文章信息
     */
    BizArticle getArticleById(String id);

    /**
     * 更新文章
     * @param article
     * @param tagId
     */
    void updateArticle(BizArticle article, Integer[] tagId);

    /**
     * 删除文章
     * @param cid
     */
    void deleteByArticleId(int cid);
}
