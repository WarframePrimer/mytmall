<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/6/11
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../include/adminHeader.jsp"%>
<%@include file="../include/adminNav.jsp"%>

<!--后续功能的完善：批量增删改查的实现以及优化-->


<% response.setContentType("text/html;charset=utf-8");%>

    <div class="listDiv">

        <!--结构树分类-->
        <ol class="breadcrumb">
            <!--项目路径需要注意-->
            <li><a href="<%=request.getContextPath()%>/admin/category/admin_category_list.do">所有分类</a></li>
            <li><a href="#nowhere">${category.name}</a></li>
            <li class="active">属性管理</li>
        </ol>

        <c:choose>
            <c:when test="${cidError != null}">
                <h1><b>${cidError}</b></h1>
            </c:when>
            <c:otherwise>
                <table class="table table-striped table-hover table-bordered table-condensed">
                    <thead>
                    <tr class="listTableTH">
                        <th>ID</th>
                        <th width="60%">属性名称</th>
                        <th>编辑</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${properties}" var="property">
                        <tr class="propertyListTableContentTR" id="${property.id}">
                            <td>${property.id}</td>
                            <td>${property.name}</td>
                            <!--点击编辑按钮，弹出编辑的选项框-->
                            <!--思路：使用ajax？或者直接传给controller？-->
                            <!--
                                version0.9 对于要编辑的属性，将相关信息进行传值跳转到pre-edit页面进行进一步的修改编辑
                            -->
                            <td>
                                <a class="propertyEdit" pageNum="${page.pageNum}" href="admin_property_preEdit.do?id=${property.id}&pageNum=${page.pageNum}${page.param}"><span class="glyphicon glyphicon-edit"></span></a></td>
                            <!--删除相关属性-->
                            <td>
                                <!--<a href="admin_property_delete.do?id=${property.id}&pageNum=${page.pageNum}"><span class="glyphicon glyphicon-trash"></span></a>-->
                                <button id="${property.id}" class="deleteRecord"><span class="glyphicon glyphicon-trash"></span></button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%@include file="../include/adminPage.jsp"%>
            </c:otherwise>
        </c:choose>
    </div>



    <!--属性的增加-->
    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增属性</div>
        <div class="panel-body">

            <form id="addForm" method="post" action="admin_property_add.do?pageNum=${page.pageNum}">
                <table class="addTable">
                    <input type="hidden" name="cid" value="${category.id}"/>
                    <tr>
                        <td>属性名称</td>
                        <td><input type="text" id="name" name="name" class="form-control"></td>
                    </tr>

                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-success">提交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>


    <script>
        $(function () {
            //点击删除记录按钮执行ajax的删除，确保还是在原来的页面
            $("button.deleteRecord").click(function () {
                var id = $(this).attr("id");

                $.ajax({
                    url:"admin_property_deleteAjax.do",
                    data:"id=" + id,
                    type:"GET",
                    dataType:"json",
                    success:function (data) {
                        //删除指定的标签
                        $("tr.propertyListTableContentTR[id=" + id + "]").remove();
                    },
                    error:function () {
                        alert("error:删除出错！！");
                    }
                });
            })



            //对于增加表单中的各字段进行相关检验，如果不符合条件则请求不发送
            $("form#addForm").submit(function () {
                //检查是否属性为空
                if(!checkEmpty("name","属性名称")){
                    return false;
                }

                return true;
            })



            //以后再改，这里已经使用跳转的简单方法解决，
            //对于使用ajax目前还没有思路
            //给超链接添加点击事件
//            $("a.propertyEdit").click(function () {
//                //alert("fuck you!!");
//
//
//            })


        })



    </script>

<%@include file="../include/adminFooter.jsp"%>·




