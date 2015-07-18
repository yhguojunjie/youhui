package com.yoxi.hudongtui.constants;

import com.yoxi.hudongtui.utils.common.ReadProperties;

/**
 * 
 * @author QQ互联API
 *
 */
public class QQConnectAPI {

	/**QQ互联 使用 Authorization_Code获取Access_Token PC网站 **/
	private static final String accessTokeUrlWeb =
			"https://graph.qq.com/oauth2.0/authorize?response_type=code&" +
			"client_id="+getAppId()+"&redirect_uri=REDIRECT_URI&state=STATE&scope=SCOPE";
	
	/**QQ互联 使用Authorization_Code获取Access_Token wap网站 **/
	private static final String accessTokeUrlWap =
			"https://graph.qq.com/oauth2.0/authorize?response_type=code&" +
			"client_id="+getAppId()+"&redirect_uri=REDIRECT_URI&state=STATE&scope=SCOPE" +
			"display=mobile";
	
	
	/**
	 * 获取appId
	 * @return
	 */
	public static String getAppId() {
		return ReadProperties.getPara("qqconnect_app_ID");
	}

	/**
	 * 获取appSecret
	 * @return
	 */
	public static String getAppKey() {
		return ReadProperties.getPara("qqconnect_app_KEY");
	}

	
	public static String getAccesstokeurlweb() {
		return accessTokeUrlWeb;
	}

	public static String getAccesstokeurlwap() {
		return accessTokeUrlWap;
	}
	
	
	
}
