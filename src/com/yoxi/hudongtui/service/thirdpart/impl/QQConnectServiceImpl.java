package com.yoxi.hudongtui.service.thirdpart.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.model.user.Third;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.thirdpart.IQQConnectService;
import com.yoxi.hudongtui.service.user.IThirdService;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.vo.user.ThirdVO;

@Service
public class QQConnectServiceImpl implements IQQConnectService {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private IThirdService thirdService;
	
	
	@Override
	public ThirdVO login(HttpServletRequest request,String role){
		
		ThirdVO thirdVO = null;
		try {
			//请求accessToken
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
		    String accessToken = null;
	        long tokenExpireIn = 0L;
	        if (accessTokenObj.getAccessToken().equals("")) {
	            log.info("***********没有获取到响应参数*****************");
	        }else{
	              accessToken = accessTokenObj.getAccessToken();
	              tokenExpireIn = accessTokenObj.getExpireIn();
	              log.info("***********accessToken********************="+accessToken);
	              log.info("***********tokenExpireIn******************="+tokenExpireIn);
	              //获取openId
	              OpenID openIDObj =  new OpenID(accessToken);
	              //使用opendId与accessToken取用户信息
	              UserInfo qzoneUserInfo = new UserInfo(accessToken, openIDObj.getUserOpenID());
	              UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
	              //组装第三方授权信息表
	              Third third = new Third();
	              third.setThirdId(openIDObj.getUserOpenID());
	              third.setAccessToken(accessToken);
	              third.setSource(Globals.USERSOURCE_QQ);
	              //组装用户表信息
	              User user = new User();
//	              log.info("***********userInfoBean.getNickname()******************="+userInfoBean.getNickname());
//	              log.info("***********userInfoBean.getGender()******************="+userInfoBean.getGender());
//	              log.info("***********userInfoBean.getLevel()******************="+userInfoBean.getLevel());
//	              log.info("***********userInfoBean.getAvatar()getAvatarURL50******************="+userInfoBean.getAvatar().getAvatarURL50());
	              user.setNickName(userInfoBean.getNickname());
	              user.setHeadimgUrl(userInfoBean.getAvatar().getAvatarURL50());
	              if(StringUtils.isNullBlank(role)){
	            	  user.setRole("1");
	              }else{
	            	  user.setRole(role);
	              }
	              try {
	            	  thirdVO = thirdService.save(third, user);
				  } catch (Exception e) {
					log.error("第三方用户入库出错！");
					e.printStackTrace();
					return thirdVO;
				  }
	          }
		} catch (QQConnectException e) {
			log.error("QQ登录Oauth获取AccessToken出错!");
			e.printStackTrace();
			return thirdVO;
		}
        
		return thirdVO;
	}

}
