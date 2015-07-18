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
		if($('.player_count').val()){
			actrule_msgbox += '• 参赛者最多' + $('.player_count').val() + '人，投票者不限人数。\n';
		}// else{
		// 	actrule_msgbox += '• 每人每天最多游戏1次。\n';
		// }
		actrule_msgbox += '• 每人只有一次投票机会。\n';
		actrule_msgbox += '(模版演示，纯属体验，不予兑奖！)\n'
		$(this).text(actrule_msgbox);
	});

});

//兑换奖品
$(document).ready(function(){
	$(document).on("click",".vote .mark .btn_green,.vote .mark .btn_del,.vote .mark .btn_modify",function(){
		var notice = '是否更改为“<span class="state">已兑换</span>”状态';
		var type = 1;
		if($(this).attr("class") == "btn_del"){
			notice = '确定是否删除？';
		}
		if($(this).attr("class") == "btn_modify"){
			notice = '票数：<input type="text" />';
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
					  url : path+"/pluginadmin/vote/delete",
					  data:{id:obj.parents('tr').find('input').val()},
					  dataType: "json",
					  success: function(data){
			 				if(data.status == "0"){
			 					window.location.reload();
			 				}else{
			 					alert("操作失败");
			 				}
					  }
			    }); 
			}else{
				$.ajax({
					  type: "POST",
					  url : path+"/pluginadmin/vote/updateVoteNum",
					  data:{id:obj.parents('tr').find('input').val(), voteNum:popbox.parents('.prompt_box').find('input').val()},
					  dataType: "json",
					  success: function(data){
			 				if(data.status == "0"){
				 				obj.parents('tr').find('td').eq(2).find('span').text(popbox.parents('.prompt_box').find('input').val());
				 				popbox.parents('.prompt_box').remove();
			 				}else{
			 					alert("操作失败");
			 				}
					  }
			    }); 
			}
			
		}else{
			$(this).parents('.prompt_box').remove();
		}
	});

}

function addscreen_json(obj){
    var screen1 = {} ;
    var prizeType,prizeName,showNum,realNum,picUrl;

    prizeType = obj.attr("data-id");
    screen1['prizeType'] = prizeType;

    prizeName = obj.find('input').eq(0).val();
    screen1['prizeName'] = prizeName;

    showNum = obj.find('input').eq(1).val();
    screen1['showNum'] = showNum;

    realNum = obj.find('input').eq(2).val();
    screen1['realNum'] = realNum;

    picUrl= obj.find('input').eq(3).val();
    screen1['picUrl'] = picUrl;

    obj.find('.screen_json').val(JSON.stringify(screen1));
}

