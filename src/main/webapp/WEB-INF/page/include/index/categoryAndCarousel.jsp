<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/7
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<!--分类和轮播图片
        竖状分类菜单 categoryMenu.jsp
        竖状分类菜单右侧的推荐产品列表 productAsideCategory.jsp
        轮播 carousel.jsp

-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(function () {
        $("div.rightMenu span").mouseenter(function () {
            var left = $(this).position().left;
            var top = $(this).position().top;
            var width = $(this).css("width");
            var destLeft = parseInt(left) + parseInt(width) / 2;
            $("img#catear").css("left", destLeft);
            $("img#catear").css("top", top - 20);
            $("img#catear").fadeIn(500);
        });
        $("div.rightMenu span").mouseleave(function () {
            $("img#catear").hide();
        });

        $("div.eachCategory").mouseenter(function () {
            var cid = $(this).attr("cid");
            showAsideCategryInfo(cid);
        });
        $("div.eachCategory").mouseleave(function () {
            var cid = $(this).attr("cid");
            hideAsideCategryInfo(cid);
        });
    });

    function showAsideCategryInfo(cid) {
        $("div.eachCategory[cid=" + cid + "]").css("background-color", "white");
        $("div.eachCategory[cid=" + cid + "] a").css({"color": "lightskyblue", "font-weight": "bold"});
        $("div.productAsideCategory[cid=" + cid + "]").show();
    }

    function hideAsideCategryInfo(cid) {
        $("div.eachCategory[cid=" + cid + "]").css("background-color", "#e2e2e3");
        $("div.eachCategory[cid=" + cid + "] a").css({"color": "black", "font-weight": "100"});
        $("div.productAsideCategory[cid=" + cid + "]").hide();
    }



</script>



<%@ include file="categoryMenu.jsp"%>

<%@include file="productAsideCategory.jsp"%>

<%@include file="carousel.jsp"%>




