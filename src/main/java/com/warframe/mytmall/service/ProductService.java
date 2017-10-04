package com.warframe.mytmall.service;

import com.warframe.mytmall.pojo.Product;

import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
public interface ProductService {

    void addProduct(Product product);

    Product getProductById(int id);

    List<Product> listAll();

    List<Product> list(int start, int count);

    void deleteProduct(int id);

    void updateProduct(Product product);

    int getTotalNumber();

    int getTotalNumberByCategoryId(int cid);

    List<Product> listProductByCategoryId(int start, int count, int cid);

    int getCategoryIdByProductId(int pid);

    List<Product> searchByKeyword(String keyword);

}
