package com.yoxi.hudongtui.vo.wx.api.message;

import java.util.List;

public class CustomNewsMessage {

	public String touser;
	public String msgtype;
	public News news;
	
	public static class News{
		public List<Article> articles;
		
	}

	public static class Article{
		public String title;
		public String description;
		public String url;
		public String picurl;
		
	}
}
