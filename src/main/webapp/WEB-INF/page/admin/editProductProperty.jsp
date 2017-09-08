<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/4
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../include/adminHeader.jsp"%>
<%@ include file="../include/adminNav.jsp"%>
<style>
    div.editProductPropertyValue{
        margin-left:50px;
        /*background-color: #0f0f0f;*/
    }
    div.editProductPropertyValue div.eachPropertyValue{
        /*background-color: #00aaee;*/
        /*margin:10px auto;*/
        float: left;
        /*background-color: #00aaee;*/
        margin:10px 10px;
    }
    span.propertyName{
        display: inline-block;
        width:100px;
        text-align: center;
        font-size: 18px;
        /*background-color: #00aaee;*/
    }

    span.propertyValue input{
        height:30px;
        border-width: 2px;
        vertical-align: middle;
        font-size: 16px;

    }


</style>


<div class="listDiv">
    <!--面包树-->
    <ol class="breadcrumb">
        <li><a href="<%=request.getContextPath()%>/admin/category/admin_category_list.do">所有分类</a></li>
        <li><a href="<%=request.getContextPath()%>/admin/category/admin_category_list.do">${category.name}</a></li>
        <li class="active">${product.name}</li>
        <li class="active">产品属性管理</li>
    </ol>

    <!--显示所有该产品的属性值-->
    <div class="editProductPropertyValue">
       <c:forEach items="${propertyValueCustomList}" var="propertyValueCustom">
           <div class="eachPropertyValue">
               <span class="propertyName">${propertyValueCustom.propertyName}</span>
               <span class="propertyValue">
                   <input type="text" class="propertyValue"
                          name="propertyValue"
                          ptid="${propertyValueCustom.propertyId}"
                          pvid="${propertyValueCustom.id}"
                          pid="${product.id}"
                          value="${propertyValueCustom.value}"></span>
           </div>
       </c:forEach>
     </div>

</div>

<!--当鼠标离开(keyup)是触发$.post时间-->
<script>
    $(function () {
        $("input.propertyValue").focusout(function () {
           var propertuValue=$(this).val();
           var pvid=$(this).attr("pvid");
           var ptid=$(this).attr("ptid");
           var pid=$(this).attr("pid");
            $(this).css("outline-color","#d58512");
            $.ajax({
                url:"admin_product_updatePropertyValue.do",
                data:{"propertyValue":propertuValue,"pvid":pvid,"ptid":ptid,"pid":pid},
                type:"POST",
                dataType:"json",
                success:function (data) {
                    if(data["pvid"]!=null){
                        var pvid = data["pvid"];
                        $("input.propertyValue[ptid=" + ptid +"]").attr("pvid",pvid);
                    }
                    $("input.propertyValue[pvid="+ pvid +"]").css("outline-color","#5cb85c");

                },
                error:function () {
                    $("input.propertyValue[pvid="+ pvid +"]").css("outline-color","#b00000");
                    alert("编辑产品是出现错误！！");
                }
            })

//            //使用jQuery中高层实现$.post
//           $.post(
//                "admin_product_updatePropertyValue.do",/*post到的地址*/
//               {"propertyValue":propertuValue,"pvid":pvid,"ptid":ptid,"pid":pid},
//               function (result) {
//                   if("success" == result) $(this).css("outline-color","#2b542c !important");
//                   else $(this).css("outline-color","#b00000 !important");
//                   console.log("$.post确实生效！");
//                   console.log();
//
//               }
//           );
        });
    });

</script>




<%@ include file="../include/adminFooter.jsp"%>
