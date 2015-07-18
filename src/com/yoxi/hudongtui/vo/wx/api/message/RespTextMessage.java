package com.yoxi.hudongtui.vo.wx.api.message;

/** 
 * 响应消息-文本消息
 *  
 * @author wangql
 * 
 * 2013-12-26
 * 
 */ 
public class RespTextMessage extends RespBaseMessage {
    // 回复的消息内容 
    public String Content;

    public RespTextMessage(){
    	
    }
    
	public RespTextMessage(String toUserName, String fromUserName, long createTime, String msgType
			) {
		super(toUserName, fromUserName, createTime, msgType);
		// TODO Auto-generated constructor stub
	} 
 
	public RespTextMessage(RespBaseMessage respBaseMessage){
		super(respBaseMessage.ToUserName, respBaseMessage.FromUserName, respBaseMessage.CreateTime, respBaseMessage.MsgType);
	}
 
}
