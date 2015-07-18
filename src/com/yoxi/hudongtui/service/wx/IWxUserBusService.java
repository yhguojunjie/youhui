package com.yoxi.hudongtui.service.wx;

import javax.servlet.http.HttpServletRequest;

import com.yoxi.hudongtui.vo.user.ThirdVO;
import com.yoxi.hudongtui.vo.wx.MpThirdVO;
import com.yoxi.hudongtui.vo.wx.api.oauth.AccessToken;


/**
 * 与微信业务相关的用户服务
 * @author wangql
 *
 */
public interface IWxUserBusService {

	 /**
	  * 网站微信授权认证完成第三方登录
	  * @param code
	  * @param role
	  * @return
	  */
	 public ThirdVO webLogin(HttpServletRequest req,String role);
	
	/**
	 * 公众平台微信用户隐形登录
	 * @param openId
	 * @return
	 */
	public MpThirdVO mpLogin(HttpServletRequest req,Integer scope,String role);
	
	/**
	 * 公众平台网页oauth用户信息获取
	 * @param req
	 * @param scope
	 * @return
	 */
	public MpThirdVO mpOauthGetUser(HttpServletRequest req,Integer scope);
	
	/**
	 * 公众平台网页oauth用户信息获取(带缓存处理)
	 * @param openId 
	 * @param expired 过期时间
	 * @return
	 * @throws Exception
	 */
	public MpThirdVO mpOauthGetUserCached(AccessToken accessToken,Integer scope,int expired)throws Exception;
	
	
//	/**
//	 * 按openid获取微信用户信息
//	 * 已加缓存机制
//	 * @param openId
//	 * @return
//	 */
//	public WxUserInfo getMpWxUserByOpenId(String openId)throws Exception;
//	
//	
//	/**
//	 * 根据openId 查找当前用户关注状态(带缓存)
//	 * @param openId
//	 * @return
//	 */
//	public Integer getSubStatusByOpenId(String openId);
//	
//	/**
//	 * 根据openId 查找当前用户关注状态(带缓存)
//	 * @param openId
//	 * @param retNum 微信接口出错情况下默认返回的值
//	 * @return
//	 */
//	public int getSubStatusByOpenId(String openId,int retNum);
//	
//	/**
//	 * 根据openId 查找当前用户关注状态(不带缓存)
//	 * @param openId
//	 * @param retNum 微信接口出错情况下默认返回的值
//	 * @return 
//	 */
//	public int getSubStatusByOpenIdNoCached(String openId,int retNum);
//	
//	/**
//	 * 更新微信用户关注状态
//	 * 只更新缓存
//	 * @param openId
//	 * @return
//	 */
//	public void updateWxUserSubscribe(String openId, int subStatus);
//	
//	/**
//	 * 用户关注与取消关注后台业务操作
//	 * @param openId
//	 * @param subStatus
//	 * @return
//	 */
//	public void processUserSub(String openId, int subStatus);
//	
//
//	/**
//	 * 处理所有关注列表
//	 */
//	public Subscribe SyncAllSubscribe(String nextOpenId);
//	
//	
//	/**
//	 * 从微信服务器获取关注者列表
//	 * @param nextOpenid  下一个关注者的openid
//	 * @return
//	 */
//	public Subscribe getSubscribeListFromWx(String nextOpenId);
//	
//	/**
//	 * 关注列表存储到缓存或服务器
//	 * 
//	 */
//	public void saveSubscribeList(Subscribe subscribe);
//	
//	
//	/**
//	 * 删除memcaced中缓存 
//	 * @throws Exception
//	 */
//	public void clearUserCached(String key)throws Exception;
//	
//	/**
//	 * 更新用户与公众号交互记录
//	 * @param openid
//	 * @param msgType
//	 * @return
//	 * @throws Exception
//	 */
//	public MpWxInteract saveOrUpdateMpWxInteract(String openid,String msgType);
	
}
