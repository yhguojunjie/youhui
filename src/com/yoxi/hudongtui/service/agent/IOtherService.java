package com.yoxi.hudongtui.service.agent;

import java.util.List;

import com.yoxi.hudongtui.model.content.ContactUs;
import com.yoxi.hudongtui.model.content.Joinboard;
import com.yoxi.hudongtui.vo.agent.AboutusInfoVO;
import com.yoxi.hudongtui.vo.agent.QuestionVO;

/**
 * 
 * 帮助中心service
 * 
 * @author gjj
 * 
 * @Date 2015年3月31日
 * 
 */
public interface IOtherService {

	/**
	 * 保存加盟代理商信息
	 * 
	 * @param joinboard
	 * @return
	 * @throws Exception
	 */
	public Integer save(Joinboard joinboard);

	/**
	 * 查询出所有的常见问题列表
	 */
	public List<QuestionVO> findQuestionInfoVO(String findStr);

	/**
	 * 查询关于我们
	 */
	public AboutusInfoVO findAboutusInfoVO(String findStr);

	/**
	 * 查找联系我们
	 * 
	 * @param agentId
	 * @return
	 * @throws Exception
	 */
	public ContactUs getContactUs(Integer agentId) throws Exception;
}
