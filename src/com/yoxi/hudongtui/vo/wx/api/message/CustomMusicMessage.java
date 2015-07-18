package com.yoxi.hudongtui.vo.wx.api.message;

public class CustomMusicMessage {

	public String touser;
	public String msgtype;
	public Music music;
	
	public static class Music{
		public String title;
		public String description;
		public String musicurl;
		public String hqmusicurl;
		public String thumb_media_id;

	}

}
