<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.yoxi.hudongtui.constants.Globals" %>
<%@ page language="java" import="com.yoxi.hudongtui.vo.agent.AgentInfoVO" %>
<script charset="utf-8" src="http://wpa.b.qq.com/cgi/wpa.php"></script>
<script type="text/javascript">
	BizQQWPA.addCustom({aty: '1', a: '1', nameAccount: '${agentInfoConst.serviceqq }', selector: 'BizQQWPABanner'});
</script>
<div class="w1190 banner" id="banner" >
		<div class="main">
			<ul class="banner_cont">
			<c:forEach items="${bannerList}" var="banner" >
			<c:if test="${banner.linktype==1}">  
			<li><a href="${url_pluginDetail }/${banner.objid}"><img src="${fileAccessPath}/${banner.pcLogo}" /></a></li>
			 </c:if> 
			 
			 <c:if test="${(banner.linktype==2)||(banner.linktype==null)}">  
			<li><a href="${banner.pcLink}"><img src="${fileAccessPath}/${banner.pcLogo}" /></a></li>
			 </c:if> 
			</c:forEach>
<!-- 				<li><a href="${basePath}pc/other/joinus"><img src="${path}/images/pc/pc_banner_00.jpg" /></a></li> -->

				<%-- <li><a href="${url_pluginDetail }/17"><img src="${path}/images/pc/pc_banner_6.jpg" /></a></li>

				<li><a href="${url_pluginDetail }/18"><img src="${path}/images/pc/pc_banner_7.jpg" /></a></li>
				<li><a href="${url_pluginDetail }/17"><img src="${path}/images/pc/pc_banner_6.jpg" /></a></li>

				<li><a href="${url_pluginDetail }/16"><img src="${path}/images/pc/pc_banner_5.jpg" /></a></li>
				<li><a href="${url_pluginDetail }/2"><img src="${path}/images/pc/pc_banner_1.jpg" /></a></li>
				<li><a href="${url_pluginDetail }/7"><img src="${path}/images/pc/pc_banner_2.jpg" /></a></li>
				<li><a href="${url_pluginDetail }/10"><img src="${path}/images/pc/pc_banner_3.jpg" /></a></li>
				<li><a href="${url_pluginDetail }/14"><img src="${path}/images/pc/pc_banner_4.jpg" /></a></li>
			 --%></ul>
		</div>
		<div class="focus">
			<ul>
			<c:forEach items="${bannerList}" varStatus="i"  var="banner" >
			<c:if test="${i.index==0}">  
				<li class="active"> ${i.index}</li>
			</c:if>	
			<c:if test="${i.index!=0}">  
				<li>${i.index}</li>
			</c:if>	
			<%-- <li>${i.index}</li> --%>
			</c:forEach>
			</ul>
		</div>
		<div class="prev"><!-- 上一张 --></div>
		<div class="next"><!-- 下一张 --></div>
		<div class="side">
			<h3>已有<em>${brandCount}</em>个品牌强势入驻：</h3>
			<ul>
				<c:forEach items="${brandList}" var="brand" >
					<li><a href="${url_otherPage }/${brand.userId}"><img src="${brand.logo}" /></a></li>
				</c:forEach>
			</ul>
			<a href="${basePath }register/pc" class="join_btn">现在加入</a>
			<p>咨询电话：${agentInfoConst.servicePhone }</p>
			<c:choose>
				<c:when test="${fn:startsWith(agentInfoConst.serviceqq,'400') || fn:startsWith(agentInfoConst.serviceqq,'800')}">
					<a href="javascript:void(0);" id="BizQQWPABanner" class="qq_btn"><em class="qq_ico"></em><span>点击QQ交谈</span></a>
				</c:when>
				<c:otherwise>
					<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${agentInfoConst.serviceqq }&site=qq&menu=yes" class="qq_btn">QQ在线客服</a>
				</c:otherwise>
			</c:choose>
			<div class="code_btn"><em>&nbsp;</em></div>
			<div class="code">
				<i class="close">╳</i>
				<p>微信官方公众号</p>
				<img src="${agentInfoConst.wxqrcode }" />
				<span>实时查看活动数据</span>
				<span>了解最新优惠信息</span>
			</div>
		</div>
		<div class="shadow"><!-- 阴影隔断 --></div>
</div>