package com.warframe.mytmall.dao;

import com.warframe.mytmall.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.Serializers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Repository
public interface OrderDAO extends BaseDAO {

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
    <T> List<T> list(@Param("start") int start, @Param("count") int count);

    @Override
    int getTotalNumber();

    List<Order> getOrdersByUserId(int uid);

    void updateOrder(Order order);

}
