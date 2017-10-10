package com.warframe.mytmall.comparator;

import com.warframe.mytmall.pojo.Product;

import java.util.Comparator;

/**
 * @Author warframe[github.com/WarframePrimer]
 * @Date 2017/10/10 16:03
 */
public class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return (int) (p1.getPromotePrice() - p2.getPromotePrice());
    }
}
