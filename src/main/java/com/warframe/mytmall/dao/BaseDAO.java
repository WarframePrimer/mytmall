package com.warframe.mytmall.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by warframe on 2017/6/4.
 * <p>
 * 这个借口提供了一些必要通用的增删改查的方法
 */

@Repository
public interface BaseDAO {

    <T> void add(T t);

    void delete(int id);

    <T> void update(T t);

    <T> T getById(int id);

    <T> List<T> listAll();

    <T> List<T> list(@Param("start") int start, @Param("count") int count);

    int getTotalNumber();

}
