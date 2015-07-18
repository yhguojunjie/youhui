package com.yoxi.hudongtui.service.wx.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.constants.MemcachedConstans;
import com.yoxi.hudongtui.dao.wx.WxUserInfoDAO;
import com.yoxi.hudongtui.model.user.Third;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.model.wx.WxUserInfo;
import com.yoxi.hudongtui.service.user.IThirdService;
import com.yoxi.hudongtui.service.wx.IWxAPIUserService;
import com.yoxi.hudongtui.service.wx.IWxOAuthService;
import com.yoxi.hudongtui.service.wx.IWxUserBusService;
import com.yoxi.hudongtui.service.wx.IWxUserInfoService;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.vo.user.ThirdVO;
import com.yoxi.hudongtui.vo.wx.MpThirdVO;
import com.yoxi.hudongtui.vo.wx.api.oauth.AccessToken;
import com.yoxi.hudongtui.vo.wx.api.oauth.MpOAuthTUser;
import com.yoxi.hudongtui.vo.wx.api.user.MpUserInfo;
import com.yoxi.hudongtui.vo.wx.api.user.OpenUserInfo;

@Service
public class WxUserBusServiceImpl implements IWxUserBusService {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private IWxOAuthService wxOAuthService;

	@Autowired
	private IWxAPIUserService wxAPIUserService;

	@Autowired
	private IWxUserInfoService wxUserInfoService;

	@Autowired
	private IThirdService thirdService;

	@Autowired
	private WxUserInfoDAO wxUserInfoDAO;

	@Autowired
	private MemcachedClient memcachedClient;

	/**
	 * 微信网站登录
	 */
	@Override
	public ThirdVO webLogin(HttpServletRequest req, String role) {

		ThirdVO thirdVO = null;

		String code = req.getParameter("code");

		if (!StringUtils.isNullBlank(code)) {

			AccessToken accessToken = wxOAuthService.getOpenAccessToke(code);

			if (accessToken != null) {

				String openId = accessToken.getOpenid();

				OpenUserInfo openUserInfo = wxAPIUserService.webGetUserInfo(accessToken.getAccess_token(), openId);

				String unionId = openUserInfo.getUnionid();
//				log.info("************unionId***********=" + unionId);
				if (!StringUtils.isNullBlank(unionId)) {
					WxUserInfo wxUserInfo = wxUserInfoDAO.findByUnionId(unionId);
//					log.info("************wxUserInfo***********=" + wxUserInfo);
					if (wxUserInfo == null) {// 用户还未入库情况
						// 组装第三方授权信息表
						Third third = new Third();
						third.setThirdId(unionId);
						third.setAccessToken(accessToken.getAccess_token());
						third.setSource(Globals.USERSOURCE_WEIXIN);
						// 组装用户表信息
						User user = new User();
						// log.info("************openUserInfo.getNickname()***********="+openUserInfo.getNickname());
						// log.info("************openUserInfo.getCity()***********="+openUserInfo.getCity());
						// log.info("************openUserInfo.getProvince()***********="+openUserInfo.getProvince());
						user.setNickName(openUserInfo.getNickname());
						user.setHeadimgUrl(openUserInfo.getHeadimgurl());
						user.setProvince(openUserInfo.getProvince());
						user.setCity(openUserInfo.getCity());
						if (StringUtils.isNullBlank(role)) {
							user.setRole("1");
						}
						else {
							user.setRole(role);
						}

						try {
							// 用户入库
							thirdVO = thirdService.save(third, user);
							// 保存微信接口返回用户信息到数据库
							wxUserInfoService.updateOpenUserInfo(openUserInfo, thirdVO.getUserId());

						}
						catch (Exception e) {
							log.error("第三方用户入库出错！");
							e.printStackTrace();
						}
					}
					else {// 用户已入库情况,返回用户id
						Integer userId = wxUserInfo.getUserId();
						if (StringUtils.isNullBlank(wxUserInfo.getWebOpenId())) {
							wxUserInfoService.updateOpenUserInfo(openUserInfo, userId);
						}

						thirdVO = new ThirdVO();
						thirdVO.setUserId(userId);
						thirdVO.setIsUpdated(true);
					}
				}

			}
			else {
				log.error("开放平台Oauth2.0授权第二步accessToken出错！");
			}
		}
		else {
			log.error("开放平台Oauth2.0授权返回code为空！");
		}
		return thirdVO;
	}

	/**
	 * 微信公众平台登录
	 */
	@Override
	public MpThirdVO mpLogin(HttpServletRequest req, Integer scope, String role) {

		String code = req.getParameter("code");

		ThirdVO thirdVO = null;

		MpThirdVO mpThirdVO = null;

		if (!StringUtils.isNullBlank(code)) {

			AccessToken accessToken = wxOAuthService.getAccessToke(code);

			if (accessToken != null) {

				String openId = accessToken.getOpenid();

				// 组装第三方授权信息表
				Third third = new Third();
				// third.setThirdId(openId);
				third.setAccessToken(accessToken.getAccess_token());
				third.setSource(Globals.USERSOURCE_WEIXIN);

				// 组装用户表信息
				User user = new User();
				if (StringUtils.isNullBlank(role)) {
					user.setRole("1");
				}
				else {
					user.setRole(role);
				}

				if (scope == 1) {

					MpUserInfo mpUserInfo = wxAPIUserService.mpGetUserInfo(openId);

					if (mpUserInfo != null) {

						if (mpUserInfo.getUnionid() != null) {
							String unionId = mpUserInfo.getUnionid();
//							log.info("************unionId***********=" + unionId);
							if (!StringUtils.isNullBlank(unionId)) {
								WxUserInfo wxUserInfo = wxUserInfoDAO.findByUnionId(unionId);
								if (wxUserInfo == null) {// 用户还未入库情况
									try {
										// 用户入库
										third.setThirdId(unionId);
										user.setNickName(mpUserInfo.getNickname() != null ? mpUserInfo.getNickname() : null);
										user.setHeadimgUrl(mpUserInfo.getHeadimgurl() != null ? mpUserInfo.getHeadimgurl() : null);
										user.setProvince(mpUserInfo.getProvince() != null ? mpUserInfo.getProvince() : null);
										user.setCity(mpUserInfo.getCity() != null ? mpUserInfo.getCity() : null);
										thirdVO = thirdService.save(third, user);
										// 保存微信接口返回用户信息到数据库
										WxUserInfo retWxuserInfo = wxUserInfoService.updateMpUserInfo(mpUserInfo, thirdVO.getUserId());
										mpThirdVO = new MpThirdVO();
										mpThirdVO.setUserId(retWxuserInfo.getUserId());
										mpThirdVO.setNickName(retWxuserInfo.getNickname());
										mpThirdVO.setHeadimgurl(retWxuserInfo.getHeadimgurl());
										mpThirdVO.setOpenId(retWxuserInfo.getMpOpenId());
										mpThirdVO.setSubscribe(retWxuserInfo.getSubscribe());
										mpThirdVO.setCity(retWxuserInfo.getCity());
										mpThirdVO.setProvince(retWxuserInfo.getProvince());
										mpThirdVO.setSex(retWxuserInfo.getSex());
										mpThirdVO.setUnionid(mpUserInfo.getUnionid());
										return mpThirdVO;
									}
									catch (Exception e) {
										log.error("第三方用户入库出错！");
										e.printStackTrace();
									}
								}
								else {// 用户已入库情况,返回用户id
									mpThirdVO = new MpThirdVO();
									if(wxUserInfo.getMpOpenId() == null){//不存在mpOpenId情况下
										mpThirdVO.setOpenId(openId);
										//更新mpOpenId字段
										wxUserInfoDAO.upByStr(wxUserInfo.getUserId(), " mpOpenId = '"+openId+"'");
										
									}else{
										mpThirdVO.setOpenId(wxUserInfo.getMpOpenId());
									}
									mpThirdVO.setUserId(wxUserInfo.getUserId());
									mpThirdVO.setNickName(wxUserInfo.getNickname());
									mpThirdVO.setHeadimgurl(wxUserInfo.getHeadimgurl());
									mpThirdVO.setSubscribe(wxUserInfo.getSubscribe());
									mpThirdVO.setCity(wxUserInfo.getCity());
									mpThirdVO.setProvince(wxUserInfo.getProvince());
									mpThirdVO.setSex(wxUserInfo.getSex());
									mpThirdVO.setUnionid(wxUserInfo.getUnionid());
									return mpThirdVO;
								
								}
							}
						}
						else {
							WxUserInfo wxUserInfo = wxUserInfoDAO.findBykey("mpOpenId", openId);
							if (wxUserInfo != null) {
								mpThirdVO = new MpThirdVO();
								mpThirdVO.setUserId(wxUserInfo.getUserId());
								mpThirdVO.setNickName(mpUserInfo.getNickname());
								mpThirdVO.setHeadimgurl(mpUserInfo.getHeadimgurl());
								mpThirdVO.setOpenId(openId);
								mpThirdVO.setSubscribe(mpUserInfo.getSubscribe());
								mpThirdVO.setCity(mpUserInfo.getCity());
								mpThirdVO.setProvince(mpUserInfo.getProvince());
								mpThirdVO.setSex(mpUserInfo.getSex());
								mpThirdVO.setUnionid(mpUserInfo.getUnionid());
								return mpThirdVO;
							}
							else {
								user.setNickName(null);
								try {
									third.setThirdId(openId);
									thirdVO = thirdService.save(third, user);
									mpThirdVO = new MpThirdVO();
									mpThirdVO.setUserId(thirdVO.getUserId());
									mpThirdVO.setNickName(mpUserInfo.getNickname());
									mpThirdVO.setHeadimgurl(mpUserInfo.getHeadimgurl());
									mpThirdVO.setOpenId(openId);
									mpThirdVO.setSubscribe(mpUserInfo.getSubscribe());
									mpThirdVO.setCity(mpUserInfo.getCity());
									mpThirdVO.setProvince(mpUserInfo.getProvince());
									mpThirdVO.setSex(mpUserInfo.getSex());
									mpThirdVO.setUnionid(mpUserInfo.getUnionid());
									return mpThirdVO;
								}
								catch (Exception e) {
									log.error("第三方用户入库出错！");
									e.printStackTrace();
								}
							}

						}
					}
					else {
						log.error("微信公众平台获取用户信息失败！");
					}

				}

				if (scope == 2) {
					// 先查找数据库，是否已入过库按openid查找

//					log.info("***********openId**************=" + openId);

					WxUserInfo wxUserInfo = wxUserInfoDAO.findBykey("mpOpenId", openId);

					if (wxUserInfo != null) {// 已入库

						if (wxUserInfo.getUnionid() != null) {
							// 大于一周更新 604800000
							if ((wxUserInfo.getUpdateTime() != null && new Date().getTime() - wxUserInfo.getUpdateTime().getTime() > 60000) || wxUserInfo.getNickname() == null) {
								MpOAuthTUser oauthUser = wxOAuthService.getUserInfo(accessToken);
								WxUserInfo retwxUserInfo = wxUserInfoService.updateMpOAuthTUser(oauthUser, wxUserInfo.getUserId());
								mpThirdVO = new MpThirdVO();
								mpThirdVO.setUserId(retwxUserInfo.getUserId());
								mpThirdVO.setNickName(retwxUserInfo.getNickname());
								mpThirdVO.setHeadimgurl(retwxUserInfo.getHeadimgurl());
								mpThirdVO.setOpenId(retwxUserInfo.getMpOpenId());
								mpThirdVO.setSubscribe(retwxUserInfo.getSubscribe());
								mpThirdVO.setCity(retwxUserInfo.getCity());
								mpThirdVO.setProvince(retwxUserInfo.getProvince());
								mpThirdVO.setSex(retwxUserInfo.getSex());
								mpThirdVO.setUnionid(oauthUser.getUnionid());
								return mpThirdVO;
							}
						}
						else {
							MpOAuthTUser oauthUser = wxOAuthService.getUserInfo(accessToken);
							WxUserInfo retwxUserInfo = wxUserInfoService.updateMpOAuthTUser(oauthUser, wxUserInfo.getUserId());
							mpThirdVO = new MpThirdVO();
							mpThirdVO.setUserId(retwxUserInfo.getUserId());
							mpThirdVO.setNickName(retwxUserInfo.getNickname());
							mpThirdVO.setHeadimgurl(retwxUserInfo.getHeadimgurl());
							mpThirdVO.setOpenId(retwxUserInfo.getMpOpenId());
							mpThirdVO.setSubscribe(retwxUserInfo.getSubscribe());
							mpThirdVO.setCity(retwxUserInfo.getCity());
							mpThirdVO.setProvince(retwxUserInfo.getProvince());
							mpThirdVO.setSex(retwxUserInfo.getSex());
							mpThirdVO.setUnionid(oauthUser.getUnionid());
							return mpThirdVO;
						}

						// 实行更新机制
						// 大于一周更新
						// if((wxUserInfo.getUpdateTime() != null && new
						// Date().getTime()-wxUserInfo.getUpdateTime().getTime()
						// > 604800000)
						// || wxUserInfo.getNickname() == null){
						// MpOAuthTUser oauthUser =
						// wxOAuthService.getUserInfo(accessToken);
						// WxUserInfo retwxUserInfo =
						// wxUserInfoService.updateMpOAuthTUser(oauthUser,
						// wxUserInfo.getUserId());
						// mpThirdVO = new MpThirdVO();
						// mpThirdVO.setUserId(retwxUserInfo.getUserId());
						// mpThirdVO.setNickName(retwxUserInfo.getNickname());
						// mpThirdVO.setOpenId(retwxUserInfo.getMpOpenId());
						// mpThirdVO.setSubscribe(retwxUserInfo.getSubscribe());
						// return mpThirdVO;
						// }else{
						// mpThirdVO = new MpThirdVO();
						// mpThirdVO.setUserId(wxUserInfo.getUserId());
						// mpThirdVO.setNickName(wxUserInfo.getNickname());
						// mpThirdVO.setOpenId(wxUserInfo.getMpOpenId());
						// mpThirdVO.setSubscribe(wxUserInfo.getSubscribe());
						// return mpThirdVO;
						// }

					}
					else { // 未入库

						MpOAuthTUser oauthUser = wxOAuthService.getUserInfo(accessToken);

//						log.info("***********oauthUser.getUnionid()**************=" + oauthUser.getUnionid());

						if (oauthUser != null && !StringUtils.isNullBlank(oauthUser.getUnionid())) {
							// 查找是否存在
							WxUserInfo retWxUserInfo = wxUserInfoDAO.findByUnionId(oauthUser.getUnionid());

//							log.info("***********retWxUserInfo.getUserId()**************=" + retWxUserInfo.getUserId());

							if (retWxUserInfo == null) {

								try {
									user.setNickName(oauthUser.getNickname());
									user.setHeadimgUrl(oauthUser.getHeadimgurl());
									user.setProvince(oauthUser.getProvince());
									user.setCity(oauthUser.getCity());
									third.setThirdId(oauthUser.getUnionid());
									thirdVO = thirdService.save(third, user);
									WxUserInfo retwxUserInfo = wxUserInfoService.updateMpOAuthTUser(oauthUser, thirdVO.getUserId());
									mpThirdVO = new MpThirdVO();
									mpThirdVO.setUserId(retwxUserInfo.getUserId());
									mpThirdVO.setNickName(retwxUserInfo.getNickname());
									mpThirdVO.setHeadimgurl(retwxUserInfo.getHeadimgurl());
									mpThirdVO.setOpenId(openId);
									mpThirdVO.setSubscribe(retwxUserInfo.getSubscribe());
									mpThirdVO.setCity(retwxUserInfo.getCity());
									mpThirdVO.setProvince(retwxUserInfo.getProvince());
									mpThirdVO.setSex(retwxUserInfo.getSex());
									mpThirdVO.setUnionid(oauthUser.getUnionid());
									return mpThirdVO;

								}
								catch (Exception e) {
									log.error("第三方用户入库出错！");
									e.printStackTrace();
								}

							}
							else {

								WxUserInfo retwxUserInfo = wxUserInfoService.updateMpOAuthTUser(oauthUser, retWxUserInfo.getUserId());
								mpThirdVO = new MpThirdVO();
								mpThirdVO.setUserId(retwxUserInfo.getUserId());
								mpThirdVO.setNickName(retwxUserInfo.getNickname());
								mpThirdVO.setHeadimgurl(retwxUserInfo.getHeadimgurl());
								mpThirdVO.setOpenId(openId);
								mpThirdVO.setSubscribe(retwxUserInfo.getSubscribe());
								mpThirdVO.setCity(retwxUserInfo.getCity());
								mpThirdVO.setProvince(retwxUserInfo.getProvince());
								mpThirdVO.setSex(retwxUserInfo.getSex());
								mpThirdVO.setUnionid(oauthUser.getUnionid());
								return mpThirdVO;

							}
						}

					}

				}

			}
			else {
				log.error("公众平台Oauth2.0授权第二步accessToken出错！");
			}
		}
		else {
			log.error("公众平台Oauth2.0授权授权认证 返回code为空！");
		}
		return mpThirdVO;
	}

	/**
	 * 微信公众平台网页授权获取用户信息
	 */
	@Override
	public MpThirdVO mpOauthGetUser(HttpServletRequest req, Integer scope) {

		String code = req.getParameter("code");

		MpThirdVO mpThirdVO = null;

		if (!StringUtils.isNullBlank(code)) {

			AccessToken accessToken = wxOAuthService.getAccessToke(code);

			if (accessToken != null) {

				String openId = accessToken.getOpenid();

				if (scope == 1) {
					MpUserInfo mpUserInfo = wxAPIUserService.mpGetUserInfo(openId);
					if (mpUserInfo != null) {
						if (mpUserInfo.getSubscribe() == 1) {
							mpThirdVO = new MpThirdVO();
							mpThirdVO.setNickName(mpUserInfo.getNickname());
							mpThirdVO.setOpenId(mpUserInfo.getOpenid());
							mpThirdVO.setSubscribe(mpUserInfo.getSubscribe());
							mpThirdVO.setHeadimgurl(mpUserInfo.getHeadimgurl());
							mpThirdVO.setCity(mpUserInfo.getCity());
							mpThirdVO.setProvince(mpUserInfo.getProvince());
							mpThirdVO.setSex(mpUserInfo.getSex());
							return mpThirdVO;
						}
						else {
							mpThirdVO = new MpThirdVO();
							mpThirdVO.setOpenId(mpUserInfo.getOpenid());
							mpThirdVO.setSubscribe(mpUserInfo.getSubscribe());
							mpThirdVO.setCity(mpUserInfo.getCity());
							mpThirdVO.setProvince(mpUserInfo.getProvince());
							mpThirdVO.setSex(mpUserInfo.getSex());
							return mpThirdVO;
						}
					}
					else {
						log.error("微信公众平台获取用户基本信息失败！");
					}
				}

				if (scope == 2) {
					MpOAuthTUser oauthUser = wxOAuthService.getUserInfo(accessToken);
					if (oauthUser != null) {
						mpThirdVO = new MpThirdVO();
						mpThirdVO.setNickName(oauthUser.getNickname());
						mpThirdVO.setOpenId(oauthUser.getOpenid());
						mpThirdVO.setHeadimgurl(oauthUser.getHeadimgurl());
						mpThirdVO.setCity(oauthUser.getCity());
						mpThirdVO.setProvince(oauthUser.getProvince());
						mpThirdVO.setSex(oauthUser.getSex());
						return mpThirdVO;
					}
					else {
						log.error("微信公众平台outh网页授权第四步获取用户信息失败！");
					}
				}
			}
			else {
				log.error("公众平台Oauth2.0授权第二步accessToken出错！");
			}
		}
		else {
			log.error("公众平台Oauth2.0授权授权认证 返回code为空！");
		}
		return mpThirdVO;
	}

	@Override
	public MpThirdVO mpOauthGetUserCached(AccessToken accessToken, Integer scope, int expired) throws Exception {

		MpThirdVO mpThirdVO = null;

		String memKey = MemcachedConstans.WX_MP_USER_KEY + accessToken.getOpenid();

		if (expired == 0) {
			expired = MemcachedConstans.WX_MP_USER_EXPIRED;
		}

		if (memcachedClient != null) {
			if (memcachedClient.get(memKey) != null) {
				mpThirdVO = (MpThirdVO) memcachedClient.get(memKey);
			}
			else {
				if (scope == 1) {
					MpUserInfo mpUserInfo = wxAPIUserService.mpGetUserInfo(accessToken.getOpenid());
					if (mpUserInfo != null) {
						if (mpUserInfo.getSubscribe() == 1) {
							mpThirdVO = new MpThirdVO();
							mpThirdVO.setNickName(mpUserInfo.getNickname());
							mpThirdVO.setOpenId(mpUserInfo.getOpenid());
							mpThirdVO.setSubscribe(mpUserInfo.getSubscribe());
							mpThirdVO.setHeadimgurl(mpUserInfo.getHeadimgurl());
							mpThirdVO.setCity(mpUserInfo.getCity());
							mpThirdVO.setProvince(mpUserInfo.getProvince());
							mpThirdVO.setSex(mpUserInfo.getSex());
							memcachedClient.set(memKey, expired, mpThirdVO);
							return mpThirdVO;
						}
					}
					else {
						log.error("微信公众平台获取用户基本信息失败！");
					}
				}
				if (scope == 2) {
					MpOAuthTUser oauthUser = wxOAuthService.getUserInfo(accessToken);
					if (oauthUser != null) {
						mpThirdVO = new MpThirdVO();
						mpThirdVO.setNickName(oauthUser.getNickname());
						mpThirdVO.setOpenId(oauthUser.getOpenid());
						mpThirdVO.setHeadimgurl(oauthUser.getHeadimgurl());
						mpThirdVO.setCity(oauthUser.getCity());
						mpThirdVO.setProvince(oauthUser.getProvince());
						mpThirdVO.setSex(oauthUser.getSex());
						return mpThirdVO;
					}
					else {
						log.error("微信公众平台outh网页授权第四步获取用户信息失败！");
					}
				}
			}
		}
		else {// 缓存服务器出错
			if (scope == 1) {
				MpUserInfo mpUserInfo = wxAPIUserService.mpGetUserInfo(accessToken.getOpenid());
				if (mpUserInfo != null) {
					if (mpUserInfo.getSubscribe() == 1) {
						mpThirdVO = new MpThirdVO();
						mpThirdVO.setNickName(mpUserInfo.getNickname());
						mpThirdVO.setOpenId(mpUserInfo.getOpenid());
						mpThirdVO.setSubscribe(mpUserInfo.getSubscribe());
						mpThirdVO.setHeadimgurl(mpUserInfo.getHeadimgurl());
						mpThirdVO.setCity(mpUserInfo.getCity());
						mpThirdVO.setProvince(mpUserInfo.getProvince());
						mpThirdVO.setSex(mpUserInfo.getSex());
						return mpThirdVO;
					}
				}
				else {
					log.error("微信公众平台获取用户基本信息失败！");
				}
			}
			if (scope == 2) {
				MpOAuthTUser oauthUser = wxOAuthService.getUserInfo(accessToken);
				if (oauthUser != null) {
					mpThirdVO = new MpThirdVO();
					mpThirdVO.setNickName(oauthUser.getNickname());
					mpThirdVO.setOpenId(oauthUser.getOpenid());
					mpThirdVO.setHeadimgurl(oauthUser.getHeadimgurl());
					mpThirdVO.setCity(oauthUser.getCity());
					mpThirdVO.setProvince(oauthUser.getProvince());
					mpThirdVO.setSex(oauthUser.getSex());
					return mpThirdVO;
				}
				else {
					log.error("微信公众平台outh网页授权第四步获取用户信息失败！");
				}

			}
		}

		return mpThirdVO;
	}

}
