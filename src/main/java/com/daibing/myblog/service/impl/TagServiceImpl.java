package com.daibing.myblog.service.impl;
import com.daibing.myblog.dao.ArticleTagsDao;
import com.daibing.myblog.dao.TagsDao;
import com.daibing.myblog.dto.BizTagsDto;
import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.pojo.BizArticleTags;
import com.daibing.myblog.pojo.BizTags;
import com.daibing.myblog.service.TagService;
import com.daibing.myblog.utils.BlogUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 标签服务实现类
 * @Auther: daibing
 * @Date: 2018/8/16 11:05
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagsDao tagsDao;

    @Autowired
    private ArticleTagsDao articleTagsDao;

    @Override
    public List<BizTags> listAllTags() {
        List<BizTags> tags = tagsDao.getAllTag();
        return tags;
    }

    @Override
    public List<BizTags> getTagsWithArticleId(String id) {
        if (StringUtils.isNotBlank(id)) {
            if (BlogUtils.isNumber(id)) {
                List<BizTags> articleTags = tagsDao.getTagsWithArticleId(Integer.parseInt(id));
                return articleTags;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer cid) {
        if (cid == null) {
            throw new TipException("要删除的标签的id为空");
        }
        // 判断该标签能否删除
        List<BizArticleTags> tags = articleTagsDao.getArticleTagsByTigId(cid);
        if (tags.size() > 0) {
            throw new TipException("标签有对应的文章不能删除");
        }
        tagsDao.delete(cid);
    }

    @Override
    public void insert(String tname) {
        if (tname == null) {
            throw new TipException("新增的标签名称为空");
        }
        BizTags tag = new BizTags();
        tag.setName(tname);
        tag.setCreateTime(new Date());

        tagsDao.insertTag(tag);
    }

    @Override
    public List<BizTagsDto> getAllTigsDto() {
        return tagsDao.getAllTigsDto();
    }
}
