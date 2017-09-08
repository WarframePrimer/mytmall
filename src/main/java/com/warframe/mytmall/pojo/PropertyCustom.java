package com.warframe.mytmall.pojo;


/**
 * 一对多映射，需要自己添加字段比较多的新的实体类
 */

public class PropertyCustom extends Property{

    /*
        添加分类属性
        category.name
    */
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



    @Override
    public String toString() {
        return "PropertyCustom{" +
                "categoryName='" + categoryName + '\'' + super.toString() +
                '}';
    }
}
