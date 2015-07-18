package com.yoxi.hudongtui.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器
 * 
 * @author wql
 *
 * 2014-12-03
 */
public class PerfFilter implements Filter{
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		MyHttpServletResponseWrapper rep=new MyHttpServletResponseWrapper((HttpServletResponse)response);
		chain.doFilter(req, rep);
		if(req.getRequestURI().indexOf("/images/")==-1 && req.getRequestURI().indexOf("/js/")==-1 && req.getRequestURI().indexOf("/css/")==-1){
			if( (rep.getStatus()==404 || rep.getStatus()==500)){
				req.setAttribute("statusCode", rep.getStatus());
				req.setAttribute("errorURI", req.getRequestURI());
			}
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
}
