package com.yoxi.hudongtui.vo.wx.api.message;

public class CustomVideoMessage {

	public String touser;
	public String msgtype;
	public Video video;
	
	public static class Video{
		public String media_id;
		public String title;
		public String description;
		
	}
}
