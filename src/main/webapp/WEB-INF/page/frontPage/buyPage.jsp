<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/13
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@include file="../include/header.jsp"%>
<%@include file="../include/top.jsp"%>

<!--结算页面-->
<div class="buyPageDiv">
    <!--结算页面标题图片-->
    <div class="buyFlow">
        <!--也可以用bootstrap中的pull-left和pull-right-->
        <img src="img/site/simpleLogo.png" alt="图片加载失败">
        <img src="img/site/buyflow.png" alt="图片加载失败" style="float: right;">
        <!--样式清除-->
        <div style="clear: both"></div>
    </div>
    <!--收货地址div-->
    <div class="address">
        <div class="addressTip">输入收货地址</div>
        <div>
            <!--4行2列-->
            <table class="addressTable">
                <tbody>
                <tr>
                    <td class="firstColumn">详细地址<span class="redStar">*</span></td>
                    <td>
                        <textarea placeholder="建议您填写详细收货地址,例如街道名称,门牌号码,楼层和房间号等信息" name="address"></textarea>
                    </td>
                </tr>
                <tr>
                    <td>邮政编码</td>
                    <td><input type="text" name="post" placeholder="如果您不清楚邮递区号，请填写000000"></td>
                </tr>
                <tr>
                    <td>收货人姓名<span class="redStar">*</span></td>
                    <td><input type="text" name="receiver" placeholder="长度不超过25个字符"></td>
                </tr>
                <tr>
                    <td>手机号码<span class="redStar">*</span></td>
                    <td><input type="text" name="mobile" placeholder="请输入11位手机号码"></td>
                </tr>

                </tbody>

            </table>

        </div>

    </div>

    <!--购买商品列表-->
    <div class="productList">
        <div class="productListTitle">确认订单信息</div>
        <!--结算页面只有当前的一个订单所以只会有一个表-->
        <table class="productListTable">
            <thead>
            <tr>
                <th class="productListTableFirstColumn" colspan="2">
                    <img width="15px" src="img/site/orderItemTmall.png" alt="图片加载失败">
                    <a href="#" class="marketLink"><span>店铺：天猫店铺</span></a>
                    <a href="#"><span class="wangwangLink"></span></a>
                </th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
                <th>配送方式</th>
            </tr>
            <tr class="rowBoder">
                <td colspan="2"></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </thead>

            <tbody class="productListTableTBody">
            <tr class="orderItemTR">
                <td class="orderItemFirstTD" width="60px">
                    <img class="orderItemImg" src="img/site/productSingle_middle/3796.jpg" alt="图片加载失败" />
                </td>
                <td class="orderItemProductInfo">
                    <a class="orderItemProductLink" href="#">购买商品的信息后台的信息传递通过JSON</a>
                    <img src="img/site/7day.png" alt="图片加载失败" title="7天保障">
                    <img src="img/site/creditcard.png" alt="图片加载失败" title="支持信用卡">
                    <img src="img/site/promise.png" alt="图片加载失败" title="7天保障">
                </td>

                <td>
                    <span class="orderItemProductPromotePrice">￥3514.05</span>
                </td>
                <td><span class="orderItemProductNumber">2</span></td>
                <td><span class="orderItemProductSumPrice">￥7028.10</span></td>

                <!--最后一列是订单中所有商品的配送是统一的所以rowspan=5-->
                <td class="orderItemLastTD" rowspan="5">
                    <label class="orderItemDeliveryLabel">
                        <input type="radio" checked="checked" value="">
                        普通配送
                    </label>
                    <!--使用select-->
                    <select class="orderItemDeliverySelect">
                        <option>快递 免邮费</option>
                        <option>快递 不免邮费</option>

                    </select>
                </td>
            </tr>
            <tr class="orderItemTR">
                <td class="orderItemFirstTD">
                    <img class="orderItemImg" src="img/site/productSingle_middle/3796.jpg" alt="图片加载失败" />
                </td>
                <td class="orderItemProductInfo">
                    <a class="orderItemProductLink" href="#">购买商品的信息</a>
                    <img src="img/site/7day.png" alt="图片加载失败" title="7天保障">
                    <img src="img/site/creditcard.png" alt="图片加载失败" title="支持信用卡">
                    <img src="img/site/promise.png" alt="图片加载失败" title="7天保障">
                </td>
                <td>
                    <span class="orderItemProductPromotePrice">￥3514.05</span>
                </td>
                <td><span class="orderItemProductNumber">2</span></td>
                <td><span class="orderItemProductSumPrice">￥7028.10</span></td>
            </tr>
            </tbody>
        </table>

        <!--订单总计-->
        <div class="orderItemSumDiv">
            <div class="pull-left">
                <span class="leaveMessageText">给卖家留言:</span>
                <span>
                        <img src="img/site/leaveMessage.png" alt="图片加载失败" class="leaveMessageImg">
                    </span>
                <span class="leaveMessageTextareaSpan" style="display: none">
                        <textarea class="leaveMessageTextarea" name="userMessage"></textarea>
                        <div style="margin-left:70px;">
                            <span>还可以输入200个字符</span>
                        </div>
                    </span>
            </div>
            <span class="pull-right">
                    店铺合计(含运费):<span clsss="orderItemSumPrice">￥9152.25</span>
                </span>
        </div>

    </div>

    <div class="orderItemTotalSumDiv">
        <div class="pull-right">
            <span>实付款:</span>
            <span class="orderItemTotalSumSpan">￥9152.25</span>
        </div>
    </div>

    <!--提交订单-->
    <div class="submitOrderDiv">
        <button class="submitOrderButton" type="button">提交订单</button>

    </div>
</div>





<%@include file="../include/footer.jsp"%>
