package com.yoxi.hudongtui.service.agent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.agent.OtherDAO;
import com.yoxi.hudongtui.dao.content.JoinboardDAO;
import com.yoxi.hudongtui.model.content.ContactUs;
import com.yoxi.hudongtui.model.content.Joinboard;
import com.yoxi.hudongtui.service.agent.IOtherService;
import com.yoxi.hudongtui.vo.agent.AboutusInfoVO;
import com.yoxi.hudongtui.vo.agent.QuestionVO;

@Service
public class OtherService implements IOtherService {

	@Autowired
	private OtherDAO otherDAO;
	@Autowired
	private JoinboardDAO joinboardDAO;

	/**
	 * 保存代理商信息
	 * 
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer save(Joinboard joinboard) {
		return joinboardDAO.add(joinboard).intValue();
	}

	/**
	 * 查询出所有的常见问题列表
	 */
	public List<QuestionVO> findQuestionInfoVO(String findStr) {
		List<QuestionVO> otherInfoVOs = otherDAO.findQuestionInfoVO(findStr);
		return otherInfoVOs;
	}

	/**
	 * 查询关于我们
	 */
	public AboutusInfoVO findAboutusInfoVO(String findStr) {
		AboutusInfoVO aboutusInfoVO = otherDAO.findAboutusInfoVO(findStr);
		return aboutusInfoVO;
	}

	@Override
	public ContactUs getContactUs(Integer agentId) throws Exception {
		return otherDAO.getContactUs(agentId);
	}

}
