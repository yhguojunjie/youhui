package com.yoxi.hudongtui.service.content;

import java.util.List;

import com.yoxi.hudongtui.vo.content.ActClassicVO;

/**
 * 
 * 经典案例
 * 
 * @author wql
 * 
 * @Date 2015年3月22日
 * 
 */
public interface IActClassicService {

	/**
	 * 获取首页经典案例推荐
	 * 
	 * @param agentId
	 * @param condition
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<ActClassicVO> findRecList(Integer agentId, String condition, int start, int count) throws Exception;

	/**
	 * 查找列表
	 * 
	 * @param agentId
	 * @param condition
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<ActClassicVO> findList(Integer agentId, String condition, int start, int count) throws Exception;

	public List<ActClassicVO> findAgentRecList(Integer agentId, String condition, int start, int count) throws Exception;

	public List<ActClassicVO> findAgentList(Integer agentId, String condition, int start, int count) throws Exception;
}
