package com.yoxi.hudongtui.utils.wx;


/**
 * 
 * 更新用户交互记录线程类
 * 
 * @author wangql
 * 
 *
 */
public class UserInteractThread implements Runnable {
	
	private String fromUserName;
	
	private String msgType;
	
//	private IWxUserBusService userBusService;
//	
//	public UserInteractThread(String fromUserName,String msgType,IWxUserBusService userBusService){  
//		this.fromUserName = fromUserName;
//		this.msgType = msgType;
//		this.userBusService = userBusService;
//	}
	
	@Override
	public void run() {
		//记录用户交互记录
		if(msgType != null && !"".equals(msgType)){
//			userBusService.saveOrUpdateMpWxInteract(fromUserName,msgType);
		}
	}

}
