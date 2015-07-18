package com.yoxi.hudongtui.dao.cj;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.ActGreetingcardConf;

/**
 * 
 * 新年贺卡配置
 * 
 * @author jjb
 *
 * 2014-12-28
 * 
 */
@DAO
public interface ActGreetingcardConfDAO {

	/**
	 * 新增贺卡
	 * @param actGreetingcardConf
	 * @return
	 */
	@SQL("insert into t_act_greetingcard_conf (activityId,#if(:1.name){name,}#if(:1.shareLink){shareLink,}"+
	 "#if(:1.logoUrl){logoUrl,}#if(:1.shareImgUrl){shareImgUrl,}#if(:1.startTime){startTime,}" +
	 "#if(:1.endTime){endTime,}#if(:1.bUrlType){bUrlType,}#if(:1.followUrl){followUrl,}createTime)"+
	 "values (:1.activityId,#if(:1.name){:1.name,}#if(:1.shareLink){:1.shareLink,}" +
	 "#if(:1.logoUrl){:1.logoUrl,}#if(:1.shareImgUrl){:1.shareImgUrl,}#if(:1.startTime){:1.startTime,}" +
	 "#if(:1.endTime){:1.endTime,}#if(:1.bUrlType){:1.bUrlType,}#if(:1.followUrl){:1.followUrl,}:1.createTime)")
	public Identity add(ActGreetingcardConf actGreetingcardConf);
	
	/**
	 * 按指定属性修改字段
	 * @param actGreetingcardConf
	 * @param key
	 * @param value
	 * @return
	 */
	@SQL("update t_act_greetingcard_conf set ##(:key) = :value where id = :1.id")
	public int updateByCriteria(ActGreetingcardConf actGreetingcardConf, @SQLParam("key") String key, @SQLParam("value") String value);
	
	/**
	 * 修改实体
	 * @param actGreetingcardConf
	 * @return
	 */
	@SQL("update t_act_greetingcard_conf set id = :1.id, activityId = :1.activityId, name = :1.name," +
			" shareLink = :1.shareLink, logoUrl = :1.logoUrl, shareImgUrl = :1.shareImgUrl, " +
			"startTime = :1.startTime, endTime = :1.endTime, bUrlType = :1.bUrlType,followUrl = :1.followUrl,"
			+ " createTime = :1.createTime where id = :1.id")
	public int update(ActGreetingcardConf actGreetingcardConf);
	
	/**
	 * 按activityId查找实体
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_greetingcard_conf where activityId = :1")
	public ActGreetingcardConf findByActivityId(Integer activityId);
}
