<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../constant.jsp"%>

<div class="norl_title" style="margin:20px 0;">
	<h3>模版预告</h3>
</div>
<div class="temp_foreshow">
	<dl>
	  <c:forEach items="${pluginPrevList }" var="pluginprev">
		<dd>
			<h3>${pluginprev.name }</h3>
			<p>${pluginprev.description }</p>
			<span>预计上架时间：<em> <fmt:formatDate value="${pluginprev.shelTime }"/></em></span>
		</dd>
	  </c:forEach>
	</dl>
</div>