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
import com.yoxi.hudongtui.dao.cj.receipt.ActReceiptConfDAO;
import com.yoxi.hudongtui.dao.cj.receipt.ActReceiptPrizeDAO;
import com.yoxi.hudongtui.dao.cj.receipt.ActReceiptRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.receipt.ActReceiptConf;
import com.yoxi.hudongtui.model.cj.receipt.ActReceiptPrize;
import com.yoxi.hudongtui.model.cj.receipt.ActReceiptRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActReceiptConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.receipt.ReceiptAwardsVo;
import com.yoxi.hudongtui.vo.cj.receipt.ReceiptRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActReceiptConfServiceImpl implements IActReceiptConfService {

	@Autowired
	private ActReceiptConfDAO actReceiptConfDAO;
	@Autowired
	private ActReceiptPrizeDAO actReceiptPrizeDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private ActReceiptRecordDAO actReceiptRecordDAO;
	
	@Override
	@Transactional
	public Integer save(ActReceiptConf actReceiptConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actReceiptConf.getName());
		pluginAct.setStartTime(actReceiptConf.getStartTime());
		pluginAct.setEndTime(actReceiptConf.getEndTime());
		pluginAct.setIcon(actReceiptConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actReceiptConf.setActivityId(activityId);
			ret = actReceiptConfDAO.add(actReceiptConf).intValue();
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
	public Integer update(ActReceiptConf actReceiptConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actReceiptConf.getActivityId());
		infoVo.setIcon(actReceiptConf.getShareImgUrl());
		infoVo.setStartTime(actReceiptConf.getStartTime());
		infoVo.setEndTime(actReceiptConf.getEndTime());
		infoVo.setTitle(actReceiptConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actReceiptConfDAO.update(actReceiptConf);
		return ret;
	}

	@Override
	public ActReceiptConf findByActId(Integer activityId) {
		ActReceiptConf actReceiptConf = actReceiptConfDAO.findByActivityId(activityId);
		return actReceiptConf;
	}
	
	@Override
	public Pagination<ActReceiptRecord> getRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actReceiptRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActReceiptRecord> recordVos = actReceiptRecordDAO.getRecord(activityId, startRow, pageSize);
		return new Pagination<ActReceiptRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActReceiptRecord> list = actReceiptRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖品名称", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		List<ReceiptRecordVo> listVo = new ArrayList<ReceiptRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActReceiptRecord record : list){
				ReceiptRecordVo recordVo = new ReceiptRecordVo();
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
		ExportExcel<ReceiptRecordVo> ex = new ExportExcel<ReceiptRecordVo>();
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
		return actReceiptRecordDAO.countAllPrizeRecord(activityId);
	}

	@Override
	public String changeOpStatus(Integer id) {
		String op = actReceiptRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actReceiptRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actReceiptRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

	@Override
	public List<ReceiptAwardsVo> parseJson(String awards) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<ReceiptAwardsVo>>(){}.getType();
		List<ReceiptAwardsVo> birdAwardsVoList = gson.fromJson(awards, type);
		return birdAwardsVoList;
	}
	
	@Override
	public void savePrize(Integer activityId, String awards) {
		List<ReceiptAwardsVo> birdAwardsVoList = parseJson(awards);
		for (ReceiptAwardsVo birdAwardsVo : birdAwardsVoList) {
			ActReceiptPrize actReceiptPrize = actReceiptPrizeDAO.findByActivityId(activityId, 
					birdAwardsVo.getPrizeType());
			if(actReceiptPrize == null){
				Integer deliverPrizeNum = actReceiptRecordDAO.countPrizeNumber(activityId,
						birdAwardsVo.getPrizeType());
				if(deliverPrizeNum == null)deliverPrizeNum = 0;
				
				actReceiptPrize = new ActReceiptPrize();
				actReceiptPrize.setActivityId(activityId);
				actReceiptPrize.setPrizeType(birdAwardsVo.getPrizeType());
				actReceiptPrize.setRealNum(birdAwardsVo.getRealNum());
				actReceiptPrize.setDeliverNum(deliverPrizeNum);
				actReceiptPrize.setVersion(1);
				actReceiptPrize.setFinalTime(new Date());
				actReceiptPrizeDAO.add(actReceiptPrize);
			}else{
				actReceiptPrizeDAO.upByStr(actReceiptPrize, " deliverNum = " 
				+ actReceiptPrize.getDeliverNum() + ",realNum = " + birdAwardsVo.getRealNum());
			}
		}
	}
}
