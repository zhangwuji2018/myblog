package com.daibing.myblog.service.impl;

import com.daibing.myblog.dao.TypeDao;
import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.pojo.BizType;
import com.daibing.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 实现类
 * @Auther: daibing
 * @Date: 2018/8/16 11:26
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public List<BizType> listAllType() {
        return typeDao.getAllType();
    }

    @Override
    public BizType getTypeById(Integer typeId) {
        BizType type = typeDao.getTypeById(typeId);
        if (type == null) {
            throw new TipException("文章分类不存在");
        }
        return type;
    }
}
