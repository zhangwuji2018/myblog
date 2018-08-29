package com.daibing.myblog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Component
public interface BizArticleTagsDao {

    /**
     * 插入到文章标签表
     * @param articleId 文章id
     * @param tagId 标签id数组
     * @return
     */
    int insertArticleTags(@Param("articleId") int articleId, @Param("tagIds") Integer[] tagIds);

    /**
     * 删除文章下对应的标签
     * @param id
     * @return
     */
    int removeTagsWithArticleId(@Param("id") Integer id);
}