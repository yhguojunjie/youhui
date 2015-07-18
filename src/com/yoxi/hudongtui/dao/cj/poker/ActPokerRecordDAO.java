package com.yoxi.hudongtui.dao.cj.poker;

import java.util.Date;
import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.poker.ActPokerRecord;

/**
 * 我坐庄
 * 
 * @author jjb
 *
 * 2015-1-22
 *
 */
@DAO
public interface ActPokerRecordDAO {

	@SQL("insert into t_act_poker_record (activityId,#if(:1.prizeName){prizeName,}#if(:1.tel){tel,}#if(:1.username){username,}" +
			"#if(:1.mailAddress){mailAddress,}#if(:1.qq){qq,}#if(:1.wechatId){wechatId,}#if(:1.otherInfo){otherInfo,}" +
			"#if(:1.opState){opState,}#if(:1.prizeTime){prizeTime,}#if(:1.exchangeTime){exchangeTime,}mpOpenId) " +
			"values (:1.activityId,#if(:1.prizeName){:1.prizeName,}#if(:1.tel){:1.tel,}#if(:1.username){:1.username,}" +
			"#if(:1.mailAddress){:1.mailAddress,}#if(:1.qq){:1.qq,}#if(:1.wechatId){:1.wechatId,}#if(:1.otherInfo){:1.otherInfo,}" +
			"#if(:1.opState){:1.opState,}#if(:1.prizeTime){:1.prizeTime,}#if(:1.exchangeTime){:1.exchangeTime,}:1.mpOpenId)")
	public Identity add(ActPokerRecord actPokerRecord);
	
	@SQL("update t_act_poker_record set #if(:1.prizeName){prizeName=:1.prizeName,}#if(:1.tel){tel=:1.tel,}#if(:1.username){username=:1.username,}" +
			"#if(:1.mailAddress){mailAddress=:1.mailAddress,}#if(:1.qq){qq=:1.qq,}#if(:1.wechatId){wechatId=:1.wechatId,}#if(:1.otherInfo){otherInfo=:1.otherInfo,}" +
			"#if(:1.opState){opState=:1.opState,}#if(:1.prizeTime){prizeTime=:1.prizeTime,}#if(:1.exchangeTime){exchangeTime=:1.exchangeTime,} where id = :1.id")
	public int update(ActPokerRecord actPokerRecord);
	
	@SQL("select * from t_act_poker_record where activityId = :1 and !ISNULL(prizeTime) order by id asc LIMIT :2,:3")
	public List<ActPokerRecord> findAll(Integer activityId, int startRow, int pageSize);
	
	@SQL("select count(*) from t_act_poker_record where activityId = :1")
	public int countAllPrizeRecord(Integer activityId);

	@SQL("SELECT count(*) FROM t_act_poker_record where activityId = :1 and !ISNULL(prizeTime)")
	public int getRecordCount(Integer activityId);
	//查询中奖名单
	@SQL("SELECT * FROM t_act_poker_record where activityId = :1 and !ISNULL(prizeTime) order by id desc LIMIT :2,:3")
	public List<ActPokerRecord> getRecord(Integer activityId, int startRow, int pageSize);
	
	///////////////////////////////////////////////////////////
	
	@SQL("select *,count(distinct mpOpenId) as temp from t_act_poker_record where activityId = :1 group by mpOpenId order by id asc LIMIT :2,:3")
	public List<ActPokerRecord> findJoinAll(Integer activityId, int startRow, int pageSize);
	
	@SQL("SELECT count(distinct mpOpenId) FROM t_act_poker_record where activityId = :1")
	public int getJoinCount(Integer activityId);
	//查询参与人数
	@SQL("SELECT *,count(distinct mpOpenId) as temp FROM t_act_poker_record where activityId = :1 group by mpOpenId order by id desc LIMIT :2,:3")
	public List<ActPokerRecord> getJoin(Integer activityId, int startRow, int pageSize);
	
	////////////////////////////////////////////////////////////////////
	
	@SQL("update t_act_poker_record set opState = :2 #if(:3){,exchangeTime = :3}#else{,exchangeTime = NULL} where id = :1")
	public int updateOpStatus(Integer id, String status, Date date);
	
	@SQL("select opState from t_act_poker_record where id = :1")
	public String findOpStatus(Integer id);
	
	//计算发出奖品数
	@SQL("select count(*) from t_act_poker_record where activityId = :1 and !ISNULL(prizeName)")
	public Integer getPrizeTotal(Integer activityId);
}
