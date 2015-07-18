package com.yoxi.hudongtui.service.wx;

import java.util.Map;

/**
 * 
 * @author wql
 *
 *	2015-01-22
 *
 */
public interface IWxJsAPIService {
	
	/**
	 * 
	 * @param url 访问url
	 * @param shareUrl 分享url
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getShareSignPackage(String url, String shareUrl) throws Exception;
}
