package com.warframe.mytmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by warframe on 2017/6/22.
 * spring controller处理ajax的请求
 *
 */

@Controller
@RequestMapping("/ajax/*")
public class AjaxController {

    @RequestMapping("ajaxTest.do")
    @ResponseBody
    public Map<String,Object> ajaxTest(@RequestParam String name){
        //判断ajaxTest.jsp中传过来的name是否符合条件
        Map<String,Object> map = new HashMap<>();

        if("warframe".equals(name)){
            map.put("msg","名字正确");
        }else{
            map.put("msg","名字错误");
        }
        return map;
    }


}
