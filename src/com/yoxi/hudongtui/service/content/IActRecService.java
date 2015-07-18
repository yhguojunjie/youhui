package com.yoxi.hudongtui.service.content;

import java.util.List;

import com.yoxi.hudongtui.vo.content.ActRecVO;

/**
 *
 * 活动推荐
 *
 * @author wql
 *
 * @Date 2015年3月25日
 *
 */
public interface IActRecService {
	
	/**
	 * 查找列表
	 * @param agentId
	 * @param condition
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<ActRecVO> findActList(Integer agentId,String condition,int start,int count)throws Exception;
}
