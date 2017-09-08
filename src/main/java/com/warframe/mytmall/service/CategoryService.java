package com.warframe.mytmall.service;

import com.warframe.mytmall.pojo.Category;

import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
public interface CategoryService {
    Category getCategoryById(int id);

    void addCategory(Category category);

    List<Category> listAll();

    List<Category> list(int start, int count);

    void updateCategory(Category category);

    void deleteCategory(int id);

    int getTotalNumber();

}
