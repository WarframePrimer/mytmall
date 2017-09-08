package com.warframe.mytmall.dao;

import com.warframe.mytmall.pojo.PropertyValue;
import com.warframe.mytmall.pojo.PropertyValueCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Repository
public interface PropertyValueDAO extends BaseDAO {

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


    //获取到指定产品的属性值记录数
    int getTotalNumberByProductId(int pid);


    //通过产品id获取所有的属性值不需要分页
    List<PropertyValue> getPropertyValuesByProductId(@Param("pid") int pid);

    List<PropertyValueCustom> getPropertyValueCustomByProductIdAndCategoryId(@Param("pid") int pid,@Param("cid") int cid);
}
