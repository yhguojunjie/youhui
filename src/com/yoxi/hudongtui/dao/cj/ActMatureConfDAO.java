package com.yoxi.hudongtui.dao.cj;

import com.yoxi.hudongtui.model.cj.ActMatureConf;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

/**
 * 红包熟了
 * 
 * @author jjb
 *
 *2015-1-11
 *
 */
@DAO
public interface ActMatureConfDAO {

	/**
	 * 新增
	 * @param actMatureConf
	 * @return
	 */
	@SQL("insert into t_act_mature_conf (activityId,#if(:1.title){title,}#if(:1.shareImgUrl){shareImgUrl,}" +
	 "#if(:1.bAdvert){bAdvert,}#if(:1.advertImgUrl){advertImgUrl,}#if(:1.advertUrl){advertUrl,}#if(:1.shareTitle){shareTitle,}"+
	 "#if(:1.shareDescription){shareDescription,}#if(:1.startTime){startTime,}#if(:1.endTime){endTime,}#if(:1.needCount){needCount,}" +
	 "#if(:1.rule){rule,}#if(:1.exExplain){exExplain,}#if(:1.userinfo){userinfo,}#if(:1.coverUrl){coverUrl,}createTime) " +
	 "values (:1.activityId,#if(:1.title){:1.title,}#if(:1.shareImgUrl){:1.shareImgUrl,}" +
	 "#if(:1.bAdvert){:1.bAdvert,}#if(:1.advertImgUrl){:1.advertImgUrl,}#if(:1.advertUrl){:1.advertUrl,}#if(:1.shareTitle){:1.shareTitle,}" +
	 "#if(:1.shareDescription){:1.shareDescription,}#if(:1.startTime){:1.startTime,}#if(:1.endTime){:1.endTime,}#if(:1.needCount){:1.needCount,}" +
	 "#if(:1.rule){:1.rule,}#if(:1.exExplain){:1.exExplain,}#if(:1.userinfo){:1.userinfo,}#if(:1.coverUrl){:1.coverUrl,}:1.createTime)")
	public Identity add(ActMatureConf actMatureConf);
	
	/**
	 * 按指定属性修改字段
	 * @param actMatureConf
	 * @param key
	 * @param value
	 * @return
	 */
	@SQL("update t_act_mature_conf set ##(:key) = :value where id = :1.id")
	public int updateByCriteria(ActMatureConf actMatureConf, @SQLParam("key") String key, @SQLParam("value") String value);
	
	/**
	 * 修改实体
	 * @param actMatureConf
	 * @return
	 */
	@SQL("update t_act_mature_conf set id = :1.id, activityId = :1.activityId, title = :1.title, " +
			"shareImgUrl = :1.shareImgUrl,bAdvert = :1.bAdvert,advertImgUrl = :1.advertImgUrl,advertUrl = :1.advertUrl," +
			"shareTitle = :1.shareTitle, shareDescription = :1.shareDescription, startTime = :1.startTime, endTime = :1.endTime, " +
			"needCount = :1.needCount, rule = :1.rule, exExplain = :1.exExplain, userinfo = :1.userinfo,coverUrl = :1.coverUrl," +
			" createTime = :1.createTime where id = :1.id")
	public int update(ActMatureConf actMatureConf);
	
	/**
	 * 按activityId查找实体
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_mature_conf where activityId = :1")
	public ActMatureConf findByActivityId(Integer activityId);
	
	/**
	 * 按id查找实体
	 * @param id
	 * @return
	 */
	@SQL("select * from t_act_mature_conf where id = :1")
	public ActMatureConf findById(Integer id);
}
