<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/25
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@include file="../include/header.jsp" %>
<%@include file="../include/top.jsp" %>


<!--这里面使用mybatis中的分页-->
<!--包含整个订单-->
<div class="orderDiv">
    <!--订单类型-->
    <div class="orderType">
        <div class="orderTypeSelected"><a href="#" orderStatus="all">所有订单</a></div>
        <div class="" href="#nowhere"><a href="#" orderStatus="waitPay">待付款</a></div>
        <div class="" href="#nowhere"><a href="#" orderStatus="waitDelivery">待发货</a></div>
        <div class="" href="#nowhere"><a href="#" orderStatus="waitConfirm">待收货</a></div>
        <div class="" href="#nowhere"><a class="noRightBorder" href="#" orderStatus="waitReview">待评价</a></div>
        <div class="orderTypeLastOne"></div>
    </div>
    <!--订单详情的表头包含宝贝，数量，单价，实付款，交易操作-->
    <div class="orderTitle">
        <!--订单表头的信息-->
        <table class="orderTitleTable">
            <tbody>
            <tr>
                <td>宝贝</td>
                <td width="100px">单价</td>
                <td width="100px">数量</td>
                <td width="120px">实付款</td>
                <td width="100px">交易操作</td>
            </tr>
            </tbody>
        </table>
    </div>
    <!--订单详细信息列表-->
    <div class="orderList" id="orderList">
        <c:choose>
            <c:when test="${orders==null}">
                <div class="orderIsNull" id="orderIsNull">
                    <img src="<%=request.getContextPath()%>/img/site/orderIsNull.png" alt="图片加载失败">
                    <span style="font-size: 14px;">没有符合条件的宝贝，请尝试其他搜索条件。</span>
                </div>

            </c:when>
            <c:otherwise>

                <c:forEach items="${orders}" var="order">
                    <!--这里一个订单就是一个表-->
                    <table class="orderListItemTable" oid="${order.id}" orderStatus="${order.status}">
                        <!--第一行显示订单编号以及订单创建时间-->
                        <tr class="orderListItemFirstTR">
                            <td colspan="2">
                                <b><fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></b>
                                <span>订单号:${order.orderCode}</span>
                            </td>
                            <td colspan="2">
                                    <%--TODO--%>
                                <img width="13px" src="<%=request.getContextPath()%>/img/site/tmallbuy.png"
                                     alt="图片加载失败">天猫商场
                            </td>
                            <td colspan="1">
                                <a href="#" class="wangwangLink">
                                    <div class="orderItemWangWangGif"></div>
                                </a>
                            </td>
                            <td class="orderItemDeleteTD">
                                <a class="deleteOrderLink" oid="${order.id}" href="#">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </a>
                            </td>
                        </tr>
                        <!--第二行表示具体的订单项内容-->
                        <c:forEach items="${order.orderItems}" var="orderItem">
                            <tr class="orderListItemDetailTR">
                                <td class="orderListItemDetailTD" width="120px">
                                    <a href="getProductDetail.do?pid=${orderItem.product.id}&cid=${orderItem.product.category.id}"><img
                                            width="80px" height="80px"
                                            src="<%=request.getContextPath()%>/img/productImage/${orderItem.product.firstProductImage.id}.jpg"
                                            alt="图片加载失败"></a>
                                </td>
                                <td class="orderListItemDetailTD">
                                    <div class="orderListItemDetailLinkOutDiv">
                                        <a href="getProductDetail.do?pid=${orderItem.product.id}&cid=${orderItem.product.category.id}">${orderItem.product.name}</a>
                                        <div class="orderListItemDetailLinkInnerDiv">
                                            <img src="img/site/7day.png" alt="图片加载失败" title="7天啥啥啥">
                                            <img src="img/site/creditcard.png" alt="图片加载失败" title="相信我">
                                            <img src="img/site/promise.png" alt="图片加载失败" title="我保证">
                                        </div>
                                    </div>
                                </td>
                                <td width="100px" valign="top"
                                    class="orderListItemDetailOriginalAndPromotePrice orderListItemDetailTD">
                                    <div class="orderListItemDetailOriginalPrice">
                                <span style="text-decoration: line-through">
                                ￥<fmt:formatNumber minFractionDigits="2" value="${orderItem.product.originalPrice}"/>
                                </span>
                                    </div>
                                    <div class="orderListItemDetailPromotePrice">￥<fmt:formatNumber
                                            minFractionDigits="2"
                                            value="${orderItem.product.promotePrice}"/></div>
                                </td>
                                <td valign="top" width="100px" class="orderListItemDetailNumberTD orderListItemDetailTD"
                                    rowspan="1">
                                    <span class="orderListItemDetailNumber">${orderItem.number}</span>
                                </td>
                                <td width="120px" valign="top" class="orderListItemDetailPriceTD orderListItemDetailTD">
                                    <div class="orderListItemDetailRealPrice">￥<fmt:formatNumber minFractionDigits="2"
                                                                                                 value="${orderItem.product.promotePrice*orderItem.number}"/></div>
                                    <div class="orderListItemDetailPriceWithTransport">(含运费:￥0.00)</div>
                                </td>
                                <td width="100px" valign="top"
                                    class="orderListItemDetailReviewTD orderListItemDetailTD">
                                    <!--对于订单的不同交易状态，提供不同的提示按钮-->
                                    <c:choose>
                                        <c:when test="${order.status == 'waitPay'}">
                                            <a href="payOrderConfirm.do?oid=${order.id}">
                                                <button class="btn btn-primary btn-xs orderListItemConfirmBtn">付款
                                                </button>
                                            </a>
                                        </c:when>
                                        <c:when test="${order.status == 'waitConfirm'}">
                                            <a href="#">
                                                <button class="btn btn-primary btn-xs orderListItemConfirmBtn"
                                                        oid="${order.id}">确认收货
                                                </button>
                                            </a>
                                        </c:when>
                                        <c:when test="${order.status == 'waitDelivery'}">
                                            <span>待发货</span>
                                            <button class="btn btn-primary btn-xs callToDelivery" oid="${order.id}">
                                                催卖家发货
                                            </button>
                                        </c:when>
                                        <c:when test="${order.status == 'waitReview'}">
                                            <a href="reviewProduct.do?pid=${orderItem.product.id}&oid=${order.id}">
                                                <button class="orderListItemReviewBtn">评价</button>
                                            </a>
                                        </c:when>
                                        <c:when test="${'finish'==order.status}">
                                            <span>订单已完成</span>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>

                        </c:forEach>

                    </table>
                </c:forEach>

            </c:otherwise>

        </c:choose>


    </div>
</div>

<script>
    //load
    $(function () {
        $("a[orderStatus]").click(function () {
            var orderStatus = $(this).attr("orderStatus");

            var orderIsNull = document.getElementById("orderIsNull");
            if (orderIsNull != null) {
                //每次点击之前都将之前的空页面清除
                document.getElementById("orderList").removeChild(orderIsNull);
            }

            //$("div.orderList").remove($("div.orderIsNull"));

            if ("all" == orderStatus) {
                if(${orders==null}){
                    createOrderIsNullDiv();
                }else{
                    $("table.orderListItemTable").show();
                }
            } else {
                $("table.orderListItemTable").hide();
                var orderItemAccordWith = $("table.orderListItemTable[orderStatus=" + orderStatus + "]");
                if (orderItemAccordWith.length > 0) {
                    orderItemAccordWith.show();
                } else {
                    createOrderIsNullDiv();
                }

            }

            /*初始化被选中的orderType*/
            $("div.orderType div.orderTypeSelected").removeClass("orderTypeSelected");
            $(this).parent("div").addClass("orderTypeSelected");

        })
        //创建空点单是的div内容(使用js动态创建)
        function createOrderIsNullDiv() {
            //用原生js创建HTML标签非常麻烦，尝试使用jQuery^_^
//                    $("div.orderIsNull").show();
            var orderList = document.getElementById("orderList");
//                    alert(orderList);
            var orderIsNull = document.createElement("div");
            orderIsNull.setAttribute("class", "orderIsNull");
            orderIsNull.setAttribute("id", "orderIsNull");
            var img = document.createElement("img");
            img.setAttribute("src", "img/site/orderIsNull.png");
            var span = document.createElement("span");
            span.style.fontSize = "14px";
//                    span.css("font-size","14px");
            span.innerHTML = "没有符合条件的宝贝，请尝试其他搜索条件。";
//                    span.html("没有符合条件的宝贝，请尝试其他搜索条件。");
            orderIsNull.appendChild(img);
            orderIsNull.appendChild(span);
            orderList.appendChild(orderIsNull);
        }

        //点击催卖家发货所触发的事件
        $("button.callToDelivery").click(function () {
//            alert("click");
            var page = "callToDelivery.do";
            var oid = $(this).attr("oid");
            //设置订单状态为待收货
            var status = "waitConfirm";
//            alert(oid);
            updateOrder(page, oid, status);
        })


        //点击确认收货触发的事件
        $("button.orderListItemConfirmBtn").click(function () {
            var page = "confirmReceipt.do";
            var oid = $(this).attr("oid");
            //设置订单状态为待评价
            var status = "waitReview";
            updateOrder(page, oid, status);
        })

//        //点击评价触发的事件
//        $("button.orderListItemReviewBtn").click(function () {
//
//        })


        //更新订单状态
        function updateOrder(page, oid, status) {
            $.post(
                page,
                {"oid": oid, "status": status},
                function (result) {
                    if ("delivered" == result["msg"]) {
                        alert("卖家已秒发，刷新一下吧^_^");
                    }
                    if ("confirmed" == result['msg']) {
                        alert("已确认收货，刷新一下吧^_^");
                    }
                }
            )
        }

    })
</script>


<%@include file="../include/footer.jsp" %>
