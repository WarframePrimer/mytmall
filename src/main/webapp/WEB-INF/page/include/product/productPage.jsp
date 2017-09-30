<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/8
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!--产品图片和价格标题等信息的展示-->
<div class="imgAndInfo">
    <!--产品图片-->
    <div class="imgInImgAndInfo">
        <img width="100px" class="bigImg"
             src="<%=request.getContextPath()%>/img/productImage/${product['productSingleImage'][0]['id']}.jpg"
             alt="图片加载失败">
        <div class="smallImageDiv">
            <c:forEach items="${product.productSingleImage}" var="singleImage">
                <img width="56px" src="<%=request.getContextPath()%>/img/productImage/${singleImage.id}.jpg"
                     alt="图片加载失败" class="smallImage"
                     bigImageURL="<%=request.getContextPath()%>/img/productImage/${singleImage.id}.jpg">
            </c:forEach>
        </div>
        <div class="image4load hidden"></div>
    </div>

    <!--产品详情-->
    <div class="infoInImgAndInfo">
        <div class="productTitle">${product.name}</div>
        <div class="productSubTitle">${product.subTitle}</div>
        <div class="productPrice">
            <div class="juhuasuan">
                <span class="juhuasuanBig">聚划算</span>
                <span>此商品即将参加聚划算，<span class="juhuasuanTime">1天19小时</span>后开始，</span>
            </div>
            <div class="productPriceDiv">
                <div class="gouwuqjuanDiv">
                    <img height="16px" src="img/site/gouwujuan.png">
                    <span>全天猫实物商品通用</span>
                </div>
                <!--原价格-->
                <div class="originalDiv">
                    <span class="originalPriceDesc">价格</span>
                    <span class="originalPriceYuan">¥</span>
                    <span class="originalPrice">
                        ${product.originalPrice}
                    </span>
                </div>
                <!--促销价-->
                <div class="promotionDiv">
                    <span class="promotionPriceDesc">促销价 </span>
                    <span class="promotionPriceYuan">¥</span>
                    <span class="promotionPrice">
                        ${product.promotePrice}
                    </span>
                </div>

            </div>
        </div>

        <div class="productSaleAndReviewNumber">
            <div>销量 <span class="redColor boldWord">${product.saleCount}</span></div>
            <div>累计评价 <span class="redColor boldWord">${product.reviewCount}</span></div>
        </div>

        <div class="productNumber">
            <span>数量</span>
            <!--设置购买数量-->
            <span>
                    <span class="productNumberSettingSpan">
                        <input type="text" value="1" class="productNumberSetting">
                    </span>
                    <span class="arrow">
                        <a href="#" class="increaseNumber">
                            <span class="updown"><img src="img/site/increase.png"></span>
                        </a>
                        <span class="updownMiddle"> </span>
                        <a href="#" class="decreaseNumber">
                            <span class="updown"><img src="img/site/decrease.png"></span>
                        </a>
                    </span>
                    件
                </span>
            <span class="productStock" stock="${product.stock}">库存${product.stock}件</span>
        </div>

        <div class="serviceCommitment">
            <span class="serviceCommitmentDesc">服务承诺</span>
            <span class="serviceCommitmentLink">
                <a href="#nowhere">正品保证</a>
                <a href="#nowhere">极速退款</a>
                <a href="#nowhere">赠运费险</a>
                <a href="#nowhere">七天无理由退换</a>
            </span>
        </div>

        <div class="buyDiv">
            <!--参数为pid-->
            <a href="buyProduct.do?pid=${product.id}" class="buyLink">
                <button class="buyButton">立即购买</button>
            </a>
            <a href="#" class="addCartLink">
                <button class="addCartButton"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车</button>
            </a>
        </div>
    </div>

    <!--??，貌似是进行样式删除的-->
    <div style="clear:both"></div>
</div>

<!--产品详情和用户评论---并且商品详情为点击-->
<div class="productDetailDiv" style="display: block">

    <!--商品详情和用户评论选项框-->
    <div class="productDetailTopPart">
        <a href="#nowhere" class="productDetailTopPartSelectedLink selected">商品详情</a>
        <a href="#nowhere" class="productDetailTopPartReviewLink">累计评价<span
                class="productDetailTopReviewLinkNumber">${product.reviewCount}</span></a>
    </div>
    <!--产品参数div-->
    <div class="productParameterPart">
        <div class="productParameter">产品参数:</div>
        <div class="productParameterList">
            <c:forEach items="${propertyValueCustomList}" var="propertyValue">
                <span>${propertyValue.propertyName}：${propertyValue.value}</span>
            </c:forEach>
        </div>
        <div style="clear: both;"></div>
    </div>
    <!--商品详情处的图片部分-->
    <div class="productDetailImagesPart">
        <!--放图片-->
        <c:forEach items="${product.productDetailImage}" var="detailImage">
            <div>
                <img src="<%=request.getContextPath()%>/img/productImage/${detailImage.id}.jpg" alt="图片加载失败">
            </div>

        </c:forEach>
    </div>
</div>

<!--产品详情和累计评论----并且累计评论为点击 -->
<div class="productReviewDiv" style="display: block">
    <!--商品详情和用户评论选项框-->
    <div class="productReviewTopPart">
        <a href="#nowhere" class="productDetailTopPartSelectedLink">商品详情</a>
        <a href="#nowhere" class="productDetailTopPartReviewLink selected">累计评价<span
                class="productDetailTopReviewLinkNumber">${product.reviewCount}</span></a>
    </div>
    <!--累计评论所在div-->
    <div class="productReviewContentPart">
        <c:forEach items="${reviewCustomList}" var="reviewCustom">
            <!--一条评论的div-->
            <div class="productReviewItem">
                <!--评论-->
                <div class="productReviewItemDesc">
                    <!--评论的具体内容-->
                    <div class="productReviewItemContent">
                            ${reviewCustom.content}
                    </div>
                    <!--评论的时间-->
                    <div class="productReviewItemDate"><fmt:formatDate value="${reviewCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                </div>
                <!--评论的用户-->
                <div class="productReviewItemUserInfo">
                    <!--不显示用户的详细昵称-->
                        ${reviewCustom.userName}<span class="userInfoGrayPart">（匿名）</span>
                </div>
                <div style="clear: both"></div>
            </div>
        </c:forEach>


    </div>


</div>


<!--交互-->
<script>
    $(function () {

        <!--大小图片的交互-->
        $("img.smallImage").mouseenter(function () {

            //对于HTML元素本身就带有的固有属性，在处理时，使用prop方法
            //对于HTML元素我们自己自定义的DOM属性，在处理时，使用attr方法
            var bigImageURL = $(this).attr("bigImageURL");
            $("img.bigImg").attr("src", bigImageURL);

            $("img.smallImage").each(function () {
                $(this).css('border-color', 'white');
            });
            $(this).css('border-color', 'black');
        });
        //预加载，这里先不用，因为太占内存
//            $("img.bigImg").load(function () {
//                $("img.smallImage").each(function () {
//                    var bigImageURL = $(this).attr("bigImageURL");
//                    img = new Image();
//                    img.src = bigImageURL;
//                    img.onload = function () {
//                        console.log(bigImageURL);
//                        $("div.image4load").append($(img));
//                    };
//                });
//            });

        //价格的交互
        var stock = $("span.productStock").attr("stock");
        $(".productNumberSetting").keyup(function () {
            var num = $(".productNumberSetting").val();
            num = parseInt(num);
            if (isNaN(num)) {
                num = 1;
            }
            if (num <= 0) num = 1;
            if (num > stock) num = stock;
            $(".productNumberSetting").val(num);

        });

        $("a.increaseNumber").click(function () {
            var num = $(".productNumberSetting").val();
            num++;
            if (num > stock) num = stock;
            $(".productNumberSetting").val(num);
        });
        $("a.decreaseNumber").click(function () {
            var num = $(".productNumberSetting").val();
            --num;
            if (num <= 0) num = 1;
            $(".productNumberSetting").val(num);
        });


        //商品详情和累计评论的交互
        $("div.productReviewDiv").hide();
        $("a.productDetailTopPartReviewLink").click(function () {
            $("div.productDetailDiv").hide();
            $("div.productReviewDiv").show();
        });
        $("a.productDetailTopPartSelectedLink").click(function () {
            $("div.productReviewDiv").hide();
            $("div.productDetailDiv").show();
        });

        //当点击立即购买和加入购物车的按钮时，触发事件检查是游客还是用户
        $("a.buyLink").click(function () {
            var page = "checkLogin.do";
            $.get(
                page,
                function (result) {
                    if ("success" == result['msg']) {
                        //表示登陆,继续下一步操作，立即购买
                        /**
                         * 实现步骤：(登录的前提下)
                         * 点击立即购买按钮之后
                         * 生成orderItem()
                         * 跳转到buyPage页面(需要参数:orderitem)
                         *
                         */
                        var productNum = $("input.productNumberSetting").val();
                        //location.href表示的是本页面跳转

                        location.href = $("a.buyLink").attr("href") + "&num=" + productNum;
                    } else {
                        //未登陆，请先登陆
                        //显示登陆模态窗口
                        $("div#loginModal").modal('show');
                    }

                }
            );
            return false;
        });
        $("a.addCartLink").click(function () {
            var page = "checkLogin.do";
            $.get(
                page,
                function (result) {
                    if ("success" == result['msg']) {
                        //表示登陆,继续下一步操作，加入购物车
                        var pid = ${product.id};
                        var num = $("input.productNumberSetting").val();
                        var addCartPage = "addCart.do";
                        $.get(
                            addCartPage,
                            {"productId": pid, "productNum": num},
                            function (result) {
                                if ("success" == result['msg']) {
                                    $("button.addCartButton").html("已加入购物车");
                                    $("button.addCartButton").attr("disabled","disabled");
                                    $("button.addCartButton").css("background-color","lightgray");
                                    $("button.addCartButton").css("color","black");
                                }else{
                                    alert("加入购物车异常！");
                                }
                            }
                        )
                    } else {
                        //未登陆，请先登陆
                        //显示登陆模态窗口
                        $("div#loginModal").modal('show');
                    }

                }
            );
            return false;
        });
    });

</script>