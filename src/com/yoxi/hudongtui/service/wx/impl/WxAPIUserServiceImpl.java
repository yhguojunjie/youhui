package com.yoxi.hudongtui.service.wx.impl;

import java.util.Date;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yoxi.hudongtui.constants.wx.MpConstants;
import com.yoxi.hudongtui.constants.wx.OpenConstanst;
import com.yoxi.hudongtui.dao.wx.WxUserInfoDAO;
import com.yoxi.hudongtui.model.wx.WxUserInfo;
import com.yoxi.hudongtui.service.wx.IWxAPIUserService;
import com.yoxi.hudongtui.service.wx.IWxConfigService;
import com.yoxi.hudongtui.utils.wx.WeixinUtils;
import com.yoxi.hudongtui.vo.wx.api.oauth.MpOAuthTUser;
import com.yoxi.hudongtui.vo.wx.api.user.OpenUserInfo;
import com.yoxi.hudongtui.vo.wx.api.user.MpUserInfo;


@Service
public class WxAPIUserServiceImpl implements IWxAPIUserService {

	private static Logger log = LoggerFactory.getLogger(WxAPIUserServiceImpl.class);
	
	@Autowired
	private IWxConfigService wxConfigService;
	
	@Autowired
	private WxUserInfoDAO wxUserInfoDAO;
	
	/**
	 * 调用微信公众平获取用户信息接口获取数据
	 */
	@Override
	public MpUserInfo mpGetUserInfo(String openId) {
		
		String apiUrl = MpConstants.getUserInfo().replace("ACCESS_TOKEN",wxConfigService.getAccessToken(MpConstants.getAppid(),MpConstants.getAppsecret()).getAccessToken())
				.replace("OPENID", openId);
		
		JSONObject jsonObject = WeixinUtils.httpsRequest(apiUrl, "GET", null); 
		
		MpUserInfo userInfo = null;
		
		 if (null != jsonObject) { 
				if (jsonObject.has("errcode") && 0 != jsonObject.getInt("errcode")) {
					 log.error("微信接口获取用户基本信息失败：errcode="+jsonObject.getInt("errcode")+",errmsg="+jsonObject.getString("errmsg")); 
					 //accessToken出错情况下，重新刷新
					 if(jsonObject.getInt("errcode") == 40001 && "invalid credential".equals(jsonObject.getString("errmsg"))){
						  wxConfigService.updateDBStorAccessToken(MpConstants.getAppid(),MpConstants.getAppsecret());
					 }
				}else{
					if(jsonObject.has("subscribe") && jsonObject.getInt("subscribe") == 0){
						userInfo = new MpUserInfo();
						userInfo.setSubscribe(jsonObject.getInt("subscribe"));
						userInfo.setOpenid(openId);
						if(jsonObject.has("unionid")){
							userInfo.setUnionid(jsonObject.getString("unionid"));
						}
					}else{
						userInfo = new MpUserInfo();
						if(jsonObject.has("city")){
							userInfo.setCity(jsonObject.getString("city"));
						}
						if(jsonObject.has("country")){
							userInfo.setCountry(jsonObject.getString("country"));
						}
						if(jsonObject.has("headimgurl")){
							userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
						}
						if(jsonObject.has("language")){
							userInfo.setLanguage(jsonObject.getString("language"));
						}
						if(jsonObject.has("nickname")){
							userInfo.setNickname(jsonObject.getString("nickname"));
						}
						if(jsonObject.has("openid")){
							userInfo.setOpenid(jsonObject.getString("openid"));
						}
						if(jsonObject.has("province")){
							userInfo.setProvince(jsonObject.getString("province"));
						}
						if(jsonObject.has("sex")){
							userInfo.setSex(jsonObject.getInt("sex"));
						}
						if(jsonObject.has("subscribe")){
							userInfo.setSubscribe(jsonObject.getInt("subscribe"));
						}
						if(jsonObject.has("subscribe_time")){
							userInfo.setSubscribe_time(jsonObject.getLong("subscribe_time"));
						}
						if(jsonObject.has("unionid")){
							userInfo.setUnionid(jsonObject.getString("unionid"));
						}						
					}

				}
		  } 
		
		return userInfo;
	}
	
    /**
     * 调用微信开放平台用户信息接口
     */
	@Override
	public OpenUserInfo webGetUserInfo(String accessToken,String openId) {
		
		String apiUrl = OpenConstanst.getWebuserinfo().replace("ACCESS_TOKEN",accessToken).replace("OPENID", openId);
		
		JSONObject jsonObject = WeixinUtils.httpsRequest(apiUrl, "GET", null); 
		
		OpenUserInfo openUserInfo = null;
		 if (null != jsonObject) { 
				if (jsonObject.has("errcode") && 0 != jsonObject.getInt("errcode")) {
					 log.error("微信接口获取用户基本信息失败：errcode="+jsonObject.getInt("errcode")+",errmsg="+jsonObject.getString("errmsg")); 
				}else{
						openUserInfo = new OpenUserInfo();
						if(jsonObject.has("openid")){
							openUserInfo.setOpenid(jsonObject.getString("openid"));
						}
						if(jsonObject.has("nickname")){
							openUserInfo.setNickname(jsonObject.getString("nickname"));
						}
						if(jsonObject.has("sex")){
							openUserInfo.setSex(jsonObject.getInt("sex"));
						}
						if(jsonObject.has("province")){
							openUserInfo.setProvince(jsonObject.getString("province"));
						}
						if(jsonObject.has("city")){
							openUserInfo.setCity(jsonObject.getString("city"));
						}
						if(jsonObject.has("country")){
							openUserInfo.setCountry(jsonObject.getString("country"));
						}
						if(jsonObject.has("headimgurl")){
							openUserInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
						}
						if(jsonObject.has("privilege")){
							openUserInfo.setPrivilege(jsonObject.getString("privilege"));
						}
						if(jsonObject.has("unionid")){
							openUserInfo.setUnionid(jsonObject.getString("unionid"));
						}
					}
				}
		
		return openUserInfo;
	}
	
	

//	/**
//	 * 从微信服务器更新用户信息入库
//	 */
//	@Override
//	public WxUserInfo updateUserInfoFromWx(Integer userId,String openId) {
//		
//		WxUserInfo dbWxUser = wxUserInfoDAO.findByOpenId(openId);
//		
//		OpenUserInfo userInfo = mpGetUserInfo(openId);
//		
//		WxUserInfo mpUserInfo = null;
//		
//		if(userInfo!= null ){
//			if(userInfo.getSubscribe() != 0){
//				userInfo.setNickname(EmojiFilter.filterEmoji(userInfo.getNickname()));
//				if(dbWxUser != null){
//					mpUserInfo = transFormUserInfo(userInfo);
//					mpUserInfo.setUserId(userId);
//					mpUserInfo.setUpdateTime(new Date());
//					mpUserInfo.setUnsubscribe_time(dbWxUser.getUnsubscribe_time());
//					wxUserInfoDAO.update(mpUserInfo);
//				}else{
//					mpUserInfo = transFormUserInfo(userInfo);
//					mpUserInfo.setUserId(userId);
//					mpUserInfo.setOpenId(openId);
//					mpUserInfo.setUpdateTime(new Date());
//					wxUserInfoDAO.add(mpUserInfo);
//				}
//			}else{
//				mpUserInfo = new WxUserInfo();
//				if(dbWxUser != null){
//					mpUserInfo.setUserId(userId);
//					mpUserInfo.setOpenId(openId);
//					mpUserInfo.setSubscribe(0);
//					wxUserInfoDAO.update(mpUserInfo);
//				}else{
//					mpUserInfo.setUserId(userId);
//					mpUserInfo.setOpenId(openId);
//					mpUserInfo.setUpdateTime(new Date());
//					mpUserInfo.setSubscribe(0);
//					wxUserInfoDAO.add(mpUserInfo);
//				}
//
//			}
//
//		}
//		
//		return mpUserInfo;
//	}
	
//	/**
//	 * 从微信服务器更新用户信息入库
//	 */
//	@Override
//	public WxUserInfo updateUserInfoFromWx(Integer userId,OpenUserInfo userInfo) {
//		
//		WxUserInfo dbWxUser = wxUserInfoDAO.findByOpenId(userInfo.getOpenid());
//		
//		WxUserInfo wxUserInfo = null;
//		
//		if(userInfo!= null ){
//			if(userInfo.getSubscribe() != 0){
//				if(dbWxUser != null){
//					wxUserInfo = transFormUserInfo(userInfo);
//					wxUserInfo.setUserId(userId);
//					wxUserInfo.setUpdateTime(new Date());
//					wxUserInfo.setUnsubscribe_time(dbWxUser.getUnsubscribe_time());
//					wxUserInfoDAO.update(wxUserInfo);
//				}else{
//					wxUserInfo = transFormUserInfo(userInfo);
//					wxUserInfo.setUserId(userId);
//					wxUserInfo.setOpenId(userInfo.getOpenid());
//					wxUserInfo.setUpdateTime(new Date());
//					wxUserInfoDAO.add(wxUserInfo);
//				}
//			}else{
//				wxUserInfo = new WxUserInfo();
//				if(dbWxUser != null){
//					wxUserInfo.setUserId(userId);
//					wxUserInfo.setOpenId(userInfo.getOpenid());
//					wxUserInfo.setSubscribe(0);
//					wxUserInfo.setUnionid(userInfo.getUnionid());
//					wxUserInfoDAO.update(wxUserInfo);
//				}else{
//					wxUserInfo.setUserId(userId);
//					wxUserInfo.setOpenId(userInfo.getOpenid());
//					wxUserInfo.setUpdateTime(new Date());
//					wxUserInfo.setSubscribe(0);
//					wxUserInfo.setUnionid(userInfo.getUnionid());
//					wxUserInfoDAO.add(wxUserInfo);
//				}
//
//			}
//
//		}
//		return wxUserInfo;
//	}
	
	/**
	 * 微信api接口返回数据封装类转换数据库实体
	 * @param userInfo
	 * @return
	 */
	public WxUserInfo transFormUserInfo(MpUserInfo userInfo){
		WxUserInfo wxUserInfo = new WxUserInfo();
		wxUserInfo.setCity(userInfo.getCity());
		wxUserInfo.setCountry(userInfo.getCountry());
		wxUserInfo.setHeadimgurl(userInfo.getHeadimgurl());
		wxUserInfo.setLanguage(userInfo.getLanguage());
		wxUserInfo.setNickname(userInfo.getNickname());
		wxUserInfo.setMpOpenId(userInfo.getOpenid());
		wxUserInfo.setProvince(userInfo.getProvince());
		wxUserInfo.setSex(userInfo.getSex());
		wxUserInfo.setSubscribe(userInfo.getSubscribe());
		wxUserInfo.setSubscribe_time(String.valueOf(userInfo.getSubscribe_time()));
		wxUserInfo.setUnionid(userInfo.getUnionid());
		return wxUserInfo;
	}

//	/**
//	 * 更新数据库字段
//	 */
//	@Override
//	public int updateByCriteria(WxUserInfo wxUserInfo, String key, String value) {
//		return wxUserInfoDAO.updateByCriteria(wxUserInfo,key,value);
//	}
//
//	@Override
//	public WxUserInfo queryByUserId(Integer userId) {
//		return wxUserInfoDAO.findByUserId(userId);
//	}

//	/**
//	 * 网页授权为userinfo情况下，从微信服务器获取数据入库
//	 */
//	@Override
//	public WxUserInfo updateUserInfoFromWx(Integer userId, MpOAuthTUser user) {
//		
//		user.setNickname(EmojiFilter.filterEmoji(user.getNickname()));
//		
//		WxUserInfo dbwxUserInfo =  wxUserInfoDAO.findByUserId(userId);
//		
//		WxUserInfo mpWxUser = null;
//		
//		if(dbwxUserInfo != null){
//			mpWxUser = transFormUser(user);
//			mpWxUser.setUserId(userId);
//			mpWxUser.setUpdateTime(new Date());
//			mpWxUser.setLanguage(dbwxUserInfo.getLanguage());
//			mpWxUser.setSubscribe(dbwxUserInfo.getSubscribe());
//			mpWxUser.setSubscribe_time(dbwxUserInfo.getSubscribe_time());
//			mpWxUser.setUnsubscribe_time(dbwxUserInfo.getUnsubscribe_time());
//			wxUserInfoDAO.update(mpWxUser);
//			
//		}else{
//			//新增加
//			mpWxUser = transFormUser(user);
//			mpWxUser.setUserId(userId);
//			mpWxUser.setUpdateTime(new Date());
//			mpWxUser.setLanguage("");
//			mpWxUser.setSubscribe(0);
//			wxUserInfoDAO.add(mpWxUser);
//		}
//		
//		return mpWxUser;
//	}

	/**
	 *  网页授权为userinfo情况下 用户封装类转换数据库实体
	 * @param userInfo
	 * @return
	 */
	private WxUserInfo transFormUser(MpOAuthTUser user){
		
		WxUserInfo mpWxUser = new WxUserInfo();
		mpWxUser.setCity(user.getCity());
		mpWxUser.setCountry(user.getCountry());
		mpWxUser.setHeadimgurl(user.getHeadimgurl());
		mpWxUser.setNickname(user.getNickname());
//		mpWxUser.setOpenId(user.getOpenid());
		mpWxUser.setProvince(user.getProvince());
		mpWxUser.setSex(user.getSex());
		return mpWxUser;
	}

	
//	/**
//	 * 更新数据库信息
//	 */
//	@Override
//	public int updateMpWxUser(WxUserInfo wxUserInfo){
//		WxUserInfo dbWxUser =  wxUserInfoDAO.findByUserIdOpenId(wxUserInfo.getUserId(),wxUserInfo.getOpenId());
//		int ret = 0;
//		if(dbWxUser != null){
//			 ret =wxUserInfoDAO.update(wxUserInfo);
//		}else{
//			ret = wxUserInfoDAO.add(wxUserInfo).intValue();
//		}
//		return ret;
//	}
	
	
}
