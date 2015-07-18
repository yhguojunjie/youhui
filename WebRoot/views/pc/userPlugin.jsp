<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
	<script type="text/javascript" src="${path }/js/pc/pagination.js"></script>
<title>我的淘插件</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 index">
		<!-- 左侧内容 开始 -->
		<%@ include file="left.jsp" %>
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始 -->
		<div class="fr w860 main">
			<div class="tab_title">
				<div class="fl options" id="myplugin_sortbtn">
					<a id="allUse" value ="0" class="active">全部</a>
					<a id="enableUse" value ="1" >可使用(${enableNum})</a>
					<a id="disableUse" value ="2" >已过期(${disenableNum})</a>
				</div>
				<div class="clear"></div>
			</div>
			<div class="myplugin" id="myplugin">
				
			</div>
				
			<div class="space_10"></div>
			<div class="norl_title">
				<h3>插件推荐</h3>
			</div>
			<div class="cont">
				<ul id="mypluginlist">
				<c:forEach items="${tjPluginVos}" var="pluginVo">
				    <li onclick="javascript:self.location='${url_pluginDetail}/${pluginVo.id}';" data="${pluginVo.id}" >
				        <a class="img"><img src="${fileAccessPath }${pluginVo.icon}" /></a>
						<h3>
							<a ><strong>${pluginVo.name}</strong></a>
						<a class="author">@${pluginVo.devNickName}</a>
							<span><fmt:formatDate value="${pluginVo.publishTime}" pattern="yyyy/MM/dd  HH:mm" /> &nbsp;&nbsp;<em>已有${pluginVo.buyNum}人购买</em></span>
						</h3>
						<div class="intro">
							<p>
								<em class="quot_left"><!-- 前引号 --></em>
								<span>${pluginVo.description}</span>
								<em class="quot_right"><!-- 后引号 --></em>
							</p>
							<c:if test="${pluginVo.price != null &&  pluginVo.price != 0.0}">
								<a class="btn_try">免费试用</a>
								<a class="btn_direct">￥${pluginVo.price}</a>
							</c:if>
							<c:if test="${pluginVo.price != null &&  pluginVo.price == 0.0}">
								<a class="btn_free">免费</a>
							</c:if>
							
						</div>
						<div class="clear"></div>
						<!-- 展开内容 -->
						<dl name = "pluginPics" id="${pluginVo.id}"></dl>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
		<!-- 右侧内容 结束 -->
		<div class="clear"></div>
	</div>
	<!-- 内容 结束 -->
	
	<!-- 底部 开始 -->
	<div class="clear"></div>
	<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
	<!-- 新手引导开始 -->
	<div class="guide" style="display: none;">
		<div class="cont">
			<img class='plugin1' src="${path }/images/pc/pluginguide1.png" />
			<img class='plugin2' src="${path }/images/pc/pluginguide2.png" />
		</div>
		<div class="bg"></div>
	</div>
	<!-- 新手引导结束 -->	
</body>
</html>
<script type="text/javascript">
var url_pluginDetail = '${url_pluginDetail}';
var url_devUserPage = '${url_devUserPage}';
var fileAccessPath = '${fileAccessPath}';
var xFlag = '0';

//插件页面
function getPageDatas(currentPage) {
	$.ajax({
		  type: "GET",
		  url : "${path }/pc/userPlugin/ajaxList?currentPage="+currentPage+"&xFlag="+xFlag,
		  dataType: "json",
		  success: function(data){
			  if(data != null && data !=undefined){
				 var currentPage = data.currentPage;//当前页数
				 var totalPage = data.totalPage;//总页数
				 var count = data.count; //每页显示多少
				 
				 $('#myplugin').empty();
		     	
		  	   	 var html = '';
		     	 html += '<div class="cont hide" id="show">';
				 
		     	 if(data.pageResult != undefined && data.pageResult != null && data.pageResult.length != 0){
		     		 for (var i = 0; i < data.pageResult.length; i++) {
		     			var overdueTime;
		     			if(data.pageResult[i].overdueTime != null && data.pageResult[i].overdueTime != undefined
								&& data.pageResult[i].overdueTime != ''){
							var  str1 = data.pageResult[i].overdueTime.toString();
		   					str1 =  str1.replace(/-/g,"/");
		   					overdueTime = new Date(str1);
		   					overdueTime = overdueTime.Format("yyyy/MM/dd"); 
						}
		     			
		     			html += '<ul>';
		     			html += '<li>';
		     			html += '<input type="hidden" id="_pluginId" name="_pluginId" value="'+data.pageResult[i].pluginId+'">';
		     			html += '<a  href="'+url_pluginDetail+'/'+data.pageResult[i].pluginId+'" class="img"><img src="'+fileAccessPath+data.pageResult[i].icon+'" /></a>';
		     			html += '<h3>';
		     			html += '<a  href="'+url_pluginDetail+'/'+data.pageResult[i].pluginId+'"><strong>'+data.pageResult[i].name+'</strong></a>';
		     			html += '<a  href="'+url_devUserPage+'/'+data.pageResult[i].devId+'" class="author">@'+data.pageResult[i].nickName+'</a>';
		     			if(data.pageResult[i].isOld == 1){
		     				html += '<span>已过期&nbsp;&nbsp;<em>'+data.pageResult[i].actNum+'个活动</em></span>';
		     			}else{
		     				html += '<span>'+overdueTime+'过期&nbsp;&nbsp;<em>'+data.pageResult[i].actNum+'个活动</em></span>';
		     			}
						html += '</h3>';
						html += '<div class="intro">';
						html += '<p>';
						html += '<em class="quot_left"><!-- 前引号 --></em>';
						html += '<span>'+data.pageResult[i].description+'</span>';
						html += '<em class="quot_right"><!-- 后引号 --></em>';
						html += '</p>';
						if(data.pageResult[i].isOld == 1){
							html += '<a href="javascript:void(0);"  class="btn_addfalse">新增活动</a>';	
		     			}else{
		     				html += '<a href="javascript:void(0);" onclick="addAct('+data.pageResult[i].id+');" class="btn_add">新增活动</a>';
		     			}
					    html += '</div>';
						html += '<div class="clear"></div>';
						html += '</li>';
						html += '</ul>';
					}
				}
		     	
		     	if(data.totalPage > 1){
		     		html += '<div id="pageNum" class="tr pages"></div>';
		     	}
				
		     	if(data.pageResult == undefined || data.pageResult == null || data.pageResult.length == 0){
		     		html += '<li style="text-align: center;">';
		     		if(xFlag == '0'){
		     			html += '您还没有购买插件!';
		     		}
		     		if(xFlag == '1'){
		     			html += '您还没有可使用的插件!';
		     		}
		     		if(xFlag == '2'){
		     			html += '您没有已过期的插件!';
		     		}
		     		html += '</div>';
		     	}
				$('#myplugin').html(html); 
				initPages(currentPage,1,totalPage,totalPage); //初始化分页
			 }
		  }
  		}); 
	  }
		
	$(document).ready(function(){
			getPageDatas(1);  //初始化
			/**
			 *根据条件获取插件状态
			 *xFlag 0表示全部，1表示可使用,2表示已过期
			 */
			$('#allUse,#enableUse,#disableUse').click(function() {
				$(this).parent().find('a').attr("class", " ");
				$(this).attr("class", "active");
				xFlag = $(this).attr("value");
				getPageDatas(1); 
			});		
			
	});
	
		/**
		 * 通过插件ID，获取插件详细信息
		 */
		$('#mypluginlist li').mouseenter(function(){
			var pluginListLi = $(this);
			$.ajax({
				  type: "GET",
				  url : "${path }/pc/plugin/pluginPics",
				  data:{pluginId:pluginListLi.attr("data")},
				  dataType: "json",
				  success: function(data){
				  		var pluginId = data[0].pluginId;
				 		$('#'+pluginId+'').empty();
				 		var html = "";
						for (var i = 0; i < data.length; i++) {
						//插件详细图片信息
						html += "<dd><img src="+fileAccessPath+data[i].url+" /></dd>";
						}
						$('#'+pluginId+'').html(html);
				  }
		    }); 
		});
	
	/**
	*	添加活动
	**/
	function addAct(upid){
		//alert("upid="+upid);
		$.ajax({
			  type: "POST",
			  url : "${path }/pc/pluginAct/toAddAct",
			  data:{userPluginId:upid},
			  dataType: "json",
			  success: function(data){
				//  alert(data.status);
				  if(data.status == "1"){
					  location.href ="${path}/"+data.addUrl+"/"+upid;
				  }else if(data.status == "2"){
					  popupPrompt("很抱歉，每个插件只能创建3个活动！");
				  }else{
					  popupPrompt("系统繁忙，请稍后再试！");
				  }
				  
			  }
	   });
		
	}
	
	/**
	* 弹框提示
	*/
	function popupPrompt(str,fucName){
		var str  =  '<div class="prompt_box">\
						<div class="cont">\
							<h3><strong>提示</strong><span class="close">╳</span></h3>\
							<div class="msgs">\
								<p>'+ str +'</p>\
							</div>\
							<div class="btns">\
								<a class="btn_done">知道了</a>\
							</div>\
						</div>\
						<div class="bg"></div>\
					</div>';
		$('body').append(str);
		$('.prompt_box .close,.prompt_box .btn_cancel,.prompt_box .btn_done').click(function(){
			if( $(this).attr("class") == "btn_done" ){
				var count = 0;
				if(fucName){
					fucName();
				}
				$(this).parents('.prompt_box').remove();
			}else{
				$(this).parents('.prompt_box').remove();
			}
		});
	}
	
	//新手引导提示
	$(function(){
		if(window.localStorage){
			var storage = window.localStorage;
			if(storage.getItem("userPluginGuide") == null){
				showPageTip(1);
			}else{
				var guide = storage.getItem("userPluginGuide")
				showPageTip(guide);
			}
		}

	});	

	function showPageTip(guide){
		if(guide <= 1){
			$('.guide').attr("style","display:block;");
		}
		$('.guide').click(function(){
			guide += 1;
			if(guide <= 2 && guide > 0){
				$('.guide .cont img').fadeOut(500);
				$('.guide .cont img.plugin' + guide).fadeIn(500);
			}else if(guide > 2){
				$('.guide').fadeOut(500);
			}
		
		});
		if(window.localStorage){
			window.localStorage.setItem("userPluginGuide", 2);
		}
	}
	
</script>

