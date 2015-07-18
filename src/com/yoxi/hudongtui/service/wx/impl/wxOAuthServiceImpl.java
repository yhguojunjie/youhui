package com.yoxi.hudongtui.service.wx.impl;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.constants.wx.MpConstants;
import com.yoxi.hudongtui.constants.wx.OpenConstanst;
import com.yoxi.hudongtui.service.wx.IWxOAuthService;
import com.yoxi.hudongtui.utils.wx.WeixinUtils;
import com.yoxi.hudongtui.vo.wx.api.oauth.AccessToken;
import com.yoxi.hudongtui.vo.wx.api.oauth.MpOAuthTUser;


@Service
public class wxOAuthServiceImpl implements IWxOAuthService {
	
	private static Logger log = LoggerFactory.getLogger(wxOAuthServiceImpl.class);
	
	/**
	 * 公众平台-Oauth2.0授权第二步获取access_token
	 */
	@Override
	public AccessToken getAccessToke(String code) {
		String apiUrl = MpConstants.getOauthAccessToken().replace("APPID", MpConstants.getAppid())
				.replace("SECRET", MpConstants.getAppsecret()).replace("CODE",code);
		
		JSONObject jsonObject = WeixinUtils.httpsRequest(apiUrl, "GET", null);
		
		AccessToken accessToken = null;
		
		if (null != jsonObject) {
			if (jsonObject.has("errcode") &&  0 != jsonObject.getInt("errcode")) {
				log.error("公众平台-Oauth2.0授权第二步:errcode="+jsonObject.getInt("errcode")+",errmsg="+jsonObject.getString("errmsg")+",code="+code);
			}else{
				accessToken = new AccessToken();
				accessToken.setAccess_token(jsonObject.getString("access_token"));
				accessToken.setExpires_in(jsonObject.getInt("expires_in"));
				accessToken.setOpenid(jsonObject.getString("openid"));
				accessToken.setRefresh_token(jsonObject.getString("refresh_token"));
				accessToken.setScope(jsonObject.getString("scope"));
			}
			
		}
		
		return accessToken;
	}

	/**
	 * 公众平台-Oauth2.0授权第三步刷新access_token（如果需要）
	 */
	@Override
	public AccessToken refreshAccessToken(String refresh_token) {
		
		String apiUrl = MpConstants.getOauthRefreshToken().replace("APPID", MpConstants.getAppid())
				.replace("SECRET", MpConstants.getAppsecret()).replace("REFRESH_TOKEN",refresh_token);
		
		JSONObject jsonObject = WeixinUtils.httpsRequest(apiUrl, "GET", null);
		
		AccessToken accessToken = null;
		
		if (null != jsonObject) {
			if (jsonObject.has("errcode") &&  0 != jsonObject.getInt("errcode")) {
				log.error("公众平台-Oauth2.0授权第三步:errcode="+jsonObject.getInt("errcode")+",errmsg="+jsonObject.getString("errmsg"));
			}else{
				accessToken = new AccessToken();
				accessToken.setAccess_token(jsonObject.getString("access_token"));
				accessToken.setExpires_in(jsonObject.getInt("expires_in"));
				accessToken.setOpenid(jsonObject.getString("openid"));
				accessToken.setRefresh_token(jsonObject.getString("refresh_token"));
				accessToken.setScope(jsonObject.getString("scope"));
			}
			
		}
		return accessToken;
	}

	/**
	 * 公众平台-Oauth2.0授权第四步：拉取用户信息(需scope为 snsapi_userinfo)
	 */
	@Override
	public MpOAuthTUser getUserInfo(AccessToken accessToken) {
		String apiUrl = MpConstants.getOauthUserinfo().replace("ACCESS_TOKEN", accessToken.getAccess_token())
				.replace("OPENID", accessToken.getOpenid());
		
		JSONObject jsonObject = WeixinUtils.httpsRequest(apiUrl, "GET", null);
		MpOAuthTUser user = null;
		if (null != jsonObject) {
			if (jsonObject.has("errcode") &&  0 != jsonObject.getInt("errcode")) {
				log.error("网页授权第四步:errcode="+jsonObject.getInt("errcode")+",errmsg="+jsonObject.getString("errmsg"));
			}else{
				user = new MpOAuthTUser();
				if(jsonObject.has("city")){
					user.setCity(jsonObject.getString("city"));
				}
				if(jsonObject.has("country")){
					user.setCountry(jsonObject.getString("country"));
				}
				if(jsonObject.has("headimgurl")){
					user.setHeadimgurl(jsonObject.getString("headimgurl"));
				}
				if(jsonObject.has("nickname")){
					user.setNickname(jsonObject.getString("nickname"));
				}
				if(jsonObject.has("openid")){
					user.setOpenid(jsonObject.getString("openid"));
				}
				if(jsonObject.has("province")){
					user.setProvince(jsonObject.getString("province"));
				}
				if(jsonObject.has("sex")){
					user.setSex(jsonObject.getInt("sex"));
				}
				if(jsonObject.has("privilege")){
					user.setPrivilege(jsonObject.getString("privilege"));
				}
				if(jsonObject.has("unionid")){
					user.setUnionid(jsonObject.getString("unionid"));
				}
			}
			
		}
		return user;
	}

	/**
	 * 开放平台-oauth2.0授权第二步获取AccessToken
	 */
	@Override
	public AccessToken getOpenAccessToke(String code) {
		
//		log.info("*************OpenConstanst.getAppid()**********="+OpenConstanst.getAppid());
//		log.info("*************OpenConstanst.getAppsecret()**********="+OpenConstanst.getAppsecret());
//		log.info("*************code**********="+code);
		String apiUrl = OpenConstanst.getOauthAccesstoken().replace("APPID", OpenConstanst.getAppid())
				.replace("SECRET", OpenConstanst.getAppsecret()).replace("CODE",code);
		
		JSONObject jsonObject = WeixinUtils.httpsRequest(apiUrl, "GET", null);
		
		AccessToken accessToken = null;
		
		if (null != jsonObject) {
			if (jsonObject.has("errcode") &&  0 != jsonObject.getInt("errcode")) {
				log.error("开放平台-oauth2.0授权第二步:errcode="+jsonObject.getInt("errcode")+",errmsg="+jsonObject.getString("errmsg")+",code="+code);
			}else{
				accessToken = new AccessToken();
				accessToken.setAccess_token(jsonObject.getString("access_token"));
				accessToken.setExpires_in(jsonObject.getInt("expires_in"));
				accessToken.setOpenid(jsonObject.getString("openid"));
				accessToken.setRefresh_token(jsonObject.getString("refresh_token"));
				accessToken.setScope(jsonObject.getString("scope"));
			}
			
		}
		
		return accessToken;
	}

	/**
	 * 开放平台-oauth2.0授权第三步刷新AccessToken
	 */
	@Override
	public AccessToken refreshOenAccessToken(String refresh_token) {
		
		String apiUrl = OpenConstanst.getRefreshAccesstoken().replace("APPID", OpenConstanst.getAppid())
				.replace("SECRET", OpenConstanst.getAppsecret()).replace("REFRESH_TOKEN",refresh_token);
		
		JSONObject jsonObject = WeixinUtils.httpsRequest(apiUrl, "GET", null);
		
		AccessToken accessToken = null;
		
		if (null != jsonObject) {
			if (jsonObject.has("errcode") &&  0 != jsonObject.getInt("errcode")) {
				log.error("开放平台-oauth2.0授权第三步刷新AccessToken"+jsonObject.getInt("errcode")+",errmsg="+jsonObject.getString("errmsg"));
			}else{
				accessToken = new AccessToken();
				accessToken.setAccess_token(jsonObject.getString("access_token"));
				accessToken.setExpires_in(jsonObject.getInt("expires_in"));
				accessToken.setOpenid(jsonObject.getString("openid"));
				accessToken.setRefresh_token(jsonObject.getString("refresh_token"));
				accessToken.setScope(jsonObject.getString("scope"));
			}
			
		}
		return accessToken;
	}


}
