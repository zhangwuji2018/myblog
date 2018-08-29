package com.daibing.myblog;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: 测试
 * @author: daibing
 * @create: 2018-07-12 14:42
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntryTest {

    @Autowired
    //StringEncryptor stringEncryptor;

    @Test
    public void test(){
        /*String root = stringEncryptor.encrypt("root");
        String s = stringEncryptor.encrypt("123");
        System.out.println(root+":"+s);
        String decrypt = stringEncryptor.decrypt(root);
        System.out.println(decrypt);*/
    }
}
