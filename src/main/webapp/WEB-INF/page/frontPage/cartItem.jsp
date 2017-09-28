<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/13
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@include file="../include/header.jsp" %>
<%@include file="../include/top.jsp" %>
<%@ include file="../include/simpleSearch.jsp" %>

<c:choose>
    <c:when test="${cartItemNumber != 0}">
        <!--购物车的一系列div-->
        <div class="cartDiv">
            <!--购物车右上方的价格显示div-->
            <div class="cartTitle">
                <div class="cartTitlePrice pull-right">
                    <span>已选商品(不含运费)<span class="cartProductSumPrice">0.00</span></span>
                    <!--点击结算按钮-->
                    <button class="createOrderButton" disabled="disabled">结算</button>
                </div>
            </div>

            <!--订单项-->
            <div class="cartProductItemList">
                <table class="cartProductItemTable">
                    <!--列名信息-->
                    <thead>
                    <th class="selectAll"><img class="selectAllProduct" src="img/site/cartNotSelected.png"
                                               selectit="false"/>全选
                    </th>
                    <th>商品信息</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>金额</th>
                    <th>操作</th>
                    </thead>
                    <!--表信息-->
                    <tbody>

                    <c:forEach items="${cartItemList}" var="cartItem">
                        <!--一行就表明是一个商品信息-->
                        <tr class="cartProductItemTR" oiid="${cartItem.id}" pid="${cartItem['product']['id']}">
                            <!--全选框和商品图片-->
                            <td width="140px">
                                <img class="productIfSelect" src="img/site/cartNotSelected.png" selectit="false"
                                     oiid="${cartItem.id}" pid="${cartItem['product']['id']}"/>
                                <img class="productImage"
                                     src="<%=request.getContextPath()%>/img/productImage/${cartItem['product']['firstProductImage']['id']}.jpg"/>
                            </td>
                            <!--商品信息-->
                            <td>
                                <div class="cartProductLinkOutDiv">
                                    <a href="getProductDetail.do?pid=${cartItem['product']['id']}&cid=${cartItem['product']['category']['id']}"
                                       class="cartProductLink" target="_blank">${cartItem['product']['name']}</a>
                                    <!--商品信息下方的承诺-->
                                    <div class="cartProductLinkInnerDiv">
                                        <a href="#nowhere"><img src="img/site/creditcard.png" alt="图片加载失败"
                                                                title="支持信用卡支付"></a>
                                        <a href="#nowhere"><img src="img/site/7day.png" alt="图片加载失败"
                                                                title="消费者保障服务 卖家承诺7天退换"></a>
                                        <a href="#nowhere"><img src="img/site/promise.png" alt="图片加载失败"
                                                                title="消费者保障服务 卖家承诺如实描述"></a>
                                    </div>
                                </div>
                            </td>
                            <!--商品单价-->
                            <td>
                                <span class="originalPrice">￥${cartItem['product']['originalPrice']}</span>
                                <span class="promotePrice">￥${cartItem['product']['promotePrice']}</span>
                            </td>
                            <!--商品数量设置-->
                            <!--商品数量设置中，每次只要进行商品数量的改变，数据库中的orderItem记录也要进行相应的update-->
                            <td>
                                <div class="cartProductAmountSetting">

                                    <span class="cartProductPromotePrice hidden" oiid="${cartItem.id}"
                                          pid="${cartItem['product']['id']}">${cartItem['product']['promotePrice']}</span>

                                    <span class="stock hidden" oiid="${cartItem.id}"
                                          pid="${cartItem['product']['id']}">${cartItem['product']['stock']}</span>
                                    <!--具体对商品购买数量的设置-->
                                    <a href="#nowhere" class="decreaseAmount" pid="${cartItem['product']['id']}"
                                       oiid="${cartItem.id}">-</a>
                                    <!--实时监听input中值的变化-->
                                    <input value="${cartItem.number}" class="cartProductItemAmount"
                                           pid="${cartItem['product']['id']}" oiid="${cartItem.id}">
                                    <a href="#nowhere" class="increaseAmount" pid="${cartItem['product']['id']}"
                                       oiid="${cartItem.id}">+</a>
                                </div>
                            </td>
                            <!--单类产品小计价格-->
                            <td>
                                <!--这里设置商品id和订单id是因为之后要将计算得到的小计价格传给这个span显示出来-->
                                <span class="cartProductItemSmallSumPrice" pid="${cartItem['product']['id']}"
                                      oiid="${cartItem.id}">￥
                                        <fmt:formatNumber minFractionDigits="2"
                                                          value="${cartItem['product']['promotePrice']*cartItem.number}"/>
                                </span>
                            </td>
                            <!--删除操作-->
                            <!--pid商品id,oiid(OrderItemId)订单项(购物车)id，删除需要知道订单号和商品号即哪个订单中的哪个产品-->
                            <td class="deleteCartProduct">
                                <!--通过ajax删除指定的订单项-->
                                <a href="#" pid="${cartItem['product']['id']}" oiid="${cartItem.id}">删除</a>
                            </td>
                        </tr>

                    </c:forEach>


                    </tbody>
                </table>
            </div>


            <!--购物车下方的结算-->
            <div class="cartFoot">

                <img src="img/site/cartNotSelected.png" alt="图片加载失败" class="selectAllProduct" selectit="false"
                     oiid="${cartItem.id}">
                <span>全选</span>
                <a href="#nowhere" class="deleteAllProduct" oiid="${cartItem.id}">删除</a>
                <div class="cartFootPrice pull-right">
                    <span>已选商品 <span class="cartProductSumNumber">0</span> 件</span>·
                    <span>合计(不含运费):<span class="cartProductSumPrice">0.00</span></span>
                    <a href="balance.do" class="createOrderLink">
                        <button class="createOrderButton" disabled="disabled">结算</button>
                    </a>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="cartIsEmpty">
            <h2>您的购物车还是空的，赶紧行动吧！</h2>
        </div>
    </c:otherwise>
</c:choose>


<script>
    /*公共函数*/
    /*1.千分制金额的格式化2.是否全选3.只要有一个商品选中，结算按钮就激活4.计算每个商品的小计价格5.计算所有选中商品的总计价格*/
    /*只要有一个商品被点击则结算按钮改为激活状态*/
    function syncOderButton() {
        var selectProduct = false;
        /*进行判断*/
        //遍历所有商品勾选框
        $("img.productIfSelect").each(function () {
            var selectit = $(this).attr("selectit");
            if ("selectit" == selectit) selectProduct = true;
        });
        /*只要有任意商品被选中，就将结算按钮激活*/
        if (selectProduct) {
            $("button.createOrderButton").removeAttr("disabled");
            $("button.createOrderButton").css("background-color", "rgb(196, 0, 0)");
        } else {
            $("button.createOrderButton").attr("disabled", "disabled");
            $("button.createOrderButton").css("background-color", "#B0B0B0");
        }

    }

    /*同步全选*/
    /*判断是否全选只需要找到一个商品没有被选中那么就表示没有全选*/
    function syncSelectAll() {
        var selectAll = true;

        var len = $("img.productIfSelect").length;
        console.log(len);
        if (len > 0) {
            $("img.productIfSelect").each(function () {
                var selectit = $(this).attr("selectit");
                if ("false" == selectit) selectAll = false;
            });
            if (selectAll) {
                $("img.selectAllProduct").attr("selectit", "selectit");
                $("img.selectAllProduct").attr("src", "img/site/cartSelected.png");
            } else {
                $("img.selectAllProduct").attr("selectit", "false");
                $("img.selectAllProduct").attr("src", "img/site/cartNotSelected.png");
            }
        } else {
            $("img.selectAllProduct").attr("selectit", "false");
            $("img.selectAllProduct").attr("src", "img/site/cartNotSelected.png");
        }


    }

    /*同步计算商品价格和数量(所有)*/
    function calcProductPriceAndNumber() {
        var sum = 0;
        var number = 0;

        $("img.productIfSelect[selectit='selectit']").each(function () {
            var pid = $(this).attr("pid");
            //console.log(pid);
            var price = $("span.cartProductItemSmallSumPrice[pid=" + pid + "]").text();
            //console.log(price);
            /*简单的正则表达式将逗号和中文符号替换成空*/
            price = price.replace(/,/g, "");
            price = price.replace(/￥/g, "");
            //console.log(price);
            sum += new Number(price)
            var num = $("input.cartProductItemAmount[pid=" + pid + "]").val();
            number += new Number(num);
        });
        //保留两位小数
        sum = sum.toFixed(2);
        /*将得到的信息进行响应的显示*/
        $("span.cartProductSumNumber").html(number);
        $("span.cartProductSumPrice").html("￥" + sum);


    }

    /*计算单个商品的小计价格*/
    function calcSmallPrice(pid, price, num) {
        $("input.cartProductItemAmount[pid=" + pid + "]").val(num);
        var smallPrice = price * num;
        smallPrice = smallPrice.toFixed(2);
        $("span.cartProductItemSmallSumPrice[pid=" + pid + "]").html("￥" + smallPrice);

        //重新计算总价格和总数目
        calcProductPriceAndNumber();
    }

    /*事件响应*/
    //1.选中一个商品
    $("img.productIfSelect").click(function () {
        var pid = $(this).attr("pid");
        var oiid = $(this).attr("oiid");
        var selectit = $(this).attr("selectit");
        if ("selectit" == selectit) {
            $(".productIfSelect[pid=" + pid + "]").attr("selectit", "false");
            $(".productIfSelect[pid=" + pid + "]").attr("src", "img/site/cartNotSelected.png");
            $(".productIfSelect[pid=" + pid + "]").parents("tr.cartProductItemTR").css("background-color", "#FCFCFC");
        } else {
            $(".productIfSelect[pid=" + pid + "]").attr("selectit", "selectit");
            $(".productIfSelect[pid=" + pid + "]").attr("src", "img/site/cartSelected.png");
            $(".productIfSelect[pid=" + pid + "]").parents("tr.cartProductItemTR").css("background-color", "#FFF8E1");
        }
        syncSelectAll();
        syncOderButton();
        calcProductPriceAndNumber();
    });
    //2.全选
    $("img.selectAllProduct").click(function () {
        //var oiid = $(this).attr("oiid");并不需要没有意义
        var selectit = $(this).attr("selectit");
        if ("selectit" == selectit) {
            $(this).attr("selectit", "false");
            $(this).attr("src", "img/site/cartNotSelected.png");
            $("img.productIfSelect").each(function () {
                $(this).attr("selectit", "false");
                $(this).parents("tr.cartProductItemTR").css("background-color", "#FCFCFC");
                $(this).attr("src", "img/site/cartNotSelected.png");
            });
        } else {
            $(this).attr("selectit", "selectit");
            $(this).attr("src", "img/site/cartSelected.png");
            $("img.productIfSelect").each(function () {
                $(this).attr("selectit", "selectit");
                $(this).parents("tr.cartProductItemTR").css("background-color", "#FFF8E1");
                $(this).attr("src", "img/site/cartSelected.png");
            });
        }
        syncSelectAll();
        syncOderButton();
        calcProductPriceAndNumber();
    });

    //商品数量增加和减少
    $("a.decreaseAmount").click(function () {
        var pid = $(this).attr("pid");
        //取得此商品的促销价
        var price = $("span.cartProductPromotePrice[pid=" + pid + "]").text();

        //取得此商品的库存量
        var stock = $("span.stock[pid=" + pid + "]").text();
        //获得当前的数量
        var num = $("input.cartProductItemAmount[pid=" + pid + "]").val();
        --num;
        if (num <= 0) num = 1;
        calcSmallPrice(pid, price, num);
        //trigger使得指定元素进行指定的事件类型
        $("input.cartProductItemAmount[pid=" + pid + "]").trigger("inputpropertychange");
    });
    $("a.increaseAmount").click(function () {
        var pid = $(this).attr("pid");
        //取得此商品的促销价
        var price = $("span.cartProductPromotePrice[pid=" + pid + "]").text();
        //取得此商品的库存量
        //alert(price);
        var stock = $("span.stock[pid=" + pid + "]").text();
        //获得当前的数量
        //alert(stock);
        var num = $("input.cartProductItemAmount[pid=" + pid + "]").val();
        num++;
        if (num > stock) num = stock;
        calcSmallPrice(pid, price, num);
        //当使用+-对数量进行设置时触发input中的inputpropertychange事件
        $("input.cartProductItemAmount[pid=" + pid + "]").trigger("inputpropertychange");
    });


    //直接对input进行赋值
    $("input.cartProductItemAmount").keyup(function () {
        var pid = $(this).attr("pid");
        var oiid = $(this).attr("oiid");
        //取得此商品的促销价
        var price = $("span.cartProductPromotePrice[pid=" + pid + "]").text();
        //取得此商品的库存量
        var stock = $("span.stock[pid=" + pid + "]").text();
        var num = $("input.cartProductItemAmount[pid=" + pid + "]").val();
        num = parseInt(num);
        if (isNaN(num)) {
            num = 1;
        }
        if (num <= 0) num = 1;
        if (num > stock) num = stock;

        calcSmallPrice(pid, price, num);
    })

    //更新数据库中订单项中商品购买数量

    function updateOrderItemNumber(oiid, number) {
        var page = "updateOrderItemNumberByAjax.do";
        $.post(
            page,
            {"oiid": oiid, "number": number},
            function (result) {
                if ("success" == result['msg']) {
                    alert("更新成功！！");
                } else {
                    alert("更新数量时出现未知错误！");
                }
            }
        );
    }

    //对input添加事件
    $("input.cartProductItemAmount").bind('inputpropertychange', function () {
        var number = $(this).val();
        var oiid = $(this).attr("oiid");
        console.log("nnumber:" + number);
        console.log("oiid:" + oiid);
        updateOrderItemNumber(oiid, number);
    });


    $("input.cartProductItemAmount").change(function () {
        var number = $(this).val();
        var oiid = $(this).attr("oiid");
        console.log("nnumber:" + number);
        console.log("oiid:" + oiid);
        updateOrderItemNumber(oiid, number);
    });

    //    //input中内容发生变化是触发事件
    //    //js改变input中的值怎么监听这个事件??
    //
    //    $("input.cartProductItemAmount").change(function () {
    //        var oiid = $(this).attr("oiid");
    //        //这里input中value取得是静态下的值，动态更新不会改变value的值
    //        var number = $(this).val();
    //        //alert(number);
    //        updateOrderItemNumber(oiid,number);
    //    })

    /*===========ajax删除指定订单项====================*/
    //删除选中商品
    $("td.deleteCartProduct a").click(function () {
        var oiid = $(this).attr("oiid");
        var pid = $(this).attr("pid");
        var now = $(this);
        //console.log(pid);
        var page = "deleteCartItemByAjax.do";
        $.get(
            page,
            {"orderItemId": oiid},
            function (result) {
                if ("success" == result['msg']) {
//                    now.parents("tr.cartProductItemTR").remove();
//                    syncSelectAll();
//                    syncOderButton();
//                    calcProductPriceAndNumber();
                    //直接重新载入.....突出不了ajax局部加载的优势。。。
                    location.reload();
                } else {
                    alert("删除出现未知错误！！");
                }
            }
        )

    })

    //删除勾选的商品(只有前台，没有后端的交互，所以是假的)这个以后进行完善
    $("a.deleteAllProduct").click(function () {
        var oiid = $(this).attr("oiid");
        //只要选中的就删除
        var selectAny = false;
        $("img.productIfSelect").each(function () {
            if ("selectit" == $(this).attr("selectit")) {
                $(this).parents("tr.cartProductItemTR").remove();
                selectAny = true;
            }
        });
        if (!selectAny) {
            alert("请选择宝贝");
        }

        syncSelectAll();
        syncOderButton();
        calcProductPriceAndNumber();

    });

    //点击结算按钮时触发的事件
    $("a.createOrderLink").click(function () {
        var param = "?";
        //获取总价格
        $("img.productIfSelect").each(function () {
            if ("selectit" == $(this).attr("selectit")) {
                var oiid = $(this).attr("oiid");
                param += "&oiid=" + oiid;
            }
        })

        param = param.substring(0,1)+param.substring(2,param.length);

        location.href = $("a.createOrderLink").attr("href") + param;

        //alert(param);

        return false;

    });


</script>


<%@include file="../include/footer.jsp" %>