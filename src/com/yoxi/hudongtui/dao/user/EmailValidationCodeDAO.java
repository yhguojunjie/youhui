package com.yoxi.hudongtui.dao.user;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.user.EmailValidationCode;
import com.yoxi.hudongtui.vo.user.EmailValidationCodeExsitVO;

/**
 * 
 * 邮件找回密码
 * 
 * @author gjj
 * 
 *         2015-3-20
 */
@DAO
public interface EmailValidationCodeDAO {

	/**
	 * 添加
	 * 
	 * @param emailValidationCode
	 * @return
	 */
	@SQL("insert into t_email_validationcode (#if(:1.userId){userId,}#if(:1.validationCode){validationCode,}#if(:1.outDate){outDate})"
			+ "values (#if(:1.userId){:1.userId,}#if(:1.validationCode){:1.validationCode,}#if(:1.outDate){:1.outDate})")
	public Identity add(EmailValidationCode emailValidationCode);

	/**
	 * 修改实体
	 * 
	 * @param emailValidationCode
	 * @return
	 */
	@SQL("update t_email_validationcode set userId = :1.userId, validataCode = :1.validataCode, outDate = :1.outDate where id = :1.id")
	public int update(EmailValidationCode emailValidationCode);

	/**
	 * 删除实体
	 * 
	 * @param userId
	 * @throws Exception
	 */
	@SQL("DELETE  FROM t_email_validationcode  where userId = :1")
	public void removeEmailValidationCode(String userId) throws Exception;

	/**
	 * 按拼凑字符串来查，确保返回记录为唯一
	 * 
	 * @param getstr
	 * @return
	 */
	@SQL("SELECT * FROM t_email_validationcode WHERE ##(:getStr) LIMIT 0,1")
	public EmailValidationCode getByStr(@SQLParam("getStr") String getStr);

	/**
	 * 按userId查找实体
	 * 
	 * @param userId
	 * @return
	 */
	@SQL("select * from t_email_validationcode where userId = :1")
	public EmailValidationCode findByUserId(String userId);

	/**
	 * 按账号查找该用户是否存在
	 * 
	 * @param account
	 * @return
	 */
	@SQL("select userId from t_email_validationcode where userId = :1")
	public EmailValidationCodeExsitVO findExistByuserId(String userId);

}
