package com.warframe.mytmall.test;

import com.alibaba.fastjson.JSON;
import com.warframe.mytmall.pojo.*;
import com.warframe.mytmall.service.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by warframe on 2017/6/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})

public class TestMybatis {

    private Logger logger = Logger.getLogger(TestMybatis.class);

    @Resource
    private UserService userService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private PropertyService propertyService;

    @Resource
    private ProductService productService;

    @Resource
    private PropertyValueService propertyValueService;

    @Resource
    private OrderItemService orderItemService;



    @Test
    public void test1() {
        User user = new User();
        user.setId(2);
        user.setName("测试BaseDAO");
        user.setPassword("fadf");


//        userService.addUser(user);
        User userCpy = userService.getUserById(2);

        logger.info(JSON.toJSONString(userCpy));

    }

    @Test
    public void test2() {
        Category category = new Category();
        category.setName("电器");

        //categoryService.addCategory(category);


        List<Category> categories = categoryService.listAll();

        for (Category c : categories) {
            logger.info(JSON.toJSONString(c));
        }


        Category c1 = categoryService.getCategoryById(2);
        logger.info(JSON.toJSONString(c1));

    }

    @Test
    public void testProperty() {
        Category category = new Category();
        category.setId(2);
        category.setName("电器");
        Property property = new Property();
        property.setCategory(category);
        property.setId(1);
        property.setName("尺寸");

        propertyService.addProperty(property);

        List<Property> properties = propertyService.listAll();
        for (Property p : properties) {
            logger.info(JSON.toJSONString(p));
        }
    }

    @Test
    public void testProduct() {
        productService.deleteProduct(3);
        Category c = new Category();
        c.setName("电器");
        c.setId(2);
        Product product = new Product();
        product.setName("神州笔记本");
        product.setOriginalPrice(4399f);
        product.setPromotePrice(4389f);
        product.setSubTitle("上船了！！！");
        product.setStock(56);
        product.setCreateDate(new Date());
        product.setCategory(c);
        productService.addProduct(product);



        List<Product> products = productService.listAll();
        for(Product p: products){
            logger.info(JSON.toJSONString(p));
        }

    }

    @Test
    public void testPropertyValue(){
        Product p = new Product();
        p.setId(1);
        Property pt = new Property();
        pt.setId(1);
        PropertyValue pv = new PropertyValue();
        pv.setId(1);
        pv.setProduct(p);
        pv.setProperty(pt);
        pv.setValue("1.9*0.8");
        propertyValueService.addPropertyValue(pv);
        List<PropertyValue> values = propertyValueService.getProperties();
        for(PropertyValue pvs:values){
            logger.info(JSON.toJSONString(pvs));
        }
    }


    @Test
    public void testPropertyCustom(){

        //mybatis中使用resultType实现
        List<PropertyCustom> propertyCustoms = propertyService.findPropertyCategoryByCategoryId(1);
        for(PropertyCustom propertyCustom:propertyCustoms){
            System.out.println(propertyCustom);
        }
    }

    @Test
    public void testPropertyValueCustomByProductId(){
        List<PropertyValueCustom> propertyValueCustoms = propertyValueService.getPropertyValueCustomByProductIdAndCategoryId(1,1);
        for(PropertyValueCustom propertyValueCustom:propertyValueCustoms){
            System.out.println(propertyValueCustom);
        }
    }


//    @Test
//    public void test3(){
//        Category category = new Category();
//        category.setId(1);
//        category.setName("家居");
//
//        Product product = new Product();
//        product.setId(1);
//        product.setName("单人床");
//        product.setSubTitle("这是一张单人床");
//        product.setCategory(category);
//
//        product.setCreateDate(new Date());
//        product.setStock(40);
//        product.setOriginalPrice(158.5f);
//        product.setPromotePrice(148.9f);
//
//        //productServicce.addProduct(product);
//
//        Product p1 = productServicce.getProductById(1);
//
//
//        logger.info(JSON.toJSONString(p1));
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sf.format(p1.getCreateDate()));
//
//    }

    @Test
    public void testUpdateProduct(){
        orderItemService.updateProductNumber(6,6,4);

    }

    @Test
    public void testUpdateProductNumberByOrderItemId(){
        OrderItemCustom orderItemCustom = new OrderItemCustom();
        orderItemCustom.setId(1);
        orderItemCustom.setPid(6);
        orderItemCustom.setUid(6);
        orderItemCustom.setNumber(4);
        orderItemService.updateProductNumberByOrderItemId(orderItemCustom);
    }

    @Test
    public void testUpdateProductNumber1(){
        orderItemService.updateProductNumber(6,7);
    }

}
