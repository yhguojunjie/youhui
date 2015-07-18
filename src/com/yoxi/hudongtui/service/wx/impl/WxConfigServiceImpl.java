package com.yoxi.hudongtui.service.wx.impl;

import java.util.Date;

import net.paoding.rose.jade.core.Identity;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.SysConfigDAO;
import com.yoxi.hudongtui.model.SysConfig;
import com.yoxi.hudongtui.service.wx.IWxConfigService;
import com.yoxi.hudongtui.utils.wx.WeixinUtils;
import com.yoxi.hudongtui.vo.wx.api.AccessToken;
import com.yoxi.hudongtui.vo.wx.api.JsApiTicket;

@Service
public class WxConfigServiceImpl implements IWxConfigService {

	private static Logger log = LoggerFactory.getLogger(WxConfigServiceImpl.class);
	
	@Autowired
	private SysConfigDAO  sysConfigDAO;
	
	@Override
	public AccessToken getAccessToken(String appId,String appsecret) {
//		SysConfig sysConfig1 = sysConfigDAO.findById(1);
		String tokenStr = " code = 'accesstoken_"+appId+"' ";
		SysConfig sysConfig1 = sysConfigDAO.getByStr(tokenStr);
		if(sysConfig1 == null){
			SysConfig s1 = new SysConfig("accesstoken_"+appId,null,"公众号token",new Date());
			Identity id = sysConfigDAO.add(s1);
			if(id != null){
				s1.setId(id.intValue());
				sysConfig1 = s1;
			}
		}
		String accessTokenStr = null;
	    if(StringUtils.isNotBlank(sysConfig1.getValue())){
	    	accessTokenStr = sysConfig1.getValue() ;
	    }
	    
	   
//	    SysConfig sysConfig2 = sysConfigDAO.findById(2);
	    String tokenTimeStr = " code = 'accesstoken_gettime_"+appId+"' ";
	    SysConfig sysConfig2 = sysConfigDAO.getByStr(tokenTimeStr);
		if(sysConfig2 == null){
			SysConfig s2 = new SysConfig("accessToken_gettime_"+appId,null,"最近一次获取公众号token时间",new Date());
			Identity id = sysConfigDAO.add(s2);
			if(id != null){
				s2.setId(id.intValue());
				sysConfig2 = s2;
			}
		}
	    Long accessTokenGetLastTime = null;
	    if(StringUtils.isNotBlank(sysConfig2.getValue())){
	    	accessTokenGetLastTime = Long.valueOf(sysConfig2.getValue());
	    }
	    AccessToken accessToken = null; 
		//首次获取token
		if(accessTokenStr == null && accessTokenGetLastTime == null){
			accessToken = WeixinUtils.getTokenFromWx(appId,appsecret);
			if(accessToken != null){
				//统计更新accessToken次数
//				updateGetAccssTokenCount(sysConfig2.getValue());
				sysConfigDAO.updateByCriteria(sysConfig1,"value", accessToken.getAccessToken());
				sysConfigDAO.updateByCriteria(sysConfig2,"value", String.valueOf(System.currentTimeMillis()));
			}
		}else{
			//如果距离获取token的时间大于5400(1.5小时),则更新token  1800000
			if(System.currentTimeMillis() - accessTokenGetLastTime > 5400000){
				accessToken = WeixinUtils.getTokenFromWx(appId,appsecret);
				if(accessToken != null){
					//统计更新accessToken次数
//					updateGetAccssTokenCount(sysConfig2.getValue());
					sysConfigDAO.updateByCriteria(sysConfig1,"value", accessToken.getAccessToken());
					sysConfigDAO.updateByCriteria(sysConfig2,"value", String.valueOf(System.currentTimeMillis()));
				}
			}else{
				accessToken = new AccessToken();
				accessToken.setAccessToken(accessTokenStr);
			}
		}
		return accessToken;
	}
	
	/**
	 * 更新数据库存储的accessToken
	 */
	public AccessToken updateDBStorAccessToken(String appId,String appsecret){
		AccessToken accessToken = WeixinUtils.getTokenFromWx(appId,appsecret);
		if(accessToken != null){
		
//			SysConfig sysConfig1 = sysConfigDAO.findById(1);
//			SysConfig sysConfig2 = sysConfigDAO.findById(2);
			String tokenStr = " code = 'accesstoken_"+appId+"' ";
			SysConfig sysConfig1 = sysConfigDAO.getByStr(tokenStr);
			 String tokenTimeStr = " code = 'accesstoken_gettime_"+appId+"' ";
		    SysConfig sysConfig2 = sysConfigDAO.getByStr(tokenTimeStr);
			
			//统计更新accessToken次数
//			updateGetAccssTokenCount(sysConfig2.getValue());
			sysConfig1.setValue(accessToken.getAccessToken());
			sysConfigDAO.updateByCriteria(sysConfig1,"value", accessToken.getAccessToken());
			sysConfigDAO.updateByCriteria(sysConfig2,"value", String.valueOf(System.currentTimeMillis()));
			
			log.error("更新数据库存储的accessToken成功！");
		}
		return accessToken;
	}

	
	/**
	 * 获取jsapi ticekt
	 */
	@Override
	public JsApiTicket getJsApiTicket(String accessToken,String appId) {

//		SysConfig sysConfig1 = sysConfigDAO.findById(4);
		String ticketStr = " code = 'jsticket_"+appId+"' ";
		SysConfig sysConfig1 = sysConfigDAO.getByStr(ticketStr);
		if(sysConfig1 == null){
			SysConfig s1 = new SysConfig("jsticket_"+appId,null,"公众号jsapiticket",new Date());
			Identity id = sysConfigDAO.add(s1);
			if(id != null){
				s1.setId(id.intValue());
				sysConfig1 = s1;
			}
		}
	    String jsapiTicketStr = null;
	    if(StringUtils.isNotBlank(sysConfig1.getValue())){
	    	jsapiTicketStr = sysConfig1.getValue() ;
	    }
	    
//	    SysConfig sysConfig2 = sysConfigDAO.findById(5);
	    String ticketTimeStr = " code = 'jsticket_gettime_"+appId+"' ";
		SysConfig sysConfig2 = sysConfigDAO.getByStr(ticketTimeStr);
		if(sysConfig2 == null){
			SysConfig s2 = new SysConfig("jsticket_gettime_"+appId,null,"最近一次获取jsapiticket时间",new Date());
			Identity id = sysConfigDAO.add(s2);
			if(id != null){
				s2.setId(id.intValue());
				sysConfig2 = s2;
			}
		}
	    Long jsapiTicketGetLastTime = null;
	    if(StringUtils.isNotBlank(sysConfig2.getValue())){
	    	jsapiTicketGetLastTime = Long.valueOf(sysConfig2.getValue());
	    }
	    JsApiTicket jsApiTicket = null; 
		//首次获取token
		if(jsapiTicketStr == null && jsapiTicketGetLastTime == null){
			jsApiTicket = WeixinUtils.getJsApiTicketFromWx(accessToken);
			if(jsApiTicket != null){
				//统计更新accessToken次数
//				updateGetAccssTokenCount(sysConfig2.getValue());
				sysConfigDAO.updateByCriteria(sysConfig1,"value", jsApiTicket.getTicket());
				sysConfigDAO.updateByCriteria(sysConfig2,"value", String.valueOf(System.currentTimeMillis()));
			}
		}else{
			//如果距离获取token的时间大于5400(1.5小时),则更新token  1800000
			if(System.currentTimeMillis() - jsapiTicketGetLastTime > 5400000){
				jsApiTicket = WeixinUtils.getJsApiTicketFromWx(accessToken);
				if(jsApiTicket != null){
					//统计更新accessToken次数
//					updateGetAccssTokenCount(sysConfig2.getValue());
					sysConfigDAO.updateByCriteria(sysConfig1,"value", jsApiTicket.getTicket());
					sysConfigDAO.updateByCriteria(sysConfig2,"value", String.valueOf(System.currentTimeMillis()));
				}
			}else{
				jsApiTicket = new JsApiTicket();
				jsApiTicket.setTicket(jsapiTicketStr);
			}
		}
		return jsApiTicket;
	}

	
	/**
	 * 更新数据库存储的全局jsapiticket
	 */
	@Override
	public JsApiTicket updateDBJsApiTicket(String accessToken,String appId) {
		JsApiTicket jsApiTicket = WeixinUtils.getJsApiTicketFromWx(accessToken);
		if(accessToken != null){
//			SysConfig sysConfig1 = sysConfigDAO.findById(4);
//			SysConfig sysConfig2 = sysConfigDAO.findById(5);
			String ticketStr = " code = 'jsticket_"+appId+"' ";
			SysConfig sysConfig1 = sysConfigDAO.getByStr(ticketStr);
		    String ticketTimeStr = " code = 'jsticket_gettime_"+appId+"' ";
			SysConfig sysConfig2 = sysConfigDAO.getByStr(ticketTimeStr);
			
			sysConfig1.setValue(jsApiTicket.getTicket());
			sysConfigDAO.updateByCriteria(sysConfig1,"value", jsApiTicket.getTicket());
			sysConfigDAO.updateByCriteria(sysConfig2,"value", String.valueOf(System.currentTimeMillis()));
			
			log.error("更新数据库存储的jsapi ticket成功！");
		}
		return jsApiTicket;
	}
	
	
	
	/**
	 * 更新获取accssToken次数
	 */
//	public void updateGetAccssTokenCount(String lastUpdateTime){
//		SysConfig sysConfig3 = sysConfigDAO.findById(3);
//		if(DateUtils.ifDayZeroClock(Long.valueOf(lastUpdateTime))){
//			sysConfigDAO.updateByCriteria(sysConfig3,"value", String.valueOf(1));
//		}else{
//			sysConfigDAO.updateByCriteria(sysConfig3,"value", String.valueOf(Integer.valueOf(sysConfig3.getValue())+1));
//		}
//	}
}
