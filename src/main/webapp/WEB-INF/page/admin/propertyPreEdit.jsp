<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/7/20
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../include/adminHeader.jsp"%>
<%@include file="../include/adminNav.jsp"%>

<% request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
%>

    <div class="contentDiv">
        <div class="editTitle">
            <ol class="breadcrumb">
                <li><a href="<%=request.getContextPath()%>/admin/category/admin_category_list.do">所有分类</a></li>
                <li><a href="<%=request.getContextPath()%>/admin/category/admin_category_list.do">${category.name}</a></li>
                <li><a href="#">${property.name}</a></li>
                <li class="active">编辑属性</li>
            </ol>
        </div>
        <div class="panel panel-warning editDiv">
            <div class="panel-heading">编辑属性</div>
            <div class="panel-body">
                <!--没有涉及到图片的上传，所以不需要设置enctype=“multipart/form-data”-->
                <form action="admin_property_edit.do" id="editForm" method="post">
                    <input type="hidden" name="cid" value="${category.id}">
                    <input type="hidden" name="id" value="${property.id}">
                    <input type="hidden" name="pageNum" value="${pageNum}">
                    <table class="editTable">
                        <tbody>
                        <tr>
                            <td>属性名称</td>
                            <td><input type="text" id="name" name="name" class="form-control" value="${property.name}"></td>
                        </tr>
                        <tr class="submitTR">
                            <td colspan="2" align="center">
                                <button type="submit" class="btn btn-success">提交</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>

    </div>


    <script>
        $(function () {
            //进行非空判断
            $("form#editForm").submit(function () {
                if(!checkEmpty("name","属性名称")){
                    return false;
                }

                console.log($("input#name").val());

                return true;
            })


        })

    </script>





<%@include file="../include/adminFooter.jsp"%>