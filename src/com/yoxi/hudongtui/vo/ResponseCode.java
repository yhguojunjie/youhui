package com.yoxi.hudongtui.vo;

import java.io.Serializable;

/**
 * 
 * 响应码
 * 
 * @author wql
 *
 * 2014-11-15
 */
public class ResponseCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1752371136188992662L;
	
	/** 响应代码 */
	protected String code;
	/**
	 * 响应结果信息
	 */
	protected String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
