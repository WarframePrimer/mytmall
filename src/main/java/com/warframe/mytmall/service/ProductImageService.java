package com.warframe.mytmall.service;

import com.warframe.mytmall.pojo.ProductImage;

import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 * <p>
 * 产品图片相关的操作
 * 图片个数有限，不用分页
 * </p>
 */
public interface ProductImageService {


    void addProductImage(ProductImage productImage);


    ProductImage getProductImageById(int id);

    List<ProductImage> listAll();

    //List<ProductImage> list(int start, int count);

    void deleteProductImageById(int id);


    //更新图片这个功能可以取消，对于要更新的图片，先进行删除然后再增加就行了
    //void updateProductImage(ProductImage productImage);

    int getTotalNumber();

    //int getTotalNumberByCategoryId(int cid);

    List<ProductImage> listProductImageByProductIdAndType(int pid,String type);

    ProductImage getFirstProductImageByProductId(int pid);


}
