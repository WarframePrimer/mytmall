package com.warframe.mytmall.dao;

import com.warframe.mytmall.pojo.ProductImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Repository
public interface ProductImageDAO extends BaseDAO {

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


    //添加功能根据相应产品id以及需要的图片(缩略图or详情)进行查询
    List<ProductImage> listProductImageByProductIdAndType(@Param("pid") int pid, @Param("type") String type);

    ProductImage getFirstProductImageByProductId(int pid);
}
