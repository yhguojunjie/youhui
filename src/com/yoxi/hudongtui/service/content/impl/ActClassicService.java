package com.yoxi.hudongtui.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.content.ActClassicDAO;
import com.yoxi.hudongtui.dao.content.ActClassicPicDAO;
import com.yoxi.hudongtui.model.content.ActClassicPic;
import com.yoxi.hudongtui.service.content.IActClassicService;
import com.yoxi.hudongtui.vo.content.ActClassicVO;

@Service
public class ActClassicService implements IActClassicService {
	// private Log log = LogFactory.getLog(getClass());
	@Autowired
	private ActClassicDAO actClassicDAO;
	@Autowired
	private ActClassicPicDAO actClassicPicDAO;

	@Override
	public List<ActClassicVO> findRecList(Integer agentId, String condition, int start, int count) throws Exception {
		List<ActClassicVO> actClassicVO = actClassicDAO.findRecList(condition, start, count);
		return actClassicVO;
	}

	@Override
	public List<ActClassicVO> findAgentRecList(Integer agentId, String condition, int start, int count) throws Exception {
		List<ActClassicVO> actClassicVO = actClassicDAO.findAgentRecList(condition, start, count);
		return actClassicVO;
	}

	@Override
	public List<ActClassicVO> findList(Integer agentId, String condition, int start, int count) throws Exception {
		List<ActClassicVO> actList = actClassicDAO.findRecList(condition, start, count);
		if (actList != null && actList.size() != 0) {
			for (ActClassicVO actClassicVO : actList) {
				List<ActClassicPic> picList = actClassicPicDAO.findList(actClassicVO.getId());
				if (picList != null && picList.size() != 0) {
					actClassicVO.setActClassicPicList(picList);
				}
			}
		}
		return actList;
	}

	@Override
	public List<ActClassicVO> findAgentList(Integer agentId, String condition, int start, int count) throws Exception {
		List<ActClassicVO> actList = actClassicDAO.findAgentRecList(condition, start, count);
		if (actList != null && actList.size() != 0) {
			for (ActClassicVO actClassicVO : actList) {
				List<ActClassicPic> picList = actClassicPicDAO.findList(actClassicVO.getId());
				if (picList != null && picList.size() != 0) {
					actClassicVO.setActClassicPicList(picList);
				}
			}
		}
		return actList;
	}

}
