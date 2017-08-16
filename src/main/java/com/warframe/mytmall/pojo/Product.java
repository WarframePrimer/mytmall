package com.warframe.mytmall.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by warframe on 2017/6/2.
 * 分析：分类和上商品的关系属于一对多的关系
 * 即一个分类中有多个商品，而一个商品只对应一个分类
 * <p>
 * 初步构想：使用静态工厂方法，简化构建bean的成本，算了，下次再说吧@_@
 */
public class Product {
    private int id;
    private Category category;

    private String name;
    private String subTitle;
    private float originalPrice;
    private float promotePrice;
    private int stock;

    private Date createDate;

    //商品的展示图片
    private ProductImage firstProductImage;

    private List<ProductImage> productSingleImage;
    private List<ProductImage> productDetailImage;

    /*评价个数和销售量为什么不放在数据库中？*/
    private int reviewCount;
    private int saleCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public float getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(float promotePrice) {
        this.promotePrice = promotePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ProductImage getFirstProductImage() {
        return firstProductImage;
    }

    public void setFirstProductImage(ProductImage firstProductImage) {
        this.firstProductImage = firstProductImage;
    }

    public List<ProductImage> getProductSingleImage() {
        return productSingleImage;
    }

    public void setProductSingleImage(List<ProductImage> productSingleImage) {
        this.productSingleImage = productSingleImage;
    }

    public List<ProductImage> getProductDetailImage() {
        return productDetailImage;
    }

    public void setProductDetailImage(List<ProductImage> productDetailImage) {
        this.productDetailImage = productDetailImage;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", originalPrice=" + originalPrice +
                ", promotePrice=" + promotePrice +
                ", stock=" + stock +
                ", createDate=" + createDate +
                '}';
    }
}
