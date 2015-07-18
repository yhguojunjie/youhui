package com.yoxi.hudongtui.dao.cj;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.ActGivegiftConf;

@DAO
public interface ActGivegiftConfDAO {

	/**
	 * 
	 * @param actGivegiftConf
	 * @return
	 */
	@SQL("insert into t_act_givegift_conf (activityId, #if(:1.title){title,}#if(:1.advertImgUrl){advertImgUrl,}" +
			"#if(:1.advertUrl){advertUrl,}#if(:1.shareImgUrl){shareImgUrl,}#if(:1.shareTitle){shareTitle,}" +
			"#if(:1.shareDescription){shareDescription,}#if(:1.setContent){setContent,}#if(:1.startTime){startTime,}" +
			"#if(:1.endTime){endTime,}#if(:1.bUrlType){bUrlType,}#if(:1.followUrl){followUrl,}#if(:1.couponName){couponName,}#if(:1.rule){rule,}#if(:1.bExchange){bExchange,}#if(:1.bgUrl){bgUrl,}createTime) " +
			"values (:1.activityId,#if(:1.title){:1.title,}#if(:1.advertImgUrl){:1.advertImgUrl,}" +
			"#if(:1.advertUrl){:1.advertUrl,}#if(:1.shareImgUrl){:1.shareImgUrl,}#if(:1.shareTitle){:1.shareTitle,}" +
			"#if(:1.shareDescription){:1.shareDescription,}#if(:1.setContent){:1.setContent,}#if(:1.startTime){:1.startTime,}" +
			"#if(:1.endTime){:1.endTime,}#if(:1.bUrlType){:1.bUrlType,}#if(:1.followUrl){:1.followUrl,}#if(:1.couponName){:1.couponName,}#if(:1.rule){:1.rule,}#if(:1.bExchange){:1.bExchange,}#if(:1.bgUrl){:1.bgUrl,}:1.createTime)")
	public Identity add(ActGivegiftConf actGivegiftConf);
	
	/**
	 * 
	 * @param actGivegiftConf
	 * @param key
	 * @param value
	 * @return
	 */
	@SQL("update t_act_givegift_conf set ##(:key) = :value where id = :1.id")
	public int updateByCriteria(ActGivegiftConf actGivegiftConf, @SQLParam("key") String key, @SQLParam("value") String value);
	
	/**
	 * 
	 * @param actGivegiftConf
	 * @return
	 */
	@SQL("update t_act_givegift_conf set activityId = :1.activityId, title = :1.title, advertImgUrl = :1.advertImgUrl,advertUrl = :1.advertUrl, shareImgUrl = :1.shareImgUrl, shareTitle = :1.shareTitle, " +
			"shareDescription = :1.shareDescription, setContent = :1.setContent, startTime = :1.startTime, endTime = :1.endTime, "
			+ "bUrlType = :1.bUrlType,followUrl = :1.followUrl,couponName = :1.couponName, rule = :1.rule,bExchange = :1.bExchange,bgUrl = :1.bgUrl," +
			"createTime = :1.createTime where id = :1.id")
	public int update(ActGivegiftConf actGivegiftConf);
	
	/**
	 * 按activityId查找实体
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_givegift_conf where activityId = :1")
	public ActGivegiftConf findByActivityId(Integer activityId);
	
	/**
	 * 按id查找实体
	 * @param id
	 * @return
	 */
	@SQL("select * from t_act_givegift_conf where id = :1")
	public ActGivegiftConf findById(Integer id);
}
