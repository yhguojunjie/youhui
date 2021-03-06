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
import com.yoxi.hudongtui.dao.cj.ripbrand.ActRipBrandConfDAO;
import com.yoxi.hudongtui.dao.cj.ripbrand.ActRipBrandPrizeDAO;
import com.yoxi.hudongtui.dao.cj.ripbrand.ActRipBrandRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.ripbrand.ActRipBrandConf;
import com.yoxi.hudongtui.model.cj.ripbrand.ActRipBrandPrize;
import com.yoxi.hudongtui.model.cj.ripbrand.ActRipBrandRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActRipBrandConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.ripbrand.RipBrandAwardsVo;
import com.yoxi.hudongtui.vo.cj.ripbrand.RipBrandRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActRipBrandConfServiceImpl implements IActRipBrandConfService {

	@Autowired
	private ActRipBrandConfDAO actRipBrandConfDAO;
	@Autowired
	private ActRipBrandPrizeDAO actRipBrandPrizeDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private ActRipBrandRecordDAO actRipBrandRecordDAO;
	
	@Override
	@Transactional
	public Integer save(ActRipBrandConf actRipBrandConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actRipBrandConf.getName());
		pluginAct.setStartTime(actRipBrandConf.getStartTime());
		pluginAct.setEndTime(actRipBrandConf.getEndTime());
		pluginAct.setIcon(actRipBrandConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actRipBrandConf.setActivityId(activityId);
			ret = actRipBrandConfDAO.add(actRipBrandConf).intValue();
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
	public Integer update(ActRipBrandConf actRipBrandConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actRipBrandConf.getActivityId());
		infoVo.setIcon(actRipBrandConf.getShareImgUrl());
		infoVo.setStartTime(actRipBrandConf.getStartTime());
		infoVo.setEndTime(actRipBrandConf.getEndTime());
		infoVo.setTitle(actRipBrandConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actRipBrandConfDAO.update(actRipBrandConf);
		return ret;
	}

	@Override
	public ActRipBrandConf findByActId(Integer activityId) {
		ActRipBrandConf actRipBrandConf = actRipBrandConfDAO.findByActivityId(activityId);
		return actRipBrandConf;
	}
	
	@Override
	public Pagination<ActRipBrandRecord> getRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actRipBrandRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActRipBrandRecord> recordVos = actRipBrandRecordDAO.getRecord(activityId, startRow, pageSize);
		return new Pagination<ActRipBrandRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActRipBrandRecord> list = actRipBrandRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖品名称", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		List<RipBrandRecordVo> listVo = new ArrayList<RipBrandRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActRipBrandRecord record : list){
				RipBrandRecordVo recordVo = new RipBrandRecordVo();
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
		ExportExcel<RipBrandRecordVo> ex = new ExportExcel<RipBrandRecordVo>();
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
		return actRipBrandRecordDAO.countAllPrizeRecord(activityId);
	}

	@Override
	public String changeOpStatus(Integer id) {
		String op = actRipBrandRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actRipBrandRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actRipBrandRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

	@Override
	public List<RipBrandAwardsVo> parseJson(String awards) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<RipBrandAwardsVo>>(){}.getType();
		List<RipBrandAwardsVo> birdAwardsVoList = gson.fromJson(awards, type);
		return birdAwardsVoList;
	}
	
	@Override
	public void savePrize(Integer activityId, String awards) {
		List<RipBrandAwardsVo> birdAwardsVoList = parseJson(awards);
		for (RipBrandAwardsVo birdAwardsVo : birdAwardsVoList) {
			ActRipBrandPrize actRipBrandPrize = actRipBrandPrizeDAO.findByActivityId(activityId, 
					birdAwardsVo.getPrizeType());
			if(actRipBrandPrize == null){
				Integer deliverPrizeNum = actRipBrandRecordDAO.countPrizeNumber(activityId,
						birdAwardsVo.getPrizeType());
				if(deliverPrizeNum == null)deliverPrizeNum = 0;
				
				actRipBrandPrize = new ActRipBrandPrize();
				actRipBrandPrize.setActivityId(activityId);
				actRipBrandPrize.setPrizeType(birdAwardsVo.getPrizeType());
				actRipBrandPrize.setRealNum(birdAwardsVo.getRealNum());
				actRipBrandPrize.setDeliverNum(deliverPrizeNum);
				actRipBrandPrize.setVersion(1);
				actRipBrandPrize.setFinalTime(new Date());
				actRipBrandPrizeDAO.add(actRipBrandPrize);
			}else{
				actRipBrandPrizeDAO.upByStr(actRipBrandPrize, " deliverNum = " 
				+ actRipBrandPrize.getDeliverNum() + ",realNum = " + birdAwardsVo.getRealNum());
			}
		}
	}

}
