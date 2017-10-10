<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/7
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<!--竖状分类菜单 categoryMenu.jsp-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!--分类列表上方的菜单栏-->
<div class="headbar">
    <div class="head">
        <span class="glyphicon glyphicon-th-list"></span>
        <span>商品分类</span>
    </div>


    <img src="img/site/catear.png" alt="图片加载失败" style="position:absolute;display: none;height: 15px; "
         class="carear" id="catear">

    <div class="rightMenu">
        <span><a href="#"><img src="img/site/chaoshi.png" alt="图片加载失败"></a></span>
        <span><a href="#"><img src="img/site/guoji.png" alt="图片加载失败"></a></span>
        <span><a href="#">平板电视</a></span>
        <span><a href="#">电热水器</a></span>
    </div>
</div>
<!--分类列表展示-->
<div style="position: relative">
    <%--问题：在将光标从分类上移开时，相关分类的属性值也会随之隐藏--%>
    <%----%>
    <div class="categoryMenu">
        <c:forEach items="${categoryList}" var="category">
            <div class="eachCategory" cid="${category.id}">
                <span class="glyphicon glyphicon-link"></span>
                <a href="<%=request.getContextPath()%>/searchByCategory.do?categoryId=${category.id}">${category.name}</a>
            </div>
        </c:forEach>
    </div>
</div>