package com.warframe.mytmall.controller;

import com.warframe.mytmall.pojo.Category;
import com.warframe.mytmall.pojo.Page;
import com.warframe.mytmall.service.*;
import com.warframe.mytmall.util.FileUpload;
import com.warframe.mytmall.util.PageUtil;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by warframe on 2017/6/20.
 *
 * 后端产品数据考虑使用java爬虫爬取相关数据
 */


@Controller
@RequestMapping("admin/category")
public class CategoryController {

    //配置loggerinfo
    private Logger logger = Logger.getLogger(CategoryController.class);


    @Resource
    private CategoryService categoryService;


    //分类管理
    //修改，将.do去掉
    @RequestMapping(value = {"admin_category_list.do"})
    public ModelAndView listCategory(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView();
        Page page = PageUtil.getPage(pageNum);

        page.setTotalRecords(categoryService.getTotalNumber());

        logger.info("pageNum:" + pageNum);
        List<Category> categories = categoryService.list(page.getStart(), page.getCount());
        modelAndView.addObject("categories", categories);

        //提供分页信息
        modelAndView.addObject("page", page);
        modelAndView.setViewName("admin/listCategory");
        return modelAndView;
    }

    /**
     * 添加分类的过程中会涉及到分类图片的上传
     */
    @RequestMapping("admin_category_add.do")
    public ModelAndView addCategory(@RequestParam("filePath") CommonsMultipartFile file,
                                    @RequestParam("name") String cname,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                    HttpServletRequest request) {
        Category c = new Category();
        c.setName(cname);

        logger.info("保存的分类名称：" + cname);
        categoryService.addCategory(c);
        //logger.info("此时的pageNum：" + pageNum);
        //会将增长后的id在设置回分类的实体类中
        logger.info(c.getId());


        //将从用户上传的文件保存到本服务器上
        FileUpload.fileUploadCategory(String.valueOf(c.getId()), file, request);

        Page page = PageUtil.getPage(pageNum);
        page.setTotalRecords(categoryService.getTotalNumber());

        ModelAndView modelAndView = new ModelAndView("redirect:admin_category_list.do?pageNum=" + page.getPageNum());
        modelAndView.addObject("page", page);
        return modelAndView;
    }


    @Deprecated
    @RequestMapping("admin_category_delete.do")
    public ModelAndView deleteCategory(@RequestParam("cid") int cid, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {


        logger.info("要删除的分类编号：" + cid);
        logger.info("删除时的pageNum:" + pageNum);

        categoryService.deleteCategory(cid);

        Page page = PageUtil.getPage(pageNum);
        page.setTotalRecords(categoryService.getTotalNumber());

        logger.info("已删除的分类编号：" + cid);


        ModelAndView modelAndView = new ModelAndView("redirect:admin_category_list.do?pageNum=" + page.getPageNum());
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    /*使用ajax实现分类的删除，是的删除后停留在删除记录的那一页*/
    @RequestMapping("admin_category_delete_ajax.do")
    @ResponseBody
    public Map<String, Object> deleteCategoryByAjax(@RequestParam("cid") int cid, HttpServletRequest request) {


        //删除在数据库中的记录
        categoryService.deleteCategory(cid);
        logger.info("成功删除编号为" + cid + "的分类");

        //删除在本地中的图片
        String path = request.getSession().getServletContext().getRealPath("img/category");

        logger.info("path:" + path);
        File fileToDelete = new File(path,cid+".jpg");

        if(fileToDelete.exists()){
            fileToDelete.delete();
        }else{
            logger.info("文件不存在！！！");
        }


        Map<String, Object> map = new HashMap<>();
        map.put("cid", cid);
        return map;
    }


    /**
     * 实现分类的编辑
     * 先不考虑编辑后listCategory的问题
     * 分类的图片名称就是分类名称.jpg
     * 分类的编辑包括分类图片的上传
     *
     * @param cid
     * @param pageNum
     * @return
     */
    @RequestMapping("admin_category_preEdit.do")
    public ModelAndView preEditCategory(@RequestParam(value = "cid") int cid, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        Category category = categoryService.getCategoryById(cid);


        ModelAndView modelAndView = new ModelAndView("admin/categoryPreEdit");

        modelAndView.addObject("category", category);
        modelAndView.addObject("pageNum", pageNum);

        logger.info("编辑前的分类信息：" + category);

        return modelAndView;
    }

    @RequestMapping(value = "admin_category_edit.do", method = RequestMethod.POST)
    public ModelAndView editCategory(@RequestParam("pageNum") int pageNum,
                                     @RequestParam("cid") int cid,
                                     @RequestParam("name") String name,
                                     @RequestParam("filePath") CommonsMultipartFile file,
                                     HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:admin_category_list.do?pageNum=" + pageNum);
        Category category = new Category();
        category.setId(cid);
        category.setName(name);
        //Page page = PageUtil.getPage(pageNum);


        categoryService.updateCategory(category);
        logger.info("编辑后的分类消息:" + category);
        FileUpload.fileUploadCategory(String.valueOf(cid), file, request);


        return modelAndView;
    }


}
