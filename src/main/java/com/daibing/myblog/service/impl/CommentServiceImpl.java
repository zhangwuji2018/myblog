package com.daibing.myblog.service.impl;

import com.daibing.myblog.dao.CommentDao;
import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.pojo.BizComment;
import com.daibing.myblog.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 评论接口实现类
 * @Auther: daibing
 * @Date: 2018/8/22 14:45
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public PageInfo<BizComment> getAllComments(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<BizComment> comments = commentDao.getAllComments();
        return new PageInfo<>(comments);
    }

    @Override
    public BizComment getCommentById(Integer id) {
        if (id != null) {
            return commentDao.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public void deleteCommentById(Integer id) {
        if (id == null) {
            throw new TipException("请求的主键id为空");
        }
        commentDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateCommentStatusById(Integer id) {
        if (id == null) {
            throw new TipException("请求的主键id为空");
        }
        commentDao.updateByPrimaryKey(id);
    }
}
