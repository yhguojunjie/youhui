package com.yoxi.hudongtui.dao.cj;

import java.util.Date;
import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.ActRunmanRecord;

/**
 * 奔跑吧兄弟
 * 
 * @author jjb
 *
 *2014-12-4
 *
 */
@DAO
public interface ActRunmanRecordDAO {

	@SQL("insert into t_act_runman_record (activityId,#if(:1.prizeName){prizeName,}#if(:1.prizeType){prizeType,}" +
			"#if(:1.prizeImgUrl){prizeImgUrl,}#if(:1.tel){tel,}#if(:1.username){username,}#if(:1.bestScore){bestScore,}" +
			"#if(:1.mailAddress){mailAddress,}#if(:1.qq){qq,}#if(:1.wechatId){wechatId,}#if(:1.otherInfo){otherInfo,}" +
			"#if(:1.opState){opState,}#if(:1.prizeTime){prizeTime,}#if(:1.exchangeTime){exchangeTime,}mpOpenId) " +
			"values (:1.activityId,#if(:1.prizeName){:1.prizeName,}#if(:1.prizeType){:1.prizeType,}" +
			"#if(:1.prizeImgUrl){:1.prizeImgUrl,}#if(:1.tel){:1.tel,}#if(:1.username){:1.username,}#if(:1.bestScore){:1.bestScore,}" +
			"#if(:1.mailAddress){:1.mailAddress,}#if(:1.qq){:1.qq,}#if(:1.wechatId){:1.wechatId,}#if(:1.otherInfo){:1.otherInfo,}" +
			"#if(:1.opState){:1.opState,}#if(:1.prizeTime){:1.prizeTime,}#if(:1.exchangeTime){:1.exchangeTime,}:1.mpOpenId)")
	public Identity add(ActRunmanRecord actRunmanRecord);
	
	@SQL("update t_act_runman_record set #if(:1.prizeName){prizeName=:1.prizeName,}#if(:1.prizeType){prizeType=:1.prizeType,}#if(:1.prizeImgUrl){prizeImgUrl=:1.prizeImgUrl,}" +
			"#if(:1.tel){tel=:1.tel,}#if(:1.username){username=:1.username,}#if(:1.bestScore){bestScore=:1.bestScore,}" +
			"#if(:1.mailAddress){mailAddress=:1.mailAddress,}#if(:1.qq){qq=:1.qq,}#if(:1.wechatId){wechatId=:1.wechatId,}#if(:1.otherInfo){otherInfo=:1.otherInfo,}" +
			"#if(:1.opState){opState=:1.opState,}#if(:1.prizeTime){prizeTime=:1.prizeTime,}#if(:1.exchangeTime){exchangeTime=:1.exchangeTime,}activityId=:1.activityId where id = :1.id")
	public int update(ActRunmanRecord actRunmanRecord);
	
	@SQL("update t_act_runman_record set ##(:key) = :value where id = :1.id")
	public int updateByCriteria(ActRunmanRecord actRunmanRecord, @SQLParam("key") String key, @SQLParam("value") String value);
	
	@SQL("SELECT * FROM t_act_runman_record where activityId = :1 and prizeType != 0 order by prizeTime desc LIMIT :2,:3")
	public List<ActRunmanRecord> getRecord(Integer activityId, int startRow, int pageSize);
	
	@SQL("SELECT count(*) FROM t_act_runman_record where activityId = :1 and prizeType != 0")
	public int getRecordCount(Integer activityId);
	
	//排行榜
	@SQL("SELECT * FROM t_act_runman_record where activityId = :1 order by bestScore desc,id asc LIMIT :2,:3")
	public List<ActRunmanRecord> getRank(Integer activityId, int startRow, int pageSize);
	
	@SQL("SELECT count(*) FROM t_act_runman_record where activityId = :1")
	public int getRankCount(Integer activityId);
	
	@SQL("update t_act_runman_record set opState = :2 #if(:3){,exchangeTime = :3}#else{,exchangeTime = NULL} where id = :1")
	public int updateOpStatus(Integer id, String status, Date date);
	
	@SQL("select opState from t_act_runman_record where id = :1")
	public String findOpStatus(Integer id);
	
	@SQL("select * from t_act_runman_record where activityId = :1 and prizeType != 0 order by prizeTime asc LIMIT :2,:3")
	public List<ActRunmanRecord> findAll(Integer activityId, int startRow, int pageSize);
	
	@SQL("select count(*) from t_act_runman_record where activityId = :1 and prizeType != 0")
	public int countAllPrizeRecord(Integer activityId);
	
	@SQL("select * from t_act_runman_record where activityId = :1 order by bestScore desc,id asc LIMIT :2,:3")
	public List<ActRunmanRecord> findAllByScore(Integer activityId, int startRow, int pageSize);
	
	@SQL("select count(*) from t_act_runman_record where activityId = :1")
	public int countAllScore(Integer activityId);
	
	@SQL("select count(*) from t_act_runman_record where activityId = :1 and prizeType = :2 and !ISNULL(prizeTime)")
	public int countPrizeNumber(Integer activityId, Integer prizeType);
	
	@SQL("update t_act_runman_record set bestScore = :2 where id = :1")
	public int updateBestScore(Integer id, Integer score);

}
