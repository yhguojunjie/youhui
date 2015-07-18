//配置内容切换
$(document).ready(function(){
	//展开收起
	$('.fold').click(function(){
		if($(this).text() == "收起"){
			$(this).text("展开");
			$(this).parent().next().attr("style","display:none;");
		}else{
			$(this).text("收起");
			$(this).parent().next().attr("style","display:block;");
		}
	});
	//插入广告
	$('.tab_ad label').click(function(){
		$(this).parent().find('label').attr("class"," ");
		$(this).attr("class","active");
		if($('.tab_ad .active').index() == 1){
			$(this).parent().parent().find('.ad_upcont').attr("style","display:none;");
		}else{
			$(this).parent().parent().find('.ad_upcont').attr("style","display:block;");
		}
	});
	
});

//活动规则
$(document).ready(function(){
	$('.actrule_msgbox textarea'). focus(function(){
		var actrule_msgbox = '';
		if($('.daterangepick').val()){
			actrule_msgbox += '• 活动时间：' + $('.daterangepick').val().replace(/\ -\ /," 至 ").replace(/-/g,"/").replace(/:00/g,"") + '。\n';
		}
		//if($('.game_count').val()){
		//	actrule_msgbox += '• 人每天最多游戏' + $('.game_count').val() + '次。\n';
		//} else{
		// 	actrule_msgbox += '• 每人每天最多游戏1次。\n';
		// }
		actrule_msgbox += '• 每个好友只能测试一次。\n';
		actrule_msgbox += '• 奖品数量有限，先到先得。\n';
		actrule_msgbox += '(模版演示，纯属体验，不予兑奖！)\n'
		$(this).text(actrule_msgbox);
	});

});

//新增虚假记录
$(document).ready(function(){
	$(document).on("click",".fingerprint .mark h4 a.bg_green",function(){
		$.ajax({
		  type: "POST",
		  url : path+"/pluginadmin/fingerprint/getPrizeJson",
		  data:{activityId:$('#activityId').val()},
		  dataType: "json",
		  success: function(data){
			popupFalsemark(data);
		  }
		}); 
	});
});

function popupFalsemark(dataarr){
	var str  =  '<div class="add_falsemark">\
					<form>\
						<h3><strong>提示</strong><span class="close">╳</span></h3>\
						<div class="space_10"></div>\
						<div class="row">\
							<strong>手机号：</strong>\
							<div class="fr">\
								<input id="phone_num" type="text" maxlength="11"/>\
								<br /><b>（页面上会将中间四位数字自动转为*）</b>\
							</div>\
							<div class="clear"></div>\
						</div>\
						<div class="row">\
							<strong>奖品名称：</strong>\
							<div class="fr">\
								<input id="gift_name" disabled="disabled" type="text" value="'+ dataarr[0].prizeName +'" />\
							</div>\
							<div class="clear"></div>\
						</div>\
						<div class="space_10"></div>\
						<div class="btns">\
							<a class="btn_cancel">取 消</a>\
							<a class="btn_done">确 定</a>\
						</div>\
					</form>\
					<div class="bg"></div>\
				</div>';
	$('body').append(str);

	$('.add_falsemark .close,.add_falsemark .btn_cancel,.add_falsemark .btn_done').click(function(){
		if( $(this).attr("class") == "btn_done" ){
			var popbox = $(this);
			$(this).parents('.prompt_box').remove();
			if($('#phone_num').val().length == 0){
				alert("请输入手机号");
				return;
			}
			var jsondata = {
				'activityId': $('#activityId').val(),
				'tel' : $('#phone_num').val(),
				'prizeName' : $('#gift_name').val(),
			};
		$.ajax({
			  type: "POST",
			  url : path+"/pluginadmin/fingerprint/addRecord",
			  data:jsondata,
			  dataType: "json",
			  success: function(data){
			  	if(data != 0){
			  		popbox.parents('.add_falsemark').remove();
			  		window.location.reload();
			  	}else{
			  		alert("增加失败");
			  	}
			  }
		}); 
		}else{
			$(this).parents('.add_falsemark').remove();
		}
	});
}

//兑换奖品
$(document).ready(function(){
	$(document).on("click",".fingerprint .mark .btn_green,.fingerprint .mark .btn_gray,.fingerprint .mark .btn_modify",function(){
		var notice = '是否更改为“<span class="state">已兑换</span>”状态';
		var type = 1;
		if($(this).attr("class") == "btn_gray"){
			notice = '是否更改为“<span class="state">未兑换</span>”状态';
		}
		if($(this).attr("class") == "btn_modify"){
			notice = '最高分：<input type="text" />';
			type = 2;
		}
		popupPrompt($(this),notice,type);
	});

});
function popupPrompt(obj,notice,type){
	var str  =  '<div class="prompt_box">\
					<div class="cont">\
						<h3><strong>提示</strong><span class="close">╳</span></h3>\
						<div class="msgs">\
							<p>'+ notice +'</p>\
						</div>\
						<div class="btns">\
							<a class="btn_cancel">取 消</a>\
							<a class="btn_done">确 定</a>\
						</div>\
					</div>\
					<div class="bg"></div>\
				</div>';
	$('body').append(str);
	$('.prompt_box .close,.prompt_box .btn_cancel,.prompt_box .btn_done').click(function(){
		if($(this).attr("class") == "btn_done"){
			popbox = $(this);
			if(type == 1){
				$.ajax({
					  type: "POST",
					  url : path+"/pluginadmin/fingerprint/changeOpStatus",
					  data:{id:obj.parents('tr').find('input').val()},
					  dataType: "json",
					  success: function(data){
			 				if(data.status == "0"){
			 					if(data.exchangeTime.length == 0){
			 						popbox.parents('.prompt_box').remove();
				 					obj.parents('tr').find('td').eq(4).find('p').text(data.exchangeTime);
									obj.attr("class","btn_green").text('未兑换');
			 					}else{
			 						popbox.parents('.prompt_box').remove();
				 					obj.parents('tr').find('td').eq(4).find('p').text(data.exchangeTime);
									obj.attr("class","btn_gray").text('已兑换');
			 					}
			 				}else{
			 					alert("操作失败");
			 				}
					  }
			    }); 
			}else{
				
			}
			
		}else{
			$(this).parents('.prompt_box').remove();
		}
	});

}

function addscreen_json(obj){
    var screen1 = {} ;
    var prizeName,showNum,realNum,picUrl,exchangeState;

    prizeName = obj.find('input').eq(0).val();
    screen1['prizeName'] = prizeName;

    showNum = obj.find('input').eq(1).val();
    screen1['showNum'] = showNum;

    realNum = obj.find('input').eq(2).val();
    screen1['realNum'] = realNum;

    picUrl= obj.find('input').eq(3).val();
    screen1['picUrl'] = picUrl;

 	if(obj.find('input[type="radio"]').eq(0).prop("checked")){
    	exchangeState = obj.find('input[type="radio"]').eq(0).val();
    }else{
    	exchangeState = obj.find('input[type="radio"]').eq(1).val();
    }
    screen1['exchangeState'] = exchangeState;

    obj.find('.screen_json').val(JSON.stringify(screen1));
}



