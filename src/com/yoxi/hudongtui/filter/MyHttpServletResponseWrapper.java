package com.yoxi.hudongtui.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyHttpServletResponseWrapper extends HttpServletResponseWrapper {

	private int status;
	
	public MyHttpServletResponseWrapper(HttpServletResponse response) {
		super(response);
	}
	@Override  
    public void sendError(int sc) throws IOException {  
		this.status = sc;  
        super.sendError(sc);  
    }  
  
    @Override  
    public void sendError(int sc, String msg) throws IOException {  
    	this.status = sc;  
        super.sendError(sc, msg);  
    }  
    
    @Override
    public void setStatus(int sc, String msg) {
        super.setStatus(sc, msg);
        this.status = sc;
    }
    
	@Override
	 public void setStatus(int sc) {
	  this.status = sc;
	  super.setStatus(sc);
	 }

	 public int getStatus() {
		 return this.status;
	 }
	
}
