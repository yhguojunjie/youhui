package com.yoxi.hudongtui.controllers.pc;

import javax.servlet.http.HttpServletRequest;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.controllers.GlobalRequired;
import com.yoxi.hudongtui.controllers.LoginRequired;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.plugin.IPluginActService;
import com.yoxi.hudongtui.service.user.OrdersService;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.vo.plugin.MyActVO;
import com.yoxi.hudongtui.vo.plugin.OrdersVo;

/**
 * 
 * 个人相关资源 ，该controller的方法 需要登录 才能调用
 * 
 * @author wql
 * 
 * @Date 2015年3月27日
 * 
 */
@GlobalRequired
@LoginRequired
public class MyController {

	@Autowired
	private IPluginActService pluginActService;
	@Autowired
	private OrdersService ordersService;

	/**
	 * 我的活动
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("actList")
	public String actList(Invocation inv) throws Exception {
		// 1.用户登录信息
		User user = SessionUtil.getUser(inv.getRequest());
		// User user = new User();
		// user.setUserId(3);
		// user.setAgentId(13);
		// 未开始活动数量
		String condition1 = "  AND a.overdueTime > NOW() AND CASE WHEN b.startTime IS NULL THEN ( ISNULL(b.startTime)) ELSE  b.startTime > NOW() END ";
		Integer startNum = pluginActService.getMyActCount(user.getUserId(), user.getAgentId(), condition1);
		inv.getRequest().setAttribute("startNum", startNum);
		// 正在进行
		String condition2 = "  AND a.overdueTime > NOW() AND NOW() BETWEEN b.startTime AND b.endTime ";
		Integer startingNum = pluginActService.getMyActCount(user.getUserId(), user.getAgentId(), condition2);
		inv.getRequest().setAttribute("startingNum", startingNum);
		// 已结束
		String condition3 = "  AND (a.overdueTime < NOW() OR b.endTime < NOW()) ";
		Integer endNum = pluginActService.getMyActCount(user.getUserId(), user.getAgentId(), condition3);
		inv.getRequest().setAttribute("endNum", endNum);
		return "myactv2";
	}

	/**
	 * ajax 获取我的活动列表
	 * 
	 * @param inv
	 * @param xFlag
	 * @param currentPage
	 * @return
	 * @throws Exception
	 */
	@Get("/ajaxmyActList")
	public String ajaxmyActList(Invocation inv, @Param("xFlag") String xFlag, @Param("currentPage") final int currentPage) throws Exception {
		// 1.用户登录信息
		User user = SessionUtil.getUser(inv.getRequest());
		// User user = new User();
		// user.setUserId(3);
		// user.setAgentId(13);
		// 2.用户插件信息 【xFlag 0表示全部，1表示未开始,2表示已过期,3表示进行中】
		String condition = " ";
		if (ValidateUtil.isEmpty(xFlag) || xFlag.equals("0")) {
			condition = " ";
		} else if (xFlag.equals("1")) {
			condition = " AND a.overdueTime > NOW() AND CASE WHEN b.startTime IS NULL THEN ( ISNULL(b.startTime)) ELSE  b.startTime > NOW() END ";
		} else if (xFlag.equals("2")) {
			condition = " AND a.overdueTime > NOW() AND NOW() BETWEEN b.startTime AND b.endTime  ";
		} else if (xFlag.endsWith("3")) {
			condition = " AND (a.overdueTime < NOW() OR b.endTime < NOW()) ";
		}
		Pagination<MyActVO> myActList = pluginActService.findMyActList4Page(user.getUserId(), user.getAgentId(), currentPage, condition);
		return "@json:" + JsonUtils.toJson(myActList);
	}

	/**
	 * 用户定单列表页
	 * 
	 * @param inv
	 * @return
	 */
	@Get("/orderList")
	public String orderList(Invocation inv) {
		return "myorderv2";
	}

	/**
	 * 查询我的订单列表信息
	 * 
	 * @param xFlag
	 *            预留条件
	 * @param currPage
	 *            当前页
	 * @return
	 * @throws Exception
	 */
	@Get("/ajaxOrderList")
	public String ajaxOrderList(Invocation inv, @Param("xFlag") String xFlag, @Param("currentPage") final int currentPage) throws Exception {
		// 1.用户登录信息
		User user = SessionUtil.getUser(inv.getRequest());
		String userId = String.valueOf(user.getUserId());
		// String userId = "1000000012";
		if (xFlag != null) {
			if (xFlag.equals("3")) {
				xFlag = " and a.tradeState=1";
			} else if (xFlag.equals("2")) {
				xFlag = " and a.tradeState=2";
			} else if (xFlag.equals("1")) {
				xFlag = " and a.tradeState=0";
			} else {
				xFlag = "";
			}
		} else {
			xFlag = "";
		}
		// 2.我的订单记录
		Pagination<OrdersVo> ordersVoPagination = ordersService.findOrdersVo4Page(userId, xFlag, currentPage, Globals.PAGE_SHOW_NUMBER);
		return "@json:" + JsonUtils.toJson(ordersVoPagination);
	}

	/**
	 * 查询订单各种状统计值
	 * 
	 * @return
	 * @throws Exception
	 */
	@Get("/ajaxOrdercount")
	public String ajaxOrdercount(Invocation inv) throws Exception {
		// 1.用户登录信息
		User user = SessionUtil.getUser(inv.getRequest());
		String userId = String.valueOf(user.getUserId());
		int account1 = ordersService.getOderWaitCount(userId);
		int account2 = ordersService.getOderSuccessCount(userId);
		int account3 = ordersService.getOderCloseCount(userId);
		String accountString = String.valueOf(account1) + ";" + String.valueOf(account2) + ";" + String.valueOf(account3);
		return "@json:" + JsonUtils.toJson(accountString);
	}

	/**
	 * 关闭交易
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Get("/closeTradeState")
	public String closeTradeState(HttpServletRequest request, @Param("id") String id) throws Exception {
		String str = " tradeState =1 ";
		ordersService.closeTradeState(Integer.parseInt(id), str);
		return null;
	}
}
