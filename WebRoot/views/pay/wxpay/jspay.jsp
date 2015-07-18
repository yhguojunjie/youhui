<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../constant.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>微信支付</title>

<%@ include file="../../wx/resource.jsp"%>

<script Language="javascript">
	
	$(document).ready(function(){
		alert("into pay");
        /* if(submitflag){return;}
       	submitflag=true; */
	/* 	 if(parseInt(${jsApiReq.paySign})<5){  
             alert("您的微信版本低于5.0无法使用微信支付");  
             return;  
         }  */ 
        alert('${jsApiReq.appId}');
        var appId = '${jsApiReq.appId}';
        alert("appId="+appId);
        var timeStamp = '${jsApiReq.timeStamp}';
        alert("timeStamp="+timeStamp);
        var nonceStr = '${jsApiReq.nonceStr}';
        alert("nonceStr="+nonceStr);
        var packages = '${jsApiReq.packageInfo}';
        alert("packages="+packages);
        var signType = '${jsApiReq.signType}';
        alert("signType="+signType);
        var paySign = '${jsApiReq.paySign}';
        alert("paySign="+paySign);
        
        WeixinJSBridge.invoke("getBrandWCPayRequest",{
			"appId" : appId,
			"timeStamp" : timeStamp, 
            "nonceStr" : nonceStr, 
            "package" : packages,
            "signType" :signType, 
            "paySign" : paySign
            },function(res){
			 //  WeixinJSBridge.log(res.err_msg);
			  // alert(res.err_code+res.err_desc+res.err_msg);
			   alert(res.err_msg);
	/* 		   if(res.err_msg = "get_brand_wcpay_request:ok"){//支付成功
				   
			   }else if(res.err_msg = "get_brand_wcpay_request:cancel"){//支付过程中用户取消
				   
			   }else(res.err_msg = "get_brand_wcpay_request:fail"){//支付失败
				   
			   } */
			 });
	});

	
</script>
</head>
</html>