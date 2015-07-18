package com.yoxi.hudongtui.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.user.Third;

/**
 * 
 * 第三用户登录信息
 * 
 * @author wql
 *
 * 2014-11-13
 * 
 */
@DAO
public interface ThirdDAO {

	/**
	 * 新增记录
	 * @param third
	 * @return
	 */
	@SQL("insert into t_third (userId,thirdId,#if(:1.accessToken){accessToken,}#if(:1.refreshToken){refreshToken,}" +
			"#if(:1.acessTokenExpireTime){acessTokenExpireTime,}createTime,updateTime,source) "+
			"values (:1.userId,:1.thirdId,#if(:1.accessToken){:1.accessToken,}#if(:1.refreshToken){:1.refreshToken,}" +
			"#if(:1.acessTokenExpireTime){:1.acessTokenExpireTime,}:1.createTime,:1.updateTime,:1.source)")
	public Identity add(Third third);
	
	/**
	 * 按指定属性修改字段
	 * @param third
	 * @param key
	 * @param value
	 * @return
	 */
	@SQL("update t_third set ##(:key) = :value where id = :1.id")
	public int updateByCriteria(Third third,@SQLParam("key") String key, @SQLParam("value") String value);
	
	/**
	 * 修改实体
	 * @param third
	 * @return
	 */
	@SQL("update t_third set userId = :1.userId, thirdId = :1.thirdId, accessToken = :1.accessToken, refreshToken = :1.refreshToken," +
		 "acessTokenExpireTime = :1.acessTokenExpireTime,createTime = :1.createTime, source = :1.source,updateTime = :1.updateTime where id = :1.id")
	public int update(Third third);
	
	/**
	 * 按第三方Id与来源字段查找
	 * @param thirdId
	 * @return
	 */
	@SQL("select * from t_third where thirdId = :1 #if(:2){and source = :2}")
	public Third findByThirdIdSource(String thirdId,String source);
	

}
