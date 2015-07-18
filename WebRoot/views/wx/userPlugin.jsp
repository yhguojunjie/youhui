<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="resource.jsp"%>
<title>我的插件</title>
</head>
<body>
	<div class="space_20"></div>
	<!-- 插件列表 开始 -->
	<div class="plugin_list">
		<div class="norl_title">
			<h3 class="tr"><h3>我的插件</h3></h3>
		</div>
		<ul id="pluginWxList">
			<c:if test="${userPluginVos.totalCount le 0 }">
				<li>您还未购买插件!</li>
			</c:if>
			<c:if test="${userPluginVos.totalCount gt 0}">
				<c:forEach items="${userPluginVos.pageResult}" var="pluginWxVo">
					<li>
					<section onclick="javascript:self.location='${wxurl_pluginDetail}/${pluginWxVo.pluginId}';">
						<a href="${wxurl_pluginDetail}/${pluginWxVo.pluginId}" class="imgbox"><img src="${fileAccessPath}${pluginWxVo.icon}" /></a>
						<h3>${pluginWxVo.name}</h3>
						<p class="tr"><a href="${url_devOtherPage}/${pluginWxVo.devId}">@${pluginWxVo.nickName}</a><span>${pluginWxVo.actNum}个活动</span></p>
						<div class="clear"></div>
						<div class="btns tr">
							<c:if test="${pluginWxVo.isOld == 1}">
								<span>已过期</span>
							</c:if>
							<c:if test="${pluginWxVo.isOld == 0 && pluginWxVo.overdueFlag == 0}">
								<span class="pink"><fmt:formatDate value="${pluginWxVo.overdueTime}" pattern="yyyy/MM/dd " />过期</span>
							</c:if>
							<c:if test="${pluginWxVo.isOld == 0 && pluginWxVo.overdueFlag == 1}">
								<span><fmt:formatDate value="${pluginWxVo.overdueTime}" pattern="yyyy/MM/dd " />过期</span>
							</c:if>
						</div>
					</section>
					<div class="btns tr absolute">
						<a class="btn_add ">新增活动</a>
					</div>
				    </li>
				</c:forEach>
			</c:if>
		</ul>
		<c:if test="${userPluginVos.totalCount gt WXPAGE_SHOW_NUMBER}">
				<div class="seemore" id="seemore_plugin_div"><a>查看更多</a></div>
		</c:if>
		
	</div>
	<div class="space_20"></div>
	<!-- 推荐插件开始 -->
	<div class="plugin_list">
		<%@ include file="plugintj.jsp" %>
	</div>
	<!-- 推荐插件结束 -->
	<div class="space_20"></div>
	<!-- 去PC端口弹出对话框 开始 -->
	<div class="gopc_box">  
	<!-- onclick="javascript:self.location='http://wpa.qq.com/msgrd?V=1&uin=209166172&Site=QQ'" -->
	
		<dl>
			<dt><i class="ico_pc"></i></dt>
			<dd class="space_10">请移步电脑，打开浏览器进行配置</dd>
			<dd><em>http://www.tchajian.com</em></dd>
			<dd>( "淘" 首字母：t，"插件" 全拼：chajian )</dd>
<!-- 			<dd><a href="tencent://message/?uin=209166172&Site=淘插件&Menu=yes" class="btn_send">发送网址到QQ</a></dd> -->
		</dl>
		<p class="source">淘插件<br />营销推广插件超市</p>
		<div class="bg"></div>
	</div>
	<!-- 去PC端口弹出对话框 结束 -->
	<%@ include file="footer.jsp"%>
	<%@ include file="menu.jsp"%>
</body>
</html>
<script type="text/javascript">


//去PC
$(document).ready(function(){
	$('.btn_add').click(function(){
		$('.gopc_box').attr("style","display:block;");
	});
	$('.gopc_box').click(function(){
		$(this).attr("style","display:none;");
	});
});

var fileAccessPath = '${fileAccessPath}';
var basePath = '${basePath}';
var userPluginCount = ${userPluginCount};
var maxpage = ${maxpage};
var currentPage = '2';

/**
 * 查看更多
*/
$('#seemore_plugin_div').click(function(){
		$.ajax({
			  type: "GET",
			  url : "${path }/wx/userPlugin/ajaxList",
			  data:{currentPage:currentPage},
			  dataType: "json",
			  success: function(data){
				  if(currentPage <= maxpage){
					currentPage++;
					var html = '';
				    for (var i = 0; i < data.pageResult.length; i++){
				    	var  str = data.pageResult[i].overdueTime.toString();
	   					str =  str.replace(/-/g,"/");
	   					var overdueTime = new Date(str);
	   					overdueTime = overdueTime.Format("yyyy/MM/dd ");  
						var pluginDetailPath = basePath + "wx/plugin/detail/"+data.pageResult[i].id; 
						var pluginicon = fileAccessPath+data.pageResult[i].icon;
						
				    	html += '<li>';
				    	html += '<section onclick="javascript:self.location=\''+pluginDetailPath+'\';event.stopPropagation();">';
						html += '<a class="imgbox"><img src="'+pluginicon+'" /></a>';
						html += '<h3>'+data.pageResult[i].name+'</h3>';
				    	html += '<p class="tr"><a href="'+url_devUserPage+'/'+data.pageResult[i].devId+'>@'+data.pageResult[i].nickName+'</a><span>'+data.pageResult[i].actNum+'个活动</span></p>';
				    	html += '<div class="clear"></div>';
				    	html += '<div class="btns tr">';
				    	
				    	if(data.pageResult[i].isOld == 1){
				    		html += '<span>已过期</span>';
				    	}else if(data.pageResult[i].isOld == 0 && data.pageResult[i].overdueFlag == 0){
				    		html += '<span class="pink">'+overdueTime+'过期</span>';
				    	}else if(data.pageResult[i].isOld == 0 && data.pageResult[i].overdueFlag == 1){
				    		html += '<span>'+overdueTime+'过期</span>';
				    	}
						
						html += '</div>';
						html += '</section>';
						html += '<div class="btns tr absolute">';
						html += '<a class="btn_add">新增活动</a>';
						html += '</div>';
						html += '</li>';
				    }
				    $('#pluginWxList').append(html);
				  }else{
					 $('#seemore_plugin_div').remove();
				  }
			  }
	  });
	  if(currentPage == maxpage){
	    	$('#seemore_plugin_div').remove();
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

