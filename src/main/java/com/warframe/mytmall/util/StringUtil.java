package com.warframe.mytmall.util;

import java.io.UnsupportedEncodingException;

/**
 * 解决post表单乱码
 * post表单提交的数值中会变成ISO-8859-1格式，使用spring中实现的
 * CharacterEncodingFilter也会出现编码错误(@_@)
 * I've no idea;
 */
public class StringUtil {

    public static String toUTF(String name, String charset) {
        String str = null;
        try {
            str = new String(name.getBytes(charset), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return str;

    }

}
