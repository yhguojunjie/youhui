package com.yoxi.hudongtui.controllers.pc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.controllers.GlobalRequired;
import com.yoxi.hudongtui.model.content.Banner;
import com.yoxi.hudongtui.model.content.Goodsclassificat;
import com.yoxi.hudongtui.model.content.WebShopVO;
import com.yoxi.hudongtui.model.plugin.Plugin;
import com.yoxi.hudongtui.service.agent.IAgentBusService;
import com.yoxi.hudongtui.service.agent.IAgentInfoService;
import com.yoxi.hudongtui.service.content.IAgentContSwitchService;
import com.yoxi.hudongtui.service.content.IBannerService;
import com.yoxi.hudongtui.service.content.IBrandService;
import com.yoxi.hudongtui.service.content.IPluginRecService;
import com.yoxi.hudongtui.service.plugin.IPluginActService;
import com.yoxi.hudongtui.service.plugin.IPluginBusService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.service.youhui.IGoodsclassificatService;
import com.yoxi.hudongtui.service.youhui.WebShopService;
import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.vo.agent.AgentInfoVO;
import com.yoxi.hudongtui.vo.plugin.PluginActCenterVo;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;
import com.yoxi.hudongtui.vo.plugin.PluginPicVo;
import com.yoxi.hudongtui.vo.user.DevVO;

/**
 * 
 * 首页相关controler
 * 
 * @author guojunjie
 * 
 * @Date 2015年8月2日
 * 
 */
@GlobalRequired
public class YouHuiController {
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private IPluginService pluginService;
	@Autowired
	private IGoodsclassificatService goodsclassificatService;

	@Autowired
	private WebShopService webShopService;

	@Autowired
	private IPluginBusService pluginBusService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IPluginActService pluginActService;
	@Autowired
	private IAgentInfoService agentInfoService;
	@Autowired
	private IAgentBusService agentBusService;
	@Autowired
	private IBrandService brandService;
	@Autowired
	private IBannerService bannerService;
	@Autowired
	private MemcachedClient memcachedClient;
	@Autowired
	private IPluginRecService pluginRecService;

	@Autowired
	private IAgentContSwitchService agentContSwitchService;

	/**
	 * 网站首页
	 * 
	 * @param typeCon
	 *            类型排序字段
	 * @param orderCon
	 *            排序字段
	 * @param currPage
	 *            当前页
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Get("/list")
	public String getPlugins(Invocation inv) throws Exception {

		// 1.分类与商城展示信息处理
		List<Goodsclassificat> goodsclassificatList = goodsclassificatService
				.findGoodsclassificat();
		inv.getRequest().setAttribute("goodsclassificatList",
				goodsclassificatList);
		// 1.代理商信息处理
		// AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
		// inv.getRequest().setAttribute("agentInfoVO", agentInfoVO);
		//
		// 2.品牌处理
		// List<Brand> brandList = null;
		// Integer brandCount;
		// String findStr =
		// " b.state = '0' AND a.hideState='0' AND b.agentId = " +
		// agentInfoVO.getId() +
		// " ORDER BY a.seq ASC,b.createTime DESC LIMIT 0,4 ";
		// String findStr2 = " agentId = " + agentInfoVO.getId() + " ";
		// String findStr3 = " agentId = " + agentInfoVO.getId() + " oder ";
		// brandList = brandService.getTopBrand(findStr3);
		// brandList = brandService.getAgentTopBrand(findStr);
		// brandCount = brandService.getAgentTotalCount(findStr2);

		// 缓存处理，暂不开启
		// String brandKey = MemcachedConstans.BRAND_INDEX_KEY;
		// String brandCountKey = MemcachedConstans.BRANDCOUNT_INDEX_KEY;
		// if(memcachedClient != null){
		// //入驻品牌处理
		// if(memcachedClient.get(brandKey) != null){
		// brandList = (ArrayList<Brand>)memcachedClient.get(brandKey);
		// }else{
		//
		// brandList = brandService.getTopBrand(findStr);
		// if(CollectionUtils.isNotEmpty(brandList)){
		// memcachedClient.set(brandKey, MemcachedConstans.BRAND_INDEX_EXPIRED,
		// brandList);
		// }
		// }
		// //品牌总数处理
		// if(memcachedClient.get(brandCountKey) != null){
		// brandCount = (Integer)memcachedClient.get(brandCountKey);
		// }else{
		// brandCount = brandService.getTotalCount();
		// if(brandCount != 0){
		// memcachedClient.set(brandCountKey,
		// MemcachedConstans.BRANDCOUNT_INDEX_EXPIRED, brandCount);
		// }
		// }
		// }else{
		// brandList = brandService.getTopBrand(findStr);
		// brandList = brandService.getAgentTopBrand(findStr);
		// brandCount = brandService.getAgentTotalCount(findStr2);
		// }

		// 3.banner处理
		int start = 0;
		int count = Globals.PC_INDEX_BANNER_NUM;

		List<Banner> bannerList = null;
		/*
		 * AgentContSwitch agentContSwitch =
		 * agentContSwitchService.getById(getAgentInfo.getId()); List<Banner>
		 * bannerList = null; if (agentContSwitch != null) { String bannerState
		 * = agentContSwitch.getBannerState(); if (bannerState.equals("0")) {
		 * String findBannerStr = " hideState=0 ORDER BY seq DESC LIMIT " +
		 * start + "," + count;
		 * 
		 * bannerList = bannerService.getBannerList(findBannerStr); } else {
		 */

		// String findBannerStr =
		// " a.hideState='0' and (a.auditstate='1' or a.auditstate='') and a.agentId = "
		// + agentInfoVO.getId() +
		// " ORDER BY a.seq ASC,a.createTime desc LIMIT " + start + ","
		// + count;
		// bannerList = bannerService.getAgentBannerList(findBannerStr);

		/*
		 * } } else { String findBannerStr =
		 * " hideState=0  ORDER BY seq DESC LIMIT " + start + "," + count;
		 * 
		 * bannerList = bannerService.getBannerList(findBannerStr); }
		 */

		// inv.getRequest().setAttribute("bannerList", bannerList);
		// inv.getRequest().setAttribute("brandList", brandList);
		// inv.getRequest().setAttribute("brandCount", brandCount);

		inv.getRequest()
				.getSession()
				.setAttribute(Globals.SESSION_LASTURL,
						inv.getRequest().getRequestURL());
		return "youhuiindex";
	}

	/**
	 * 
	 * @param inv
	 * @param start
	 * @throws Exception
	 */
	@Get("ajaxList")
	public String getWebShopAjaxList(Invocation inv, @Param("id") int id)
			throws Exception {

		List<WebShopVO> webShops = webShopService.findWebShopList(id);
		return "@json:" + JsonUtils.toJson(webShops);

	}

	/**
	 * 模板库页
	 * 
	 * @param typeCon
	 *            类型排序字段
	 * @param orderCon
	 *            排序字段
	 * @param currPage
	 *            当前页
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Get("/pluginList")
	public String getPluginList(Invocation inv,
			@Param("typeFlag") String typeFlag,
			@Param("orderFlag") String orderFlag,
			@Param("publishTime_Flag") String publishTime_Flag,
			@Param("buyNum_Flag") String buyNum_Flag,
			@Param("price_Flag") String price_Flag) throws Exception {

		inv.getRequest().setAttribute("orderFlag", orderFlag);
		inv.getRequest().setAttribute("publishTime_Flag", publishTime_Flag);
		inv.getRequest().setAttribute("buyNum_Flag", buyNum_Flag);
		inv.getRequest().setAttribute("price_Flag", price_Flag);

		// AgentInfoVO agentInfo = SessionUtil.getAgentInfo(inv.getRequest());
		// 推荐
		// String condition = " AND a.agentId = " + agentInfo.getId() +
		// " ORDER BY b.publishTime DESC ";
		// List<PluginRecVO> pluginRecList =
		// pluginRecService.findPluginList(agentInfo.getId(),condition,0,Globals.PC_INDEX_PLUGINREC_NUM);
		// inv.getRequest().setAttribute("pluginRecList",pluginRecList);

		// 1.设置参数
		// String xFlag = "0";
		// typeFlag 0表示全部，1.表示即买即用
		// orderFlag 1表示发布时间publishTime，2.表示销量buyNum,3.表示价格price
		/*
		 * if ("1".equals(orderFlag)) { xFlag = publishTime_Flag; } else if
		 * ("2".equals(orderFlag)) { xFlag = buyNum_Flag; } else if
		 * ("3".equals(orderFlag)) { xFlag = price_Flag; }
		 */
		// 0表示降序，1表示升序
		if (ValidateUtil.isEmpty(publishTime_Flag)
				&& ValidateUtil.isEmpty(buyNum_Flag)
				&& ValidateUtil.isEmpty(price_Flag)) {
			publishTime_Flag = "0";
			buyNum_Flag = "0";
			price_Flag = "0";
		}
		// 2.查询插件
		// String orderStrCon = pluginService.getAgentOrderFlag(orderFlag,
		// xFlag);
		// String cond =
		// " AND a.id NOT IN (SELECT d.pluginId FROM t_plugin_rec d) " +
		// orderStrCon;
		// List<PluginDetailVO> pluginList =
		// pluginService.getAgentPluginList(agentInfo.getId(), cond, 0,
		// Globals.PC_PLUGINLIST_NUM );
		// inv.getRequest().setAttribute("pluginList",pluginList);
		return "pluginIndex";
	}

	/**
	 * 根据插件ID查询插件图片明细信息
	 * 
	 * @param pluginId
	 *            pluginId 插件ID
	 * @return
	 * @throws Exception
	 */
	@Get("/pluginPics")
	public String getPluginePics(Invocation inv,
			@Param("pluginId") Integer pluginId) throws Exception {
		List<PluginPicVo> pluginPics = pluginService.findPluginePics(pluginId);
		String PluginPicJson = JsonUtils.toJson(pluginPics);
		return "@json:" + PluginPicJson;
	}

	/**
	 * 插件详情
	 * 
	 * @param inv
	 * @param plugingId
	 * @return
	 * @throws Exception
	 */
	@Get("/detail/{pluginId}")
	public String getPluginDetail(Invocation inv,
			@Param("pluginId") Integer pluginId) throws Exception {

		// inv.getRequest().getSession().setAttribute(Globals.SESSION_LASTURL,
		// inv.getRequest().getRequestURL());
		// 获取代理商信息
		AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
		// log.error("**********pluginDetail agentId*********="+agentInfoVO.getId());
		// 1.插件详细信息
		PluginDetailVO plugin = pluginService.getAgentPlugin(pluginId,
				agentInfoVO.getId());
		inv.getRequest().setAttribute("plugin", plugin);
		// 2.插件图片信息
		List<PluginPicVo> picVos = pluginService.findPluginePics(pluginId);
		inv.getRequest().setAttribute("picVos", picVos);

		// 3.该插件正在进行的活动
		String condition = " AND a.agentId = " + agentInfoVO.getId()
				+ " AND a.pluginId =" + pluginId
				+ " AND NOW() <= b.overdueTime " + " AND a.browseNum >= "
				+ Globals.ACT_BROWSERNUM_LIMIT + " ORDER BY a.browseNum DESC";
		List<PluginActCenterVo> pluginActVos = pluginActService
				.findActVos(condition);
		inv.getRequest().setAttribute("pluginActVos", pluginActVos);
		inv.getRequest().setAttribute("pluginActVoCount", pluginActVos.size());

		// 4.推荐插件
		String tjCondition = "WHERE a.status = '1' ORDER BY a.publishTime DESC LIMIT 0,5";
		List<PluginDetailVO> tjPluginVos = pluginService
				.findTjPlugines(tjCondition);
		Collections.shuffle(tjPluginVos);
		inv.getRequest().setAttribute("tjPluginVoCount", tjPluginVos.size());
		inv.getRequest().setAttribute("tjPluginVos", tjPluginVos);

		// 5.开发者信息
		DevVO dev = userService.getDevInfo(plugin.getUserId());
		inv.getRequest().setAttribute("dev", dev);

		// 6.开发者的其他插件
		String condition1 = " and a.userId = " + plugin.getUserId()
				+ " and a.id !=" + pluginId
				+ " ORDER BY a.publishTime DESC LIMIT 0,3";
		List<PluginDetailVO> otherPluginVos = pluginService
				.findPluginesByCondition(condition1);
		inv.getRequest().setAttribute("otherPluginVos", otherPluginVos);
		inv.getRequest().setAttribute("otherPluginVoCount",
				otherPluginVos.size());
		inv.getRequest().setAttribute("pluginId4query", pluginId);
		return "pluginDetailv2";
	}

	/**
	 * 插件详情页面推荐插件
	 * 
	 * @param inv
	 * @param plugingId
	 * @return
	 * @throws Exception
	 */
	@Get("/tjPlugins")
	public String getTjPlugins(Invocation inv,
			@Param("pluginId") Integer pluginId) throws Exception {
		// 推荐插件
		String tjCondition = "WHERE a.status = '1' ORDER BY a.publishTime DESC";
		List<PluginDetailVO> tjPluginVos = pluginService
				.findTjPlugines(tjCondition);
		Collections.shuffle(tjPluginVos);
		String tjPluginVosJson = JsonUtils.toJson(tjPluginVos.subList(0, 5));
		return "@json:" + tjPluginVosJson;
	}

	/**
	 * 试用插件
	 * 
	 * @param inv
	 * @param plugingId
	 *            插件Id
	 * @return json 1试用添加成功.2用户未登录.3 已购买,已试用. 4已购买,未试用. 5未购买,已试用
	 * @throws Exception
	 */
	@Post("tryout")
	public String tryout(Invocation inv, @Param("pluginId") Integer plugingId)
			throws Exception {
		// 判断是否登录
		if (SessionUtil.getUser(inv.getRequest()) == null) {
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("status", "2");
			return "@json:" + JsonUtils.toJson(dataMap);
		}
		Map<String, String> dataMap = pluginBusService.tryOut(new Plugin(
				plugingId), SessionUtil.getUser(inv.getRequest()).getUserId());
		return "@json:" + JsonUtils.toJson(dataMap);
	}

	/**
	 * 购买插件条件判断
	 * 
	 * @param inv
	 * @param plugingId
	 *            插件Id
	 * @return status取值 1进入正常购买流程,2 用户未登录, 3 已购买，插件过期，重新购买,可以进入购买流程
	 *         ,4已购买，未过期，可正常使用 repreCoin 当前用户账号拥有的代币数
	 * @throws Exception
	 */
	@Post("tobuy")
	public String tobuy(Invocation inv, @Param("pluginId") Integer plugingId)
			throws Exception {
		if (SessionUtil.getUser(inv.getRequest()) == null) {
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("status", "2");
			return "@json:" + JsonUtils.toJson(dataMap);
		}
		Map<String, String> dataMap = pluginBusService.prepareBuy(new Plugin(
				plugingId), SessionUtil.getUser(inv.getRequest()).getUserId());
		return "@json:" + JsonUtils.toJson(dataMap);
	}

	/**
	 * 免费使用
	 * 
	 * @param inv
	 * @param plugingId
	 *            插件id
	 * @return 1 成功处理 跳到我的淘插件.2用户未登录.3已免费使用过，如果过期则自己续期. 0系统错误
	 * @throws Exception
	 */
	@Post("freeUse")
	public String freeUse(Invocation inv, @Param("pluginId") Integer plugingId)
			throws Exception {
		if (SessionUtil.getUser(inv.getRequest()) == null) {
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("status", "2");
			return "@json:" + JsonUtils.toJson(dataMap);
		}
		Map<String, String> dataMap = pluginBusService.freeUser(new Plugin(
				plugingId), SessionUtil.getUser(inv.getRequest()).getUserId());
		return "@json:" + JsonUtils.toJson(dataMap);
	}

	/**
	 * 计算折扣价格
	 * 
	 * @param mon
	 * @param pid
	 * @param agid
	 * @return
	 * @throws Exception
	 */
	@Post("calculatePrice")
	public String calculatePrice(@Param("mon") Integer mon,
			@Param("pid") Integer pid, @Param("agid") Integer agid)
			throws Exception {
		// 找出当前定价
		PluginDetailVO plugin = pluginService.getAgentPlugin(pid, agid);
		log.error("**********plugin saleprice********=" + plugin.getSalePrice());
		Double salePrice = plugin.getSalePrice();
		Double afPrice = salePrice;
		afPrice = ConvertUtil.calPluginPrice(mon, afPrice);
		return "@json:" + "{\"afprice\":\"" + afPrice.intValue() + "\"}";
	}
}
