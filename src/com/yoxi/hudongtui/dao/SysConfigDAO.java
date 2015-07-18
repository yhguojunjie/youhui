package com.yoxi.hudongtui.dao;


import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;
import com.yoxi.hudongtui.model.SysConfig;

/**
 * 
 * 系统配置
 *
 * @author wql
 * 
 * 2014-11-18
 *
 */
@DAO
public interface SysConfigDAO {
	
	/**
	 * 添加
	 * @param sysConfig
	 * @return
	 */
	@SQL("insert into t_sysconfig (code,value,#if(:1.remark){remark,}createTime) "+
			"values (:1.code,:1.value,#if(:1.remark){:1.remark,}:1.createTime)")
	public Identity add(SysConfig sysConfig);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	@SQL("select * from t_sysconfig where id = :1")
	public SysConfig findById(Integer id);
	
	/**
	 * 按拼凑字符串查询
	 * @param getStr
	 * @return
	 */
	@SQL("SELECT * FROM t_sysconfig WHERE ##(:getStr) limit 0,1")
	public SysConfig getByStr(@SQLParam("getStr") String getStr);
	
	
	/**
	 * 按指定属性修改字段
	 * @param third
	 * @param key
	 * @param value
	 * @return
	 */
	@SQL("update t_sysconfig set ##(:key) = :value where id = :1.id")
	public int updateByCriteria(SysConfig sysConfig,@SQLParam("key") String key, @SQLParam("value") String value);
	
}
