package com.daibing.myblog.controller.admin;

import com.daibing.myblog.bo.RestResponseBo;
import com.daibing.myblog.dto.LogActions;
import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.pojo.BizArticle;
import com.daibing.myblog.pojo.BizTags;
import com.daibing.myblog.pojo.BizType;
import com.daibing.myblog.pojo.SysUser;
import com.daibing.myblog.service.ArticleService;
import com.daibing.myblog.service.CategoryService;
import com.daibing.myblog.service.LogService;
import com.daibing.myblog.service.TagService;
import com.daibing.myblog.utils.BlogUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 文章管理控制器
 * @Auther: daibing
 * @Date: 2018/8/16 11:31
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {
    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LogService logService;

    /**
     * 文章列表界面
     * @param page 页数
     * @param limit 文章个数
     * @return
     */
    @GetMapping(value = "")
    public ModelAndView articleList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "15") int limit) {
        LOGGER.info("参数page="+page+";参数limit="+limit);

        ModelAndView view = new ModelAndView("admin/article_list");
        PageInfo<BizArticle> articlePageInfo = articleService.getArticleWithPage(page, limit);
        view.addObject("articles", articlePageInfo);
        return view;
    }

    /**
     * 发布文章界面
     * @return
     */
    @GetMapping("/publish")
    public ModelAndView publish() {

        ModelAndView view = new ModelAndView("admin/article_edit");
        List<BizTags> tags = tagService.listAllTags();
        List<BizType> types = categoryService.listAllType();
        view.addObject("tags",tags);
        view.addObject("types",types);

        return view;
    }

    /**
     * 提交文章
     * @param article 文章参数
     * @param tagId 标签id数组
     * @param request 请求
     * @return
     */
    @PostMapping("/publish")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo publishArticle(BizArticle article, Integer[] tagId, Integer typeId, HttpServletRequest request) {
        LOGGER.info("请求参数article="+article+";tagId="+ Arrays.toString(tagId));

        SysUser loginUser = BlogUtils.getLoginUser(request);
        BizType type = categoryService.getTypeById(typeId);
        article.setUserId(loginUser.getId());
        article.setType(type);
        article.setCreateTime(new Date());
        try {
            articleService.publishArticle(article, tagId);
        } catch (Exception e) {
            String msg = "文章发布失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            }else {
                LOGGER.info(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    /**
     * 文章编辑
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ModelAndView editArticle(@PathVariable String id) {
        LOGGER.info("参数id="+id);

        ModelAndView view = new ModelAndView("admin/article_edit");
        BizArticle article = articleService.getArticleById(id);
        List<BizTags> tags = tagService.listAllTags();
        List<BizType> types = categoryService.listAllType();
        List<BizTags> articleTags = tagService.getTagsWithArticleId(id);
        view.addObject("tags",tags);
        view.addObject("types", types);
        view.addObject("article",article);
        view.addObject("articleTags",articleTags);
        view.addObject("active", "article");
        return view;
    }

    /**
     * 更新文章
     * @param article 文章参数
     * @param tagId 标签组
     * @param typeId 分类ID
     * @param request
     * @return
     */
    @PostMapping("/modify")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo updateArticle(BizArticle article, Integer[] tagId, Integer typeId, HttpServletRequest request) {
        LOGGER.info("文章参数="+article+";标签组tagId="+ Arrays.toString(tagId)+";分类typeId="+typeId);

        SysUser loginUser = BlogUtils.getLoginUser(request);
        BizType type = categoryService.getTypeById(typeId);
        article.setUserId(loginUser.getId());
        article.setType(type);

        try {
            articleService.updateArticle(article, tagId);
        } catch (Exception e) {
            String msg = "文章编辑失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    /**
     * 删除文章
     * @param cid
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam int cid, HttpServletRequest request) {
        LOGGER.info("参数cid="+cid);
        try {
            articleService.deleteByArticleId(cid);
            logService.insertLog(LogActions.DEL_ARTICLE.getAction(), cid+"", request.getRemoteAddr(), BlogUtils.getLoginUser(request).getId());
        } catch (Exception e) {
            String msg = "文章删除失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
}
