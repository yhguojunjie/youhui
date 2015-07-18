<%@ page language="java" pageEncoding="UTF-8"%>

<!-- 左侧内容 开始 -->
<div class="fl w300 side">
	<div class="usermsg_subnav">
		<div class="space_15"></div>
		<div class="space_30"></div>
		<div class="user_card">
			<a href="${url_myPage }" target="_blank" class="avatar"><img src="${sessionScope.SESSION_USER.headimgUrl}" /></a>
			<h3>
				<a href="${url_myPage }" target="_blank" class="fl" >${sessionScope.SESSION_USER.nickName}</a>
			</h3>
			<c:choose>
				<c:when test="${sessionScope.SESSION_USER.introduce != null && sessionScope.SESSION_USER.introduce != '' }">
					<p>${sessionScope.SESSION_USER.introduce}</p>
				</c:when>
				<c:otherwise>
					<p>还没简介</p>
				</c:otherwise>
			</c:choose>
			
			<div class="clear"></div>
			<ul>
				<li><i class="ico_gold"> <!-- 图标 -->
				</i> 代币：<em>
				<c:set var="repreCoinNoDot" value="${fn:substringBefore(sessionScope.SESSION_USER.repreCoin, '.')}" />
					${repreCoinNoDot}
					</em>
				</li>
				<li><a href="javascript:alert('敬请期待!');">赚代币</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="subnav">
			<ul>
				<li><a href="${url_myact }">我的活动</a></li>
				<li><a href="${url_orders }">我的订单</a></li>
				<li><a href="${url_userEdit }">账号设置</a></li>
				<li><a target="_blank" href="${url_myPage }">个人主页</a></li>
			</ul>
		</div>
	</div>
</div>
<!-- 左侧内容 结束 -->
<!-- 支付弹出对话框 开始 -->
<div class="pay_box">
	<form>
		<h3>
			<strong>确认支付</strong> <span class="close">
				<!-- 关闭窗口按钮 -->╳
			</span>
		</h3>
		<h4>消费：￥100</h4>
		<div class="row">
			<label><input type="checkbox" />使用代币：￥200</label> <a class="paycash">赚代币</a>
		</div>
		<div class="clear space_10"></div>
		<div class="row">
			<a class="btn_cancel">取消</a> <input type="submit" value="支付" />
		</div>
		<div class="clear"></div>
	</form>
	<div class="bg"></div>
</div>
<!-- 支付弹出对话框 结束 -->
<!-- 赚代币弹出对话框 开始 -->
<div class="earn_box">
	<dl>
		<dt>代币可以代替现金在淘插件平台上购买插件和服务。</dt>
		<dt>可通过以下方式获得代币：</dt>
		<dd class="space_20">
			1、分享插件库到微信朋友圈，10个代币。<a href="#">去分享</a>
		</dd>
		<dd>
			2、个人主页朋友数突破10，20个代币。<a href="#">邀好友</a>
		</dd>
		<dd class="tc space_20">
			<a class="btn_cancel">知道了</a>
		</dd>
	</dl>
	<div class="bg"></div>
</div>
<!-- 赚代币弹出对话框 结束 -->
<script type="text/javascript">
$(document).ready(function(){
	var url_left = self.location + '';
	if(url_left.indexOf("my/actList") != -1){
		$('.subnav a').attr("class","");
		$('.subnav a').eq(0).attr("class","active");
	}else if(url_left.indexOf("my/orderList") != -1){
		$('.subnav a').attr("class","");
		$('.subnav a').eq(1).attr("class","active");
	}else if(url_left.indexOf("/user/userEdit") != -1){
		$('.subnav a').attr("class","");
		$('.subnav a').eq(2).attr("class","active");
	}else if(url_left.indexOf("/user/myPage") != -1){
		$('.subnav a').attr("class","");
		$('.subnav a').eq(3).attr("class","active");
	}else{
		$('.subnav a').attr("class","");
		$('.subnav a').eq(0).attr("class","active");
	}
});
</script>