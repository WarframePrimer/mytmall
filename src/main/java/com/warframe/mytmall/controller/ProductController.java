package com.warframe.mytmall.controller;

import com.warframe.mytmall.pojo.Category;
import com.warframe.mytmall.pojo.Page;
import com.warframe.mytmall.pojo.Product;
import com.warframe.mytmall.service.CategoryService;
import com.warframe.mytmall.service.ProductService;
import com.warframe.mytmall.util.PageUtil;
import com.warframe.mytmall.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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


    /**
     * 显示对应分类所包含的所有的产品(传入对应的{category.id})
     *
     * @return
     */
    @RequestMapping(value = "admin_product_list.do")
    public ModelAndView listProduct(@RequestParam(value = "cid", defaultValue = "0") int cid, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
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
        logger.info("category:" + category);

        Product product = new Product();
        product.setCategory(category);
        product.setName(StringUtil.toUTF(pname, "ISO-8859-1"));
        product.setOriginalPrice(originalPrice);
        product.setPromotePrice(promotePrice);
        product.setCreateDate(new Date());
        product.setSubTitle(StringUtil.toUTF(subTitle, "ISO-8859-1"));
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
        product.setSubTitle(StringUtil.toUTF(subTitle,"ISO-8859-1"));
        product.setStock(stock);
        product.setOriginalPrice(originalPrice);
        product.setPromotePrice(promotePrice);
        product.setName(StringUtil.toUTF(productName,"ISO-8859-1"));
        product.setCategory(category);
        product.setCreateDate(new Date());

        logger.info("修改后的商品信息：" + product);

        productService.updateProduct(product);

        ModelAndView modelAndView = new ModelAndView("redirect:admin_product_list.do?cid=" + cid + "&pageNum=" + pageNum);


        return modelAndView;
    }



    

}
