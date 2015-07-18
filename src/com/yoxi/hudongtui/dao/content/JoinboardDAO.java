package com.yoxi.hudongtui.dao.content;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.content.Joinboard;

/**
 * 
 * 加盟代理
 * 
 * @author gjj
 * 
 * @Date 2015年4月17日
 * 
 */
@DAO
public interface JoinboardDAO {

	/**
	 * 添加
	 * 
	 * @param joinboard
	 * @return
	 */
	@SQL("insert into t_joinboard (#if(:1.qq){qq,}#if(:1.mobile){mobile,}#if(:1.email){email,}" + "#if(:1.contact){contact,}#if(:1.remark){remark,}" + "#if(:1.createTime){createTime})"
			+ "values (#if(:1.qq){:1.qq,}#if(:1.mobile){:1.mobile,}#if(:1.email){:1.email,}#if(:1.contact){:1.contact,}#if(:1.remark){:1.remark,}" + "#if(:1.createTime){:1.createTime})")
	public Identity add(Joinboard joinboard);

}
