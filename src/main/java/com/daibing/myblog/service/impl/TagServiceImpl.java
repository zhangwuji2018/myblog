package com.daibing.myblog.service.impl;

import com.daibing.myblog.dao.BizTagsDao;
import com.daibing.myblog.pojo.BizTags;
import com.daibing.myblog.service.TagService;
import com.daibing.myblog.utils.BlogUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 标签服务实现类
 * @Auther: daibing
 * @Date: 2018/8/16 11:05
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private BizTagsDao tagsDao;

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
}
