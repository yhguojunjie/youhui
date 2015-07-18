package com.yoxi.hudongtui.service.content;

import java.util.List;

import com.yoxi.hudongtui.model.content.Banner;

/**
 * 
 * Banner
 * 
 * @author gjj
 * 
 * @Date 2015年4月20日
 * 
 */
public interface IBannerService {

	/**
	 * 获取Banner列表
	 * 
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<Banner> getBannerList(String findStr) throws Exception;

	public List<Banner> getAgentBannerList(String findStr) throws Exception;
}
