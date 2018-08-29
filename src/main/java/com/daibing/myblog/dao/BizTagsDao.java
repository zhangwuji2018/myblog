package com.daibing.myblog.dao;

import com.daibing.myblog.pojo.BizTags;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 标签接口
 */
@Component
public interface BizTagsDao {

    /**
     * 查出所有的标签
     * @return
     */
    List<BizTags> getAllTag();

    /**
     * 根据文章id查出文章对应的标签
     * @param articleId
     * @return 标签集合
     */
    List<BizTags> getTagsWithArticleId(@Param("articleId") int articleId);
}