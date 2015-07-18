package com.yoxi.hudongtui.exception;
/**
 * http认证异常
 *
 */
public class AuthenticateException extends Exception {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 错误代码
	 */
	private int errorCode;
	
	public AuthenticateException() {
		super();
	}
	
	public AuthenticateException(int errorCode,String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public AuthenticateException(int errorCode,String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public AuthenticateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AuthenticateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}	

}
