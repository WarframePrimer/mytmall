package com.warframe.mytmall.service;

import com.warframe.mytmall.pojo.OrderItem;

import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
public interface OrderItemService {

    OrderItem getOrderItemById(int id);
    void addOrderItem(OrderItem orderItem);
    int getTotalNumber();
    List<OrderItem> listAll();
    void deleteOrderItem(int id);
    void updateOrderItem(OrderItem orderItem);

    List<OrderItem> list(int start,int count);
}
