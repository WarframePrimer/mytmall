package com.warframe.mytmall.service.impl;

import com.warframe.mytmall.dao.OrderDAO;
import com.warframe.mytmall.dao.OrderItemDAO;
import com.warframe.mytmall.pojo.Order;
import com.warframe.mytmall.pojo.OrderItem;
import com.warframe.mytmall.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);
    //要完成自动装配，需要在对应的dao抽象接口前添加@Repository注解
    @Resource
    private OrderDAO orderDAO;

    @Resource
    private OrderItemDAO orderItemDAO;


    @Override
    public Order getOrderById(int id) {
        return orderDAO.getById(id);
    }

    @Override
    public void addOrder(Order order) {
        orderDAO.add(order);
    }

    @Override
    public int getTotalNumber() {
        return orderDAO.getTotalNumber();
    }

    @Override
    public List<Order> getOrders() {
        return orderDAO.listAll();
    }

    @Override
    public void deleteOrder(int id) {
        orderDAO.delete(id);
    }

    @Override
    public List<Order> list(int start, int count) {
        return orderDAO.list(start, count);
    }

    @Override
    public void createOrder(Order order) {

        orderDAO.add(order);
        logger.info("orderId:" + order.getId());

        List<OrderItem> orderItemList = order.getOrderItems();
        for (OrderItem orderItem : orderItemList) {
            logger.info("新增的order为：" + order.getId());
            orderItem.setOrder(order);
            //如果订单详情项id为零，表示其在数据库中不存在，需要先将其添加到数据库中
            if(orderItem.getId()==0){
                //添加后就需要进行更新了
                orderItemDAO.add(orderItem);
            }else{
                int count = orderItemDAO.updateOrderId(orderItem);
                if (count != 1) {
                    throw new RuntimeException("error");
                }
            }
//            logger.info("添加orderId后的orderItemId："+ orderItem.getId());

        }
    }

    @Override
    public List<Order> getOrdersByUserId(int uid) {
        return orderDAO.getOrdersByUserId(uid);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }
}
