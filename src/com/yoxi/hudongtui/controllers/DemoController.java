package com.yoxi.hudongtui.controllers;


import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.service.statistic.IPlugActStatService;
import com.yoxi.hudongtui.service.wx.IWxQrcodeService;
import com.yoxi.hudongtui.utils.common.QCodeUtil;

/**
 * 
 * 测试用
 * 
 * @author wql
 *
 */
public class DemoController {
	
	private Log log = LogFactory.getLog(getClass());
//
//	@Autowired
//	private MemcachedClient memcachedClient;
//	
//	@Get("memcachedSet")
//	public String memcachedSet(Invocation inv)throws Exception{
//		
////		List<User> testArrayList = new ArrayList<User>();
////		User user1 = new User();
////		user1.setAccount("dsf@182.com");
////		user1.setUserId(112222);
////		testArrayList.add(user1);
////		
////		User user2 = new User();
////		user2.setAccount("aaa@182.com");
////		user2.setUserId(112223);
////		testArrayList.add(user2);
//		
//		boolean ts = true;
//		
//		memcachedClient.set("testkey",20,ts);
//		
//		return null;
//	}
//	
//	@Get("memcachedGet")
//	public String memcachedGet(Invocation inv)throws Exception{
//		
////		List<User> userList = (ArrayList<User>)memcachedClient.get("testkey");
//		
//		Boolean is = (Boolean)memcachedClient.get("testkey");
//		
//		return "@json:" + is;
//	}
	
	@Autowired
	private IPlugActStatService PlugActStatRedisServiceImpl;

	/**
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@Get("getJoinNum")
	public String getJoinNum(@Param("actId") String actId)throws Exception{
		Integer joinNum = PlugActStatRedisServiceImpl.getJoinNum(actId);
		return "@json:" + "{\"JoinNum\":" + joinNum + "}";
	}
	
	
	/**
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@Get("getBrowserNum")
	public String getBrowserNum(@Param("actId") String actId)throws Exception{
		Integer browserNum = PlugActStatRedisServiceImpl.getBrowserNum(actId);
		return "@json:" + "{\"browserNum\":" + String.valueOf(browserNum) + "}";
	}
	
	/**
	 * 
	 * @param actId
	 * @return
	 * @throws Exception
	 */
	@Get("statDataTodb")
	public String statDataTodb(@Param("actId") String actId)throws Exception{
		log.info("***********活动计数作业开始***************");
		PlugActStatRedisServiceImpl.statDataTodb();
		log.info("***********活动计数作业结束***************");
		return null;
	}
	
	
	@Autowired
	private IWxQrcodeService wxQrcodeService;
	/***
	 * 场景二维码
	 * @param inv
	 * @param scene_str
	 * @throws Exception
	 */
	@Get("getlimitQcode")
	public void getQcode(Invocation inv, @Param("sceneStr")String sceneStr)throws Exception{
		BufferedImage bufImg = ImageIO.read(wxQrcodeService.getLimitQrcode(sceneStr));
		ImageIO.write(bufImg, "JPEG", inv.getResponse().getOutputStream());
	}
}
