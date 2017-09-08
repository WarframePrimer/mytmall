package com.warframe.mytmall.pojo;

/**
 * 自定义属性值实体类
 * 添加字段
 * productName
 * propertyName
 *
 * 该自定义实体类包括 propertyValue.id,propertyName,propertyValue,productName
 *
 */

public class PropertyValueCustom extends PropertyValue{

    private int productId;
    private int propertyId;
    private String productName;
    private String propertyName;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String toString() {
        return "PropertyValueCustom{" +
                "productName='" + productName + '\'' +
                ", propertyName='" + propertyName + '\'' + super.toString() +
                '}';
    }
}
