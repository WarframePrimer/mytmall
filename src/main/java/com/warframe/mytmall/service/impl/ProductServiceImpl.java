package com.warframe.mytmall.service.impl;

import com.warframe.mytmall.dao.ProductDAO;
import com.warframe.mytmall.pojo.Product;
import com.warframe.mytmall.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */

@Service("productService")
public class ProductServiceImpl implements ProductService{


    @Resource
    private ProductDAO productDAO;

    @Override
    public void addProduct(Product product) {
        productDAO.add(product);
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.getById(id);
    }

    @Override
    public List<Product> listAll() {
        return productDAO.listAll();
    }

    @Override
    public List<Product> list(int start, int count) {
        return productDAO.list(start, count);
    }

    @Override
    public void deleteProduct(int id) {
        productDAO.delete(id);
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    @Override
    public int getTotalNumber() {
        return productDAO.getTotalNumber();
    }

    @Override
    public int getTotalNumberByCategoryId(int cid) {
        return productDAO.getTotalNumberByCategoryId(cid);
    }

    @Override
    public List<Product> listProductByCategoryId(int start, int count, int cid) {
        return productDAO.listProductByCategoryId(start, count, cid);
    }

    @Override
    public int getCategoryIdByProductId(int pid) {
        return productDAO.getCategoryIdByProductId(pid);
    }

    @Override
    public List<Product> searchByKeyword(String keyword) {
        return productDAO.searchByKeyword(keyword);
    }
}