package com.daibing.myblog.controller.admin;

import com.daibing.myblog.pojo.BizTags;
import com.daibing.myblog.pojo.BizType;
import com.daibing.myblog.service.TagService;
import com.daibing.myblog.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @description: 分类标签控制器
 * @author: daibing
 * @date: 2018/8/29 15:36
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    /**
     * 请求跳转标签分类页面
     * @return
     */
    @GetMapping(value = "")
    public ModelAndView category() {
        ModelAndView view = new ModelAndView("admin/category");
        List<BizTags> tags = tagService.listAllTags();
        List<BizType> types = typeService.listAllType();
        view.addObject("tags",tags);
        view.addObject("types",types);
        return view;
    }
}
