package com.yoxi.hudongtui.service.order;

import com.yoxi.hudongtui.model.agent.AgentOrder;
import com.yoxi.hudongtui.vo.order.OrderDownVO;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;

/**
 * 
 *  定单
 * 
 * @author wql
 *
 * 2014-11-15
 *
 */
public interface IOrderService {
	
	/**
	 * 下单处理
	 * @param orderDown
	 * @return
	 */
	public OrderDownVO down(OrderDownVO orderDown,PluginDetailVO plugin)throws Exception;
	
	/**
	 * 查找订单
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public AgentOrder findByOrderId(Integer orderId)throws Exception;
}
