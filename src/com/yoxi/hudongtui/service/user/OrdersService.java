package com.yoxi.hudongtui.service.user;

import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.plugin.OrdersVo;

/**
 * 
 * 我的订单记录service
 * 
 * @author wql
 * 
 *         2014-11-17
 * 
 */
public interface OrdersService {

	/**
	 * 查询我的订单
	 * 
	 * @param param
	 *            用户ID
	 * @param param
	 *            条件
	 * @param start
	 *            每页开始行
	 * @param count
	 *            每页显示数
	 * @return List<T>
	 */
	public Pagination<OrdersVo> findOrdersVo4Page(String userId, String param, int currPage, int pageSize) throws Exception;

	/**
	 * 查询等待付款数
	 */
	public int getOderWaitCount(String userId) throws Exception;

	/**
	 * 查询交易成功数
	 */
	public int getOderSuccessCount(String userId) throws Exception;

	/**
	 * 查询交易关闭数
	 */
	public int getOderCloseCount(String userId) throws Exception;

	/**
	 * 关闭交易
	 * 
	 */
	public Integer closeTradeState(Integer id, String str);

	/**
	 * 组装排序条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getOderFlag(String userId, String param) throws Exception;
}
