<%--
  Created by IntelliJ IDEA.
  User: 86156
  Date: 2022/8/30
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="header_con">
    <div class="header">
        <div class="welcome fl">欢迎来到天天生鲜!</div>
        <div class="fr">
            <div class="login_info fl">
                欢迎您：<em>张 山</em>
            </div>
            <div class="login_btn fl">
                <a href="login.html">登录</a>
                <span>|</span>
                <a href="register.html">注册</a>
            </div>
            <div class="user_link fl">
                <span>|</span>
                <a href="/order/getOrderListPage">用户中心</a>
                <span>|</span>
                <a href="cart.html">我的购物车</a>
                <span>|</span>
                <a href="/order/getOrderListPage">我的订单</a>
            </div>
        </div>
    </div>
</div>

<div class="search_bar clearfix">
    <a href="/" class="logo fl"><img src="/static/images/logo.png"></a>
    <div class="search_con fl">
        <input type="text" class="input_text fl" name="" placeholder="搜索商品">
        <input type="button" class="input_btn fr" name="" value="搜索">
    </div>
    <div class="guest_cart fr">
        <a href="/cart/getCartListPage" class="cart_name fl">我的购物车</a>
        <div class="goods_count fl" id="show_count">1</div>
    </div>
</div>

</body>
</html>
