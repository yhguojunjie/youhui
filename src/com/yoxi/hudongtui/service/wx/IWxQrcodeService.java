package com.yoxi.hudongtui.service.wx;

import java.io.InputStream;

/**
 * 
 * 场景二维码
 *
 * @author wql
 *
 * @Date 2015年3月10日
 *
 */
public interface IWxQrcodeService {
	
	/**
	 *  获取永久二维码
	 * @param scene_str
	 */
	public InputStream getLimitQrcode(String scene_str);  
}
