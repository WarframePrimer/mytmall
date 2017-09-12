package com.warframe.mytmall.util;

import java.io.UnsupportedEncodingException;

/**
 * 解决post表单乱码
 * post表单提交的数值中会变成ISO-8859-1格式，使用spring中实现的
 * CharacterEncodingFilter也会出现编码错误(@_@)
 * I've no idea;
 */
public class StringUtil {
    public static final String CHARSET = "ISO-8859-1";


    //字符传编码格式转换
    public static String toUTF(String name) {
        String str = null;
        try {
            str = new String(name.getBytes(CHARSET), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return str;

    }


    //获取匿名昵称
    public static String getAnonymousName(String userName) {
        if (userName.length() <= 1) return "*";
        if (userName.length() == 2) return userName.substring(0, 1) + "*";

        char[] cs = userName.toCharArray();
        for (int i = 1; i < userName.length() - 1; i++) {
            cs[i] = '*';
        }
        return new String(cs);


    }

}
