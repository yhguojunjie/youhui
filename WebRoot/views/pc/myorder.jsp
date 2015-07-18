<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
<script type="text/javascript" src="${path }/js/pc/pagination.js"></script>	
<title>我的订单</title>
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
		<script type="text/javascript">
		$(function() {	
			 $.ajax({
			      type: "GET",
			      url: "${basePath}pc/my/ajaxOrdercount",
			      dataType: "json",
			      success: function(data) {
			    	  var str=data.split(";");
			    	  $("#wait").html("等待付款("+str[0]+")");
			    	  $("#succes").html("交易成功("+str[1]+")");
			    	  $("#close").html("交易关闭("+str[2]+")");
			      }
			  });
			getPageDatas(1,0);
		  $('#all,#wait,#succes,#close').click(function() {
		      //获取排序字段
		      orderFlag = $(this).attr("value");
		      //排序方式(选中状态)
		      if (orderFlag == '3') {
		    	  var currentPage1=3;
		          $('#close').attr("class", "active");
		          $('#all').attr("class", " ");
		          $('#wait').attr("class", " ");
		          $('#succes').attr("class", " ");
		          getPageDatas(1,currentPage1);
		      } else if (orderFlag == '2') {
		    	  var currentPage2=2;
		    	  $('#close').attr("class", "");
			      $('#all').attr("class", "");
			      $('#wait').attr("class", " ");
			      $('#succes').attr("class", "active");
			      getPageDatas(1,currentPage2);
		      } else if(orderFlag == '1'){
		    	  var currentPage3=1;
		    	  $('#close').attr("class", "");
		          $('#all').attr("class", " ");
		          $('#wait').attr("class", "active");
		          $('#succes').attr("class", " ");
		          getPageDatas(1,currentPage3);
		      }else {
		    	  var currentPage4=0;
		    	  $('#close').attr("class", "");
		          $('#all').attr("class", "active");
		          $('#wait').attr("class", " ");
		          $('#succes').attr("class", " ");
		          getPageDatas(1,currentPage4);
		      }
		  });
		});
		</script>
		<!-- 右侧内容 开始 -->
		<div class="fr w890 main">
			<div class="fr w860" >
				<div class="tab_title">
					<div class="fl options" id="myactivity_sortbtn">
						<a class="active" id="all" value="0">全部</a>
						<a id="wait" value="1"></a>
						<a id="succes" value="2"></a>
						<a id="close" value="3"></a>
					</div>
					<div class="fr add_activity">
						<a class="del">删除</a>
						<!-- <a class="add">新增活动</a> -->
					</div>
					<div class="clear"></div>
				</div>	
				<div id="orders">			
		        </div> 
			</div>
		</div>
		<!-- 右侧内容 结束 -->
		<div class="clear"></div>
	</div>
	<!-- 内容 结束 -->
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
</body>
</html>
<script type="text/javascript">
var fileAccessPath = '${fileAccessPath}';
function notice(id){ 
	var r=confirm("是否关闭交易？");  
    if(r==true){
    	$.ajax({
  	      type: "GET",
  	      url: "${basePath}pc/my/closeTradeState?id="+id,
  	      dataType: "json",
  	      success: function(data) {
  	    	 location.reload();
  	        return true;  
  	      }
    	});
    }else{
      return false;   
    }
     }
var xFlag = '0';
function getPageDatas(currentPage,xFlag) {
	  $.ajax({
	      type: "GET",
	      url: "${basePath}pc/my/ajaxOrderList?currentPage="+currentPage+"&xFlag="+xFlag,
	      dataType: "json",
	      success: function(data) {
	    	  if(data != null && data !=undefined){
	    	    $('#orders').empty();
		     	 if(data.pageResult != undefined && data.pageResult != null && data.pageResult.length != 0){
		     		 var currentPage = data.currentPage;//当前页数
					 var totalPage = data.totalPage;//总页数
					 var count = data.count; //每页显示多少
		     		 var html = '';
					
					var  startTime;
	         	 for (var i = 0; i < data.pageResult.length; i++) {	 
					 var  str1 = data.pageResult[i].orderTime.toString().trim();
					      str1 =  str1.replace(/(\d{4})-(\d{2})-(\d{2})T(.*)?\.(.*)/, "$1/$2/$3 $4");
					      str1=str1.replace(".0","").substring(0,16);
			              html +='<div class="order">';  
	                    if(data.pageResult[i].tradeState==0){
	                            html +='<h3 class="payingbg">';
	                            html +='<span class="fr">下单时间：'+str1+'</span>';
	                            html +='<span>订单号：'+data.pageResult[i].id+'</span></h3>';    
	                      }
	                    if(data.pageResult[i].tradeState==2){
	                    	    html +='<h3 class="payedbg">';
			                    html +='<span class="fr">下单时间：'+str1+'</span>';
			                    html +='<span>订单号：'+data.pageResult[i].id+'</span></h3>';  
	                      }
                        if(data.pageResult[i].tradeState==1){
                      	        html +='<h3 class="unpaybg">';
			                    html +='<span class="fr">下单时间：'+str1+'</span>';
			                    html +='<span>订单号：'+data.pageResult[i].id+'</span></h3>';
	                      }
                        html +='<table><tr>';
                        html +='<td><a href="#"><img src='+fileAccessPath+data.pageResult[i].productIcon+' /></a></td>';
                        html +='<td><a href="#"><strong>'+data.pageResult[i].productName+'</strong></a></td>';
                        html +='<td><em>'+data.pageResult[i].salePrice+'</em>元/月</td>';
                        html +='<td><em>'+data.pageResult[i].buyNum+'</em>个月</td>';
                        html +='<td><del>'+data.pageResult[i].payAll+'元</del><p><em>'+data.pageResult[i].charge+'</em>元</p></td>';   
	                 
                        if(data.pageResult[i].tradeState==1){
                      	    html +='<td><p class="unpay">交易关闭</p></td>';
	                      }
                        if(data.pageResult[i].tradeState==2){
                      	    html +='<td><p class="paydone">交易成功</p></td>';
	                      }
                        if(data.pageResult[i].tradeState==0){
                      	    html +='<td><a href="${basePath}pay/pay/toAlipayWapApi?orderId='+data.pageResult[i].id+'" class="pay">支付</a><a href="#" onclick="notice('+data.pageResult[i].id+');" class="closepay">关闭交易</a></td>';
	                      }
                        html += '</tr></table>';
                        html += '</div>';
	                  }
	         	      if(data.totalPage > 1){
		     		      html += '<div id="pageNum" class="tr pages"></div>';
		     	       }
	              } else {
	            	      html += '<div class="order">';
	            	      if(xFlag == '0'){
	            	    	  html += '<li style="text-align:center;padding:30px;color:#999;">您还没有订单！</li></div>';
				              html=html.toString().replace("undefined",""); 
				     		}
				     		if(xFlag == '1'){
				     		  html += '<li style="text-align:center;padding:30px;color:#999;">您还没有等待付款的订单！</li></div>';
					          html=html.toString().replace("undefined",""); 
				     		}
				     		if(xFlag == '2'){
				     		  html += '<li style="text-align:center;padding:30px;color:#999;">您还没有交易成功的订单！</li></div>';
					          html=html.toString().replace("undefined","");  
				     		}
				     		if(xFlag == '3'){
					     	  html += '<li style="text-align:center;padding:30px;color:#999;">您还没有交易关闭的订单！</li></div>';
						      html=html.toString().replace("undefined","");  
					     	}
			              
	              }
	              $('#orders').html(html);
	          	initPages(currentPage,1,totalPage,totalPage); //初始化分页
	    	  }	  
	       }
	  });
	}
</script>


