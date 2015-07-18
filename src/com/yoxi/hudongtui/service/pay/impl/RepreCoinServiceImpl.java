package com.yoxi.hudongtui.service.pay.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.dao.agent.AgentOrderDAO;
import com.yoxi.hudongtui.dao.plugin.PluginDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.dao.user.UserDAO;
import com.yoxi.hudongtui.dao.user.UserTradeLogDAO;
import com.yoxi.hudongtui.model.agent.AgentOrder;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.model.user.UserTradeLog;
import com.yoxi.hudongtui.service.pay.IRepreCoinService;
import com.yoxi.hudongtui.service.plugin.IPluginBusService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.vo.order.OrderDownVO;
import com.yoxi.hudongtui.vo.pay.daipi.BuyResp;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;

@Service
public class RepreCoinServiceImpl implements IRepreCoinService {

	@Autowired
	private AgentOrderDAO orderDAO;
	
	@Autowired
	private PluginDAO pluginDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Autowired
	private UserTradeLogDAO userTradeLogDAO;
	
	@Autowired
	private IPluginBusService pluginBusService;
	
	@Override
	@Transactional
	public BuyResp buy(OrderDownVO orderDown,PluginDetailVO plugin) throws Exception {
		
		 BuyResp buyResp = new BuyResp();
		
		 //查看是否当前可用的订购
//		 if(orderDAO.isOrdered(orderDown) == 0){
			User user = userDAO.findByUserId(orderDown.getUserId());
			if(user.getRepreCoin() == null){
				user.setRepreCoin(0.0);
			}else if(orderDown.getRepreCoin() <= user.getRepreCoin()){
				//扣减账户代币
				Double afDeduction = user.getRepreCoin() - orderDown.getRepreCoin();
			  	int flag = userDAO.updateByCriteria(orderDown.getUserId(),"repreCoin",String.valueOf(afDeduction));
			  	if(flag > 0){//扣减成功,生成定单
			  		AgentOrder order = new AgentOrder();
			  		order = transFromOrderDown(orderDown);
			  		order.setRepreCoin(orderDown.getRepreCoin());
			  		order.setPurchaseType(Globals.PURCHASETYPE_REPRECOIN);
			  		order.setPurchaserId(orderDown.getUserId());
			  		order.setPayOrderStatus(Globals.ORDERSTATUS_DONE);
			  		order.setSourceType(orderDown.getSourceType());
			  		order.setBuyNum(orderDown.getBuyMon());
			  		order.setAgentId(orderDown.getAgentId());
			  		order.setBuyNum(orderDown.getBuyMon());
			  		order.setSalePrice(plugin.getSalePrice());
			  		order.setCostPrice(plugin.getPrice());
			  		order.setUserPluginId(orderDown.getUserPluginId());
					//计算使用开始与结束时间
					int vaild =  plugin.getValid();
					Date startDate = new Date();
					UserPlugin uplg = null;
					if(orderDown.getUserPluginId() != null){
						uplg = userPluginDAO.getUserPluginById(orderDown.getUserPluginId());
						if(uplg.getOverdueTime() != null){
							if(uplg.getUseType().equals(Globals.PLUGINUSER_USETYPE_BUY)
								&& uplg.getOverdueTime().after(new Date())){
									startDate = uplg.getOverdueTime();
								}
						}
						
					}
					order.setStartDate(startDate);
					Date endDate = DateUtils.addMonths(new Date(), vaild * order.getBuyNum());
					order.setEndDate(endDate);
					order.setOrderTime(new Date());
					order.setTradeState(Globals.ORDERTRADESTATE_DONE);
					Integer orderId = orderDAO.save(order).intValue();
					
					if(orderId != null){
						 //插件相关处理
						if(order.getProductType().equals(Globals.PRODUCTTYPE_PLUGINBUY)){//新购买
							pluginBusService.buy(plugin,order.getUserId(),order.getBuyNum());
						}
						if(order.getProductType().equals(Globals.PRODUCTTYPE_PLUGINRENEWAL)){//续费购买
							pluginBusService.renewal(plugin,uplg,order.getUserId(),order.getBuyNum());
						}
						//生成业务表数据
						UserTradeLog utLog = new UserTradeLog();
					    utLog.setTradeType(Globals.TRADETYPE_CONSUMEDAIPI);
						utLog.setAmount(order.getRepreCoin());
						utLog.setUserId(order.getUserId());
						utLog.setSourceType(order.getSourceType());
						utLog.setProductId(order.getProductId());
						utLog.setProductType(order.getProductType());
						utLog.setSourceType(order.getSourceType());
						utLog.setPurchaseType(order.getPurchaseType());
						utLog.setOrderId(orderId);
						utLog.setAgentId(user.getAgentId());
						utLog.setDescription("使用代币购买插件：\""+plugin.getName()+"\"，交易金额："+order.getRepreCoin()+"代币");
					    userTradeLogDAO.add(utLog);
					}
					buyResp.setCode(Globals.ORDERDOWNSTATUS_OK);
					buyResp.setMsg("下单成功!");
			  	}
			}else{
				buyResp.setCode(Globals.ORDERDOWNSTATUS_NOCOIN);
				buyResp.setMsg("代币不足");
			}
			 
	/*	 }else{
			 buyResp.setCode(Globals.ORDERDOWNSTATUS_BUYED);
			 buyResp.setCode("已订购过该产品!");
		 }*/
		 
		return buyResp;
	}
	
	
	/**
	 * order转换
	 * @param orderDown
	 * @return
	 */
	public AgentOrder transFromOrderDown(OrderDownVO orderDown){
		AgentOrder order = new AgentOrder();
		order.setCharge(orderDown.getCharge());
		order.setProductId(orderDown.getProductId());
		order.setProductType(orderDown.getProductType());
		order.setUserId(orderDown.getUserId());
		order.setRepreCoin(orderDown.getRepreCoin());
		order.setPurchaseType(orderDown.getPurchaseType());
		order.setSourceType(orderDown.getSourceType());
		return order;
	}

}
