<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,user-scalable=no">
<title>帮助中心</title>
<link rel="stylesheet" href="${path }/css/wx/layout.css">
<script type="text/javascript" src="${path }/js/wx/zepto.js"></script>
<script type="text/javascript" src="${path }/js/wx/touchslide.js"></script>
<script type="text/javascript" src="${path }/js/wx/layout.js"></script>
</head>
<body>
	<div class="space_20"></div>
	<!-- 帮助中心 开始 -->
	<div class="help_center">
		<div class="title">关于我们</div>
		<div class="text">
			<p>有戏（厦门）网络科技有限公司推出的淘插件，是一个基于SaaS模式的第三方服务交易平台，专注社会化营销推广领域，汇集众多业内优秀开发者，为商家提供最具创意的营销推广插件，如微活动、微游戏等。</p>
			<p>平台支持插件免费试用，在线购买，个性配置，让营销推广更简单，更灵活，更实用。</p>
			<h3>有以下特点优势：</h3>
			<p><em>•</em>基本涵盖市面上所有热门的营销推广活动游戏，并在此基础上做了优化改进，如大转盘、砸金蛋、刮刮卡、优惠券等。</p>
			<p><em>•</em>紧追时下热点，在各假节日之前及时推出最新最炫的相关插件，成功捕获客户眼球，如七夕索吻、中秋拆礼包、圣诞送礼物等。</p>
			<p><em>•</em>无需绑定微信公众号，或其他平台账号，无论是订阅号，服务号，还是没有公众号的商家或个人都可使用，且与微盟等其他平台提供的服务不冲突。</p>
			<p><em>•</em>每款插件单独销售，部分免费，价格实惠，先试用后购买，配置完直接使用，营销推广成本低，大品牌使用的插件，普通商家也可拥有。</p>
			<p><em>•</em>统一管理平台，方便活动的发布与运营，另外可参考其他商家正在进行的活动方案。</p>
		</div>
	</div>
	<div class="space_10"></div>
	<div class="help_center">
		<div class="title">联系我们</div>
		<div class="text">
			<p>客服电话：0592-7127103</p>
			<p>客服QQ：209166172</p>
			<p>粉丝QQ群：53749373</p>
			<p>邮箱：cs@tchajian.com</p>
			<p>地址：福建省厦门市湖里区万达广场SOHO公寓D1栋3楼</p>
		</div>
	</div>
	<!-- 帮助中心 结束 -->
	<div class="space_20"></div>
</body>
</html>
<script type="text/javascript">
	$(document).ready(function(){
		$('.help_center .title').click(function(){
			$(this).next().show(200);
		});
	});
</script>
