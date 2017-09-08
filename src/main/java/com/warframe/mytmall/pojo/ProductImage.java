package com.warframe.mytmall.pojo;

/**
 * Created by warframe on 2017/6/2.
 * 一个商品有多张图片，一个图片只对应一个商品
 *
 * 图片属于哪一个产品
 */
public class ProductImage {

    private int id;
    private Product product;

    //这张图片属于什么类型的是详情中还是缩略图等
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
