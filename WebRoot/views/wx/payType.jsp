<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>
<%@ page language="java" import="com.yoxi.hudongtui.model.user.User" %>

<!DOCTYPE html>
<html>
<head>
<title>支付方式</title>
	<%@ include file="resource.jsp"%>
	<script type="text/javascript" src="${path }/js/md5util.js"></script>
	
</head>
<body>
	<div class="space_20"></div>
	<!-- 支付方式选择 开始 -->
	<div class="pay_method">
		<div class="norl_title">
			<h3>请选择支付方式</h3>
		</div>
		<dl>
			<dt>
				<p>您所购买的<b>${plugin.name }</b>插件</p>
				商品价格：<strong id="need_money">${plugin.price }</strong>元
			</dt>
			<dd>
				<label class="btn_virtual">
					<span>支付:<strong> </strong>元</span>
					<c:set var="repreCoinNoDot" value="${fn:substringBefore(user.repreCoin, '.')}" />
					<input type="checkbox" />代币（余额:${repreCoinNoDot }元）
				</label>
			</dd>
			<dd>
				<label class="btn_alipay">
					<span>支付:<strong> </strong>元</span>
					<input type="radio" name="payway" />支付宝支付
				</label>
			</dd>
			<dt><a class="btn_done">确认支付</a></dt>
		</dl>
		<div class="space_10"></div>
	</div>
	<!-- 支付方式选择 结束 -->
	<%@ include file="footer.jsp"%>
	<%@ include file="menu.jsp"%>
</body>
</html>
<script type="text/javascript">
		//支付方式选择
		//virtual_money：当前拥有代币数;need_money：需要的钱;
	function pay(virtual_money,need_money){
		if(virtual_money >= need_money){
			$('.btn_virtual input[type="checkbox"]').prop("checked",true);
			$('.btn_virtual').parents('dd').attr("class","active");
			$('.btn_virtual span strong').html(need_money);
			$('.btn_alipay span strong').html(0);

			$('.btn_alipay').change(function(){
				$('.btn_virtual input[type="checkbox"]').prop("checked",false);
				$('.btn_virtual').parents('dd').attr("class"," ");
				$('.btn_virtual span strong').html(0);

				$('.btn_alipay').parents('dd').attr("class","active");
				$('.btn_alipay span strong').html(need_money);
			});

			$('.btn_virtual').change(function(){
				if($('.btn_virtual input[type="checkbox"]').prop("checked") == false){
					$('.btn_virtual input[type="checkbox"]').prop("checked",true);
				}else{
					$('.btn_alipay input[type="radio"]').prop("checked",false);
					$('.btn_alipay').parents('dd').attr("class"," ");
					$('.btn_alipay span strong').html(0);

					$('.btn_virtual').parents('dd').attr("class","active");
					$('.btn_virtual span strong').html(need_money);
				}

			});

		}else if(virtual_money > 0 && virtual_money < need_money){
			$('.btn_virtual input[type="checkbox"]').prop("checked",true);
			$('.btn_virtual').parents('dd').attr("class","active");
			$('.btn_virtual span strong').html(virtual_money);

			$('.btn_alipay input[type="radio"]').prop("checked",true);
			$('.btn_alipay').parents('dd').attr("class","active");
			$('.btn_alipay span strong').html(need_money - virtual_money);


			$('.btn_virtual').change(function(){
				if($('.btn_virtual input[type="checkbox"]').prop("checked") == false){
					$('.btn_alipay input[type="radio"]').prop("checked",true);
					$('.btn_alipay').parents('dd').attr("class","active");
					$('.btn_alipay span strong').html(need_money);

					$('.btn_virtual').parents('dd').attr("class"," ");
					$('.btn_virtual span strong').html(0);
				}else{
					$('.btn_virtual input[type="checkbox"]').prop("checked",true);
					$('.btn_virtual').parents('dd').attr("class","active");
					$('.btn_virtual span strong').html(virtual_money);

					$('.btn_alipay input[type="radio"]').prop("checked",true);
					$('.btn_alipay').parents('dd').attr("class","active");
					$('.btn_alipay span strong').html(need_money - virtual_money);
				}

			});

		}else{
			$('.btn_alipay input[type="radio"]').prop("checked",true);
			$('.btn_alipay').parents('dd').attr("class","active");
			$('.btn_alipay span strong').html(need_money);
			$('.btn_virtual input[type="checkbox"]').prop("disabled","disabled");
			$('.btn_virtual span strong').html(0);

		}
	}
	
	
	
	$(document).ready(function(){
		
		pay(${user.repreCoin},${plugin.price});
		//返回状态
		$('.pay_method .btn_done').one("click",function(){
			var state = 0;
			$('.pay_method dd input').each(function(){
				if($(this).prop("checked")){
					if($(this).parent().attr("class") == "btn_virtual"){
						state += 1;
					}else if($(this).parent().attr("class") == "btn_wechat"){
						state += 2;
					}else{
						state += 4;
					}
				}
			});
			
		//	alert(state);
			
			var sourceType = "2"; 
			var authStr = getAuthStr('${user.userId}','${plugin.id}');
			
			if(state == 1){//代币支付
			/* 	alert("pluginId="+${plugin.id});
				alert("userId="+${user.userId});
				alert("sourceType="+sourceType); 
				alert("authStr="+authStr);*/
				
				$.ajax({
					type: "POST",
					url : "${path }/pay/pay/repreCoinPay",
					data:{productId:${plugin.id},
						 userId:${user.userId},
						 sourceType:sourceType,
						 authStr:authStr
						 },
					dataType: "json",
					success: function(data){
						if(data.code == '0'){
							window.location.href= '${basePath}wx/userPlugin/redircWxUserPluginUrl?scope=1';
						}
					}
				});
			}
			
			if(state == 4 || state == 5){
				$.ajax({
					type: "POST",
					url : "${path }/pay/pay/alipayWap",
					data:{productId:${plugin.id},
						  userId:${user.userId},
						  sourceType:sourceType,
						  authStr:authStr,
						  state:state
						 },
					dataType: "json",
					success: function(data){
						if(data.code == '0'){
							//alert("orderId="+data.orderId);
							window.location.href= '${basePath}pay/pay/toAlipayWapApi?sourceType=2&orderId='+data.orderId;
						}else{
							alert("系统错误，请稍后再试！");
						}
					}
				});
			}
			
			if(state == 2 || state == 3){
				
				
				$.ajax({
					type: "POST",
					url : "${path }/pay/pay/weixinPay",
					data:{productId:${plugin.id},
						  userId:${user.userId},
						  sourceType:sourceType,
						  authStr:authStr,
						  state:state
						 },
					dataType: "json",
					success: function(data){
						if(data.code == '0'){
						//	alert("orderId="+data.orderId);
							window.location.href = 'http://test.tchajian.com/pay/wxPay/jsPay?openId=${openId}&orderId='+data.orderId;
						//	window.location.href = 'http://192.168.2.104:8082/pay/wxPay/jsPay?openId=${openId}&orderId='+data.orderId;
						}else{
							alert("系统错误，请稍后再试！");
						}
					}
				});
			}
			
			/**
			*
				state:1(纯代币支付)
				state:2(纯微信支付)
				state:4(纯支付宝支付)
				state:3(代币+微信支付)
				state:5(代币+支付宝支付)
			*
			**/
		});
	});	
	
	//微信分享
	wx.config({
// 	    appId: '${shareArg.appId}',
// 	    timestamp: '${shareArg.timestamp}',
// 	    nonceStr: '${shareArg.nonceStr}',
// 	    signature: '${shareArg.signature}',
	    jsApiList: [
	        'wx.hideOptionMenu()'
	    ]
	});
	wx.ready(function () {
		wx.hideOptionMenu();
	});

</script>

