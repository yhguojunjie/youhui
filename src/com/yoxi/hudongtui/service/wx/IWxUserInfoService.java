package com.yoxi.hudongtui.service.wx;

import com.yoxi.hudongtui.model.wx.WxUserInfo;
import com.yoxi.hudongtui.vo.wx.api.oauth.MpOAuthTUser;
import com.yoxi.hudongtui.vo.wx.api.user.MpUserInfo;
import com.yoxi.hudongtui.vo.wx.api.user.OpenUserInfo;

/**
 * 
 * 微信用户信息表service
 * 
 * @author wql
 *
 */
public interface IWxUserInfoService {

	/**
	 * 开放平台用户信息接口入库
	 * @param openUserInfo
	 * @return
	 */
	public WxUserInfo updateOpenUserInfo(OpenUserInfo openUserInfo,Integer userId);
	
	
	/**
	 * 公众平台用户信息入库
	 * @param mpUserInfo
	 * @param userId
	 * @return
	 */
	public WxUserInfo updateMpUserInfo(MpUserInfo mpUserInfo,Integer userId);
	
	/**
	 * 公众平台-oauth授权第三步用户信息入库
	 * @return
	 */
	public WxUserInfo updateMpOAuthTUser(MpOAuthTUser mpOAuthTUser,Integer userId);
	
	/**
	 * 按key查找
	 * @param key
	 * @param value
	 * @return
	 */
	public WxUserInfo findByKey(String key, String value);
	
	/**
	 * 判断系统用户是否绑定了微信账号
	 * @param userId
	 * @return 查找返回的主键Id
	 */
	public Integer userExist(Integer userId);
}
