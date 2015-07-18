package com.yoxi.hudongtui.dao.cj;

import java.util.Date;
import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.ActGivegiftRecord;

@DAO
public interface ActGivegiftRecordDAO {

	@SQL("insert into t_act_givegift_record (activityId,#if(:1.prizeName){prizeName,}#if(:1.tel){tel,}" +
			"#if(:1.opState){opState,}#if(:1.prizeTime){prizeTime,}#if(:1.exchangeTime){exchangeTime,}mpOpenId) " +
			"values (:1.activityId,#if(:1.prizeName){:1.prizeName,}#if(:1.tel){:1.tel,}#if(:1.opState){:1.opState,}" +
			"#if(:1.prizeTime){:1.prizeTime,}#if(:1.exchangeTime){:1.exchangeTime,}:1.mpOpenId)")
	public Identity add(ActGivegiftRecord actGivegiftRecord);
	
	@SQL("select * from t_act_givegift_record where activityId = :1 and mpOpenId = :2")
	public ActGivegiftRecord findByActIdAndOpenId(Integer activityId, String openId);
	
	@SQL("update t_act_givegift_record set ##(:key) = :value where id = :1.id")
	public int updateByCriteria(ActGivegiftRecord actGivegiftRecord, @SQLParam("key") String key, @SQLParam("value") String value);
	
	@SQL("select * from t_act_givegift_record where activityId = :1 order by prizeTime asc LIMIT :2,:3")
	public List<ActGivegiftRecord> findAll(Integer activityId, int startRow, int pageSize);
	
	@SQL("select count(*) from t_act_givegift_record where activityId = :1")
	public int countAllPrizeRecord(Integer activityId);
	
	@SQL("SELECT count(*) FROM t_act_givegift_record where activityId = :1")
	public int getRecordCount(Integer activityId);
	
	@SQL("SELECT * FROM t_act_givegift_record where activityId = :1 order by prizeTime desc LIMIT :2,:3")
	public List<ActGivegiftRecord> getRecord(Integer activityId, int startRow, int pageSize);
	
	@SQL("update t_act_givegift_record set opState = :2 #if(:3){,exchangeTime = :3}#else{,exchangeTime = NULL} where id = :1")
	public int updateOpStatus(Integer id, String status, Date date);
	
	@SQL("select opState from t_act_givegift_record where id = :1")
	public String findOpStatus(Integer id);
}
