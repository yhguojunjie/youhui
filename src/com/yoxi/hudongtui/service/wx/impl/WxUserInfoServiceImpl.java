package com.yoxi.hudongtui.service.wx.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.wx.WxUserInfoDAO;
import com.yoxi.hudongtui.model.wx.WxUserInfo;
import com.yoxi.hudongtui.service.wx.IWxUserInfoService;
import com.yoxi.hudongtui.utils.common.EmojiFilter;
import com.yoxi.hudongtui.vo.wx.api.oauth.MpOAuthTUser;
import com.yoxi.hudongtui.vo.wx.api.user.MpUserInfo;
import com.yoxi.hudongtui.vo.wx.api.user.OpenUserInfo;

@Service
public class WxUserInfoServiceImpl implements IWxUserInfoService {
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private WxUserInfoDAO wxUserInfoDAO;
	
	@Override
	public WxUserInfo updateOpenUserInfo(OpenUserInfo openUserInfo,Integer userId) {
		WxUserInfo dbWxUser = wxUserInfoDAO.findByUserId(userId);
		WxUserInfo wxUserInfo = null;
	    if(openUserInfo != null){
			if(dbWxUser != null){
				wxUserInfo = transFromOpenUserInfo(openUserInfo);
				wxUserInfo.setUserId(userId);
				wxUserInfo.setUpdateTime(new Date());
				wxUserInfo.setUnsubscribe_time(dbWxUser.getUnsubscribe_time());
				wxUserInfo.setAppOpenId(dbWxUser.getAppOpenId());
				wxUserInfo.setMpOpenId(dbWxUser.getMpOpenId());
				wxUserInfo.setSubscribe(dbWxUser.getSubscribe());
				wxUserInfo.setSubscribe_time(dbWxUser.getSubscribe_time());
				wxUserInfo.setLanguage(dbWxUser.getLanguage());
				wxUserInfoDAO.update(wxUserInfo);
			}else{
				wxUserInfo = transFromOpenUserInfo(openUserInfo);
				wxUserInfo.setUserId(userId);
				wxUserInfo.setUpdateTime(new Date());
				wxUserInfoDAO.add(wxUserInfo);
			}
		}
		return wxUserInfo;
	}
	
	
	/**
	 * 
	 * @param openUserInfo
	 * @return
	 */
	public WxUserInfo transFromOpenUserInfo(OpenUserInfo openUserInfo){
		
		WxUserInfo wxUserInfo = new WxUserInfo();
		wxUserInfo.setCity(openUserInfo.getCity());
		wxUserInfo.setCountry(openUserInfo.getCountry());
		wxUserInfo.setHeadimgurl(openUserInfo.getHeadimgurl());
		wxUserInfo.setNickname(EmojiFilter.filterEmoji(openUserInfo.getNickname()));
		wxUserInfo.setWebOpenId(openUserInfo.getOpenid());
		wxUserInfo.setProvince(openUserInfo.getProvince());
		wxUserInfo.setUnionid(openUserInfo.getUnionid());
		wxUserInfo.setSex(openUserInfo.getSex());
		wxUserInfo.setCity(openUserInfo.getCity());
		wxUserInfo.setPrivilege(openUserInfo.getPrivilege());
		return wxUserInfo;
	}


	@Override
	public WxUserInfo updateMpUserInfo(MpUserInfo mpUserInfo, Integer userId) {
	
		WxUserInfo dbWxUser = wxUserInfoDAO.findByUserId(userId);
		
		WxUserInfo wxUserInfo = null;
		
	    if(mpUserInfo != null){
			if(dbWxUser != null){
				wxUserInfo = transFromMpUserInfo(mpUserInfo);
				wxUserInfo.setUserId(userId);
				wxUserInfo.setUpdateTime(new Date());
				wxUserInfo.setUnsubscribe_time(dbWxUser.getUnsubscribe_time());
				wxUserInfo.setAppOpenId(dbWxUser.getAppOpenId());
				wxUserInfo.setWebOpenId(dbWxUser.getWebOpenId());
				wxUserInfoDAO.update(wxUserInfo);
			}else{
				wxUserInfo = transFromMpUserInfo(mpUserInfo);
				wxUserInfo.setUserId(userId);
				wxUserInfo.setUpdateTime(new Date());
				wxUserInfoDAO.add(wxUserInfo);
			}

		}
		return wxUserInfo;
	}
	
	/**
	 * 
	 * @param openUserInfo
	 * @return
	 */
	public WxUserInfo transFromMpUserInfo(MpUserInfo mpUserInfo){
		
		WxUserInfo wxUserInfo = new WxUserInfo();
		wxUserInfo.setCity(mpUserInfo.getCity());
		wxUserInfo.setCountry(mpUserInfo.getCountry());
		wxUserInfo.setHeadimgurl(mpUserInfo.getHeadimgurl());
		wxUserInfo.setNickname(EmojiFilter.filterEmoji(mpUserInfo.getNickname()));
		wxUserInfo.setMpOpenId(mpUserInfo.getOpenid());
		wxUserInfo.setProvince(mpUserInfo.getProvince());
		wxUserInfo.setUnionid(mpUserInfo.getUnionid());
		wxUserInfo.setSex(mpUserInfo.getSex());
		wxUserInfo.setCity(mpUserInfo.getCity());
		wxUserInfo.setLanguage(mpUserInfo.getLanguage());
		wxUserInfo.setSubscribe(mpUserInfo.getSubscribe());
		wxUserInfo.setSubscribe_time(String.valueOf(mpUserInfo.getSubscribe_time()));
		wxUserInfo.setUnionid(mpUserInfo.getUnionid());
		
		
		return wxUserInfo;
	}


	@Override
	public WxUserInfo updateMpOAuthTUser(MpOAuthTUser mpOAuthTUser,Integer userId) {
		
		WxUserInfo dbWxUser = wxUserInfoDAO.findByUserId(userId);
		
		WxUserInfo wxUserInfo = null;
		
	    if(mpOAuthTUser != null){
			if(dbWxUser != null){
				wxUserInfo = transFromMpOAuthTUser(mpOAuthTUser);
				wxUserInfo.setUserId(userId);
				wxUserInfo.setUpdateTime(new Date());
				wxUserInfo.setUnsubscribe_time(dbWxUser.getUnsubscribe_time());
				wxUserInfo.setAppOpenId(dbWxUser.getAppOpenId());
				wxUserInfo.setWebOpenId(dbWxUser.getWebOpenId());
				wxUserInfo.setLanguage(dbWxUser.getLanguage());
				wxUserInfoDAO.update(wxUserInfo);
				
			}else{
				wxUserInfo = transFromMpOAuthTUser(mpOAuthTUser);
				wxUserInfo.setUserId(userId);
				wxUserInfo.setUpdateTime(new Date());
				wxUserInfoDAO.add(wxUserInfo);
			}

		}
		return wxUserInfo;
	}
	
	
	/**
	 * 
	 * @param openUserInfo
	 * @return
	 */
	public WxUserInfo transFromMpOAuthTUser(MpOAuthTUser mpOAuthTUser){
		
		WxUserInfo wxUserInfo = new WxUserInfo();
		wxUserInfo.setCity(mpOAuthTUser.getCity());
		wxUserInfo.setCountry(mpOAuthTUser.getCountry());
		wxUserInfo.setHeadimgurl(mpOAuthTUser.getHeadimgurl());
		wxUserInfo.setNickname(EmojiFilter.filterEmoji(mpOAuthTUser.getNickname()));
		wxUserInfo.setMpOpenId(mpOAuthTUser.getOpenid());
		wxUserInfo.setProvince(mpOAuthTUser.getProvince());
		wxUserInfo.setUnionid(mpOAuthTUser.getUnionid());
		wxUserInfo.setSex(mpOAuthTUser.getSex());
		wxUserInfo.setCity(mpOAuthTUser.getCity());
		wxUserInfo.setPrivilege(mpOAuthTUser.getPrivilege());
		
		return wxUserInfo;
	}


	@Override
	public WxUserInfo findByKey(String key, String value) {
		return wxUserInfoDAO.findBykey(key, value);
	}


	@Override
	public Integer userExist(Integer userId) {
		return wxUserInfoDAO.userExist(userId);
	}
	
}
