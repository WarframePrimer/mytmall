<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/6/8
  Time: 8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/adminHeader.jsp" %>
<%@include file="../include/adminNav.jsp" %>


<div class="listDiv">
    <!--面包树结构的标题导航-->
    <h1 class="label label-info">订单管理</h1>

    <table class="table tale-hover table-bordered table-striped table-condensed">
        <!--表头信息-->
        <thead>
        <tr class="listTableTH">
            <th>ID</th>
            <th>状态</th>
            <th>金额</th>
            <th>商品数量</th>
            <th>买家名称</th>
            <th>创建时间</th>
            <th>支付时间</th>
            <th>发货时间</th>
            <th>确认收货时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <!--具体信息-->
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr class="orderListTableContentTR" style="font-size: 14px">
                <td>${order.id}</td>
                <td>${order.statusDesc}</td>
                <td>${order.totalPrice}</td>
                <td>${order.totalNumber}</td>
                <td>${order.user.name}</td>
                <td><fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${order.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${order.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${order.confirmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                <td>
                    <c:if test="${'waitPay'==order.status}">
                        <button class="btn btn-primary btn-xs showDetails" oid="${order.id}">查看详情</button>
                        <button class="btn disabled btn-primary btn-xs">待买家付款</button>
                    </c:if>
                    <c:if test="${'waitDelivery'==order.status}">
                        <button class="btn btn-primary btn-xs showDetails" oid="${order.id}">查看详情</button>
                        <a href="#">
                            <button class="btn btn-primary btn-xs">发货</button>
                        </a>
                    </c:if>
                    <c:if test="${'waitConfirm'==order.status}">
                        <button class="btn btn-primary btn-xs showDetails" oid="${order.id}">查看详情</button>
                        <button class="btn btn-primary disabled btn-xs">待买家确认收货</button>
                    </c:if>
                    <c:if test="${'waitReview'==order.status}">
                        <button class="btn btn-primary btn-xs showDetails" oid="${order.id}">查看详情</button>
                        <button class="btn btn-primary disabled btn-xs">待买家评价</button>
                    </c:if>
                    <c:if test="${'finish'==order.status}">
                        <button class="btn btn-primary btn-xs showDetails" oid="${order.id}">查看详情</button>
                        <button class="btn btn-primary disabled btn-xs">订单完成</button>
                    </c:if>

                </td>

            </tr>
            <%--默认隐藏--%>
            <%--在点击查看详请是，从隐藏状态toggle出来--%>
            <tr class="orderItemDetailTR" oid="${order.id}" style="display: none">
                <%--这里从来span为10是因为要跟上面的列td一样--%>
                <td colspan="10" align="center">
                    <div class="orderItemDetail">
                        <table width="800px" align="center" class="orderItemsTable">
                            <c:forEach items="${order.orderItems}" var="orderItem">
                                <tr>
                                    <td align="left">
                                        <img width="40px" height="40px" src="<%=request.getContextPath()%>/img/productImage/${orderItem.product.firstProductImage.id}.jpg"
                                             alt="图片加载失败">
                                    </td>

                                    <td>
                                        <a href="<%=request.getContextPath()%>/getProductDetail.do?pid=${orderItem.product.id}&cid=${orderItem.product.category.id}" target="_blank">
                                            <span>${orderItem.product.subTitle}</span>
                                        </a>
                                    </td>
                                    <td align="right">
                                        <span class="text-muted">${orderItem.number}个</span>
                                    </td>
                                    <td align="right">

                                        <span class="text-muted">单价：￥${orderItem.product.promotePrice}</span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
            </tr>

        </c:forEach>

        </tbody>
    </table>


    <%@ include file="../include/adminPage.jsp" %>
</div>

<script>
    $(function () {
        $("button.showDetails").click(function () {
            var oid = $(this).attr("oid");
            $("tr.orderItemDetailTR[oid=" + oid + "]").toggle();
        })
    })
</script>


<%@include file="../include/adminFooter.jsp" %>
