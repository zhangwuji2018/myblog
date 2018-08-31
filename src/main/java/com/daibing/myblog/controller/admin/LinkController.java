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
import org.springframework.web.bind.annotation.*;
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

    /**
     * 保存一条链接
     * @param link
     * @return
     */
    @PostMapping(value = "save")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo saveLink(SysLink link) {
        LOGGER.info("请求链接link"+link);
        try {
            if (link.getId() == null) {
                // 新增链接
                linkService.addLink(link);
            } else {
                // 修改链接
                linkService.updateLink(link);
            }
        } catch (Exception e) {
            String msg = "友链保存失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    /**
     * 删除链接
     * @param id
     * @return
     */
    @PostMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo deleteLink(@RequestParam Integer id) {
        LOGGER.info("请求链接的linkId"+id);
        try {
            linkService.deleteLink(id);
        } catch (Exception e) {
            String msg = "友链删除失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
}
