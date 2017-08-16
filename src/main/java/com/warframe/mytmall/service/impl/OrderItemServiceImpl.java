package com.warframe.mytmall.service.impl;

import com.warframe.mytmall.dao.OrderItemDAO;
import com.warframe.mytmall.pojo.OrderItem;
import com.warframe.mytmall.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService{
    @Resource
    private OrderItemDAO orderItemDAO;



    @Override
    public OrderItem getOrderItemById(int id) {
        return orderItemDAO.getById(id);
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        orderItemDAO.add(orderItem);
    }

    @Override
    public int getTotalNumber() {
        return orderItemDAO.getTotalNumber();
    }

    @Override
    public List<OrderItem> listAll() {
        return orderItemDAO.listAll();
    }

    @Override
    public void deleteOrderItem(int id) {
        orderItemDAO.delete(id);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        orderItemDAO.update(orderItem);
    }


    @Override
    public List<OrderItem> list(int start, int count) {
        return orderItemDAO.list(start, count);
    }
}
