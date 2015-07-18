<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="p" uri="page"%>
<%@ include file="../constant.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/bootstrap/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/bootstrap/daterangepicker.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/basic.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/global.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/layout.css" />
<link type="text/css" rel="stylesheet" href="${adminCssPath}/guessgame.css"/>
<link type="text/css" rel="stylesheet" href="${commonCssPath}/kindeditor/default.css"/>
<script type="text/javascript" src="${commonJsPath}/jquery.js"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/moment.min.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/daterangepicker.js"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js"></script>
<script type="text/javascript" src="${adminJsPath}/guessgame.js"></script>
<script type="text/javascript" src="${commonJsPath}/pluginpublic.js"></script>
<script type="text/javascript" src="${commonJsPath}/common.js"></script>
<title>比赛管理</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="../../pc/header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 guessgame">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<c:choose>
					<c:when test="${userPluginId != null && userPluginId != 0}">
						<a href="${path}/pluginadmin/guessgame/add/${userPluginId}">活动设置</a>
					</c:when>
					<c:otherwise>
						<a href="${path}/pluginadmin/guessgame/edit/${activityId}">活动设置</a>
					</c:otherwise>
				</c:choose>
				<a href="#" class="active">比赛管理</a>
				<a href="${path}/pluginadmin/guessgame/betrecord/${activityId}?upid=${userPluginId}">投注记录</a>
				<a href="${path}/pluginadmin/guessgame/record/${activityId}?upid=${userPluginId}">中奖名单</a>
			</div>
			<div class="cont mark">
				<h4 class="tr">
					<span class="fl">比赛总数：<em>${recordVos.totalCount}</em></span>
					<c:choose>
						<c:when test="${activityId != 0}">
							<a class="bg_green" id="add_game">新增比赛</a>
						</c:when>
						<c:otherwise>
							<a class="" id="">新增比赛</a>
						</c:otherwise>
					</c:choose>
				</h4>
				<form action="" id="recordListForm" name="recordListForm" method="get">
					<input type="hidden" name="currentPage"/>
					<input type="hidden" name="count"/>
				<table>
					<tr>
						<th>序号</th>
						<th>比赛名称</th>
						<th>比赛结果</th>
						<th>操作</th>
					</tr>
					<c:forEach var="list" items="${recordVos.pageResult}" varStatus="s">
						<tr>
							<td>${s.count + count*(currentPage-1)}</td>
							<td>${list.name}：${list.leftTeam}VS${list.rightTeam}</td>
							<td>
							<c:choose>
								<c:when test="${bJoinType == '0' && list.leftScore != -1 && list.rightScore != -1}">
									<c:choose>
										<c:when test="${list.leftScore > list.rightScore}">
											左赢
										</c:when>
										<c:when test="${list.leftScore < list.rightScore}">
											右赢
										</c:when>
										<c:otherwise>
											平局
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:if test="${list.leftScore != -1 && list.rightScore != -1}">${list.leftScore}:${list.rightScore}</c:if>
								</c:otherwise>
							</c:choose>
							</td>
							<td>
								<p><a class="btn_green edit_game">编辑</a></p>
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
	<div class="game_box" style="display:none;position:absolute;">
		<h3>
			<em class="close">╳</em> 
			<strong>比赛添加</strong>
		</h3>
		<div class="space_30"></div>
		<div class="row gametitle">
			<span>比赛名称：</span>
			<div class="fr">
				<input type="text" name="name" maxlength="50" style="width:330px;" />
				<em>必填，长度不超过50个字符</em>
			</div>
			<div class="clear"></div>
		</div>
		<div class="row gameleft_msg">
			<span>左：</span>
			<div class="fr">
				<ul class="conts">
					<li class="text">
						<input type="text" name="leftTeam" maxlength="16" />
						<em>必填，长度不超过16个字符</em>
					</li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="row gameleft_img">
			<span>左图：</span>
			<div class="fr">
				<ul class="conts">
					<li class="img">
						<img src="" />
						<div class="pickimg">
							<input type="text" class="fileuploadclass" name="leftPicUrl"/>
							<!-- 你要用下面这个，把上面的干掉就行 -->
							<!-- <input type="text" /> -->
							上传图片
						</div>
						<em>建议尺寸：100*100（或等比），大小不超过50K</em>
					</li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="row gameright_msg">
			<span>右：</span>
			<div class="fr">
				<ul class="conts">
					<li class="text">
						<input type="text" maxlength="16" name="rightTeam"/>
						<em>必填，长度不超过16个字符</em>
					</li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="row gameright_img">
			<span>右图：</span>
			<div class="fr">
				<ul class="conts">
					<li class="img">
						<img src="" />
						<div class="pickimg">
							<input type="text" class="fileuploadclass" name="rightPicUrl"/>
							<!-- 你要用下面这个，把上面的干掉就行 -->
							<!-- <input type="text" /> -->
							上传图片
						</div>
						<em>建议尺寸：100*100（或等比），大小不超过50K</em>
					</li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="row gametime">
			<span>比赛时间：</span>
			<div class="fr">
				<ul class="conts">
					<li class="text">
						<input type="text" name="activityTime" class="daterangepick input-xlarge" style="width:280px;" value="" readonly="readonly" />
						<em>必填</em>
					</li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="row">
			<span>比赛结果：</span>
			<div class="fr">
				<ul class="conts">
					<li class="text">
						<input class="gameleft_point" type="text" style="width:60px;text-align:center;" name="leftScore"/>∶<input class="gameright_point" type="text" style="width:60px;text-align:center;" name="rightScore"/>
						<em>选填（猜输赢请用1:0，0:1，1:1分别表示左赢，右赢，平局）</em>
					</li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="row">
			<div class="fr">
				<a class="done">保&nbsp;存</a>
				<a class="cancel">取&nbsp;消</a>
			</div>
			<div class="clear"></div>
		</div>
		<input type="hidden" name="id" value="" />
	</div>
	</form>
	<div class="prompt_box" id="bedeldone" style="display:none;">
		<div class="cont">
			<h3><strong>提示</strong><span class="close">╳</span></h3>
			<div class="msgs">
				<p>不可恢复，确定删除？</p>
			</div>
			<div class="btns">
				<a class="btn_cancel">取 消</a>
				<a class="btn_done">确 定</a>
			</div>
		</div>
		<div class="bg"></div>
	</div>
</body>

</html>
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
	                    $(e.target).parent().prev().attr("src","${resUrl}"+url);
	                    $(e.target).val(url);
	                    editor.hideDialog();
	                }
	            });
	        });
	    });
	});

	function onClick(){
		var title = $('.gametitle').find('input[type="text"]').val();
		var leftteam = $('.gameleft_msg').find('input[type="text"]').val();
		var lefturl = $('.gameleft_img .fileuploadclass').val();
		var rightteam = $('.gameright_msg').find('input[type="text"]').val();
		var righturl = $('.gameright_img .fileuploadclass').val();
		var time = $('.gametime').find('input[type="text"]').val();
		var leftpoint = $('.gameleft_point').val();
		var rightpoint = $('.gameright_point').val();
		if(leftpoint.length != 0 || rightpoint.length != 0){
			var endtime=new Date(time.substr(22,43)).getTime();
			var today=new Date().getTime();
			if(endtime > today){
				alert("比赛结束之后才能填写比赛结果");
				return false;
			}
		}
		if(title != null && title.length == 0){alert("请输入比赛名称");return false;}
		if(leftteam != null && leftteam.length == 0){alert("请输入左队名称");return false;}
		if(lefturl != null && lefturl.length == 0){alert("请上传左图");return false;}
		if(rightteam != null && rightteam.length == 0){alert("请输入右队名称");return false;}
		if(righturl != null && righturl.length == 0){alert("请上传右图");return false;}
		if(time != null && time.length == 0){alert("请选择比赛时间");return false;}
	}

	$(function(){

		//关闭比赛框
		$(document).on("click",'.game_box .cancel,.game_box .close',function(){
			$(this).parents('.game_box').css('display','none');
		});

		//新增比赛
		$('#add_game').click(function(){
			$('.game_box').css('display','block');
			$('.gametitle').find('input[type="text"]').val("");
			$('.gameleft_msg').find('input[type="text"]').val("");
			$('.gameleft_img').find('img').attr("src","${adminImgPath}/guessgame/def.png");
			$('.gameright_msg').find('input[type="text"]').val("");
			$('.gameright_img').find('img').attr("src","${adminImgPath}/guessgame/def.png");
			$('.gametime').find('input[type="text"]').val("");
			$('.gameleft_point').val("");
			$('.gameright_point').val("");

			//保存新增的比赛数据
			$('.game_box .done').click(function(){
				$("#myform").attr("action", "${path}/pluginadmin/guessgame/addGame/${activityId}");
				$('#myform').submit();
				//$('.game_box .done').unbind('click');
			});
		});

		//删除比赛
		$(document).on("click",'.clear_game',function(){
			var obj = $(this);
			$('#bedeldone').css("display","block");
			$('.prompt_box .btn_done').click(function(){
				$(this).parents('.prompt_box').css("display","none");
				var id = obj.parent().parent().parent().find('input[type="hidden"]').eq(0).val();
				$.ajax({
					  type: "POST",
					  url : path+"/pluginadmin/guessgame/removeGame",
					  data:{id:id},
					  dataType: "json",
					  success: function(data){
			 			 if(data.status == "0"){
			 			 	window.location.reload();
			 			 }else{
			 			 	alert("删除失败");
			 			 }
					  }
				}); 
			});
		});

		//隐藏删除提示框
		$(document).on("click",'.prompt_box .close,.prompt_box .btn_cancel',function(){
			$(this).parents('.prompt_box').css("display","none");
		});

		//编辑比赛
		$(document).on("click",'.edit_game',function(){
			var id = $(this).parent().parent().parent().find('input[type="hidden"]').eq(0).val();
			$.ajax({
				  type: "POST",
				  url : path+"/pluginadmin/guessgame/editGame",
				  data:{id:id},
				  dataType: "json",
				  success: function(data){
		 			 if(data.status == "0"){
		 			 	$('.game_box').css('display','block');
		 			 	$('.game_box').find('input[type="hidden"]').val(data.admin.id);
		 			 	$('.gametitle').find('input[type="text"]').val(data.admin.name);
						$('.gameleft_msg').find('input[type="text"]').val(data.admin.leftTeam);
						$('.gameleft_img').find('img').attr("src",resUrl+data.admin.leftPicUrl);
						$('.gameleft_img .fileuploadclass').val(data.admin.leftPicUrl);
						$('.gameright_msg').find('input[type="text"]').val(data.admin.rightTeam);
						$('.gameright_img').find('img').attr("src",resUrl+data.admin.rightPicUrl);
						$('.gameright_img .fileuploadclass').val(data.admin.rightPicUrl);
						$('.gametime').find('input[type="text"]').val(data.admin.startTime+" - "+data.admin.endTime);
						if(data.admin.leftScore != -1){
							$('.gameleft_point').val(""+data.admin.leftScore);
						}else{
							$('.gameleft_point').val("");
						}
						if(data.admin.rightScore != -1){
							$('.gameright_point').val(""+data.admin.rightScore);
						}else{
							$('.gameright_point').val("");
						}
						$('.game_box .done').click(function(){
							$("#myform").attr("action", "${path}/pluginadmin/guessgame/saveGame/${activityId}");
							$('#myform').submit();
							//$('.game_box .done').unbind('click');
						});
		 			 }else{
		 			 	alert("编辑失败");
		 			 }
				  }
		    }); 

		});

	});

</script>
