package com.yoxi.hudongtui.vo.wx.api.message;

/** 
 * 文本消息
 *  
 * @author wangql
 * 
 * 2013-12-26
 * 
 */ 
public class ReqTextMessage extends ReqBaseMessage {
	
	// 回复的消息内容 
    private String Content; 
 
    public String getContent() { 
        return Content; 
    } 
 
    public void setContent(String content) { 
        Content = content; 
    }
}
