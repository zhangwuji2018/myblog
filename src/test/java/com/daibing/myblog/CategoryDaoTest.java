package com.daibing.myblog;

import com.daibing.myblog.dao.CategoryDao;
import com.daibing.myblog.pojo.BizType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @auther: daibing
 * @date: 2018/8/31 11:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDaoTest {

    @Autowired
    CategoryDao categoryDao;

    @Test
    public void test() {
        BizType typeById = categoryDao.getTypeById(1);
        System.out.println(typeById.getArticles().size());
    }
}
