package com.yoxi.hudongtui.service.user;

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
public interface EmailValidationCodeService {

	/**
	 * 保存
	 * 
	 * @param emailValidationCode
	 * @return
	 */
	public Integer save(EmailValidationCode emailValidationCode);

	/**
	 * 更新邮件找回常用字段
	 * 
	 * @param user
	 * @return
	 */
	public Integer updataEmailValidationCodeSet(
			EmailValidationCode emailValidationCode);

	/**
	 * 删除好友
	 * 
	 * @param emailValidationCodeId
	 * @param removeIds
	 * @throws Exception
	 */
	public void removeEmailValidationCode(String userId) throws Exception;

	public EmailValidationCode getByStr(String getStr) throws Exception;

	public EmailValidationCode findByUserId(String userId) throws Exception;

	public EmailValidationCodeExsitVO findExistByuserId(String userId)
			throws Exception;

}
