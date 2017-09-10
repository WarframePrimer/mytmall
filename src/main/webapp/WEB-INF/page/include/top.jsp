<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/7
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--导航栏-->
<nav class="header">

    <div class="header-content">
        <a href="<%=request.getContextPath()%>/home"><span class="glyphicon glyphicon-home" style="color: #c40000"></span>天猫首页</a>
        <span>喵，欢迎来到天猫</span>
        <a href="#">请登录</a>
        <a href="#">免费注册</a>

        <span class="pull-right">
                    <a href="#">我的订单</a>
                    <span class="glyphicon glyphicon-shopping-cart" style="color: #c40000"></span>
                    购物车<strong style="color: #000">0</strong>件

            </span>

    </div>
    <button class="btn btn-success btn-xs linkBack" style="position: absolute;top:3px; "
            onclick="javascrtpt:window.location.href='http://localhost:8080/tmall/admin/category/admin_category_list.do'">
        进入后台
    </button>
</nav>

