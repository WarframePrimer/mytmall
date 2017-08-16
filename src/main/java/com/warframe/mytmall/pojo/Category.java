package com.warframe.mytmall.pojo;

import java.util.List;

/**
 * Created by warframe on 2017/6/2.
 */
public class Category {
    private int id;
    private String name;

    private List<Product> products;

    //这个的作用在后面会出现后面的商品分类展示中需要将每一分类的商品进行按行展示
    private List<List<Product>> productsByRows;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsByRows() {
        return productsByRows;
    }

    public void setProductsByRows(List<List<Product>> productsByRows) {
        this.productsByRows = productsByRows;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
