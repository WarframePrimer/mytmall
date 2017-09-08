<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/6/8
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/adminHeader.jsp"%>
<%@include file="../include/adminNav.jsp"%>


<div class="listDiv">
    <!--面包树结构的标题导航-->
    <h1 class="label label-info">用户管理</h1>
    <!--bootstrap中的表格样式：悬停，斑马线，边框，紧凑-->
    <table class="table table-hover table-striped table-condensed table-bordered">
        <!--表头信息-->
        <thead>
        <tr class="listTableTH">
            <th width="43.5%" >ID</th>
            <th>用户名称</th>
        </tr>
        </thead>
        <!--具体信息-->
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr class="userListTableContentTR">
                <td>${user.id}</td>
                <td>${user.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <%@ include file="../include/adminPage.jsp"%>
</div>

<%@include file="../include/adminFooter.jsp"%>
