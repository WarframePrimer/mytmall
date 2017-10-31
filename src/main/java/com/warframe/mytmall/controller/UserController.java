package com.warframe.mytmall.controller;

import com.warframe.mytmall.pojo.Page;
import com.warframe.mytmall.pojo.User;
import com.warframe.mytmall.service.UserService;
import com.warframe.mytmall.util.PageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/27.
 */

@Controller
@RequestMapping("admin/user")
public class UserController {

    @Resource
    private UserService userService;


    private Logger logger = Logger.getLogger(UserController.class);

    //用户管理
    @RequestMapping("admin_user_list.do")
    public ModelAndView listUser(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView();
        Page page = PageUtil.getPage(pageNum);
        page.setTotalRecords(userService.getTotalNumber());
        List<User> users = userService.list(page.getStart(), page.getCount());
        modelAndView.addObject("users", users);
        modelAndView.addObject("page", page);
        modelAndView.setViewName("admin/listUser");
        return modelAndView;
    }


}
