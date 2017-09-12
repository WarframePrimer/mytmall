package com.warframe.mytmall.controller;

import com.warframe.mytmall.pojo.*;
import com.warframe.mytmall.service.*;
import com.warframe.mytmall.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import java.util.HashMap;
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
    private PropertyValueService propertyValueService;

    @Resource
    private ReviewService reviewService;

    @Resource
    private OrderItemService orderItemService;


    //实现登录注册


    /**
     * register.do和login.do只是单纯的到指定的页面，没有任何额外操作
     *
     * @return
     */

    @RequestMapping("register.do")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("frontPage/register");
        return modelAndView;
    }

    @RequestMapping("login.do")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("frontPage/login");
        return modelAndView;
    }


    //注册
    @RequestMapping(value = "registerUser.do", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam("name") String name,
                                     @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView("frontPage/register");
        name = StringUtil.toUTF(name);
        password = StringUtil.toUTF(password);
        //对字符串进行转义，防止恶意注册
        name = HtmlUtils.htmlEscape(name);
        password = HtmlUtils.htmlEscape(password);
        logger.info(name + ":" + password);

        if (!userService.isExist(name)) {
            //用户名尚未被注册，进行新增操作
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            userService.addUser(user);

            modelAndView.addObject("msg", "注册成功，请返回主页进行登录。");
        } else {
            //用户名已经被注册！！
            modelAndView.addObject("msg", "用户名已被注册！！请重新注册。");
        }

        return modelAndView;
    }

    //登录

    @RequestMapping("loginUser.do")
    public ModelAndView loginUser(@RequestParam("name") String name,
                                  @RequestParam("password") String password,
                                  HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        name = HtmlUtils.htmlEscape(StringUtil.toUTF(name));
        password = HtmlUtils.htmlEscape(StringUtil.toUTF(password));
        logger.info(name + ":" + password);


        if (userService.isExist(name)) {

            //存在该用户名，进行下一步判断
            //logger.info("存在该用户");
            if (userService.checkUser(name, password)) {
                //用户名和密码都正确，登录成功

                request.getSession().setAttribute("userName", name);
                modelAndView.setViewName("redirect:home.do");
                //不需要在modelAndView中添加用户名，userName已经添加到session中了
                //modelAndView.addObject("userName", name);
            } else {
                //密码错误
                modelAndView.setViewName("frontPage/login");
                modelAndView.addObject("msg", "密码错误");
            }
        } else {
            //用户名不存在
            modelAndView.setViewName("frontPage/login");
            modelAndView.addObject("msg", "用户名不存在！");
        }


        return modelAndView;
    }

    //检查用户是否登陆，只需要检查session中是否有userName这个属性即可
    @RequestMapping("checkLogin.do")
    @ResponseBody
    //将返回的map对象转换为json对象
    public Map<String, String> checkLogin(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        Map<String, String> map = new HashMap<>();
        if (null != userName)
            //表示用户已登录
            map.put("msg","success");
        //表示为登陆
        else map.put("msg","fail");

        return map;
    }

    //loginAjax
    @RequestMapping("loginAjax.do")
    @ResponseBody
    public Map<String,String> loginAjax(@RequestParam("name")String name,
                                        @RequestParam("password")String password,
                                        HttpServletRequest request){
        Map<String,String> map = new HashMap<>();

        if (userService.isExist(name)) {

            //存在该用户名，进行下一步判断
            //logger.info("存在该用户");
            if (userService.checkUser(name, password)) {
                //用户名和密码都正确，登录成功
                request.getSession().setAttribute("userName", name);
                map.put("msg","success");
            } else {
                //密码错误
               map.put("msg","密码错误!");
            }
        } else {
            //用户名不存在
            map.put("msg","用户名不存在!!");
        }

        return map;
    }



    //退出登录
    @RequestMapping("logout.do")
    public ModelAndView logout(HttpServletRequest request) {
        //将登陆的用户名从session中remove
        request.getSession().removeAttribute("userName");
        ModelAndView modelAndView = new ModelAndView("redirect:home.do");
        return modelAndView;
    }


    //首页
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

        //可不可以将对于产品一系列的初始化转移到响应的service中去的，以便进行更好的封装？

        //产品属性属性值
        List<PropertyValueCustom> propertyValueCustomList = propertyValueService.getPropertyValueCustomByProductIdAndCategoryId(pid, cid);

        //对产品设置缩略图和详情图
        List<ProductImage> productSingleImage = productImageService.listProductImageByProductIdAndType(pid, "type_single");
        List<ProductImage> productDetailImage = productImageService.listProductImageByProductIdAndType(pid, "type_detail");

        product.setProductSingleImage(productSingleImage);
        product.setProductDetailImage(productDetailImage);

        //给产品设置评价数量和销量
        product.setReviewCount(reviewService.getReviewCountByProductId(pid));

        List<Integer> productNumberList = orderItemService.getNumberByProductId(pid);
        int saleCount = 0;
        if (!productNumberList.isEmpty()) {
            for (Integer integer : productNumberList) {
                saleCount += integer;
            }
        }
        product.setSaleCount(saleCount);


        //具体评价内容
        List<ReviewCustom> reviewCustomList = reviewService.getReviewCustomsByProductId(pid);
        if (!reviewCustomList.isEmpty()) {
            //设置匿名昵称
            String anonymousName;
            for (ReviewCustom reviewCustom : reviewCustomList) {
                anonymousName = StringUtil.getAnonymousName(reviewCustom.getUserName());
                reviewCustom.setUserName(anonymousName);
            }
        }

        logger.info("productInfo:" + product);

        modelAndView.addObject("product", product);
        modelAndView.addObject("propertyValueCustomList", propertyValueCustomList);
        modelAndView.addObject("reviewCustomList", reviewCustomList);
        return modelAndView;
    }


}
