package com.yoxi.hudongtui.service.content;

import java.util.List;

import com.yoxi.hudongtui.vo.content.PluginRecVO;

/**
 *
 * 模板推荐
 *
 * @author wql
 *
 * @Date 2015年3月22日
 *
 */
public interface IPluginRecService {
	
	/**
	 * 模板推荐
	 * @param agentId
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<PluginRecVO> findPluginList(Integer agentId,String condition,int start, int count)throws Exception;
}
