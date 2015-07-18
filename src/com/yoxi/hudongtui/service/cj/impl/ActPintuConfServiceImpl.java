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
import com.yoxi.hudongtui.dao.cj.ActPintuConfDAO;
import com.yoxi.hudongtui.dao.cj.ActPintuPrizeDAO;
import com.yoxi.hudongtui.dao.cj.ActPintuRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.ActPintuConf;
import com.yoxi.hudongtui.model.cj.ActPintuPrize;
import com.yoxi.hudongtui.model.cj.ActPintuRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActPintuConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.pintu.PintuAwardsVo;
import com.yoxi.hudongtui.vo.cj.pintu.PintuRankVo;
import com.yoxi.hudongtui.vo.cj.pintu.PintuRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActPintuConfServiceImpl implements IActPintuConfService {

	@Autowired
	private ActPintuConfDAO actPintuConfDAO;
	@Autowired
	private ActPintuPrizeDAO actPintuPrizeDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private ActPintuRecordDAO actPintuRecordDAO;
	
	@Override
	@Transactional
	public Integer save(ActPintuConf actPintuConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actPintuConf.getTitle());
		pluginAct.setStartTime(actPintuConf.getStartTime());
		pluginAct.setEndTime(actPintuConf.getEndTime());
		pluginAct.setIcon(actPintuConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actPintuConf.setActivityId(activityId);
			ret = actPintuConfDAO.add(actPintuConf).intValue();
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
	public Integer update(ActPintuConf actPintuConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actPintuConf.getActivityId());
		infoVo.setIcon(actPintuConf.getShareImgUrl());
		infoVo.setStartTime(actPintuConf.getStartTime());
		infoVo.setEndTime(actPintuConf.getEndTime());
		infoVo.setTitle(actPintuConf.getTitle());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actPintuConfDAO.update(actPintuConf);
		return ret;
	}

	@Override
	public ActPintuConf findByActId(Integer activityId) {
		ActPintuConf actPintuConf = actPintuConfDAO.findByActivityId(activityId);
		return actPintuConf;
	}

	@Override
	public List<PintuAwardsVo> parseJson(String content) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<PintuAwardsVo>>(){}.getType();
		List<PintuAwardsVo> pintuAwardsVoList = gson.fromJson(content, type);
		return pintuAwardsVoList;
	}
	
	@Override
	public Pagination<ActPintuRecord> getRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actPintuRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActPintuRecord> recordVos = actPintuRecordDAO.getRecord(activityId, startRow, pageSize);
		return new Pagination<ActPintuRecord>(totalCount, pageSize, currPage, recordVos);
	}
	
	@Override
	public Pagination<ActPintuRecord> getRankPage(Integer activityId,
		int currPage, int pageSize) {
		// 获取总数
		int totalCount = actPintuRecordDAO.getRankCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActPintuRecord> recordVos = actPintuRecordDAO.getRank(activityId, startRow, pageSize);
		return new Pagination<ActPintuRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActPintuRecord> list = actPintuRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖项", "奖品名称", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		String u[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		List<PintuRecordVo> listVo = new ArrayList<PintuRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActPintuRecord record : list){
				PintuRecordVo recordVo = new PintuRecordVo();
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
		ExportExcel<PintuRecordVo> ex = new ExportExcel<PintuRecordVo>();
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
		List<ActPintuRecord> list = actPintuRecordDAO.findAllByScore(activityId, startRow, pageSize);
		String[] headers = {"序号", "最高分", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称"};
		List<PintuRankVo> listVo = new ArrayList<PintuRankVo>();
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActPintuRecord record : list){
				PintuRankVo rankVo = new PintuRankVo();
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
		ExportExcel<PintuRankVo> ex = new ExportExcel<PintuRankVo>();
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
		return actPintuRecordDAO.countAllPrizeRecord(activityId);
	}

	@Override
	public int countAllScore(Integer activityId) {
		return actPintuRecordDAO.countAllScore(activityId);
	}

	@Override
	public String changeOpStatus(Integer id) {
		String op = actPintuRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actPintuRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actPintuRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

	@Override
	public int updateBestScore(Integer id, Integer score) {
		return actPintuRecordDAO.updateBestScore(id, score);
	}

	@Override
	public void savePrize(Integer activityId, String awards) {
		List<PintuAwardsVo> pintuAwardsVoList = parseJson(awards);
		for (PintuAwardsVo pintuAwardsVo : pintuAwardsVoList) {
			ActPintuPrize actPintuPrize = actPintuPrizeDAO.findByActivityId(activityId, 
					pintuAwardsVo.getPrizeType());
			if(actPintuPrize == null){
				Integer deliverPrizeNum = actPintuRecordDAO.countPrizeNumber(activityId, pintuAwardsVo.getPrizeType());
				if(deliverPrizeNum == null)deliverPrizeNum = 0;
				
				actPintuPrize = new ActPintuPrize();
				actPintuPrize.setActivityId(activityId);
				actPintuPrize.setPrizeType(pintuAwardsVo.getPrizeType());
				actPintuPrize.setRealNum(pintuAwardsVo.getRealNum());
				actPintuPrize.setDeliverNum(deliverPrizeNum);
				actPintuPrize.setVersion(1);
				actPintuPrize.setFinalTime(new Date());
				actPintuPrizeDAO.add(actPintuPrize);
			}else{
				actPintuPrizeDAO.upByStr(actPintuPrize, " deliverNum = " 
				+ actPintuPrize.getDeliverNum() + ",realNum = " + pintuAwardsVo.getRealNum());
			}
		}
	}
}
