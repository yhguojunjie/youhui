package com.yoxi.hudongtui.dao.user;


import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.user.UserTradeLog;
import com.yoxi.hudongtui.vo.plugin.UserTradeLogVo;

/**
 * 
 * 用户交易记录
 * 
 * @author wql
 *
 * 2014-10-17
 * 
 */
@DAO
public interface UserTradeLogDAO {

	/**
	 * 添加
	 * @param userTradeLog
	 * @return
	 */
	@SQL("insert into t_user_tradelog (userId,tradeType,#if(:1.purchaseType){purchaseType,}#if(:1.productId){productId,}#if(:1.agentId){agentId,}" +
			"#if(:1.productType){productType,}#if(:1.orderId){orderId,}#if(:1.description){description,}amount,sourceType,createTime)" +
			" values (:1.userId,:1.tradeType,#if(:1.purchaseType){:1.purchaseType,}#if(:1.productId){:1.productId,}#if(:1.agentId){:1.agentId,}" +
			"#if(:1.productType){:1.productType,}#if(:1.orderId){:1.orderId,}#if(:1.description){:1.description,}:1.amount,:1.sourceType,NOW())")
	public Identity add(UserTradeLog ordersVo);
	
	
	/**
	 * 按用户id查找
	 * @param userId
	 * @return
	 */
	@SQL("select * from t_user_tradelog where userId = :1 order by createTime desc")
	public List<UserTradeLog> findByUserId(Integer userId);
	
	/**
	 * 查询交易记录总数
	 * @param condition 查询条件
	 */
	@SQL("SELECT count(*) from t_user_tradelog a WHERE 1 =1 ##(:condition)")
	public int getUserTradeLogCount(@SQLParam("condition") String condition) throws Exception;
	
	/**
	 * 查询交易记录列表信息
	 * @param condition 查询条件[预留]
	 * @param startRow 每页开始行
	 * @param pageSize 每页显示数
	 * @return List<UserPluginVo>
	 */
	@SQL("select * from t_user_tradelog a where 1=1 ##(:condition) order by a.createTime LIMIT :2,:3" )
	public List<UserTradeLogVo> findUserTradeLogs(@SQLParam("condition") String condition, int startRow, int pageSize) throws Exception;
	
}
