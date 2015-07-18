package com.yoxi.hudongtui.service.statistic.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.cache.redis.ObjectRedisTemplate;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.service.plugin.impl.PluginActServiceImpl;
import com.yoxi.hudongtui.service.statistic.IPlugActStatService;
import com.yoxi.hudongtui.vo.plugin.PluginActVo;

/**
 * 
 * 2015-01-13
 * 
 * @author wql
 *
 * 使用Redis实现统计
 * 
 */

@Service
public class PlugActStatRedisServiceImpl implements IPlugActStatService {

	@Override
	public void actBrowserNumTodb() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actJoinNumTodb() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void statDataTodb() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getJoinNum(String actId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getBrowserNum(String actId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
 
//	private Log log = LogFactory.getLog(getClass());
//    	
//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate;
//	@Autowired
//	private PluginActServiceImpl pluginActService;
//	@Autowired
//	private PluginActDAO pluginActDAO;
//	
//	/**
//	 * 更新数据的活动数
//	 * @return
//	 * @throws Exception
//	 */
//	public void actBrowserNumTodb() throws Exception {
//		
//		ObjectRedisTemplate<Integer> template = new ObjectRedisTemplate<Integer>(
//				   redisTemplate.getConnectionFactory(), Integer.class);
//		
//		String getStr = " dataStatus ='0'";
//		List<PluginActVo> pluginActVoList = pluginActDAO.getByStr(getStr);
//		if(CollectionUtils.isNotEmpty(pluginActVoList)){
//			for(PluginActVo pluginActVo : pluginActVoList){
//				String key = "pluginAct_browserNum_"+pluginActVo.getId();
//				Integer browserNum = template.opsForValue().get(key);
//				if(browserNum != null){
//					if(browserNum > pluginActVo.getBrowseNum()){
//						String upstr = " browseNum = "+browserNum;
//						pluginActDAO.upByStr(pluginActVo.getId(), upstr);
////						if(flag > 0){
////							template.delete(key);
////						}
//					}
//				}
//			}
//		}
//	}
//
//	/**
//	 * 更新参与人数,独立IP到数据库
//	 * @throws Exception
//	 */
//	public void actJoinNumTodb() throws Exception {
//		
//		String getStr = " dataStatus ='0'";
//		List<PluginActVo> pluginActVoList = pluginActDAO.getByStr(getStr);
//		if(CollectionUtils.isNotEmpty(pluginActVoList)){
//			for(PluginActVo pluginActVo : pluginActVoList){
//				String key  = "pluginAct_joinNum_"+pluginActVo.getId();
//				Set afSet = redisTemplate.opsForSet().members(key);
//				if(afSet != null && afSet.size() != 0){
//					if(afSet.size() > pluginActVo.getJoinNum()){
//						String upstr = " joinNum = "+afSet.size();
//						pluginActDAO.upByStr(pluginActVo.getId(), upstr);
////						if(flag > 0){
////							template.delete(key);
////						}
//					}
//				}
//			}
//		}
//		
//	}
//	
//	/**
//	 * 更新活动统计数，包含浏览量和独立IP
//	 * @throws Exception
//	 */
//	public void statDataTodb()throws Exception{
//		
//		ObjectRedisTemplate<Integer> tplInt = new ObjectRedisTemplate<Integer>(
//				   redisTemplate.getConnectionFactory(), Integer.class);
//		
//		ObjectRedisTemplate<Set> tplSet = new ObjectRedisTemplate<Set>(
//				   redisTemplate.getConnectionFactory(), Set.class);
//		String getStr = " dataStatus = '0' and NOW() between startTime and endTime ";
//		List<PluginActVo> pluginActVoList = pluginActDAO.getByStr(getStr);
//		if(CollectionUtils.isNotEmpty(pluginActVoList)){
//			for(PluginActVo pluginActVo : pluginActVoList){
//				//获取浏览量数据统计
//				String browserKey  = "pluginAct_browserNum_"+pluginActVo.getId();
//				Integer browserNum = tplInt.opsForValue().get(browserKey);
//				//获取独立IP数据统计 
//				String joinKey  = "pluginAct_joinNum_"+pluginActVo.getId();
//				Set afSet = tplSet.opsForValue().get(joinKey);
//				
//				if(CollectionUtils.isNotEmpty(afSet) && (afSet.size() > pluginActVo.getJoinNum())){
//					if(browserNum != null && (browserNum > pluginActVo.getBrowseNum())){
//						String upstr = " joinNum = "+afSet.size()+", browseNum = "+browserNum;
//						pluginActDAO.upByStr(pluginActVo.getId(), upstr);
//						continue;
//					}else{
//						String upstr = " joinNum = "+afSet.size();
//						pluginActDAO.upByStr(pluginActVo.getId(), upstr);
//					}
//					
//				}
//				
//				if(browserNum != null && (browserNum > pluginActVo.getBrowseNum())){
//					if(CollectionUtils.isNotEmpty(afSet) && (afSet.size() > pluginActVo.getJoinNum())){
//						String upstr = " joinNum = "+afSet.size()+", browseNum = "+browserNum;
//						pluginActDAO.upByStr(pluginActVo.getId(), upstr);
//						continue;
//					}else{
//						String upstr = " browseNum = "+browserNum;
//						pluginActDAO.upByStr(pluginActVo.getId(), upstr);
//					}
//				}
//			}
//		}
//		
//	}
//
//	@Override
//	public Integer getJoinNum(String actId) throws Exception {
//		ObjectRedisTemplate<Set> tplSet = new ObjectRedisTemplate<Set>(
//				   redisTemplate.getConnectionFactory(), Set.class);
//		String key  = "pluginAct_joinNum_"+actId;
//		Set afSet = tplSet.opsForValue().get(key);
//		return afSet.size();
//	}
//
//	@Override
//	public Integer getBrowserNum(String actId) throws Exception {
//		ObjectRedisTemplate<Integer> template = new ObjectRedisTemplate<Integer>(
//				   redisTemplate.getConnectionFactory(), Integer.class);
//		String key = "pluginAct_browserNum_"+actId;
//		Integer browserNum = template.opsForValue().get(key);
//		return browserNum;
//	}
//	
}
