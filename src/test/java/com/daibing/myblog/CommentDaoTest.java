package com.daibing.myblog;

import com.daibing.myblog.dao.CommentDao;
import com.daibing.myblog.pojo.BizComment;
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
public class CommentDaoTest {

    @Autowired
    CommentDao commentDao;

    @Test
    public void test(){
        List<BizComment> comments = commentDao.getAllComments();
        for (BizComment comment : comments) {
            System.out.println(comment);
        }
    }
}
