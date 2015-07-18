package com.yoxi.hudongtui.constants.wx;

import com.yoxi.hudongtui.utils.common.ReadProperties;


/**
 * 微信开发使用常量
 * 
 * 
 * @author wangql
 * 
 * 2013-12-25
 *
 */
public class MpConstants {
	
	/**公众平台接口-获取token(GET) **/
	private static final String getAccessTokeUrl = "https://api.weixin.qq.com/cgi-bin/token";
	
	/**公众平台接口-菜单创建(POST) **/
	private static final String MENU_CREATE = 
			"https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**公众平台接口-菜单删除 (GET) **/
	private static final String MENU_DELETE =
			"https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**公众平台接口-永久二维码(POST) **/
	private static final String QR_LIMIT_SCENE =
			"https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=PRAMTOKEN";
	
	/**公众平台接口-临时二维码(POST) **/
	private static final String QR_SCENE = 
			"https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=PRAMTOKEN";
	
	/**公众平台接口-通过ticket换取二维码(GET) **/
	private static final String SHOW_QRCODE = 
			"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=PARMTICKET";
	
	/**公众平台接口-网页授权发起访问的地址**/
	private static final String OAUTH_ACCESS_BASE = 
			"https://open.weixin.qq.com/connect/oauth2/authorize?" +
					"appid=APPID&redirect_uri=REDIRECT_URI" +
					"&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
	/**公众平台接口-网页授权发起访问的地址**/
	private static final String OAUTH_ACCESS_USERINFO = 
			"https://open.weixin.qq.com/connect/oauth2/authorize?" +
					"appid=APPID&redirect_uri=REDIRECT_URI" +
					"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
	
	/**公众平台接口-网页授权第二步,通过code换取网页授权access_token(GET) **/
	private static final String OAUTH_ACCESS_TOKEN = 
			"https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	/**公众平台接口-网页授权第三步：刷新access_token（如果需要）(GET) **/
	private static final String OAUTH_REFRESH_TOKEN = 
    		"https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	
	/**公众平台接口-网页授权第四步：拉取用户信息(需scope为 snsapi_userinfo)(GET) **/
	private static final String OAUTH_USERINFO = 
			"https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**公众平台接口-获取用户基本信息 (GET) **/
	private static final String USER_INFO = 
			"https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
	
	/**公众平台接口-发送客服消息 (POST)**/
	private static final String MESSAGE_CUSTOM = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	
	/**公众平台接口-获取关注者列表 (GET)**/
	private static final String USER_GET = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
	
	/**公众平台接口-发货通知**/
	private static final String DELIVERNOTIFY="https://api.weixin.qq.com/pay/delivernotify?access_token=ACCESS_TOKEN";
	
	/**公众平台接口-上传多媒体文件(POST)**/
	private static final String MEDIAUPLOAD = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	/**公众平台接口-下载多媒体文件(GET)**/
	private static final String MEDIAGET = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	
	/**微信支付，统一支付接口**/
	private static final String UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	/**请求获得jsapi_ticket **/
	private static final String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	/**
	 * 获取开发者模式token
	 * @return
	 */
	public static String getToken() {
		return ReadProperties.getPara("wx_mp_devtoken");
	}

	/**
	 * 获取appId
	 * @return
	 */
	public static String getAppid() {
		return ReadProperties.getPara("wx_mp_appid");
	}

	/**
	 * 获取appSecret
	 * @return
	 */
	public static String getAppsecret() {
		return ReadProperties.getPara("wx_mp_appsecret");
	}

	/**
	 * 公众平台接口-获取accessToke 接口地址
	 * @return
	 */
	public static String getGetaccesstokeurl() {
		return getAccessTokeUrl;
	}

	/**
	 * 公众平台接口-获取创建菜单接口地址
	 * @return
	 */
	public static String getMenuCreate() {
		return MENU_CREATE;
	}

	/**
	 * 公众平台接口-获取删除菜单接口地址
	 * @return
	 */
	public static String getMenuDelete() {
		return MENU_DELETE;
	}

	/**
	 * 公众平台接口-获取永久二维码接口地址
	 * @return
	 */
	public static String getQrLimitScene() {
		return QR_LIMIT_SCENE;
	}
	
	/**
	 * 公众平台接口-获取临时二维码接口地址
	 * @return
	 */

	public static String getQrScene() {
		return QR_SCENE;
	}

	/**
	 * 公众平台接口-通过ticket换取二维码
	 * @return
	 */
	public static String getShowQrcode() {
		return SHOW_QRCODE;
	}

	/**
	 * 公众平台接口-网页授权第二步,通过code换取网页授权access_token
	 * @return
	 */
	public static String getOauthAccessToken() {
		return OAUTH_ACCESS_TOKEN;
	}

	/**
	 * 公众平台接口-网页授权第三步：刷新access_token（如果需要）
	 * @return
	 */
	public static String getOauthRefreshToken() {
		return OAUTH_REFRESH_TOKEN;
	}
	
	/**
	 * 公众平台接口-网页授权第四步：拉取用户信息(需scope为 snsapi_userinfo)(GET)
	 * @return
	 */
	public static String getOauthUserinfo() {
		return OAUTH_USERINFO;
	}

	/**
	 * 公众平台接口-获取用户基本信息 (GET)
	 * @return
	 */
	public static String getUserInfo() {
		return USER_INFO;
	}

	/**
	 * 公众平台接口-发送客服消息(POST)
	 * @return
	 */
	public static String getMessageCustom() {
		return MESSAGE_CUSTOM;
	}
	
	/**
	 * 公众平台接口-获取关注者列表(GET)
	 * @return
	 */
	public static String getUserGet() {
		return USER_GET;
	}

	/**
	 * 公众平台接口-微信支付 发货通知
	 * @return
	 */
	public static String getDelivernotify() {
		return DELIVERNOTIFY;
	}

	/**
	 * 公众平台接口-获取网页授权发起地址base方式 (GET)
	 * @return
	 */
	public static String getOauthAccessBase() {
		return OAUTH_ACCESS_BASE.replace("APPID",getAppid());
	}

	/**
	 * 公众平台接口-获取网页授权发起地址USERINOF方式 (GET)
	 * @return
	 */
	public static String getOauthAccessUserinfo() {
		return OAUTH_ACCESS_USERINFO.replace("APPID",getAppid());
	}

	/**
	 * 公众平台接口-上传多媒体文件(POST)
	 * @return
	 */
	public static String getMediaupload() {
		return MEDIAUPLOAD;
	}

	/**
	 * 公众平台接口-下载多媒体文件(GET)
	 * @return
	 */
	public static String getMediaget() {
		return MEDIAGET;
	}

	/**
	 * 微信支付-统一支付接口
	 * @return
	 */
	public static String getUnifiedorder() {
		return UNIFIEDORDER;
	}

	/**
	 * 请求获得jsapi_ticket
	 * @return
	 */
	public static String getJsapiTicket() {
		return JSAPI_TICKET;
	}
	
	
}
