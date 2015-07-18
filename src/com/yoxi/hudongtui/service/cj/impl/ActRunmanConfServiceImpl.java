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
import com.yoxi.hudongtui.dao.cj.ActRunmanConfDAO;
import com.yoxi.hudongtui.dao.cj.ActRunmanPrizeDAO;
import com.yoxi.hudongtui.dao.cj.ActRunmanRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.ActRunmanConf;
import com.yoxi.hudongtui.model.cj.ActRunmanPrize;
import com.yoxi.hudongtui.model.cj.ActRunmanRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActRunmanConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.runman.RunmanAwardsVo;
import com.yoxi.hudongtui.vo.cj.runman.RunmanRankVo;
import com.yoxi.hudongtui.vo.cj.runman.RunmanRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActRunmanConfServiceImpl implements IActRunmanConfService {
	
	@Autowired
	private ActRunmanConfDAO actRunmanConfDAO;
	@Autowired
	private ActRunmanPrizeDAO actRunmanPrizeDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private ActRunmanRecordDAO actRunmanRecordDAO;
	
	@Override
	@Transactional
	public Integer save(ActRunmanConf actRunmanConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actRunmanConf.getTitle());
		pluginAct.setStartTime(actRunmanConf.getStartTime());
		pluginAct.setEndTime(actRunmanConf.getEndTime());
		pluginAct.setIcon(actRunmanConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actRunmanConf.setActivityId(activityId);
			ret = actRunmanConfDAO.add(actRunmanConf).intValue();
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
	public Integer update(ActRunmanConf actRunmanConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actRunmanConf.getActivityId());
		infoVo.setIcon(actRunmanConf.getShareImgUrl());
		infoVo.setStartTime(actRunmanConf.getStartTime());
		infoVo.setEndTime(actRunmanConf.getEndTime());
		infoVo.setTitle(actRunmanConf.getTitle());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actRunmanConfDAO.update(actRunmanConf);
		return ret;
	}

	@Override
	public ActRunmanConf findByActId(Integer activityId) {
		ActRunmanConf actRunmanConf = actRunmanConfDAO.findByActivityId(activityId);
		return actRunmanConf;
	}

	@Override
	public List<RunmanAwardsVo> parseJson(String content) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<RunmanAwardsVo>>(){}.getType();
		List<RunmanAwardsVo> runmanAwardsVoList = gson.fromJson(content, type);
		return runmanAwardsVoList;
	}
	
	@Override
	public Pagination<ActRunmanRecord> getRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actRunmanRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActRunmanRecord> recordVos = actRunmanRecordDAO.getRecord(activityId, startRow, pageSize);
		return new Pagination<ActRunmanRecord>(totalCount, pageSize, currPage, recordVos);
	}
	
	@Override
	public Pagination<ActRunmanRecord> getRankPage(Integer activityId,
		int currPage, int pageSize) {
		// 获取总数
		int totalCount = actRunmanRecordDAO.getRankCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActRunmanRecord> recordVos = actRunmanRecordDAO.getRank(activityId, startRow, pageSize);
		return new Pagination<ActRunmanRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActRunmanRecord> list = actRunmanRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖项", "奖品名称", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		String u[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		List<RunmanRecordVo> listVo = new ArrayList<RunmanRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActRunmanRecord record : list){
				RunmanRecordVo recordVo = new RunmanRecordVo();
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
		ExportExcel<RunmanRecordVo> ex = new ExportExcel<RunmanRecordVo>();
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
		List<ActRunmanRecord> list = actRunmanRecordDAO.findAllByScore(activityId, startRow, pageSize);
		String[] headers = {"序号", "最高分", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称"};
		List<RunmanRankVo> listVo = new ArrayList<RunmanRankVo>();
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActRunmanRecord record : list){
				RunmanRankVo rankVo = new RunmanRankVo();
				rankVo.setId(i++);
				rankVo.setBestScore(record.getBestScore());
				rankVo.setMailAddress(record.getMailAddress());
				rankVo.setQq(record.getQq());
				rankVo.setWechatId(record.getWechatId());
				rankVo.setOtherInfo(record.getOtherInfo());
				rankVo.setTel(record.getTel());
				rankVo.setUsername(record.getUsername());
				listVo.add(rankVo);
			}
		}
		ExportExcel<RunmanRankVo> ex = new ExportExcel<RunmanRankVo>();
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
	public int countAllRecord(Integer activityId) {
		return actRunmanRecordDAO.countAllPrizeRecord(activityId);
	}

	@Override
	public int countAllScore(Integer activityId) {
		return actRunmanRecordDAO.countAllScore(activityId);
	}

	@Override
	public String changeOpStatus(Integer id) {
		String op = actRunmanRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actRunmanRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actRunmanRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

	@Override
	public int updateBestScore(Integer id, Integer score) {
		return actRunmanRecordDAO.updateBestScore(id, score);
	}

	@Override
	public void savePrize(Integer activityId, String awards) {
		List<RunmanAwardsVo> runmanAwardsVoList = parseJson(awards);
		for (RunmanAwardsVo runmanAwardsVo : runmanAwardsVoList) {
			ActRunmanPrize actRunmanPrize = actRunmanPrizeDAO.findByActivityId(activityId, 
					runmanAwardsVo.getPrizeType());
			if(actRunmanPrize == null){
				Integer deliverPrizeNum = actRunmanRecordDAO.countPrizeNumber(activityId, runmanAwardsVo.getPrizeType());
				if(deliverPrizeNum == null)deliverPrizeNum = 0;
				
				actRunmanPrize = new ActRunmanPrize();
				actRunmanPrize.setActivityId(activityId);
				actRunmanPrize.setPrizeType(runmanAwardsVo.getPrizeType());
				actRunmanPrize.setRealNum(runmanAwardsVo.getRealNum());
				actRunmanPrize.setDeliverNum(deliverPrizeNum);
				actRunmanPrize.setVersion(1);
				actRunmanPrize.setFinalTime(new Date());
				actRunmanPrizeDAO.add(actRunmanPrize);
			}else{
				actRunmanPrizeDAO.upByStr(actRunmanPrize, " deliverNum = " 
				+ actRunmanPrize.getDeliverNum() + ",realNum = " + runmanAwardsVo.getRealNum());
			}
		}
	}
}
