package com.warframe.mytmall.controller;

import com.warframe.mytmall.pojo.*;
import com.warframe.mytmall.service.*;
import com.warframe.mytmall.util.FillUtil;
import com.warframe.mytmall.util.PageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by warframe on 2017/6/27.
 * 对于订单，增加和删除都交给用户在页面进行处理
 */
@Controller
@RequestMapping("admin/order")
public class OrderController {

    private Logger logger = Logger.getLogger(OrderController.class);

    @Resource
    private UserService userService;


    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductService productService;

    @Resource
    private ProductImageService productImageService;

    @Resource
    private ReviewService reviewService;

    @Resource
    private OrderItemService orderItemService;

    @Resource
    private OrderService orderService;

    //订单管理
    @RequestMapping("admin_order_list.do")
    public ModelAndView listOrder(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView();
        Page page = PageUtil.getPage(pageNum);
        page.setTotalRecords(orderService.getTotalNumber());
        List<Order> orders = orderService.list(page.getStart(), page.getCount());
        for (Order order : orders) {
            FillUtil.fillOrder(order,userService.getUserById(order.getUid()),orderService,productService,userService,productImageService,
                    categoryService,reviewService,orderItemService);
            //logger.info(order);
        }


        modelAndView.addObject("orders", orders);
        modelAndView.addObject("page", page);
        modelAndView.setViewName("admin/listOrder");
        return modelAndView;
    }




}
