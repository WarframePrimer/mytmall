package com.warframe.mytmall.comparator;

import com.warframe.mytmall.pojo.Product;

import java.util.Comparator;

/**
 * @Author warframe[github.com/WarframePrimer]
 * @Date 2017/10/10 16:01
 */
public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getCreateDate().compareTo(p2.getCreateDate());
    }
}
