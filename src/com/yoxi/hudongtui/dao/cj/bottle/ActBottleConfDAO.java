package com.yoxi.hudongtui.dao.cj.bottle;

import com.yoxi.hudongtui.model.cj.bottle.ActBottleConf;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

/**
 * 漂流瓶
 * 
 * @author jjb
 *
 * 2015-4-27
 *
 */
@DAO
public interface ActBottleConfDAO {

	/**
	 * 新增
	 * @param actBottleConf
	 * @return
	 */
	@SQL("insert into t_act_bottle_conf (activityId,#if(:1.name){name,}#if(:1.shareImgUrl){shareImgUrl,}" +
	 "#if(:1.bAdvert){bAdvert,}#if(:1.advertImgUrl){advertImgUrl,}#if(:1.advertUrl){advertUrl,}#if(:1.shareTitle){shareTitle,}"+
	 "#if(:1.shareDescription){shareDescription,}#if(:1.startTime){startTime,}#if(:1.endTime){endTime,}" +
	 "#if(:1.bUrlType){bUrlType,}#if(:1.followUrl){followUrl,}#if(:1.playText){playText,}#if(:1.inviteText){inviteText,}"
	 + "#if(:1.joinText){joinText,}#if(:1.shareText){shareText,}#if(:1.prizePeople){prizePeople,}#if(:1.exExplain){exExplain,}" +
	 "#if(:1.userinfo){userinfo,}#if(:1.coverUrl){coverUrl,}createTime) " +
	 "values (:1.activityId,#if(:1.name){:1.name,}#if(:1.shareImgUrl){:1.shareImgUrl,}" +
	 "#if(:1.bAdvert){:1.bAdvert,}#if(:1.advertImgUrl){:1.advertImgUrl,}#if(:1.advertUrl){:1.advertUrl,}#if(:1.shareTitle){:1.shareTitle,}" +
	 "#if(:1.shareDescription){:1.shareDescription,}#if(:1.startTime){:1.startTime,}#if(:1.endTime){:1.endTime,}" +
	 "#if(:1.bUrlType){:1.bUrlType,}#if(:1.followUrl){:1.followUrl,}#if(:1.playText){:1.playText,}#if(:1.inviteText){:1.inviteText,}"
	 + "#if(:1.joinText){:1.joinText,}#if(:1.shareText){:1.shareText,}#if(:1.prizePeople){:1.prizePeople,}#if(:1.exExplain){:1.exExplain,}" +
	 "#if(:1.userinfo){:1.userinfo,}#if(:1.coverUrl){:1.coverUrl,}:1.createTime)")
	public Identity add(ActBottleConf actBottleConf);

	/**
	 * 修改实体
	 * @param actBottleConf
	 * @return
	 */
	@SQL("update t_act_bottle_conf set id = :1.id, activityId = :1.activityId, name = :1.name, " +
			"shareImgUrl = :1.shareImgUrl,bAdvert = :1.bAdvert,advertImgUrl = :1.advertImgUrl,advertUrl = :1.advertUrl," +
			"shareTitle = :1.shareTitle, shareDescription = :1.shareDescription, startTime = :1.startTime, " +
			"endTime = :1.endTime, bUrlType = :1.bUrlType,followUrl = :1.followUrl, playText = :1.playText,"
			+ "inviteText = :1.inviteText,joinText = :1.joinText,shareText = :1.shareText, prizePeople = :1.prizePeople, exExplain = :1.exExplain, userinfo = :1.userinfo," +
			" coverUrl = :1.coverUrl,createTime = :1.createTime where id = :1.id")
	public int update(ActBottleConf actBottleConf);
	
	/**
	 * 按activityId查找实体
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_bottle_conf where activityId = :1")
	public ActBottleConf findByActivityId(Integer activityId);
}
