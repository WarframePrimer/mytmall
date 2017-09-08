package com.warframe.mytmall.service;

import com.warframe.mytmall.pojo.Property;
import com.warframe.mytmall.pojo.PropertyCustom;

import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
public interface PropertyService {
    Property getPropertyById(int id);

    void addProperty(Property property);

    List<Property> listAll();

    List<Property> list(int start, int count);

    void updateProperty(Property property);

    void deleteProperty(int id);

    int getTotalNumber();

    List<Property> listByCategoryId(int cid);

    int getTotalNumberByCategoryId(int cid);

    List<PropertyCustom> findPropertyCategoryByCategoryId(int cid);


}
