package com.yoxi.hudongtui.constants;

/**
 * 错误代码描述枚举类
 * 
 * 
 */
public enum ErrorEnum implements ICodeEnum {

	SYSTEM_ERROR(10001, "系统错误"), DATABASE_ERROR(10002, "数据库错误"), AUTHENTICATE_ERROR(
			10003, "未经授权"), REQUEST_IS_ILLEGAL(10104, "非法请求"),PARAM_ERROR(10105,"参数错误"),

	PARAM_IS_NULL_ERROR(20001, "参数为空"), UPDATE_IS_FAILURE_ERROR(20002, "更新不成功"), DELETE_IS_FAILURE_ERROR(
			20003, "删除不成功"), USER_ISNOT_EXIST_ERROR(20101, "用户不存在"), USER_IS_EXIST_ERROR(
			20102, "用户已存在"), USER_ACCOUNT_NULL_ERROR(20103, "账号为空"), USER_PASSWORD_ERROR(
			20104, "密码错误"), USER_PASSWORD_NULL_ERROR(20105, "密码为空"), USER_ACCOUNT_RECHARGE_FAILURE_ERROR(
			20106, "用户充值失败"), USER_BALANCE_ISNOT_ENOUGH_ERROR(20107, "用户余额不足"),
			DATE_FORMAT_ERROR(30001, "日期格式错误"), EMAIL_FORMAT_ERROR(30002, "邮箱格式错误"), PHONENO_FORMAT_ERROR(30003, "手机号格式错误"), SYSTEM_CONFIG_NOTCONFIG(
							20301, "系统配置项未配置"), SYSTEM_CONFIG_ERROR(20302, "系统配置项配置错误"),
	WX_GETOPENID_FAIL(70001,"获取微信openId失败");
	private int code;
	private String desc;

	private ErrorEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getDesc() {
		return desc;
	}

}
