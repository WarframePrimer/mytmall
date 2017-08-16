package com.warframe.mytmall.controller;

import com.warframe.mytmall.pojo.*;

import com.warframe.mytmall.service.*;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.List;

/**
 * Created by warframe on 2017/6/7.
 * 后台管理页面的相关controller
 */

public class AdminController {

    //配置loggerinfo
    Logger logger = Logger.getLogger(AdminController.class);


    @Resource
    private CategoryService categoryService;

    @Resource
    private UserService userService;

    @Resource
    private PropertyService propertyService;

    @Resource
    private ProductService productService;

    @Resource
    private PropertyValueService propertyValueService;

    @Resource
    private ReviewService reviewService;

    @Resource
    private OrderService orderService;

    @Resource
    private OrderItemService orderItemService;


//    //用户管理
//    @RequestMapping("admin_user_list.do")
//    public ModelAndView listUser(HttpServletRequest request,HttpServletResponse response){
//        ModelAndView modelAndView = new ModelAndView();
//        Page page = this.getPage(request);
//        int start = (page.getPageNum()-1)*page.getCount();
//        page.setTotalRecords(userService.getTotalNumber());
//        List<User> users = userService.list(start,page.getCount());
//        modelAndView.addObject("users",users);
//        modelAndView.addObject("page",page);
//        modelAndView.setViewName("admin/listUser");
//        return modelAndView;
//    }

//    //订单管理
//    @RequestMapping("admin_order_list.do")
//    public ModelAndView listOrder(HttpServletRequest request,HttpServletResponse response){
//        ModelAndView modelAndView = new ModelAndView();
//        Page page = this.getPage(request);
//        int start = (page.getPageNum()-1)*page.getCount();
//        page.setTotalRecords(orderService.getTotalNumber());
//        List<Order> orders = orderService.list(start,page.getCount());
//        modelAndView.addObject("orders",orders);
//        modelAndView.addObject("page",page);
//        modelAndView.setViewName("admin/listOrder");
//        return modelAndView;
//    }

//    //属性管理
//    @RequestMapping("admin_property_list.do")
//    public ModelAndView listProperty(HttpServletRequest request,HttpServletResponse response){
//        ModelAndView modelAndView = new ModelAndView("admin/listProperty") ;
//        String param = request.getParameter("cid");
//        if((null == param) || (""== param)) {
//            modelAndView.addObject("cidError","无cid传入");
//            return modelAndView;
//        }else {
//            int cid = Integer.parseInt(param);
//            Page page = this.getPage(request);
//            int start = (page.getPageNum()-1)*page.getCount();
//            page.setTotalRecords(propertyService.getTotalNumber());
//            page.setParam("&cid="+cid);
//            //取得分类的对象实体类
//            Category category = categoryService.getCategoryById(cid);
//            List<Property> properties = propertyService.listByCategoryId(start,page.getCount(),cid);
//            modelAndView.addObject("page",page);
//            modelAndView.addObject("category",category);
//            modelAndView.addObject("properties",properties);
//            return modelAndView;
//        }
//
//    }


//    //这个可以用@RequestParam注解直接从request中获取到想要的值
//    private Page getPage(HttpServletRequest request){
//        int pageNum;
//        String param = request.getParameter("pageNum");
//        if((null == param) || (""== param)) {
//            pageNum = 1;
//        }else {
//            pageNum = Integer.parseInt(param);
//        }
//        Page page = new Page(5,pageNum);
//        //String uri = request.getRequestURI();
//
//        return page;
//
//    }


}
