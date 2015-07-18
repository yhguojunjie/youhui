package com.yoxi.hudongtui.vo.wx.api.message;

/** 
 * 响应消息基类（公众帐号 -> 普通用户）
 *  
 * @author wangql
 * 
 * 2013-12-26
 * 
 */ 
public class RespBaseMessage {
	
	// 接收方帐号（收到的OpenID） 
    public String ToUserName; 
    // 开发者微信号 
    public String FromUserName; 
    // 消息创建时间 （整型） 
    public long CreateTime; 
    // 消息类型（text/music/news） 
    public String MsgType; 
    // 位0x0001被标志时，星标刚收到的消息 
//    public int FuncFlag;
    
    public RespBaseMessage(){
    	
    }
    
	public RespBaseMessage(String toUserName, String fromUserName, long createTime, String msgType
			) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
	} 
 

}
