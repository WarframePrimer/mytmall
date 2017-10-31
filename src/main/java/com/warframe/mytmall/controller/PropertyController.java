package com.warframe.mytmall.controller;

import com.warframe.mytmall.pojo.Category;
import com.warframe.mytmall.pojo.Page;
import com.warframe.mytmall.pojo.Property;
import com.warframe.mytmall.service.CategoryService;
import com.warframe.mytmall.service.PropertyService;

import com.warframe.mytmall.util.PageUtil;
import com.warframe.mytmall.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by warframe on 2017/7/4.
 */

@Controller
@RequestMapping("admin/property")
public class PropertyController {

    //配置loggerinfo
    private Logger logger = Logger.getLogger(PropertyController.class);

    @Resource
    private PropertyService propertyService;

    @Resource
    private CategoryService categoryService;


    //属性管理
    @RequestMapping("admin_property_list.do")
    public ModelAndView listProperty(@RequestParam(value = "cid", defaultValue = "0") int cid, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("admin/listProperty");

        if (0 == cid) {
            modelAndView.addObject("cidError", "无cid传入");
            return modelAndView;
        } else {
            Page page = PageUtil.getPage(pageNum);
            page.setTotalRecords(propertyService.getTotalNumberByCategoryId(cid));
            //便于进行返回的查询
            page.setParam("&cid=" + cid);
            //取得分类的对象实体类
            Category category = categoryService.getCategoryById(cid);
            List<Property> properties = propertyService.listByCategoryId(cid);
            modelAndView.addObject("page", page);
            modelAndView.addObject("category", category);
            modelAndView.addObject("properties", properties);
            return modelAndView;
        }

    }

    //属性分类的增加只需要新增属性的名称即可，不需要图片的上传
    @RequestMapping(value = "admin_property_add.do", method = RequestMethod.POST)
    public ModelAndView addProperty(@RequestParam(value = "cid") int cid, @RequestParam("name") String pname, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

        logger.info("分类编号为：" + cid);
        //logger.info("新增属性名称为:" + pname);
        logger.info("分页页数：" + pageNum);
        logger.info("编码后的属性:" + StringUtil.toUTF(pname));

        Property property = new Property();
        property.setName(StringUtil.toUTF(pname));
        property.setCategory(categoryService.getCategoryById(cid));

        //logger.info("保存的属性名称：" + pname);
        propertyService.addProperty(property);

        Page page = PageUtil.getPage(pageNum);
        page.setParam("&cid=" + cid);
        page.setTotalRecords(propertyService.getTotalNumberByCategoryId(cid));

        ModelAndView modelAndView = new ModelAndView("redirect:admin_property_list.do?pageNum=" + page.getPageNum() + page.getParam());
        modelAndView.addObject("page", page);
        return modelAndView;
    }


    //ajax实现删除
    @RequestMapping("admin_property_deleteAjax.do")
    @ResponseBody
    public Map<String, Object> deletePropertyByAjax(@RequestParam("id") int id) {

        propertyService.deleteProperty(id);
        logger.info("删除编号为：" + id + "的属性");

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        return map;
    }


    @RequestMapping("admin_property_preEdit.do")
    /**
     * 预修改
     * 请求过程中获取到要编辑修改的属性编号(唯一)，分页页数以及属性所属的分类编号
     */
    public ModelAndView preEditProperty(@RequestParam("id") int id, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam("cid") int cid) {

        //得到要修改的属性bean
        Property property = propertyService.getPropertyById(id);

        Category category = categoryService.getCategoryById(cid);

        ModelAndView modelAndView = new ModelAndView("admin/propertyPreEdit");

        modelAndView.addObject("property", property);
        modelAndView.addObject("category", category);
        modelAndView.addObject("pageNum", pageNum);

        return modelAndView;
    }


    @RequestMapping(value = "admin_property_edit.do", method = RequestMethod.POST)
    /**
     * 对属性进行编辑修改
     * 提交修改使用的是post方法
     */
    public ModelAndView editProperty(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam("cid") int cid) {
        //将从表单获取的字段封装成响应的bean，并进行dao操作，同步到数据库


        logger.info("获取到的name：" + name);

        logger.info("获取到的pageNum：" + pageNum);

        Property property = new Property();
        property.setId(id);
        property.setName(StringUtil.toUTF(name));
        Category category = categoryService.getCategoryById(cid);
        property.setCategory(category);

        propertyService.updateProperty(property);

        ModelAndView modelAndView = new ModelAndView("redirect:admin_property_list.do?cid=" + cid + "&pageNum=" + pageNum);
        return modelAndView;
    }


}
