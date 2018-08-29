package com.daibing.myblog.controller.admin;

import com.daibing.myblog.bo.RestResponseBo;
import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.pojo.BizComment;
import com.daibing.myblog.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 评论管理器
 * @Auther: daibing
 * @Date: 2018/8/22 14:42
 */
@Controller
@RequestMapping("admin/comments")
public class CommentController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    /**
     * 返回评论列表
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "")
    public ModelAndView intoCommentsList(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "limit", defaultValue = "15") int limit) {
        LOGGER.info("参数page="+page+";参数limit="+limit);

        ModelAndView view = new ModelAndView("admin/comment_list");
        PageInfo<BizComment> commentPage = commentService.getAllComments(page, limit);
        view.addObject("commentPages", commentPage);
        return view;
    }

    /**
     * 删除一条评论
     * @param id 评论ID
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo deleteComment(@RequestParam("id") Integer id) {
        LOGGER.info("请求的评论的主键id="+id);

        try {
            BizComment comment = commentService.getCommentById(id);
            if (comment == null) {
                return RestResponseBo.fail("不存在该评论");
            }
            commentService.deleteCommentById(id);
        } catch (Exception e) {
            String msg = "删除评论失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg,e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    /**
     * 审核评论
     * @param id 评论ID
     * @return
     */
    @PostMapping("/status")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo updateCommentStatus(@RequestParam("id") Integer id) {
        LOGGER.info("请求的评论的主键id="+id);

        try {
            BizComment comment = commentService.getCommentById(id);
            if (comment == null) {
                return RestResponseBo.fail("不存在该评论");
            }
            commentService.updateCommentStatusById(id);
        } catch (Exception e) {
            String msg = "审核操作失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg,e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
}
