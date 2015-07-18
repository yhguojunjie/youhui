package com.yoxi.hudongtui.utils.wx;

import java.util.Random;

import com.yoxi.hudongtui.utils.common.ReadProperties;

/**
 *  wx版工具类
 * 
 * @author wql
 *
 */
public class CommonUtils {

	/**
	 * 替换httpurl主机名为随机数字
	 * @param url
	 * @return
	 */
	public static String randomUrl(){
		String url = ReadProperties.getPara("wx_httpPath");
		int rand = new Random().nextInt(1000000);
		if(url.contains("www")){
			url = url.replace("www",rand+"");
		}
		return url;
	}
}
