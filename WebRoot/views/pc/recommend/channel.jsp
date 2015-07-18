<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../constant.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div class="norl_title">
		<!-- <div class="fr"><a href="#" class='more_btn'>更多>></a></div> -->
		<h3>渠道推荐</h3>
	</div>
<div class="channel_hot">
	<ul>
	  <c:forEach items="${channelVOList }" var="channel">
		<!-- <li><a href="${url_otherPage }/${channel.userId}"> <img src="${channel.logo }" />
				 -->
				<li> <img src="${channel.logo }" />
				 <h3>
					<strong>${channel.name }</strong>
					<c:if test="${channel.type=='1'}">
		              <i class="wechat_ico">订</i>
		            </c:if>
		            <c:if test="${channel.type=='2'}">
		              <i class="wechat_ico">服</i>
		            </c:if>
		            <c:if test="${channel.type=='3'}">
		              <i class="wechat_ico">个</i>
		            </c:if>
		            <c:if test="${channel.type=='4'}">
		              <i class="sina_ico"></i>
		            </c:if>
		            <c:if test="${channel.type=='5'}">
		              <i class="web_ico"></i>
		            </c:if>	
		            <c:if test="${channel.type=='6'}">
		              <i class="app_ico"></i>
		            </c:if>		
				</h3> <span  style="display:block;">${channel.introduce }</span>
				<div class="space_5 clear"></div>
				<c:if test="${channel.fansNum!=null}">
				<p>
					粉丝数：<em>${channel.fansNum}万</em>
				</p>
				</c:if>
				<c:if test="${(channel.price)% 1.0 == 0}">
				<p>
				<c:set var="string1" value="${channel.price}"/>
					价位：<em > <fmt:formatNumber value="${string1}" pattern="#" type="number"/>元/条左右</em>
				</p>
				</c:if>
				<c:if test="${(channel.price)% 1.0 != 0}">
				<p>
					价位：<em >${channel.price}元/条左右</em>
				</p>
				</c:if>
				
				</li>
		<!-- </a></li> -->
	  </c:forEach>
	</ul>
	<div class="space_20 clear"></div>
	<a href="${url_channel }" class="see_all">查看全部</a>
</div>