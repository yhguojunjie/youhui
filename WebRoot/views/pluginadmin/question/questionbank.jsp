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
<link type="text/css" rel="stylesheet" href="${adminCssPath}/question.css"/>
<script type="text/javascript" src="${commonJsPath}/jquery.js"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js"></script>
<script type="text/javascript" src="${adminJsPath}/question.js"></script>
<script type="text/javascript" src="${commonJsPath}/common.js"></script>
<title>题库管理</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="../../pc/header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 question">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<c:choose>
					<c:when test="${userPluginId != null && userPluginId != 0}">
						<a href="${path}/pluginadmin/question/add/${userPluginId}">活动设置</a>
					</c:when>
					<c:otherwise>
						<a href="${path}/pluginadmin/question/edit/${activityId}">活动设置</a>
					</c:otherwise>
				</c:choose>
				<a class="active" href="#">题库管理</a>
				<a href="${path}/pluginadmin/question/record/${activityId}?upid=${userPluginId}">中奖名单</a>
				<a href="${path}/pluginadmin/question/rank/${activityId}?upid=${userPluginId}">排行榜</a>
			</div>
			<div class="cont mark">
				<h4 class="tr">
					<span class="fl">题目总数：<em>${recordVos.totalCount}</em></span>
					<c:choose>
						<c:when test="${activityId != 0}">
							<a class="bg_green" id="add_ques">新增题目</a>
						</c:when>
						<c:otherwise>
							<a class="" id="">新增题目</a>
						</c:otherwise>
					</c:choose>
				</h4>
				<form action="" id="recordListForm" name="recordListForm" method="get">
					<input type="hidden" name="currentPage"/>
					<input type="hidden" name="count"/>
				<table>
					<tr>
						<th>序号</th>
						<th>题目名称</th>
						<th>操作</th>
					</tr>
					<c:forEach var="list" items="${recordVos.pageResult}" varStatus="s">
						<tr>
							<td>${s.count + count*(currentPage-1)}</td>
							<td><script>var obj=JSON.parse('${list.question}');document.writeln(obj[0]['qcont']);</script></td>
							<td>
								<p><a class="btn_green edit_ques">编辑</a></p>
								<p><a class="btn_green clear_ques">删除</a></p>
							</td>
							<input type="hidden" value="${list.id}"/>
							<input type="hidden" value='${list.question}'/>
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
</body>

</html>
<script type="text/javascript">

	//保存单个题目数据
	function saveQues(obj){
		//单个问题
		var quescont  = {
							"qcont" : "",//问题
							"acont" : "",//答案
							"awnum" : "A"//正确答案
						};
		quescont.qcont = obj.parents('.ques_box').find('#questitle').val();//更新问题
		var tabanswer = [];//选项名称和内容
		//组织选项数据
		obj.parents('.ques_box').find('.text').each(function(){
			if($(this).find('input[type="text"]').val() != ''){
				tabanswer.push({ "num" : $(this).attr("tabdata") , "cont" : $(this).find('input[type="text"]').val() });
			}
		});
		quescont.acont = tabanswer;//更新选项
		quescont.awnum = $('.ques_box #anum').val();//更新答案
		obj.parents('.ques_box').remove();//关闭窗口

		console.log(quescont);
		var o = [];
		o.push(quescont);
		return o;
	}

	$(function(){

		//关闭题目框
		$(document).on("click",'.ques_box .cancel,.ques_box .close',function(){
			$(this).parents('.ques_box').remove();
		});

		//新增题目
		$('#add_ques').click(function(){
			$('body').append('<div class="ques_box">\
							<h3>\
								<em class="close">╳</em> \
								<strong>题目添加</strong>\
							</h3>\
							<div class="space_30"></div>\
							<div class="row">\
								<span>题目名称：</span>\
								<div class="fr">\
									<input id="questitle" type="text" style="width:330px;" maxlength="60" />\
									<em>必填，长度不超过60个字符</em>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<span>选项A：</span>\
								<div class="fr">\
									<ul class="conts">\
										<li class="text" tabdata="A">\
											<input type="text" id="text1" maxlength="16"/>\
											<em>必填，长度不超过16个字符</em>\
										</li>\
									</ul>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<span>选项B：</span>\
								<div class="fr">\
									<ul class="conts">\
										<li class="text" tabdata="B">\
											<input type="text" id="text2" maxlength="16"/>\
											<em>必填，长度不超过16个字符</em>\
										</li>\
									</ul>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<span>选项C：</span>\
								<div class="fr">\
									<ul class="conts">\
										<li class="text" tabdata="C">\
											<input type="text" maxlength="16"/>\
											<em>选填，长度不超过16个字符</em>\
										</li>\
									</ul>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<span>选项D：</span>\
								<div class="fr">\
									<ul class="conts">\
										<li class="text" tabdata="D">\
											<input type="text" maxlength="16"/>\
											<em>选填，长度不超过16个字符</em>\
										</li>\
									</ul>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<span>选项E：</span>\
								<div class="fr">\
									<ul class="conts">\
										<li class="text" tabdata="E">\
											<input type="text" maxlength="16"/>\
											<em>选填，长度不超过16个字符</em>\
										</li>\
									</ul>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<span>题目答案：</span>\
								<div class="fr">\
									<select id="anum">\
										<option value="A">选项A</option>\
										<option value="B">选项B</option>\
										<option value="C">选项C</option>\
										<option value="D">选项D</option>\
										<option value="E">选项E</option>\
									</select>\
									<b style="font-weight:normal;color:#e0848f;"></b>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<div class="fr">\
									<a class="done">保&nbsp;存</a>\
									<a class="cancel">取&nbsp;消</a>\
								</div>\
								<div class="clear"></div>\
							</div>\
						</div>');
		});

		//保存新增的题目数据
		$(document).on("click",'.ques_box .done',function(){

			var title = $('#questitle').val();
			var text1 = $('#text1').val();
			var text2 = $('#text2').val();
			if(title != null && title.length == 0){alert("请输入题目");return;}
			if(text1 != null && text1.length == 0){alert("请填写选项A");return;}
			if(text2 != null && text2.length == 0){alert("请填写选项B");return;}

			if($('.conts li[tabdata="'+$('#anum').val()+'"]').find('input').val().length == 0){
				$('#anum').parent().find('b').text('您的答案与选项不符合');
				return;
			}

			var json = saveQues($(this));
			var jsonstr = JSON.stringify(json);
			$.ajax({
				  type: "POST",
				  url : path+"/pluginadmin/question/addQuestion/${activityId}",
				  data:{question:jsonstr},
				  dataType: "json",
				  success: function(data){
		 			 if(data.status == "0"){
		 			 	window.location.reload();
		 			 }else{
		 			 	alert("新增失败");
		 			 }
				  }
		    }); 
		});

		//删除题目
		$(document).on("click",'.clear_ques',function(){
			var id = $(this).parent().parent().parent().find('input[type="hidden"]').eq(0).val();
			$.ajax({
					  type: "POST",
					  url : path+"/pluginadmin/question/removeQuestion",
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

		//编辑题目
		$(document).on("click",'.edit_ques',function(){
			var id = $(this).parent().parent().parent().find('input[type="hidden"]').eq(0).val();
			var json = $(this).parent().parent().parent().find('input[type="hidden"]').eq(1).val();
			var quesjson = JSON.parse(''+json);
			var ques = quesjson[0];

			var queshtml ='<div class="ques_box">\
							<h3>\
								<em class="close">╳</em> \
								<strong>题目添加</strong>\
							</h3>\
							<div class="space_30"></div>\
							<div class="row">\
								<span>题目名称：</span>\
								<div class="fr">\
									<input id="questitle" type="text" style="width:330px;" value="'+ques.qcont+'" maxlength="60"/>\
									<em>必填，长度不超过60个字符</em>\
								</div>\
								<div class="clear"></div>\
							</div>';
			if(ques.acont.length >= 1){
				queshtml += '<div class="row">\
									<span>选项A：</span>\
									<div class="fr">\
										<ul class="conts">\
											<li class="text" tabdata="A">\
												<input type="text" value="'+ques.acont[0].cont+'" id="text1" maxlength="16"/>\
												<em>必填，长度不超过16个字符</em>\
											</li>\
										</ul>\
									</div>\
									<div class="clear"></div>\
								</div>';

			}else{
				queshtml += '<div class="row">\
									<span>选项A：</span>\
									<div class="fr">\
										<ul class="conts">\
											<li class="text" tabdata="A">\
												<input type="text"  id="text1" maxlength="16"/>\
												<em>必填，长度不超过16个字符</em>\
											</li>\
										</ul>\
									</div>\
									<div class="clear"></div>\
								</div>';
			}
			if(ques.acont.length >= 2){
				queshtml += '<div class="row">\
									<span>选项B：</span>\
									<div class="fr">\
										<ul class="conts">\
											<li class="text" tabdata="B">\
												<input type="text" value="'+ques.acont[1].cont+'" id="text2" maxlength="16"/>\
												<em>必填，长度不超过16个字符</em>\
											</li>\
										</ul>\
									</div>\
									<div class="clear"></div>\
								</div>';

			}else{
				queshtml += '<div class="row">\
									<span>选项B：</span>\
									<div class="fr">\
										<ul class="conts">\
											<li class="text" tabdata="B">\
												<input type="text"  id="text2" maxlength="16"/>\
												<em>必填，长度不超过16个字符</em>\
											</li>\
										</ul>\
									</div>\
									<div class="clear"></div>\
								</div>';
			}							
			if(ques.acont.length >= 3){
				queshtml += '<div class="row">\
									<span>选项C：</span>\
									<div class="fr">\
										<ul class="conts">\
											<li class="text" tabdata="C">\
												<input type="text" value="'+ques.acont[2].cont+'" maxlength="16"/>\
												<em>选填，长度不超过16个字符</em>\
											</li>\
										</ul>\
									</div>\
									<div class="clear"></div>\
								</div>';

			}else{
				queshtml += '<div class="row">\
									<span>选项C：</span>\
									<div class="fr">\
										<ul class="conts">\
											<li class="text" tabdata="C">\
												<input type="text"  maxlength="16"/>\
												<em>选填，长度不超过16个字符</em>\
											</li>\
										</ul>\
									</div>\
									<div class="clear"></div>\
								</div>';
			}
			if(ques.acont.length >= 4){
				queshtml += '<div class="row">\
									<span>选项D：</span>\
									<div class="fr">\
										<ul class="conts">\
											<li class="text" tabdata="D">\
												<input type="text" value="'+ques.acont[3].cont+'" maxlength="16"/>\
												<em>选填，长度不超过16个字符</em>\
											</li>\
										</ul>\
									</div>\
									<div class="clear"></div>\
								</div>';

			}else{
				queshtml += '<div class="row">\
									<span>选项D：</span>\
									<div class="fr">\
										<ul class="conts">\
											<li class="text" tabdata="D">\
												<input type="text" maxlength="16"/>\
												<em>选填，长度不超过16个字符</em>\
											</li>\
										</ul>\
									</div>\
									<div class="clear"></div>\
								</div>';
			}
			if(ques.acont.length == 5){
				queshtml += '<div class="row">\
									<span>选项E：</span>\
									<div class="fr">\
										<ul class="conts">\
											<li class="text" tabdata="E">\
												<input type="text" value="'+ques.acont[4].cont+'" maxlength="16"/>\
												<em>选填，长度不超过16个字符</em>\
											</li>\
										</ul>\
									</div>\
									<div class="clear"></div>\
								</div>';

			}else{
				queshtml += '<div class="row">\
									<span>选项E：</span>\
									<div class="fr">\
										<ul class="conts">\
											<li class="text" tabdata="E">\
												<input type="text" maxlength="16"/>\
												<em>选填，长度不超过16个字符</em>\
											</li>\
										</ul>\
									</div>\
									<div class="clear"></div>\
								</div>';
			}
			queshtml += '<div class="row">\
								<span>题目答案：</span>\
								<div class="fr">\
							<select id="anum" value="'+ques.awnum+'">';
			if(ques.awnum == "A"){
				queshtml += '<option selected="selected" value="A">选项A</option>\
							 <option value="B">选项B</option>\
							 <option value="C">选项C</option>\
							 <option value="D">选项D</option>\
							 <option value="E">选项E</option>';

			}else if(ques.awnum == "B"){
				queshtml += '<option value="A">选项A</option>\
							 <option selected="selected" value="B">选项B</option>\
							 <option value="C">选项C</option>\
							 <option value="D">选项D</option>\
							 <option value="E">选项E</option>';

			}else if(ques.awnum == "C"){
				queshtml += '<option value="A">选项A</option>\
							 <option value="B">选项B</option>\
							 <option selected="selected" value="C">选项C</option>\
							 <option value="D">选项D</option>\
							 <option value="E">选项E</option>';
				
			}else if(ques.awnum == "D"){
				queshtml += '<option value="A">选项A</option>\
							 <option value="B">选项B</option>\
							 <option value="C">选项C</option>\
							 <option selected="selected" value="D">选项D</option>\
							 <option value="E">选项E</option>';
				
			}else{
				queshtml += '<option value="A">选项A</option>\
							 <option value="B">选项B</option>\
							 <option value="C">选项C</option>\
							 <option value="D">选项D</option>\
							 <option selected="selected" value="E">选项E</option>';
			}
			queshtml += '</select>\
									<b style="font-weight:normal;color:#e0848f;"></b>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<div class="fr">\
									<a class="editdone">保&nbsp;存</a>\
									<a class="cancel">取&nbsp;消</a>\
								</div>\
								<div class="clear"></div>\
							</div>\
						</div>';
			$('body').append(queshtml);

			//保存修改的题目数据
			$('.ques_box .editdone').click(function(){
				var title = $('#questitle').val();
				var text1 = $('#text1').val();
				var text2 = $('#text2').val();
				if(title != null && title.length == 0){alert("请输入题目");return;}
				if(text1 != null && text1.length == 0){alert("请填写选项A");return;}
				if(text2 != null && text2.length == 0){alert("请填写选项B");return;}

				if($('.conts li[tabdata="'+$('#anum').val()+'"]').find('input').val().length == 0){
					$('#anum').parent().find('b').text('您的答案与选项不符合');
					return;
				}

				var json = saveQues($(this));
				var jsonstr = JSON.stringify(json);

				$.ajax({
					  type: "POST",
					  url : path+"/pluginadmin/question/updateQuestion/${activityId}",
					  data:{id:id,question:jsonstr},
					  dataType: "json",
					  success: function(data){
			 			 if(data.status == "0"){
			 			 	window.location.reload();
			 			 }else{
			 			 	alert("编辑失败");
			 			 }
					  }
			    }); 

			});
		});
	});

</script>
