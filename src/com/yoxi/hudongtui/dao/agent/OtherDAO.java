package com.yoxi.hudongtui.dao.agent;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.model.content.ContactUs;
import com.yoxi.hudongtui.vo.agent.AboutusInfoVO;
import com.yoxi.hudongtui.vo.agent.QuestionVO;

/**
 * 
 * 帮助中心信息DAO
 * 
 * @author gjj
 * 
 * @Date 2015年3月31日
 * 
 */
@DAO
public interface OtherDAO {

	/**
	 * 查询出所有的常见问题列表
	 * 
	 * @param findStr
	 * @return
	 */
	@SQL("SELECT answer,createTime,id,question,seq,updateTime FROM t_faq WHERE 1=1 ##(:findStr)")
	public List<QuestionVO> findQuestionInfoVO(@SQLParam("findStr") String findStr);

	/**
	 * 关于我们
	 * 
	 * @param findStr
	 * @return
	 */
	@SQL("SELECT a.agentId,a.content,a.createTime,a.id,a.updateTime FROM t_aboutus a WHERE 1=1 ##(:findStr)")
	public AboutusInfoVO findAboutusInfoVO(@SQLParam("findStr") String findStr);

	
	/**
	 * 联系我们
	 * @param agentId
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT id,content,agentId FROM t_aboutus WHERE auditState = '1' AND status = '1' AND agentId = :1 LIMIT 0,1")
	public ContactUs getContactUs(Integer agentId) throws Exception;

}
