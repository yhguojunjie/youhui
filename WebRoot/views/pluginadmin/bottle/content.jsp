<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="p" uri="page"%>
<%@ include file="../constant.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/basic.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/global.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/layout.css" />
<link type="text/css" rel="stylesheet" href="${adminCssPath}/bottle.css"/>
<link type="text/css" rel="stylesheet" href="${commonCssPath}/kindeditor/default.css"/>
<script type="text/javascript" src="${commonJsPath}/jquery.js"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js"></script>
<script type="text/javascript" src="${adminJsPath}/bottle.js"></script>
<script type="text/javascript" src="${commonJsPath}/common.js"></script>
<title>内容管理</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="../../pc/header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 bottle">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<c:choose>
					<c:when test="${userPluginId != null && userPluginId != 0}">
						<a href="${path}/pluginadmin/bottle/add/${userPluginId}">活动设置</a>
					</c:when>
					<c:otherwise>
						<a href="${path}/pluginadmin/bottle/edit/${activityId}">活动设置</a>
					</c:otherwise>
				</c:choose>
				<a class="active" href="#">内容管理</a>
				<a href="${path}/pluginadmin/bottle/record/${activityId}?upid=${userPluginId}">中奖名单</a>
			</div>
			<div class="cont mark">
				<h4 class="tr">
					<span class="fl">内容总数：<em>${recordVos.totalCount}</em></span>
					<c:choose>
						<c:when test="${activityId != 0}">
							<a class="bg_green" id="add_content">新增内容</a>
						</c:when>
						<c:otherwise>
							<a class="" id="">新增内容</a>
						</c:otherwise>
					</c:choose>
				</h4>
				<form action="" id="recordListForm" name="recordListForm" method="get">
					<input type="hidden" name="currentPage"/>
					<input type="hidden" name="count"/>
				<table>
					<tr>
						<th>序号</th>
						<th>名称</th>
						<th>操作</th>
					</tr>
					<c:forEach var="list" items="${recordVos.pageResult}" varStatus="s">
						<tr>
							<td>${s.count + count*(currentPage-1)}</td>
							<td>${list.name}</td>
							<td>
								<p><a class="btn_modify edit_content">编辑</a></p>
							</td>
							<input type="hidden" value="${list.id}"/>
						</tr>
					</c:forEach>
				</table>
				</form>
				<input type="hidden" id="activityId" value="${activityId}">
			</div>
			<!-- 分页 开始 -->
			<div class="tr pages">
				<p:page page="recordVos" formname="recordListForm" />
			</div>
			<!-- 分页 结束 -->
		</div>
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始 -->
		<%@ include file="../../pc/contactSupport.jsp" %>
		<!-- 右侧内容 结束 -->
		<div class="clear"></div>
	</div>
	<!-- 内容 结束 -->
	<!-- 底部 开始 -->
	<%@ include file="../../pc/footer.jsp" %>
	<!-- 底部 结束 -->
	<form action="" method="post" onsubmit="return onClick();" id="myform" name="myform">
	<div class="cont_box" style="display:none;">
		<h3>
			<em class="close" onclick="hideContbox()">╳</em> 
			<strong>内容添加</strong>
			</h3>
		<div class="space_20"></div>
		<div class="row" id="contImg">
			<span>图片：</span>
			<div class="fr">
				<div class="preview"><img class="w100 h120" src=""></div>
				<div class="upload_file">
					<input type="text" class="fileuploadclass" name="picUrl"/>
					<span><em class="ico_image"><!-- 图标 --></em>选择封面</span>
				</div>
				<em>建议尺寸：500*600（或等比），大小不超过50K。</em>
			</div>
			<div class="clear"></div>
		</div>
		<div class="row">
			<span>名称：</span>
			<div class="fr">
				<input type="text" id="contName" name="name" value="" maxlength="16" />
				<em>必填，长度不超过16个字符</em>
			</div>
			<div class="clear"></div>
		</div>
		<div class="row">
			<span>描述：</span>
			<div class="fr">
				<textarea id="contIntro" name="descs" maxlength="60"></textarea>
				<em>必填，长度不超过60个字符</em>
			</div>
			<div class="clear"></div>
		</div>
		<div class="row">
			<div class="fr">
				<a class="done">保&nbsp;存</a>
				<a class="cancel" onclick="hideContbox()">取&nbsp;消</a>
			</div>
			<div class="clear"></div>
		</div>
		<input type="hidden" name="id" value="" />
	</div>
	</form>
</body>
<script type="text/javascript">
	KindEditor.ready(function(K) {
	    var editor = K.editor({
	    	formatUploadUrl:false,
	        allowFileManager: false,
	        uploadJson: "<%=path%>/common/toServer",
	        filePostName:"Filedata",
	        extraFileUploadParams:{uploadType:"image"},
	    });
	    $('.fileuploadclass').live("click",function(e) {
	        editor.loadPlugin('image',function() {
	            editor.plugin.imageDialog({
	                showRemote : false,
	                clickFn: function(url, filename, width, height, border, align) {
	                    $(e.target).parent().prev().find('img').attr("src","${resUrl}"+url);
	                    $(e.target).val(url);
	                    editor.hideDialog();
	                }
	            });
	        });
	    });
	});
	//隐藏
	function hideContbox(){
		$('.cont_box').hide();
	}

	$(function(){
		$('#add_content').click(function(){
			//更新图片
			var imgUrl = '${adminImgPath}/bottle/img.jpg';
			$('#contImg').find('.preview img').attr("src",imgUrl);
			//$('#contImg').find('.fileuploadclass').val(imgUrl);
			//更新名称
			var contName = '';
			$('#contName').val(contName);
			//更新介绍
			var contIntro = '';
			$('#contIntro').val(contIntro);

			$('.cont_box').show();

			$('.cont_box .done').click(function(){
				$("#myform").attr("action", "${path}/pluginadmin/bottle/addContent/${activityId}");
				$('#myform').submit();
			});
		});

		$(document).on("click",'.edit_content',function(){
			var id = $(this).parent().parent().parent().find('input[type="hidden"]').eq(0).val();
			$.ajax({
				  type: "POST",
				  url : path+"/pluginadmin/bottle/editContent",
				  data:{id:id},
				  dataType: "json",
				  success: function(data){
		 			 if(data.status == "0"){
		 			 	$('.cont_box').show();

		 			 	$('#contImg').find('.preview img').attr("src",resUrl+data.content.picUrl);
						$('#contImg').find('.fileuploadclass').val(data.content.picUrl);

		 			 	$('#contName').val(data.content.name);
		 			 	$('#contIntro').val(data.content.descs);

		 			 	$('.cont_box').find('input[type="hidden"]').val(data.content.id);

		 			 	$('.cont_box .done').click(function(){
							$("#myform").attr("action", "${path}/pluginadmin/bottle/saveContent/${activityId}");
							$('#myform').submit();
						});
		 			 }else{
		 			 	alert("编辑失败");
		 			 }
				  }
		    }); 
		});
	});

	//保存该条内容
	function onClick(){
		//得到该面板的数据
		var imgurl = $('#contImg').find('.fileuploadclass').val();
		var name = $('#contName').val();
		var desc = $('#contIntro').val();

		if(imgurl != null && imgurl.length == 0){alert("请上传图片");return false;}
		if(name != null && name.length == 0){alert("请输入名称");return false;}
		if(desc != null && desc.length == 0){alert("请填写描述");return false;}
	}
</script>
</html>
