package com.yoxi.hudongtui.service.wx.impl;

import java.io.InputStream;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.constants.wx.MpConstants;
import com.yoxi.hudongtui.service.wx.IWxConfigService;
import com.yoxi.hudongtui.service.wx.IWxQrcodeService;
import com.yoxi.hudongtui.utils.wx.WeixinUtils;
import com.yoxi.hudongtui.vo.wx.api.QrcodeTicket;

@Service
public class WxQrcodeServiceImpl implements IWxQrcodeService {

	private static Logger log = LoggerFactory.getLogger(WxAPIUserServiceImpl.class);
	
	@Autowired
	private IWxConfigService wxConfigService;
	
	@Override
	public InputStream getLimitQrcode(String scene_str) {
		
		QrcodeTicket qrcodeTicket = getQRLimitScene(scene_str);
//		QrcodeTicket qrcodeTicket = getQRScene(scene_str);
		
		return  showQrcode(qrcodeTicket.getTicket());
		
	}
	
	/**
	 * 创建二维码ticket
	 * @param scene_str
	 * @return
	 */
	public QrcodeTicket getQRLimitScene(String scene_str) {

		String apiUrl = MpConstants.getQrLimitScene().replace("PRAMTOKEN",
				wxConfigService.getAccessToken(MpConstants.getAppid(),MpConstants.getAppsecret()).getAccessToken());
		
		String postData = "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\":{\"scene_str\":\""+scene_str+"\"}}}";
	
		String postData1 = "{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":"+scene_str+"}}}";
		
		log.info("**********postData*********="+postData1);
		
		JSONObject jsonObject = WeixinUtils.httpsRequest(apiUrl, "POST", postData);
		
		QrcodeTicket qrcodeTicket = null;
		
		 if (null != jsonObject){ 
				if (jsonObject.has("errcode") && 0 != jsonObject.getInt("errcode")) {
					 log.error("微信接口-永久二维码请求失败：errcode="+jsonObject.getInt("errcode")+",errmsg="+jsonObject.getString("errmsg")); 
					 //accessToken出错情况下，重新刷新
					 if(jsonObject.getInt("errcode") == 40001 && "invalid credential".equals(jsonObject.getString("errmsg"))){
						  wxConfigService.updateDBStorAccessToken(MpConstants.getAppid(),MpConstants.getAppsecret());
					 }
				}else{
					qrcodeTicket = new QrcodeTicket();
					if(jsonObject.has("ticket")){
						qrcodeTicket.setTicket(jsonObject.getString("ticket"));
					}
					if(jsonObject.has("expire_seconds")){
						qrcodeTicket.setExpire_seconds(jsonObject.getInt("expire_seconds"));
					}
					if(jsonObject.has("url")){
						qrcodeTicket.setUrl(jsonObject.getString("url"));
					}
					
				}
		 }
		 return qrcodeTicket;
	}
	
	
	/**
	 * 
	 * @param scene_str
	 * @return
	 */
	public QrcodeTicket getQRScene(String scene_str) {

		String apiUrl = MpConstants.getQrScene().replace("PRAMTOKEN",
				wxConfigService.getAccessToken(MpConstants.getAppid(),MpConstants.getAppsecret()).getAccessToken());
		
		String postData = "{\"expire_seconds\":1800,\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":"+scene_str+"}}}";
	
		
		log.info("**********postData*********="+postData);
		
		JSONObject jsonObject = WeixinUtils.httpsRequest(apiUrl, "POST", postData);
		
		QrcodeTicket qrcodeTicket = null;
		
		 if (null != jsonObject){ 
				if (jsonObject.has("errcode") && 0 != jsonObject.getInt("errcode")) {
					 log.error("微信接口-永久二维码请求失败：errcode="+jsonObject.getInt("errcode")+",errmsg="+jsonObject.getString("errmsg")); 
					 //accessToken出错情况下，重新刷新
					 if(jsonObject.getInt("errcode") == 40001 && "invalid credential".equals(jsonObject.getString("errmsg"))){
						  wxConfigService.updateDBStorAccessToken(MpConstants.getAppid(),MpConstants.getAppsecret());
					 }
				}else{
					qrcodeTicket = new QrcodeTicket();
					if(jsonObject.has("ticket")){
						qrcodeTicket.setTicket(jsonObject.getString("ticket"));
					}
					if(jsonObject.has("expire_seconds")){
						qrcodeTicket.setExpire_seconds(jsonObject.getInt("expire_seconds"));
					}
					if(jsonObject.has("url")){
						qrcodeTicket.setUrl(jsonObject.getString("url"));
					}
					
				}
		 }
		 return qrcodeTicket;
	}
	

	/**
	 * 通过ticket换取二维码
	 * @param ticket
	 * @return
	 */
	public InputStream showQrcode(String ticket){
		
		String apiUrl = MpConstants.getShowQrcode().replace("PARMTICKET",ticket);
		
		InputStream inputStream = WeixinUtils.httpsRequestStream(apiUrl, "GET", null);
		
		return inputStream;
	}


	
}
