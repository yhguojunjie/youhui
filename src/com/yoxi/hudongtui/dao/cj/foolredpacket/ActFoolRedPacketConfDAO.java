package com.yoxi.hudongtui.dao.cj.foolredpacket;

import com.yoxi.hudongtui.model.cj.foolredpacket.ActFoolRedPacketConf;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

/**
 * 
 * 愚人红包
 * 
 * @author jjb
 *
 * 2015-3-22
 * 
 */
@DAO
public interface ActFoolRedPacketConfDAO {

	/**
	 * 新增
	 * @param actFoolRedPacketConf
	 * @return
	 */
	@SQL("insert into t_act_foolredpacket_conf (activityId,#if(:1.name){name,}#if(:1.shareImgUrl){shareImgUrl,}"
			+ "#if(:1.advertUrl){advertUrl,}#if(:1.shareTitle){shareTitle,}"+
	 "#if(:1.shareDescription){shareDescription,}#if(:1.startTime){startTime,}#if(:1.endTime){endTime,}" +
	 "#if(:1.bUrlType){bUrlType,}#if(:1.followUrl){followUrl,}createTime) " +
	 "values (:1.activityId,#if(:1.name){:1.name,}#if(:1.shareImgUrl){:1.shareImgUrl,}"
	 + "#if(:1.advertUrl){:1.advertUrl,}#if(:1.shareTitle){:1.shareTitle,}" +
	 "#if(:1.shareDescription){:1.shareDescription,}#if(:1.startTime){:1.startTime,}#if(:1.endTime){:1.endTime,}" +
	 "#if(:1.bUrlType){:1.bUrlType,}#if(:1.followUrl){:1.followUrl,}:1.createTime)")
	public Identity add(ActFoolRedPacketConf actFoolRedPacketConf);

	/**
	 * 修改实体
	 * @param actFoolRedPacketConf
	 * @return
	 */
	@SQL("update t_act_foolredpacket_conf set activityId = :1.activityId, name = :1.name, shareImgUrl = :1.shareImgUrl," +
			"advertUrl = :1.advertUrl,shareTitle = :1.shareTitle, shareDescription = :1.shareDescription, startTime = :1.startTime, " +
			"endTime = :1.endTime, bUrlType = :1.bUrlType,followUrl = :1.followUrl, " +
			" createTime = :1.createTime where id = :1.id")
	public int update(ActFoolRedPacketConf actFoolRedPacketConf);
	
	/**
	 * 按activityId查找实体
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_foolredpacket_conf where activityId = :1")
	public ActFoolRedPacketConf findByActivityId(Integer activityId);
	
}
