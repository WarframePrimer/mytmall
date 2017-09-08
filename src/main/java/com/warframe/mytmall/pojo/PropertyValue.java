package com.warframe.mytmall.pojo;

/**
 * Created by warframe on 2017/6/2.
 * <p>
 * 哪个商品的哪个属性
 */
public class PropertyValue {
    private int id;
    private String value;
    private Product product;

    private Property property;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "PropertyValue{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
