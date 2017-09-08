package com.warframe.mytmall.service.impl;

import com.warframe.mytmall.dao.CategoryDAO;
import com.warframe.mytmall.pojo.Category;
import com.warframe.mytmall.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
    @Resource
    private CategoryDAO categoryDAO;


    @Override
    public Category getCategoryById(int id) {
        return categoryDAO.getById(id);
    }

    @Override
    public void addCategory(Category category) {
        categoryDAO.add(category);
    }

    @Override
    public List<Category> listAll() {
        return categoryDAO.listAll();
    }

    @Override
    public List<Category> list(int start, int count) {
        return categoryDAO.list(start,count);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDAO.update(category);
    }

    @Override
    public void deleteCategory(int id) {
        categoryDAO.delete(id);
    }

    @Override
    public int getTotalNumber() {
        return categoryDAO.getTotalNumber();
    }
}
