<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/7
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<!--首页索要展示的一些信息：
    分类和轮播图片；categoryAndCarousel.jsp
        竖状分类菜单 categoryMenu.jsp
        竖状分类菜单右侧的推荐产品列表 productAsideCategory.jsp
        轮播 carousel.jsp
    首页所要展示的分类的产品列表 homepageCategoryProducts.jsp
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="categoryWithCarousel">
<%@include file="categoryAndCarousel.jsp"%>
</div>

<%@include file="homepageCategoryProducts.jsp"%>

