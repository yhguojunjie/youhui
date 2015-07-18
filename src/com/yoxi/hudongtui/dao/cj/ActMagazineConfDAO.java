package com.yoxi.hudongtui.dao.cj;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.ActMagazineConf;

/**
 * 
 * 微场景配置
 * 
 * @author jjb
 *
 * 2014-11-14
 * 
 */
@DAO
public interface ActMagazineConfDAO {

	/**
	 * 新增微场景
	 * @param actWeiChangJinConf
	 * @return
	 */
	@SQL("insert into t_act_magazine_conf (activityId,#if(:1.name){name,}#if(:1.shareImgUrl){shareImgUrl,}"+
	 "#if(:1.shareTitle){shareTitle,}#if(:1.shareDescription){shareDescription,}#if(:1.shareLink){shareLink,}" +
	 "#if(:1.effect){effect,}#if(:1.bgMusic){bgMusic,}#if(:1.bgMusicUrl){bgMusicUrl,}#if(:1.iconEffect){iconEffect,}"+
	 "#if(:1.content){content,}#if(:1.startTime){startTime,}#if(:1.endTime){endTime,}createTime) " +
	 "values (:1.activityId,#if(:1.name){:1.name,}#if(:1.shareImgUrl){:1.shareImgUrl,}#if(:1.shareTitle){:1.shareTitle,}#if(:1.shareDescription){:1.shareDescription,}" +
	 "#if(:1.shareLink){:1.shareLink,}#if(:1.effect){:1.effect,}#if(:1.bgMusic){:1.bgMusic,}" +
	 "#if(:1.bgMusicUrl){:1.bgMusicUrl,}#if(:1.iconEffect){:1.iconEffect,}#if(:1.content){:1.content,}" +
	 "#if(:1.startTime){:1.startTime,}#if(:1.endTime){:1.endTime,}:1.createTime)")
	public Identity add(ActMagazineConf actMagazineConf);
	
	/**
	 * 按指定属性修改字段
	 * @param actMagazineConf
	 * @param key
	 * @param value
	 * @return
	 */
	@SQL("update t_act_magazine_conf set ##(:key) = :value where id = :1.id")
	public int updateByCriteria(ActMagazineConf actMagazineConf, @SQLParam("key") String key, @SQLParam("value") String value);
	
	/**
	 * 修改实体
	 * @param actMagazineConf
	 * @return
	 */
	@SQL("update t_act_magazine_conf set id = :1.id, activityId = :1.activityId, name = :1.name, shareImgUrl = :1.shareImgUrl," +
			" shareTitle = :1.shareTitle, shareDescription = :1.shareDescription, shareLink = :1.shareLink, " +
			"effect = :1.effect, bgMusic = :1.bgMusic, bgMusicUrl = :1.bgMusicUrl, iconEffect = :1.iconEffect, " +
			"content = :1.content, startTime = :1.startTime, endTime = :1.endTime, createTime = :1.createTime where id = :1.id")
	public int update(ActMagazineConf actMagazineConf);
	
	/**
	 * 按activityId查找实体
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_magazine_conf where activityId = :1")
	public ActMagazineConf findByActivityId(Integer activityId);
	
	/**
	 * 按id查找实体
	 * @param id
	 * @return
	 */
	@SQL("select * from t_act_magazine_conf where id = :1")
	public ActMagazineConf findById(Integer id);
}
