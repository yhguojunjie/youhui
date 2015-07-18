package com.yoxi.hudongtui.dao.cj.coupon;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.coupon.ActCouponConf;

/**
 * 礼券
 * 
 * @author jjb
 *
 * 2015-4-1
 *
 */
@DAO
public interface ActCouponConfDAO {

	/**
	 * 新增
	 * @param actCouponConf
	 * @return
	 */
	@SQL("insert into t_act_coupon_conf (activityId,#if(:1.name){name,}#if(:1.shareImgUrl){shareImgUrl,}" +
	 "#if(:1.bAdvert){bAdvert,}#if(:1.advertImgUrl){advertImgUrl,}#if(:1.advertUrl){advertUrl,}#if(:1.shareTitle){shareTitle,}"+
	 "#if(:1.shareDescription){shareDescription,}#if(:1.startTime){startTime,}#if(:1.endTime){endTime,}"
	 + "#if(:1.couponName){couponName,}#if(:1.couponImgUrl){couponImgUrl,}#if(:1.getAwardUrl){getAwardUrl,}" +
	 "#if(:1.bUrlType){bUrlType,}#if(:1.followUrl){followUrl,}#if(:1.rule){rule,}#if(:1.bgUrl){bgUrl,}createTime) " +
	 "values (:1.activityId,#if(:1.name){:1.name,}#if(:1.shareImgUrl){:1.shareImgUrl,}" +
	 "#if(:1.bAdvert){:1.bAdvert,}#if(:1.advertImgUrl){:1.advertImgUrl,}#if(:1.advertUrl){:1.advertUrl,}#if(:1.shareTitle){:1.shareTitle,}" +
	 "#if(:1.shareDescription){:1.shareDescription,}#if(:1.startTime){:1.startTime,}#if(:1.endTime){:1.endTime,}" +
	 "#if(:1.couponName){:1.couponName,}#if(:1.couponImgUrl){:1.couponImgUrl,}#if(:1.getAwardUrl){:1.getAwardUrl,}"
	 + "#if(:1.bUrlType){:1.bUrlType,}#if(:1.followUrl){:1.followUrl,}#if(:1.rule){:1.rule,}#if(:1.bgUrl){:1.bgUrl,}:1.createTime)")
	public Identity add(ActCouponConf actCouponConf);

	/**
	 * 修改实体
	 * @param actCouponConf
	 * @return
	 */
	@SQL("update t_act_coupon_conf set id = :1.id"
			+ ", activityId = :1.activityId, name = :1.name, " +
			"shareImgUrl = :1.shareImgUrl,bAdvert = :1.bAdvert,advertImgUrl = :1.advertImgUrl,advertUrl = :1.advertUrl," +
			"shareTitle = :1.shareTitle, shareDescription = :1.shareDescription,startTime = :1.startTime, " +
			"endTime = :1.endTime, couponName = :1.couponName,couponImgUrl = :1.couponImgUrl,getAwardUrl = :1.getAwardUrl,"
			+ "bUrlType = :1.bUrlType,followUrl = :1.followUrl, rule = :1.rule, bgUrl = :1.bgUrl,createTime = :1.createTime where id = :1.id")
	public int update(ActCouponConf actCouponConf);
	
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_coupon_conf where activityId = :1")
	public ActCouponConf findByActivityId(Integer activityId);
}
