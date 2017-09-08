package com.warframe.mytmall.service.impl;

import com.warframe.mytmall.dao.PropertyDAO;
import com.warframe.mytmall.pojo.Property;
import com.warframe.mytmall.pojo.PropertyCustom;
import com.warframe.mytmall.service.PropertyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Service("propertyService")
public class PropertyServiceImpl implements PropertyService{


    @Resource
    private PropertyDAO propertyDAO;

    @Override
    public Property getPropertyById(int id) {
        return propertyDAO.getById(id);
    }

    @Override
    public void addProperty(Property property) {
        propertyDAO.add(property);
    }

    @Override
    public List<Property> listAll() {
        return propertyDAO.listAll();
    }

    @Override
    public List<Property> list(int start, int count) {
        return propertyDAO.list(start, count);
    }

    @Override
    public void updateProperty(Property property) {
        propertyDAO.update(property);
    }

    @Override
    public void deleteProperty(int id) {
        propertyDAO.delete(id);
    }

    @Override
    public int getTotalNumber() {
        return propertyDAO.getTotalNumber();
    }

    @Override
    public List<Property> listByCategoryId(int cid) {
        return propertyDAO.listByCategoryId(cid);
    }

    @Override
    public int getTotalNumberByCategoryId(int cid) {
        return propertyDAO.getTotalNumberByCategoryId(cid);
    }

    @Override
    public List<PropertyCustom> findPropertyCategoryByCategoryId(int cid) {
        return propertyDAO.findPropertyCategoryByCategoryId(cid);
    }
}
