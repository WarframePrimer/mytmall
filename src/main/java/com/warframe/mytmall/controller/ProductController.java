package com.warframe.mytmall.controller;

import com.warframe.mytmall.pojo.*;
import com.warframe.mytmall.service.*;
import com.warframe.mytmall.util.FileUpload;
import com.warframe.mytmall.util.PageUtil;
import com.warframe.mytmall.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by warframe on 2017/7/13.
 */

@Controller
@RequestMapping("admin/product")
public class ProductController {

    //将产生的信息添加进日志
    /**
     * 用于进行测试调试
     */
    private Logger logger = Logger.getLogger(ProductController.class);

    @Resource
    private ProductService productService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductImageService productImageService;

    @Resource
    private PropertyService propertyService;

    @Resource
    private PropertyValueService propertyValueService;


    /**
     * 显示对应分类所包含的所有的产品(传入对应的{category.id})
     *
     * @return
     */
    @RequestMapping(value = "admin_product_list.do")
    public ModelAndView listProduct(@RequestParam(value = "cid", defaultValue = "0") int cid,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("admin/listProduct");

        //首先判断传入值是否合理
        if (0 == cid) {
            modelAndView.addObject("cidError", "无cid传入");
            return modelAndView;
        } else {
            //对page分页实体类进行相关设置(设置总记录数，当前页数，每页记录数)
            Page page = PageUtil.getPage(pageNum);
            page.setTotalRecords(productService.getTotalNumberByCategoryId(cid));
            page.setParam("&cid=" + cid);

            //生成相应cid的category实体类
            Category category = categoryService.getCategoryById(cid);

            //获得相应的产品信息
            List<Product> products = productService.listProductByCategoryId(page.getStart(), page.getCount(), cid);

            //向页面中添加之后构建网页元素的数据
            modelAndView.addObject("page", page);
            modelAndView.addObject("category", category);
            modelAndView.addObject("products", products);

            return modelAndView;


        }
    }


    /**
     * 产品增加的方法，在相应的分类中添加从form表单中获取的产品信息列表
     * 需要的产品信息有分类，产品名称，产品小标题，初始价格，优惠价格，库存
     */
    @RequestMapping(value = "admin_product_add.do", method = RequestMethod.POST)
    public ModelAndView addProduct(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                   @RequestParam("categoryId") int cid,
                                   @RequestParam("productName") String pname,
                                   @RequestParam("subTitle") String subTitle,
                                   @RequestParam float originalPrice,
                                   @RequestParam float promotePrice,
                                   @RequestParam int stock) {
        /**
         * 可优化
         */
        Category category = categoryService.getCategoryById(cid);
        //logger.info("category:" + category);

        Product product = new Product();
        product.setCategory(category);
        product.setName(StringUtil.toUTF(pname));
        product.setOriginalPrice(originalPrice);
        product.setPromotePrice(promotePrice);
        product.setCreateDate(new Date());
        product.setSubTitle(StringUtil.toUTF(subTitle));
        product.setStock(stock);


        logger.info("添加的产品信息为:" + product);

        productService.addProduct(product);

        ModelAndView modelAndView = new ModelAndView("redirect:admin_product_list.do?cid=" + category.getId() + "&pageNum=" + pageNum);

        return modelAndView;
    }


    //使用ajax进行删除指定产品id的产品信息并同步到数据库
    @RequestMapping(value = "admin_product_ajaxDelete.do", method = RequestMethod.GET)
    //ResponseBody注解用来读取Request请求的body的部分数据，使用系统默认配置的HttpMessageConverter进行解析
    //然后将相应的数据绑定到要返回的对象上。
    @ResponseBody
    public Map<String, Object> deleteProduct(@RequestParam("pid") int pid) {


        productService.deleteProduct(pid);
        logger.info("删除的产品id：" + pid);

        Map<String, Object> map = new HashMap<>();
        map.put("pid", pid);
        return map;
    }


    @RequestMapping("admin_product_preEdit.do")
    public ModelAndView preEditProduct(@RequestParam("pid") int pid, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam("cid") int cid) {
        Product product = productService.getProductById(pid);

        Category category = categoryService.getCategoryById(cid);

        logger.info("编辑前的产品信息：" + product);
        logger.info("产品所属分类：" + category.getName());


        ModelAndView modelAndView = new ModelAndView("admin/productPreEdit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("product", product);
        modelAndView.addObject("pageNum", pageNum);


        return modelAndView;
    }


    /**
     * 修改商品
     *
     * @return
     */
    @RequestMapping(value = "admin_product_edit.do", method = RequestMethod.POST)
    public ModelAndView editProduct(@RequestParam("productName") String productName,
                                    @RequestParam("cid") int cid,
                                    @RequestParam("pid") int pid,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam("subTitle") String subTitle,
                                    @RequestParam("originalPrice") float originalPrice,
                                    @RequestParam("promotePrice") float promotePrice,
                                    @RequestParam("stock") int stock) {

        Category category = categoryService.getCategoryById(cid);


        Product product = new Product();
        product.setId(pid);
        product.setSubTitle(StringUtil.toUTF(subTitle));
        product.setStock(stock);
        product.setOriginalPrice(originalPrice);
        product.setPromotePrice(promotePrice);
        product.setName(StringUtil.toUTF(productName));
        product.setCategory(category);
        product.setCreateDate(new Date());

        //logger.info("修改后的商品信息：" + product);

        productService.updateProduct(product);

        logger.info("成功修改商品信息!");

        ModelAndView modelAndView = new ModelAndView("redirect:admin_product_list.do?cid=" + cid + "&pageNum=" + pageNum);


        return modelAndView;
    }



    /*===============================ProductImage的相关操作====================================================*/
    /**
     * 将关于productImage的相关操作也放到ProductController中，避免过多的controller
     */


    /**
     * 指定产品的相关图片(包括缩略图和详情图)
     * list图片，所以不涉及文件的上传，不需要CommonsMultiFile用于文件上传
     *
     * @param pid
     * @param cid
     * @param pageNum
     * @return
     */
    @RequestMapping("admin_product_imageList.do")
    public ModelAndView listProductImage(@RequestParam("pid") int pid,
                                         @RequestParam("cid") int cid,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

        Category category = categoryService.getCategoryById(cid);
        Product product = productService.getProductById(pid);

        //缩略图
        List<ProductImage> productImageSingle = productImageService.listProductImageByProductIdAndType(pid, "type_single");
        //详情图
        List<ProductImage> productImageDetail = productImageService.listProductImageByProductIdAndType(pid, "type_detail");

        ModelAndView modelAndView = new ModelAndView("admin/listProductImage");

        modelAndView.addObject("category", category);
        modelAndView.addObject("product", product);
        modelAndView.addObject("productImageSingle", productImageSingle);
        modelAndView.addObject("productImageDetail", productImageDetail);


        return modelAndView;
    }


    /**
     * 添加产品图片(包括缩略图和详情图)
     *
     * @param pid
     * @param type
     * @return
     */
    @RequestMapping("admin_productImage_add.do")
    public ModelAndView addProductImage(@RequestParam("filePath") CommonsMultipartFile filePath,
                                        @RequestParam("pid") int pid,
                                        @RequestParam("cid") int cid,
                                        @RequestParam("type") String type,
                                        HttpServletRequest request) {


        Product product = productService.getProductById(pid);
        logger.info("要添加图片的产品:" + product.getName());

        Category category = categoryService.getCategoryById(cid);
        logger.info("要添加图片产品所属分类:" + category.getName());

        ProductImage productImage = new ProductImage();
        productImage.setProduct(product);
        productImage.setType(type);

        //使用数据库自增策略后会将增长的id注入到原来的bean中
        productImageService.addProductImage(productImage);

        logger.info("添加图片的id:" + productImage.getId());

        FileUpload.fileUploadProductImage(String.valueOf(productImage.getId()), filePath, request);


        ModelAndView modelAndView = new ModelAndView("redirect:admin_product_imageList.do?pid=" + pid + "&cid=" + cid);

        return modelAndView;

    }

    /**
     * 删除产品图片
     * 使用ajax
     * done
     *
     * @param piid
     * @return
     */
    @RequestMapping("admin_productImage_deleteByAjax.do")
    @ResponseBody
    public Map<String, Integer> deleteProductImage(@RequestParam("piid") int piid, HttpServletRequest request) {


        Map<String, Integer> map = new HashMap<>();
        //删除数据库中的记录

        //ProductImage productImage = productImageService.getProductImageById(piid);
        productImageService.deleteProductImageById(piid);
        logger.info("删除的产品id:" + piid);


        //删除图片
        String path = request.getSession().getServletContext().getRealPath("/img/productImage");

        logger.info("path:" + path);

        File fileToDelete = new File(path, piid + ".jpg");

        System.out.println(fileToDelete.getParent());
        logger.info("文件是否存在:" + fileToDelete.exists());

        boolean delete = fileToDelete.delete();

        if (!delete) {
            logger.info("图片删除失败！");
        } else {
            logger.info("删除的图片名：" + fileToDelete.getName());
        }

        map.put("piid", piid);

        return map;

    }


    /*======================================PropertyValue的相关操作======================================================================*/

    /**
     * 属性值是跟产品挂钩的，所以考虑将属性值的相关操作也加进ProductController中
     *
     */

    /**
     * 属性值应该是可以直接进行编辑的
     * 只需要传入产品所需的属性和属性值即可
     * <p>
     * 显示的应该是产品所属分类的所有属性
     *
     * @param pid
     * @param cid
     * @return
     */
    @RequestMapping("admin_product_propertyEdit.do")
    public ModelAndView listProductProperty(@RequestParam("pid") int pid,
                                            @RequestParam("cid") int cid) {
        ModelAndView modelAndView = new ModelAndView("admin/editProductProperty");


        Category category = categoryService.getCategoryById(cid);
        logger.info("分类:" + category.getName());
        Product product = productService.getProductById(pid);

        List<PropertyValueCustom> propertyValueCustomList = propertyValueService.getPropertyValueCustomByProductIdAndCategoryId(pid, cid);


//        Category category = categoryService.getCategoryById(cid);
//        //通过分类id获取所有的属性名
//        List<Property> propertyList = propertyService.listByCategoryId(cid);
//
//        Product product = productService.getProductById(pid);
//        //通过产品id获取所有属性值
//        List<PropertyValue> propertyValues = propertyValueService.getPropertyValuesByProductId(pid);


        modelAndView.addObject("product", product);
        modelAndView.addObject("category", category);
//        modelAndView.addObject("propertyList",propertyList);
//        modelAndView.addObject("propertyValues",propertyValues);

        modelAndView.addObject("propertyValueCustomList", propertyValueCustomList);

        return modelAndView;
    }


    //对产品属性值的设置以及更新
    @RequestMapping("admin_product_updatePropertyValue.do")
    @ResponseBody
    public Map<String, Integer> updatePropertyValue(@RequestParam("propertyValue") String value,
                                                    @RequestParam("pid") int pid,
                                                    @RequestParam("ptid") int ptid,
                                                    @RequestParam("pvid") int pvid) {
        logger.info("propertyValue:" + value);
        logger.info("pid:" + pid);
        logger.info("ptid:" + ptid);
        logger.info("pvid:" + pvid);

        Map<String, Integer> map = new HashMap<>();
        PropertyValue propertyValue;

        if (pvid != 0) {
            //如果id不等于0说明数据库中已经存在该记录那么就进行修改
            propertyValue = propertyValueService.getPropertyValueById(pvid);
            //这个部署到服务器为什么会出错
            propertyValue.setProduct(productService.getProductById(pid));
            propertyValue.setProperty(propertyService.getPropertyById(ptid));
            logger.info("显示属性值相关信息:" + propertyValue.toString());
            logger.info("原来的属性值:" + propertyValue.getValue());
            propertyValue.setValue(value);
            propertyValueService.updatePropertyValue(propertyValue);
            logger.info("修改后的属性值:" + propertyValue.getValue());
        } else {
            //如果id为0进行插入操作
            propertyValue = new PropertyValue();
            propertyValue.setProduct(productService.getProductById(pid));
            propertyValue.setProperty(propertyService.getPropertyById(ptid));
            propertyValue.setValue(value);
            logger.info("新增的属性值:" + propertyValue.getValue());
            propertyValueService.addPropertyValue(propertyValue);

            //对于新增的propertyvalue来说，应该传回一个增加后的pvid，防止进行多次add操作
            map.put("pvid", propertyValue.getId());

        }


//        PropertyValue propertyValue = new PropertyValue();
//        if(pvid!=0) propertyValue.setId(pvid);
//        propertyValue.setProduct(productService.getProductById(pid));
//        propertyValue.setProperty(propertyService.getPropertyById(ptid));
//        propertyValue.setValue(value);
//        propertyValueService.addPropertyValue(propertyValue);


        map.put("info", 1);


        return map;
    }


}
