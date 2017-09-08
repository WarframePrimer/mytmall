<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/6/27
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<%@ include file="../include/adminHeader.jsp"%>
<%@ include file="../include/adminNav.jsp"%>

<% request.setCharacterEncoding("utf-8"); %>


    <!--通用编辑样式，便于管理-->
    <div class="contentDiv">
      <div class="editTitle">
          <ol class="breadcrumb">
            <li><a href="<%=request.getContextPath()%>/admin/category/admin_category_list.do">所有分类</a></li>
            <li class="active">编辑分类</li>
          </ol>
      </div>
      <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑分类</div>
        <div class="panel-body">
            <form action="admin_category_edit.do" id="editForm" class="editForm" method="post" enctype="multipart/form-data">
                <input type="hidden" name="cid" value="${category.id}">
                <input type="hidden" name="pageNum" value="${pageNum}">
                <table class="editTable">
                    <tbody>
                        <tr>
                            <td>分类名称</td>
                            <td><input type="text" id="name" name="name" class="form-control" value="${category.name}"></td>
                        </tr>
                        <tr>
                            <td>分类图片</td>
                            <td><input id="categoryPic" type="file" name="filePath" accept="image/*"></td>
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
        //这里的js中需要对编辑表单中的内容进行非空等常规检验
        $(function () {
            //进行非空判断
            $("form#editForm").submit(function () {
                if(!checkEmpty("name","分类名称")){
                    return false;
                }
                if(!checkEmpty("categoryPic","图片路径")){
                    return false;
                }

                return true;
            })


        })


    </script>




<%@ include file="../include/adminFooter.jsp"%>




