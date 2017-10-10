<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/10/9
  Time: 16:04
  To change this template use File | Settings | File Templates.

  通过分类进行的搜索结果页面，可以对搜索结果进行按价格排序、按销量排序等排序
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/header.jsp" %>
<%@include file="../include/top.jsp" %>
<%@include file="../include/index/search.jsp"%>

<%--搜索结果--%>
<div class="categoryPageDiv">
    <img class="categoryPageImage" src="<%=request.getContextPath()%>/img/category/${category.id}.jpg" alt="图片加载失败">
    <!--对商品进行排序分类-->
    <div class="categorySortBar">
        <!--放排序按钮的table-->
        <table class="categorySortBarTable categorySortTable">
            <tbody>
            <tr>
                <%--如果td的class为grayColumn表示被选中的条件标签--%>
                <td <c:if test="${'all'==sortType||empty sortType}">class="grayColumn"</c:if>><a href="searchByCategory.do?categoryId=${category.id}&sort=all">综合<span class="glyphicon glyphicon-arrow-down"></span></a></td>
                <td <c:if test="${'review'==sortType}">class="grayColumn"</c:if>><a href="searchByCategory.do?categoryId=${category.id}&sort=review">人气<span class="glyphicon glyphicon-arrow-down"></span></a></td>
                <td <c:if test="${'date'==sortType}">class="grayColumn"</c:if>><a href="searchByCategory.do?categoryId=${category.id}&sort=date">新品<span class="glyphicon glyphicon-arrow-down"></span></a></td>
                <td <c:if test="${'saleCount'==sortType}">class="grayColumn"</c:if>><a href="searchByCategory.do?categoryId=${category.id}&sort=saleCount">销量<span class="glyphicon glyphicon-arrow-down"></span></a></td>
                <td <c:if test="${'price'==sortType}">class="grayColumn"</c:if>><a href="searchByCategory.do?categoryId=${category.id}&sort=price">价格<span class="glyphicon glyphicon-resize-vertical"></span></a></td>
            </tr>
            </tbody>
        </table>
        <!--放价格区间的table-->
        <table class="categorySortBarTable">
            <tbody>
            <tr>
                <td><input class="sortBarPrice beginPrice" type="text" placeholder="请输入"/></td>
                <td class="grayColumn priceMiddleColumn">-</td>
                <td><input class="sortBarPrice endPrice" type="text" placeholder="请输入"/></td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="categoryProducts">
        <c:forEach items="${productListByCategoryId}" var="product">
            <div class="productUnit" price="${product.promotePrice}">
                <div class="productUnitFrame">
                    <a href="getProductDetail.do?cid=${product.category.id}&pid=${product.id}"><img height="190px" width="100%" src="<%=request.getContextPath()%>/img/productImage/${product.firstProductImage.id}.jpg" alt="图片加载失败"></a>
                    <span class="productPrice">￥<fmt:formatNumber value="${product.promotePrice}" maxFractionDigits="2"/></span>
                    <a href="getProductDetail.do?cid=${product.category.id}&pid=${product.id}" class="productLink">${product.subTitle}</a>
                    <a href="#nowhere" class="tmallLink">天猫专卖</a>
                    <div class="show1 productInfo">
                        <span class="monthDeal">月成交<span class="productDealNumber">${product.saleCount}笔</span></span>
                        <span class="productReview">评价<span class="productReviewNumber">${product.reviewCount}</span></span>
                        <span class="wangwang">
                            <a href="#nowhere" class="wangwangLink"><img src="img/site/wangwang.png" alt="图片加载失败"></a>
                        </span>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
    <%--清除浮动？？！！emmmmm--%>
    <div style="clear: both"></div>

</div>



<script>
    $(function () {
        $("input.sortBarPrice").keyup(function () {
            var num = $(this).val();
            if(num.length==0){
                $("div.productUnit").show();
                return;
            }

            num = parseInt(num);
            if(isNaN(num)) num=1;
            if(num<1) num=1;
            $(this).val(num);

            var begin = $("input.beginPrice").val();

            var end = $("input.endPrice").val();

            if(!isNaN(begin) && !isNaN(end)){
                $("div.productUnit").hide();

                $("div.productUnit").each(function () {
                    var price = $(this).attr("price");

                    price = new Number(price);

                    if(price<=end && price>=begin){
                        $(this).show();
                    }
                });
            }

        });
    })

</script>





<%@include file="../include/footer.jsp" %>
