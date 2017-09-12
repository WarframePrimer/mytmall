<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/7
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>

<!--这个modal.jsp提供了了两个模态窗口
    1.登录模态窗口  用户在未登录时，点击加入购物车或购买按钮的时候会弹出
    2.删除模态窗口  当用户在我的订单页面和购物车页面进行删除操作的时候，就会模态删除窗口

    后续需要的时候进行添加
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--登陆的模态窗口-->
<div class="modal " id="loginModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog loginDivInProductPageModalDiv">
        <div class="modal-content">
            <div class="loginDivInProductPage">
                <div class="loginErrorMessageDiv">
                    <div class="alert alert-danger" >
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                        <span class="errorMessage"></span>
                    </div>
                </div>

                <div class="login_acount_text">账户登录</div>
                <div class="loginInput" >
                            <span class="loginInputIcon ">
                                <span class=" glyphicon glyphicon-user"></span>
                            </span>
                    <input id="name" name="name" placeholder="手机/会员名/邮箱" type="text">
                </div>

                <div class="loginInput" >
                            <span class="loginInputIcon ">
                                <span class=" glyphicon glyphicon-lock"></span>
                            </span>
                    <input id="password" name="password"  type="password" placeholder="密码" type="text">
                </div>
                <div>
                    <a href="#nowhere">忘记登录密码</a>
                    <a href="register.do" class="pull-right">免费注册</a>
                </div>
                <div style="margin-top:20px">
                    <button class="btn btn-block redButton loginSubmitButton" type="submit">登录</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!--删除的模态窗口-->
<div class="modal" id="deleteConfirmModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog deleteConfirmModalDiv">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">确认删除？</h4>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
                <button class="btn btn-primary deleteConfirmButton" id="submit" type="button">确认</button>
            </div>
        </div>
    </div>
</div>
</div></div>

<script>
    $(function () {
        //异常信息提示框先隐藏
        $("div.loginErrorMessageDiv").hide();


        //点击模态窗口中的登录按钮式
        $("button.loginSubmitButton").click(function () {
           //检验输入是否符合要求
            var name = $("input#name").val();
            var password = $("input#password").val();
            if(0==name.length || 0==password.length){
                $("span.errorMessage").html("请输入账户密码");
                $("div.loginErrorMessageDiv").show();
                return false;
            }

            var page = "loginAjax.do";
            $.post(
                page,{name:name,password:password},function (result) {
                    if("success"==result['msg']){
                        //重新加载页面
                        location.reload();
                    }else{
                        $("span.errorMessage").html(result['msg']);
                        $("div.loginErrorMessageDiv").show();
                        return false;
                    }
                }
            )
        });

    });



</script>

