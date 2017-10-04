<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/7
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--搜索框-->
<div>
    <img src="img/site/logo.gif" alt="图片加载失败" class="logo">
    <div class="searchDiv">
        <form action="searchByKeyword.do">
            <input type="text" name="keyword" placeholder="时尚男鞋 太阳镜">
            <button type="submit">搜索</button>
            <div class="searchBelow">
                <a href="#">衬衫男</a><span>|</span>
                <a href="#">连衣裙</a><span>|</span>
                <a href="#">四件套</a><span>|</span>
                <a href="#">笔记本电脑</a>
            </div>
        </form>

    </div>
</div>