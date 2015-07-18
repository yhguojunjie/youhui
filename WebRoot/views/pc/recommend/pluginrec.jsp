<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../constant.jsp"%>

<ul>
	<c:forEach items="${pluginList }" var="pluginrec">
		<li onclick="javascript:self.location='${url_pluginDetail}/${pluginrec.id }'">
			<a href="javascript:void(0);" class="img"><img src="${pluginrec.icon }" /></a>
			<h3>
				<a href="${url_pluginDetail }/${pluginrec.id}"><strong>${pluginrec.name }</strong></a>
				 <a href="${url_devOtherPage }/${pluginrec.userId}" class="author">@${pluginrec.nickName }</a> 
				 <span>
					<fmt:formatDate value="${pluginrec.publishTime}" pattern="yyyy/MM/dd HH:mm"/>
					<em>已有${pluginrec.buyNum }人购买</em>
				</span>
			</h3>
			<div class="intro">
				<p>
					<em class="quot_left"> <!-- 前引号 -->
					</em> <span>${pluginrec.description }</span> <em class="quot_right">
						<!-- 后引号 -->
					</em>
				</p>
				<a href="javascript:void(0);" class="btn_try">免费试用</a> 
				<a class="btn_direct">
					<c:choose>
						<c:when test="${pluginrec.salePrice != '0.0' }">
							￥${pluginrec.salePrice  }
						</c:when>
						<c:otherwise>
							价格面议
						</c:otherwise>
					</c:choose>
				</a>
			</div>
			<div class="clear"></div> <!-- 展开内容 -->
		</li>
	</c:forEach>
</ul>
