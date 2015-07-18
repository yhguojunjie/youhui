package com.yoxi.hudongtui.utils.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoxi.hudongtui.constants.wx.MpConstants;


import net.sf.json.JSONObject;

/**
 * 
	 *        
	 * 名称：MessageReplyUtils    
	 *
	 * 作用：
	 *    
	 * 作者：yejp 2014-1-15     
	 *
 */
public class MessageReplyUtils {

	private static Logger log = LoggerFactory.getLogger(MessageReplyUtils.class);

	/**
	 * 发送客服信息（已废弃）
	 * @param body
	 */
	public static void postMessageCustomSend(String body){
		String url = MpConstants.getMessageCustom();
		String access_token = WeixinUtils.getAccessToken(MpConstants.getAppid(),MpConstants.getAppsecret()).getAccessToken();
		url = url.replace("ACCESS_TOKEN", access_token);
		
		log.info("***************发送客服信息的url************="+url);
		log.info("***************body************="+body);
		JSONObject resultObject = WeixinUtils.httpsRequest(url, "POST", body);
		
		// 如果请求成功 
		if (null != resultObject) { 
			String errcode = resultObject.getString("errcode");
			String errmsg = resultObject.getString("errmsg");
			if (("0").equals(errcode)) {
				log.info("************发送客服信息成功************errcode="+errcode+", errmsg="+errmsg);
			}else{
				log.info("************发送客服信息失败************errcode="+errcode+", errmsg="+errmsg);
			}
		}else{
			log.info("************发送客服信息失败************resultObject为空");
		}
		
	}
	
}
