package com.warframe.mytmall.util;

import com.warframe.mytmall.pojo.*;
import com.warframe.mytmall.service.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author warframe[github.com/WarframePrimer]
 * @Date 2017/9/28 10:01
 * 对各实体类进行填充
 */
public class FillUtil {

    /**
     *
     * @param product 要填充的对象
     * @return
     */
    public static Product fillProduct(Product product,
                                      ProductImageService productImageService,
                                      CategoryService categoryService,
                                      ReviewService reviewService,
                                      OrderItemService orderItemService,
                                      ProductService productService){
        int pid = product.getId();


        product.setFirstProductImage(productImageService.getFirstProductImageByProductId(pid));

        product.setCategory(categoryService.getCategoryById(productService.getCategoryIdByProductId(pid)));

        product.setProductSingleImage(productImageService.listProductImageByProductIdAndType(pid, "type_single"));
        product.setProductDetailImage(productImageService.listProductImageByProductIdAndType(pid, "type_detail"));


        //评价数量
        product.setReviewCount(reviewService.getReviewCountByProductId(pid));
        List<Integer> productNumberList = orderItemService.getNumberByProductId(pid);

        //求出总销量(如果是月销量呢？)
        int saleCount = 0;
        if (!productNumberList.isEmpty()) {
            for (Integer integer : productNumberList) {
                saleCount += integer;
            }
        }
        product.setSaleCount(saleCount);

        return product;
    }


    public static OrderItem fillOrderItem(OrderItemCustom orderItemCustom,
                                          OrderService orderService,
                                          ProductService productService,
                                          UserService userService,
                                          ProductImageService productImageService,
                                          CategoryService categoryService,
                                          ReviewService reviewService,
                                          OrderItemService orderItemService){
        OrderItem orderItem = new OrderItem();
        int oid = orderItemCustom.getOid();
        int id = orderItemCustom.getId();
        int number = orderItemCustom.getNumber();
        int pid = orderItemCustom.getPid();
        int uid = orderItemCustom.getUid();

//        logger.info("oid:" + orderItemCustom.getOid());
        //oid不为空
        if (0 != orderItemCustom.getOid()) {
            Order order = orderService.getOrderById(oid);
            orderItem.setOrder(order);
        }
        Product product = productService.getProductById(pid);
        product = fillProduct(product,productImageService,categoryService,reviewService,orderItemService,productService);
        orderItem.setProduct(product);

        User user = userService.getUserById(uid);
        orderItem.setUser(user);

        orderItem.setId(id);
        orderItem.setNumber(number);


        return orderItem;
    }


    public static Order fillOrder(Order order,
                                  User user,
                                  OrderService orderService,
                                  ProductService productService,
                                  UserService userService,
                                  ProductImageService productImageService,
                                  CategoryService categoryService,
                                  ReviewService reviewService,
                                  OrderItemService orderItemService){

        List<OrderItemCustom> orderItemCustomList;
        //存放订单中对应的订单项
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem;

        order.setUser(user);
        int totalNumber = 0;
        float totalPrice = 0f;
        //得到简单的订单项
        orderItemCustomList = orderItemService.getOrderItemCustomByOrderId(order.getId());

        //对简单订单项进行填充
        for (OrderItemCustom orderItemCustom : orderItemCustomList) {
            orderItem = fillOrderItem(orderItemCustom,orderService,productService,
                    userService,productImageService,categoryService,reviewService,orderItemService);

            totalNumber += orderItem.getNumber();
            totalPrice += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
            orderItemList.add(orderItem);
        }

        //填充订单
        order.setOrderItems(orderItemList);
        order.setTotalPrice(totalPrice);
        order.setTotalNumber(totalNumber);

        return order;
    }
}
