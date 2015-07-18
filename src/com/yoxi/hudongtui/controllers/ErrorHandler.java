package com.yoxi.hudongtui.controllers;

import net.paoding.rose.web.ControllerErrorHandler;
import net.paoding.rose.web.Invocation;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.yoxi.hudongtui.constants.ErrorEnum;
import com.yoxi.hudongtui.exception.AuthenticateException;
import com.yoxi.hudongtui.exception.ServiceException;


/**
 * 异常统一处理类
 *
 */
public class ErrorHandler implements ControllerErrorHandler {
	
	
	private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class);
	
	/**
	 * 授权认证异常
	 * @param inv
	 * @param ex
	 * @return
	 * @throws Throwable
	 */
	public Object onError(Invocation inv, AuthenticateException ex) throws Throwable {
	
		LOGGER.error(ex.getMessage(),ex);
		
		String uri = inv.getRequest().getRequestURI();
		
		if(uri.indexOf( "pc" ) > 0 || uri.indexOf( "cj/admin" ) > 0 ){
			
			return "pc/error.jsp";
		
		}else if(uri.indexOf( "wx" ) > 0){
			
			return "wx/error.jsp";
		
		}else if(uri.indexOf( "mobile" ) > 0){
					
		}else if(uri.indexOf( "cj" ) > 0){
			
			return "wx/error.jsp";
		}
		
		return "pc/error.jsp";
	}
	
	/**
	 * service 自定义异常
	 * @param inv
	 * @param ex
	 * @return
	 * @throws Throwable
	 */
	public Object onError(Invocation inv, ServiceException ex) throws Throwable {
		
		if(ex.getErrorCode() == ErrorEnum.WX_GETOPENID_FAIL.getCode()){
			LOGGER.error(ex.getErrorMsg());
			return "wx/error_later.jsp";
		}else{
			LOGGER.error(ex.getMessage(),ex);
		}
		
		String uri = inv.getRequest().getRequestURI();
		
		if(uri.indexOf( "pc" ) > 0 || uri.indexOf( "cj/admin" ) > 0 ){
			
			return "pc/error.jsp";
		
		}else if(uri.indexOf( "wx" ) > 0){
			
			return "wx/error.jsp";
		
		}else if(uri.indexOf( "mobile" ) > 0){
					
		}else if(uri.indexOf( "cj" ) > 0){
			
			return "wx/error_later.jsp";
		}
		
		return "pc/error.jsp";
	}
	
	/**
	 * 数据库访问异常
	 * @param inv
	 * @param ex
	 * @return
	 * @throws Throwable
	 */
	public Object onError(Invocation inv, DataAccessException ex) throws Throwable {
		
		LOGGER.error(ex.getMessage(),ex);
		
		String uri = inv.getRequest().getRequestURI();
		
		if(uri.indexOf( "pc" ) > 0 || uri.indexOf( "cj/admin" ) > 0 ){
			
			return "pc/error.jsp";
		
		}else if(uri.indexOf( "wx" ) > 0){
			
			return "wx/error.jsp";
		
		}else if(uri.indexOf( "mobile" ) > 0){
					
		}else if(uri.indexOf( "cj" ) > 0){
			
			return "wx/error_later.jsp";
		}
		
		return "pc/error.jsp";
	}

	/**
	 * 其它异常
	 */
	@Override
	public Object onError(Invocation inv, Throwable ex) throws Throwable {
	
		LOGGER.error(ex.getMessage(),ex);
		
		String uri = inv.getRequest().getRequestURI();
		
		if(uri.indexOf( "pc" ) > 0 || uri.indexOf( "cj/admin" ) > 0 ){
			
			return "pc/error.jsp";
		
		}else if(uri.indexOf( "wx" ) > 0){
			
			return "wx/error.jsp";
		
		}else if(uri.indexOf( "mobile" ) > 0){
					
		}else if(uri.indexOf( "cj" ) > 0){
			
			return "wx/error_later.jsp";
		}
		
		return "pc/error.jsp";
	}
	
}
