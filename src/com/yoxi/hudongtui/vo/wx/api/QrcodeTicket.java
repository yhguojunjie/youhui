package com.yoxi.hudongtui.vo.wx.api;

/**
 * 
 * 创建二维码ticket返回值
 *
 * @author wql
 *
 * @Date 2015年3月10日
 *
 */
public class QrcodeTicket {
	
	/**获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码*/
	private java.lang.String ticket;
	/**二维码的有效时间，以秒为单位。最大不超过1800*/
	private java.lang.Integer expire_seconds;
	/**二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片*/
	private java.lang.String url;
	
	
	public java.lang.String getTicket() {
		return ticket;
	}
	public void setTicket(java.lang.String ticket) {
		this.ticket = ticket;
	}
	public java.lang.Integer getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(java.lang.Integer expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public java.lang.String getUrl() {
		return url;
	}
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
}
