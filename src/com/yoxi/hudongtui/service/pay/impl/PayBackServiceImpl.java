package com.yoxi.hudongtui.service.pay.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.dao.agent.AgentInfoDAO;
import com.yoxi.hudongtui.dao.agent.AgentOrderDAO;
import com.yoxi.hudongtui.dao.plugin.PluginDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.dao.user.UserDAO;
import com.yoxi.hudongtui.dao.user.UserTradeLogDAO;
import com.yoxi.hudongtui.model.agent.AgentInfo;
import com.yoxi.hudongtui.model.agent.AgentOrder;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.model.user.UserTradeLog;
import com.yoxi.hudongtui.service.pay.IPayBackService;
import com.yoxi.hudongtui.service.plugin.IPluginBusService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.vo.order.OrderDownVO;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;

@Service
public class PayBackServiceImpl implements IPayBackService {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private AgentOrderDAO orderDAO;
	@Autowired
	private PluginDAO pluginDAO;
	@Autowired
	private UserTradeLogDAO userTradeLogDAO;
	@Autowired
	private IPluginBusService pluginBusService;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private IPluginService pluginService;
	@Autowired
	private AgentInfoDAO agentInfoDAO;
	
	@Override
	public String back(OrderDownVO orderDownVO,AgentOrder order) throws Exception {
		//判断是否接收过
		if(order != null){
			if(!StringUtils.isNullBlank(order.getTradeState())){
				if(order.getTradeState().equals("2") || order.getTradeState().equals("1")){
					return "2"; //该定单已被处理过
				}else{
					order.setPayOrderStatus(Globals.ORDERSTATUS_DONE);
					order.setTradeState(Globals.ORDERTRADESTATE_DONE);
					order.setPurchaseType(Globals.PURCHASETYPE_OFFLINE);
					order.setCharge(0.0);
					//计算使用开始与结束时间
					PluginDetailVO plugin = pluginService.getAgentPlugin(order.getProductId(), order.getAgentId());
					int vaild =  plugin.getValid();
					//查找用户当前是否购买，是否是续费
					Date startDate = new Date();
					UserPlugin uplg = null;
					if(order.getUserPluginId() != null){
						uplg = userPluginDAO.getUserPluginById(order.getUserPluginId());
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
				
					int upFlag = orderDAO.update(order);
					if(upFlag > 0){
						//插件业务处理
						if(order.getProductType().equals(Globals.PRODUCTTYPE_PLUGINBUY)){//新购买
							pluginBusService.buy(plugin,order.getUserId(),order.getBuyNum());
						}
						if(order.getProductType().equals(Globals.PRODUCTTYPE_PLUGINRENEWAL)){//续费购买
							pluginBusService.renewal(plugin,uplg,order.getUserId(),order.getBuyNum());
						}
						
						//计算代理商账号金额相关
						AgentInfo agentInfo = agentInfoDAO.getById(order.getAgentId());
							if(agentInfo != null){
								//扣减代理商账户余额
								Double costIncome = ConvertUtil.calPluginPrice(order.getBuyNum(),order.getCostPrice());
								Double blance =  agentInfo.getBlance() - costIncome;
								String upStr = " , blance = "+blance;
								
							/*	if(order.getCharge() != null && order.getCharge() != 0.0){
									Double totalIncome = agentInfo.getTotalIncome();
									totalIncome = totalIncome + order.getCharge();
									upStr = upStr + " , totalIncome = "+totalIncome;
								}*/
								
								if(blance < 0.0 && agentInfo.getDebtTime() == null){
										upStr = upStr + " , debtTime = NOW() ";
								}
								agentInfoDAO.upByStr(order.getAgentId(), upStr);
							}
					
					
						//写用户交易日志
						UserTradeLog utLog = new UserTradeLog();
						utLog.setOrderId(orderDownVO.getOrderId());
						utLog.setTradeType(Globals.TRADETYPE_OFFLINE);
						if(order.getCharge() != null && order.getCharge() != 0.0){
							utLog.setAmount(order.getCharge());
						}
						utLog.setUserId(order.getUserId());
						utLog.setSourceType(order.getSourceType());
						utLog.setProductId(order.getProductId());
						utLog.setProductType(order.getProductType());
						utLog.setSourceType(order.getSourceType());
						utLog.setPurchaseType(order.getPurchaseType());
						utLog.setAgentId(order.getAgentId());
						utLog.setDescription("线下交易：\""+plugin.getName());
						userTradeLogDAO.add(utLog);
					}
					return "0";
				}
			}else{//交易状态值不明确
				return "3";
			}
			
		}else{ //定单不存在
			return "4";
		}
	}
	
}
