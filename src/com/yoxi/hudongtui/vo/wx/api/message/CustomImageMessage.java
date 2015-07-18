package com.yoxi.hudongtui.vo.wx.api.message;

public class CustomImageMessage {

	public String touser;
	public String msgtype;
	public Image image;
	
	public  static class Image{
		public String media_id;
	}
}
