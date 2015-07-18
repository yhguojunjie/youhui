package com.yoxi.hudongtui.constants;

/**
 * 
 * memcahed 定义常量
 *
 * @author wql
 *
 * @Date 2015年3月20日
 *
 */
public class MemcachedConstans {
	
	//memcached 缓存微信公众平台用户信息key前缀字符串
	public static final String WX_MP_USER_KEY = "hudongtui_mpopenid_";
	public static final int WX_MP_USER_EXPIRED = 24*60*60;
	
	//代理商信息缓存
	public static final String AGENTINFO_KEY = "hudt_pc_agentInfo_";
	public static final int AGENTINFO_KEY_EXPIRED = 5*60;
	
	//首页品牌信息缓存
	public static final String BRAND_INDEX_KEY = "hudt_pc_index_brand";
	public static final int BRAND_INDEX_EXPIRED = 60;
	
	//首页品牌总数信息缓存
	public static final String BRANDCOUNT_INDEX_KEY = "hudt_pc_index_brandCount";
	public static final int BRANDCOUNT_INDEX_EXPIRED = 60;
}
