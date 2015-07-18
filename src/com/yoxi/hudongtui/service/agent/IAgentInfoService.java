package com.yoxi.hudongtui.service.agent;

import java.util.List;

import com.yoxi.hudongtui.model.agent.AgentInfo;
import com.yoxi.hudongtui.vo.pay.alipay.AlipayConfigVO;

/**
 * 
 * 代理商信息service
 *
 * @author wql
 *
 * @Date 2015年3月14日
 *
 */
public interface IAgentInfoService {
	
	/**
	 * 按拼凑字符串来查，确保返回记录为唯一
	 * @param getStr
	 * @return 代理商
	 */
	public AgentInfo getByStr(String getStr);
	
	/**
	 * 按拼凑字符串来查, 返回值为列表
	 * @param findStr
	 * @return 代理商列表
	 */
	public List<AgentInfo> findByStr(String findStr);
	
	/**
	 * 按主键id查找返回 代理商域名
	 * @param id
	 * @return 代理商域名
	 */
	public String getDomainById(Integer id);
	
	/**
	 * 按主键查找返回实体
	 * @param id
	 * @return
	 */
	public AgentInfo getById(Integer id);
	
}
