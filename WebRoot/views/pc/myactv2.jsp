<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
	<script type="text/javascript" src="${path }/js/zeroclipboard/ZeroClipboard.js"></script>
	<script type="text/javascript" src="${path }/js/pc/pagination.js"></script>
	<script type="text/javascript" src="${path }/js/jquery.zclip.min.js"></script>
<title>我的活动</title>
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
			<div class="fr w890 main">
				<div class="fr w860">
					<div class="tab_title">
						<div class="fl options" id="myactivity_sortbtn">
							<a id="allNum" value ="0" class="">全部</a>
							<a id="startNum" value ="1" >未开始(${startNum})</a>
							<a id="startingNum" value ="2" >正在进行(${startingNum})</a>
							<a id="endNum" value ="3" >已结束(${endNum})</a>
						</div>
						<div class="fr add_activity">
							<a class="del">删除</a>
							<!-- <a class="add">新增活动</a> -->
						</div>
						<div class="clear"></div>
					</div>
					<div class="cont activity_list" id="pluginActList">
					</div>
				</div>
				<div class="clear space_10"></div>
				<!-- 模版推荐 开始 -->
				<div class="fr w860">
					<div class="norl_title">
						<div class="fr"><a href="${url_pluginList }" class="more_btn">更多&gt;&gt;</a></div>
						<h3>模版推荐</h3>
					</div>
					<div class="cont" id="plugin_rec"></div>
				</div>
				<div class="clear"></div>
				<!-- 模版推荐 结束 -->
			</div>
			<!-- 右侧内容 结束 -->
			<div class="clear"></div>
		</div>
	<!-- 内容 结束 -->
	
	<!-- 支付弹出对话框 开始 -->
	<div class="pay_box" id="paysuc">
		<form action="${path }/pay/order/down" method="post" name="payForm"
			id="payForm">
			<h3>
				<strong>购买活动</strong> <span class="close"> <!-- 关闭登录窗口按钮 -->╳
				</span>
			</h3>
			<div class="clear space_20"></div>
			<div id="pay_contact"></div>
			<div class="row">
				有效期
				<select name="buyMon" id="buyMon" onchange="useMoney(this)">
					<option value="1" >1个月</option>
					<option value="2">2个月</option>
					<option value="3">3个月</option>
					<option value="4">4个月</option>
					<option value="5">5个月</option>
					<option value="6">6个月</option>
					<option value="7">7个月</option>
					<option value="8">8个月</option>
					<option value="9">9个月</option>
					<option value="10">10个月</option>
					<option value="11">11个月</option>
					<option value="12">12个月</option>
				</select>
			</div>
			<div id="pay_info"></div>
			<div class="clear space_20"></div>
			<div class="row">
				<a class="btn_cancel">取消</a> <a onclick="topay();" class="btn_pay">提交订单</a>
			</div>
			<div class="clear"></div>
			<div id="pluginIdInputHid">
				<input type="hidden" id="productId" name="productId" />
				<input type="hidden" id="charge" name="charge"  />
				<input type="hidden" id="productType" name="productType" value="2" />
				<input type="hidden" id="agid" name="agid" />
				<input type="hidden" id="upid" name="userPluginId" />
				<input type="hidden" id="salePrice"  />
			</div>
		</form>
		<div class="bg"></div>
	</div>
	
	<div class="pay_box" id="payfal">
		<div class="cont">
			<h3>
				<strong>购买活动</strong><span onclick="closepayfal()" class="close">╳</span>
			</h3>
			<div class="space_10"></div>
			<div class="space_5"></div>
			<p>暂不支持线上购买</p>
			<p>请联系客服</p>
			<div class="space_10"></div>
			<div class="space_5"></div>
			<div class="btns">
				<a onclick="closepayfal()" class="btn_cancel">知道了</a>
			</div>
		</div>
		<div class="bg"></div>
	</div>
	
	<!-- 支付弹出对话框 结束 -->
	
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
	
	function topay(){ 
		$("#payForm").submit();
	}
	
	//购买提示
	function buyfal(){
		$("#payfal").css("display","block");
	}

	//关闭支付失败
	function closepayfal(){
		$('#payfal').hide();
	}
	//计算价格
	function useMoney(obj){
		var m = $(obj).val();
		var pid = $('#productId').val();;
		var agid = $('#agid').val();
// 		console.log("m="+m);
// 		console.log("pid="+pid);
// 		console.log("agid="+agid);
		$.ajax({
			type: "POST",
			url : "${path }/pc/plugin/calculatePrice",
			data:{mon:m,pid:pid,agid:agid},
			dataType: "json",
			success: function(data){
				console.log("data.afprice="+data.afprice);
				console.log("salePrice="+$('#salePrice').val());
				var priceInit = $('#salePrice').val()*m;
				console.log("priceInit="+priceInit);
				if(priceInit != data.afprice){
 					$('#useMoneyOld').css("display","");
 				}else{
 					$('#useMoneyOld').css("display","none");
 				}
				$('#useMoneyOld').html(priceInit);
				$('#useMoney').html(data.afprice);
				if(data.afprice != 0 && data.afprice != undefined){
					$('#charge').val(data.afprice);
				}
				
			}
		});
	}
	
	//购买
	function tobuy(pluginId,upid){
		$.ajax({
			type: "POST",
			url : "${path }/pc/plugin/tobuy",
			data:{pluginId:pluginId},
			dataType: "json",
			success: function(data){
				if(data.status == "2"){
					$('.login_box').fadeIn(50);
					$('.login_box .main').fadeIn(200);
					window.location.href = "${basePath}login";
				}else if(data.status == "3" || data.status == "1"){
// 					console.log("data.status="+data.status);
// 					console.log("data.plgStatus="+data.plgStatus);
// 					console.log("upid="+upid);
					if(data.plgStatus == "1" || data.plgStatus == "5"){
						$('#pay_info').html('');
						$('#pay_contact').html('');
						$('#buyMon').find('option').eq(0).attr("selected","selected");
						$('#paysuc').css("display","block");
						$('#haveMoney').html(data.repreCoin);
						var salePrice = data.salePrice;
						if(salePrice > 0){
							var pay_info='<h4>消费：<span>￥<del style="display:none;" id="useMoneyOld">'+data.salePrice+'</del>&nbsp;<em id="useMoney">'+data.salePrice+'</em></span></h4>';
								pay_info+='<div class="row">';
								pay_info+='<label><input type="checkbox" name="isRepreCoin" id="isRepreCoin">使用代币：￥<span id="haveMoney">'+data.repreCoin+'</span></label>';
								pay_info+='</div>';
							$('#pay_info').html(pay_info);
							$('#charge').val(data.salePrice);
							$('#salePrice').val(data.salePrice);
						}else{
							$('#pay_contact').html('<div class="row">请先提交订单，稍后与我们联系，协商定价</div><div class="clear space_20"></div>');
							$('#charge').val();
							$('#salePrice').val();
						}
						
						$('#productId').val(pluginId);
						$('#agid').val(data.agid);
						$('#upid').val(upid)
						$('.pay_box .close,.pay_box .btn_cancel').click(function(){
							$('.pay_box').fadeOut(50);
						});
					}else{
						$("#payfal").css("display","block");
					}
			
					
				}else{
					popupPrompt("系统繁忙，请稍后再试！");	  
				}
			}
			  
		}); 
	}
		
	
	var url_pluginDetail = '${url_pluginDetail}';
	var url_devUserPage = '${url_devUserPage}';
	var fileAccessPath = '${fileAccessPath}';
	var url_getQcode ='${url_getQcode}';
	var cjpcShowServer = '${cjpcShowServer}';
	var xFlag = '0';
	
	$(document).ready(function(){
		getPageDatas(1);  //初始化
		$('#allNum,#startNum,#startingNum,#endNum').click(function() {
			$(this).parent().find('a').attr("class", " ");
			$(this).attr("class", "active");
			xFlag = $(this).attr("value");
			getPageDatas(1); 
		});		
		$("#plugin_rec").load("${basePath}pc/recommend/getPluginRecList");
	});
	
	//插件页面
	function getPageDatas(currentPage){
		$.ajax({
			  type: "GET",
			  url : "${path }/pc/my/ajaxmyActList?currentPage="+currentPage+"&xFlag="+xFlag,
			  dataType: "json",
			  success: function(data){
				  if(data != null && data !=undefined){
					 var currentPage = data.currentPage;//当前页数
					 var totalPage = data.totalPage;//总页数
					 var count = data.count; //每页显示多少
					 $('#pluginActList').empty();
			  	
			     	 if(data.pageResult != undefined && data.pageResult != null && data.pageResult.length != 0){
			     	 	 var html = '';
			     		 for (var i = 0; i < data.pageResult.length; i++) {
			     			var overdueTime;
			     			if(data.pageResult[i].overdueTime != null && data.pageResult[i].overdueTime != undefined
									&& data.pageResult[i].overdueTime != ''){
								var  str1 = data.pageResult[i].overdueTime.toString();
			   					str1 =  str1.replace(/-/g,"/");
			   					overdueTime = new Date(str1);
			   					overdueTime = overdueTime.Format("yyyy-MM-dd hh:mm"); 
							}
			      			var startTime;
			     			if(data.pageResult[i].startTime != null && data.pageResult[i].startTime != undefined
									&& data.pageResult[i].startTime != ''){
								var  str1 = data.pageResult[i].startTime.toString();
			   					str1 =  str1.replace(/-/g,"/");
			   					startTime = new Date(str1);
			   					startTime = startTime.Format("yyyy-MM-dd hh:mm"); 
							}
			     			
			     			var endTime;
			     			if(data.pageResult[i].endTime != null && data.pageResult[i].endTime != undefined
									&& data.pageResult[i].endTime != ''){
								var  str1 = data.pageResult[i].endTime.toString();
			   					str1 =  str1.replace(/-/g,"/");
			   					endTime = new Date(str1);
			   					endTime = endTime.Format("yyyy-MM-dd hh:mm"); 
							}
			     			
			     		  
					     	html += '<dl>';
					     	html += '<dd>';
					     	html += '<div class="fl">';
					     	if(data.pageResult[i].actId == null || data.pageResult[i].actId == undefined 
					     			|| data.pageResult[i].actId == ''){ //该模板没有活动
					     		html += '<a href="javascript:void(0);" class="img"><img src="'+data.pageResult[i].pluginIcon+'" /></a>';
						     	html += '<h3><a href="javascript:void(0);">'+data.pageResult[i].pluginName+'</a></h3>';
					     	}else{
					     		var cjpjShowPath = cjpcShowServer+data.pageResult[i].actAccessUrl+'/'+data.pageResult[i].actId;
					     	   	html += '<a href="'+cjpjShowPath+'" class="img"><img src="'+data.pageResult[i].actIcon+'" /></a>';
						     	html += '<h3><a href="'+cjpjShowPath+'">'+data.pageResult[i].actTitle+'</a></h3>';
						     	html += '<p>';
						     	html += '总浏览量：<em>'+data.pageResult[i].browseNum+'</em>';
						     	html += '</p>';
						     	html += '<div class="clear space_5"></div>';
						     	html += '<p>';
						     	html += '活动时间：'+startTime+' 到 '+endTime+' &nbsp;&nbsp;';
						     	html += '<em class="end">'+data.pageResult[i].actState+'</em>';
						     	html += '</p>';
					     	}
					     	html += '</div>';
					     	if(data.pageResult[i].actId != null && data.pageResult[i].actId != undefined 
					     			&& data.pageResult[i].actId != ''){
						     	html += '<div class="fr">';
						     	html += '<textarea style="display:none" id="mytext">'+cjpcShowServer+data.pageResult[i].actAccessUrl+'/'+data.pageResult[i].actId+'</textarea>';
						     	html += '<img onclick="showBigimg(this);" id="imges" src="'+url_getQcode+cjpcShowServer+data.pageResult[i].actAccessUrl+'/'+data.pageResult[i].actId+'" />';
						     	html += '<a class="btn_copy" id="immg" onclick="init();" >双击复制地址</a>';
						     	html += '</div>';
					     	}
					     	html += '<div class="clear space_10"></div>';
					     	html += '<div class="btns">';
					     	html += '<span>';
					     	html += '使用模版：<a href="'+url_pluginDetail+'/'+data.pageResult[i].pluginId+'">《'+data.pageResult[i].pluginName+'》</a>';
					     	html += '&nbsp;&nbsp;&nbsp;&nbsp;到期时间：'+overdueTime+'&nbsp;&nbsp;';
					     	html += '<em>'+data.pageResult[i].pluginState+'</em>';
					     	html += '</span>';
					     	html += '<a  onclick="tobuy('+data.pageResult[i].pluginId+','+data.pageResult[i].userPluginId+')" class="renew btn_direct">续费</a>';
					     	
					     	if(data.pageResult[i].actId != null && data.pageResult[i].actId != undefined 
					     			&& data.pageResult[i].actId != ''){
					     		html += '<a href="'+data.pageResult[i].actEditUrl+'" class="set">配置</a>';
					     	}else{
					     		html += '<a href="'+data.pageResult[i].actAddUrl+'" class="set">配置</a>';
					     	}
					     	html += '</div>';
					     	html += '</dd>';
					     	html += '</dl>';
						}
					}
			     	
			     	if(data.totalPage > 1){
			     		html += '<div id="pageNum" class="tr pages"></div>';
			     	}
					
			     	if(data.pageResult == undefined || data.pageResult == null || data.pageResult.length == 0){
			     		html += '<li style="text-align:center;color:#999;">';
			     		if(xFlag == '0'){
			     			html += '您还没有活动!';
			     			html=html.toString().replace("undefined",""); 
			     		}
			     		if(xFlag == '1'){
			     			html += '您还没有未开始的活动!';
			     			html=html.toString().replace("undefined",""); 
			     		}
			     		if(xFlag == '2'){
			     			html += '您还没有正在进行的活动！';
			     			html=html.toString().replace("undefined",""); 
			     		}
			     		if(xFlag == '3'){
			     			html += '您还没有已结束的活动！';
			     			html=html.toString().replace("undefined",""); 
			     		}
			     		html += '</div>';
			     	}
					$('#pluginActList').html(html); 
					initPages(currentPage,1,totalPage,totalPage); //初始化分页
				 }
			  }
	  		}); 
		  }
	
	//显示大图(code)
	$(function(){
		$('.activity_list td .code').click(function(){
			$('body').append('<div class="bigimg_box">\
								<img src="'+$(this).attr("src")+'" />\
							</div>');
			$('.bigimg_box img').load(function(){
				$(this).attr("style","margin-top:-" + ($(this).height()/2-5) +"px;margin-left:-" + ($(this).width()/2-5) +"px;");
			});
		});
		$(document).on("click",".bigimg_box",function(){
			$(this).remove();
		});
	});

	//回到顶部
	$(function(){
		$(".gotop").click(function(){
			$("body,html").animate({scrollTop:0},300);
		});
		$(window).scroll(function(){
			if($(window).scrollTop() > 10){
				$('.gotop').attr("style","display:block;");
			}else{
				$('.gotop').attr("style","display:none;");
			}
		});
	});
	//双击复制
    	function init(){
        $('#immg').zclip({ 
            path: "${path }/js/zeroclipboard/ZeroClipboard.swf", 
            copy: function(){//复制内容 
                return $('#mytext').val(); 
            }
        }); 
    	}
	
	//支付
	$(function(){
		$('.btn_direct').click(function(){
			if(true){
				$('#paysuc').css("display","block");
			}else{
				$('#payfal').css("display","block");
			}
		});
		$('.pay_box .close,.pay_box .btn_cancel').click(function(){
			$('.pay_box').attr("style","display:none;");
		});
	});
	
	
	function alertset(){
		$('body').append('<div class="alertset">\
			<dl style="display:block;">\
				<dt>请您先配置活动</dd>\
				<dd class="tc space_20"><a class="btn_cancel" onclick="hidealert(this);">知道了</a></dd>\
			</dl>\
			<div class="bg"></div>\
		</div>');
	}
	
	function hidealert(obj){
		$(obj).parents(".alertset").remove();
	}
	
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
/* 	$(function(){
		if(window.localStorage){
			var storage = window.localStorage;
			if(storage.getItem("userPluginGuide") == null){
				showPageTip(1);
			}else{
				var guide = storage.getItem("userPluginGuide")
				showPageTip(guide);
			}
		}

	});	 */

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

