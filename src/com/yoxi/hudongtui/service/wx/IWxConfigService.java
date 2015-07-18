package com.yoxi.hudongtui.service.wx;

import com.yoxi.hudongtui.vo.wx.api.AccessToken;
import com.yoxi.hudongtui.vo.wx.api.JsApiTicket;


/**
 * 与微信相关的配置
 * 
 * @author wangql 
 *
 */
public interface IWxConfigService {
	
	/**
	 * 获取全局AccessToken
	 * @param appId 公众号appid
	 * @param appsecret 公众号 appsecret
	 * @return AccessToken accessToken实体
	 */
	public AccessToken getAccessToken(String appId,String appsecret);
	
	/**
	 * 更新数据库存储的全局AccessToken
	 * @param appId 公众号appid
	 * @param appsecret 公众号 appsecret
	 * @return AccessToken accessToken实体
	 */
	public AccessToken updateDBStorAccessToken(String appId,String appsecret);
	
	
	/**
	 * 获取jsapiticket
	 * @param accessToken 公众号当前accessToken
	 * @param appId 公众号appId
	 * @return JsApiTicket JsApiTicket实体
	 */
	public JsApiTicket getJsApiTicket(String accessToken,String appId);
	
	/**
	 * 更新数据库存储的全局jsapiticket
	 * @param accessToken 公众号当前accessToken
	 * @param appId 公众号appId
	 * @return JsApiTicket JsApiTicket实体
	 */
	public JsApiTicket updateDBJsApiTicket(String accessToken,String appId);
}
