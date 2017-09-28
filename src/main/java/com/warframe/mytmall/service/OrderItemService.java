package com.warframe.mytmall.service;


import com.warframe.mytmall.pojo.OrderItem;
import com.warframe.mytmall.pojo.OrderItemCustom;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
public interface OrderItemService {

    OrderItemCustom getOrderItemCustomById(int id);

    void addOrderItem(OrderItem orderItem);

    int getTotalNumber();

    List<OrderItem> listAll();

    void deleteOrderItem(int id);

    void updateOrderItem(OrderItem orderItem);


    List<OrderItem> list(int start, int count);

    List<Integer> getNumberByProductId(int pid);

    //只是一对键值的数据，没有进行填充
    List<OrderItemCustom> getSimpleCartItemList(int uid);

    //未完成购买的订单项数量(购物车数量)
    int getCartItemNumber(int uid);


    //判断指定用户未完成订单项中是否含有指定产品的记录
    boolean isExistInOrderItemWithOutOidByProductIdAndUserId(int pid,int uid);

    OrderItemCustom getOrderItemCustomWithoutOidByProductIdAndProductId(int pid,int uid);

    int updateProductNumberByOrderItemId(OrderItemCustom orderItemCustom);

    @Transactional
    int updateProductNumber(int pid,int uid,int productNum);

    @Transactional
    void updateProductNumber(int id,int productNum);

    //点击立即购买后，添加订单项纪录
    void createNewOrderItem(OrderItemCustom orderItemCustom);

    int updateOrderId(OrderItem orderItem);

    List<OrderItemCustom> getOrderItemCustomByOrderId(int oid);
}
