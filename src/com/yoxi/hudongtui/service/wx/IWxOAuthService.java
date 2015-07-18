package com.yoxi.hudongtui.service.wx;

import com.yoxi.hudongtui.vo.wx.api.oauth.AccessToken;
import com.yoxi.hudongtui.vo.wx.api.oauth.MpOAuthTUser;



/**
 * 
 * 微信Oauth2.0相关
 * 
 * @author wangql 
 * 
 * 2014-11-19
 *
 */
public interface IWxOAuthService {

	/**
	 * 公众平台-Oauth2.0授权第二步获取access_token
	 * @param code 第一步中所获取的code 
	 * @return
	 */
	public AccessToken getAccessToke(String code); 
	
	/**
	 * 公众平台-Oauth2.0授权第三步刷新access_token（如果需要）
	 * @param refresh_token
	 * @return
	 */
	public AccessToken refreshAccessToken(String refresh_token);
	
	
	/**
	 * 公众平台-Oauth2.0授权第四步：拉取用户信息(需scope为 snsapi_userinfo)
	 * @return
	 */
	public MpOAuthTUser getUserInfo(AccessToken accessToken);
	
	
	/**
	 * 开放平台-Oauth2.0授权第二步获取access_token
	 * @param code 第一步中所获取的code 
	 * @return
	 */
	public AccessToken getOpenAccessToke(String code); 
	
	/**
	 * 开放平台-Oauth2.0授权第三步刷新access_token（如果需要）
	 * @param refresh_token
	 * @return
	 */
	public AccessToken refreshOenAccessToken(String refresh_token);
}
