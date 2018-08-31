package com.daibing.myblog.service;

import com.daibing.myblog.pojo.BizComment;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description: 评论服务接口
 * @Auther: daibing
 * @Date: 2018/8/22 14:45
 */
public interface CommentService {

    /**
     * 分页获取评论信息
     * @param page
     * @param limit
     * @return
     */
    PageInfo<BizComment> getAllComments(int page, int limit);

    /**
     * 通过ID获取评论
     * @param id
     * @return
     */
    BizComment getCommentById(Integer id);

    /**
     * 删除一条评论
     * @param id
     */
    void deleteCommentById(Integer id);

    /**
     * 审核评论
     * @param id
     */
    void updateCommentStatusById(Integer id);

    /**
     * 获取所有的评论
     * @return
     */
    List<BizComment> listAllComments();

    /**
     * 获取最新的评论
     * @return
     */
    List<BizComment> getAllCommentsWithLimit();
}
