package com.warframe.mytmall.service.impl;

import com.warframe.mytmall.dao.OrderItemDAO;
import com.warframe.mytmall.pojo.OrderItem;
import com.warframe.mytmall.pojo.OrderItemCustom;
import com.warframe.mytmall.service.OrderItemService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {
    private static Logger logger = Logger.getLogger(OrderItemServiceImpl.class);


    @Resource
    private OrderItemDAO orderItemDAO;


    @Override
    public OrderItemCustom getOrderItemCustomById(int id) {
        return orderItemDAO.getById(id);
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        orderItemDAO.add(orderItem);
    }

    @Override
    public int getTotalNumber() {
        return orderItemDAO.getTotalNumber();
    }

    @Override
    public List<OrderItem> listAll() {
        return orderItemDAO.listAll();
    }

    @Override
    public void deleteOrderItem(int id) {
        orderItemDAO.delete(id);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        orderItemDAO.update(orderItem);
    }


    @Override
    public List<OrderItem> list(int start, int count) {
        return orderItemDAO.list(start, count);
    }

    @Override
    public List<Integer> getNumberByProductId(int pid) {
        return orderItemDAO.getNumberByProductId(pid);
    }

    @Override
    public List<OrderItemCustom> getSimpleCartItemList(int uid) {
        return orderItemDAO.getOrderItemListWithOutOid(uid);
    }

    @Override
    public int getCartItemNumber(int uid) {
        return orderItemDAO.getOrderItemNumber(uid);
    }

    @Override
    public boolean isExistInOrderItemWithOutOidByProductIdAndUserId(int pid, int uid) {
        //已经存在记录返回true，否则返回false
        return orderItemDAO.isExistOrderItemWithOutOidByProductIdAndUserId(pid, uid) >= 1 ? true : false;
    }

    @Override
    public OrderItemCustom getOrderItemCustomWithoutOidByProductIdAndProductId(int pid, int uid) {
        OrderItemCustom orderItemCustom = null;
        if(isExistInOrderItemWithOutOidByProductIdAndUserId(pid,uid)){
            orderItemCustom = orderItemDAO.getOrderItemCustomWithOutOidByProductIdAndUserId(pid, uid);

        }else{
            try {
                throw new Exception("订单项不存在！！");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("订单项不存在！！！");
            }
        }
        return orderItemCustom;
    }


    /**
     * 这里数据库的增删改查都没有考虑异常，后面完善
     * @param orderItemCustom
     * @return
     */
    @Override
    public int updateProductNumberByOrderItemId(OrderItemCustom orderItemCustom) {
        return orderItemDAO.updateProductNumberByOrderItemId(orderItemCustom);
    }

    //先取得要修改的记录，对相应参数进行修改，然后再update回数据库所以
    //应该要使用事务，上面是不奏效
    /**
     *
     * @param pid
     * @param uid
     * @return
     */
    @Override
    public int updateProductNumber(int pid, int uid,int productNum) {
        int count;
        //如果已经存在，找到该条记录
        OrderItemCustom orderItemCustom = getOrderItemCustomWithoutOidByProductIdAndProductId(pid, uid);

        logger.info(getOrderItemCustomWithoutOidByProductIdAndProductId(pid, uid));

        //重新设置商品数量
        orderItemCustom.setNumber(orderItemCustom.getNumber()+productNum);

        count = updateProductNumberByOrderItemId(orderItemCustom);

        logger.info(getOrderItemCustomWithoutOidByProductIdAndProductId(pid, uid));

        return count;
    }
    //overload(参数类型，数量)
    @Override
    public void updateProductNumber(int id, int productNum) {
        OrderItemCustom orderItemCustom = orderItemDAO.getById(id);
        orderItemCustom.setNumber(productNum);
        logger.info(orderItemCustom.toString());
        //更新商品数量
        updateProductNumberByOrderItemId(orderItemCustom);
    }


    /**
     * 点击立即购买之后创建新的订单项
     *
     * @param orderItemCustom
     */
    @Override
    public void createNewOrderItem(OrderItemCustom orderItemCustom) {
        if(isExistInOrderItemWithOutOidByProductIdAndUserId(orderItemCustom.getPid(),orderItemCustom.getUid())){

        }
    }

    @Override
    public int updateOrderId(OrderItem orderItem) {
        return orderItemDAO.updateOrderId(orderItem);
    }

    @Override
    public List<OrderItemCustom> getOrderItemCustomByOrderId(int oid) {
        return orderItemDAO.getOrderItemCustomByOrderId(oid);
    }


}
