package com.warframe.mytmall.comparator;

import com.warframe.mytmall.pojo.Product;

import java.util.Comparator;

/**
 * @Author warframe[github.com/WarframePrimer]
 * @Date 2017/10/10 15:55
 * <p>
 * comparable和comparator的区别：
 * comparable是一个内比较器，通常是一个class实现这么一个接口，只有一个方法要重写compareTo
 * comparator是一个外比较器，比较两个对象
 */
public class ProductAllComparator implements Comparator<Product> {


    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount() * p2.getSaleCount() - p1.getReviewCount() * p1.getSaleCount();
    }
}
