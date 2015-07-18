package com.yoxi.hudongtui.utils.common;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/********************************************************************************
 * <p>
 * Title: MailSender
 * </p>
 * <p>
 * Description: MailSender 发送邮件类
 * </p>
 * <p>
 * Copyright:Copyright(c)2015-2016
 * </p>
 * <p>
 * Company:互动推
 * </p>
 * 
 * @author guojunjie
 * @email
 * @version 1.0
 *******************************************************************************/
public class MailSender {
	private static final MailSender ME = new MailSender();

	private MailSender() {

	}

	public static MailSender getInstance() {
		return ME;
	}

	public boolean sentEmail(String content, String subject, String toAddress) {
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.exmail.qq.com");
		email.setAuthentication("service@hudongtui.com", "yoxi20143");
		email.setCharset("UTF-8");
		try {
			email.addTo(toAddress);// 要发送的地址
			email.setFrom("service@hudongtui.com");// 必须和Authentication使用的用户相同，否则失败
			email.setSubject(subject);// 要发送的主题
			email.setMsg(content);// 要发送的内容
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();

		}
		return true;
	}
}
