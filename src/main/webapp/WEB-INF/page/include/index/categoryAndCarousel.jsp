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

        //这个是为了实现猫耳朵的效果
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
            console.log("mouseenter");
            var cid = $(this).attr("cid");
            showAsideCategoryInfo(cid);
        });


        $("div.eachCategory").mouseleave(function () {
            console.log("mouseleave");
            var cid = $(this).attr("cid");
            hideAsideCategoryInfo(cid);
        });

        //mouseenter,mouseleave&&mouseover
        $("div.productAsideCategory").mouseenter(function () {
            var cid = $(this).attr("cid");
            showAsideCategoryInfo(cid);
        });
        $("div.productAsideCategory").mouseleave(function () {
            var cid = $(this).attr("cid");
            hideAsideCategoryInfo(cid);
        });

        //这个函数的功能是当光标移到相应的分类是，显示出分类下的一些属性值进行展示
        function showAsideCategoryInfo(cid) {
            $("div.eachCategory[cid=" + cid + "]").css("background-color", "white");
            $("div.eachCategory[cid=" + cid + "] a").css({"color": "lightskyblue", "font-weight": "bold"});
            $("div.productAsideCategory[cid=" + cid + "]").show();
        }

        //与上面的函数相反，在光标离开即失去焦点是隐藏相应分类的属性值展示
        function hideAsideCategoryInfo(cid) {
            $("div.eachCategory[cid=" + cid + "]").css("background-color", "#e2e2e3");
            $("div.eachCategory[cid=" + cid + "] a").css({"color": "black", "font-weight": "100"});
            $("div.productAsideCategory[cid=" + cid + "]").hide();
        }
    });
</script>



<%@ include file="categoryMenu.jsp"%>

<%@include file="productAsideCategory.jsp"%>

<%@include file="carousel.jsp"%>




