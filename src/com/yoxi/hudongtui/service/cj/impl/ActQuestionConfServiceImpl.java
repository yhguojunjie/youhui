package com.yoxi.hudongtui.service.cj.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.paoding.rose.web.Invocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yoxi.hudongtui.dao.cj.question.ActQuestionBankDAO;
import com.yoxi.hudongtui.dao.cj.question.ActQuestionConfDAO;
import com.yoxi.hudongtui.dao.cj.question.ActQuestionPrizeDAO;
import com.yoxi.hudongtui.dao.cj.question.ActQuestionRecordDAO;
import com.yoxi.hudongtui.dao.cj.question.ActQuestionScoreDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.question.ActQuestionBank;
import com.yoxi.hudongtui.model.cj.question.ActQuestionConf;
import com.yoxi.hudongtui.model.cj.question.ActQuestionPrize;
import com.yoxi.hudongtui.model.cj.question.ActQuestionRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActQuestionConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.question.QuestionAwardsVo;
import com.yoxi.hudongtui.vo.cj.question.QuestionRankVo;
import com.yoxi.hudongtui.vo.cj.question.QuestionRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;
import com.yoxi.hudongtui.vo.plugin.PluginShowActInfoVo;

@Service
public class ActQuestionConfServiceImpl implements IActQuestionConfService {

	@Autowired
	private ActQuestionConfDAO actQuestionConfDAO;
	@Autowired
	private ActQuestionPrizeDAO actQuestionPrizeDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private ActQuestionRecordDAO actQuestionRecordDAO;
	@Autowired
	private ActQuestionScoreDAO actQuestionScoreDAO;
	@Autowired
	private ActQuestionBankDAO actQuestionBankDAO;
	
	@Override
	@Transactional
	public Integer save(ActQuestionConf actQuestionConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actQuestionConf.getName());
		pluginAct.setStartTime(actQuestionConf.getStartTime());
		pluginAct.setEndTime(actQuestionConf.getEndTime());
		pluginAct.setIcon(actQuestionConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actQuestionConf.setActivityId(activityId);
			ret = actQuestionConfDAO.add(actQuestionConf).intValue();
			//更新用户插件表活动数
			if(ret != 0){
				String upstr = " actNum = actNum + 1 ";
				userPluginDAO.upByStr(userPluginId, upstr);
			}
			
			PluginShowActInfoVo showActInfoVo = pluginActDAO.findShowActId(activityId);
			if(showActInfoVo != null && showActInfoVo.getShowActId() != null){
				List<ActQuestionBank> quesList =  actQuestionBankDAO.getBank(showActInfoVo.getShowActId(), 0, 10);
				for (ActQuestionBank actQuestionBank : quesList) {
					actQuestionBank.setActivityId(activityId);
					actQuestionBank.setFinalTime(new Date());
					actQuestionBankDAO.add(actQuestionBank);
				}
			}
		}
		return ret;
	}

	@Override
	@Transactional
	public Integer update(ActQuestionConf actQuestionConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actQuestionConf.getActivityId());
		infoVo.setIcon(actQuestionConf.getShareImgUrl());
		infoVo.setStartTime(actQuestionConf.getStartTime());
		infoVo.setEndTime(actQuestionConf.getEndTime());
		infoVo.setTitle(actQuestionConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actQuestionConfDAO.update(actQuestionConf);
		return ret;
	}

	@Override
	public ActQuestionConf findByActId(Integer activityId) {
		ActQuestionConf actQuestionConf = actQuestionConfDAO.findByActivityId(activityId);
		return actQuestionConf;
	}

	@Override
	public List<QuestionAwardsVo> parseJson(String content) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<QuestionAwardsVo>>(){}.getType();
		List<QuestionAwardsVo> questionAwardsVoList = gson.fromJson(content, type);
		return questionAwardsVoList;
	}
	
	@Override
	public Pagination<ActQuestionRecord> getRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actQuestionRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActQuestionRecord> recordVos = actQuestionRecordDAO.getRecord(activityId, startRow, pageSize);
		return new Pagination<ActQuestionRecord>(totalCount, pageSize, currPage, recordVos);
	}
	
	@Override
	public Pagination<QuestionRankVo> getRankPage(Integer activityId,
		int currPage, int pageSize) {
		// 获取总数
		int totalCount = actQuestionScoreDAO.getRankCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<QuestionRankVo> recordVos = actQuestionScoreDAO.getRank(activityId, startRow, pageSize);
		return new Pagination<QuestionRankVo>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActQuestionRecord> list = actQuestionRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖项", "奖品名称", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		String u[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		List<QuestionRecordVo> listVo = new ArrayList<QuestionRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActQuestionRecord record : list){
				QuestionRecordVo recordVo = new QuestionRecordVo();
				recordVo.setId(i++);
				recordVo.setPrizeType(u[record.getPrizeType()]+"等奖");
				recordVo.setPrizeName(record.getPrizeName());
				recordVo.setMailAddress(record.getMailAddress());
				recordVo.setQq(record.getQq());
				recordVo.setWechatId(record.getWechatId());
				recordVo.setOtherInfo(record.getOtherInfo());
				recordVo.setTel(record.getTel());
				recordVo.setUsername(record.getUsername());
				if(record.getPrizeTime() != null){
					recordVo.setPrizeTime(ft.format(record.getPrizeTime()));
				}
				if(record.getExchangeTime() != null){
					recordVo.setExchangeTime(ft.format(record.getExchangeTime()));
				}
				if(record.getOpState() == null || record.getOpState().equals("0")){
					recordVo.setOpState("未兑换");
				}else if(record.getOpState().equals("1")){
					recordVo.setOpState("已兑换");
				}
				listVo.add(recordVo);
			}
		}
		ExportExcel<QuestionRecordVo> ex = new ExportExcel<QuestionRecordVo>();
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
			ex.exportExcel("中奖记录",headers, listVo, out);
			ExportUtil.ExcelFileExport(inv, tempFilePath,crruentTime+".xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exportRank(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<QuestionRankVo> list = actQuestionScoreDAO.getRank(activityId, startRow, pageSize);
		String[] headers = {"序号", "时间", "答对题数","邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称"};
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(QuestionRankVo rank : list){
				rank.setId(i++);
			}
		}
		ExportExcel<QuestionRankVo> ex = new ExportExcel<QuestionRankVo>();
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
			ex.exportExcel("中奖记录",headers, list, out);
			ExportUtil.ExcelFileExport(inv, tempFilePath,crruentTime+".xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int countAllRecord(Integer activityId) {
		return actQuestionRecordDAO.countAllPrizeRecord(activityId);
	}

	@Override
	public int countAllScore(Integer activityId) {
		return actQuestionScoreDAO.getRankCount(activityId);
	}

	@Override
	public String changeOpStatus(Integer id) {
		String op = actQuestionRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actQuestionRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actQuestionRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

	@Override
	public int updateBestScore(Integer id, Integer playTime, Integer rightNum) {
		return actQuestionScoreDAO.updateBestScore(id, playTime, rightNum);
	}

	@Override
	public void savePrize(Integer activityId, String awards) {
		List<QuestionAwardsVo> questionAwardsVoList = parseJson(awards);
		for (QuestionAwardsVo questionAwardsVo : questionAwardsVoList) {
			ActQuestionPrize actQuestionPrize = actQuestionPrizeDAO.findByActivityId(activityId, 
					questionAwardsVo.getPrizeType());
			if(actQuestionPrize == null){
				Integer deliverPrizeNum = actQuestionRecordDAO.countPrizeNumber(activityId, questionAwardsVo.getPrizeType());
				if(deliverPrizeNum == null)deliverPrizeNum = 0;
				
				actQuestionPrize = new ActQuestionPrize();
				actQuestionPrize.setActivityId(activityId);
				actQuestionPrize.setPrizeType(questionAwardsVo.getPrizeType());
				actQuestionPrize.setRealNum(questionAwardsVo.getRealNum());
				actQuestionPrize.setDeliverNum(deliverPrizeNum);
				actQuestionPrize.setVersion(1);
				actQuestionPrize.setFinalTime(new Date());
				actQuestionPrizeDAO.add(actQuestionPrize);
			}else{
				actQuestionPrizeDAO.upByStr(actQuestionPrize, " deliverNum = " 
				+ actQuestionPrize.getDeliverNum() + ",realNum = " + questionAwardsVo.getRealNum());
			}
		}
	}
	
	@Override
	public Pagination<ActQuestionBank> getBankPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actQuestionBankDAO.getBankCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActQuestionBank> recordVos = actQuestionBankDAO.getBank(activityId, startRow, pageSize);
		return new Pagination<ActQuestionBank>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public Integer addQuestion(ActQuestionBank actQuestionBank) {
		int ret = actQuestionBankDAO.add(actQuestionBank).intValue();
		return ret;
	}

	@Override
	public Integer updateQuestion(ActQuestionBank actQuestionBank) {
		int ret = actQuestionBankDAO.upByStr(actQuestionBank, "question = '" + actQuestionBank.getQuestion() + "'");
		return ret;
	}

	@Override
	public Integer removeQuestion(Integer id) {
		int ret = actQuestionBankDAO.remove(id);
		return ret;
	}
}
