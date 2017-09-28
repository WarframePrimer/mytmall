<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/24
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../include/header.jsp"%>
<%@include file="../include/top.jsp"%>

    <div class="aliPayPageDiv">
        <div class="payLogo">
            <img src="<%=request.getContextPath()%>/img/site/simpleLogo.png" alt="图片加载失败" class="pull-left">
            <div style="clear:both;"></div>
        </div>

        <div class="aliPayPageDivTextCenter">
            <span class="confirmMoneyText">扫一扫付款(元)</span>
            <span class="confirmMoney">
                ￥<fmt:formatNumber minFractionDigits="2" value="${order.totalPrice}"/>
            </span>
        </div>
        <!--二维码-->
        <div class="aliPayPageDivTextCenter">
            <img class="aliPay" src="<%=request.getContextPath()%>/img/site/alipay2wei.png" alt="图片加载失败">
        </div>
        <div class="aliPayPageDivTextCenter">
            <!--点击确认支付以后将order中的orderStatus设为'waitDelivery'(待发货)-->
            <a href="payComplete.do?oid=${order.id}"><button class="confirmPay">确认支付</button></a>
        </div>


    </div>

    <script language="javascript">
        $(function () {
            //防止页面后退,避免出现误操作
            //暂时没有找到点击上一步失效并且是刷新当前页面的js操作
            history.pushState(null, null, document.URL);
            window.addEventListener('popstate', function () {
                history.pushState(null, null, document.URL);
            });

        });

    </script>




<%@include file="../include/footer.jsp"%>