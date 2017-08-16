package com.warframe.mytmall.controller;

import com.warframe.mytmall.pojo.Order;
import com.warframe.mytmall.pojo.Page;
import com.warframe.mytmall.service.OrderService;
import com.warframe.mytmall.util.PageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by warframe on 2017/6/27.
 */
@Controller
@RequestMapping("admin/order")
public class OrderController {

    private Logger logger = Logger.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;

    //订单管理
    @RequestMapping("admin_order_list.do")
    public ModelAndView listOrder(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum){
        ModelAndView modelAndView = new ModelAndView();
        Page page = PageUtil.getPage(pageNum);
        int start = (page.getPageNum()-1)*page.getCount();
        page.setTotalRecords(orderService.getTotalNumber());
        List<Order> orders = orderService.list(start,page.getCount());
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("page",page);
        modelAndView.setViewName("admin/listOrder");
        return modelAndView;
    }

}
