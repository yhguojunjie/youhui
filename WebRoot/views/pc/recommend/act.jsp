<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../constant.jsp"%>

<ul>
	<c:forEach items="${actList }" var="act">
		<li><a href="${cjpcShowServer }${act.actAccessUrl }/${act.actId}" class="imgbox"> <img
				class="ativ_img" src="${act.actIcon }" /> <span class="code_img"><img
					src="${url_getQcode }${basePath}${act.actAccessUrl}/${act.actId}" /></span>
		</a>
			<h3>
				<a href="${cjpcShowServer}${act.actAccessUrl}/${act.actId}">${act.actTitle }</a>
				<p>
					<span>浏览量：<em>${act.actBrowseNum }</em></span>
				</p>
			</h3>
			<div class="card">
				<a href="${url_otherPage }/${act.userId}" class="avatar"><img src="${act.headimgUrl }" /></a>
				<h4>${act.nickName }
					<c:if test="${act.channelId != null }">
						<em>渠道</em>
					</c:if>
				</h4>
				<p>${act.introduce }</p>
				<div class="clear"></div>
			</div>
			<div class="describe">
				使用模版：<a href="${url_pluginDetail }/${act.pluginId}">《${act.pluginName }》</a>
			</div></li>
	</c:forEach>
</ul>

