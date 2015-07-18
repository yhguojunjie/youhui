package com.yoxi.hudongtui.service.user;

import com.yoxi.hudongtui.model.user.UserTradeLog;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.plugin.UserTradeLogVo;

/**
 * 
 * 用户交易记录service
 * 
 * @author wql
 * 
 * 2014-11-17
 *
 */
public interface IUserTradeLogService {

	/**
	 * 保存
	 * @param userTradeLog
	 * @return
	 */
	public Integer add(UserTradeLog userTradeLog);
	
	/**
	 * 查询交易记录
	 * @param param 用户ID
	 * @param param 条件
	 * @param start 每页开始行
	 * @param count 每页显示数
	 * @return List<T>
	 */
	public Pagination<UserTradeLogVo> findUserTradeLogs4Page(String userId, String param, int currPage, int pageSize) throws Exception;

	/**
	 * 组装排序条件
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getOderFlag(String userId, String param) throws Exception;
}
