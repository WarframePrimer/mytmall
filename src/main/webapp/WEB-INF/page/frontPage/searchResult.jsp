<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/10/4
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>
<%@include file="../include/top.jsp" %>
<%@include file="../include/index/search.jsp"%>
<%--查询结果--%>
<div class="searchByKeywordResultDiv">
    <c:choose>
        <c:when test="${productList == null}">
            <div class="searchByKeywordResultIsNull">
                <div>没有满足条件的产品</div>
            </div>
        </c:when>
        <c:otherwise>
            <!--罗列具体的产品信息-->
            <div class="searchByKeywordProductListDiv">
                <c:forEach items="${productList}" var="product">
                    <div class="productUnitDiv">
                        <a href="getProductDetail.do?pid=${product.id}&cid=${product.category.id}">
                            <img height="190px" width="100%" src="<%=request.getContextPath()%>/img/productImage/${product.firstProductImage.id}.jpg" alt="图片加载失败">
                        </a>
                        <span class="promotePrice">￥${product.promotePrice}</span>
                        <a class="productLink" href="getProductDetail.do?pid=${product.id}&cid=${product.category.id}">${product.subTitle}</a>
                        <%--用于存放销量和评价量--%>
                        <div class="show1 productInfo">
                            <span class="monthDeal">月成交 <span class="monthDealNumber">${product.saleCount}笔</span></span>
                            <span class="productReview">评价 <span>${product.reviewCount}</span></span>
                        </div>
                    </div>
                </c:forEach>
                <div style="clear: both"></div>
            </div>
        </c:otherwise>
    </c:choose>
    
    
</div>








<%@include file="../include/footer.jsp" %>
