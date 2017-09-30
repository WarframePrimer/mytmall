<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/30
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<%@include file="../include/header.jsp" %>
<%@include file="../include/top.jsp" %>

<!--评价页面-->
<div class="reviewDiv">
    <!--评价商品的详细信息-->
    <div class="reviewProductInfoDiv">
        <div class="reviewProductImage">
            <img width="460px" height="460px" src="<%=request.getContextPath()%>/img/productImage/${product.firstProductImage.id}.jpg" alt="图片加载失败">
        </div>
        <div class="reviewProductRightDiv">
            <!--商品的详细信息表-->
            <table class="reviewProductInfoTable" width="526px">
                <tr>
                    <td colspan="2"><h4>${product.subTitle}</h4></td>
                </tr>
                <tr>
                    <td class="reviewProductTD" width="75px">价格</td>
                    <td><strong class="reviewProductPromotePrice">
                        <fmt:formatNumber maxFractionDigits="2" value="${product.promotePrice}"/></strong><span>元</span></td>
                </tr>
                <tr>
                    <td class="reviewProductTD">配送</td>
                    <td>快递：<span class="productTransportPrice">0.00</span></td>
                </tr>
                <tr>
                    <td class="reviewProductTD">月销量</td>
                    <td><strong class="reviewProductMonthSaleNumber">${product.saleCount}</strong> 件</td>
                </tr>
            </table>
            <div class="reviewProductInfoRightBelowDiv">
                <span class="reviewProductInfoRightBelowImage"><img src="img/site/reviewLight.png"/></span>
                <span class="reviewProductInfoRightBelowText">现在查看的是 您所购买商品的信息
    于<fmt:formatDate value="${order.payDate}" pattern="yyyy年MM月dd日"/> 下单购买了此商品 </span>
            </div>
        </div>
        <div style="clear:both;"></div>
    </div>
    <!--评价-->
    <div class="reviewProductDiv">
        <!--显示累计评价-->
        <!--评价的标题设置-->
        <div class="reviewProductTitle">
            <div class="reviewProductTitleLeft">
                <div class="reviewProductTitleLeftTop"></div>
                <div class="reviewProductTitleContent">累计评价<span class="reviewProductCommentNumber">${product.reviewCount}</span></div>
                <div class="reviewProductTitleLeftFoot"></div>
            </div>
            <div class="reviewProductTitleRight">
                <div class="reviewProductTitleRightTopEmpty"></div>
                <div class="reviewProductTitleRightFoot"></div>
            </div>
        </div>
        <!--评价的具体Content-->
        <div class="reviewProductContentDiv">
            <!--表单评价内容的提交-->
            <form method="post" action="commitReview.do">
                <input type="hidden" name="pid" value="${product.id}">
                <input type="hidden" name="uid" value="${user.id}">
                <input type="hidden" name="oid" value="${order.id}">
                <div class="reviewProductContentText">其他买家，需要你的建议哦！</div>

                <!--一行两列的表格：评价商品----评价内容-->
                <table class="reviewProductContentTable">
                    <tbody>
                    <tr>
                        <td width="69px">评价商品</td>
                        <td><textarea class="reviewProductTextArea" name="content"></textarea></td>
                    </tr>
                    </tbody>
                </table>
                <!--进行提交-->
                <div class="submitButtonDiv">
                    <button type="submit" class="reviewProductSubmitButton">提交评价</button>
                </div>

            </form>
        </div>

    </div>
</div>



<%@include file="../include/footer.jsp" %>