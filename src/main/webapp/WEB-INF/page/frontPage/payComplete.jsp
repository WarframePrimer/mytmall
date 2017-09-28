<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/28
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/header.jsp"%>
<%@include file="../include/top.jsp"%>

<div class="payedDiv">
    <!--支付成功后的提示-->
    <div class="payMessage">
        <img src="img/site/paySuccess.png" alt="图片记载失败">
        <span class="payMessageText">您已成功付款</span>
    </div>
    <!--显示详细的支付信息-->
    <div class="payedDetail" >
        <ul class="payedDetailList">
            <li>收货地址： ${order.address}</li>
            <li>实付款： <span class="payMoney">￥<fmt:formatNumber minFractionDigits="2" value="${order.totalPrice}"/></span></li>
            <li>预计08月08日送达</li>
        </ul>
        <span class="forMoreDetail">您可以 <a href="showOrder.do">查看已买到的宝贝</a> <a href="showOrder.do">查看交易详情</a></span>

    </div>

    <!--单纯显示一条下换线-->
    <div class="borderLine"></div>

    <!--安全提醒-->
    <div class="securityRemind">
        <img src="img/site/warning.png" alt="图片加载失败">
        <span><b>安全提醒:</b>下单后，<span class="redFont">用QQ给您发送链接办理退款的都是骗子！</span>天猫不存在系统升级，订单异常等问题，谨防假冒客服电话诈骗！</span>
    </div>


</div>




<%@include file="../include/footer.jsp"%>






