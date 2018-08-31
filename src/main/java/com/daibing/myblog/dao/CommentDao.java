package com.daibing.myblog.dao;

import com.daibing.myblog.pojo.BizComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 评论接口
 */
@Component
public interface CommentDao {
    /**
     * 删除一条评论
     * @param id 评论id
     * @return
     */
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(BizComment record);

    int insertSelective(BizComment record);

    /**
     * 通过ID获取评论
     * @param id
     * @return
     */
    BizComment selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(BizComment record);

    /**
     * 审核评论，修改评论的status
     * @param id
     * @return
     */
    int updateByPrimaryKey(@Param("id") Integer id);

    /**
     * 获取所有的评论
     * @return
     */
    List<BizComment> getAllComments();

    /**
     * 获取最新的评论
     * @return
     */
    List<BizComment> getCommentsWithLimit();
}