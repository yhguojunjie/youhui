package com.yoxi.hudongtui.exception;
/**
 * 服务层异常
 *
 */
public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 错误代码
	 */
	private int errorCode;
	
	private String errorMsg;
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(int errorCode,String message) {
		super(message);
		this.errorCode = errorCode;
		this.errorMsg = message;
	}

	public ServiceException(int errorCode,String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.errorMsg = message;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
