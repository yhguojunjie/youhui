package com.yoxi.hudongtui.dao.cj.bottle;

import java.util.Date;
import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.bottle.ActBottleRecord;

/**
 * 漂流瓶
 * 
 * @author jjb
 *
 * 2015-4-27
 *
 */
@DAO
public interface ActBottleRecordDAO {

	@SQL("insert into t_act_bottle_record (activityId,#if(:1.tel){tel,}#if(:1.username){username,}" +
			"#if(:1.mailAddress){mailAddress,}#if(:1.qq){qq,}#if(:1.wechatId){wechatId,}#if(:1.otherInfo){otherInfo,}" +
			"#if(:1.count){count,}#if(:1.opState){opState,}#if(:1.submitTime){submitTime,}#if(:1.exchangeTime){exchangeTime,}mpOpenId) " +
			"values (:1.activityId,#if(:1.tel){:1.tel,}#if(:1.username){:1.username,}" +
			"#if(:1.mailAddress){:1.mailAddress,}#if(:1.qq){:1.qq,}#if(:1.wechatId){:1.wechatId,}#if(:1.otherInfo){:1.otherInfo,}" +
			"#if(:1.count){:1.count,}#if(:1.opState){:1.opState,}#if(:1.submitTime){:1.submitTime,}#if(:1.exchangeTime){:1.exchangeTime,}:1.mpOpenId)")
	public Identity add(ActBottleRecord actBottleRecord);
	
	@SQL("update t_act_bottle_record set #if(:1.tel){tel=:1.tel,}#if(:1.username){username=:1.username,}" +
			"#if(:1.mailAddress){mailAddress=:1.mailAddress,}#if(:1.qq){qq=:1.qq,}#if(:1.wechatId){wechatId=:1.wechatId,}#if(:1.otherInfo){otherInfo=:1.otherInfo,}" +
			"#if(:1.opState){opState=:1.opState,}#if(:1.count){count=:1.count,}#if(:1.submitTime){submitTime=:1.submitTime,}#if(:1.exchangeTime){exchangeTime=:1.exchangeTime,} where id = :1.id")
	public int update(ActBottleRecord actBottleRecord);
	
	@SQL("update t_act_bottle_record set ##(:key) = :value where id = :1.id")
	public int updateByCriteria(ActBottleRecord actBottleRecord, @SQLParam("key") String key, @SQLParam("value") String value);
	
	@SQL("SELECT * FROM t_act_bottle_record where activityId = :1 order by id desc LIMIT :2,:3")
	public List<ActBottleRecord> getRecord(Integer activityId, int startRow, int pageSize);
	
	@SQL("SELECT count(*) FROM t_act_bottle_record where activityId = :1")
	public int getRecordCount(Integer activityId);
	
	@SQL("select * from t_act_bottle_record where activityId = :1 order by id asc LIMIT :2,:3")
	public List<ActBottleRecord> findAll(Integer activityId, int startRow, int pageSize);
	
	@SQL("select count(*) from t_act_bottle_record where activityId = :1")
	public int countAllPrizeRecord(Integer activityId);
	
	@SQL("update t_act_bottle_record set opState = :2 #if(:3){,exchangeTime = :3}#else{,exchangeTime = NULL} where id = :1")
	public int updateOpStatus(Integer id, String status, Date date);
	
	@SQL("select opState from t_act_bottle_record where id = :1")
	public String findOpStatus(Integer id);
}
