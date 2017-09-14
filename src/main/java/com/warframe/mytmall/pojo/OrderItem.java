package com.warframe.mytmall.pojo;

import java.util.List;

/**
 * Created by warframe on 2017/6/2.
 * 一个订单项只属于一个用户
 * 一个订单项中只有一个商品
 * 一个订单中才有多个商品！！
 * 一个订单项只属于一个订单
 */
public class OrderItem {

    private int id;
    private User user;
    private Product product;
    private int number;
    private Order order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", number=" + number +
                ", order=" + order +
                '}';
    }
}
