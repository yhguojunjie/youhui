package com.yoxi.hudongtui.dao.wx;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.wx.WxUserInfo;

/**
 * 微信用户信息
 * 
 * @author wql
 * 
 * 2014-11-18
 * 
 */
@DAO
public interface WxUserInfoDAO {
	
	/**
	 * 按用户Id查找
	 * @param userId
	 * @return
	 */
	@SQL("select * from t_wx_userinfo where userId = :1")
	public WxUserInfo findByUserId(Integer userId);
	
	
	/**
	 * 按unionid查找
	 * @param openId
	 * @return
	 */
	@SQL("select * from t_wx_userinfo where unionid = :1")
	public WxUserInfo findByUnionId(String unionId);
	
	/**
	 * 按某个属性查找，必须确保查找返回的记录只有一条
	 * @param key
	 * @param value
	 * @return
	 */
	@SQL("select * from t_wx_userinfo where ##(:key) = :value limit 0,1")
	public WxUserInfo findBykey(@SQLParam("key") String key,@SQLParam("value") String value);
	
	/**
	 * 新增
	 * @param mpWxUser
	 * @return
	 */
	@SQL("INSERT INTO t_wx_userinfo (#if(:1.userId){userId}#if(:1.mpOpenId){,mpOpenId}#if(:1.appOpenId){,appOpenId}" +
			"#if(:1.webOpenId){,webOpenId}#if(:1.subscribe){,subscribe}#if(:1.nickname){,nickname}" +
			"#if(:1.sex){,sex}#if(:1.city){,city}#if(:1.country){,country}#if(:1.province){,province}#if(:1.language){,language}" +
			"#if(:1.headimgurl){,headimgurl}#if(:1.subscribe_time){,subscribe_time}#if(:1.unsubscribe_time){,unsubscribe_time}" +
			"#if(:1.updateTime){,updateTime}#if(:1.unionid){,unionid}#if(:1.privilege){,privilege})"+
			"VALUES (#if(:1.userId){:1.userId}#if(:1.mpOpenId){,:1.mpOpenId}#if(:1.appOpenId){,:1.appOpenId}" +
			"#if(:1.webOpenId){,:1.webOpenId}#if(:1.subscribe){,:1.subscribe}#if(:1.nickname){,:1.nickname}"+
			"#if(:1.sex){,:1.sex}#if(:1.city){,:1.city}#if(:1.country){,:1.country}#if(:1.province){,:1.province}#if(:1.language){,:1.language}" +
			"#if(:1.headimgurl){,:1.headimgurl}#if(:1.subscribe_time){,:1.subscribe_time}#if(:1.unsubscribe_time){,:1.unsubscribe_time}" +
			"#if(:1.updateTime){,:1.updateTime}#if(:1.unionid){,:1.unionid}#if(:1.privilege){,:1.privilege})")
	public Identity add(WxUserInfo wxUserInfo);
	
	/**
	 * 按属性更新
	 * @param mpWxUser
	 * @param key
	 * @param value
	 * @return
	 */
	@SQL("update t_wx_userinfo set ##(:key) = :value where userId = :1.userId")
	public int updateByCriteria(WxUserInfo wxUserInfo,@SQLParam("key") String key, @SQLParam("value") String value); 
	
	
	/**
	 * 更新实体
	 * @param mpWxUser
	 * @return
	 */
	@SQL("update t_wx_userinfo set mpOpenId = :1.mpOpenId,appOpenId = :1.appOpenId,webOpenId = :1.webOpenId,subscribe = :1.subscribe," +
			"nickname = :1.nickname,sex = :1.sex, city = :1.city, country = :1.country, province = :1.province,language = :1.language," +
			"headimgurl = :1.headimgurl,subscribe_time = :1.subscribe_time,updateTime = :1.updateTime," +
			"unsubscribe_time = :1.unsubscribe_time,privilege = :1.privilege,unionid = :1.unionid where userId = :1.userId")
	public int update(WxUserInfo wxUserInfo);
	
	/**
	 * 按拼凑字符串更新
	 * @param pluginId
	 * @param upstr
	 * @return
	 */
	@SQL("update t_wx_userinfo set ##(:upstr) where userId = :1")
	public int upByStr(Integer userId, @SQLParam("upstr") String upstr);
	
	/**
	 * 判断系统用户是否绑定了微信账号
	 * @param userId
	 * @return 查找返回的主键Id
	 */
	@SQL("select userId from t_wx_userinfo where userId = :1 LIMIT 0,1")
	public Integer userExist(Integer userId);

}
