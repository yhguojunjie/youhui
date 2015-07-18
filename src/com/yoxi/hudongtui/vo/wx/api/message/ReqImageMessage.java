package com.yoxi.hudongtui.vo.wx.api.message;

/** 
 * 图片消息
 *  
 * @author wangql
 * 
 * 2013-12-26
 * 
 */ 
public class ReqImageMessage extends ReqBaseMessage {
	
	// 图片链接 
    private String PicUrl; 
 
    public String getPicUrl() { 
        return PicUrl; 
    } 
 
    public void setPicUrl(String picUrl) { 
        PicUrl = picUrl; 
    } 
}
