<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/3
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/adminNav.jsp" %>

<style>

    div.listProductImage {
        /*background-color: #0f0f0f;*/
        text-align: center;

    }

    div.listProductImage div.imageDiv {
        display: inline-block;
        margin-top: 20px;
        margin-left: 100px;
        margin-right: 100px;
    }

</style>

<div class="listDiv">

    <!--结构树分类-->
    <ol class="breadcrumb">
        <!--项目路径需要注意-->
        <li><a href="<%=request.getContextPath()%>/admin/category/admin_category_list.do">所有分类</a></li>
        <li><a href="#nowhere">${category.name}</a></li>
        <li class="active">${product.name}</li>
        <li class="active">产品图片管理</li>
    </ol>

    <div class="listProductImage">
        <div class="panel panel-warning imageDiv">
            <div class="panel-heading">新增产品<b style="color: #00aaee">缩略</b>图</div>
            <div class="panel-body">
                <!--产品图片的上传-->
                <!--如果表单中存在文件上传的话，需要设置enctype为multipart/form-data-->
                <form class="addForm addProductImageSingleForm" method="post" action="admin_productImage_add.do"
                      enctype="multipart/form-data">

                    <input type="hidden" name="pid" value="${product.id}">
                    <input type="hidden" name="cid" value="${category.id}">
                    <input type="hidden" name="type" value="type_single">
                    <table class="addTable">
                        <tr>
                            <td colspan="2"><b>请选择图片 400x400最佳</b></td>

                        </tr>

                        <tr>
                            <td>产品图片</td>
                            <td><input id="productImageSinglePic" type="file" name="filePath" accept="image/*"></td>
                        </tr>
                        <tr class="submitTR">
                            <td colspan="2" align="center">
                                <button type="submit" class="btn btn-success">提交</button>
                            </td>
                        </tr>
                    </table>
                </form>

                <!--指定产品的缩略图一览-->
                <table class="table table-striped table-bordered table-hover  table-condensed">
                    <thead>
                    <tr class="listTableTH">
                        <th>ID</th>
                        <th>产品缩略图</th>
                        <th>删除</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${productImageSingle}" var="productSingle">
                        <tr class="listProductImageContentTR" piid="${productSingle.id}">
                            <td>${productSingle.id}</td>
                            <td><img src="<%=request.getContextPath()%>/img/productImage/${productSingle.id}.jpg"
                                     alt="图片加载失败" height="50px"></td>
                            <td>
                                <button class="deleteRecord" piid="${productSingle.id}">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </button>
                            </td>
                        </tr>

                    </c:forEach>

                    </tbody>


                </table>


            </div>
        </div>
        <div class="panel panel-warning imageDiv">
            <div class="panel-heading">新增产品<b style="color: #00aaee">详情</b>图</div>
            <div class="panel-body">
                <!--如果表单中存在文件上传的话，需要设置enctype为multipart/form-data-->
                <form class="addForm addProductImageDetailForm" method="post" action="admin_productImage_add.do"
                      enctype="multipart/form-data">

                    <input type="hidden" name="pid" value="${product.id}">
                    <input type="hidden" name="cid" value="${category.id}">
                    <input type="hidden" name="type" value="type_detail">
                    <table class="addTable">
                        <tr>
                            <td colspan="2"><b>请选择图片 宽度790最佳</b></td>
                        </tr>
                        <tr>
                            <td>产品图片</td>
                            <td><input id="productImageDetailPic" type="file" name="filePath" accept="image/*"></td>
                        </tr>
                        <tr class="submitTR">
                            <td colspan="2" align="center">
                                <button type="submit" class="btn btn-success">提交</button>
                            </td>
                        </tr>
                    </table>
                </form>

                <!--指定产品的详情图一览-->
                <table class="table table-striped table-bordered table-hover  table-condensed">
                    <thead>
                    <tr class="listTableTH">
                        <th>ID</th>
                        <th>产品缩略图</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${productImageDetail}" var="productDetail">
                        <tr class="listProductImageContentTR" piid="${productDetail.id}">
                            <td>${productDetail.id}</td>
                            <!--图片命名方式是使用图片id.jpd的方式进行存储的-->
                            <td><img src="<%=request.getContextPath()%>/img/productImage/${productDetail.id}.jpg"
                                     alt="图片加载失败" height="50px"></td>
                            <td>
                                <button class="deleteRecord" piid="${productDetail.id}"><span
                                        class="glyphicon glyphicon-trash"></span></button>
                            </td>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>


</div>

<script>

    $(function () {
        $("form.addProductImageSingleForm").submit(function () {
            //在提交表单前进行内容验证
            if (!checkEmpty("productImageSinglePic", "图片路径")) {
                return false;
            }
            return true;
        })
        $("form.addProductImageDetailForm").submit(function () {
            //在提交表单前进行内容验证
            if (!checkEmpty("productImageDetailPic", "图片路径")) {
                return false;
            }
            return true;
        })


        //点击删除图片按钮的操作：Ajax
        $("button.deleteRecord").click(function () {
            var piid = $(this).attr("piid");
            //alert(piid);

            $.ajax({
                url: "admin_productImage_deleteByAjax.do",
                data: "piid=" + piid,
                type: "GET",
                dataType:"json",
                success:function (data) {
                    $("tr.listProductImageContentTR[piid="+ piid +"]").remove();
                },
                error:function () {
                    alert("error:删除出错！");
                }
            });
        })


    })


</script>


<%@ include file="../include/adminFooter.jsp" %>
