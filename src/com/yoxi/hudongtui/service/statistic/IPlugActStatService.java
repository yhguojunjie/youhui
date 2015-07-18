package com.yoxi.hudongtui.service.statistic;

/**
 * 
 * 插件活动统计相关
 * 
 * @author wql
 * 
 * 2015-01-13
 *
 */
public interface IPlugActStatService {

	/**
	 * 更新数据的活动数
	 * @return
	 * @throws Exception
	 */
	public void actBrowserNumTodb()throws Exception;
	
	/**
	 * 更新参与人数,独立IP到数据库
	 * @throws Exception
	 */
	public void actJoinNumTodb()throws Exception;
	
	/**
	 * 更新活动统计数，包含浏览量和独立IP
	 * @throws Exception
	 */
	public void statDataTodb()throws Exception;
	
	/**
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Integer getJoinNum(String actId)throws Exception;
	
	/**
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Integer getBrowserNum(String actId)throws Exception;
}
