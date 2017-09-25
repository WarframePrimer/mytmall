package com.warframe.mytmall.util;

import com.warframe.mytmall.pojo.User;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author warframe[github.com/WarframePrimer]
 * @Date 2017/9/25 10:06
 */
public class FormatUtil {
    /**
     * 用于生成订单编号
     */
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");


    public static String createOrderCode(User user){
       StringBuilder stringBuilder = new StringBuilder(simpleDateFormat.format(new Date()));
       stringBuilder.append(user.getId());
       return stringBuilder.toString();
    }
}
