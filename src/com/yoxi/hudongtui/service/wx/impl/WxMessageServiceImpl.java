package com.yoxi.hudongtui.service.wx.impl;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.service.wx.IWxMessageService;
import com.yoxi.hudongtui.utils.wx.MessageUtils;
import com.yoxi.hudongtui.vo.wx.api.menu.TMenus;
import com.yoxi.hudongtui.vo.wx.api.message.RespBaseMessage;
import com.yoxi.hudongtui.vo.wx.api.message.RespTextMessage;


@Service
public class WxMessageServiceImpl implements IWxMessageService {
	
	@Override
	public String processReq(HttpServletRequest request) {
	      String respMessage = null; 
	        try { 
	            // 默认返回的文本消息内容 
	            String respContent = "请求处理异常，请稍候尝试！"; 
	 
	            // xml请求解析 
	            Map<String, String> requestMap = MessageUtils.parseXml(request);
	 
	            // 发送方帐号（open_id） 
	            String fromUserName = requestMap.get("FromUserName"); 
	            // 公众帐号 
	            String toUserName = requestMap.get("ToUserName"); 
	            // 消息类型 
	            String msgType = requestMap.get("MsgType"); 
	 
	            // 设置回复消息的基本参数
	            RespBaseMessage baseMessage = new RespBaseMessage();
	            baseMessage.ToUserName = fromUserName; 
	            baseMessage.FromUserName = toUserName; 
	            baseMessage.CreateTime = new Date().getTime(); 
	            
	            // 文本消息 
	            if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_TEXT)) {
	            	String fromContent = requestMap.get("Content");
	            	if (StringUtils.isNotBlank(fromContent)) {
	            		fromContent = fromContent.trim();
//	            		respMessage = processTextReplyMessage(fromContent, baseMessage);
					}
	            	
//	            	saveOrUpdateMpWxInteract(fromUserName,"1");
	            } 
	            // 图片消息 
	            else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_IMAGE)) { 
//	            	saveOrUpdateMpWxInteract(fromUserName,"2");
	            } 
	            // 地理位置消息 
	            else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_LOCATION)) { 
	            	
//	            	saveOrUpdateMpWxInteract(fromUserName,"3");
	            } 
	            // 链接消息 
	            else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_LINK)) { 
//	            	saveOrUpdateMpWxInteract(fromUserName,"4");
	            	
	            } 
	            // 音频消息 
	            else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_VOICE)) {
	            	//TODO
//	            	String autoReplyMsg = processAutoReplyMessage(baseMessage);
//					if (StringUtils.isNotBlank(autoReplyMsg)) {
//						respMessage = autoReplyMsg;
//					};
					
//	            	saveOrUpdateMpWxInteract(fromUserName,"5");
	            } 
	            // 事件推送 
	            else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_EVENT)) { 
	                // 事件类型 
	                String eventType = requestMap.get("Event"); 
	                // 订阅 (关注)
	                if (eventType.equals(MessageUtils.EVENT_TYPE_SUBSCRIBE)) { 
//	                	String subscribeReplyMsg = processSubscribeMessage(baseMessage);
//	    				if (StringUtils.isNotBlank(subscribeReplyMsg)) {
//	    					respMessage = subscribeReplyMsg;
//	    				};
	    				//更新用户关注状态fromUserName
//	    				ProcessUserSubThread put1 = new ProcessUserSubThread(fromUserName,1,"6",userBusService); 
//	    				new Thread(put1).start(); 
	                
	                } 
	                // 取消订阅 (取消关注)
	                else if (eventType.equals(MessageUtils.EVENT_TYPE_UNSUBSCRIBE)) { 
//	                	ProcessUserSubThread put2 = new ProcessUserSubThread(fromUserName,0,null,userBusService); 
//	    				new Thread(put2).start(); 
	                } 
	                // 扫描带参数二维码事件   2. 用户已关注时的事件推送
	                else if (eventType.equals(MessageUtils.EVENT_TYPE_SCAN)) { 
//	                	saveOrUpdateMpWxInteract(fromUserName,"8");
	                } 
	                // 自定义菜单点击事件 
	                else if (eventType.equals(MessageUtils.EVENT_TYPE_CLICK)) { 
	                	String eventKey = requestMap.get("EventKey");
	                	
	                	if(eventKey != null && !"".equals(eventKey)){
	                		
//	                         TMenus tMuenus = menuService.getTMenusByKey(eventKey);
//	                        	if(tMuenus != null){
//	                        	   if(tMuenus.getResourceId() != 0){
//	                        		   //图文消息回复
//	                        		   if(tMuenus.getResourceType() == 1){
//	                                 		RespNewsMessage rsp = replyNewsMessage(baseMessage, tMuenus.getResourceId());
//	                                   		respMessage =MessageUtils.newsMessageToXml(rsp);
//	                        		   }
//	         
//	                        	   }	
//	                        	}
	                	}

//	                	saveOrUpdateMpWxInteract(fromUserName,"9");
	                } 
	                //上报地理位置事件
	                else if (eventType.equals(MessageUtils.EVENT_TYPE_LOCATION)) { 
//	                	saveOrUpdateMpWxInteract(fromUserName,"10");
	                }else if(eventType.equals(MessageUtils.EVENT_TYPE_VIEW)){//菜单链接跳转
//	                	saveOrUpdateMpWxInteract(fromUserName,"11");
	                }
	                
	            } 
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	 
	        return respMessage; 
	}

}
