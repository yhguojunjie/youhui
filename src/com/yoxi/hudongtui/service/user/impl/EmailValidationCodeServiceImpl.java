package com.yoxi.hudongtui.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.user.EmailValidationCodeDAO;
import com.yoxi.hudongtui.model.user.EmailValidationCode;
import com.yoxi.hudongtui.service.user.EmailValidationCodeService;
import com.yoxi.hudongtui.vo.user.EmailValidationCodeExsitVO;

/**
 * 
 * 邮件找回密码
 * 
 * @author gjj
 * 
 *         2015-3-20
 */
@Service("emailValidationService")
public class EmailValidationCodeServiceImpl implements
		EmailValidationCodeService {

	@Autowired
	private EmailValidationCodeDAO emailValidationCodeDAO;

	@Override
	public Integer save(EmailValidationCode emailValidationCode) {
		return emailValidationCodeDAO.add(emailValidationCode).intValue();
	}

	/**
	 * 更新用户常用户字段
	 * 
	 * @param EmailValidationCode
	 * @return
	 */
	@Override
	public Integer updataEmailValidationCodeSet(
			EmailValidationCode emailValidationCode) {
		return emailValidationCodeDAO.update(emailValidationCode);
	}

	/**
	 * 删除好友
	 * 
	 * @param EmailValidationCodeId
	 * @param removeIds
	 * @throws Exception
	 */
	public void removeEmailValidationCode(String userId) throws Exception {
		emailValidationCodeDAO.removeEmailValidationCode(userId);
	}

	/**
	 * 
	 */
	@Override
	public EmailValidationCode getByStr(String getStr) throws Exception {
		return emailValidationCodeDAO.getByStr(getStr);
	}

	/**
	 * 
	 */
	@Override
	public EmailValidationCode findByUserId(String userId) throws Exception {
		return emailValidationCodeDAO.findByUserId(userId);
	}

	/**
	 * 
	 */
	@Override
	public EmailValidationCodeExsitVO findExistByuserId(String userId)
			throws Exception {
		return emailValidationCodeDAO.findExistByuserId(userId);
	}

}
