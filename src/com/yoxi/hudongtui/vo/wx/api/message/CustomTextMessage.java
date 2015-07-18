package com.yoxi.hudongtui.vo.wx.api.message;

public class CustomTextMessage {

	public String touser;
	public String msgtype;
	public Text text;
	
	public static class Text{
		public String content;
	}
}
