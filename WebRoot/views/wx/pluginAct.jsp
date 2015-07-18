<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="resource.jsp"%>
<title>我的活动</title>
</head>
<body>
	<div class="space_20"></div>
	<!-- 活动列表 开始 -->
	<div class="activity_list" id="pluginActList">
			<c:if test="${paginationPluginAct.totalCount le 0 }">
				<div class="if_none">您还没有活动!</div>
			</c:if>
			<c:if test="${paginationPluginAct.totalCount gt 0}">
				<c:forEach items="${paginationPluginAct.pageResult}" var="pluginActVo">
					<dl>
					<c:if test="${pluginActVo.activeFlag == 1}">
						<dt class="not">
						<span><fmt:formatDate value="${pluginActVo.startTime}" pattern="yyyy/MM/dd " /> 
						~ 
						<fmt:formatDate value="${pluginActVo.endTime}" pattern="yyyy/MM/dd " /></span>
						<em>未开始</em>
						</dt>
					</c:if>
					<c:if test="${pluginActVo.activeFlag == 2}">
						<dt class="ing">
						<span><fmt:formatDate value="${pluginActVo.startTime}" pattern="yyyy/MM/dd " /> 
						~ 
						<fmt:formatDate value="${pluginActVo.endTime}" pattern="yyyy/MM/dd " /></span>
						<em>正在进行</em>
						</dt>
					</c:if>
					<c:if test="${pluginActVo.activeFlag == 3}">
						<dt class="end">
						<span><fmt:formatDate value="${pluginActVo.startTime}" pattern="yyyy/MM/dd " /> 
						~ 
						<fmt:formatDate value="${pluginActVo.endTime}" pattern="yyyy/MM/dd " /></span>
						<em>已结束</em>
						</dt>
					</c:if>
					
					<dd>
						<c:if test="${pluginActVo.type == '0' }">
							<a href="${cjShowServer }${pluginActVo.actAccessUrl}/${pluginActVo.id}">
							<img src="${fileAccessPath}${pluginActVo.icon}" />
						</c:if>
						<c:if test="${pluginActVo.type == '1' }">
							<a href="${pluginActVo.accessUrl}">
							<c:choose>
								<c:when test="${pluginActVo.icon != null && pluginActVo.icon != ''}">
									<img src="${fileAccessPath}${pluginActVo.icon}" />
								</c:when>
								<c:otherwise>
									<img src="${path}/images/wx/outact.gif" />
								</c:otherwise>
							</c:choose>
							
						</c:if>
							
							<span>${pluginActVo.title}</span>
						<c:if test="${pluginActVo.type == '0' }">
							<strong><i class="ico_eye"></i>${pluginActVo.browseNum}</strong>
						</c:if>
						</a>
					</dd>
					</dl>
				</c:forEach>
			</c:if>
	</div>
	<c:if test="${paginationPluginAct.totalCount gt WXPAGE_SHOW_NUMBER}">
		<div class="get_more" id="see_more_act">查看更多</a></div>
	</c:if>
	<div class="space_20"></div>
	<!-- 活动列表 结束 -->
	
	<!-- 活动推荐 开始 -->
	<div class="activity_recomd">
		<div class="norl_title">
			<h3>活动推荐</h3>
		</div>
		<ul>
			<c:forEach items="${tjPluginActVos}" var="tjPluginActVo">
				<li>
				<c:if test="${tjPluginActVo.type == '0'}">
					<section onclick="javascript:self.location='${cjShowServer }${tjPluginActVo.actAccessUrl}/${tjPluginActVo.id}'">
				</c:if>
				<c:if test="${tjPluginActVo.type == '1'}">
					<section onclick="javascript:self.location='${tjPluginActVo.accessUrl}'">
				</c:if>
					<a class="imgbox"><img src="${fileAccessPath}${tjPluginActVo.icon}" /></a>
					<a class="title">${tjPluginActVo.title}</a>
					<div class="clear"></div>
				</section>
				<a href="${url_otherUserPage }/${tjPluginActVo.userId}" class="enterprise">${tjPluginActVo.nickName}</a>
			</li>
			</c:forEach>
		</ul>
	</div>
	<!-- 活动推荐 结束 -->
	<div class="space_20"></div>
	<%@ include file="footer.jsp"%>
	<%@ include file="menu.jsp"%>
</body>
</html>

<script type="text/javascript">
var fileAccessPath = '${fileAccessPath}';
var basePath = '${basePath}';
var cjShowServer = '${cjShowServer}';
var maxpage = ${maxpage};
var currentPage = '2';

/**
 * 查看更多
*/
$('#see_more_act').click(function(){
		$.ajax({
			  type: "GET",
			  url : "${path }/wx/pluginAct/ajaxList/${userId}",
			  data:{currentPage:currentPage},
			  dataType: "json",
			  success: function(data){
				  if(currentPage <= maxpage){
					currentPage++;
					var html = '';
				    for (var i = 0; i < data.pageResult.length; i++){
				    	var startTime;
		     			if(data.pageResult[i].startTime != null && data.pageResult[i].startTime != undefined
								&& data.pageResult[i].startTime != ''){
							var  str1 = data.pageResult[i].startTime.toString();
		   					str1 =  str1.replace(/-/g,"/");
		   					startTime = new Date(str1);
		   					startTime = startTime.Format("yyyy/MM/dd hh:mm"); 
		   					var  str2 = data.pageResult[i].endTime.toString();
						}
		     			var endTime;
	   				 	if(data.pageResult[i].endTime != null && data.pageResult[i].endTime != undefined
								&& data.pageResult[i].endTime != ''){
	   				 		var str2 =  str2.replace(/-/g,"/");
	   						endTime = new Date(str2);
	   						endTime = endTime.Format("yyyy/MM/dd hh:mm"); 
	   				 	}
	   				 	
	   				 	var actIcon = fileAccessPath+data.pageResult[i].icon;
						if(data.pageResult[i].type == '1'){
							if(data.pageResult[i].icon == null || data.pageResult[i].icon ==''){
								actIcon = basePath+data.pageResult[i].icon;
							}
	   				 	}
	   				 	
	   				   html += '<dl>';
	   				   if(data.pageResult[i].activeFlag == 1){
	   					html += '<dt class=\"not\">';
	   					html += '<span>'+startTime+' ~ '+endTime+'</span>';
	   					html += '<em>未开始</em>';
	   					html += '</dt>';
	   				   }
		   			   if(data.pageResult[i].activeFlag == 2){
		   				html += '<dt class=\"ing\">';
	   					html += '<span>'+startTime+' ~ '+endTime+'</span>';
	   					html += '<em>正在进行</em>';
	   					html += '</dt>';   
		   			   }
		   			   if(data.pageResult[i].activeFlag == 3){
		   				html += '<dt class=\"end\">';
	   					html += '<span>'+startTime+' ~ '+endTime+'</span>';
	   					html += '<em>已结束</em>';
	   					html += '</dt>';   
		   			   }
					   if(data.pageResult[i].type == '0'){
					   		html += '<dd><a href="'+cjShowServer+data.pageResult[i].actAccessUrl+'/'+data.pageResult[i].id+'">';
					   }
		   			   if(data.pageResult[i].type == '1'){
		   			     	html += '<dd><a href="'+data.pageResult[i].accessUrl+'">';
		   			   }
		   			   html += '<img src="'+fileAccessPath+data.pageResult[i].icon+'" />';
		   			   html += '<span>'+data.pageResult[i].title+'</span>';
		   			   if(data.pageResult[i].type == '0'){
		   					html += '<strong><i class=\"ico_eye\"></i>'+data.pageResult[i].browseNum+'</strong>';  
		   			   }
		   			   html += '</a></dd>';
		   			   html += '</dl>';
				    }
				    $('#pluginActList').append(html);
				  }else{
					 $('#see_more_act').remove();
				  }
			  }
	  });
	  if(currentPage == maxpage){
	    	$('#see_more_act').remove();
	  }
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