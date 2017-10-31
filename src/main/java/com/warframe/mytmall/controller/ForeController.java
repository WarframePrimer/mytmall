package com.warframe.mytmall.controller;


import com.warframe.mytmall.comparator.*;
import com.warframe.mytmall.pojo.*;
import com.warframe.mytmall.service.*;
import com.warframe.mytmall.util.FillUtil;
import com.warframe.mytmall.util.FormatUtil;
import com.warframe.mytmall.util.StringUtil;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;


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


        }


        modelAndView.addObject("categoryList", categoryList);
        //modelAndView.addObject("productsByCategoryMap", productsByCategoryMap);
        return modelAndView;
    }

    /**
     * 没有使用类似于solr和ElasticSearch等基于lucene的框架，只是简单的使用了数据库中的模糊查询，后面再改进
     *
     * @param keyword
     * @return
     */
    @RequestMapping("searchByKeyword.do")
    public ModelAndView searchByKeyword(@RequestParam(value = "keyword", defaultValue = "") String keyword) {
        ModelAndView modelAndView = new ModelAndView("frontPage/searchResult");

        logger.info("keyword:" + keyword);


        List<Product> productList = productService.searchByKeyword(keyword);
        //如果结果只不为零，遍历所有并进行填充
        if (productList.size() != 0) {
            for (Product product : productList) {
                //细节:这里传入的product是一个对象，所以在传值是其实传递的是一个引用，改变引用里面的值，对象就会改变
                FillUtil.fillProduct(product, productImageService, categoryService, reviewService, orderItemService, productService);
            }
            modelAndView.addObject("productList", productList);
        }
        return modelAndView;
    }

    /**
     * 通过分类进行相关搜索，并且对搜索后的结果提供按需求排序的效果
     *
     * @param categoryId 分类编号
     * @return
     */
    @RequestMapping("searchByCategory.do")
    public ModelAndView searchByCategory(@RequestParam(value = "categoryId", required = true) int categoryId,
                                         @RequestParam(value = "sort", defaultValue = "all") String sort) {
        ModelAndView modelAndView = new ModelAndView("frontPage/searchByCategoryResult");
        logger.info("要搜索的分类编号：" + categoryId);

        //获取到category
        Category category = categoryService.getCategoryById(categoryId);

        //获取该分类下的产品List 5*12 5个一排，一共12排，之后就进入下一页
        List<Product> productListByCategoryId = productService.listProductByCategoryId(0, productService.getTotalNumberByCategoryId(categoryId), categoryId);
        //填充product
        for (Product product : productListByCategoryId) {
            FillUtil.fillProduct(product, productImageService, categoryService, reviewService, orderItemService, productService);
        }

        //针对不同的sort对list进行不同的排序
        switch (sort) {
            case "all":
                Collections.sort(productListByCategoryId, new ProductAllComparator());
                break;
            case "date":
                Collections.sort(productListByCategoryId, new ProductDateComparator());
                break;
            case "saleCount":
                Collections.sort(productListByCategoryId, new ProductSaleCountComparator());
                break;
            case "price":
                Collections.sort(productListByCategoryId, new ProductPriceComparator());
                break;
            case "review":
                Collections.sort(productListByCategoryId, new ProductReviewComparator());
                break;
        }

        modelAndView.addObject("sortType", sort);
        modelAndView.addObject("category", category);
        modelAndView.addObject("productListByCategoryId", productListByCategoryId);

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
        FillUtil.fillProduct(product, productImageService, categoryService, reviewService, orderItemService, productService);


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

        //logger.info("productInfo:" + product);

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
        Map<String, String> map = new HashMap<>(1);
        User user = getLoginUser(request);
        int uid = user.getId();

        //如果已经存在相关记录，只需要在该记录的基础上对number字段增加相应的数量即可
        if (orderItemService.isExistInOrderItemWithOutOidByProductIdAndUserId(pid, uid)) {
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
        orderItem = FillUtil.fillOrderItem(orderItemCustom, orderService, productService, userService,
                productImageService, categoryService, reviewService, orderItemService);
        //logger.info(orderItem);
        orderItemList.add(orderItem);
        //计算总价格
        totalPrice = orderItem.getNumber() * orderItem.getProduct().getPromotePrice();

        //后续要生成订单，直接将orderitem(这里的orderitem是没有存到数据库中的)和交易金额保存到session中
        request.getSession(false).setAttribute("orderItemList", orderItemList);
        request.getSession(false).setAttribute("totalPrice", totalPrice);
        return modelAndView;
    }


    /**
     * 使用ajax对商品购买个数进行设置
     *
     * @param id
     * @param number
     * @return
     */
    @RequestMapping("updateOrderItemNumberByAjax.do")
    @ResponseBody
    public Map<String, String> updateOrderItemNumber(@RequestParam("oiid") int id,
                                                     @RequestParam("number") int number) {
        Map<String, String> map = new HashMap<>(1);

        orderItemService.updateProductNumber(id, number);

        map.put("msg", "success");

        return map;
    }


    //提交购物车的cartItem信息进行结算
    //要进行操作的是选中的购物车选项
    //结算的英文单词：balance
    //缺陷：价格不应该在参数中出现，这样就可以进行随意修改了，有很大的隐患
    @RequestMapping("balance.do")
    public ModelAndView balance(@RequestParam("oiid") List<Integer> oiids,
                                HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("frontPage/confirmOrder");

        List<OrderItem> orderItemList = new ArrayList<>();
        float totalPrice = 0;

        OrderItem orderItem;
        for (Integer oiid : oiids) {
            OrderItemCustom orderItemCustom = orderItemService.getOrderItemCustomById(oiid);
            orderItem = FillUtil.fillOrderItem(orderItemCustom, orderService, productService, userService,
                    productImageService, categoryService, reviewService, orderItemService);
            totalPrice += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
            orderItemList.add(orderItem);

        }


        //这里考虑将最后要提交的订单项保存到session中，后续的生成订单就可以直接从session中取
        httpSession.setAttribute("orderItemList", orderItemList);
        httpSession.setAttribute("totalPrice", totalPrice);
        return modelAndView;
    }


    //点击提交订单

    /**
     * 点击提交订单后，之前在结算和立即购买中已经将要提交的订单项添加到session中了
     * 可以直接从session中获取订单项
     * 之后进行新增订单操作，映射相关信息
     *
     * @param httpSession 这里面保存了要提交的订单项和交易的金额
     * @return
     */
    @RequestMapping("payOrder.do")
    public ModelAndView confirmOrder(@RequestParam("address") String address,
                                     @RequestParam("post") String post,
                                     @RequestParam("receiver") String receiver,
                                     @RequestParam("mobile") String mobile,
                                     @RequestParam(value = "userMessage", defaultValue = " ") String userMessage,
                                     HttpSession httpSession,
                                     HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("frontPage/payPage");
        address = StringUtil.toUTF(address);
        receiver = StringUtil.toUTF(receiver);
        userMessage = StringUtil.toUTF(userMessage);
//        logger.info(address + post + receiver + mobile + userMessage);
        User user = getLoginUser(request);
        //logger.info(user);
        List<OrderItem> orderItemList = (List<OrderItem>) httpSession.getAttribute("orderItemList");
        //logger.info("orderItemList：" + orderItemList.size());
        float totalPrice = (Float) httpSession.getAttribute("totalPrice");
        int totalNumber = 0;
        for (OrderItem orderItem : orderItemList) {
            totalNumber += orderItem.getNumber();
        }

        //一旦点击提交订单，无论支付与否都会update进order数据表，并且从购物车中移除，对于未支付的的订单，status为waitPay

        Order order = new Order();
        order.setAddress(address);
        order.setMobile(mobile);
        order.setPost(post);
        order.setReceiver(receiver);
        order.setUserMessage(userMessage);
        order.setUser(user);
        order.setCreateDate(new Date());
        order.setTotalPrice(totalPrice);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItemList);
        order.setOrderCode(FormatUtil.createOrderCode(user));
        order.setStatus("waitPay");
        //生成订单
        orderService.createOrder(order);

        //重新刷新购物车数量
        sessionSetUserAndCartItemNumber(request, user.getName());
        modelAndView.addObject("order", order);

        return modelAndView;
    }

    //点击付款之后跳转到payPage进行支付
    @RequestMapping("payOrderConfirm.do")
    public ModelAndView payOrderConfirm(@RequestParam("oid") int oid, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("frontPage/payPage");
        User user = getLoginUser(request);
        Order order = orderService.getOrderById(oid);
        order = FillUtil.fillOrder(order, user, orderService, productService,
                userService, productImageService, categoryService, reviewService, orderItemService);

        modelAndView.addObject("order", order);

        return modelAndView;
    }

    /**
     * 完成支付
     * 更新orderStatus为waitDelivery
     *
     * @param oid
     * @param request
     * @return
     */
    @RequestMapping("payComplete.do")
    public ModelAndView payComplete(@RequestParam("oid") int oid, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("frontPage/payComplete");
        User user = getLoginUser(request);

        Order order = orderService.getOrderById(oid);
        order.setPayDate(new Date());
        order.setStatus("waitDelivery");

        //logger.info("order:" + order);
        orderService.updateOrder(order);
        logger.info("order修改成功!!");

        order = FillUtil.fillOrder(order, user, orderService, productService,
                userService, productImageService, categoryService, reviewService, orderItemService);

        modelAndView.addObject("order", order);
        return modelAndView;
    }

    /**
     * 订单信息
     *
     * @return
     */
    @RequestMapping("showOrder.do")
    public ModelAndView showOrder(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            modelAndView.setViewName("redirect:login.do");
            return modelAndView;
        }
        //从数据库中获取相关数据
        List<Order> orders = orderService.getOrdersByUserId(user.getId());
        //logger.info(orders.size());
        if (orders.size() != 0) {
            for (Order order : orders) {
                FillUtil.fillOrder(order, user, orderService, productService,
                        userService, productImageService, categoryService, reviewService, orderItemService);

                //logger.info(order);

            }
            modelAndView.addObject("orders", orders);
        }


        modelAndView.setViewName("frontPage/myOrder");


        return modelAndView;
    }

    @RequestMapping("callToDelivery.do")
    @ResponseBody
    public Map<String, String> callToDelivery(@RequestParam("oid") int oid, @RequestParam("status") String status) {
        Map<String, String> map = new HashMap<>(1);

        Order order = orderService.getOrderById(oid);
        //设置发货时间
        order.setDeliveryDate(new Date());
        //更新订单状态为待收货
        order.setStatus(status);

        orderService.updateOrder(order);

        map.put("msg", "delivered");
        return map;
    }

    @RequestMapping("confirmReceipt.do")
    @ResponseBody
    public Map<String, String> confirmReceipt(@RequestParam("oid") int oid, @RequestParam("status") String status) {
        Map<String, String> map = new HashMap<>(1);

        Order order = orderService.getOrderById(oid);
        //设置收货事件
        order.setConfirmDate(new Date());
        //更新订单状态为待评价
        order.setStatus(status);

        orderService.updateOrder(order);

        map.put("msg", "confirmed");
        return map;
    }

    @RequestMapping("reviewProduct.do")
    public ModelAndView reviewProduct(@RequestParam("pid") int pid,
                                      @RequestParam("oid") int oid,
                                      HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("frontPage/reviewProduct");

        Order order = orderService.getOrderById(oid);

        Product product = productService.getProductById(pid);
        User user = (User) httpSession.getAttribute("user");


        //填充product
        product = FillUtil.fillProduct(product, productImageService, categoryService, reviewService, orderItemService, productService);


        modelAndView.addObject("product", product);
        modelAndView.addObject("order", order);
        //其实没必要
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping("commitReview.do")
    public ModelAndView commitReview(@RequestParam("pid") int pid,
                                     @RequestParam("oid") int oid,
                                     @RequestParam("content") String content,
                                     HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.getProductById(pid);
        int categoryId;
        int productId;


        Order order = orderService.getOrderById(oid);
        //填充product
        product = FillUtil.fillProduct(product, productImageService, categoryService, reviewService, orderItemService, productService);
        User user = (User) httpSession.getAttribute("user");

        //转码，防止乱码
        content = StringUtil.toUTF(content);

        //提交评价
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setContent(content);
        review.setCreateDate(new Date());

        reviewService.addReview(review);

        categoryId = product.getCategory().getId();
        productId = pid;

        //添加：评价商品完成后，将订单状态设置为完成,先不考虑细节问题
        order.setStatus("finish");
        orderService.updateOrder(order);

        modelAndView.setViewName("redirect:getProductDetail.do?pid=" + productId + "&cid=" + categoryId);

        //返回商品页面
        return modelAndView;
    }


    //得到当前登录的User对象
    public User getLoginUser(HttpServletRequest request) {
        User user = (User) request.getSession(false).getAttribute("user");
        return user;
    }

    //session中设置user对象和cartItemNumber属性
    public void sessionSetUserAndCartItemNumber(HttpServletRequest request, String userName) {
        User user = userService.getByUserName(userName);
        request.getSession().setAttribute("user", user);
        int cartItemNumber = orderItemService.getCartItemNumber(user.getId());
        request.getSession().setAttribute("cartItemNumber", cartItemNumber);
    }


    //简单购物车信息id,uid,pid,oid等等
    public List<OrderItemCustom> getSimpleCartItemList(HttpServletRequest request) {
        List<OrderItemCustom> simpleCartItemList = new ArrayList<>();
        User user = getLoginUser(request);
        if (null != user) {
            simpleCartItemList = orderItemService.getSimpleCartItemList(user.getId());
        }
        return simpleCartItemList;
    }

    //获取到当前用户的详细购物车信息
    public List<OrderItem> getCartItemList(HttpServletRequest request) {
        List<OrderItemCustom> simpleCartItemList = getSimpleCartItemList(request);

        List<OrderItem> cartItemList = new ArrayList<>();
        OrderItem orderItem;
        if (!simpleCartItemList.isEmpty()) {
            for (OrderItemCustom orderItemCustom : simpleCartItemList) {
                orderItem = FillUtil.fillOrderItem(orderItemCustom, orderService, productService, userService,
                        productImageService, categoryService, reviewService, orderItemService);
                cartItemList.add(orderItem);
            }

        }


        return cartItemList;
    }


}
