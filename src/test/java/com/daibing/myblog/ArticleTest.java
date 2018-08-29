package com.daibing.myblog;
import com.daibing.myblog.dao.ArticleDao;
import com.daibing.myblog.pojo.BizArticle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @description: 测试
 * @author: daibing
 * @create: 2018-07-12 14:42
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTest {

    @Autowired
    ArticleDao articleDao;

    @Test
    public void test(){
        List<BizArticle> articles = articleDao.listAllArticles();
        for (BizArticle article : articles) {
            System.out.println(article.getType());
        }
    }
}
