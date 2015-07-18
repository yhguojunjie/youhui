package com.yoxi.hudongtui.constants.wx;

import com.yoxi.hudongtui.utils.common.ReadProperties;

/**
 * 
 * 微信开放平台数据
 * 
 * @author wql
 * 
 * 2014-11-20
 *
 */
public class OpenConstanst {

	/**网站登录发起地址*/
	private static final String WEB_OAUTHACCESS_SNSAPI_LOGIN= "https://open.weixin.qq.com/connect/qrconnect?appid=APPID" +
					"&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";
	
	/**获取用户信息GET**/
	private static final String WEBUSERINFO = "https://api.weixin.qq.com/sns/userinfo?" +
									"access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/**网站登录第二步获取accessToken*/
	private static final String	OAUTH_ACCESSTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
					"appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	/**网站登录第三步刷新accessToken*/
	private static final String REFRESH_ACCESSTOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?" +
					"appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	
	/**检验授权凭证（access_token）是否有效**/
	private static final String CHECK_ACCESSTOKEN = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";

	
	/**
	 * 获取appId
	 * @return
	 */
	public static String getAppid() {
		return ReadProperties.getPara("wx_open_appid");
	}

	/**
	 * 获取appSecret
	 * @return
	 */
	public static String getAppsecret() {
		return ReadProperties.getPara("wx_open_appsecret");
	}
	
	
	/**
	 * 获取用户信息
	 * @return
	 */
	public static String getWebuserinfo() {
		return WEBUSERINFO;
	}

	/**
	 * 网站登录第二步获取accessToken
	 * @return
	 */
	public static String getOauthAccesstoken() {
		return OAUTH_ACCESSTOKEN;
	}

	/**
	 * 网站登录第三步刷新accessToken
	 * @return
	 */
	public static String getRefreshAccesstoken() {
		return REFRESH_ACCESSTOKEN;
	}

	/**
	 * 检验授权凭证（access_token）是否有效
	 * @return
	 */
	public static String getCheckAccesstoken() {
		return CHECK_ACCESSTOKEN;
	}

	/**
	 * 网站登录发起地址
	 * @return
	 */
	public static String getWebOauthaccessSnsapiLogin() {
		return WEB_OAUTHACCESS_SNSAPI_LOGIN;
	}

	
}
