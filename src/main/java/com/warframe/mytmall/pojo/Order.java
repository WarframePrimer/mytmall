package com.warframe.mytmall.pojo;

import com.warframe.mytmall.dao.OrderDAO;

import java.util.Date;
import java.util.List;

/**
 * Created by warframe on 2017/6/2.
 * 一个订单有多个订单项
 * 一个订单项只对应一个点单
 * 一个订单属于一个用户
 */
public class Order {
    private int id;

    private int uid;

    private User user;
    private List<OrderItem> orderItems;

    private String orderCode;
    private String address;
    private String userMessage;
    private String status;
    private String mobile;

    private Date createDate;
    private Date payDate;
    private Date deliveryDate;
    private Date confirmDate;

    private String post;
    private String receiver;


    //数量和总价格后期添加
    private int totalNumber;
    private float totalPrice;

    //获取状态码
    public String getStatusDesc() {
        String desc = "未知";
        switch (status) {
            case "waitPay":
                desc = "待付款";
                break;
            case "waitDelivery":
                desc = "待发货";
                break;
            case "waitConfirm":
                desc = "待收货";
                break;
            case "waitReview":
                desc = "等评价";
                break;
            case "finish":
                desc = "完成";
                break;
            case "delete":
                desc = "刪除";
                break;
            default:
                desc = "未知";
        }
        return desc;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uid=" + uid +
                ", user=" + user +
                ", orderItems=" + orderItems +
                ", orderCode='" + orderCode + '\'' +
                ", address='" + address + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", status='" + status + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createDate=" + createDate +
                ", payDate=" + payDate +
                ", deliveryDate=" + deliveryDate +
                ", confirmDate=" + confirmDate +
                ", post='" + post + '\'' +
                ", receiver='" + receiver + '\'' +
                ", totalNumber=" + totalNumber +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
