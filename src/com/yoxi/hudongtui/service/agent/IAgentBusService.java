package com.yoxi.hudongtui.service.agent;

import javax.servlet.http.HttpServletRequest;

import com.yoxi.hudongtui.vo.agent.AgentInfoVO;
import com.yoxi.hudongtui.vo.pay.alipay.AlipayConfigVO;


/**
 * 
 * 代理商相关业务操作serivce
 *
 * @author wql
 *
 * @Date 2015年3月19日
 *
 */
public interface IAgentBusService {
	
	/**
	 * 获取代理商相关信息
	 * @param agentId
	 * @return
	 * @throws Exception
	 */
	public AgentInfoVO getAgentInfoVO(Integer agentId)throws Exception;
	
	/**
	 * 带Memcahced缓存处理获代理商信息
	 * @param agentId
	 * @return
	 * @throws Exception
	 */
	public AgentInfoVO getMcacheAgentInfo(Integer agentId)throws Exception;
	
	/**
	 * 根据用户访问的域名查找所归属的代理商id
	 * @param inv
	 * @return 代理商id
	 */
	public Integer getAgentIdForUser(HttpServletRequest request)throws Exception;

	/**
	 * 根据代理商id返回支付宝信息，未添加信息情况下读系统配置文件
	 * @param id
	 * @return
	 */
	public AlipayConfigVO getAgentAliConfg(Integer id)throws Exception;
}

