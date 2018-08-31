package com.daibing.myblog.service.impl;
import com.daibing.myblog.dao.CategoryDao;
import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.pojo.BizArticle;
import com.daibing.myblog.pojo.BizType;
import com.daibing.myblog.service.ArticleService;
import com.daibing.myblog.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 实现类
 * @Auther: daibing
 * @Date: 2018/8/16 11:26
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ArticleService articleService;

    @Override
    public List<BizType> listAllType() {
        return categoryDao.getAllType();
    }

    @Override
    public BizType getTypeById(Integer typeId) {
        BizType type = categoryDao.getTypeById(typeId);
        if (type == null) {
            throw new TipException("文章分类不存在");
        }
        return type;
    }

    @Override
    public void saveCategory(Integer cid, String cname) {
        if (StringUtils.isBlank(cname)) {
            throw new TipException("分类名称不能为空");
        }
        BizType type = new BizType();
        type.setName(cname);
        if (cid != null) {
            // 更新分类
            type.setId(cid);
            categoryDao.update(type);
        } else {
            // 新增分类
            type.setCreateTime(new Date());
            categoryDao.insert(type);
        }
    }

    @Override
    public void delete(Integer cid) {
        if (cid == null) {
            throw new TipException("要删除的分类id为空");
        }
        // 获取分类下的文章，用于判断能否删除
        List<BizArticle> articles = articleService.getArticleByTypeId(cid);
        if (articles.size() > 0) {
            throw new TipException("该分类下还有文章,不能删除");
        }
        categoryDao.delete(cid);
    }
}
