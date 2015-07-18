package com.yoxi.hudongtui.service.wx;

import javax.servlet.http.HttpServletRequest;

/**
 * 消息服务接口
 * 
 * @author wangql
 * 
 * 2013-12-26
 *
 */
public interface IWxMessageService {
	
	/**
	 * 处理微信发来的消息
	 * @param request
	 * @return
	 */
	public String processReq(HttpServletRequest request);
}
