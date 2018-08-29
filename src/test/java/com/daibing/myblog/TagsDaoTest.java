package com.daibing.myblog;

import com.daibing.myblog.dao.BizTagsDao;
import com.daibing.myblog.pojo.BizTags;
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
public class TagsDaoTest {

    @Autowired
    BizTagsDao tagsDao;

    @Test
    public void test(){
        List<BizTags> tags = tagsDao.getTagsWithArticleId(1);
        for (BizTags tag : tags) {
            System.out.println(tag.toString());
        }
    }
}
