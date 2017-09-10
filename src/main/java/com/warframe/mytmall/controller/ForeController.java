package com.warframe.mytmall.controller;

import com.warframe.mytmall.pojo.*;
import com.warframe.mytmall.service.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;



import java.util.List;
import java.util.Map;

/**
 * @Author warframe[github.com/WarframePrimer]
 * @Date 2017/9/6 14:41
 * <p>
 * 用于处理一系列前端的地址转向和跳转
 */

@Controller
public class ForeController {

    private static Logger logger = Logger.getLogger(ForeController.class);

    @Resource
    private UserService userService;

    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductService productService;
    @Resource
    private ProductImageService productImageService;
    @Resource
    private PropertyService propertyService;
    @Resource
    private PropertyValueService propertyValueService;

    //实现登录注册

    @RequestMapping("register.do")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView("frontPage/register");
        return modelAndView;
    }

    @RequestMapping(value = "registerUser.do",method= RequestMethod.POST)

    public ModelAndView registerUser(@RequestParam("name")String name,
                                     @RequestParam("password")String password){



        return null;
    }





    @RequestMapping("home.do")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("frontPage/home");

        //获取所有分类
        List<Category> categoryList = categoryService.listAll();


//        //获取所属分类的产品,并且放入一个map中
//        //这里使用LinkedHashMap的原因是这样可以保证顺序不变，如果使用HashMap会导致前端页面每次获取到产品页面会变动
//        Map<Category, List<Product>> productsByCategoryMap = new LinkedHashMap<>(categoryList.size());

        //添加每个分类下的产品关键词中用于在分类旁的推荐中进行显示


        for (Category category : categoryList) {
            List<Product> productList = productService.listProductByCategoryId(0, productService.getTotalNumberByCategoryId(category.getId()), category.getId());

            //为每一个产品set一个展示图片
            for (Product product : productList) {
                product.setFirstProductImage(productImageService.getFirstProductImageByProductId(product.getId()));
            }

            //将该分类的所有商品都赋值给category的products属性
            category.setProducts(productList);

            //给每个分类设置productsByRow
            category.setProductsByRow(category.createProductsByRows(productList));

            //productsByCategoryMap.put(category, productList);
        }


        modelAndView.addObject("categoryList", categoryList);
        //modelAndView.addObject("productsByCategoryMap", productsByCategoryMap);
        return modelAndView;
    }

    //商品页面
    @RequestMapping("getProductDetail.do")
    public ModelAndView getProductDetail(@RequestParam("pid") int pid,
                                         @RequestParam("cid") int cid) {
        ModelAndView modelAndView = new ModelAndView("frontPage/product");
        Product product = productService.getProductById(pid);
        Category category = categoryService.getCategoryById(cid);
        product.setCategory(category);

        //产品属性属性值
        List<PropertyValueCustom> propertyValueCustomList = propertyValueService.getPropertyValueCustomByProductIdAndCategoryId(pid,cid);

        //对产品设置缩略图和详情图
        List<ProductImage> productSingleImage = productImageService.listProductImageByProductIdAndType(pid, "type_single");
        List<ProductImage> productDetailImage = productImageService.listProductImageByProductIdAndType(pid,"type_detail");

        product.setProductSingleImage(productSingleImage);
        product.setProductDetailImage(productDetailImage);

        //TODO 产品的销量和评价数



        logger.info("productInfo:" + product);

        modelAndView.addObject("product",product);
        modelAndView.addObject("propertyValueCustomList",propertyValueCustomList);
        return modelAndView;
    }




}
