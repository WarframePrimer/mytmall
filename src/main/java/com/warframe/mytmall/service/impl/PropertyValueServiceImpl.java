package com.warframe.mytmall.service.impl;

import com.warframe.mytmall.dao.PropertyValueDAO;
import com.warframe.mytmall.pojo.PropertyValue;
import com.warframe.mytmall.pojo.PropertyValueCustom;
import com.warframe.mytmall.service.PropertyValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Service("propertyValueService")
public class PropertyValueServiceImpl implements PropertyValueService{

    @Resource
    private PropertyValueDAO propertyValueDAO;

    @Override
    public PropertyValue getPropertyValueById(int id) {
        return propertyValueDAO.getById(id);
    }

    @Override
    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueDAO.add(propertyValue);
    }

    @Override
    public int getTotalNumber() {
        return propertyValueDAO.getTotalNumber();
    }

    @Override
    public List<PropertyValue> getProperties() {
        return propertyValueDAO.listAll();
    }

    @Override
    public void deletePropertyValue(int id) {
        propertyValueDAO.delete(id);
    }

    @Override
    public void updatePropertyValue(PropertyValue propertyValue) {
        propertyValueDAO.update(propertyValue);
    }

    @Override
    public List<PropertyValue> list(int start, int count) {
        return propertyValueDAO.list(start, count);
    }

    @Override
    public int getTotalNumberByProductId(int pid) {
        return propertyValueDAO.getTotalNumberByProductId(pid);
    }

    @Override
    public List<PropertyValue> getPropertyValuesByProductId(int pid) {
        return propertyValueDAO.getPropertyValuesByProductId(pid);
    }

    @Override
    public List<PropertyValueCustom> getPropertyValueCustomByProductIdAndCategoryId(int pid,int cid) {
        return propertyValueDAO.getPropertyValueCustomByProductIdAndCategoryId(pid,cid);
    }
}
