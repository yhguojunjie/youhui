package com.yoxi.hudongtui.dao.cj.fingerprint;

import com.yoxi.hudongtui.model.cj.fingerprint.ActFingerprintConf;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

/**
 * 指纹配对
 * 
 * @author jjb
 *
 * 2015-2-4
 *
 */
@DAO
public interface ActFingerprintConfDAO {

	/**
	 * 新增
	 * @param actFingerprintConf
	 * @return
	 */
	@SQL("insert into t_act_fingerprint_conf (activityId,#if(:1.name){name,}#if(:1.shareImgUrl){shareImgUrl,}" +
	 "#if(:1.bAdvert){bAdvert,}#if(:1.advertImgUrl){advertImgUrl,}#if(:1.advertUrl){advertUrl,}#if(:1.shareTitle){shareTitle,}"+
	 "#if(:1.shareDescription){shareDescription,}#if(:1.bShowList){bShowList,}#if(:1.awards){awards,}#if(:1.startTime){startTime,}#if(:1.endTime){endTime,}" +
	 "#if(:1.probability){probability,}#if(:1.bUrlType){bUrlType,}#if(:1.followUrl){followUrl,}#if(:1.bCanPlay){bCanPlay,}#if(:1.rule){rule,}#if(:1.exExplain){exExplain,}" +
	 "#if(:1.relation){relation,}#if(:1.prizeRelation){prizeRelation,}#if(:1.coverUrl){coverUrl,}#if(:1.bgUrl){bgUrl,}#if(:1.userinfo){userinfo,}createTime) " +
	 "values (:1.activityId,#if(:1.name){:1.name,}#if(:1.shareImgUrl){:1.shareImgUrl,}" +
	 "#if(:1.bAdvert){:1.bAdvert,}#if(:1.advertImgUrl){:1.advertImgUrl,}#if(:1.advertUrl){:1.advertUrl,}#if(:1.shareTitle){:1.shareTitle,}" +
	 "#if(:1.shareDescription){:1.shareDescription,}#if(:1.bShowList){:1.bShowList,}#if(:1.awards){:1.awards,}#if(:1.startTime){:1.startTime,}#if(:1.endTime){:1.endTime,}" +
	 "#if(:1.probability){:1.probability,}#if(:1.bUrlType){:1.bUrlType,}#if(:1.followUrl){:1.followUrl,}#if(:1.bCanPlay){:1.bCanPlay,}#if(:1.rule){:1.rule,}#if(:1.exExplain){:1.exExplain,}" +
	 "#if(:1.relation){:1.relation,}#if(:1.prizeRelation){:1.prizeRelation,}#if(:1.coverUrl){:1.coverUrl,}#if(:1.bgUrl){:1.bgUrl,}#if(:1.userinfo){:1.userinfo,}:1.createTime)")
	public Identity add(ActFingerprintConf actFingerprintConf);

	/**
	 * 修改实体
	 * @param actFingerprintConf
	 * @return
	 */
	@SQL("update t_act_fingerprint_conf set id = :1.id, activityId = :1.activityId, name = :1.name, " +
			"shareImgUrl = :1.shareImgUrl,bAdvert = :1.bAdvert,advertImgUrl = :1.advertImgUrl,advertUrl = :1.advertUrl," +
			"shareTitle = :1.shareTitle, shareDescription = :1.shareDescription, bShowList = :1.bShowList, awards = :1.awards, startTime = :1.startTime, " +
			"endTime = :1.endTime, probability = :1.probability,bUrlType = :1.bUrlType,followUrl = :1.followUrl, " +
			"bCanPlay = :1.bCanPlay, rule = :1.rule, exExplain = :1.exExplain, userinfo = :1.userinfo," +
			" relation = :1.relation,prizeRelation = :1.prizeRelation,coverUrl = :1.coverUrl, bgUrl = :1.bgUrl,createTime = :1.createTime where id = :1.id")
	public int update(ActFingerprintConf actFingerprintConf);
	
	/**
	 * 按activityId查找实体
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_fingerprint_conf where activityId = :1")
	public ActFingerprintConf findByActivityId(Integer activityId);
	
	/**
	 * 按id查找实体
	 * @param id
	 * @return
	 */
	@SQL("select * from t_act_fingerprint_conf where id = :1")
	public ActFingerprintConf findById(Integer id);
	
	
	@SQL("select awards from t_act_fingerprint_conf where activityId = :1")
	public String findAwards(Integer activityId);
	
}
