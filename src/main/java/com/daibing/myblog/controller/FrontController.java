package com.daibing.myblog.controller;

import com.daibing.myblog.pojo.BizArticle;
import com.daibing.myblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @description: 主页面控制器
 * @author: daibing
 * @create: 2018-07-04 15:28
 **/
@Controller
public class FrontController {


    @RequestMapping("/")
    public String index(Model model){

        return "main";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }
}
