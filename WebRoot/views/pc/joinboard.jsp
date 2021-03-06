<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
	<script type="text/javascript" src="${path }/js/lrz.pc.min.js"></script>
	<title>招商加盟</title>
</head>
<style type="text/css">
	body{background:#fff;}
	.resurce{color:#1f4b43;font:14px/30px "微软雅黑";}
	.resurce_w200{display:block;width:200px;}
	.resurce_w250{display:block;width:250px;}
	.resurce_w300{display:block;width:300px;}
	.resurce_w350{display:block;width:350px;}
	.resurce_w400{display:block;width:400px;}
	.resurce_w450{display:block;width:450px;}
	.resurce_w500{display:block;width:500px;}
	.resurce_w550{display:block;width:550px;}
	.resurce_w600{display:block;width:600px;}
	.resurce_w650{display:block;width:650px;}
	.resurce_w700{display:block;width:700px;}
	.resurce_w750{display:block;width:750px;}
	.resurce_w800{display:block;width:800px;}
	.resurce_w850{display:block;width:850px;}
	.resurce_w900{display:block;width:900px;}
	.resurce_w950{display:block;width:950px;}
	.resurce_w1000{display:block;width:1000px;}
	.resurce_ma0{margin:0px auto;}
	.resurce_ma10{margin:10px auto;}
	.resurce_ma15{margin:15px auto;}
	.resurce_ma20{margin:20px auto;}
	.resurce_ma25{margin:25px auto;}
	.resurce_ma30{margin:30px auto;}
	.resurce_ma35{margin:35px auto;}
	.resurce_ma40{margin:40px auto;}
	.resurce_ma45{margin:45px auto;}
	.resurce_ma50{margin:50px auto;}
	.resurce_ma80{margin:80px auto;}
	.resurce_qa{}
	.resurce_qa h3{font:bold 16px/40px "微软雅黑";}
	.resurce_qa table{width:80%;margin:0 auto;}
	.resurce_qa th{border:1px solid #1f4b43;text-align:center;font:bold 14px/30px "微软雅黑";}
	.resurce_qa td{border:1px solid #1f4b43;text-align:center;font:12px/30px "微软雅黑";}
	.resurce_qa td em{color:#f19500;font:bold 20px/30px "微软雅黑";}
	.resurce_qa form .fl{width:20%;text-align:right;}
	.resurce_qa form .fr{width:80%;}
	.resurce_qa form .row{font:14px/40px "微软雅黑";margin:10px 0;}
	.resurce_qa form .row em{font-size:12px;color:#e0848f;}
	.resurce_qa form .row em img{width:20px;height:20px;vertical-align:-4px;}
	.resurce_qa form .row b{font-size:12px;color:#999;font-weight:normal;margin-right:5px;}
	.resurce_qa form .row i{font-style:normal;font-family:"微软雅黑";color:#e0848f;margin:0 5px;}
	.resurce_qa form .row a{color:#63c7bb;margin:0px 3px;}
	.resurce_qa form .row input[type="text"]{width:400px;height:20px;font:14px/20px "微软雅黑";border-radius:3px;border:1px solid #ccc;padding:5px;}
	.resurce_qa form .row input[type="button"]{width:400px;height:60px;background:#fa0;border-radius:3px;border:0;color:#fff;font:18px/18px "微软雅黑";}
	.resurce_qa form .row input[type="button"]:hover{cursor:pointer;background:#f80;}
	.resurce_qa form .row textarea{border:1px solid #ccc;border-radius:3px;width:700px;height:100px;resize:none;vertical-align:top;padding:5px;font:14px/20px "微软雅黑";}
	.cooperation dl{position:fixed;right:10px;top:200px;z-index:100;width:180px;height:266px;background:url(../../images/pc/cooperation.png) no-repeat 0 0;font:18px/35px "微软雅黑";text-align:center;color:#fff;}
	.cooperation dt{height:35px;overflow:hidden;margin-bottom:8px;}
	.cooperation dt img{float:left;margin:7px -10px 0 15px;}
	.cooperation dd{width:160px;height:288px;padding:10px;background:#5ab3a1;}
	.cooperation dd .row{margin-bottom:10px;}
	.cooperation input[type="text"]{border:0px;border-radius:3px;padding:5px;width:150px;height:16px;font:12px/16px "微软雅黑";background:rgba(255,255,255,.2);color:#fff;border-top:1px solid rgba(0,0,0,.5);border-bottom:1px solid rgba(255,255,255,.75);outline:none;}
	.cooperation textarea{border:0px;border-radius:3px;padding:5px;font:12px/16px "微软雅黑";background:rgba(255,255,255,.2);color:#fff;border-top:1px solid rgba(0,0,0,.5);border-bottom:1px solid rgba(255,255,255,.75);outline:none;resize:none;width:150px;height:80px;}
	.cooperation input[type="button"]{width:80px;height:30px;background:#f19500;border-radius:3px;border:0;color:#fff;font:14px/18px "微软雅黑";}
	.cooperation input[type="button"]:hover{cursor:pointer;background:#fa0;}
	.cooperation .turntop{display:none;position:fixed;right:10px;bottom:10px;z-index:101;width:34px;cursor:pointer;}
	.float_menu{right:-200px;}
</style>
<body>
	<!-- 头部 开始 -->
		<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	<!-- 招商加盟 开始 -->
	<div class="resurce">
		<div class="resurce_w1000 resurce_ma0">
			<div class="space_20"></div>
			<img class="resurce_w400 resurce_ma50" src="../../images/pc/resurce_pt1.png" />
			<div class="resurce_w600 resurce_ma0">
				<p class="tc resurce_ma10">互动推是有戏（厦门）网络科技有限公司针对代理商推出的移动社交互动广告代理平台，支持代理商生成一个完全属于自己的互动广告平台。</p>
				<p class="tc resurce_ma10">为代理商的客户提供微活动、微游戏模板，平台支持免费试用、线上（线下）支付、个性配置、即买即用，帮助客户轻松获取百万关注。</p>
				<p class="tc resurce_ma10">同时为代理商带去源源不断、可持续增长的平台收入。</p>
			</div>
			<div class="space_30"></div>
			<img class="resurce_w550 resurce_ma0" src="../../images/pc/resurce_img1.png" />
			<div class="space_30"></div>
			<img class="resurce_w1000 resurce_ma30" src="../../images/pc/resurce_pt2.png" />
			<img class="resurce_w300" src="../../images/pc/resurce_t1.png" />
			<div class="resurce_w800 resurce_ma20 resurce_qa">
				<h3><strong class="fl" style="width:4%;">Q：</strong>这东西有钱赚么？</h3>
				<strong class="fl" style="width:4%;">A：</strong>
				<div class="fr" style="width:96%;">
					<p>1、每款模板价格支持自定义，平台收取成本价300元左右，市场价低端客户在500元~900 元，高端客户在几千到上万不等。</p>
					<p>2、年卡会员价格支持自定义，平台收取成本价5000元，市场价在8000元~10000元不等。</p>
					<p>3、定制活动，平台收取成本价5000元~10000元，市场价在10000元~50000元不等。</p>
					<p>4、互动推平台自营阶段，无做任何付费推广，仅凭口碑传播，每月平均成交105单，定制订单3单。</p>
					<p>5、代理商每月的收益，预计会在30000元~100000元。</p>
				</div>
				<div class="clear space_20"></div>
				<h3><strong class="fl" style="width:4%;">Q：</strong>业务好开展么？</h3>
				<strong class="fl" style="width:4%;">A：</strong>
				<div class="fr" style="width:96%;">
					<p>1、市场需求大，从大品牌厂商、经销商到小商家，不论哪个行业，在一年各类节日，都有促销活动的需求，购买频率高，回购率也很高。</p>
					<p>2、支持查看演示活动、免费试用、即买即用，单款插件价格相对套餐低，客户决策时间短，购买转化率高。</p>
					<p>3、平台大数据支持，如大品牌客户案例等，代理商共享，说服力强。</p>
					<p>4、拥有自己的平台，可持续发展，订单自增长，从平台访问到付费转化率高达10%。</p>
					<p>5、互动推云平台负责策划、开发、运营、维护，代理商只需有销售人员，即可轻松开展业务。</p>
				</div>
				<div class="clear space_20"></div>
				<h3><strong>Q：</strong>我的客户会流失么？</h3>
				<strong class="fl" style="width:4%;">A：</strong>
				<div class="fr" style="width:96%;">
					<p>1、互动推独创的客户注册绑定技术，有效地防止了代理商客户的流失。每个代理商都拥有属于自己的平台，在自己平台上完成注册的客户，不论是在互动推官网，还是在其他代理商的平台完成登录，都将跳转显示当前页面对应的绑定代理商的平台页面，进行后续的操作。</p>
					<p>2、代理商可给自己的平台设置独立的域名、名称、logo、标题等，还有活动页面上的版权标识，让客户认知这是完全属于代理商的平台，也将认准代理商的品牌。</p>
					<p>3、支持代理有设置模板价格面议，与客户线下交易，客户资料与模板成交价完全保密。</p>
				</div>
				<div class="clear space_20"></div>
				<table>
					<tr>
						<td>&nbsp;</td>
						<th>代理费3000元，首次预存费10000元，共13000元</th>
					</tr>
					<tr>
						<td>网站LOGO</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>网站域名</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>网站标题</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>网站简介</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>网站关键词</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>模板版权</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>公众号二维码</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>客服电话</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>客户QQ</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>邮箱</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>公司地址</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>客户绑定</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>模板定价</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>订单改价</td>
						<td><em>√</em></td>
					</tr>
					<tr>
						<td>案例展示</td>
						<td><em>√</em></td>
					</tr>
				</table>
				<div class="clear space_20"></div>
			</div>
			<img class="resurce_w300" src="../../images/pc/resurce_t2.png" />
			<img class="resurce_w800 resurce_ma30" src="../../images/pc/resurce_img2.png" />
			<img class="resurce_w800 resurce_ma30" src="../../images/pc/resurce_img3.png" />
			<div class="space_20"></div>
			<img class="resurce_w300" src="../../images/pc/resurce_t3.png" />
			<img class="resurce_w800 resurce_ma30" src="../../images/pc/resurce_img4.png" />
			<div class="space_20"></div>
			<img class="resurce_w1000 resurce_ma50" src="../../images/pc/resurce_pt3.png" />
			<img class="resurce_w300" src="../../images/pc/resurce_t4.png" />
			<img class="resurce_w800 resurce_ma50" src="../../images/pc/resurce_img5.png" />
			<img class="resurce_w300" src="../../images/pc/resurce_t5.png" />
			<img class="resurce_w800 resurce_ma50" src="../../images/pc/resurce_img6.png" />
			<img class="resurce_w300" src="../../images/pc/resurce_t6.png" />
			<img class="resurce_w600 resurce_ma50" src="../../images/pc/resurce_img7.png" />
			<div class="resurce_w600 resurce_ma0">
				<p><strong>防屏蔽：</strong>使用技术手段减少朋友圈屏蔽风险。</p>
				<p><strong>防封号：</strong>无需绑定公众号，减少技术跟踪风险。更有流量报警机制。</p>
			</div>
			<div class="clear space_50"></div>
			<img class="resurce_w300" src="../../images/pc/resurce_t7.png" />
			<div class="resurce_w600 resurce_ma20">
				<p>使用阿里云服务，全国几十台网点部署，采用高级别数据库架构，经过大型活动抗压测试，支持上千并发量，系统稳定。</p>
			</div>
			<img class="resurce_w600 resurce_ma50" src="../../images/pc/resurce_img8.png" />
			<div class="clear space_50"></div>
			<img class="resurce_w300" src="../../images/pc/resurce_t8.png" />
			<div class="resurce_w600 resurce_ma30">
				<p><strong>不冲突：</strong>无需绑定公众号，可与其他任何微信第三方服务同时使用。</p>
				<p><strong>无限制：</strong>不限订阅号、服务号、个人号，均可无差别正常使用。</p>
			</div>
			<img class="resurce_w600 resurce_ma50" src="../../images/pc/resurce_img9.png" />
			<div class="clear space_50"></div>
			<img class="resurce_w300" src="../../images/pc/resurce_t9.png" />
			<img class="resurce_w700 resurce_ma50" src="../../images/pc/resurce_img10.png" />
			<div class="clear space_50"></div>
			<img class="resurce_w300" src="../../images/pc/resurce_t10.png" />
			<img class="resurce_w600 resurce_ma50" src="../../images/pc/resurce_img11.png" />
			<div class="clear space_50"></div>
			<img class="resurce_w300" src="../../images/pc/resurce_t11.png" />
			<img class="resurce_w600 resurce_ma50" src="../../images/pc/resurce_img12.png" />
			<div class="resurce_w600 resurce_ma20">
				<p>多种方案可选，支持代理商设置线上支付或线下支付，明码标价或价格面议。支持客户单款插件按使用时间购买，或成为年卡会员。</p>
			</div>
			<div class="clear space_50"></div>
			<img class="resurce_w300" src="../../images/pc/resurce_t12.png" />
			<div class="clear space_20"></div>
			<img class="resurce_w800 resurce_ma20" src="../../images/pc/resurce_img13.png" />
			<div class="resurce_w800 resurce_ma20">
				<p>
					<strong>发布渠道：</strong>
					平台上整合了各类优秀渠道资源，有百万粉丝级别的订阅号、服务号、微博，还有几千好友的个人号作为活动发布渠道，支撑代理商为客户带去更好的服务效果！
				</p>
			</div>
		</div>
		<div class="clear space_20"></div>
		<div class="resurce_w1000 resurce_ma30"><img class="resurce_w300" src="../../images/pc/resurce_t13.png" /></div>
		<div class="resurce_w1000 resurce_ma20 resurce_qa">
			<form  >
				<div class="row">
					<span class="fl">合作联系人：</span>
					<div class="fr">
						<input class="verify_name" name="contact" type="text" verify="false" />
						<i>*</i><em></em>
					</div>
					<div class="clear"></div>
				</div>
				<div class="row">
					<span class="fl">联系人手机：</span>
					<div class="fr">
						<input class="verify_phone" name="mobile" type="text" verify="false" />
						<i>*</i><em></em>
					</div>
					<div class="clear"></div>
				</div>
				<div class="row">
					<span class="fl">E-mail：</span>
					<div class="fr">
						<input class="verify_mail" name="email" type="text" verify="false" />
						<i>*</i><em></em>
					</div>
					<div class="clear"></div>
				</div>
				<div class="row">
					<span class="fl">QQ号码：</span>
					<div class="fr">
						<input class="qq_name" name="qq" type="text" verify="false" />
						<i>*</i><em></em>
					</div>
					<div class="clear"></div>
				</div>
				<div class="row">
					<span class="fl">备注：</span>
					<div class="fr">
						<textarea name="remark"></textarea>
					</div>
					<div class="clear"></div>
				</div>
				<div class="row tc">
					<div class="clear space_20"></div>
					<input type="button" onclick="verify_submit();" value="提交，一起分享市场" />
					<div class="clear space_20"></div>
				</div>
			</form>
		</div>
		<div class="clear space_50"></div>
	</div>
	<!-- 招商加盟 结束 -->
	<!-- 底部 开始 -->
		<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
	<!-- 浮动菜单 开始 -->
	<div class="cooperation">
		<dl>
			<dt>招商加盟</dt>
			<dt><img src="../../images/pc/cooperation_ico.png">4000057661</dt>
			<dd>
				<form>
					<div class="row">
						<input type="text" verify="false" class="verify_name1" id="contact" name="contact" value="联系人" onblur="if(this.value=='')this.value='联系人';" onfocus="if(this.value=='联系人')this.value='';" />
					</div>
					<div class="row">
						<input type="text" verify="false" class="verify_phone1" id="mobile" name="mobile" value="手机号" onblur="if(this.value=='')this.value='手机号';" onfocus="if(this.value=='手机号')this.value='';" />
					</div>
					<div class="row">
						<input type="text" verify="false" class="verify_mail1" id="email" name="email" value="邮箱" onblur="if(this.value=='')this.value='邮箱';" onfocus="if(this.value=='邮箱')this.value='';" />
					</div>
					<div class="row">
						<input type="text" verify="false" class="qq_name1" id="qq" name="qq" value="QQ号码" onblur="if(this.value=='')this.value='QQ号码';" onfocus="if(this.value=='QQ号码')this.value='';" />
					</div>
					<div class="row">
						<textarea name="remark" id="remark" onblur="if(this.value=='')this.value='备注';" onfocus="if(this.value=='备注')this.value='';">备注</textarea>
					</div>
					<div class="row tc">
						<input type="button" onclick="verify_submitt();" value="提交" />
					</div>
				</form>
			</dd>
		</dl>
		<img class="turntop" src="../../images/pc/top.png" />
	</div>
	<!-- 浮动菜单 结束 -->
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){

	//回到顶部
	$(function(){
		$(".cooperation .turntop").click(function(){
			$("body,html").animate({scrollTop:0},300);
		});
		$(window).scroll(function(){
			if($(window).scrollTop() > 10){
				$('.cooperation .turntop').attr("style","display:block;");
			}else{
				$('.cooperation .turntop').attr("style","display:none;");
			}
			if($(window).scrollTop()>8500){
				$('.cooperation dl').attr("style","display:none;");
			}else{
				$('.cooperation dl').attr("style","display:block;");
			}
		});
	});

	//验证联系人
	$(function(){
		$('.verify_name').blur(function(){
			if($(this).val()){
				insertNotice($(this),'<img src="../../images/pc/done_ico.png">');
				$(this).attr("verify","true");
				return true;
			}else{
				insertNotice($(this),"请输入联系人名");
				$(this).attr("verify","false");
				return false;
			}
		});
		/* $(".verify_name1").blur(function(){
			if($(".verify_name1").val()!="联系人"){
				$(this).attr("verify","true");
				return true;
			}else{
				alert("请输入联系人名");
				return false;
			}
		}); */
	});
	//验证QQ号码
	$(function(){
		$('.qq_name').blur(function(){
			if($(this).val()){
				if(!verify($(this).val(),'number')){
					insertNotice($(this),"QQ号码应为全数字");
					$(this).attr("verify","false");
					return false;
				}else{
					insertNotice($(this),'<img src="../../images/pc/done_ico.png">');
					$(this).attr("verify","true");
					return true;	
				}

			}else{
				insertNotice($(this),"请输入QQ号码");
				$(this).attr("verify","false");
				return false;
			}
		});
		/* $(".qq_name1").blur(function(){
			if($(".qq_name1").val()!="QQ号码"){
				$(this).attr("verify","true");
				return true;
			}else{
				alert("请输入QQ号码");
				return false;
			}
		}); */
	});

	//验证电话
	$('.verify_phone').blur(function(){
		if($(this).val()){
			if(!verify($(this).val(),'number')){
				insertNotice($(this),"电话号码应为全数字");
				$(this).attr("verify","false");
				return false;
			}else if(!verify($(this).val(),11,'count')){
				insertNotice($(this),"请输入11位电话号码");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),'<img src="../../images/pc/done_ico.png">');
				$(this).attr("verify","true");
				return true; 
			}
		}else{
			insertNotice($(this),"请输入手机号");
				$(this).attr("verify","false");
				return false;
		}
	});
	/* $(".verify_phone1").blur(function(){
		if($(".verify_phone1").val()!="手机号"){
			$(this).attr("verify","true");
			return true;
		}else{
			alert("请输入手机号");
			return false;
		}
	}); */

	//验证邮箱
	$(function(){
		$('.verify_mail').blur(function(){
			if($(this).val()){
				if(!verify($(this).val(),'email','')){
					insertNotice($(this),"请输入正确的邮箱格式");
					$(this).attr("verify","false");
					return false;
				}else{
					insertNotice($(this),'<img src="../../images/pc/done_ico.png">');
					$(this).attr("verify","true");
					return true; 
				}
			}else{
				insertNotice($(this),"请输入邮箱");
				$(this).attr("verify","false");
				return false;
			}
		});
	});
/* 	$(".verify_mail1").blur(function(){
		if($(".verify_mail1").val()!="邮箱"){
			$(this).attr("verify","true");
			return true;
		}else{
			alert("请输入邮箱");
			return false;
		}
	}); */

});
var flag1=true;
//提交表单前端验证
function verify_submit(){
    var contact=$(".verify_name").val();
	var mobile=$(".verify_phone").val();
	var email=$(".verify_mail").val();
	var qq=$(".qq_name").val();
	var remark=$(".remark").val(); 

 	var verify = 0;
		$('.resurce_qa input[type="text"]').each(function(){
			$(this).blur();
			if($(this).attr("verify") == "false"){
				verify += 1;
			}
		});
		if(verify > 0){
			return false;
		}else{
		//防止表单重复提交
		if (flag1==false){
			return false;
			window.location.reload();//刷新当前页面.
			}
			flag1=false;
	 	  $.ajax({
	 		  type: "POST",
		      url: "${path}/pc/other/doBeJoinboard",
		      data:{"contact":contact,"mobile":mobile,"email":email,"qq":qq,"remark":remark},
		      dataType: "json",
		      success: function(data) {
               if(data!=0){
            	   alert("\t提交成功！\n 我们的工作人员会尽快联系您");
            	   window.location.reload();//刷新当前页面.
               }
		      }
		  });  
		}
	//}
}

//浮动窗口提交表单前端验证
function verify_submitt(){
 	var contact=$("#contact").val();
	var mobile=$("#mobile").val();
	var email=$("#email").val();
	var qq=$("#qq").val();
	var remark=$("#remark").val();
	
	if($(".verify_name1").val()=="联系人"){
		alert("请输入联系人名称");
	}else if($(".verify_phone1").val()=="手机号"){
		alert("请输入手机号");
	}else if($(".verify_mail1").val()=="邮箱"){
		alert("请输入邮箱");
	}else if($(".qq_name1").val()=="QQ号码"){
		alert("请输入QQ号码");
	}else{
		
	 $.ajax({
		  type: "POST",
	      url: "${path}/pc/other/doBeJoinboard",
	      data:{"contact":contact,"mobile":mobile,"email":email,"qq":qq,"remark":remark},
	      dataType: "json",
	      success: function(data) {
          if(data!=0){
       	   alert("\t提交成功！\n我们的工作人员会尽快联系您");
       	   window.location.reload();//刷新当前页面.
          }
	      }
	  });
	}
  
}
</script>




