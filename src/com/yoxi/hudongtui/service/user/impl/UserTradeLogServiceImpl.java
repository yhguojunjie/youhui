package com.yoxi.hudongtui.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.user.UserTradeLogDAO;
import com.yoxi.hudongtui.model.user.UserTradeLog;
import com.yoxi.hudongtui.service.user.IUserTradeLogService;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.plugin.UserTradeLogVo;

@Service
public class UserTradeLogServiceImpl implements IUserTradeLogService {

	@Autowired
	private UserTradeLogDAO userTradeLogDAO;
	
	@Override
	public Integer add(UserTradeLog userTradeLog) {
		return userTradeLogDAO.add(userTradeLog).intValue();
	}

	/**
	 * 组装排序条件
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getOderFlag(String userId, String param) throws Exception {
		String condition = " and a.userId = " + userId;
		// param 条件待定
		return condition;
	}

	/**
	 * 查询交易记录
	 * @param param 用户ID
	 * @param param 条件
	 * @param start 每页开始行
	 * @param count 每页显示数
	 * @return List<T>
	 */
	@Override
	public Pagination<UserTradeLogVo> findUserTradeLogs4Page(String userId, String param, int currPage, int pageSize) throws Exception {
		// 条件
		String condition = getOderFlag(userId, param);
		// 获取交易记录总数
		int totalCount = userTradeLogDAO.getUserTradeLogCount(condition);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<UserTradeLogVo> userTradeLogVos = userTradeLogDAO.findUserTradeLogs(condition, startRow, pageSize);
		return new Pagination<UserTradeLogVo>(totalCount, pageSize, currPage, userTradeLogVos);
	}

}
