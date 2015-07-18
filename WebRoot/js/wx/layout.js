/*

	For TinyLi 2014-11-20
	LayoutjQuery v1.0

*/


//焦点图
$(document).ready(function(){
	TouchSlide({ 
		slideCell:"#scrollBox",
		titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
		effect:"leftLoop", 
		autoPage:true, //自动分页
		switchLoad:"_src", //切换加载，真实图片路径为"_src" 
		autoPlay:true,
		interTime:4000,
		delayTime:400
	});
	$('#scrollBox .bd li').height($(document).width()/2.15);
	$('.scrollBox .hd').attr("style","margin-left:-"+$('.scrollBox .hd').width()/2+"px;");
	$('.ativ_center #scrollBox .bd li').height($(document).width()/4.5);
});


//首页切换
//$(document).ready(function(){
//
//	$('#index_tab a').click(function(){
//		$(this).parent().find('a').attr("class","");
//		$(this).attr("class","active");
//	});
//
//});
/*
	$('#index_tab a').click(function(){
		$(this).parent().find('a').attr("class","");
		$(this).attr("class","active");
		$('#index_tab .active').click(function(){
			$('#index_tab em').attr("class"," ");
			$(this).find('em').attr("class","active");
		});
	});

	$("#index_tab .active").click(function(){
		$('#index_tab em').attr("class"," ");
		$(this).find('em').attr("class","active");
	});


var ny=0,rotYINT;
$(document).ready(function(){
	$("#index_tab .active").click(function(){
		$(this).find('em').attr("class"," ");
		clearInterval(rotYINT);
		rotYINT=setInterval('startYRotate()',10);
	});
});
function startYRotate(){
	ny=ny+1;
	$('#index_tab em').attr("style","transform:rotateX(90deg);-webkit-transform:rotateX(90deg);-moz-transform:rotateX(90deg);");
	if (ny==90){
		clearInterval(rotYINT);
	}
}*/


//确认支付
$(document).ready(function(){
	$(document).on("click",".btn_direct",function(event){
		$('.pay_box').attr("style","display:block;");
		$('.pay_box form').attr("style","display:block;");
		event.stopPropagation();
	});
	$('.pay_box .close,.pay_box .btn_cancel').click(function(){
		$('.pay_box').attr("style","display:none;");
	});
});


//赚代币
$(document).ready(function(){
	$('.paycash').click(function(){
		$('.earn_box').attr("style","display:block;");
		$('.earn_box dl').attr("style","display:block;");
	});
	$('.earn_box .btn_cancel').click(function(){
		$('.earn_box').attr("style","display:none;");
	});
});


//分享&邀好友
$(document).ready(function(){
	$('.earn_box .go_share,.earn_box .go_invite,.float_btns .btn_invit').click(function(){
		if($(this).attr("class") == "go_share"){
			$('.share .notice').html("首次将插件库分享到朋友圈<br />即可获得10个代币");
		}else if($(this).attr("class") == "go_invite"){
			$('.share .notice').html("邀请微信好友成为你的“插友”，突破10人<br />即可获得20个代币");
		}else{
			$('.share .notice').html("邀请微信好友成为你的“插友”<br />来抢礼包");
		}
		$('.share').attr("style","display:block;");
	});
	$('.share').click(function(){
		$('.share').attr("style","display:none;");
	});
});


//新增活动
$(document).ready(function(){
	$(document).on("click",".btn_add",function(){
		$('.gopc_box').attr("style","display:block;");
	});
	/*$('.gopc_box').click(function(){
		$(this).hide(200);
	});*/
});


//抢礼包
$(document).ready(function(){
	$('.float_btns .btn_befriend').click(function(){
		var gift = 0;
		if(gift == 0){
			$('.robgift_box .text').html('<p><span>没有抢到礼包</span></p><p><span>首次成为别人的插友，奖励代币：<em>￥10</em></span></p>');
			$('.robgift_box .tc').html('<a class="btn_done">去使用</a>');
		}else if(gift == 1){
			$('.robgift_box .text').html('<p><span>没有抢到礼包</span></p><p><span>去别家试试！</span></p>');
			$('.robgift_box .tc').html('<a class="btn_cancel">知道了</a>');
		}else{
			$('.robgift_box .text').html('<p><span>恭喜抢到礼包！</span></p><p><span>代币：<em>￥500</em></span></p>');
			$('.robgift_box .tc').html('<a class="btn_done">去使用</a>');
		}
		$('.robgift_box').attr("style","display:block;");
		$('.robgift_box .cont').attr("style","display:block;");
	});
	$(document).on("click",".robgift_box .close,.robgift_box .btn_done,.robgift_box .btn_cancel",function(){
		$('.robgift_box').attr("style","display:none;");
	});
});



//礼包被抢
$(document).ready(function(){
	$('.friend .title li').click(function(){
		if($(this).attr("class") == "active"){
			$('.nogift_box').attr("style","display:block;");
			$('.nogift_box .cont').attr("style","display:block;");
		}else{
			$('.remaininggift_box').attr("style","display:block;");
			$('.remaininggift_box .cont').attr("style","display:block;");
		}
	});
	$('.nogift_box .btn_cancel,.nogift_box .close').click(function(){
		$('.nogift_box').attr("style","display:none;");
	});
	$('.remaininggift_box .btn_cancel,.remaininggift_box .close').click(function(){
		$('.remaininggift_box').attr("style","display:none;");
	});
});


//联系我
$(document).ready(function(){
	$('.user_center .contact_btn a').click(function(){
		if($(this).attr("class") == "btn_pink" || $(this).attr("class") == "btn_green"){
			$('.contact_sucbox').attr("style","display:block;");
			$('.contact_sucbox .cont').attr("style","display:block;");
		}else if($(this).attr("class") == "btn_grey"){
				$('.contact_faibox').attr("style","display:block;");
				$('.contact_faibox .cont').attr("style","display:block;");
				$('.contact_faibox .btn_done').click(function(){
				$('.contact_faibox').attr("style","display:none;");
			});
		}else{
			return 0;
		}
	});
	$('.contact_sucbox .btn_cancel,.contact_sucbox .close,.contact_sucbox .row a').click(function(){
		$('.contact_sucbox').attr("style","display:none;");
	});
	$('.contact_faibox .btn_cancel,.contact_faibox .close,.contact_faibox p a').click(function(){
		$('.contact_faibox').attr("style","display:none;");
	});

});


//编辑个人中心
//$(document).ready(function(){
//	$('.float_btns .btn_edit').click(function(){
//		$('.my_activitys ul').attr("class","can_del");
//		$('.friend_list_cont').attr("class","friend_list_cont can_del");
//		$('.user_center .card h3 .btn_edit').attr("style","display:block;");
//		$(this).parent().attr("id"," ");
//		$(this).parent().next().attr("id","show");
//		$('.active_layout,.cover_layout').attr("style","display:block;");
//	});
//
//	$('.float_btns .btn_done').click(function(){
//		$('.my_activitys ul').attr("class"," ");
//		$('.friend_list_cont').attr("class","friend_list_cont");
//		$(this).parent().attr("id"," ");
//		$(this).parent().prev().attr("id","show");
//
//		$('.friend_list li .ico_delete').each(function(){
//			$(this).parents('li').remove();
//		});
//		$('.my_activitys li .ico_delete').each(function(){
//			$(this).parents('li').remove();
//		});
//		$('.user_center .card h3 .btn_edit').attr("style","display:none;");
//		$('.active_layout,.cover_layout').attr("style","display:none;");
//
//	});
//	$('.float_btns .btn_cancel').click(function(){
//		$('.my_activitys ul').attr("class"," ");
//		$('.friend_list_cont').attr("class","friend_list_cont");
//		$(this).parent().attr("id"," ");
//		$(this).parent().prev().attr("id","show");
//
//		$('.friend_list li .ico_delete').each(function(){
//			$(this).parents('li').attr("style","");
//		});
//		$('.my_activitys li .ico_delete').each(function(){
//			$(this).parents('li').attr("style","");
//		});
//		$('.user_center .card h3 .btn_edit').attr("style","display:none;");
//		$('.active_layout,.cover_layout').attr("style","display:none;");
//		
//	});
//
//});



//删除好友
/*$(document).ready(function(){
	$('.friend_list li .ico_bedelete').click(function(){
		$(this).attr("class","ico_delete");
		$(this).parents('li').attr("style","display:none;");
	});
});*/
/*$(document).ready(function(){
	$('.friend_list li .ico_delete').click(function(){
		$('.delfriend_box').show(50);
		$('.delfriend_box .cont').show(200);
		var delelement = $(this);
		$('.delfriend_box .btn_done').click(function(){
			$('.delfriend_box').hide(50);
			delelement.parents('li').remove();
		});
	});
	$('.delfriend_box .btn_cancel,.delfriend_box .close').click(function(){
		$('.delfriend_box').hide(50);
	});
});*/


//删除活动
/*$(document).ready(function(){
	$('.my_activitys li .ico_bedelete').click(function(){
		$(this).attr("class","ico_delete");
		$(this).parents('li').attr("style","display:none;");
	});
});*/
/*$(document).ready(function(){
	$('.my_activitys li .ico_delete').click(function(){
		$('.delfriend_box').show(50);
		$('.delfriend_box .cont').show(200);
		var delelement = $(this);
		$('.delfriend_box .btn_done').click(function(){
			$('.delfriend_box').hide(50);
			delelement.parents('li').remove();
		});
	});
	$('.delfriend_box .btn_cancel,.delfriend_box .close').click(function(){
		$('.delfriend_box').hide(50);
	});
});*/


//使用说明展开
$(document).ready(function(){
	$('.use_explain .fold').click(function(){
		if($(this).text() == "收起"){
			$(this).text("展开");
			$(this).parent().find('.words').attr("style","height:150px;");
		}else{
			$(this).text("收起");
			$(this).parent().find('.words').attr("style","height:auto;");
		}
	}); 
});

//菜单
$(document).ready(function(){
	$('.fixed_menubtn').toggle(function(){
		$(this).attr("style","transform:rotate(90deg);-ms-transform:rotate(90deg);-moz-transform:rotate(90deg);-webkit-transform:rotate(90deg);-o-transform:rotate(90deg);");
		$('.fixed_menu').attr("style","left:0;");
	},function(){
		$(this).attr("style","transform:rotate(0deg);-ms-transform:rotate(0deg);-moz-transform:rotate(0deg);-webkit-transform:rotate(0deg);-o-transform:rotate(0deg);");
		$('.fixed_menu').attr("style","left:-100%;");
	});
});

/* ------------------------------ 表单验证 开始 ------------------------------ */


//表单错误信息提示
function insertNotice(obj,str){
	/*if(obj.next().prop("nodeName") == "EM" || obj.next().next().prop("nodeName") == "EM"){
		obj.next().text(str);
	}else if(obj.next().prop("nodeName") == "SPAN"){
		obj.next().after("<em>" + str + "</em>");
	}else{
		obj.after("<em>" + str + "</em>");
	}*/
	if(obj.parent().find('em').prop("nodeName") == "EM"){
		obj.parent().find('em').text(str);
	}else{
		obj.parent().append("<em>" + str + "</em>");
	}
	
}

//表单字段验证
function verify(string,rule,addrules) {
	//string(字段),rule(规则),addrules(附加规则)
	//判断是否为空
	if (rule == '' || rule == 'require') {
		if (string == '') {
			return false;
		}else {
			return true;
		}
	}
	//判断是否为数字
	if (rule == 'number') {
		if (!/^[0-9]{0,20}$/.test(string)) {
			return false;
		}else {
			return true;
		}
	}
	//判断是位数
	if (addrules == 'count') {
		if (string.length != rule){
			return false;
		}else {
			return true;
		}
	}
	//判断长度
	if (addrules == 'length') {
		var arr = rule.split(',');
		if (string.length < arr[0] || string.length > arr[1]) {
			return false;
		}else {
			return true;
		}
	}
	//验证两个字段值是否相同
	if (addrules == 'equal') {
		if (string != rule) {
			return false;
		}else {
			return true;
		}
	}
	//正则表达式判断
	if (addrules == 'regexp') {
		if (!rule.test(string)) {
			return false;
		}else {
			return true;
		}
	}
	//验证邮箱
	if (rule == 'email') {
		if (!/^([a-zA-Z0-9_\.-]+)@([\da-zDA-Z0-9\.-]+)\.([a-zA-Z0-9\.]{2,6})$/.test(string)) {
			return false;
		}else {
			return true;
		}
	}
	//验证URL地址
	if (rule == 'url') {
		// if (!/^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/.test(string)) {
		// 	return false;
		// }else {
		// 	return true;
		// }
		return true;
	}
	//验证IP地址
	if (rule == 'ip') {
		if (!/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(string)) {
			return false;
		}else {
			return true;
		}
	}
	//验证用户名，包含中文
	if (rule == 'username_zh') {
		if (!/^[a-zA-Z0-9_-]|[\u4e00-\u9fa5]{2,16}$/.test(string)) {
			return false;
		}else {
			return true;
		}
	}
	//验证用户名，不包含中文
	if (rule == 'username') {
		if (!/^[a-zA-Z0-9_-]{2,16}$/.test(string)) {
			return false;
		}else {
			return true;
		}
	}
}

/* ------------------------------ 表单验证 结束 ------------------------------ */







































































































