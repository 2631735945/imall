<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<%@ include file="header.jsp"%>
	<title>天天生鲜-商品详情</title>
	<link rel="stylesheet" type="text/css" href="/static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">

</head>
<body>
<%@ include file="top.jsp"%>

<div class="navbar_con">
	<div class="navbar clearfix">
		<div class="subnav_con fl">
			<h1>全部商品分类</h1>
			<span></span>
			<ul class="subnav">
				<li><a href="#" class="fruit">新鲜水果</a></li>
				<li><a href="#" class="seafood">海鲜水产</a></li>
				<li><a href="#" class="meet">猪牛羊肉</a></li>
				<li><a href="#" class="egg">禽类蛋品</a></li>
				<li><a href="#" class="vegetables">新鲜蔬菜</a></li>
				<li><a href="#" class="ice">速冻食品</a></li>
			</ul>
		</div>
		<ul class="navlist fl">
			<li><a href="">首页</a></li>
			<li class="interval">|</li>
			<li><a href="">手机生鲜</a></li>
			<li class="interval">|</li>
			<li><a href="">抽奖</a></li>
		</ul>
	</div>
</div>

<div class="breadcrumb">
	<a href="#">全部分类</a>
	<span>></span>
	<a href="#">新鲜水果</a>
	<span>></span>
	<a href="#">商品详情</a>
</div>

<div class="goods_detail_con clearfix">
	<div class="goods_detail_pic fl"><img style="width: 350px;height: 350px" src="${product.mainImageUrl}"></div>

	<div class="goods_detail_list fr">
		<h3>${product.name}</h3>
		<p>${product.subtitle}</p>
		<div class="prize_bar">
			<span class="show_pirze">¥<em>${product.price}</em></span>
			<span class="show_unit">单  位：500g</span>
		</div>
		<div class="goods_num clearfix">
			<div class="num_name fl">数 量：</div>
			<div class="num_add fl">
				<input type="text" class="num_show fl" id="quantity" value="1">
				<a href="javascript:;" class="add fr">+</a>
				<a href="javascript:;" class="minus fr">-</a>
			</div>
		</div>
		<div class="total">总价：<em>16.80元</em></div>
		<div class="operate_btn">
			<a href="javascript:;" class="buy_btn">立即购买</a>
			<a href="javascript:;" class="add_cart" id="add_cart">加入购物车</a>
		</div>
	</div>
</div>

<div class="main_wrap clearfix">
	<div class="l_wrap fl clearfix">
		<div class="new_goods">
			<h3>新品推荐</h3>
			<ul>
				<li>
					<a href="#"><img src="/static/images/goods/goods001.jpg"></a>
					<h4><a href="#">进口柠檬</a></h4>
					<div class="prize">￥3.90</div>
				</li>
				<li>
					<a href="#"><img src="/static/images/goods/goods002.jpg"></a>
					<h4><a href="#">玫瑰香葡萄</a></h4>
					<div class="prize">￥16.80</div>
				</li>
			</ul>
		</div>
	</div>

	<div class="r_wrap fr clearfix">
		<ul class="detail_tab clearfix">
			<li class="active">商品介绍</li>
			<li>评论</li>
		</ul>

		<div class="tab_content">
			<dl>
				<dt>商品详情：</dt>
				<dd>${product.detail}</dd>
			</dl>
		</div>

	</div>
</div>

<div class="footer">
	<div class="foot_link">
		<a href="#">关于我们</a>
		<span>|</span>
		<a href="#">联系我们</a>
		<span>|</span>
		<a href="#">招聘人才</a>
		<span>|</span>
		<a href="#">友情链接</a>
	</div>
	<p>CopyRight © 2016 北京天天生鲜信息技术有限公司 All Rights Reserved</p>
	<p>电话：010-****888    京ICP备*******8号</p>
</div>
<div class="add_jump"></div>

<%--要弹出的登录界面--%>
<div id="lgoinForm" style="display: none" class="login_form fr">
	<div class="login_title clearfix">
		<h1>用户登录</h1>
		<a href="#">立即注册</a>
	</div>
	<div class="form_input">
		<form id="formId">
			<input type="text" name="username" class="name_input" placeholder="请输入用户名">
			<div class="user_error">输入错误</div>
			<input type="password" name="password" class="pass_input" placeholder="请输入密码">
			<div class="pwd_error">输入错误</div>
			<div class="more_input clearfix">
				<input type="checkbox" name="">
				<label>记住用户名</label>
				<a href="#">忘记密码</a>
			</div>
			<input type="button" onclick="submitForm()" name="" value="登录" class="input_submit">
		</form>
	</div>
</div>

<script type="text/javascript" src="js/jquery-1.12.2.js"></script>
<script type="text/javascript">
	$('#add_cart').click(function() {
		// 加入购物车之前首先判断有没有登录
		// 如果没有登录首先完成登录
		// 如果已经登录，就将商品加入cart这个购物车表里面，然后跳转到购物车列表界面
		$.post(
				'/user/checkUserLogin',
				function(jsonResult) {
					// getOk() 魔法数字
					if (jsonResult.ok) {
						// 已经登录，直接加入购物车
						insertToCart();
					} else {
						layer.open({
							type : 1,
							title : '登录',
							area : ['370px', '480px'],
							content: $('#lgoinForm')
						});
					}
				},
				'json'
		);
	});

	function submitForm() {
		// {'name':'zhansgan', 'age':23}
		// console.log($('#formId').serialize());
		$.post(
				'/user/login',
				$('#formId').serialize(),
				function(jsonResult) {
					console.log(jsonResult);
					if (jsonResult.ok) {
						// mylayer.okUrl(jsonResult.msg, '/cart/getCartListPage')
						insertToCart();
					} else {
						mylayer.errorMsg(jsonResult.msg);
					}
				},
				'json'
		);
	}

	function insertToCart() {
		$.post(
				'/cart/add',
				{'productId':'${product.id}','quantity':$('#quantity').val()},
				function (jsonResult) {
					if (jsonResult.ok) {
						mylayer.okUrl(jsonResult.msg, '/cart/getCartListPage')
					} else {
						mylayer.errorMsg(jsonResult.msg);
					}
				},
				'json'
		)
	}


	var $add_x = $('#add_cart').offset().top;
	var $add_y = $('#add_cart').offset().left;

	var $to_x = $('#show_count').offset().top;
	var $to_y = $('#show_count').offset().left;

	$(".add_jump").css({'left':$add_y+80,'top':$add_x+10,'display':'block'})
	$('#add_cart').click(function(){
		$(".add_jump").stop().animate({
					'left': $to_y+7,
					'top': $to_x+7},
				"fast", function() {
					$(".add_jump").fadeOut('fast',function(){
						$('#show_count').html(2);
					});

				});
	})
</script>

</body>
</html>