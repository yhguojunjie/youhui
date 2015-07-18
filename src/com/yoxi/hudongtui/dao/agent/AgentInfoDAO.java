package com.yoxi.hudongtui.dao.agent;

import java.util.List;

import com.yoxi.hudongtui.model.agent.AgentInfo;
import com.yoxi.hudongtui.vo.agent.AgentInfoVO;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

/**
 * 
 * 代理商信息DAO
 *
 * @author wql
 *
 * @Date 2015年3月14日
 *
 */
@DAO
public interface AgentInfoDAO {

	
	/**
	 * 按主键ID查找
	 * @param id
	 * @return
	 */
	@SQL("SELECT * FROM t_agent_info WHERE id = :1")
	public AgentInfo getById(Integer id);
	
	/**
	 * 按拼凑字符串来查，确保返回记录为唯一
	 * @param getStr
	 * @return
	 */
	@SQL("SELECT * FROM t_agent_info WHERE ##(:getStr) LIMIT 0,1")
	public AgentInfo getByStr(@SQLParam("getStr") String getStr);
	
	/**
	 * 按拼凑字符串来查, 返回值为列表
	 * @param findStr
	 * @return
	 */
	@SQL("SELECT * FROM t_agent_info WHERE ##(:findStr)")
	public List<AgentInfo> findByStr(@SQLParam("findStr") String findStr);
	
	
	/**
	 * 按主键id查找返回 代理商域名
	 * @param id
	 * @return
	 */
	@SQL("SELECT forwardDomain FROM t_agent_info WHERE id = :1")
	public String getDomainById(Integer id);
	
	/**
	 * 按id查找前端展示代理商信息
	 * @param id
	 * @return
	 */
	@SQL("SELECT a.id,a.address,a.companyName,a.mydomain,a.forwardDomain,a.wxqrcode,a.redirecDomain,a.logo,a.webRecord,"
			+ "a.logoDesc,a.websiteTitle,a.websiteDesc,a.websiteKeyword,a.websiteIcon,a.serviceEmail,"
			+ "a.servicePhone,a.serviceqq,a.qqGroup,a.version FROM t_agent_info a WHERE a.id = :1 ")
	public AgentInfoVO getAgentInfoVOById(Integer id);
	
	/**
	 * 按条件查看代理商id,如果代理商Id不存在，则返回官方代理商id
	 * @param getStr
	 * @return id
	 */
	@SQL("SELECT " 
			+"CASE WHEN (SELECT a.id from t_agent_info a ,t_s_base_user b WHERE a.id = b.id  AND b.`status` = 1 AND ##(:getStr) LIMIT 0,1) IS NOT NULL "
			+"THEN (SELECT a.id from t_agent_info a ,t_s_base_user b WHERE a.id = b.id AND b.`status` = 1 AND ##(:getStr) LIMIT 0,1) "  
			+"ELSE (SELECT c.id FROM t_agent_info c WHERE c.isOfficial = 1 LIMIT 0,1) "  
			+"END as id")
	public Integer getAgentIdForUser(@SQLParam("getStr") String getStr);
	
	/**
	 * 查看官方代理商id
	 * @return
	 */
	@SQL("SELECT id FROM t_agent_info WHERE isOfficial = 1 LIMIT 0,1")
	public Integer getOfficialAgId();
	
	/**
	 * 按拼凑字符串更新
	 * @param pluginId
	 * @param upstr
	 * @return
	 */
	@SQL("update t_agent_info set updateTime = NOW() ##(:upstr) where id = :1")
	public int upByStr(Integer id,@SQLParam("upstr") String upstr);

}
