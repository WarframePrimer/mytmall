<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/6/7
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!--管理页面的导航栏-->


<div class="adminNav">
    <nav class="navbar navbar-default navbar-static-top navbar-inverse">
        <a href="<%=request.getContextPath()%>/home.do"><img style="margin-left: 10px;width:40px;" src="<%=request.getContextPath()%>/img/site/tmallbuy.png" class="pull-left" alt="图片加载失败"></a>
        <a href="<%=request.getContextPath()%>/admin/category/admin_category_list.do" class="navbar-brand">天猫后台</a>
        <a href="<%=request.getContextPath()%>/admin/category/admin_category_list.do" class="navbar-brand">分类管理</a>
        <a href="<%=request.getContextPath()%>/admin/user/admin_user_list.do" class="navbar-brand">用户管理</a>
        <a href="<%=request.getContextPath()%>/admin/order/admin_order_list.do" class="navbar-brand">订单管理</a>
    </nav>
</div>