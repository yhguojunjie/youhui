package com.yoxi.hudongtui.service.pay;

import com.yoxi.hudongtui.model.agent.AgentOrder;
import com.yoxi.hudongtui.vo.order.OrderDownVO;

/**
 * 支付回调
 * @Description
 *
 * @author wql
 *
 * @Date 2015年4月20日
 *
 */
public interface IPayBackService {

	/**
	 * 系统定单处理回调
	 * @param orderDownVO
	 * @return
	 * @throws Exception
	 */
	public String back(OrderDownVO orderDownVO,AgentOrder agentOrder)throws Exception;
}
