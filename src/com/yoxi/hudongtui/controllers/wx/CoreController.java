package com.yoxi.hudongtui.controllers.wx;

import java.io.IOException;
import java.io.PrintWriter;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.service.wx.IWxMessageService;
import com.yoxi.hudongtui.utils.wx.SignUtil;

/**
 * 与微信服务器通信核心类，包含身份验证和消息收发
 * 
 * @author wangql
 * 
 * 2013-12-26
 *
 */
public class CoreController {
	
	private static final Logger log = Logger.getLogger(CoreController.class);
	
	@Autowired
	private IWxMessageService messageService;
	
	/**
	 * 处理微信服务器GET请求
	 * 
	 * @param inv
	 */
	@Get("")
	public void doGet(Invocation inv){
		
	    // 微信加密签名  
        String signature =  inv.getRequest().getParameter("signature"); 
        // 时间戳  
        String timestamp = inv.getRequest().getParameter("timestamp");  
        // 随机数  
        String nonce = inv.getRequest().getParameter("nonce");  
        // 随机字符串  
        String echostr = inv.getRequest().getParameter("echostr");  
  
        PrintWriter out;
		try {
			out = inv.getResponse().getWriter();
		   // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
	        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
	        	log.info("*********echostr**********="+echostr);
	            out.print(echostr);  
	        }  
	        out.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}  
        
        out = null;  
	}
	
	/**
	 * 处理微信服务器POST请求
	 * @throws Exception
	 */
	@Post("")
	public void doPost(Invocation inv)throws Exception{
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码） 
		inv.getRequest().setCharacterEncoding("UTF-8"); 
		inv.getResponse().setCharacterEncoding("UTF-8"); 
 
        // 调用核心业务类接收消息、处理消息 
        String respMessage = messageService.processReq(inv.getRequest()); 
        log.info("*********respMessage**********="+respMessage);
        // 响应消息 
        PrintWriter out = inv.getResponse().getWriter(); 
        out.print(respMessage); 
        out.close(); 
    } 
}
