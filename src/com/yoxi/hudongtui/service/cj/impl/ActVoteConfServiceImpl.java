package com.yoxi.hudongtui.service.cj.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.paoding.rose.web.Invocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.hudongtui.dao.cj.vote.ActVoteConfDAO;
import com.yoxi.hudongtui.dao.cj.vote.ActVoteVoterDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.vote.ActVoteConf;
import com.yoxi.hudongtui.model.cj.vote.ActVoteVoter;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActVoteConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.vote.VoterVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActVoteConfServiceImpl implements IActVoteConfService {

	@Autowired
	private ActVoteConfDAO actVoteConfDAO;
	@Autowired
	private ActVoteVoterDAO actVoteVoterDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Override
	@Transactional
	public Integer save(ActVoteConf actVoteConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actVoteConf.getName());
		pluginAct.setStartTime(actVoteConf.getStartTime());
		pluginAct.setEndTime(actVoteConf.getEndTime());
		pluginAct.setIcon(actVoteConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actVoteConf.setActivityId(activityId);
			ret = actVoteConfDAO.add(actVoteConf).intValue();
			//更新用户插件表活动数
			if(ret != 0){
				String upstr = " actNum = actNum + 1 ";
				userPluginDAO.upByStr(userPluginId, upstr);
			}
		}
		return ret;
	}

	@Override
	@Transactional
	public Integer update(ActVoteConf actVoteConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actVoteConf.getActivityId());
		infoVo.setIcon(actVoteConf.getShareImgUrl());
		infoVo.setStartTime(actVoteConf.getStartTime());
		infoVo.setEndTime(actVoteConf.getEndTime());
		infoVo.setTitle(actVoteConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actVoteConfDAO.update(actVoteConf);
		return ret;
	}

	@Override
	public ActVoteConf findByActId(Integer activityId) {
		ActVoteConf actVoteConf = actVoteConfDAO.findByActivityId(activityId);
		return actVoteConf;
	}

	@Override
	public Pagination<ActVoteVoter> getRankPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actVoteVoterDAO.getRankCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActVoteVoter> rankVos = actVoteVoterDAO.getRank(activityId, startRow, pageSize);
		return new Pagination<ActVoteVoter>(totalCount, pageSize, currPage, rankVos);
	}

	@Override
	public void exportRank(Integer activityId, Invocation inv,
			Integer startRow, Integer pageSize) {
		List<VoterVo> list = actVoteVoterDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "编号", "票数", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称"};
		List<VoterVo> listVo = new ArrayList<VoterVo>();
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(VoterVo voter : list){
				VoterVo voterVo = new VoterVo();
				voterVo.setId(i++);
				voterVo.setVoterId(voter.getVoterId());
				voterVo.setMailAddress(voter.getMailAddress());
				voterVo.setQq(voter.getQq());
				voterVo.setWechatId(voter.getWechatId());
				voterVo.setOtherInfo(voter.getOtherInfo());
				voterVo.setTel(voter.getTel());
				voterVo.setUsername(voter.getUsername());
				voterVo.setVoteNum(voter.getVoteNum());
				listVo.add(voterVo);
			}
		}
		ExportExcel<VoterVo> ex = new ExportExcel<VoterVo>();
		String ioTmpdir = System.getProperty("java.io.tmpdir");
		String crruentTime = DateUtils.getCurrentTime();
		String tempFilePath = ioTmpdir+"//"+crruentTime+".xls";
		OutputStream out = null;
		try {
			out = new FileOutputStream(tempFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			ex.exportExcel("排行记录",headers, listVo, out);
			ExportUtil.ExcelFileExport(inv, tempFilePath,crruentTime+".xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int countAllRank(Integer activityId) {
		return actVoteVoterDAO.getRankCount(activityId);
	}
	
	@Override
	public int updateVoteNum(Integer id, Integer score) {
		return actVoteVoterDAO.updateVoteNum(id, score);
	}

	@Override
	public int delete(Integer id) {
		return actVoteVoterDAO.delete(id);
	}
}
