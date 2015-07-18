<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
<title>${sessionScope.SESSION_USER.nickName}个人主页</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 homepage">
		<!-- 企业名片 开始 -->
		<div class="visitcard">
			<a href="${url_userEdit }" class="avatar"><img src="${sessionScope.SESSION_USER.headimgUrl}" /></a>
			<div class="fl">
				<h3>
					<a href="${url_userEdit }" class="fl">${sessionScope.SESSION_USER.nickName}</a>
					<a href="${url_userEdit }" class="fr">编辑资料</a>
				</h3>
				<p><em class="city"><i class="ico_area"></i>${sessionScope.SESSION_USER.province}&nbsp;${sessionScope.SESSION_USER.city}</em></p>
				<p class="indent">
					<c:choose>
						<c:when test="${sessionScope.SESSION_USER.introduce != null && sessionScope.SESSION_USER.introduce != ''}">
							${sessionScope.SESSION_USER.introduce}
						</c:when>
						<c:otherwise>
							您还没有简介
						</c:otherwise>
					</c:choose>
					
				</p>
				<!-- 有联系资料: a标签类名为pinkbtn; -->
				<!-- 无联系资料: a标签类名为greybtn; -->
				<!-- 不能联系我: a标签则无类名; 
				<div class="contact_btn"><a class="pinkbtn">联系我</a></div>-->
			</div>
			<div class="fr">
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
		<!-- 企业名片 结束 -->
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="norl_title">我的活动（${myPluginActCount}）</div>
			<div class="cont">
				<ul id = "pluginActVoList">
				<c:if test="${myPluginActCount le 0 }">
					<li>
					<div class="if_none"><p>您还没有活动，去<a href="${basePath }">购买</a></p></div>
					</li>
				</c:if>
				<c:if test="${myPluginActCount gt 0}">
				<c:forEach items="${myPluginActVos}" var="myPluginActVo">
					<li>
						<div class="fl">
							<c:if test="${myPluginActVo.type=='0'}">
								<a target="_blank" href="${cjpcShowServer }${myPluginActVo.actAccessUrl}/${myPluginActVo.id}" class="imgbox"><img src="${fileAccessPath }${myPluginActVo.icon}" /></a>
							</c:if>
							<c:if test="${myPluginActVo.type=='1'}">
								<a target="_blank" href="${myPluginActVo.accessUrl}" class="imgbox"><img src="${fileAccessPath }${myPluginActVo.icon}" /></a>
							</c:if>
							<div class="space_20"></div>
							<c:if test="${myPluginActVo.type=='0'}">
								<h3><a target="_blank" href="${cjpcShowServer }${myPluginActVo.actAccessUrl}/${myPluginActVo.id}">${myPluginActVo.title}</a></h3>
							</c:if>
							<c:if test="${myPluginActVo.type=='1'}">
								<h3><a target="_blank" href="${myPluginActVo.accessUrl}">${myPluginActVo.title}</a></h3>
							</c:if>
							<div class="clear"></div>
						</div>
						<div class="fr">
							<c:if test="${myPluginActVo.type =='0'}">
								<img class="code" onclick="showBigimg(this);" 
								src="${url_getQcode }${basePath }${myPluginActVo.actAccessUrl}/${myPluginActVo.id}" />
							</c:if>
							<c:if test="${myPluginActVo.type =='1'}">
								<img class="code" onclick="" src="${url_getQcode }${myPluginActVo.accessUrl}" />
							</c:if>
						</div>
						<div class="clear"></div>
					</li>
				</c:forEach>
				</c:if>
				<c:if test="${myPluginActCount gt PAGE_SHOW_NUMBER}">
					<div class="seemore"><a onclick="moreActes();" id="seemore_avt">查看更多</a></div>
				</c:if>
				</ul>
			</div>
		</div>
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始 -->
		<div class="fr w320 side">
			<div class="norl_title">
				<div class="fr"><a href="${basePath }pc/channel/beChannel" class="more_btn">新增渠道</a></div>
				<h3>名下渠道(${myChannelCount})</h3>
			</div>
			<div class="ditchs">
				<dl id="channelVoList">
				
				<c:forEach items="${channelVOList}" var="channel">
					<dd>
						<a href="${url_otherPage }/${channel.userId}">
							<img class="avatar" src="${channel.logo }" />
							<h3><strong>${channel.name }</strong>
							
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
							</h3>
							<span style="display:block;">${channel.introduce }</span>
							<p>
				                <c:if test="${channel.fansNum!=null}">
				                <b class="fl">粉丝数：<em>${channel.fansNum}万</em></b>
								</c:if>	
								<c:if test="${(channel.price)% 1.0 == 0}">
								<c:set var="string1" value="${channel.price}"/>
								<b>价位：<em ><fmt:formatNumber value="${string1}" pattern="#" type="number"/>元/条左右</em></b>
								</c:if>
								<c:if test="${(channel.price)% 1.0 != 0}">
								<b>价位：<em>${channel.price }元/条左右</em></b>
								</c:if>
								
							</p>
						</a>
						<%-- <c:if test="${channel.type == 1 || channel.type == 2 || channel.type == 3}">
					       <div class="url_img">
					       	<c:if test="${channel.qrcode != null }">
					       		<img src="${fileAccessPath }${channel.qrcode}"/>
					       	</c:if>
					       </div>
		                </c:if>
						<c:if test="${channel.type == 4 || channel.type == 5 || channel.type == 6}">
					       <div class="url_img">
					       	<c:if test="${channel.website != null}"></c:if>
					       		<a target="_blank" href="${channel.website}">${channel.website}</a>
					       </div>
		                </c:if> --%>
						
					</dd>
				</c:forEach>
				<c:if test="${myChannelCount gt PAGE_SHOW_NUMBER}">
					<div class="space_5"></div>
					<div class="more_btn"><a class="see_all" onclick="javascript:channel();" id="seemore_channel" style="width:28%;line-height:28px;height:28px;">查看更多</a></div>
		        </c:if>
	          
				</dl>
			</div>
			</div>
		<!-- 右侧内容 结束 -->
		<div class="clear space_10"></div>
	</div>
	<!-- 其他商家活动 开始 -->
	<div class="w1190">
	<div class="norl_title">
			<div class="fr"><a href="${basePath }pc/act" class="more_btn">更多&gt;&gt;</a></div>
			<h3>其他商家活动</h3>
		</div>
		<div class="ativ_center">
			<div class="main">
				<ul class="in_userarea">
					<c:forEach items="${otherPluginActVos}" var="pluginActVo">
			 			<li>
							<a href="${cjpcShowServer}${pluginActVo.actAccessUrl}/${pluginActVo.actId}" class="imgbox">
								<img class="ativ_img" src="${pluginActVo.actIcon}" />
								<span class="code_img"><img src="${url_getQcode}${cjShowServer}${pluginActVo.actAccessUrl}/${pluginActVo.actId}" /></span>
							</a>
							<h3>
								<a href="${cjpcShowServer}${pluginActVo.actAccessUrl}/${pluginActVo.actId}">${pluginActVo.actTitle}</a>
								<p >
									<span>浏览量：<em>${pluginActVo.actBrowseNum}</em></span>
								</p>
							</h3>
							<div class="card">
								<a href="${url_otherPage }/${pluginActVo.userId}" class="avatar"><img src="${pluginActVo.headimgUrl}"></a>
								<h4>
								${pluginActVo.nickName}
								<c:if test="${pluginActVo.channelId != null }">
						           <em>渠道</em>
					            </c:if>
								</h4>
								<p>${pluginActVo.introduce}</p>
								<div class="clear"></div>
							</div>
							<div class="describe">使用模板：<a href="${basePath}pc/plugin/detail/${pluginActVo.pluginId}">《${pluginActVo.pluginName}》</a> </div>
						</li>
					</c:forEach>
				</ul>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<!-- 内容 结束 -->
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
	
</body>
</html>
<script type="text/javascript">
	var _myPluginActCount = ${myPluginActCount};
	var _myChannelCount = ${myChannelCount};
	var actCount = 10;
	var channelCount=10;
	var loginId = ${sessionScope.SESSION_USER.userId};
	var cjShowServer = '${cjShowServer}';
	var cjpcShowServer = '${cjpcShowServer}';
	var fileAccessPath = '${fileAccessPath}';
	var url_getQcode = '${url_getQcode }';
	var url_otherPage = '${url_otherUserPage}';
	var basePath = '${basePath}';

	/**
	 * 查看更多渠道
	*/
//	$('#seemore_channel').click(function(){
	function channel(){
	 		$.ajax({
	 			  type: "GET",
	 			  url : "${path }/pc/user/getMoreChannes",
	 			  data:{channelCount:channelCount,loginId:loginId},
	 			  dataType: "json",
	 			  success: function(data){
	 				 channelCount += 5;
	 				  $('#channelVoList').empty();
	 				  
	 					var html = '';
	 				    for (var i = 0; i < data.length; i++){
	 				    
		 				    	html += '<dd><a href="">';
		 				    	html += '<img class="avatar" src="'+data[i].logo+'"/></a>';
		 				    	html += '<h3><strong>'+data[i].name+'</strong>';
							  if(data[i].type==1){
		                    	  html += '<i class="wechat_ico">订</i>'; 
		                      }
	                          if(data[i].type==2){
	                        	  html += '<i class="wechat_ico">服</i>';
		                      }
							  if(data[i].type==3){
								  html += '<i class="wechat_ico">个</i>';
							  }
							  if(data[i].type==4){
								  html += '<i class="sina_ico"></i>'; 						  
							  }
							  if(data[i].type==5){
								  html += '<i class="web_ico"></i>'; 						  
							  }
							  if(data[i].type==6){
								  html += '<i class="app_ico"></i>'; 						  
							  }
							  
							  html += '</h3>';
		 				    	html += '<span style="display:block;">'+data[i].introduce+'</span>';
		 				    	if(data[i].fansNum!=null){
		 				    	html += '<p><b class="fl">粉丝数：<em>'+data[i].fansNum+'万</em></b>';
		 				    	}
		 				    	html += '<b>价位：<em>'+data[i].price+'元/条左右</em></b></p></a>';
		 				    	html +=' <div class="url_img">';
		 				    	/* if(data[i].type == '1' || data[i].type == '2' || data[i].type == '3'){
		 				    		if(data[i].qrcode != null){
		 				    			html += '<img src="'+fileAccessPath+data[i].qrcode+'" onclick="showBigimg(this);" class="code">';
		 				    		}
								}
		 				    	if(data[i].type == '4' || data[i].type == '5' || data[i].type == '6'){
			 				    	if(data[i].website != null && data[i].website != undefined){
			 				    		html += '<a  target="_blank" href="'+data[i].website+'">'+data[i].website+'</a>';
			 				    	}
								} */
		 				    	html +='</div>';
	 				    	    html += '</dd>';
	 				    }
	 				   if(_myChannelCount > data.length){
	 				    	html += '<div class="seemore"><a class="see_all" onclick="channel();" id="seemore_channel" style="width:28%;line-height:28px;height:28px;">查看更多</a></div>';
	 				    }
	 				    $('#channelVoList').html(html);
	 			  }
	 		
	 	  }); 
	}

	/**
	  * 查看更多活动
	 */
function moreActes(){
 		$.ajax({
 			  type: "GET",
 			  url : "${path }/pc/user/getMoreActes",
 			  data:{actCount:actCount,loginId:loginId},
 			  dataType: "json",
 			  success: function(data){
 				  actCount += 5;
 				  $('#pluginActVoList').empty();
 					var html = '';
 				    for (var i = 0; i < data.length; i++){
 				    	var actUrl;
 				    	if('0'==data[i].type){
 				    		actUrl = cjpcShowServer+data[i].actAccessUrl+'/'+data[i].id;
						}else{
							actUrl = data[i].accessUrl;
						}
 				    	html += '<li>';
	 				    	html += '<div class="fl">';
	 				    	html += '<a target="_blank" href="'+actUrl+'" class="imgbox"><img src="'+fileAccessPath+data[i].icon+'" /></a>';
	 				    	html += '<div class="space_20"></div>';
	 				    	html += '<h3><a target="_blank" href="'+actUrl+'">'+data[i].title+'</a></h3>';
	 				    	html += '<div class="clear"></div>';
	 				    	html += '</div>';
	 				    	html += '<div class="fr">';
	 				    	if('0'==data[i].type){
	 				    		html += '<img class="code" src="'+url_getQcode+basePath+data[i].actAccessUrl+'/'+data[i].id+'" />';
							}else{
								html += '<img class="code" src="'+url_getQcode+data[i].actAccessUrl+'" />';
							}
	 				    	html += '</div>';
	 				    	html += '<div class="clear"></div>';
 				    	html += '</li>';
 				    }
 				    if(_myPluginActCount > data.length){
 				    	html += '<div class=\"seemore\"><a onclick="moreActes();" id=\"seemore_avt\">查看更多</a></div>';
 				    }
 				    $('#pluginActVoList').html(html);
 			  }
 	  }); 
	}

//显示大图(code)
	function showBigimg(obj){
		$('body').append('<div onclick="hideBigimg(this);" class="bigimg_box">\
						<img src="'+$(obj).attr("src")+'" />\
					</div>');
		$('.bigimg_box img').load(function(){
			$(this).attr("style","margin-top:-" + ($(this).height()/2-5) +"px;margin-left:-" + ($(this).width()/2-5) +"px;");
		});
	}
	function hideBigimg(obj){
		$(obj).remove();
	}

</script>



