package com.yoxi.hudongtui.utils.wx;


/**
 * 
 * 关注处理的线程类
 * 
 * @author wangql
 * 
 *
 */
public class ProcessUserSubThread implements Runnable {
	
	private String fromUserName;
	
	private int subStatus;
	
	private String msgType;
	
//	private IWxUserBusService userBusService;
	
//	public ProcessUserSubThread(String fromUserName,int subStatus,String msgType,IWxUserBusService userBusService){  
//		this.fromUserName = fromUserName;
//		this.subStatus = subStatus;
//		this.msgType = msgType;
//		this.userBusService = userBusService;
//	}
	
	@Override
	public void run() {
		//处理关注
//		userBusService.processUserSub(fromUserName, subStatus);
		//记录用户交互记录
		if(msgType != null && !"".equals(msgType)){
//			userBusService.saveOrUpdateMpWxInteract(fromUserName,msgType);
		}
	}

}
