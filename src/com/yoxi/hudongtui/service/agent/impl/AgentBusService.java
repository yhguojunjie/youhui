package com.yoxi.hudongtui.service.agent.impl;

import javax.servlet.http.HttpServletRequest;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.constants.MemcachedConstans;
import com.yoxi.hudongtui.dao.agent.AgentInfoDAO;
import com.yoxi.hudongtui.dao.content.ContactUsDAO;
import com.yoxi.hudongtui.model.agent.AgentInfo;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.agent.IAgentBusService;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.vo.agent.AgentInfoVO;
import com.yoxi.hudongtui.vo.pay.alipay.AlipayConfigVO;

@Service("agentBusService")
public class AgentBusService implements IAgentBusService {

	@Autowired
	private AgentInfoDAO agentInfoDAO;
	@Autowired
	private ContactUsDAO contactUsDAO;
	@Autowired
	private MemcachedClient memcachedClient;
	
	@Override
	public AgentInfoVO getAgentInfoVO(Integer agentId) throws Exception {
		AgentInfoVO agetnInfoVO = agentInfoDAO.getAgentInfoVOById(agentId);
		return agetnInfoVO;
	}
	
	@Override
	public AgentInfoVO getMcacheAgentInfo(Integer agentId) throws Exception {
		String agentInfoKey = MemcachedConstans.AGENTINFO_KEY+agentId;
		AgentInfoVO agentInfoVO = null;
		if(memcachedClient != null){
			//代理商联系信息处理
			if(memcachedClient.get(agentInfoKey) != null){
				agentInfoVO = (AgentInfoVO)memcachedClient.get(agentInfoKey);
			}else{
				agentInfoVO = getAgentInfoVO(agentId);
				if(agentInfoVO != null){
					memcachedClient.set(agentInfoKey, MemcachedConstans.AGENTINFO_KEY_EXPIRED, agentInfoVO);
				}
			}
		}else{
			agentInfoVO = getAgentInfoVO(agentId);
		}
		return agentInfoVO;
	}

	
	@Override
	public Integer getAgentIdForUser(HttpServletRequest request)throws Exception {
		//如果用户登录情况下，先从session取值
		if(SessionUtil.getUser(request) != null ){
			User user = SessionUtil.getUser(request);
			return  user.getAgentId();
		}else{
			String basePath = request.getScheme() + "://"+ request.getServerName();
			String getStr = " a.mydomain = '"+basePath+"' OR '"+basePath.trim()+"/' ";
			Integer agentId = agentInfoDAO.getAgentIdForUser(getStr);
			return agentId;
		}
	}

	@Override
	public AlipayConfigVO getAgentAliConfg(Integer id) throws Exception {
		AgentInfo agentInfo = agentInfoDAO.getById(id);
		AlipayConfigVO alipayConfig = null;
		if(StringUtils.isNotBlank(agentInfo.getAliPartner()) && StringUtils.isNotBlank(agentInfo.getAliKey())
				&& StringUtils.isNotBlank(agentInfo.getAliSellerAccount())){
			alipayConfig = new AlipayConfigVO();
			alipayConfig.setPartner(agentInfo.getAliPartner());
			alipayConfig.setKey(agentInfo.getAliKey());
			alipayConfig.setSellerAccount(agentInfo.getAliSellerAccount());
		}else{
			alipayConfig = new AlipayConfigVO();
			alipayConfig.setPartner(ReadProperties.getPara("alipay_partner"));
			alipayConfig.setKey(ReadProperties.getPara("alipay_key"));
			alipayConfig.setSellerAccount(ReadProperties.getPara("alipay_seller_account"));
		}
		return alipayConfig;
	}

}
