package com.yoxi.hudongtui.dao.user;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.vo.plugin.OrdersVo;

/**
 * 
 * 我的订单记录
 * 
 * @author gjj
 * 
 *         2015-3-26
 * 
 */
@DAO
public interface OrdersDAO {

	/**
	 * 查询我的订单总数
	 * 
	 * @param condition
	 *            查询条件
	 */
	@SQL("SELECT count(*) from t_agent_order a WHERE 1 =1 ##(:condition)")
	public int getOrdersVoCount(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 查询等待付款数
	 */
	@SQL("select count(*) from  t_agent_order where tradeState=0 ##(:condition)")
	public int getOderWaitCount(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 查询交易成功数
	 */
	@SQL("select count(*) from  t_agent_order where tradeState=2 ##(:condition)")
	public int getOderSuccessCount(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 查询交易关闭数
	 */
	@SQL("select count(*) from  t_agent_order where tradeState=1 ##(:condition)")
	public int getOderCloseCount(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 按拼凑字符串更新
	 * 
	 * @param pluginId
	 * @param upstr
	 * @return
	 */
	@SQL("update t_agent_order set  ##(:upstr) where id = :1")
	public int upByStr(Integer id, @SQLParam("upstr") String upstr);

	/**
	 * 查询我的订单记录列表信息
	 * 
	 * @param condition
	 *            查询条件[预留]
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<UserPluginVo>
	 */
	@SQL("select a.id,a.agentId,a.buyNum,a.charge,a.costPrice," + "  a.orderTime,(a.buyNum)*(a.salePrice) as payAll,a.payOrderStatus,a.payTradeNo,a.productId,"
			+ "  a.productType,a.purchaserId,a.purchaseType,a.repreCoin," + "  a.salePrice,a.tradeState,a.userId,(SELECT b.name from " + "  t_plugin b where  b.id=a.productId )as  "
			+ "  productName,(SELECT b.icon from t_plugin b where  " + "  b.id=a.productId )as  productIcon  from " + "  t_agent_order a  where 1=1 ##(:condition) "
			+ "  order by a.orderTime desc LIMIT :2,:3")
	public List<OrdersVo> findOrdersVos(@SQLParam("condition") String condition, int startRow, int pageSize) throws Exception;

}
