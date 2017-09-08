package com.warframe.mytmall.util;

import com.warframe.mytmall.pojo.Page;

/**
 * Created by warframe on 2017/6/27.
 */
public class PageUtil {

    public static Page getPage(int pageNum) {
        Page page = new Page(5, pageNum);
        int start = (page.getPageNum() - 1) * page.getCount();
        page.setStart(start);
        return page;
    }
}
