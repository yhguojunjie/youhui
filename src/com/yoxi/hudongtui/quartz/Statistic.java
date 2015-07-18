package com.yoxi.hudongtui.quartz;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yoxi.hudongtui.service.statistic.IPlugActStatService;

/**
 * 
 * 计数相关
 * 
 * @author wql
 * 
 * 2015-01-07
 *
 */
public class Statistic {
	
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private IPlugActStatService PlugActStatRedisServiceImpl;
	
	
	/**
	 * 更数活动统计数据到DB
	 * @return
	 * @throws Exception
	 */
	public void statDataTodb()throws Exception{
		log.info("***********活动计数作业开始***************");
		PlugActStatRedisServiceImpl.statDataTodb();
		log.info("***********活动计数作业结束***************");
	}

}
