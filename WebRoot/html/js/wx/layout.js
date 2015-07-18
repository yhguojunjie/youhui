
//菜单
$(function(){
	$('.menu_btn').toggle(function(){
		$(this).attr("style","transform:rotate(90deg);-ms-transform:rotate(90deg);-moz-transform:rotate(90deg);-webkit-transform:rotate(90deg);-o-transform:rotate(90deg);");
		$('.menu').attr("style","left:0;");
	},function(){
		$(this).attr("style","transform:rotate(0deg);-ms-transform:rotate(0deg);-moz-transform:rotate(0deg);-webkit-transform:rotate(0deg);-o-transform:rotate(0deg);");
		$('.menu').attr("style","left:-100%;");
	});
});


//模版预告滚动
$(function(){
	setInterval(function(){
		$('.temp_notice ul').animate({marginTop:'-36px'},function(){
			$('.temp_notice ul').append($('.temp_notice ul li').eq(0));
			$('.temp_notice ul').css("margin-top","0");
		});
	},2000);
	
});



//关闭提示框

function closePrompt(obj){
	$(obj).parents('.prompt').remove();
}

/* ------------------------------ 表单验证 开始 ------------------------------ */


//表单错误信息提示
function insertNotice(obj,str){
	if(obj.parent().find('em').prop("nodeName") == "EM"){
		obj.parent().find('em').html(str);
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




























































































































































