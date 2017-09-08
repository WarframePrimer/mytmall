package com.warframe.mytmall.pojo;

import java.util.Date;

/**
 * Created by warframe on 2017/6/2.
 * 一个商品有多条评价
 * 一个评价只对应一个商品
 * 一个评价只对应一个用户
 * 一个用户有多个评价(没有这个业务需求,待定(需求：用户要查看自己的评价记录))
 */
public class Review {
    private int id;
    private String content;
    private User user;
    private Product product;
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
