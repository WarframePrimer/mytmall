package com.warframe.mytmall.controller;


import com.warframe.mytmall.pojo.*;
import com.warframe.mytmall.service.*;
import com.warframe.mytmall.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
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

    @Resource
    private OrderService orderService;

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

    //登录使用ajax来进行实现

//    @RequestMapping("loginUser.do")
//    public ModelAndView loginUser(@RequestParam("name") String name,
//                                  @RequestParam("password") String password,
//                                  HttpServletRequest request) {
//        ModelAndView modelAndView = new ModelAndView();
//        name = HtmlUtils.htmlEscape(StringUtil.toUTF(name));
//        password = HtmlUtils.htmlEscape(StringUtil.toUTF(password));
//        logger.info(name + ":" + password);
//
//
//        if (userService.isExist(name)) {
//
//            //存在该用户名，进行下一步判断
//            //logger.info("存在该用户");
//            if (userService.checkUser(name, password)) {
//                //用户名和密码都正确，登录成功
//                sessionSetUserAndCartItemNumber(request, name);
//                modelAndView.setViewName("redirect:home.do");
//
//            } else {
//                //密码错误
//                modelAndView.setViewName("frontPage/login");
//                modelAndView.addObject("msg", "密码错误");
//            }
//        } else {
//            //用户名不存在
//            modelAndView.setViewName("frontPage/login");
//            modelAndView.addObject("msg", "用户名不存在！");
//        }
//
//        return modelAndView;
//    }

    //检查用户是否登陆，只需要检查session中是否有userName这个属性即可
    @RequestMapping("checkLogin.do")
    @ResponseBody
    //将返回的map对象转换为json对象
    public Map<String, String> checkLogin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Map<String, String> map = new HashMap<>();
        if (null != user)
            //表示用户已登录
            map.put("msg", "success");
            //表示为登陆
        else map.put("msg", "fail");

        return map;
    }

    //loginAjax
    @RequestMapping("loginAjax.do")
    @ResponseBody
    public Map<String, String> loginAjax(@RequestParam("name") String name,
                                         @RequestParam("password") String password,
                                         HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        logger.info("userName:" + name);
        logger.info("password:" + password);

        if (userService.isExist(name)) {

            //存在该用户名，进行下一步判断
            //logger.info("存在该用户");
            if (userService.checkUser(name, password)) {
                //用户名和密码都正确，登录成功
                sessionSetUserAndCartItemNumber(request, name);
                map.put("msg", "success");
            } else {
                //密码错误
                map.put("msg", "密码错误!");
            }
        } else {
            //用户名不存在
            map.put("msg", "用户名不存在!!");
        }

        return map;
    }

    //退出登录
    @RequestMapping("logout.do")
    public ModelAndView logout(HttpServletRequest request) {
        //将登陆的用户名和购物车数量从session中remove
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("cartItemNumber");
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

        //产品属性属性值
        List<PropertyValueCustom> propertyValueCustomList = propertyValueService.getPropertyValueCustomByProductIdAndCategoryId(pid, cid);

        //对产品具体信息进行填充
        fillProduct(product);


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


    /**
     * uid,pid,number
     * 添加购物车 使用ajax添加购物车
     *
     * @param pid
     * @param productNum
     * @param request
     * @return
     */
    @RequestMapping("addCart.do")
    @ResponseBody
    public Map<String, String> addCart(@RequestParam("productId") int pid,
                                       @RequestParam("productNum") int productNum,
                                       HttpServletRequest request) {
        //新的问题，对于同一个产品，如果点击多次加入购物车，不应该新建一个orderItem记录，应该在原有记录上增加数量


        Map<String, String> map = new HashMap<>(1);
        User user = getLoginUser(request);
        int uid = user.getId();

        //如果已经存在相关记录，只需要在该记录的基础上对number字段增加相应的数量即可
        if (orderItemService.isExistInOrderItemWithOutOidByProductIdAndUserId(pid, uid)) {
            //update商品数量
            orderItemService.updateProductNumber(pid, uid, productNum);
        } else {
            //如果不存在就新增orderItem记录
            Product product = productService.getProductById(pid);
            OrderItem orderItem = new OrderItem();
            orderItem.setUser(user);
            orderItem.setProduct(product);
            orderItem.setNumber(productNum);
            //添加到数据库中
            orderItemService.addOrderItem(orderItem);
        }


        sessionSetUserAndCartItemNumber(request, user.getName());

        //添加成功
        map.put("msg", "success");
        return map;
    }





    @RequestMapping(value = "deleteCartItemByAjax.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> deleteCartItem(@RequestParam("orderItemId") int oiid, HttpServletRequest request) {
        logger.info("要删除的订单项:" + oiid);
        User user = getLoginUser(request);
        Map<String, String> map = new HashMap<>();

        orderItemService.deleteOrderItem(oiid);

        map.put("msg", "success");

        sessionSetUserAndCartItemNumber(request, user.getName());

        return map;

    }


    /**
     * 显示购物车信息
     * 在数据库中如果orderitem表中有记录中oid字段为空，就表示该条记录为未提交订单的订单项
     * 并进行提交订单的动作
     * 确认订单
     *
     * @return
     */
    @RequestMapping("cart.do")
    public ModelAndView cartList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        User user = getLoginUser(request);
        if (null == user) {
            modelAndView.setViewName("redirect:login.do");
            return modelAndView;
        }

        //购物车信息
        List<OrderItem> cartItemList = getCartItemList(request);

        modelAndView.setViewName("frontPage/cartItem");
        modelAndView.addObject("cartItemList", cartItemList);

        return modelAndView;
    }

    /**
     * 在登录的前提下
     * 立即购买
     * 立即购买之后如果没有提交订单那么这次纪录不要更新进数据库
     *
     * @return
     */
    @RequestMapping("buyProduct.do")
    public ModelAndView buyProduct(@RequestParam("pid") int pid,
                                   @RequestParam("num") int productNum,
                                   HttpServletRequest request) {
        //跳转到提交订单的页面
        ModelAndView modelAndView = new ModelAndView("frontPage/confirmOrder");

        User user = getLoginUser(request);

        OrderItemCustom orderItemCustom = new OrderItemCustom();
        orderItemCustom.setNumber(productNum);
        orderItemCustom.setPid(pid);
        orderItemCustom.setUid(user.getId());

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem;
        float totalPrice;

        /**
         * 如果点击立即购买，不管数据库中是否有相关记录都新建一个订单项
         * 这样可以省去很多数据库误读脏读数据的问题
         *
         */
        orderItem = fillOrderItem(orderItemCustom);
        logger.info(orderItem);
        orderItemList.add(orderItem);
        //计算总价格
        totalPrice = orderItem.getNumber() * orderItem.getProduct().getPromotePrice();

        modelAndView.addObject("orderItemList", orderItemList);
        modelAndView.addObject("totalPrice", totalPrice);

        return modelAndView;
    }


    @RequestMapping("updateOrderItemNumberByAjax.do")
    @ResponseBody
    public Map<String,String> updateOrderItemNumber(@RequestParam("oiid")int id,
                                                    @RequestParam("number")int number){
        Map<String,String> map = new HashMap<>(1);

        orderItemService.updateProductNumber(id,number);

        map.put("msg","success");

        return map;
    }



    //提交购物车的cartItem信息进行结算
    //对各订单项的最后数据进行确认，update各订单项
    // 之后进行提交订单的操作
    //结算的英文单词：balance
    @RequestMapping("balance.do")
    public ModelAndView balance(@RequestParam(value = "totalPrice",required = true)float Price,
                                HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("frontPage/confirmOrder");
        User user = getLoginUser(request);
        List<OrderItem> orderItemList = getCartItemList(request);
        float totalPrice = Price;

        modelAndView.addObject("orderItemList", orderItemList);
        modelAndView.addObject("totalPrice", totalPrice);

        return modelAndView;
    }




    //点击提交订单
    @RequestMapping("confirmOrder.do")
    public ModelAndView confirmOrder(){
        return null;
    }


    //填充Product对象
    private Product fillProduct(Product product) {
        int pid = product.getId();
        //展示图片
        product.setFirstProductImage(productImageService.getFirstProductImageByProductId(pid));

        //设置商品的分类
        Category category = categoryService.getCategoryById(productService.getCategoryIdByProductId(pid));

        product.setCategory(category);
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


        return product;

    }

    //填充OrderItem对象
    private OrderItem fillOrderItem(OrderItemCustom orderItemCustom) {
        OrderItem orderItem = new OrderItem();
        int oid = orderItemCustom.getOid();
        int id = orderItemCustom.getId();
        int number = orderItemCustom.getNumber();
        int pid = orderItemCustom.getPid();
        int uid = orderItemCustom.getUid();

        logger.info("oid:" + orderItemCustom.getOid());
        //oid不为空
        if (0 != orderItemCustom.getOid()) {
            Order order = orderService.getOrderById(oid);
            orderItem.setOrder(order);
        }
        Product product = productService.getProductById(pid);
        product = fillProduct(product);
        orderItem.setProduct(product);

        User user = userService.getUserById(uid);
        orderItem.setUser(user);

        orderItem.setId(id);
        orderItem.setNumber(number);
        //logger.info(orderItem);
        return orderItem;
    }

    //得到当前登录的User对象
    private User getLoginUser(HttpServletRequest request) {
        User user = (User) request.getSession(false).getAttribute("user");
        return user;
    }

    //session中设置user对象和cartItemNumber属性
    private void sessionSetUserAndCartItemNumber(HttpServletRequest request, String userName) {
        User user = userService.getByUserName(userName);
        request.getSession().setAttribute("user", user);
        int cartItemNumber = orderItemService.getCartItemNumber(user.getId());
        request.getSession().setAttribute("cartItemNumber", cartItemNumber);
    }


    //简单购物车信息id,uid,pid,oid等等
    private List<OrderItemCustom> getSimpleCartItemList(HttpServletRequest request){
        List<OrderItemCustom> simpleCartItemList = new ArrayList<>();
        User user = getLoginUser(request);
        if (null != user) {
            logger.info("userId:" + user.getId());
            simpleCartItemList = orderItemService.getSimpleCartItemList(user.getId());
        }
        return simpleCartItemList;
    }

    //获取到当前用户的详细购物车信息
    private List<OrderItem> getCartItemList(HttpServletRequest request) {
        List<OrderItemCustom> simpleCartItemList = getSimpleCartItemList(request);

        List<OrderItem> cartItemList = new ArrayList<>();
        OrderItem orderItem;
        if (!simpleCartItemList.isEmpty()) {
            for (OrderItemCustom orderItemCustom : simpleCartItemList) {
                orderItem = fillOrderItem(orderItemCustom);
                cartItemList.add(orderItem);
            }

        }

        for (OrderItem orderItem1 : cartItemList) {
            logger.info(orderItem1);
        }


        return cartItemList;
    }

}
