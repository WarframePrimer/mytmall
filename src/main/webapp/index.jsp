<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>tmallDemo</title>
    <!--BootStrap3-->
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <link href="css/front/tmallDemo.css" rel="stylesheet"/>
</head>
<body>


<!--导航栏-->
<nav class="header">

    <div class="header-content">
        <a href="#"><span class="glyphicon glyphicon-home" style="color: #c40000"></span>天猫首页</a>
        <span>喵，欢迎来到天猫</span>
        <a href="#">请登录</a>
        <a href="#">免费注册</a>

        <span class="pull-right">
                <a href="#">我的订单</a>
                <span class="glyphicon glyphicon-shopping-cart" style="color: #c40000"></span>
                购物车<strong style="color: #000">0</strong>件

        </span>

    </div>
    <button class="btn btn-success btn-xs linkBack" style="position: absolute;top:3px; "
            onclick="javascrtpt:window.location.href='http://localhost:8080/tmall/admin/category/admin_category_list.do'">
        进入后台
    </button>
</nav>

<!--搜索框-->
<div>
    <img src="img/site/logo.gif" alt="图片加载失败" class="logo">
    <div class="searchDiv">
        <input type="text" placeholder="搜索内容">
        <button type="submit">搜索</button>
        <div class="searchBelow">
            <a href="#">衬衫男</a><span>|</span>
            <a href="#">连衣裙</a><span>|</span>
            <a href="#">四件套</a><span>|</span>
            <a href="#">笔记本电脑</a>

        </div>
    </div>
</div>


<!--轮播图-->
<!--categoryWithCarousel设置了相对定位-->
<div class="categoryWithCarousel">
    <div class="headbar">
        <div class="head">
            <span class="glyphicon glyphicon-th-list"></span>
            <span>商品分类</span>
        </div>


        <img src="img/site/catear.png" alt="图片加载失败" style="position:absolute;display: none;height: 15px; "
             class="carear" id="catear">

        <div class="rightMenu">
            <span><a href="#"><img src="img/site/chaoshi.png" alt="图片加载失败"></a></span>
            <span><a href="#"><img src="img/site/guoji.png" alt="图片加载失败"></a></span>
            <span><a href="#">平板电视</a></span>
            <span><a href="#">电热水器</a></span>
        </div>
    </div>

    <!--分类列表展示-->
    <div style="position: relative">
        <div class="categoryMenu">
            <div class="eachCategory" cid="1">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#">平板电视</a>
            </div>
            <div class="eachCategory" cid="2">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#">马桶</a>
            </div>
            <div class="eachCategory" cid="3">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#">沙发</a>
            </div>
            <div class="eachCategory">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#">电热水器</a>
            </div>
            <div class="eachCategory">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#">平衡车</a>
            </div>
            <div class="eachCategory" cid="78">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#nowhere">
                    扫地机器人
                </a>
            </div>
            <div class="eachCategory" cid="77">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#nowhere">
                    原汁机
                </a>
            </div>
            <div class="eachCategory" cid="76">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#nowhere">
                    冰箱
                </a>
            </div>
            <div class="eachCategory" cid="75">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#nowhere">
                    空调
                </a>
            </div>
            <div class="eachCategory" cid="74">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#nowhere">
                    女表
                </a>
            </div>
            <div class="eachCategory" cid="73">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#nowhere">
                    男表
                </a>
            </div>
            <div class="eachCategory" cid="72">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#nowhere">
                    男士手拿包
                </a>
            </div>
            <div class="eachCategory" cid="71">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#nowhere">
                    男士西服
                </a>
            </div>
            <div class="eachCategory" cid="69">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#nowhere">
                    时尚男鞋
                </a>
            </div>
            <div class="eachCategory" cid="68">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#nowhere">
                    品牌女装
                </a>
            </div>
            <div class="eachCategory" cid="64">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#nowhere">
                    太阳镜
                </a>
            </div>
            <div class="eachCategory" cid="60">
                <span class="glyphicon glyphicon-link"></span>
                <a href="#nowhere">
                    安全座椅
                </a>
            </div>
        </div>
    </div>


    <div style="position:relative;top: 0;left: 0">
        <div class="productAsideCategory" cid="1">
            <div class="row ">
                <a href="#nowhere">
                    屏大影院
                </a>
                <a href="#nowhere">
                    周末
                </a>
                <a href="#nowhere">
                    新品特惠
                </a>
                <a href="#nowhere">
                    32吋电视机
                </a>
                <a href="#nowhere">
                    智能网络
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    USB高清解
                </a>
                <a href="#nowhere">
                    芒果TV在线
                </a>
                <a href="#nowhere">
                    抢购价
                </a>
                <a href="#nowhere">
                    USB解码
                </a>
                <a href="#nowhere">
                    32英吋
                </a>
                <a href="#nowhere">
                    10核
                </a>
                <a href="#nowhere">
                    TCL品牌日
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    不要赠品
                </a>
                <a href="#nowhere">
                    新品上市
                </a>
                <a href="#nowhere">
                    4K硬屏
                </a>
                <a href="#nowhere">
                    领100元券
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    智能高清
                </a>
                <a href="#nowhere">
                    8月，酷暑
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    8月大促
                </a>
                <a href="#nowhere">
                    天猫定制
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    智能操作系统
                </a>
                <a href="#nowhere">
                    金色外观
                </a>
                <a href="#nowhere">
                    三星屏幕
                </a>
                <a href="#nowhere">
                    客厅爆款
                </a>
                <a href="#nowhere">
                    八核配置
                </a>
                <a href="#nowhere">
                    限时特惠
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    限时特惠
                </a>
                <a href="#nowhere">
                    热销爆款
                </a>
                <a href="#nowhere">
                    4K全高清
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    六核智能
                </a>
                <a href="#nowhere">
                    14核4K
                </a>
                <a href="#nowhere">
                    YUNOS
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    YUNOS
                </a>
                <a href="#nowhere">
                    64位处理器
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    YUNOS
                </a>
                <a href="#nowhere">
                    微信电视
                </a>
                <a href="#nowhere">
                    4k超清
                </a>
                <a href="#nowhere">
                    64位真4K
                </a>
                <a href="#nowhere">
                    10核机芯
                </a>
                <a href="#nowhere">
                    V字黑釉底座
                </a>
                <a href="#nowhere">
                    4K超清
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    64位14核
                </a>
                <a href="#nowhere">
                    海量影视
                </a>
                <a href="#nowhere">
                    人气爆款
                </a>
                <a href="#nowhere">
                    限时特惠
                </a>
                <a href="#nowhere">
                    真4K屏
                </a>
                <a href="#nowhere">
                    65吋巨屏
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    4K超清视界
                </a>
                <a href="#nowhere">
                    限时特惠
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    55寸旗舰
                </a>
                <a href="#nowhere">
                    4K机皇
                </a>
                <a href="#nowhere">
                    曲面机皇
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    轻薄4K
                </a>
                <div class="seperator"></div>
            </div>
        </div>
    </div>
    <div style="position:relative;top: 0;left: 0">
        <div class="productAsideCategory" cid="2">
            <div class="row ">
                <a href="#nowhere">
                    希尔瓦娜斯
                </a>
                <a href="#nowhere">
                    魔兽世界
                </a>
                <a href="#nowhere">
                    新品特惠
                </a>
                <a href="#nowhere">
                    32吋电视机
                </a>
                <a href="#nowhere">
                    智能网络
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    USB高清解
                </a>
                <a href="#nowhere">
                    芒果TV在线
                </a>
                <a href="#nowhere">
                    抢购价
                </a>
                <a href="#nowhere">
                    USB解码
                </a>
                <a href="#nowhere">
                    32英吋
                </a>
                <a href="#nowhere">
                    10核
                </a>
                <a href="#nowhere">
                    TCL品牌日
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    不要赠品
                </a>
                <a href="#nowhere">
                    新品上市
                </a>
                <a href="#nowhere">
                    4K硬屏
                </a>
                <a href="#nowhere">
                    领100元券
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    智能高清
                </a>
                <a href="#nowhere">
                    8月，酷暑
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    8月大促
                </a>
                <a href="#nowhere">
                    天猫定制
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    智能操作系统
                </a>
                <a href="#nowhere">
                    金色外观
                </a>
                <a href="#nowhere">
                    三星屏幕
                </a>
                <a href="#nowhere">
                    客厅爆款
                </a>
                <a href="#nowhere">
                    八核配置
                </a>
                <a href="#nowhere">
                    限时特惠
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    限时特惠
                </a>
                <a href="#nowhere">
                    热销爆款
                </a>
                <a href="#nowhere">
                    4K全高清
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    六核智能
                </a>
                <a href="#nowhere">
                    14核4K
                </a>
                <a href="#nowhere">
                    YUNOS
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    YUNOS
                </a>
                <a href="#nowhere">
                    64位处理器
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    YUNOS
                </a>
                <a href="#nowhere">
                    微信电视
                </a>
                <a href="#nowhere">
                    4k超清
                </a>
                <a href="#nowhere">
                    64位真4K
                </a>
                <a href="#nowhere">
                    10核机芯
                </a>
                <a href="#nowhere">
                    V字黑釉底座
                </a>
                <a href="#nowhere">
                    4K超清
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    64位14核
                </a>
                <a href="#nowhere">
                    海量影视
                </a>
                <a href="#nowhere">
                    人气爆款
                </a>
                <a href="#nowhere">
                    限时特惠
                </a>
                <a href="#nowhere">
                    真4K屏
                </a>
                <a href="#nowhere">
                    65吋巨屏
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    4K超清视界
                </a>
                <a href="#nowhere">
                    限时特惠
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    55寸旗舰
                </a>
                <a href="#nowhere">
                    4K机皇
                </a>
                <a href="#nowhere">
                    曲面机皇
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    轻薄4K
                </a>
                <div class="seperator"></div>
            </div>
        </div>
    </div>
    <div style="position:relative;top: 0;left: 0">
        <div class="productAsideCategory" cid="3">
            <div class="row ">
                <a href="#nowhere">
                    星际争霸
                </a>
                <a href="#nowhere">
                    守望先锋
                </a>
                <a href="#nowhere">
                    新品特惠
                </a>
                <a href="#nowhere">
                    32吋电视机
                </a>
                <a href="#nowhere">
                    智能网络
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    USB高清解
                </a>
                <a href="#nowhere">
                    芒果TV在线
                </a>
                <a href="#nowhere">
                    抢购价
                </a>
                <a href="#nowhere">
                    USB解码
                </a>
                <a href="#nowhere">
                    32英吋
                </a>
                <a href="#nowhere">
                    10核
                </a>
                <a href="#nowhere">
                    TCL品牌日
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    不要赠品
                </a>
                <a href="#nowhere">
                    新品上市
                </a>
                <a href="#nowhere">
                    4K硬屏
                </a>
                <a href="#nowhere">
                    领100元券
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    智能高清
                </a>
                <a href="#nowhere">
                    8月，酷暑
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    8月大促
                </a>
                <a href="#nowhere">
                    天猫定制
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    智能操作系统
                </a>
                <a href="#nowhere">
                    金色外观
                </a>
                <a href="#nowhere">
                    三星屏幕
                </a>
                <a href="#nowhere">
                    客厅爆款
                </a>
                <a href="#nowhere">
                    八核配置
                </a>
                <a href="#nowhere">
                    限时特惠
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    限时特惠
                </a>
                <a href="#nowhere">
                    热销爆款
                </a>
                <a href="#nowhere">
                    4K全高清
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    六核智能
                </a>
                <a href="#nowhere">
                    14核4K
                </a>
                <a href="#nowhere">
                    YUNOS
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    YUNOS
                </a>
                <a href="#nowhere">
                    64位处理器
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    YUNOS
                </a>
                <a href="#nowhere">
                    微信电视
                </a>
                <a href="#nowhere">
                    4k超清
                </a>
                <a href="#nowhere">
                    64位真4K
                </a>
                <a href="#nowhere">
                    10核机芯
                </a>
                <a href="#nowhere">
                    V字黑釉底座
                </a>
                <a href="#nowhere">
                    4K超清
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    64位14核
                </a>
                <a href="#nowhere">
                    海量影视
                </a>
                <a href="#nowhere">
                    人气爆款
                </a>
                <a href="#nowhere">
                    限时特惠
                </a>
                <a href="#nowhere">
                    真4K屏
                </a>
                <a href="#nowhere">
                    65吋巨屏
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    4K超清视界
                </a>
                <a href="#nowhere">
                    限时特惠
                </a>
                <div class="seperator"></div>
            </div>
            <div class="row ">
                <a href="#nowhere">
                    55寸旗舰
                </a>
                <a href="#nowhere">
                    4K机皇
                </a>
                <a href="#nowhere">
                    曲面机皇
                </a>
                <a href="#nowhere" style="color: rgb(135, 206, 250);">
                    轻薄4K
                </a>
                <div class="seperator"></div>
            </div>
        </div>
    </div>


    <!--轮播-->
    <div data-ride="carousel" class="carousel-of-product carousel slide" id="carousel-of-product">
        <!--indicators-->
        <ol class="carousel-indicators">
            <li class="active" data-slide-to="0" data-target="#carousel-of-product"></li>
            <li data-slide-to="1" data-target="#carousel-of-product"></li>
            <li data-slide-to="2" data-target="#carousel-of-product"></li>
            <li data-slide-to="3" data-target="#carousel-of-product"></li>
        </ol>

        <!--wrapper for slide-->
        <div class="carousel-inner">
            <div class="item active"><a href="#"><img src="img/site/lunbo/lunbo/1.jpg" alt="图片加载失败"></a></div>
            <div class="item"><a href="#"><img src="img/site/lunbo/lunbo/2.jpg" alt="图片加载失败"></a></div>
            <div class="item"><a href="#"><img src="img/site/lunbo/lunbo/3.jpg" alt="图片加载失败"></a></div>
            <div class="item"><a href="#"><img src="img/site/lunbo/lunbo/4.jpg" alt="图片加载失败"></a></div>
        </div>
    </div>


</div>
<div class="carouselBackgroundDiv"></div>


<!--分类商品信息列表-->
<!--首页的分类商品列表信息-->
<div class="homepageCategoryProduct">
    <!--每一种分类的商品列表信息-->
    <div class="eachHomepageCategoryProducts">
        <div class="left-mark"></div>
        <span class="categoryTitle">太阳镜</span>
        <br>
        <!--相应分类下的每一个产品信息-->
        <div class="productItem">
            <a href="#">
                <img width="100px" src="img/site/productSingle_middle/9543.jpg" alt="图片加载失败">
            </a>
            <a href="#" class="productItemDescLink"><span
                    class="productItemDesc">[热销]好先生同款墨镜孙红雷偏光男士太阳镜韩明星</span>
            </a>
            <span class="productPrice">97.50</span>
        </div>
        <div class="productItem">
            <a href="#">
                <img width="100px" src="img/site/productSingle_middle/9521.jpg" alt="图片加载失败">
            </a>
            <a href="#" class="productItemDescLink"><span
                    class="productItemDesc">[热销]帕莎Prsr太阳镜女偏光镜潮范冰冰同款女</span>
            </a>
            <span class="productPrice">518.70</span>
        </div>
        <div class="productItem">
            <a href="#">
                <img width="100px" src="img/site/productSingle_middle/9510.jpg" alt="图片加载失败">
            </a>
            <a href="#" class="productItemDescLink"><span
                    class="productItemDesc">[热销]变色眼镜男女款半框太阳镜大框潮流防辐射防</span>
            </a>
            <span class="productPrice">624.00</span>
        </div>
        <div class="productItem">
            <a href="#">
                <img width="100px" src="img/site/productSingle_middle/9499.jpg" alt="图片加载失败">
            </a>
            <a href="#" class="productItemDescLink"><span
                    class="productItemDesc">[热销]新款男士偏光太阳镜日夜两用墨镜潮运动开车</span>
            </a>
            <span class="productPrice">551.00</span>
        </div>
        <div class="productItem">
            <a href="#">
                <img width="100px" src="img/site/productSingle_middle/9532.jpg" alt="图片加载失败">
            </a>
            <a href="#" class="productItemDescLink"><span
                    class="productItemDesc">[热销]陌森太阳眼镜男女2016偏光定制驾驶近视</span>
            </a>
            <span class="productPrice">1024.00</span>
        </div>
        <div style="clear:both"></div>
    </div>
    <div class="eachHomepageCategoryProducts">
        <div class="left-mark"></div>
        <span class="categoryTitle">安全座椅</span>
        <br>
        <div class="productItem">
            <a href="#nowhere"><img width="100px" src="img/site/productSingle_middle/10192.jpg"></a>
            <a href="#nowhere" class="productItemDescLink">
                            <span class="productItemDesc">[热销]
                            新生儿婴儿提篮式安全座椅汽车用车载儿童安
                            </span>
            </a>
            <span class="productPrice">
                            882.00
                        </span>
        </div>
        <div class="productItem">
            <a href="#nowhere"><img width="100px" src="img/site/productSingle_middle/10181.jpg"></a>
            <a href="#nowhere" class="productItemDescLink">
                            <span class="productItemDesc">[热销]
                            REEBABY汽车儿童安全座椅ISOFI
                            </span>
            </a>
            <span class="productPrice">
                            1,344.00
                        </span>
        </div>
        <div class="productItem">
            <a href="#nowhere"><img width="100px" src="img/site/productSingle_middle/10170.jpg"></a>
            <a href="#nowhere" class="productItemDescLink">
                            <span class="productItemDesc">[热销]
                            REEBABY儿童安全座椅9个月-12岁
                            </span>
            </a>
            <span class="productPrice">
                            1,216.00
                        </span>
        </div>
        <div class="productItem">
            <a href="#nowhere"><img width="100px" src="img/site/productSingle_middle/10159.jpg"></a>
            <a href="#nowhere" class="productItemDescLink">
                            <span class="productItemDesc">[热销]
                            好孩子汽车儿童安全座椅goodbaby9
                            </span>
            </a>
            <span class="productPrice">
                            1,199.40
                        </span>
        </div>
        <div class="productItem">
            <a href="#nowhere"><img width="100px" src="img/site/productSingle_middle/10148.jpg"></a>
            <a href="#nowhere" class="productItemDescLink">
                            <span class="productItemDesc">[热销]
                            惠尔顿儿童安全座椅isofix硬接口汽车
                            </span>
            </a>
            <span class="productPrice">
                            1,993.60
                        </span>
        </div>
        <div style="clear:both"></div>
    </div>

</div>


<!--存放页脚-->
<div id="footer" class="container">

    <!--页脚1-->
    <div class="footer1">
        <!--商家承诺-->
        <div class="footer1_ensure">
            <a href="#"><img src="img/site/ensure.png" alt="图片加载失败"></a>
        </div>
        <!--具体信息-->
        <div class="footer1_desc">
            <div class="descColumn">
                <span class="desc_title">购物指南</span>
                <a href="#">免费注册</a>
                <a href="#">开通支付宝</a>
                <a href="#">支付宝充值</a>
            </div>
            <div class="descColumn">
                <span class="desc_title">天猫保障</span>
                <a href="#">发票保障</a>
                <a href="#">售后规则</a>
                <a href="#">缺货赔付</a>
            </div>
            <div class="descColumn">
                <span class="desc_title">支付方式</span>
                <a href="#">快捷支付</a>
                <a href="#">信用卡</a>
                <a href="#">余额宝</a>
                <a href="#">蚂蚁花呗</a>
                <a href="#">货到付款</a>
            </div>
            <div class="descColumn">
                <span class="desc_title">商家服务</span>
                <a href="#">商家入驻</a>
                <a href="#">商家中心</a>
                <a href="#">天猫智库</a>
                <a href="#">天猫规则</a>
                <a href="#">物流服务</a>
                <a href="#">喵言喵语</a>
                <a href="#">运营服务</a>
            </div>
            <div class="descColumn">
                <span class="desc_title">手机天猫</span>
                <a href="#"><img src="img/site/ma.png" alt="图片加载失败"></a>
            </div>
        </div>
    </div>


    <!--页脚二-->
    <div class="footer2">
        <div class="footer2_middle">
            <img src="img/site/cateye.png" alt="图片加载失败" class="cateye">
            <div class="copyright" id="copyright">
                <div class="white_link">
                    <a href="#">关于天猫</a>
                    <a href="#">关于天猫</a>
                    <a href="#">关于天猫</a>
                    <a href="#">关于天猫</a>
                    <a href="#">关于天猫</a>
                    <a href="#">关于天猫</a>
                    <a href="#">关于天猫</a>
                    <a href="#">关于天猫</a>
                    <a href="#">关于天猫</a>
                </div>
                <div class="white_link"><a href="#">阿里旅行</a><span>|</span><a href="#">阿里旅行</a><span>|</span><a href="#">阿里旅行</a><span>|</span><a
                        href="#">阿里旅行</a><span>|</span><a
                        href="#">阿里旅行</a><span>|</span><a href="#">阿里旅行</a><span>|</span><a
                        href="#">阿里旅行</a><span>|</span><a href="#">阿里旅行</a><span>|</span><a
                        href="#">阿里旅行</a><span>|</span><a
                        href="#">阿里旅行</a><span>|</span><a href="#">阿里旅行</a><span>|</span><a
                        href="#">阿里旅行</a><span>|</span><a href="#">阿里旅行</a><span>|</span><a
                        href="#">阿里旅行</a><span>|</span><a
                        href="#">阿里旅行</a>
                </div>
                <div class="license">
                    <span>增值电信业务经营许可证： 浙B2-20110446</span>
                    <span>网络文化经营许可证：浙网文[2015]0295-065号</span>
                    <span>互联网医疗保健信息服务 审核同意书 浙卫网审【2014】6号 </span>
                    <span>互联网药品信息服务资质证书编号：浙-（经营性）-2012-0005</span>
                    <div class="copyRightYear">© 2003-2016 TMALL.COM 版权所有</div>
                    <div>
                        <img src="img/site/copyRight1.jpg" alt="图片加载失败">
                        <img src="img/site/copyRight2.jpg" alt="图片加载失败">
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>


<!--交互-->
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

    $("div.eachCategory").mouseenter(function () {
        var cid = $(this).attr("cid");
        showAsideCategryInfo(cid);
    });
    $("div.eachCategory").mouseleave(function () {
        var cid = $(this).attr("cid");
        hideAsideCategryInfo(cid);
    });

</script>

</body>
</html>
