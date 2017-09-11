<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/11
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<%@include file="../include/header.jsp"%>
<%@include file="../include/top.jsp"%>
<%@include file="../include/simpleSearch.jsp"%>



<!--应该使用post方法，密码不可见-->
<form class="registerForm" action="<%=request.getContextPath()%>/registerUser.do" method="post">

    <div class="registerDiv">
        <!--注册后的msg-->
        <c:if test="${!empty msg}">
            <div class="alert alert-danger">
                <span class="msg">${msg}</span>
            </div>
        </c:if>

        <!--表单检验时异常时的错误提示-->
        <div class="registerErrorMessageDiv">
            <div class="alert alert-danger" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                <span class="errorMessage"></span>
            </div>
        </div>

        <table align="center" class="registerTable">
            <tbody><tr>
                <td class="registerTip registerTableLeftTD">设置会员名</td>
                <td></td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">登陆名</td>
                <td class="registerTableRightTD"><input placeholder="会员名一旦设置成功，无法修改" name="name" id="name"> </td>
            </tr>
            <tr>
                <td class="registerTip registerTableLeftTD">设置登陆密码</td>
                <td class="registerTableRightTD">登陆时验证，保护账号信息</td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">登陆密码</td>
                <td class="registerTableRightTD"><input type="password" placeholder="设置你的登陆密码" name="password" id="password"> </td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">密码确认</td>
                <td class="registerTableRightTD">
                    <input type="password" placeholder="请再次输入你的密码" id="repeatpassword">
                </td>

            </tr>
            <tr>
                <td class="registerButtonTD" colspan="2">
                    <a href="#"><button>提   交</button></a>
                </td>
            </tr>
            </tbody></table>
    </div>

</form>

<script>
    $(function () {

        $("form.registerForm").submit(function () {
            if(0==$("#name").val().length){
                $("span.errorMessage").html("请输入用户名");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#password").val().length){
                $("span.errorMessage").html("请输入密码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#repeatpassword").val().length){
                $("span.errorMessage").html("请输入重复密码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if($("#password").val() !=$("#repeatpassword").val()){
                $("span.errorMessage").html("重复密码不一致");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }

            return true;
        });


    });

</script>

<%@include file="../include/footer.jsp"%>

