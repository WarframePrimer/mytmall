<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/8
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<!--简易搜索框-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="simpleLogoAndSimpleSearchDiv">
    <a href="#"><img src="img/site/simpleLogo.png" class="simpleLogo"></a>

    <div class="simpleSearchDiv pull-right">
        <form action="searchByKeyword.do">
            <input type="text" name="keyword" placeholder="平衡车 榨汁机"/>
            <button type="submit" class="searchButton">搜天猫</button>
            <div class="searchBelow">
                <span>
                    <a href="">冰箱</a><span>|</span>
                    <a href="">空调</a><span>|</span>
                    <a href="">女表</a><span>|</span>
                    <a href="">男表</a>
                </span>
            </div>
        </form>
    </div>
</div>
