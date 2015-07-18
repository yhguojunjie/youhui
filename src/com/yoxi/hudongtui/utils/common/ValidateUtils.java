package com.yoxi.hudongtui.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具类
 * 
 * 
 */
public class ValidateUtils {

	/**
	 * 验证邮箱地址是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码是否正确
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {

		Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");

		Matcher m = p.matcher(mobiles);

		return m.matches();
	}

	/**
	 * 校验日期是否正确
	 * @param date
	 * @param format
	 * @return
	 */
	public static boolean validateDateString(String date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			simpleDateFormat.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(ValidateUtils.isEmail("3449890@qq.com"));
		System.out.println(ValidateUtils.isMobileNO("14759259699"));
		System.out.println(ValidateUtils.isMobileNO("12016155153"));
	}
}
