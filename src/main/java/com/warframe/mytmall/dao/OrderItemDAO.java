package com.warframe.mytmall.dao;

import com.warframe.mytmall.pojo.OrderItem;
import com.warframe.mytmall.pojo.OrderItemCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Repository
public interface OrderItemDAO extends BaseDAO {


    @Override
    <T> void add(T t);

    @Override
    void delete(int id);

    @Override
    <T> void update(T t);

    @Override
    <T> T getById(int id);

    @Override
    <T> List<T> listAll();

    @Override
    <T> List<T> list(int start, int count);

    @Override
    int getTotalNumber();

    List<Integer> getNumberByProductId(int pid);

    List<OrderItemCustom> getOrderItemListWithOutOid(int uid);
    int getOrderItemNumber(int uid);

    int isExistOrderItemWithOutOidByProductIdAndUserId(@Param("pid") int pid,@Param("uid") int uid);

    OrderItemCustom getOrderItemCustomWithOutOidByProductIdAndUserId(@Param("pid") int pid,@Param("uid") int uid);

    int updateProductNumberByOrderItemId(OrderItemCustom orderItem);

    int updateOrderId(OrderItem orderItem);

    List<OrderItemCustom> getOrderItemCustomByOrderId(int oid);
}


