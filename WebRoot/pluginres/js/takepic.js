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

// $(document).ready(function(){
// 	$('.actrule_msgbox textarea'). focus(function(){
// 		var actrule_msgbox = '';
// 		if($('.daterangepick').val()){
// 			actrule_msgbox += '• 活动时间：' + $('.daterangepick').val().replace(/\ -\ /," 到 ").replace(/-/g,"/").replace(/:00/g,"") + '。\n';
// 		}
// 		if($('.act_count').val()){
// 			actrule_msgbox += '• 每人每天最多拍' + $('.act_count').val() + '次大白。\n';
// 		}else{
// 			actrule_msgbox += '• 每人每天最多拍1次大白\n';
// 		}
// 		actrule_msgbox += '• 分享到朋友圈可额外获得1次拍大白机会。\n'
// 		actrule_msgbox += '(模版演示，纯属体验，不予兑奖！)\n'
// 		$(this).text(actrule_msgbox);
// 	});

// });
