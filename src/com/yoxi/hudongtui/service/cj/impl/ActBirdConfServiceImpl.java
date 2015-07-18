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
import com.yoxi.hudongtui.dao.cj.bird.ActBirdConfDAO;
import com.yoxi.hudongtui.dao.cj.bird.ActBirdPrizeDAO;
import com.yoxi.hudongtui.dao.cj.bird.ActBirdRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.bird.ActBirdConf;
import com.yoxi.hudongtui.model.cj.bird.ActBirdPrize;
import com.yoxi.hudongtui.model.cj.bird.ActBirdRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActBirdConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.bird.BirdAwardsVo;
import com.yoxi.hudongtui.vo.cj.bird.BirdRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActBirdConfServiceImpl implements IActBirdConfService {

	@Autowired
	private ActBirdConfDAO actBirdConfDAO;
	@Autowired
	private ActBirdPrizeDAO actBirdPrizeDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private ActBirdRecordDAO actBirdRecordDAO;
	
	@Override
	@Transactional
	public Integer save(ActBirdConf actBirdConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actBirdConf.getName());
		pluginAct.setStartTime(actBirdConf.getStartTime());
		pluginAct.setEndTime(actBirdConf.getEndTime());
		pluginAct.setIcon(actBirdConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actBirdConf.setActivityId(activityId);
			ret = actBirdConfDAO.add(actBirdConf).intValue();
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
	public Integer update(ActBirdConf actBirdConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actBirdConf.getActivityId());
		infoVo.setIcon(actBirdConf.getShareImgUrl());
		infoVo.setStartTime(actBirdConf.getStartTime());
		infoVo.setEndTime(actBirdConf.getEndTime());
		infoVo.setTitle(actBirdConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actBirdConfDAO.update(actBirdConf);
		return ret;
	}

	@Override
	public ActBirdConf findByActId(Integer activityId) {
		ActBirdConf actBirdConf = actBirdConfDAO.findByActivityId(activityId);
		return actBirdConf;
	}
	
	@Override
	public Pagination<ActBirdRecord> getRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actBirdRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActBirdRecord> recordVos = actBirdRecordDAO.getRecord(activityId, startRow, pageSize);
		return new Pagination<ActBirdRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActBirdRecord> list = actBirdRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖品名称", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		List<BirdRecordVo> listVo = new ArrayList<BirdRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActBirdRecord record : list){
				BirdRecordVo recordVo = new BirdRecordVo();
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
		ExportExcel<BirdRecordVo> ex = new ExportExcel<BirdRecordVo>();
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
		return actBirdRecordDAO.countAllPrizeRecord(activityId);
	}

	@Override
	public String changeOpStatus(Integer id) {
		String op = actBirdRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actBirdRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actBirdRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

	@Override
	public List<BirdAwardsVo> parseJson(String awards) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<BirdAwardsVo>>(){}.getType();
		List<BirdAwardsVo> birdAwardsVoList = gson.fromJson(awards, type);
		return birdAwardsVoList;
	}
	
	@Override
	public void savePrize(Integer activityId, String awards) {
		List<BirdAwardsVo> birdAwardsVoList = parseJson(awards);
		for (BirdAwardsVo birdAwardsVo : birdAwardsVoList) {
			ActBirdPrize actBirdPrize = actBirdPrizeDAO.findByActivityId(activityId, 
					birdAwardsVo.getPrizeType());
			if(actBirdPrize == null){
				Integer deliverPrizeNum = actBirdRecordDAO.countPrizeNumber(activityId,
						birdAwardsVo.getPrizeType());
				if(deliverPrizeNum == null)deliverPrizeNum = 0;
				
				actBirdPrize = new ActBirdPrize();
				actBirdPrize.setActivityId(activityId);
				actBirdPrize.setPrizeType(birdAwardsVo.getPrizeType());
				actBirdPrize.setRealNum(birdAwardsVo.getRealNum());
				actBirdPrize.setDeliverNum(deliverPrizeNum);
				actBirdPrize.setVersion(1);
				actBirdPrize.setFinalTime(new Date());
				actBirdPrizeDAO.add(actBirdPrize);
			}else{
				actBirdPrizeDAO.upByStr(actBirdPrize, " deliverNum = " 
				+ actBirdPrize.getDeliverNum() + ",realNum = " + birdAwardsVo.getRealNum());
			}
		}
	}
}
