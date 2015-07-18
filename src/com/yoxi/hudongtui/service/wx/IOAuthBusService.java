package com.yoxi.hudongtui.service.wx;

/**
 * 
 * 授权认证业务处理类
 * 
 * @author wangql
 * 
 * 2014-10-20
 *
 */
public interface IOAuthBusService {
	
	/**
	 * 处理微信授权认证用户数据入库
	 * @param code 微信授权认证返回
	 * @param oauthType 认证类型  0 为base 1为userInfo
	 * @return
	 */
	public Boolean process(String code, Integer oauthType);
}
