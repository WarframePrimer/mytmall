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

    <!--提交表单要进行的表单信息填写-->
    <form id="orderCommitForm" action="payOrder.do" method="post">
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
                            <textarea id="address" placeholder="建议您填写详细收货地址,例如街道名称,门牌号码,楼层和房间号等信息" name="address"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>邮政编码</td>
                        <td><input id="post" type="text" name="post" placeholder="如果您不清楚邮递区号，请填写000000"></td>
                    </tr>
                    <tr>
                        <td>收货人姓名<span class="redStar">*</span></td>
                        <td><input id="receiver" type="text" name="receiver" placeholder="长度不超过25个字符"></td>
                    </tr>
                    <tr>
                        <td>手机号码<span class="redStar">*</span></td>
                        <td><input id="mobile" type="text" name="mobile" placeholder="请输入11位手机号码"></td>
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
                        <a href="#nowhere" class="marketLink"><span>店铺：天猫店铺</span></a>
                        <a href="#nowhere"><span class="wangwangLink"></span></a>
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
                <c:forEach items="${orderItemList}" var="orderItem" varStatus="st">
                    <tr class="orderItemTR">
                        <td class="orderItemFirstTD" width="60px">
                            <a href="getProductDetail.do?pid=${orderItem['product']['id']}&cid=${orderItem['product']['category']['id']}"><img class="orderItemImg" src="<%=request.getContextPath()%>/img/productImage/${orderItem['product']['firstProductImage']['id']}.jpg" alt="图片加载失败" /></a>
                        </td>
                        <td class="orderItemProductInfo">
                            <a class="orderItemProductLink" href="getProductDetail.do?pid=${orderItem['product']['id']}&cid=${orderItem['product']['category']['id']}">${orderItem['product']['name']}</a>
                            <img src="img/site/7day.png" alt="图片加载失败" title="7天保障">
                            <img src="img/site/creditcard.png" alt="图片加载失败" title="支持信用卡">
                            <img src="img/site/promise.png" alt="图片加载失败" title="7天保障">
                        </td>

                        <td>
                            <span class="orderItemProductPromotePrice"><fmt:formatNumber minFractionDigits="2" value="${orderItem['product']['promotePrice']}"/></span>
                        </td>
                        <td><span class="orderItemProductNumber">${orderItem.number}</span></td>
                        <td><span class="orderItemProductSumPrice">
                            <fmt:formatNumber value="${orderItem.number*orderItem.product.promotePrice}" minFractionDigits="2"/>
                        </span></td>

                        <!--最后一列是订单中所有商品的配送是统一的所以rowspan=5-->
                        <c:if test="${st.count==1}">
                            <td rowspan="5"  class="orderItemLastTD">
                                <label class="orderItemDeliveryLabel">
                                    <input type="radio" value="" checked="checked">
                                    普通配送
                                </label>

                                <select class="orderItemDeliverySelect" class="form-control">
                                    <option>快递 免邮费</option>
                                </select>

                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
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
                    店铺合计(含运费):<span clsss="orderItemSumPrice">￥<fmt:formatNumber value="${totalPrice}" minFractionDigits="2"/></span>
                </span>
            </div>

        </div>

        <div class="orderItemTotalSumDiv">
            <div class="pull-right">
                <span>实付款:</span>
                <span class="orderItemTotalSumSpan">
                ￥<fmt:formatNumber value="${totalPrice}" minFractionDigits="2"/>
            </span>
            </div>
        </div>

        <!--提交订单-->
        <div class="submitOrderDiv">
            <a href="https://www.github.com/warframeprimer" class="backToCartLink">返回购物车</a>
            <a href="#nowhere" class="submitOrderLink"><button class="submitOrderButton" type="button">提交订单</button></a>
        </div>
    </form>

</div>

<script>
    $(function () {
        //TODO
        $("img.leaveMessageImg").click(function () {
            $(this).css("display","none");
            $("span.leaveMessageTextareaSpan").css("display","inline-block");
        })


        //点击提交订单

        $("a.submitOrderLink").click(function () {
            //点击提交订单是进行表单验证
            var address = $("#address").val();
            var post = $("#post").val();
            var receiver = $("#receiver").val();
            var mobile = $("#mobile").val();
            //alert(address+post+receiver+mobile);
            /*表单验证错误先简单写写，这不是重点*/
            if(0==address.length||0==post.length||0==receiver.length||0==mobile.length){
                alert("所填信息都不能为空！！！");
            }else{
                $("#orderCommitForm").submit();
            }


        });
    });
</script>




<%@include file="../include/footer.jsp"%>
