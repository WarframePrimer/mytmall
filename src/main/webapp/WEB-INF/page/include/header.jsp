<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/6
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!--引入JSTL标签库-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>tmallDemo</title>
    <!--添加网站icon-->
    <link rel="icon" href="<%=request.getContextPath()%>/img/site/favicon.png" type="image/x-icon"/>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/site/favicon.png" type="image/x-icon" />
    <!--BootStrap3-->
    <script src="<%=request.getContextPath()%>/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="<%=request.getContextPath()%>/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <link href="<%=request.getContextPath()%>/css/front/tmallDemo.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/front/productPage.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/front/register.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/front/login.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/front/buyPage.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/front/cartItem.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/front/payPage.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/front/myOrder.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/front/payComplete.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/front/reviewProduct.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/front/searchByKeyword.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/front/searchByCategoryResult.css" rel="stylesheet"/>


    <!--添加一些必要的js交互-->

</head>

<body>
