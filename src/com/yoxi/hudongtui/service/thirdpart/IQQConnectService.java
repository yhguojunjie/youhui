package com.yoxi.hudongtui.service.thirdpart;

import javax.servlet.http.HttpServletRequest;

import com.yoxi.hudongtui.vo.user.ThirdVO;

/**
 * QQ互联
 * 
 * @author wql
 *
 */
public interface IQQConnectService {
	
	/**
	 * 登录
	 * @param role 角色  1商家，2开发者
	 * @throws Exception
	 */
	public ThirdVO login(HttpServletRequest request,String role)throws Exception;
}
