<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<%@ include file="header.jsp"%>
	<title>天天生鲜-提交订单</title>
	<link rel="stylesheet" type="text/css" href="/static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
</head>
<body>

<%@ include file="top.jsp"%>

<h3 class="common_title">确认收货地址</h3>

<div class="common_list_con clearfix">
	<dl>
		<dt>寄送到：</dt>
		<c:forEach items="${shippingList}" var="shipping">
			<dd><input type="radio" name="shippingId" value="${shipping.id}" >${shipping.receiverProvince}-${shipping.receiverCity}-${shipping.receiverDistrict} （ ${shipping.receiverName}收） ${shipping.receiverPhone}</dd>
		</c:forEach>
	</dl>
	<a href="user_center_site.html" class="edit_site">编辑收货地址</a>
</div>

<h3 class="common_title">支付方式</h3>
<div class="common_list_con clearfix">
	<div class="pay_style_con clearfix">
		<input type="radio" name="paymentType" value="0" checked>
		<label class="cash">货到付款</label>
		<input type="radio" name="paymentType" value="1">
		<label class="weixin">微信支付</label>
		<input type="radio" name="paymentType" value="2">
		<label class="zhifubao"></label>
		<input type="radio" name="paymentType" value="3">
		<label class="bank">银行卡支付</label>
	</div>
</div>

<h3 class="common_title">商品列表</h3>

<div class="common_list_con clearfix">
	<ul class="goods_list_th clearfix">
		<li class="col01">商品名称</li>
		<li class="col02">商品单位</li>
		<li class="col03">商品价格</li>
		<li class="col04">数量</li>
		<li class="col05">小计</li>
	</ul>
	<c:forEach items="${cartVOList}" var="cartVO">
		<ul class="goods_list_td clearfix">
			<li class="col01">1</li>
			<li class="col02"><img src="${cartVO.mainImageUrl}"></li>
			<li class="col03">${cartVO.productName}</li>
			<li class="col04">500g</li>
			<li class="col05">${cartVO.productPrice}元</li>
			<li class="col06">${cartVO.quantity}</li>
			<li class="col07">${cartVO.productPrice*cartVO.quantity}元</li>
		</ul>
	</c:forEach>

</div>

<h3 class="common_title">总金额结算</h3>

<div class="common_list_con clearfix">
	<div class="settle_con">
		<div class="total_goods_count">共<em>2</em>件商品，总金额<b>42.60元</b></div>
		<div class="transit">运费：<b>10元</b></div>
		<div class="total_pay">实付款：<b>52.60元</b></div>
	</div>
</div>

<div class="order_submit clearfix">
	<a href="javascript:;" id="order_btn">提交订单</a>
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

<div class="popup_con">
	<div class="popup">
		<p>订单提交成功！</p>
	</div>

	<div class="mask"></div>
</div>
<script type="text/javascript" src="js/jquery-1.12.2.js"></script>
<script type="text/javascript">
	$('#order_btn').click(function() {
		$.post(
				'/order/add',
				{'shippingId':$('input[name="shippingId"]:checked').val(), 'paymentType':$('input[name="paymentType"]:checked').val()},
				function(jsonResult) {
					if (jsonResult.ok) {
						mylayer.okUrl(jsonResult.msg, '/order/getOrderListPage');
					} else {
						mylayer.errorMsg(jsonResult.msg);
					}
				},
				'json'
		);

	});
</script>
</body>
</html>