package com.yoxi.hudongtui.service.user.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.paoding.rose.jade.core.Identity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.dao.ThirdDAO;
import com.yoxi.hudongtui.dao.user.UserDAO;
import com.yoxi.hudongtui.dao.wx.WxUserInfoDAO;
import com.yoxi.hudongtui.model.user.Third;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.model.wx.WxUserInfo;
import com.yoxi.hudongtui.service.user.IThirdService;
import com.yoxi.hudongtui.service.wx.IWxAPIUserService;
import com.yoxi.hudongtui.service.wx.IWxOAuthService;
import com.yoxi.hudongtui.service.wx.IWxUserInfoService;
import com.yoxi.hudongtui.utils.common.EmojiFilter;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.vo.user.ThirdVO;
import com.yoxi.hudongtui.vo.user.UserExsitVO;
import com.yoxi.hudongtui.vo.wx.BindWxdVO;
import com.yoxi.hudongtui.vo.wx.api.oauth.AccessToken;
import com.yoxi.hudongtui.vo.wx.api.user.OpenUserInfo;

@Service
public class ThirdServiceImpl implements IThirdService {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ThirdDAO thirdDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private WxUserInfoDAO wxUserInfoDAO;
	@Autowired
	private IWxUserInfoService wxUserInfoService;
	@Autowired
	private IWxOAuthService wxOAuthService;
	@Autowired
	private IWxAPIUserService wxAPIUserService;
	
	@Override
	@Transactional
	public ThirdVO save(Third third,User user)throws Exception{
		   
		   Third thirdTemp = thirdDAO.findByThirdIdSource(third.getThirdId(),third.getSource());
		  
		   ThirdVO thirdVO = new ThirdVO();
		   
		   if(thirdTemp == null){
			  UserExsitVO userExsitVO = userDAO.findExistByAccount(third.getThirdId());
			  Integer userId = null;
			  if(userExsitVO == null){
				  //t_user表新建用户
				  User userNew = new User();
				  userNew.setAccount(third.getThirdId());
				  userNew.setCreateTime(new Date());
				  userNew.setPluginNum(0);
				  userNew.setFriendNum(0);
				  userNew.setRole(user.getRole());
				  userNew.setRepreCoin(0d);
				  userNew.setSource(third.getSource());
				  userNew.setNickName(EmojiFilter.filterEmoji(user.getNickName()));
				  userNew.setHeadimgUrl(user.getHeadimgUrl());
				  userNew.setProvince(user.getProvince());
				  userNew.setCity(user.getCity());
				  userId = userDAO.add(userNew).intValue();
			  }else{
				  userId = userExsitVO.getUserId();
			  }
			  
			  //userId产生后，记录第三该用户授权信息表
			  if(userId != null){
				  thirdTemp = new Third(userId, third.getThirdId(),third.getSource(),third.getAccessToken(),new Date());
				  thirdDAO.add(thirdTemp);
			  }
			 
			  thirdVO.setUserId(userId);
			  thirdVO.setIsUpdated(false);
			  return thirdVO;
		   }else{
			   Boolean isUpdated = isUpdateUser(thirdTemp.getUserId());
			   thirdVO.setUserId(thirdTemp.getUserId());
			   thirdVO.setIsUpdated(isUpdated);
			   return thirdVO;
		   }
	}
	


	@Override
	public ThirdVO bind(Third third, User user) throws Exception {
		
		   Third thirdTemp = thirdDAO.findByThirdIdSource(third.getThirdId(),third.getSource());
			  
		   ThirdVO thirdVO = new ThirdVO();
		   
		   if(thirdTemp == null){
			  if(user.getUserId() != null){
				  thirdTemp = new Third(user.getUserId(), third.getThirdId(),third.getSource(),third.getAccessToken(),new Date());
				  thirdDAO.add(thirdTemp);
			  }
			  thirdVO.setUserId(user.getUserId());
			  thirdVO.setIsUpdated(false);
			  return thirdVO;
		   }else{
			   Boolean isUpdated = isUpdateUser(thirdTemp.getUserId());
			   thirdVO.setUserId(thirdTemp.getUserId());
			   thirdVO.setIsUpdated(isUpdated);
			   return thirdVO;
		   }
	}

	/**
	 * 判断用户信息是否已更新过
	 * 
	 * @param userId
	 * @param isUpdated
	 * @return
	 */
	private Boolean isUpdateUser(Integer userId) {
		Boolean isUpdated = false;
		Date updateTime = userDAO.findUpdateTimeByUserId(userId);
		if (updateTime != null) {
			isUpdated = true;
		}
		return isUpdated;
	}

	
	@Transactional
	@Override
	public BindWxdVO bindwx(HttpServletRequest req, Integer userId) throws Exception {
		
		BindWxdVO bindWxdVO = null;
		String code = req.getParameter("code");
		if (!StringUtils.isNullBlank(code)){
			AccessToken accessToken = wxOAuthService.getOpenAccessToke(code);
			if (accessToken != null) {
				String openId = accessToken.getOpenid();
				OpenUserInfo openUserInfo = wxAPIUserService.webGetUserInfo(accessToken.getAccess_token(), openId);
				String unionId = openUserInfo.getUnionid();
				if (!StringUtils.isNullBlank(unionId)) {
					WxUserInfo wxUserInfo = wxUserInfoDAO.findByUnionId(unionId);
					Third dbThird = thirdDAO.findByThirdIdSource(unionId, null);
					if(wxUserInfo != null && dbThird !=null){
						bindWxdVO = new BindWxdVO();
						bindWxdVO.setBinded(true); //该微信账号已经绑定过用户
					}else if(dbThird == null && wxUserInfo == null) {
							Third third = new Third();
							third.setThirdId(unionId);
							third.setAccessToken(accessToken.getAccess_token());
							third.setSource(Globals.USERSOURCE_WEIXIN);
							third.setUserId(userId);
							third.setCreateTime(new Date());
							third.setUpdateTime(new Date());
							Identity id = thirdDAO.add(third);
							if(id != null){
								wxUserInfoService.updateOpenUserInfo(openUserInfo,userId);
							}
							//如user表中的呢称与头像为空，则更新
							User user = userDAO.findByUserId(userId);
							if(user != null){
								String upstr = "";
								if(user.getNickName() == null && user.getHeadimgUrl() == null){
									upstr = ", nickName = '"+openUserInfo.getNickname() + "', headimgUrl = '"+openUserInfo.getHeadimgurl()+"'";
								}
								if(user.getNickName() == null){
									upstr = ", nickName = '"+openUserInfo.getNickname()+"'";
								}
								if(user.getHeadimgUrl() == null){
									upstr = ", headimgUrl = '"+openUserInfo.getHeadimgurl()+"'";
								}
								userDAO.upByStr(userId, upstr);
							}
							bindWxdVO = new BindWxdVO();
							bindWxdVO.setUnionId(unionId);
							bindWxdVO.setUserId(userId);
							bindWxdVO.setThirdDbId(id.intValue());
							bindWxdVO.setBinded(false);
//							wxUserInfoService.updateOpenUserInfo(openUserInfo,userId);
							return bindWxdVO;
					}
				}
				
			}else {
				log.error("开放平台Oauth2.0授权第二步accessToken出错！");
			}
			
		}else {
			log.error("开放平台Oauth2.0授权返回code为空！");
		}
		return bindWxdVO;
	}
}
