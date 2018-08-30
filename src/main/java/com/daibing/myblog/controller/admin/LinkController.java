package com.daibing.myblog.controller.admin;

import com.daibing.myblog.bo.RestResponseBo;
import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.pojo.SysLink;
import com.daibing.myblog.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @description: 链接控制器
 * @auther: daibing
 * @date: 2018/8/30 16:42
 */
@Controller
@RequestMapping("/admin/links")
public class LinkController {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkController.class);

    @Autowired
    private LinkService linkService;

    /**
     * 链接页面
     * @return
     */
    @GetMapping(value = "")
    public ModelAndView link() {
        ModelAndView view = new ModelAndView("admin/links");
        List<SysLink> links = linkService.getAllLink();
        view.addObject("links",links);
        LOGGER.info("链接集合links"+links.toString());
        return view;
    }

    @PostMapping(value = "save")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo saveLink(SysLink link) {
        LOGGER.info("请求链接link"+link);

        return null;
    }
}
