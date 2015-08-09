/*

	For TinyLi 2014-11-12
	LayoutjQuery v1.0

*/


/* ------------------------ 通过父亲找到对应类名的子元素 ------------------------- */

function getByClass(oParent,nClass){
	var eLe = oParent.getElementsByTagName('*');
	var aRrent  = [];
	for(var i=0; i<eLe.length; i++){
		if(eLe[i].className == nClass){
			aRrent.push(eLe[i]);
		}
	}
	return aRrent;
}

/* ------------------------ 通过父亲找到对应类名的子元素 ------------------------- */


/* ---------------------------------- 首页 开始 ---------------------------------- */


//登录
/*$(document).ready(function(){
	$('.login_btn').click(function(){
		$('.login_box').attr("style","display:block;");
		$('.login_box .main').attr("style","display:block;");
	});
	$('.login_box .close').click(function(){
		$('.login_box').attr("style","display:none;");
	});
});*/

//确认支付
// $(document).ready(function(){
// 	$('.btn_direct').click(function(){
// 		$('.pay_box').attr("style","display:block;");
// 		$('.pay_box form').attr("style","display:block;");
// 	});
// 	$('.pay_box .close,.pay_box .btn_cancel').click(function(){
// 		$('.pay_box').attr("style","display:none;");
// 	});
// });


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

//左侧内容展开
$(document).ready(function(){
	(function($){
		$.fn.hoverDelay = function(options){
			var defaults = {
				hoverDuring: 250,
				outDuring: 100,
				hoverEvent: function(){
					$.noop();
				},
				outEvent: function(){
					$.noop();
				}
			};
			var sets = $.extend(defaults,options || {});
			var hoverTimer, outTimer;
			return $(this).each(function(){
				$(this).hover(function(){
					clearTimeout(outTimer);
					hoverTimer = setTimeout(sets.hoverEvent, sets.hoverDuring);
				},function(){
					clearTimeout(hoverTimer);
					outTimer = setTimeout(sets.outEvent, sets.outDuring);
				});	
			});
		}
	})(jQuery);
	var delay;
	$('#mypluginlist li').hover(function(){
		delay = $(this);
	});
	$('#mypluginlist li').hoverDelay({
		hoverEvent:function(){
			delay.parent().find('dl').slideUp(300);
			delay.find('dl').slideDown(300);
		}
	});
	$('#mypluginlist li').mouseleave(function(){
		$(this).find('dl').slideUp(300);
	});
});

// 首页导航下拉
$(function(){
	$('.users_btn').hover(function(){
		$('.users_btns').attr("style","display:block;");
	},function(){
		$('.users_btns').attr("style","display:none;");
	});
});

//左侧内容排序切换
/*$(document).ready(function(){
	$('.tab_title .sorts span').click(function(){
		$(this).parent().find('span').attr("class"," ");
		$(this).attr("class","active");
	});
	$('.tab_title .sorts span').toggle(function(){
		$(this).find('em').attr("class","down_arrow");
	},function(){
		$(this).find('em').attr("class","up_arrow");
	});
});*/


//微信放大
$(document).ready(function(){
	$('.index .side .ico_code').hover(function(){
		$(this).next().attr("style","display:block;top:"+ ($(this).offset().top-1) +"px;left:"+ ($(this).offset().left-1) + "px;");
	});
	$('.index .side .code').mouseleave(function(){
		$(this).attr("style","display:none;");
	});
});

//微信浮动
$(document).ready(function(){
	/*$(window).scroll(function(){
		if($(window).scrollTop() > $('body').height()-200){
			$('.wechat').fadeIn(300);
		}else{
			$('.wechat').fadeOut(300);
		}
	});*/
	$('.wechat .close').click(function(){
		$(this).parent().remove();
	});
});

/* ---------------------------------- 首页 结束 ---------------------------------- */


/* ------------------------------- 我的淘插件 开始 ------------------------------- */

//左边侧宽度
$(document).ready(function(){
	$('.usermsg_subnav').height($('.usermsg_subnav').parents('.index').height());
});

//右侧切换
/*$(document).ready(function(){
	$('#myplugin_sortbtn a').click(function(){
		$(this).parent().find('a').attr("class"," ");
		$(this).attr("class","active");
		$(this).parents('.main').find('.myplugin .hide').attr("id"," ");
		$(this).parents('.main').find('.myplugin .hide').eq($(this).index()).attr("id","show");
	});
});*/

/* ------------------------------- 我的淘插件 结束 ------------------------------- */


/* -------------------------------- 我的活动 开始 -------------------------------- */

//右侧切换
/*$(document).ready(function(){
	$('#myactivity_sortbtn a').click(function(){
		$(this).parent().find('a').attr("class"," ");
		$(this).attr("class","active");
		$(this).parents('.main').find('.activity_list .hide').attr("id"," ");
		$(this).parents('.main').find('.activity_list .hide').eq($(this).index()).attr("id","showtable");
		$('.activity_list input').attr("checked",false);
		$('.add_activity .del').attr("style","display:none;");
	});
});*/

//全选&删除
/*$(document).ready(function(){
	$('.activity_list th input').change(function(){
		if($(this).attr("checked") == 'checked'){
			$(this).parents('table').find('td input').attr("checked",true);
			$('.add_activity .del').attr("style","display:inline-block;");
		}else{
			$(this).parents('table').find('td input').attr("checked",false);
			$('.add_activity .del').attr("style","display:none;");
		}
	});
	$('.activity_list td input').change(function(){
		$(this).parents('table').find('td input').each(function(){
			if($(this).attr("checked") == 'checked'){
				$('.add_activity .del').attr("style","display:inline-block;");
			}
		});
	});
	$(document).on("click",".add_activity .del",function(){
		$('.delactivity_box').fadeIn(50);
		$('.delactivity_box .cont').fadeIn(200);
		$('.delactivity_box .btn_done').click(function(){
			$('.delactivity_box').fadeOut(50);
			$('#showtable td input').each(function(){
				if($(this).attr("checked") == 'checked'){
					$(this).parents('tr').remove();
				}
			});
			$('.add_activity .del').attr("style","display:none;");
		});
	});
	$('.delactivity_box .btn_cancel,.delactivity_box .close').click(function(){
		$('.delactivity_box').fadeOut(50);
	});	
});*/

//选择插件
$(document).ready(function(){
	$('.pluginlist_box .pluginlist li').click(function(){
		$(this).parent().find('li').attr("class"," ");
		$(this).attr("class","active");
	});
	$(".add_activity .add").click(function(){
		$('.pluginlist_box').attr("style","display:block;");
		$('.pluginlist_box .cont').attr("style","display:block;");
	});
	$('.pluginlist_box .btn_done,.pluginlist_box .close').click(function(){
		$('.pluginlist_box').attr("style","display:none;");
	});

});


//活动推荐二维码放大
$(document).ready(function(){
	$('.activity_recommend li').hover(function(){
		$(this).find('.code').animate({
			opacity:'0.9',
			height:'70px',
			width:'70px'
		});
	},function(){
		$(this).find('.code').animate({
			opacity:'0.5',
			height:'20px',
			width:'20px'
		});
	});

});


/* -------------------------------- 我的活动 结束 -------------------------------- */


/* -------------------------------- 个人主页 开始 -------------------------------- */

//礼包被抢
$(document).ready(function(){
	$('.friend_list li').click(function(){
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

//更多活动
/*$(document).ready(function(){
	$('#seemore_avt').click(function(){
		var html = '<li><div class="fl">';
		html += '<a href="#" class="imgbox"><img src="images/app1.png" /></a>';
		if($('#developer').attr("id")=="developer"){
			html += '<h3><a href="#">干掉御泥坊</a></h3><p><em class="quot_left"></em><span>面霜类电商适用，烧到带敬爱较大搜救是敬爱是骄傲排水口烧到带敬爱较大的</span><em class="quot_right"></em></p>';
		}else{
			html += '<div class="space_20"></div><h3><a href="#">干掉御泥坊</a></h3>';
		}
		html += '<div class="clear"></div></div>';
		html += '<div class="fr"><img class="code" src="images/code1.jpg" /></div>';
		html += '<div class="clear"></div></li>';
		html = html + html + html + html + html;
		$(this).parent().prev().append(html);
	});
});*/

//更多好友
/*$(document).ready(function(){
	$('#seemore_frd').click(function(){
		var html = '<dd><i class="ico_delet"></i>';
		html += '<a href="#" class="avatar"><img src="images/app3.png" /></a><h3>';
		if($('#myself').attr("id")=="myself"){
			html += '<i class="ico_delet">×</i>';
		}
		html += '<a href="#">大宝</a></h3>';
		html += '<p>大宝天天见</p><div class="clear"></div></dd>';
		html = html + html + html + html + html;
		$(this).parent().before(html);
	});
});*/

//删除好友
$(document).ready(function(){
	$(document).on("click",".friend_list dd .ico_delet",function(){
		$('.delfriend_box').attr("style","display:block;");
		$('.delfriend_box .cont').attr("style","display:block;");
		var delelement = $(this);
		$('.delfriend_box .btn_done').click(function(){
			$('.delfriend_box').attr("style","display:none;");
			delelement.parents('dd').remove();
		});
	});
	$('.delfriend_box .btn_cancel,.delfriend_box .close').click(function(){
		$('.delfriend_box').attr("style","display:none;");
	});
});

//联系我
$(document).ready(function(){
	$(document).on("click",".visitcard .contact_btn a,#btn_contact",function(){
		if($(this).attr("class") == "pinkbtn" || $(this).attr("class") == "greenbtn" || $(this).attr("id") == "btn_contact"){
			$('.contact_sucbox').attr("style","display:block;");
			$('.contact_sucbox .cont').attr("style","display:block;");
		}else if($(this).attr("class") == "greybtn"){
			$('.contact_faibox').attr("style","display:block;");
			$('.contact_faibox .cont').attr("style","display:block;");
			var delelement = $(this);
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



/* -------------------------------- 个人主页 结束 -------------------------------- */


/* -------------------------------- 详情 开始 -------------------------------- */


//使用说明展开收起
$(document).ready(function(){
	$('.plugin_detail .text .see_more a').click(function(){
		if($(this).text() == "收起"){
			$(this).text("查看更多");
			$(this).parent().parent().attr("style","height:330px;");
		}else{
			$(this).text("收起");
			$(this).parent().parent().attr("style","height:auto;");
		}
	});
});

//正在进行的活动切换
$(document).ready(function(){
	var activiting_cont = $(".activity_recommend ul");
	$(".fold_across .next").click(function(){
		activiting_cont.animate({marginLeft:'-=840px'},function(){
			activiting_cont.find("li").eq(0).appendTo(activiting_cont);
			activiting_cont.find("li").eq(0).appendTo(activiting_cont);
			activiting_cont.find("li").eq(0).appendTo(activiting_cont);
			activiting_cont.attr("style","margin-left:0px;");
		});
	});
	$(".fold_across .prev").click(function(){
		activiting_cont.find("li:last").prependTo(activiting_cont);
		activiting_cont.find("li:last").prependTo(activiting_cont);
		activiting_cont.find("li:last").prependTo(activiting_cont);
		activiting_cont.attr("style","margin-left:-840px;");
		activiting_cont.animate({marginLeft:'+=840px'});
	});
	//var activitingcont = setInterval(function(){
	//	$(".next").click();
	//},3000);
	//activiting_cont.hover(function(){
	//	clearInterval(activitingcont);},function(){
	//		activitingcont = setInterval(function(){$(".fold_across .next").click();},3000);
	//});
});

//正在进行的活动切换
$(document).ready(function(){
	var activiting_contnew = $(".ativ_center ul");
	$(".fold_across .next").click(function(){
		activiting_contnew.animate({marginLeft:'-=828px'},function(){
			activiting_contnew.find("li").eq(0).appendTo(activiting_contnew);
			activiting_contnew.find("li").eq(0).appendTo(activiting_contnew);
			activiting_contnew.find("li").eq(0).appendTo(activiting_contnew);
			activiting_contnew.attr("style","margin-left:0px;");
		});
	});
	$(".fold_across .prev").click(function(){
		activiting_contnew.find("li:last").prependTo(activiting_contnew);
		activiting_contnew.find("li:last").prependTo(activiting_contnew);
		activiting_contnew.find("li:last").prependTo(activiting_contnew);
		activiting_contnew.attr("style","margin-left:-828px;");
		activiting_contnew.animate({marginLeft:'+=828px'});
	});
});

/* -------------------------------- 详情 结束 -------------------------------- */


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
		obj.parent().find('em').html(str);
	}else{
		obj.parent().append("<em style='color:red'>" + str + "</em>");
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



























