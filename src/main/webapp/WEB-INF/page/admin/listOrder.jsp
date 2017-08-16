<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/6/8
  Time: 8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/adminHeader.jsp"%>
<%@include file="../include/adminNav.jsp"%>


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
        <tr class="orderListTableContentTR">
            <td>1</td>
            <td>待发货</td>
            <td>￥152.90</td>
            <td>2</td>
            <td>姚嘉斌</td>
            <td>2017-06-09 14:44:00</td>
            <td>2017-06-09 14:44:05</td>
            <td></td>
            <td></td>
            <td><button class="btn btn-primary btn-xs">查看详情</button></td>
        </tr>
        <tr class="orderListTableContentTR">
            <td>1</td>
            <td>待发货</td>
            <td>￥152.90</td>
            <td>2</td>
            <td>姚嘉斌</td>
            <td>2017-06-09 14:44:00</td>
            <td>2017-06-09 14:44:05</td>
            <td></td>
            <td></td>
            <!--添加onclick事件-->
            <td><button class="btn btn-primary btn-xs" type="submit">查看详情</button></td>
        </tr>

        </tbody>
    </table>


    <%@ include file="../include/adminPage.jsp"%>
</div>


<%@include file="../include/adminFooter.jsp"%>
