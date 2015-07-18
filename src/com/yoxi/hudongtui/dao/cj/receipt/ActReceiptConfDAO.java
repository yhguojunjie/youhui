package com.yoxi.hudongtui.dao.cj.receipt;

import com.yoxi.hudongtui.model.cj.receipt.ActReceiptConf;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

/**
 * 刮发票
 * 
 * @author jjb
 *
 * 2015-4-20
 *
 */
@DAO
public interface ActReceiptConfDAO {

	/**
	 * 新增
	 * @param actReceiptConf
	 * @return
	 */
	@SQL("insert into t_act_receipt_conf (activityId,#if(:1.name){name,}#if(:1.shareImgUrl){shareImgUrl,}" +
	 "#if(:1.bAdvert){bAdvert,}#if(:1.advertImgUrl){advertImgUrl,}#if(:1.advertUrl){advertUrl,}#if(:1.shareTitle){shareTitle,}"+
	 "#if(:1.shareDescription){shareDescription,}#if(:1.awards){awards,}#if(:1.startTime){startTime,}#if(:1.endTime){endTime,}" +
	 "#if(:1.sellerName){sellerName,}#if(:1.bUrlType){bUrlType,}#if(:1.followUrl){followUrl,}#if(:1.bInputPass){bInputPass,}#if(:1.password){password,}"
	 + "#if(:1.playTimes){playTimes,}#if(:1.bCanPlay){bCanPlay,}#if(:1.rule){rule,}#if(:1.exExplain){exExplain,}" +
	 "#if(:1.userinfo){userinfo,}#if(:1.coverUrl){coverUrl,}createTime) " +
	 "values (:1.activityId,#if(:1.name){:1.name,}#if(:1.shareImgUrl){:1.shareImgUrl,}" +
	 "#if(:1.bAdvert){:1.bAdvert,}#if(:1.advertImgUrl){:1.advertImgUrl,}#if(:1.advertUrl){:1.advertUrl,}#if(:1.shareTitle){:1.shareTitle,}" +
	 "#if(:1.shareDescription){:1.shareDescription,}#if(:1.awards){:1.awards,}#if(:1.startTime){:1.startTime,}#if(:1.endTime){:1.endTime,}" +
	 "#if(:1.sellerName){:1.sellerName,}#if(:1.bUrlType){:1.bUrlType,}#if(:1.followUrl){:1.followUrl,}#if(:1.bInputPass){:1.bInputPass,}#if(:1.password){:1.password,}"
	 + "#if(:1.playTimes){:1.playTimes,}#if(:1.bCanPlay){:1.bCanPlay,}#if(:1.rule){:1.rule,}#if(:1.exExplain){:1.exExplain,}" +
	 "#if(:1.userinfo){:1.userinfo,}#if(:1.coverUrl){:1.coverUrl,}:1.createTime)")
	public Identity add(ActReceiptConf actReceiptConf);

	/**
	 * 修改实体
	 * @param actReceiptConf
	 * @return
	 */
	@SQL("update t_act_receipt_conf set id = :1.id, activityId = :1.activityId, name = :1.name, " +
			"shareImgUrl = :1.shareImgUrl,bAdvert = :1.bAdvert,advertImgUrl = :1.advertImgUrl,advertUrl = :1.advertUrl," +
			"shareTitle = :1.shareTitle, shareDescription = :1.shareDescription, awards = :1.awards, startTime = :1.startTime, " +
			"endTime = :1.endTime, sellerName = :1.sellerName, bUrlType = :1.bUrlType,followUrl = :1.followUrl, bInputPass = :1.bInputPass,"
			+ "password = :1.password,playTimes = :1.playTimes,bCanPlay = :1.bCanPlay, rule = :1.rule, exExplain = :1.exExplain, userinfo = :1.userinfo," +
			" coverUrl = :1.coverUrl,createTime = :1.createTime where id = :1.id")
	public int update(ActReceiptConf actReceiptConf);
	
	/**
	 * 按activityId查找实体
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_receipt_conf where activityId = :1")
	public ActReceiptConf findByActivityId(Integer activityId);
	
	/**
	 * 按id查找实体
	 * @param id
	 * @return
	 */
	@SQL("select * from t_act_receipt_conf where id = :1")
	public ActReceiptConf findById(Integer id);
	
	
	@SQL("select awards from t_act_receipt_conf where activityId = :1")
	public String findAwards(Integer activityId);
}
