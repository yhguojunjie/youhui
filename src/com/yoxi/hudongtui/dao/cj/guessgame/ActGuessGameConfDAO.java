package com.yoxi.hudongtui.dao.cj.guessgame;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.guessgame.ActGuessGameConf;

/**
 * 
 * 猜比赛
 * 
 * @author jjb
 *
 * 2015-3-24
 * 
 */
@DAO
public interface ActGuessGameConfDAO {

	/**
	 * 新增猜比赛
	 * @param actGuessGameConf
	 * @return
	 */
	@SQL("insert into t_act_guessgame_conf (activityId,#if(:1.name){name,}#if(:1.bAdvert){bAdvert,}" +
	 "#if(:1.advertImgUrl){advertImgUrl,}#if(:1.advertUrl){advertUrl,}#if(:1.shareImgUrl){shareImgUrl,}"+
	 "#if(:1.shareTitle){shareTitle,}#if(:1.shareDescription){shareDescription,}" +
	 "#if(:1.startTime){startTime,}#if(:1.endTime){endTime,}#if(:1.bUrlType){bUrlType,}#if(:1.followUrl){followUrl,}#if(:1.bJoinType){bJoinType,}" +
	 "#if(:1.rule){rule,}#if(:1.exExplain){exExplain,}#if(:1.userinfo){userinfo,}#if(:1.bgUrl){bgUrl,}createTime) " +
	 "values (:1.activityId,#if(:1.name){:1.name,}#if(:1.bAdvert){:1.bAdvert,}" +
	 "#if(:1.advertImgUrl){:1.advertImgUrl,}#if(:1.advertUrl){:1.advertUrl,}#if(:1.shareImgUrl){:1.shareImgUrl,}" +
	 "#if(:1.shareTitle){:1.shareTitle,}#if(:1.shareDescription){:1.shareDescription,}" +
	 "#if(:1.startTime){:1.startTime,}#if(:1.endTime){:1.endTime,}#if(:1.bUrlType){:1.bUrlType,}#if(:1.followUrl){:1.followUrl,}#if(:1.bJoinType){:1.bJoinType,}" +
	 "#if(:1.rule){:1.rule,}#if(:1.exExplain){:1.exExplain,}#if(:1.userinfo){:1.userinfo,}#if(:1.bgUrl){:1.bgUrl,}:1.createTime)")
	public Identity add(ActGuessGameConf actGuessGameConf);
	
	/**
	 * 修改实体
	 * @param actGuessGameConf
	 * @return
	 */
	@SQL("update t_act_guessgame_conf set activityId = :1.activityId, name = :1.name, " +
			"bAdvert = :1.bAdvert,advertImgUrl = :1.advertImgUrl,advertUrl = :1.advertUrl," +
			"shareImgUrl = :1.shareImgUrl,shareTitle = :1.shareTitle, shareDescription = :1.shareDescription,  " +
			"startTime = :1.startTime, endTime = :1.endTime, bUrlType = :1.bUrlType,followUrl = :1.followUrl," +
			"bJoinType = :1.bJoinType, rule = :1.rule, exExplain = :1.exExplain, " +
			"userinfo = :1.userinfo, bgUrl = :1.bgUrl, createTime = :1.createTime where id = :1.id")
	public int update(ActGuessGameConf actGuessGameConf);
	
	/**
	 * 按activityId查找实体
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_guessgame_conf where activityId = :1")
	public ActGuessGameConf findByActivityId(Integer activityId);
	
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@SQL("select bJoinType from t_act_guessgame_conf where activityId = :1")
	public String findJoinType(Integer activityId);
}
