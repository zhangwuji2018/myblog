package com.daibing.myblog.controller.admin;

import com.daibing.myblog.bo.RestResponseBo;
import com.daibing.myblog.dto.BizTagsDto;
import com.daibing.myblog.exception.TipException;
import com.daibing.myblog.pojo.BizType;
import com.daibing.myblog.service.CategoryService;
import com.daibing.myblog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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
    private CategoryService categoryService;

    /**
     * 请求跳转标签分类页面
     * @return
     */
    @GetMapping(value = "")
    public ModelAndView category() {
        ModelAndView view = new ModelAndView("admin/category");
        List<BizTagsDto> tags = tagService.getAllTigsDto();
        List<BizType> types = categoryService.listAllType();
        view.addObject("tags",tags);
        view.addObject("types",types);
        return view;
    }

    /**
     * 保存分类
     * @param cid 分类id
     * @param cname 分类名称
     * @return
     */
    @PostMapping("/saveCategory")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo saveCategory(@RequestParam Integer cid, @RequestParam String cname) {
        LOGGER.info("参数cid="+cid+";参数cname="+cname);

        try {
            categoryService.saveCategory(cid, cname);
        } catch (Exception e) {
            String msg = "分类保存失败";
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
     * 删除分类/标签
     * @param cid
     * @param flag
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam Integer cid, @RequestParam String flag) {
        LOGGER.info("参数cid="+cid+";flag="+flag);
        try {
            if ("category".equals(flag)) {
                // 删除分类
                categoryService.delete(cid);
            }
            if ("tag".equals(flag)) {
                // 删除标签
                tagService.delete(cid);
            }
        } catch (Exception e) {
            String msg = "删除失败";
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
     * 保存标签
     * @param tname 标签名称
     * @return
     */
    @PostMapping("/saveTag")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo saveTag(@RequestParam String tname) {
        LOGGER.info("参数tname="+tname);

        try {
            tagService.insert(tname);
        } catch (Exception e) {
            String msg = "标签保存失败";
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
