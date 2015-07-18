<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../constant.jsp"%>
<%@ include file="../resource.jsp"%>

	<ul>
	  <c:forEach items="${channelVOList }" var="channelRecL">
		<li>
		<a href="${url_otherPage }/${channelRecL.userId}">
		 <b class="rehot">推荐</b>
		 <img src="${channelRecL.logo }" />
				<h3>
					<strong>${channelRecL.name }</strong>
				
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
				</h3> <span  style="display:block;">${channelRecL.introduce }</span>
				<div class="space_5 clear"></div>
				<c:if test="${channelRecL.fansNum!=null}">
				<p>
					粉丝数：<em>${channelRecL.fansNum }万</em>
				</p>
				</c:if>
				<p>
					价位：<em>${channelRecL.price }元/条左右</em>
				</p>
				
		</a>
		</li>
	  </c:forEach>
	</ul>