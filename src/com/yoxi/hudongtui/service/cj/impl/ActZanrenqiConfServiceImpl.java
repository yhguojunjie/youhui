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
import com.yoxi.hudongtui.dao.cj.zanrenqi.ActZanrenqiConfDAO;
import com.yoxi.hudongtui.dao.cj.zanrenqi.ActZanrenqiPrizeDAO;
import com.yoxi.hudongtui.dao.cj.zanrenqi.ActZanrenqiRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.zanrenqi.ActZanrenqiConf;
import com.yoxi.hudongtui.model.cj.zanrenqi.ActZanrenqiPrize;
import com.yoxi.hudongtui.model.cj.zanrenqi.ActZanrenqiRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActZanrenqiConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.zanrenqi.ZanrenqiAwardsVo;
import com.yoxi.hudongtui.vo.cj.zanrenqi.ZanrenqiRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActZanrenqiConfServiceImpl implements IActZanrenqiConfService {

	@Autowired
	private ActZanrenqiConfDAO actZanrenqiConfDAO;
	@Autowired
	private ActZanrenqiPrizeDAO actZanrenqiPrizeDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private ActZanrenqiRecordDAO actZanrenqiRecordDAO;
	
	@Override
	@Transactional
	public Integer save(ActZanrenqiConf actZanrenqiConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actZanrenqiConf.getName());
		pluginAct.setStartTime(actZanrenqiConf.getStartTime());
		pluginAct.setEndTime(actZanrenqiConf.getEndTime());
		pluginAct.setIcon(actZanrenqiConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actZanrenqiConf.setActivityId(activityId);
			ret = actZanrenqiConfDAO.add(actZanrenqiConf).intValue();
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
	public Integer update(ActZanrenqiConf actZanrenqiConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actZanrenqiConf.getActivityId());
		infoVo.setIcon(actZanrenqiConf.getShareImgUrl());
		infoVo.setStartTime(actZanrenqiConf.getStartTime());
		infoVo.setEndTime(actZanrenqiConf.getEndTime());
		infoVo.setTitle(actZanrenqiConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actZanrenqiConfDAO.update(actZanrenqiConf);
		return ret;
	}

	@Override
	public ActZanrenqiConf findByActId(Integer activityId) {
		ActZanrenqiConf actZanrenqiConf = actZanrenqiConfDAO.findByActivityId(activityId);
		return actZanrenqiConf;
	}
	
	@Override
	public Pagination<ActZanrenqiRecord> getRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actZanrenqiRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActZanrenqiRecord> recordVos = actZanrenqiRecordDAO.getRecord(activityId, startRow, pageSize);
		return new Pagination<ActZanrenqiRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActZanrenqiRecord> list = actZanrenqiRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖品名称", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		List<ZanrenqiRecordVo> listVo = new ArrayList<ZanrenqiRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActZanrenqiRecord record : list){
				ZanrenqiRecordVo recordVo = new ZanrenqiRecordVo();
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
		ExportExcel<ZanrenqiRecordVo> ex = new ExportExcel<ZanrenqiRecordVo>();
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
		return actZanrenqiRecordDAO.countAllPrizeRecord(activityId);
	}

	@Override
	public String changeOpStatus(Integer id) {
		String op = actZanrenqiRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actZanrenqiRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actZanrenqiRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

	@Override
	public List<ZanrenqiAwardsVo> parseJson(String awards) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<ZanrenqiAwardsVo>>(){}.getType();
		List<ZanrenqiAwardsVo> zanrenqiAwardsVoList = gson.fromJson(awards, type);
		return zanrenqiAwardsVoList;
	}

	@Override
	public int addRecord(Integer activityId, ZanrenqiRecordVo recordVo) {
		ActZanrenqiRecord zanrenqiRecord = new ActZanrenqiRecord();
		zanrenqiRecord.setActivityId(activityId);
		zanrenqiRecord.setMpOpenId("");
		zanrenqiRecord.setPrizeType(recordVo.getPrizeType());
		zanrenqiRecord.setPrizeName(recordVo.getPrizeName());
		zanrenqiRecord.setTel(recordVo.getTel());
		zanrenqiRecord.setPrizeTime(new Date());
		zanrenqiRecord.setExchangeTime(new Date());
		zanrenqiRecord.setOpState("1");//已兑换
		return actZanrenqiRecordDAO.add(zanrenqiRecord).intValue();
	}

	@Override
	public String findAwards(Integer activityId) {
		return actZanrenqiConfDAO.findAwards(activityId);
	}
	
	@Override
	public void savePrize(Integer activityId, String awards) {
		List<ZanrenqiAwardsVo> zanrenqiAwardsVoList = parseJson(awards);
		for (ZanrenqiAwardsVo zanrenqiAwardsVo : zanrenqiAwardsVoList) {
			ActZanrenqiPrize actZanrenqiPrize = actZanrenqiPrizeDAO.findByActivityId(activityId, 
					zanrenqiAwardsVo.getPrizeType());
			if(actZanrenqiPrize == null){
				Integer deliverPrizeNum = actZanrenqiRecordDAO.countPrizeNumber(activityId,
						zanrenqiAwardsVo.getPrizeType());
				if(deliverPrizeNum == null)deliverPrizeNum = 0;
				
				actZanrenqiPrize = new ActZanrenqiPrize();
				actZanrenqiPrize.setActivityId(activityId);
				actZanrenqiPrize.setPrizeType(zanrenqiAwardsVo.getPrizeType());
				actZanrenqiPrize.setRealNum(zanrenqiAwardsVo.getRealNum());
				actZanrenqiPrize.setDeliverNum(deliverPrizeNum);
				actZanrenqiPrize.setVersion(1);
				actZanrenqiPrize.setFinalTime(new Date());
				actZanrenqiPrizeDAO.add(actZanrenqiPrize);
			}else{
				actZanrenqiPrizeDAO.upByStr(actZanrenqiPrize, " deliverNum = " 
				+ actZanrenqiPrize.getDeliverNum() + ",realNum = " + zanrenqiAwardsVo.getRealNum());
			}
		}
	}
}
