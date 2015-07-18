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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yoxi.hudongtui.dao.cj.poker.ActPokerConfDAO;
import com.yoxi.hudongtui.dao.cj.poker.ActPokerPrizeDAO;
import com.yoxi.hudongtui.dao.cj.poker.ActPokerRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.poker.ActPokerConf;
import com.yoxi.hudongtui.model.cj.poker.ActPokerPrize;
import com.yoxi.hudongtui.model.cj.poker.ActPokerRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActPokerConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.poker.PokerAwardsVo;
import com.yoxi.hudongtui.vo.cj.poker.PokerJoinVo;
import com.yoxi.hudongtui.vo.cj.poker.PokerRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActPokerConfServiceImpl implements IActPokerConfService {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ActPokerConfDAO actPokerConfDAO;
	@Autowired
	private ActPokerRecordDAO actPokerRecordDAO;
	@Autowired
	private ActPokerPrizeDAO actPokerPrizeDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Override
	@Transactional
	public Integer save(ActPokerConf actPokerConf, Integer userPluginId) throws Exception{
		
		//保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actPokerConf.getName());
		pluginAct.setStartTime(actPokerConf.getStartTime());
		pluginAct.setEndTime(actPokerConf.getEndTime());
		pluginAct.setIcon(actPokerConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actPokerConf.setActivityId(activityId);
			ret = actPokerConfDAO.add(actPokerConf).intValue();
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
	public Integer update(ActPokerConf actPokerConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actPokerConf.getActivityId());
		infoVo.setIcon(actPokerConf.getShareImgUrl());
		infoVo.setStartTime(actPokerConf.getStartTime());
		infoVo.setEndTime(actPokerConf.getEndTime());
		infoVo.setTitle(actPokerConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actPokerConfDAO.update(actPokerConf);
		return ret;
	}

	@Override
	public ActPokerConf findByActId(Integer activityId) {
		ActPokerConf actPokerConf = actPokerConfDAO.findByActivityId(activityId);
		return actPokerConf;
	}

	@Override
	public List<PokerAwardsVo> parseJson(String content) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<PokerAwardsVo>>(){}.getType();
		List<PokerAwardsVo> giftboxAwardsVoList = gson.fromJson(content, type);
		return giftboxAwardsVoList;
	}

	@Override
	public Pagination<ActPokerRecord> getRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actPokerRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActPokerRecord> recordVos = actPokerRecordDAO.getRecord(activityId, startRow, pageSize);
		return new Pagination<ActPokerRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActPokerRecord> list = actPokerRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖品名称", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		List<PokerRecordVo> listVo = new ArrayList<PokerRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActPokerRecord record : list){
				PokerRecordVo recordVo = new PokerRecordVo();
				recordVo.setId(i++);
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
		ExportExcel<PokerRecordVo> ex = new ExportExcel<PokerRecordVo>();
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
		log.debug("excel生成成功");
	}

	@Override
	public int countAllRecord(Integer activityId) {
		return actPokerRecordDAO.countAllPrizeRecord(activityId);
	}
	
	@Override
	public String changeOpStatus(Integer id) {
		String op = actPokerRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actPokerRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actPokerRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

	@Override
	public String findAwards(Integer activityId) {
		return actPokerConfDAO.findAwards(activityId);
	}

	@Override
	public int addRecord(Integer activityId, PokerRecordVo recordVo) {
		ActPokerRecord giftboxRecord = new ActPokerRecord();
		giftboxRecord.setActivityId(activityId);
		giftboxRecord.setMpOpenId("");
		giftboxRecord.setPrizeName(recordVo.getPrizeName());
		giftboxRecord.setTel(recordVo.getTel());
		giftboxRecord.setPrizeTime(new Date());
		giftboxRecord.setExchangeTime(new Date());
		giftboxRecord.setOpState("1");//已兑换
		return actPokerRecordDAO.add(giftboxRecord).intValue();
	}

	@Override
	public Pagination<ActPokerRecord> getJoinPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actPokerRecordDAO.getJoinCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActPokerRecord> recordVos = actPokerRecordDAO.getJoin(activityId, startRow, pageSize);
		return new Pagination<ActPokerRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportJoin(Integer activityId, Invocation inv,
			Integer startRow, Integer pageSize) {
		List<ActPokerRecord> list = actPokerRecordDAO.findJoinAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "邮寄地址", "qq", "微信号", "其他信息", "手机号 ", "用户名称", "是否中奖"};
		List<PokerJoinVo> listVo = new ArrayList<PokerJoinVo>();
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActPokerRecord record : list){
				PokerJoinVo recordVo = new PokerJoinVo();
				recordVo.setId(i++);
				recordVo.setMailAddress(record.getMailAddress());
				recordVo.setQq(record.getQq());
				recordVo.setWechatId(record.getWechatId());
				recordVo.setOtherInfo(record.getOtherInfo());
				recordVo.setTel(record.getTel());
				recordVo.setUsername(record.getUsername());
				if(record.getPrizeTime() == null){
					recordVo.setIsPrize("否");
				}else{
					recordVo.setIsPrize("是");
				}
				listVo.add(recordVo);
			}
		}
		ExportExcel<PokerJoinVo> ex = new ExportExcel<PokerJoinVo>();
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
			ex.exportExcel("参与人数",headers, listVo, out);
			ExportUtil.ExcelFileExport(inv, tempFilePath,crruentTime+".xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("excel生成成功");
	}

	@Override
	public void savePrize(Integer activityId, String awards) {
		List<PokerAwardsVo> pokerAwardsVoList = parseJson(awards);
		for (PokerAwardsVo pokerAwardsVo : pokerAwardsVoList) {
			ActPokerPrize actPokerPrize = actPokerPrizeDAO.findByActivityId(activityId);
			if(actPokerPrize == null){
				Integer deliverPrizeNum = actPokerRecordDAO.getPrizeTotal(activityId);
				if(deliverPrizeNum == null)deliverPrizeNum = 0;
				
				actPokerPrize = new ActPokerPrize();
				actPokerPrize.setActivityId(activityId);
				actPokerPrize.setRealNum(pokerAwardsVo.getRealNum());
				actPokerPrize.setDeliverNum(deliverPrizeNum);
				actPokerPrize.setVersion(1);
				actPokerPrize.setFinalTime(new Date());
				actPokerPrizeDAO.add(actPokerPrize);
			}else{
				actPokerPrizeDAO.upByStr(actPokerPrize, " deliverNum = " 
				+ actPokerPrize.getDeliverNum() + ",realNum = " + pokerAwardsVo.getRealNum());
			}
		}
	}
}
