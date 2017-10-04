package com.warframe.mytmall.dao;

import com.warframe.mytmall.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.Serializers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Repository
public interface ProductDAO extends BaseDAO {
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

    //产品分类id为相应id的所有产品总数
    int getTotalNumberByCategoryId(int cid);

    //分页显示对应分类id的产品信息
    List<Product> listProductByCategoryId(@Param("start") int start, @Param("count") int count, @Param("cid") int cid);


    int getCategoryIdByProductId(int pid);

    List<Product> searchByKeyword(String keyword);

//    void addProduct(Product product);
//
//    Product getProductById(int id);
//
//    int getTotalNumber();
//
//    void update(Product product);
//
//    void delete(int id);
//
//    List<Product> listAll();
//
//    List<Product> list(@Param("start") int start, @Param("count") int count);

}
