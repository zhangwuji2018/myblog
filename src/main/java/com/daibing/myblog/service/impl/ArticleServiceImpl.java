package com.daibing.myblog.service.impl;

import com.daibing.myblog.dao.ArticleDao;
import com.daibing.myblog.dao.BizArticleTagsDao;
import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.pojo.BizArticle;
import com.daibing.myblog.service.ArticleService;
import com.daibing.myblog.utils.BlogUtils;
import com.daibing.myblog.utils.WebConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 文章服务类
 * @author: daibing
 * @create: 2018-07-20 14:55
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private BizArticleTagsDao articleTagsDao;

    @Override
    public void publishArticle(BizArticle article, Integer[] tagId) {
        if (article == null) {
            throw new TipException("文章对象为空");
        }
        if (StringUtils.isBlank(article.getTitle())) {
            throw new TipException("文章标题不能为空");
        }
        if (StringUtils.isBlank(article.getContent())) {
            throw new TipException("文章内容不能为空");
        }
        int titleLength = article.getTitle().length();
        if (titleLength > WebConstant.MAX_TITLE_COUNT) {
            throw new TipException("文章标题过长");
        }
        int contentLength = article.getContent().length();
        if (contentLength > WebConstant.MAX_TEXT_COUNT) {
            throw new TipException("文章内容过长");
        }
        if (article.getUserId() == null) {
            throw new TipException("请登录后发布文章");
        }
        //  将表情符号转为字符
        article.setContent(EmojiParser.parseToAliases(article.getContent()));
        int result1 = articleDao.insertArticle(article);
        int articleId = article.getId();

        int result2 = articleTagsDao.insertArticleTags(articleId, tagId);

        LOGGER.info("插入文章结果:"+result1+"返回的主键:"+articleId+"插入文章标签表的结果:"+result2);
    }

    @Override
    public PageInfo<BizArticle> getArticleWithPage(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<BizArticle> articles = articleDao.listAllArticles();
        return new PageInfo<>(articles);
    }

    @Override
    public BizArticle getArticleById(String id) {
        if (StringUtils.isNotBlank(id)) {
            if (BlogUtils.isNumber(id)) {

                return articleDao.getArticleById(Integer.parseInt(id));
            }
        }
        return null;
    }

    @Override
    public void updateArticle(BizArticle article, Integer[] tagId) {
        if (article == null) {
            throw new TipException("文章对象为空");
        }
        if (StringUtils.isBlank(article.getTitle())) {
            throw new TipException("文章标题不能为空");
        }
        if (StringUtils.isBlank(article.getContent())) {
            throw new TipException("文章内容不能为空");
        }
        int titleLength = article.getTitle().length();
        if (titleLength > WebConstant.MAX_TITLE_COUNT) {
            throw new TipException("文章标题过长");
        }
        int contentLength = article.getContent().length();
        if (contentLength > WebConstant.MAX_TEXT_COUNT) {
            throw new TipException("文章内容过长");
        }
        if (article.getUserId() == null) {
            throw new TipException("请登录后发布文章");
        }
        //  将表情符号转为字符
        article.setContent(EmojiParser.parseToAliases(article.getContent()));

        // 更新文章表
        int update_result = articleDao.updateArticle(article);
        // 先清除文章对应的标签
        int delete_result = articleTagsDao.removeTagsWithArticleId(article.getId());
        // 从新插入标签
        int insert_result = articleTagsDao.insertArticleTags(article.getId(), tagId);

        LOGGER.info("更新文章状态:update_result="+update_result+"--;清除标签状态:delete_result="+delete_result+"--;插入标签状态:insert_result"+insert_result);
    }

    @Override
    public void deleteByArticleId(int cid) {
        BizArticle article = this.getArticleById(cid + "");
        if (article != null) {
            articleDao.deleteByArticleId(cid);
            articleTagsDao.removeTagsWithArticleId(article.getId());
        }
    }
}
