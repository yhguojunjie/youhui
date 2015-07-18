<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
	<title>成为渠道</title>
</head>
<body>
	<!-- 头部 开始 -->
		<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
		<!-- 成为渠道 开始 -->
	<div class="w1190 register">
		<form class="cont" action="${path }/pc/channel/doBeChannel" method="post" onsubmit="return verify_submit()">
			<h2>成为渠道</h2>
			<div class="row">
				<span class="fl">类型</span>
				<div class="fr">
					<select id="trenchtype" name="type">
						<option value="1">微信订阅号</option>
						<option value="2">微信服务号</option>
						<option value="3">微信个人号</option>
						<option value="4">微博帐号</option>
						<option value="5">网站</option>
						<option value="6">APP</option>
					</select>
				</div>
				<div class="clear"></div>
			</div>
			<input type="hidden" name="token" value="${token}"> 
			<div class="space_10"></div>
			<div class="row" id="code">
				<span class="fl">二维码</span>
				<div class="fr">
				    <input id="preview1"  class="preview11" name="qrcode" type="hidden" />
					<img class="preview" id="preview111" src="${path}/images/registerlogo.png" />
					<div class="uploadimg"><input class="verify_logo"  onchange="imgToBase641(this)" type="file" verify="false" />上传图片</div>
					<i>*</i><b>推荐150X50或等比尺寸</b><em id="preview1111">请上传二维码</em>
				</div>
				<div class="clear"></div>
				<span class="fl">关注链接</span>
				<div class="fr">
					<input class="verify_cont" name="attentionLink" maxLength=40 type="text" style="width:400px;" />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="row" id="url" style="display:none;">
				<span class="fl">网址</span>
				<div class="fr">
					<input class="verify_cont" id="website1" name="website" maxLength=40 type="text" style="width:400px;" />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="row" id="app" style="display:none;">
				<span class="fl">网址</span>
				<div class="fr">
					<input class="verify_cont" id="website2" name="website" maxLength=40 type="text" style="width:400px;" />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
				<span class="fl">下载链接</span>
				<div class="fr">
					<input class="verify_cont" name="downloadLink" maxLength=50 type="text" style="width:400px;" />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="row" id="person" style="display:none;">
				<span class="fl">二维码</span>
				<div class="fr">
				    <input id="preview1"  class="preview22" name="qrcode" type="hidden" />
					<img class="preview" id="preview222" src="${path}/images/registerlogo.png" />
					<div class="uploadimg"><input class="verify_logo"  onchange="imgToBase641(this)" type="file" verify="false" />上传图片</div>
					<i>*</i><b>推荐150X50或等比尺寸</b><em id="preview2222">请上传二维码</em>
				</div>
				<div class="clear"></div>
				<span class="fl">微信号</span>
				<div class="fr">
					<input class="verify_cont" name="microNum" type="text" />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="row" id="number" style="display:none;">
				<span class="fl">手机号</span>
				<div class="fr">
					<input class="verify_cont" type="text" style="width:200px;" />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="space_10"></div>
			<div class="row">
				<span class="fl">名称</span>
				<div class="fr">
					<input class="verify_cont" name="name" type="text" maxLength=50  />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="space_10"></div>
			<div class="row">
				<span class="fl">LOGO</span>
				<div class="fr">
				    <input id="preview2" name="logo" type="hidden" />
					<img class="preview" src="${path}/images/registerlogo.png" />
					<div class="uploadimg"><input class="verify_logo" onchange="imgToBase642(this)" type="file" verify="false" />上传图片</div>
					<i>*</i><b>推荐150X50或等比尺寸</b><em>请上传LOGO</em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="space_10"></div>
			<div class="row">
				<span class="fl">简介</span>
				<div class="fr">
					<textarea class="verify_cont" name="introduce" maxLength=150></textarea>
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="space_15"></div>
			<div class="row">
				<span class="fl">粉丝数</span>
				<div class="fr">
					<input class="verify_conttt" name="fansNum" id="fansNum" type="text" maxLength=11 style="width:100px;" />&nbsp;万
				</div>
				<div class="clear"></div>
			</div>
			<div class="space_10"></div>
			<div class="row">
				<span class="fl">发布价位</span>
				<div class="fr">
					<input class="verify_conttt" type="text" id="price" name="price" maxLength=10 style="width:80px;" />
					<b>元/条左右</b><i>*</i>
				</div>
				<div class="clear"></div>
			</div>
			<div class="space_10"></div>
			<div class="row">
				<span class="fl">联系人QQ</span>
				<div class="fr">
					<input class="verify_cont"  name="qq" maxLength=14 type="text" />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="space_10"></div>
			<div class="row">
				<span class="fl">电话</span>
				<div class="fr">
					<input class="verify_contt" id="mobile" maxLength=20 name="mobile" type="text" />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="row">
				<span class="fl">邮箱</span>
				<div class="fr">
					<input  name="email" type="text" maxLength=20/>
				</div>
				<div class="clear"></div>
			</div>
			<div class="space_20"></div>
			<div class="row">
				<span class="fl">&nbsp;</span>
				<div class="fr">
					<input type="submit" value="提 交" /> 
				</div>
				<div class="clear space_20"></div>
			</div>
		</form>
	</div>
	<!-- 成为渠道 结束 -->
	<!-- 底部 开始 -->
		<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
</body>
</html>
<script type="text/javascript">
//图片上传预览
function imgToBase641(file) {
    if (file.files && file.files[0]) {
        var reader = new FileReader();
        reader.onload = function(evt) {
            $(file).parent().prev().attr("src", evt.target.result);
            $('#preview1').attr("value",evt.target.result);
        }
        reader.readAsDataURL(file.files[0]);
    }
}
//图片上传预览
function imgToBase642(file) {
    if (file.files && file.files[0]) {
        var reader = new FileReader();
        reader.onload = function(evt) {
            $(file).parent().prev().attr("src", evt.target.result);
            $('#preview2').attr("value",evt.target.result);
        }
        reader.readAsDataURL(file.files[0]);
    }
}
//渠道类型切换
$(function() {
    $('#trenchtype').on("change",
    function() {
        if (($(this).val() == '1')||($(this).val() == '2')) {
            $('#url,#app,#person').css("display", "none");
            $('#code').css("display", "block");
        } else if (($(this).val() == '4')||($(this).val() == '5')) {
        	$('#code,#app,#person').css("display", "none");
            $('#url').css("display", "block");
        } else if ($(this).val() == '6') {
            $('#code,#url,#person').css("display", "none");
            $('#app').css("display", "block");
        } else {
            $('#code,#url,#app').css("display", "none");
            $('#person').css("display", "block");
        }
        
        if (($(this).val() == '1')||($(this).val() == '2')) {
        	 $("#preview222").attr("src","${path}/images/registerlogo.png");
             $(".preview22").attr("value"," ");
             $("#preview2222").html("请上传二维码 ");
             
        }else if (($(this).val() == '3')) {
             $("#preview111").attr("src","${path}/images/registerlogo.png");	
             $(".preview11").attr("value"," ");
             $("#preview1111").html("请上传二维码 ");
        }else {
        	 $("#preview222").attr("src","${path}/images/registerlogo.png");
        	 $("#preview111").attr("src","${path}/images/registerlogo.png");
        	 $(".preview11").attr("value"," ");
        	 $(".preview22").attr("value"," ");
        	 $("#preview2222").html("请上传二维码 ");
        	 $("#preview1111").html("请上传二维码 ");
        } 
        
    });
});

//验证品牌标志是否上传
$(function() {
    $('.verify_logo').change(function() {
        insertNotice($(this).parent(), '<img src="${path}/images/pc/done_ico.png">');
        $(this).attr("verify", "true");
        return true;
    });
});

//验证填写内容
$(function() {
    $('.verify_cont').blur(function() {
        if ($(this).val()) {
            insertNotice($(this), '<img src="${path}/images/pc/done_ico.png">');
            $(this).attr("verify", "true");
            return true;
        } else {
            insertNotice($(this), "请输入内容");
            $(this).attr("verify", "false");
            return false;
        }
    });
    
});
var flag=true;
//提交表单前端验证
function verify_submit() {
    var verify = 0;
    $('input[type="text"],textarea,.verify_logo').each(function() {
        $(this).blur();
        if ($(this).attr("verify") == "false" && !$(this).parents('.row').attr('style')) {
            verify += 1;
        }
        console.log(verify);
    });
    if (verify > 0) {
        return false;
    } else {
    	//防止表单重复提交
		if (flag==false){
			return false;
			window.location.reload();//刷新当前页面.
			}
			flag=false;
			return true;
			window.location.reload();//刷新当前页面.
    }
}

//回到顶部
$(function() {
    $(".gotop").click(function() {
        $("body,html").animate({
            scrollTop: 0
        },
        300);
    });
    $(window).scroll(function() {
        if ($(window).scrollTop() > 10) {
            $('.gotop').attr("style", "display:block;");
        } else {
            $('.gotop').attr("style", "display:none;");
        }
    });
});
var reg2 = /^\d{1,20}$/;
var re = /^\d+(?=\.{0,1}\d+$|$)/
	$(function() {
	    $('#price').blur(function() {
	    if ($('#price').val()!="") {
	    	 if(!re.test($("#price").val())){
	 	        insertNotice($("#price"), "只能输入数字和小数点！");
		        $("#price").attr("verify", "false");
		        return false;
	    	    }else{
       	    	insertNotice($("#price"), '<img src="${path}/images/pc/done_ico.png">');
       	        $("#price").attr("verify", "true");
       	        return true; 	 
	    	    }
	    }else{
	    	 insertNotice($(this), "请输入内容");
	         $(this).attr("verify", "false");
	         return false;    	
	    }
	    
	    });
	    var parttenn = /^\d+$/;
	   
	    $('#fansNum').blur(function() {
	    	 if ($('#fansNum').val()!="") {
		    	 if(!parttenn.test($('#fansNum').val())){
		 	        insertNotice($('#fansNum'), "只能输入数字！");
			        $('#fansNum').attr("verify", "false");
			        return false;
		    	    }else{
           	    	insertNotice($('#fansNum'), '<img src="${path}/images/pc/done_ico.png">');
           	        $('#fansNum').attr("verify", "true");
           	        return true; 	
		    	    }
	    	 }
		    });
	}); 
 var partten = /^\d+$/;
$(function() {
    $('#mobile').blur(function() {
    if ($("#mobile").val()!="") {
    	 if(!partten.test($("#mobile").val())){
 	        insertNotice($("#mobile"), "只能输入数字！");
	        $("#mobile").attr("verify", "false");
	        return false;
    	    }else{
     	    	insertNotice($("#mobile"), '<img src="${path}/images/pc/done_ico.png">');
     	        $("#mobile").attr("verify", "true");
     	        return true; 	
    	    }
    }else{
    	 insertNotice($(this), "请输入内容");
         $(this).attr("verify", "false");
         return false;    	
    }
    
    });
    
}); 

</script>


