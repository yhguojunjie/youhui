package com.yoxi.hudongtui.dao.agent;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.agent.AgentOrder;
import com.yoxi.hudongtui.vo.order.OrderDownVO;

/**
 * 
 * 代理商定单DAO
 * 
 * @author wql
 * 
 * 2014-10-15
 * 
 */

@DAO
public interface AgentOrderDAO {

	/**
	 * 添加
	 * @param order
	 * @return
	 */
	@SQL("insert into t_agent_order(userId,productId,charge,#if(:1.productType){productType,}#if(:1.repreCoin){repreCoin,}#if(:1.purchaserId){purchaserId,}"
			+ "#if(:1.purchaseType){purchaseType,}#if(:1.payOrderStatus){payOrderStatus,}#if(:1.payTradeNo){payTradeNo,}#if(:1.sellerAccount){sellerAccount,}" 
			+ "#if(:1.payNoticeTime){payNoticeTime,}#if(:1.sourceType){sourceType,}#if(:1.startDate){startDate,}#if(:1.endDate){endDate,}"
			+ "#if(:1.salePrice){salePrice,}#if(:1.costPrice){costPrice,}#if(:1.buyNum){buyNum,}#if(:1.tradeState){tradeState,}#if(:1.agentId){agentId,}#if(:1.userPluginId){userPluginId,}orderTime) "
			+ "values(:1.userId,:1.productId,:1.charge,#if(:1.productType){:1.productType,}#if(:1.repreCoin){:1.repreCoin,}#if(:1.purchaserId){:1.purchaserId,}"
			+ "#if(:1.purchaseType){:1.purchaseType,}#if(:1.payOrderStatus){:1.payOrderStatus,}#if(:1.payTradeNo){:1.payTradeNo,}#if(:1.sellerAccount){:1.sellerAccount,}"
			+ "#if(:1.payNoticeTime){:1.payNoticeTime,}#if(:1.sourceType){:1.sourceType,}#if(:1.startDate){:1.startDate,}#if(:1.endDate){:1.endDate,}"
			+ "#if(:1.salePrice){:1.salePrice,}#if(:1.costPrice){:1.costPrice,}#if(:1.buyNum){:1.buyNum,}#if(:1.tradeState){:1.tradeState,}#if(:1.agentId){:1.agentId,}#if(:1.userPluginId){:1.userPluginId,}:1.orderTime)")
	public Identity save(AgentOrder order);

	/**
	 * 修改实体
	 * @return
	 */
	@SQL("update t_agent_order set userId = :1.userId, productId = :1.productId,productType = :1.productType, charge = :1.charge, repreCoin = :1.repreCoin,"
			+ "purchaserId = :1.purchaserId,purchaseType = :1.purchaseType, orderTime = :1.orderTime,payOrderStatus = :1.payOrderStatus, "
			+ "payTradeNo = :1.payTradeNo,payNoticeTime = :1.payNoticeTime,sourceType = :1.sourceType,startDate = :1.startDate, endDate = :1.endDate,"
			+ "salePrice = :1.salePrice,costPrice = :1.costPrice,buyNum = :1.buyNum,tradeState = :1.tradeState,agentId = :1.agentId where id = :1.id")
	public int update(AgentOrder order);

	/**
	 * 按属性修改
	 * @param third
	 * @param key
	 * @param value
	 * @return
	 */
	@SQL("update t_agent_order set ##(:key) = :value where id = :1.id")
	public int updateByCriteria(AgentOrder order, @SQLParam("key") String key, @SQLParam("value") String value);

	/**
	 * 按主键ID查找
	 * @param 
	 * @return
	 */
	@SQL("select * from t_agent_order where id = :1")
	public AgentOrder findByOrderId(Integer orderId);

	/**
	 * 按第三方交易号和平台类型查找实体
	 * @param payTradeNo
	 * @param purchaseType
	 * @return
	 */
	@SQL("select * from t_agent_order where payTradeNo = :1 and purchaseType = :2")
	public AgentOrder findBypayTradeNo(String payTradeNo, String purchaseType);
	
	/**
	 * 查找当前是否有效定单，在使用时间内
	 * @param productId
	 * @param userId
	 * @param endDate
	 * @return
	 */
	@SQL("SELECT COUNT(*) FROM t_agent_order t WHERE t.payOrderStatus = 1 and t.productId = :1.productId and t.userId = :1.userId " +
			" and NOW() BETWEEN t.startDate and t.endDate")
	public int isOrdered(OrderDownVO orderDown);
}
