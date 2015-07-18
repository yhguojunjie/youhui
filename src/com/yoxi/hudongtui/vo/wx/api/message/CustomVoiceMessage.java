package com.yoxi.hudongtui.vo.wx.api.message;

public class CustomVoiceMessage {

	public String touser;
	public String msgtype;
	public Voice voice;
	
	public class Voice{
		public String media_id;
	}
}
