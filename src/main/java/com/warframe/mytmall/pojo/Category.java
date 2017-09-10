package com.warframe.mytmall.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by warframe on 2017/6/2.
 */
public class Category {
    private int id;
    private String name;

    private List<Product> products;

    //这个的作用在后面会出现后面的商品分类展示中需要将每一分类的商品进行按行展示
    private List<List<Product>> productsByRow;

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

    public List<List<Product>> getProductsByRow() {
        return productsByRow;
    }


    public void setProductsByRow(List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }

    //productByRows属性是根据List<Product>来进行设置的，每8个产品放入一个list，在把这个list放到List<List<Product>>中
    public List<List<Product>> createProductsByRows(List<Product> products){
        int productNumberEachRow = 8;

        List<List<Product>> productsByRow = new ArrayList<>();
        for(int i = 0;i<products.size();i+=productNumberEachRow){
            int size = i+productNumberEachRow;
            size = size>products.size()?products.size():size;
            List<Product> productsOfEachRow = products.subList(i,size);
            productsByRow.add(productsOfEachRow);
        }

        return productsByRow;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
