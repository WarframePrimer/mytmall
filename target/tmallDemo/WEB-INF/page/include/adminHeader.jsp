<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/6/7
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UFT-8">
    <title>管理员管理</title>
    <!--引入bootstrap3-->

    <script src="<%=request.getContextPath()%>/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="<%=request.getContextPath()%>/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet"/>
    <script src="<%=request.getContextPath()%>/js/bootstrap/3.3.6/bootstrap.min.js"></script>

    <link href="<%=request.getContextPath()%>/css/back/back.css" rel="stylesheet"/>
    <!---->
    <script>
        function checkEmpty(id,name) {
            var value=$("#"+id).val();
            if(value.length==0){
                alert(name+"不能为空");
                $("#"+id)[0].focus();
                return false;
            }
            return true;
        }

        function checkNumber(id,name) {
            var value=$("#"+id).val();
            if(value.length==0){
                alert(name+"不能为空");
                $("#"+id)[0].focus();
                return false;
            }
            if(isNaN(value)){
                alert(name+"必须是数字");
                $("#"+id)[0].focus();
                return false;
            }
            return true;
        }


        function checkInt(id,name) {
            var value=$("#"+id).val();
            if(value.length==0){
                alert(name+"不能为空");
                $("#"+id)[0].focus();
                return false;
            }
            if(parseInt(value)!=value){
                alert(name+"必须是整数");
                    $("#"+id)[0].focus();
                return false;
            }
            return true;
        }
        
        $(function () {
            //点击删除按钮发生的事件
            $("a").click(function () {
                var deleteLink = $(this).attr("deleteLink");
                console.log(deleteLink);
                if("true"==deleteLink){
                    var confirmDelete = confirm("确定要删除");
                    if(confirmDelete)  return true;
                    return false;
                }
            })
        })
    </script>

</head>
<body>





