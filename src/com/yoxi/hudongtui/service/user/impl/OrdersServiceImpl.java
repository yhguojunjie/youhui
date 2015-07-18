package com.yoxi.hudongtui.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.user.OrdersDAO;
import com.yoxi.hudongtui.service.user.OrdersService;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.plugin.OrdersVo;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDAO ordersDAO;

	/**
	 * 组装排序条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getOderFlag(String userId, String param) throws Exception {
		String condition = " and a.userId = " + userId + param;

		return condition;
	}

	/**
	 * 查询等待付款数
	 */
	public int getOderWaitCount(String userId) throws Exception {
		String condition = " and userId = " + userId;
		return ordersDAO.getOderWaitCount(condition);
	}

	/**
	 * 查询交易成功数
	 */
	public int getOderSuccessCount(String userId) throws Exception {
		String condition = " and userId = " + userId;
		return ordersDAO.getOderSuccessCount(condition);
	}

	/**
	 * 查询交易关闭数
	 */
	public int getOderCloseCount(String userId) throws Exception {
		String condition = " and userId = " + userId;
		return ordersDAO.getOderCloseCount(condition);
	}

	/**
	 * 关闭交易
	 * 
	 */
	public Integer closeTradeState(Integer id, String str) {
		return ordersDAO.upByStr(id, str);
	}

	/**
	 * 查询我的订单记录
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
	@Override
	public Pagination<OrdersVo> findOrdersVo4Page(String userId, String param, int currPage, int pageSize) throws Exception {
		// 条件
		String condition = getOderFlag(userId, param);
		// 获取交易记录总数
		int totalCount = ordersDAO.getOrdersVoCount(condition);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0)
			startRow = 0;
		List<OrdersVo> userTradeLogVos = ordersDAO.findOrdersVos(condition, startRow, pageSize);
		return new Pagination<OrdersVo>(totalCount, pageSize, currPage, userTradeLogVos);
	}

}
