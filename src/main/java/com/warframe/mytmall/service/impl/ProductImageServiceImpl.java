package com.warframe.mytmall.service.impl;

import com.warframe.mytmall.dao.ProductImageDAO;
import com.warframe.mytmall.pojo.ProductImage;
import com.warframe.mytmall.service.ProductImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Service("productImageService")
public class ProductImageServiceImpl implements ProductImageService{

    @Resource
    private ProductImageDAO productImageDAO;



    @Override
    public void addProductImage(ProductImage productImage) {
        productImageDAO.add(productImage);
    }

    @Override
    public ProductImage getProductImageById(int id) {
        return productImageDAO.getById(id);
    }

    @Override
    public List<ProductImage> listAll() {
        return productImageDAO.listAll();
    }

    @Override
    public void deleteProductImageById(int id) {
        productImageDAO.delete(id);
    }



    @Override
    public int getTotalNumber() {
        return productImageDAO.getTotalNumber();
    }

    @Override
    public List<ProductImage> listProductImageByProductIdAndType(int pid, String type) {
        return productImageDAO.listProductImageByProductIdAndType(pid,type);
    }

    @Override
    public ProductImage getFirstProductImageByProductId(int pid) {
        return productImageDAO.getFirstProductImageByProductId(pid);
    }


}
