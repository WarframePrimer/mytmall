package com.warframe.mytmall.dao;


import com.warframe.mytmall.pojo.Property;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */

@Repository
public interface PropertyDAO extends BaseDAO {

    @Override
    <T> List<T> listAll();

    @Override
    <T> List<T> list(@Param("start") int start, @Param("count") int count);

    @Override
    <T> void add(T t);

    @Override
    void delete(int id);

    @Override
    <T> void update(T t);

    @Override
    <T> T getById(int id);

    @Override
    int getTotalNumber();


    List<Property> listByCategoryId(@Param("start") int start, @Param("count") int count, @Param("cid") int cid);

    int getTotalNumberByCategoryId(int cid);


}
