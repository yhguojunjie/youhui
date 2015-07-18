package com.yoxi.hudongtui.dao.cj.fingerprint;

import java.util.Date;
import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.fingerprint.ActFingerprintRecord;

/**
 * 指纹配对
 * 
 * @author jjb
 *
 * 2015-2-4
 *
 */
@DAO
public interface ActFingerprintRecordDAO {
	
	@SQL("insert into t_act_fingerprint_record (activityId,#if(:1.prizeName){prizeName,}#if(:1.tel){tel,}#if(:1.username){username,}" +
			"#if(:1.mailAddress){mailAddress,}#if(:1.qq){qq,}#if(:1.wechatId){wechatId,}#if(:1.otherInfo){otherInfo,}" +
			"#if(:1.opState){opState,}#if(:1.prizeTime){prizeTime,}#if(:1.exchangeTime){exchangeTime,}mpOpenId,helpOpenId) " +
			"values (:1.activityId,#if(:1.prizeName){:1.prizeName,}#if(:1.tel){:1.tel,}#if(:1.username){:1.username,}" +
			"#if(:1.mailAddress){:1.mailAddress,}#if(:1.qq){:1.qq,}#if(:1.wechatId){:1.wechatId,}#if(:1.otherInfo){:1.otherInfo,}" +
			"#if(:1.opState){:1.opState,}#if(:1.prizeTime){:1.prizeTime,}#if(:1.exchangeTime){:1.exchangeTime,}:1.mpOpenId,:1.helpOpenId)")
	public Identity add(ActFingerprintRecord actFingerprintRecord);

	@SQL("select * from t_act_fingerprint_record where activityId = :1 and !ISNULL(prizeTime) order by id asc LIMIT :2,:3")
	public List<ActFingerprintRecord> findAll(Integer activityId, int startRow, int pageSize);
	
	@SQL("select count(*) from t_act_fingerprint_record where activityId = :1")
	public int countAllPrizeRecord(Integer activityId);

	@SQL("SELECT count(*) FROM t_act_fingerprint_record where activityId = :1 and !ISNULL(prizeTime)")
	public int getRecordCount(Integer activityId);
	//查询中奖名单
	@SQL("SELECT * FROM t_act_fingerprint_record where activityId = :1 and !ISNULL(prizeTime) order by id desc LIMIT :2,:3")
	public List<ActFingerprintRecord> getRecord(Integer activityId, int startRow, int pageSize);
	
	////////////////////////////////////////////////////////////////////
	
	@SQL("update t_act_fingerprint_record set opState = :2 #if(:3){,exchangeTime = :3}#else{,exchangeTime = NULL} where id = :1")
	public int updateOpStatus(Integer id, String status, Date date);
	
	@SQL("select opState from t_act_fingerprint_record where id = :1")
	public String findOpStatus(Integer id);
	
	//统计奖品数量 
	@SQL("select count(*) from t_act_fingerprint_record where activityId = :1")
	public Integer countPrizeNumber(Integer activityId);
}
