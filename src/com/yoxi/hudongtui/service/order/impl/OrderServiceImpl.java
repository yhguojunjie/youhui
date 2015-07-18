package com.yoxi.hudongtui.service.order.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.dao.agent.AgentInfoDAO;
import com.yoxi.hudongtui.dao.agent.AgentOrderDAO;
import com.yoxi.hudongtui.dao.plugin.PluginDAO;
import com.yoxi.hudongtui.model.agent.AgentInfo;
import com.yoxi.hudongtui.model.agent.AgentOrder;
import com.yoxi.hudongtui.service.order.IOrderService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.vo.order.OrderDownVO;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;

@Service
public class OrderServiceImpl implements IOrderService {
	
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private AgentOrderDAO orderDAO;
	@Autowired
	private PluginDAO pluginDAO;
	@Autowired
	private IPluginService pluginService;
	@Autowired
	private AgentInfoDAO agentInfoDAO;
	
	
	@Override
	public OrderDownVO down(OrderDownVO orderDown,PluginDetailVO plugin) throws Exception {
   
		// 生成订单
		AgentOrder order = new AgentOrder();
		order.setUserId(orderDown.getUserId());
		if(orderDown.getCharge() != null){
			order.setCharge(Double.valueOf(orderDown.getCharge()));
		}
		order.setPayOrderStatus(Globals.ORDERSTATUS_WAIT);
		order.setProductId(orderDown.getProductId());
		order.setProductType(orderDown.getProductType());
		order.setPurchaserId(orderDown.getUserId());
		order.setPurchaseType(orderDown.getPurchaseType());
		order.setRepreCoin(orderDown.getRepreCoin());
		order.setSourceType(orderDown.getSourceType());
		order.setBuyNum(orderDown.getBuyMon());
		order.setCostPrice(plugin.getPrice());
		order.setSalePrice(plugin.getSalePrice());
		order.setAgentId(orderDown.getAgentId());
		order.setTradeState(Globals.ORDERTRADESTATE_WAIT);
		order.setUserPluginId(orderDown.getUserPluginId());
		order.setOrderTime(new Date());
		
		AgentInfo agentInfo = agentInfoDAO.getById(orderDown.getAgentId());
		if(agentInfo != null){
			if(StringUtils.isNotBlank(agentInfo.getAliPartner()) && StringUtils.isNotBlank(agentInfo.getAliKey())
					&& StringUtils.isNotBlank(agentInfo.getAliSellerAccount())){
				order.setSellerAccount(agentInfo.getAliSellerAccount());
			}else{
				order.setSellerAccount(ReadProperties.getPara("alipay_seller_account"));
			}
		}else{
			order.setSellerAccount(ReadProperties.getPara("alipay_seller_account"));
		}
		
		Integer orderId = orderDAO.save(order).intValue();
		orderDown.setOrderId(orderId);
		
		orderDown.setProductName(plugin.getName());
		orderDown.setCode("0");
		orderDown.setMsg("下单成功");
		
		return orderDown;
	}

	@Override
	public AgentOrder findByOrderId(Integer orderId)throws Exception {
		return orderDAO.findByOrderId(orderId);
	}

}
