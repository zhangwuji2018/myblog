package com.daibing.myblog.dao;

import com.daibing.myblog.pojo.BizArticle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 文章接口
 * @author: daibing
 * @create: 2018-07-20 14:56
 **/
@Component
public interface ArticleDao {
    /**
     * 新增文章
     * @param article
     * @return
     */
    int insertArticle(BizArticle article);

    /**
     * 查询所有文章
     * @return 文章集合
     */
    List<BizArticle> listAllArticles();

    /**
     * 通过文章id查出文章信息
     * @param parseInt
     * @return
     */
    BizArticle getArticleById(@Param("id") int id);

    /**
     * 更新文章
     * @param article
     */
    int updateArticle(BizArticle article);

    /**
     * 删除文章
     * @param cid
     */
    void deleteByArticleId(@Param("cid") int cid);

    /**
     *
     * @param top 1代表顶部 2代表右侧
     * @return 查询文章集合
     */
    //List<BizArticle> listArticle(@Param("top") int top);

}
