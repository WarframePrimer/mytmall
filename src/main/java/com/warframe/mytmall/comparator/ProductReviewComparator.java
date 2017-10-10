package com.warframe.mytmall.comparator;

import com.warframe.mytmall.pojo.Product;

import java.util.Comparator;

/**
 * @Author warframe[github.com/WarframePrimer]
 * @Date 2017/10/10 16:04
 */
public class ProductReviewComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount() - p1.getReviewCount();
    }
}
