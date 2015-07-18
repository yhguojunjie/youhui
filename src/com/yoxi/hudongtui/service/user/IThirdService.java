package com.yoxi.hudongtui.service.user;

import javax.servlet.http.HttpServletRequest;

import com.yoxi.hudongtui.model.user.Third;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.vo.user.ThirdVO;
import com.yoxi.hudongtui.vo.wx.BindWxdVO;

/**
 * 
 * 第三方用户授权信息
 * 
 * @author wql
 *
 * 2014-10-13
 */
public interface IThirdService {

	/**
	 * 关联用户与第三方用户信息
	 * @param third
	 */
	public ThirdVO save(Third third,User user)throws Exception;
	
	
	/**
	 * 第三方用户绑定
	 * @param third
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ThirdVO bind(Third third,User user)throws Exception;
	
	
	/**
	 * 微信第三方账号绑定
	 * @param req
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public BindWxdVO bindwx(HttpServletRequest req, Integer userId)throws Exception;
}
