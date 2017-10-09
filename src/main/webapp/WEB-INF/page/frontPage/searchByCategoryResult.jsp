<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/10/9
  Time: 16:04
  To change this template use File | Settings | File Templates.

  通过分类进行的搜索结果页面，可以对搜索结果进行按价格排序、按销量排序等排序
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/header.jsp" %>
<%@include file="../include/top.jsp" %>
<%@include file="../include/index/search.jsp"%>

<%--搜索结果--%>
<div class="categoryPageDiv">
    <img src="img/site/72.jpg" alt="图片加载失败">
    <!--对商品进行排序分类-->
    <div class="categorySortBar">
        <!--放排序按钮的table-->
        <table class="categorySortBarTable categorySortTable">
            <tbody>
            <tr>
                <td class="grayColumn"><a href="#">综合<span class="glyphicon glyphicon-arrow-down"></span></a></td>
                <td><a href="#">人气<span class="glyphicon glyphicon-arrow-down"></span></a></td>
                <td><a href="#">新品<span class="glyphicon glyphicon-arrow-down"></span></a></td>
                <td><a href="#">销量<span class="glyphicon glyphicon-arrow-down"></span></a></td>
                <td><a href="#">价格<span class="glyphicon glyphicon-resize-vertical"></span></a></td>
            </tr>
            </tbody>
        </table>
        <!--放价格区间的table-->
        <table class="categorySortBarTable">
            <tbody>
            <tr>
                <td><input class="sortBarPrice beginPrice" type="text" placeholder="请输入"/></td>
                <td class="grayColumn priceMiddleColumn">-</td>
                <td><input class="sortBarPrice endPrice" type="text" placeholder="请输入"/></td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="categoryProducts">

        <div class="productUnit" price="799">
            <div class="productUnitFrame">
                <a href="#"><img src="img/site/productSingle_middle/7058.jpg" alt="图片加载失败"></a>
                <span class="productPrice">￥799.20</span>
                <a href="#" class="productLink"> MAXFEEL休闲男士手包真皮手拿包大容量信封包手抓包夹包软韩版潮</a>
                <a href="#" class="tmallLink">天猫专卖</a>
                <div class="show1 productInfo">
                    <span class="monthDeal">月成交<span class="productDealNumber">46笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">18</span></span>
                    <span class="wangwang">
                            <a href="#" class="wangwangLink"><img src="img/site/wangwang.png" alt="图片加载失败"></a>
                        </span>
                </div>
            </div>
        </div>
        <div class="productUnit" price="799">
            <div class="productUnitFrame">
                <a href="#"><img src="img/site/productSingle_middle/7047.jpg" alt="图片加载失败"></a>
                <span class="productPrice">￥799.20</span>
                <a href="#" class="productLink"> MAXFEEL休闲男士手包真皮手拿包大容量信封包手抓包夹包软韩版潮</a>
                <a href="#" class="tmallLink">天猫专卖</a>
                <div class="show1 productInfo">
                    <span class="monthDeal">月成交<span class="productDealNumber">46笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">18</span></span>
                    <span class="wangwang">
                            <a href="#" class="wangwangLink"><img src="img/site/wangwang.png" alt="图片加载失败"></a>
                        </span>
                </div>
            </div>
        </div>
        <div class="productUnit" price="600">
            <div class="productUnitFrame">
                <a href="#"><img src="img/site/productSingle_middle/7036.jpg" alt="图片加载失败"></a>
                <span class="productPrice">￥799.20</span>
                <a href="#" class="productLink"> MAXFEEL休闲男士手包真皮手拿包大容量信封包手抓包夹包软韩版潮</a>
                <a href="#" class="tmallLink">天猫专卖</a>
                <div class="show1 productInfo">
                    <span class="monthDeal">月成交<span class="productDealNumber">46笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">18</span></span>
                    <span class="wangwang">
                            <a href="#" class="wangwangLink"><img src="img/site/wangwang.png" alt="图片加载失败"></a>
                        </span>
                </div>
            </div>
        </div>
        <div class="productUnit" price="510">
            <div class="productUnitFrame">
                <a href="#"><img src="img/site/productSingle_middle/7025.jpg" alt="图片加载失败"></a>
                <span class="productPrice">￥799.20</span>
                <a href="#" class="productLink"> MAXFEEL休闲男士手包真皮手拿包大容量信封包手抓包夹包软韩版潮</a>
                <a href="#" class="tmallLink">天猫专卖</a>
                <div class="show1 productInfo">
                    <span class="monthDeal">月成交<span class="productDealNumber">46笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">18</span></span>
                    <span class="wangwang">
                            <a href="#" class="wangwangLink"><img src="img/site/wangwang.png" alt="图片加载失败"></a>
                        </span>
                </div>
            </div>
        </div>
        <div class="productUnit" price="4">
            <div class="productUnitFrame">
                <a href="#"><img src="img/site/productSingle_middle/7014.jpg" alt="图片加载失败"></a>
                <span class="productPrice">￥799.20</span>
                <a href="#" class="productLink"> MAXFEEL休闲男士手包真皮手拿包大容量信封包手抓包夹包软韩版潮</a>
                <a href="#" class="tmallLink">天猫专卖</a>
                <div class="show1 productInfo">
                    <span class="monthDeal">月成交<span class="productDealNumber">46笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">18</span></span>
                    <span class="wangwang">
                            <a href="#" class="wangwangLink"><img src="img/site/wangwang.png" alt="图片加载失败"></a>
                        </span>
                </div>
            </div>
        </div>
        <div class="productUnit" price="5000">
            <div class="productUnitFrame">
                <a href="#"><img src="img/site/productSingle_middle/7003.jpg" alt="图片加载失败"></a>
                <span class="productPrice">￥799.20</span>
                <a href="#" class="productLink"> MAXFEEL休闲男士手包真皮手拿包大容量信封包手抓包夹包软韩版潮</a>
                <a href="#" class="tmallLink">天猫专卖</a>
                <div class="show1 productInfo">
                    <span class="monthDeal">月成交<span class="productDealNumber">46笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">18</span></span>
                    <span class="wangwang">
                            <a href="#" class="wangwangLink"><img src="img/site/wangwang.png" alt="图片加载失败"></a>
                        </span>
                </div>
            </div>
        </div>
        <div class="productUnit" price="200">
            <div class="productUnitFrame">
                <a href="#"><img src="img/site/productSingle_middle/6992.jpg" alt="图片加载失败"></a>
                <span class="productPrice">￥799.20</span>
                <a href="#" class="productLink"> MAXFEEL休闲男士手包真皮手拿包大容量信封包手抓包夹包软韩版潮</a>
                <a href="#" class="tmallLink">天猫专卖</a>
                <div class="show1 productInfo">
                    <span class="monthDeal">月成交<span class="productDealNumber">46笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">18</span></span>
                    <span class="wangwang">
                            <a href="#" class="wangwangLink"><img src="img/site/wangwang.png" alt="图片加载失败"></a>
                        </span>
                </div>
            </div>
        </div>
        <div class="productUnit" price="1">
            <div class="productUnitFrame">
                <a href="#"><img src="img/site/productSingle_middle/6981.jpg" alt="图片加载失败"></a>
                <span class="productPrice">￥799.20</span>
                <a href="#" class="productLink"> MAXFEEL休闲男士手包真皮手拿包大容量信封包手抓包夹包软韩版潮</a>
                <a href="#" class="tmallLink">天猫专卖</a>
                <div class="show1 productInfo">
                    <span class="monthDeal">月成交<span class="productDealNumber">46笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">18</span></span>
                    <span class="wangwang">
                            <a href="#" class="wangwangLink"><img src="img/site/wangwang.png" alt="图片加载失败"></a>
                        </span>
                </div>
            </div>
        </div>

    </div>


</div>



<script>
    $(function () {
        $("input.sortBarPrice").keyup(function () {
            var num = $(this).val();
            if(num.length==0){
                $("div.productUnit").show();
                return;
            }

            num = parseInt(num);
            if(isNaN(num)) num=1;
            if(num<1) num=1;
            $(this).val(num);

            var begin = $("input.beginPrice").val();

            var end = $("input.endPrice").val();

            if(!isNaN(begin) && !isNaN(end)){
                $("div.productUnit").hide();

                $("div.productUnit").each(function () {
                    var price = $(this).attr("price");

                    price = new Number(price);

                    if(price<=end && price>=begin){
                        $(this).show();
                    }
                });
            }

        });
    })

</script>





<%@include file="../include/footer.jsp" %>
