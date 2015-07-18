<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
<title>${loginUser.nickName}个人主页</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 homepage">
		<!-- 企业名片 开始 -->
		<div class="visitcard">
			<a  class="avatar"><img src="${loginUser.headimgUrl}" /></a>
			<div class="fl">
				<h3>
					<a  class="fl">@${loginUser.nickName}</a>
				</h3>
				<p><em class="city"><i class="ico_area"></i>${loginUser.province}&nbsp;${loginUser.city}</em></p>
				<p class="indent">${loginUser.introduce}</p>
				<!-- 有联系资料: a标签类名为pinkbtn; -->
				<!-- 无联系资料: a标签类名为greybtn; -->
				<!-- 不能联系我: a标签则无类名; -->
				<!--<div class="contact_btn"><a class="greenbtn">联系我</a></div>-->
<!-- 				<p class="suc_gold">
					<span><i class="ico_suc"></i>成交：<em>14</em> 笔</span>
					<b>|</b>
					<span><i class="ico_gold"></i>收入：<em>￥1545.6</em></span>
				</p> -->
			</div>
			<div class="fr">
				<%-- <div class="code_share">
					<img src="${url_getQcode }${qrcodeUrl}" />
				</div> --%>
				<!-- Baidu Share BEGIN -->
			<!-- 	<div class="bdsharebuttonbox" style="float:right;margin:0 -6px 0 0;">
						<a href="#" class="bds_more" data-cmd="more"></a>
						<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
						<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
						<a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
						<a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
						<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
				</div>
				<script>
						window._bd_share_config={
								    "common":{
											"bdSnsKey":{},
											"bdText":"",
											"bdMini":"2",
											"bdMiniList":false,
											"bdPic":"",
											"bdStyle":"1",
											"bdSize":"16"
									},
									"share":{}
						};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
						 -->	
				
				<!-- Baidu Share END -->
<!-- 				<span class="sharehead">分享到：</span> -->
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
		<!-- 企业名片 结束 -->
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="norl_title">我开发的（${devCountNum}）</div>
			<div class="cont">
				<ul id="developer">
					<c:if test="${devCountNum le 0 }">
						<li>
						<p>TA还没有任何插件，去<a href="${path }/pc/plugin/list">首页帮TA挑选</a></p>
						</li>
					</c:if>
					<c:if test="${devCountNum gt 0}">
					<c:forEach items="${devPluginVos}" var="devPluginVo">
						<li>
							<div class="fl">
								<a href="${url_pluginDetail }/${devPluginVo.id}" class="imgbox"><img src="${fileAccessPath }${devPluginVo.icon}" /></a>
								<h3><a href="${url_pluginDetail }/${devPluginVo.id}">${devPluginVo.name}</a></h3>
								<p>
									<em class="quot_left"><!-- 前引号 --></em>
									<span>${devPluginVo.description}</span>
									<em class="quot_right"><!-- 后引号 --></em>
								</p>
								<div class="clear"></div>
							</div>
							<div class="fr">
								<img class="code" src="${url_getQcode }${cjShowServer }${devPluginVo.actAccessUrl}/${devPluginVo.showActId}" />
							</div>
							<div class="clear"></div>
						</li>
					</c:forEach>
					</c:if>
					<c:if test="${devCountNum gt PAGE_SHOW_NUMBER}">
						<div class="seemore"><a onclick="morePlugins();" id="seemore_avt">查看更多</a></div>
					</c:if>
					</ul>
			</div>
		</div>
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始 -->
		<!-- 右侧内容 结束 -->
		<div class="clear space_30"></div>
		<!-- 其他开发者 开始 -->
		<div class="norl_title">其他开发者</div>
		<div class="developer_card">
			<ul>
				<c:forEach items="${otherDevUsers}" var="otherDevUser">
				<li style="height:auto;">
					<dl>
						<dt>
							<a href="${url_devOtherPage }/${otherDevUser.userId}" class="avatar"><img src="${otherDevUser.headimgUrl}" /></a>
							<h3><a href="${url_devOtherPage }/${otherDevUser.userId}">@${otherDevUser.nickName}</a></h3>
							<p>${otherDevUser.introduce}</p>
						</dt>
<!-- 						<dd>
							<span class="fr"><i class="ico_gold"></i>收入：<strong>￥1545.6</strong></span>
							<span><i class="ico_suc"></i>成交：<strong>14</strong>笔</span>
						</dd> -->
						<dd>
							<span>开发插件：<strong>${otherDevUser.countNum}</strong></span>
						</dd>
					</dl>
				</li>
				</c:forEach>
				
			</ul>
			<div class="clear"></div>
		</div>
		<!-- 其他开发者 结束 -->
	</div>
	<!-- 内容 结束 -->
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
	<!-- 抢走礼包提示 开始 -->
	<div class="nogift_box">
		<div class="cont">
			<h3>
				<strong>提示</strong>
				<span class="close"><!-- 关闭窗口按钮 -->╳</span>
			</h3>
			<p>
				<img src="images/avatar.jpg" />
				<span>嘿嘿，被<em>我</em>抢走了！</span>
			</p>
			<a class="btn_cancel">知道了</a>
		</div>
		<div class="bg"></div>
	</div>
	<!-- 抢走礼包提示 结束 -->
	<!-- 剩余礼包提示 开始 -->
	<div class="remaininggift_box">
		<div class="cont">
			<h3>
				<strong>提示</strong>
				<span class="close"><!-- 关闭窗口按钮 -->╳</span>
			</h3>
			<div class="space_20"></div>
			<p>
				<span>每个淘插件账号都有<em>3个神秘大礼包</em></span>
			</p>
			<p>
				<span>随机赠送给粉丝</span>
			</p>
			<p>
				<span>快去成为TA的粉丝，抢TA礼包！</span>
			</p>
			<div class="space_20"></div>
			<div class="tc">
				<a class="btn_cancel">知道了</a>
			</div>
		</div>
		<div class="bg"></div>
	</div>
	<!-- 剩余礼包提示 结束 -->
	<!-- 联系我成功提示 开始 -->
	<div class="contact_sucbox">
		<div class="cont">
			<h3>
				<strong>提示</strong>
				<span class="close"><!-- 关闭窗口按钮 -->╳</span>
			</h3>
			<table>
				<tr>
					<td>
						<div class="row"><i class="ico_phone"></i>手机：
							<c:choose>
								<c:when test="${loginUser.mobile !=null && loginUser.mobile != '' 
													&& loginUser.mobileOpen == '1'}">
										${loginUser.mobile}
								</c:when>
								<c:otherwise>
								暂无
								</c:otherwise>
							</c:choose>
								
						</div>
						<div class="row">
							<c:choose>
									<c:when test="${loginUser.qqAccount !=null && loginUser.qqAccount != ''}">
										<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${loginUser.qqAccount}&site=qq&menu=yes" class="fr">发消息</a> 
										<i class="ico_qq"></i>Q Q：
										${loginUser.qqAccount}
									</c:when>
									<c:otherwise>
										<i class="ico_qq"></i>Q Q：
										暂无
									</c:otherwise>
							</c:choose>
						</div>
						<div class="row"><i class="ico_wechat"></i>微信：
							<c:choose>
								<c:when test="${loginUser.weixinAccount !=null && loginUser.weixinAccount != ''}">
									${loginUser.weixinAccount}
								</c:when>
								<c:otherwise>
									暂无
								</c:otherwise>
							</c:choose>
						</div>
						<div class="row">
						<%-- <a href="Mail to ${loginUser.email}" class="fr">发邮件</a> --%>
						<i class="ico_mail"></i>邮箱：
							<c:choose>
								<c:when test="${loginUser.email !=null && loginUser.email != ''}">
									${loginUser.email}
								</c:when>
								<c:otherwise>
									暂无
								</c:otherwise>
							</c:choose>
						
						</div>
					</td>
				</tr>
			</table>
			<a class="btn_cancel">知道了</a>
		</div>
		<div class="bg"></div>
	</div>
	<!-- 联系我成功提示 结束 -->
	<!-- 联系我失败提示 开始 -->
	<div class="contact_faibox">
		<div class="cont">
			<h3>
				<strong>提示</strong>
				<span class="close"><!-- 关闭窗口按钮 -->╳</span>
			</h3>
			<div class="space_20"></div>
			<p>
				<span>您还没有填写任何联系方式</span>
			</p>
			<p>
				<span>快去“<a target="_blank" href="${url_userEdit }">帐号设置</a>”，补全资料吧~</span>
			</p>
			<div class="space_20"></div>
			<div class="tc">
				<a class="btn_cancel">取消</a>
				<a class="btn_done">确定</a>
			</div>
		</div>
		<div class="bg"></div>
	</div>
	<!-- 联系我失败提示 结束 -->
</body>
</html>
<script type="text/javascript">
var pluginCount = 10;

var loginId = ${loginUser.userId};
var cjShowServer = '${cjShowServer}';
var cjpcShowServer = '${cjpcShowServer}';
var fileAccessPath = '${fileAccessPath}';
var url_getQcode = '${url_getQcode }';
var url_otherPage = '${url_otherUserPage}';
var url_pluginDetail = '${url_pluginDetail}';
var _devCountNum = ${devCountNum};

 
/**
  * 查看更多插件
 */
function morePlugins(){
 		$.ajax({
 			  type: "GET",
 			  url : "${path }/pc/user/getMorePlugins",
 			  data:{pluginCount:pluginCount,loginId:loginId},
 			  dataType: "json",
 			  success: function(data){
 				  pluginCount += 5;
 				  $('#developer').empty();
 					var html = '';
 				    for (var i = 0; i < data.length; i++){
 				    	html += '<li>';
	 				    	html += '<div class="fl">';
	 				    	html += '<a href="'+url_pluginDetail+'/'+data[i].id+'" class="imgbox"><img src="'+fileAccessPath+data[i].icon+'" /></a>';
	 				    	html += '<h3><a  href="'+url_pluginDetail+'/'+data[i].id+'">'+data[i].name+'</a></h3>';
	 				    	html += '<p>';
	 				    	html += '<em class="quot_left"><!-- 前引号 --></em>';
	 				    	html += '<span>'+data[i].description+'</span>';
	 				    	html += '<em class="quot_right"><!-- 后引号 --></em>';
	 				    	html += '</p>';
	 				    	html += '<div class="clear"></div>';
	 				    	html += '</div>';
	 				    	html += '<div class="fr">';
	 				    	html += '<img class="code" src="'+url_getQcode+data[i].actAccessUrl+'/'+data[i].showActId+'" />';
	 				    	html += '</div>';
	 				    	html += '<div class="clear"></div>';
 				    	html += '</li>';
 				    }
 				    if(_devCountNum > data.length){
				    	html += '<div class=\"seemore\"><a onclick="morePlugins();" id=\"seemore_avt\">查看更多</a></div>';
				    }
 				    $('#developer').html(html);
 			  }
 	  }); 
}

</script>
