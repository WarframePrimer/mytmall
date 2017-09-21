<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/7
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>

<!--首页所要展示的分类的产品列表-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--分类商品信息列表-->
<!--首页的分类商品列表信息-->

<c:forEach items="${categoryList}" var="category">
    <div class="homepageCategoryProduct">
        <div class="eachHomepageCategoryProducts">
            <div class="left-mark"></div>
            <span class="categoryTitle">${category.name}</span>
            <br>
            <!--相应分类下的每一个产品信息-->
            <!--这里显示的产品只需要其中的5个就行了-->
            <c:forEach items="${category.products}" var="product">
                <div class="productItem">
                    <!--跳转到商品详情页面-->
                    <a href="getProductDetail.do?pid=${product.id}&cid=${category.id}" target="_blank">
                        <img width="100px" src="<%=request.getContextPath()%>/img/productImage/${product.firstProductImage.id}.jpg" alt="图片加载失败">
                    </a>
                    <a href="getProductDetail.do?pid=${product.id}&cid=${category.id}" class="productItemDescLink"><span
                            class="productItemDesc">${product.name}</span>
                    </a>
                    <span class="productPrice">￥${product.originalPrice}</span>
                </div>

            </c:forEach>
            <div style="clear:both"></div>
        </div>
    </div>
</c:forEach>



