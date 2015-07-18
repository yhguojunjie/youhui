package com.yoxi.hudongtui.service.wx.impl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yoxi.hudongtui.service.wx.IWxConfigService;
import com.yoxi.hudongtui.service.wx.IWxJsAPIService;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.wx.JsApiUtils;
import com.yoxi.hudongtui.vo.wx.api.AccessToken;
import com.yoxi.hudongtui.vo.wx.api.JsApiTicket;

@Service
public class wxJsAPIServiceImpl implements IWxJsAPIService {

	@Autowired
	private IWxConfigService wxConfigService;
	
	@Override
	public Map<String, String> getShareSignPackage(String url, String shareUrl) throws Exception{
		String jsapi_ticket = "";
		String appId = ReadProperties.getPara("wx_mp_appid");
		String appsecret = ReadProperties.getPara("wx_mp_appsecret");
		AccessToken accessToken = wxConfigService.getAccessToken(appId, appsecret);
		if(accessToken != null){
			JsApiTicket jsApiTicket = wxConfigService.getJsApiTicket(accessToken.getAccessToken(), appId);
			if(jsApiTicket != null){
				jsapi_ticket = jsApiTicket.getTicket();
			}
		}
		Map<String, String> signPackage = JsApiUtils.getSignPackage(jsapi_ticket, url);
		signPackage.put("shareUrl", shareUrl);
		signPackage.put("appId", appId);
		return signPackage;
	}

	
}
