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


<div class="modal" id="loginModal" tabindex="-1" role="dialog">
    <div class="modal-dialog loginDivInProductPage">
        <!--登录出错-->
        <div class="loginErrorMessageDiv">
            <div class="alert alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                <span class="errorMessage"></span>
            </div>
        </div>
        <div class="login_account_text">账户登录</div>

    </div>


</div>

