package com.yoxi.hudongtui.service.wx;

import com.yoxi.hudongtui.vo.wx.api.user.OpenUserInfo;
import com.yoxi.hudongtui.vo.wx.api.user.MpUserInfo;

/**
 * 与用户相关的微信接口操作
 * 
 * @author wangql
 * 
 * 2014-11-19
 *
 */
public interface IWxAPIUserService {
	
	
	/**
	 * web网站获取用户基本信息
	 * @param accessToken oauth认证过程中返回的accessToken
	 * @param openId  普通用户的标识，对当前开发者帐号唯一
	 * @return
	 */
	public OpenUserInfo webGetUserInfo(String accessToken,String openId);
	
	
	/**
	 * 公众平台获取用户基本信息
	 * @param openId 普通用户的标识，对当前公众号唯一
	 * @return
	 */
	public MpUserInfo mpGetUserInfo(String openId);
	
	
//	/**
//	 * 更新微信用户信息到数据库
//	 * @param userId 乐阅用户id
//	 * @param openid 微信唯一ID
//	 */
//	public WxUserInfo updateUserInfoFromWx(Integer userId, String openid);
//	
//	/**
//	 * 
//	 * @param 保存或者更新微信用户信息到数据库
//	 * @param userId 乐阅用户id
//	 * @param userInfo 微信返回信息封装实体
//	 * @return
//	 */
//	public WxUserInfo updateUserInfoFromWx(Integer userId,OpenUserInfo userInfo);
//	
//	/**
//	 * 微信返回信息封装实体和本地用户实体转换
//	 * @param userInfo
//	 * @return
//	 */
//	public WxUserInfo transFormUserInfo(OpenUserInfo userInfo);
//	
//	/**
//	 * 按字段更新实体
//	 * @param mpWxUser
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public int updateByCriteria(WxUserInfo wxUserInfo,String key,String value);
//	
//	/**
//	 * 按userId 查找
//	 * @param userId
//	 * @return
//	 */
//	public WxUserInfo queryByUserId(Integer userId);
//	
//	/**
//	 * 
//	 * @param 保存或者更新oauth2.0第四步获取微信用户信息到数据库
//	 * @return
//	 */
//	public WxUserInfo updateUserInfoFromWx(Integer userId,MpOAuthTUser user);
//	
//	/**
//	 * 修改信息入库
//	 * @param mpWxUser
//	 * @return
//	 */
//	public int updateMpWxUser(WxUserInfo wxUserInfo);
}
