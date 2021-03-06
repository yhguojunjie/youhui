package com.yoxi.hudongtui.dao.cj.vote;

import com.yoxi.hudongtui.model.cj.vote.ActVoteConf;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

/**
 * 投票
 * 
 * @author jjb
 *
 * 2015-3-15
 *
 */
@DAO
public interface ActVoteConfDAO {

	/**
	 * 新增
	 * @param actVoteConf
	 * @return
	 */
	@SQL("insert into t_act_vote_conf (activityId,#if(:1.name){name,}#if(:1.shareImgUrl){shareImgUrl,}" +
	 "#if(:1.bAdvert){bAdvert,}#if(:1.advertImgUrl){advertImgUrl,}#if(:1.advertUrl){advertUrl,}#if(:1.shareTitle){shareTitle,}"+
	 "#if(:1.shareDescription){shareDescription,}#if(:1.startTime){startTime,}#if(:1.endTime){endTime,}" +
	 "#if(:1.bUrlType){bUrlType,}#if(:1.followUrl){followUrl,}#if(:1.playPeople){playPeople,}#if(:1.rule){rule,}#if(:1.publicInfo){publicInfo,}" +
	 "#if(:1.hideInfo){hideInfo,}#if(:1.bgUrl){bgUrl,}createTime) " +
	 "values (:1.activityId,#if(:1.name){:1.name,}#if(:1.shareImgUrl){:1.shareImgUrl,}" +
	 "#if(:1.bAdvert){:1.bAdvert,}#if(:1.advertImgUrl){:1.advertImgUrl,}#if(:1.advertUrl){:1.advertUrl,}#if(:1.shareTitle){:1.shareTitle,}" +
	 "#if(:1.shareDescription){:1.shareDescription,}#if(:1.startTime){:1.startTime,}#if(:1.endTime){:1.endTime,}" +
	 "#if(:1.bUrlType){:1.bUrlType,}#if(:1.followUrl){:1.followUrl,}#if(:1.playPeople){:1.playPeople,}#if(:1.rule){:1.rule,}#if(:1.publicInfo){:1.publicInfo,}" +
	 "#if(:1.hideInfo){:1.hideInfo,}#if(:1.bgUrl){:1.bgUrl,}:1.createTime)")
	public Identity add(ActVoteConf actVoteConf);

	/**
	 * 修改实体
	 * @param actVoteConf
	 * @return
	 */
	@SQL("update t_act_vote_conf set id = :1.id, activityId = :1.activityId, name = :1.name, " +
			"shareImgUrl = :1.shareImgUrl,bAdvert = :1.bAdvert,advertImgUrl = :1.advertImgUrl,advertUrl = :1.advertUrl," +
			"shareTitle = :1.shareTitle, shareDescription = :1.shareDescription, startTime = :1.startTime, " +
			"endTime = :1.endTime, bUrlType = :1.bUrlType,followUrl = :1.followUrl, playPeople = :1.playPeople, rule = :1.rule, publicInfo = :1.publicInfo, "
			+ "hideInfo = :1.hideInfo, bgUrl = :1.bgUrl, createTime = :1.createTime where id = :1.id")
	public int update(ActVoteConf actVoteConf);
	
	/**
	 * 按activityId查找实体
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_vote_conf where activityId = :1")
	public ActVoteConf findByActivityId(Integer activityId);
}
