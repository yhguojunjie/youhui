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
import com.yoxi.hudongtui.dao.cj.ActGiftboxConfDAO;
import com.yoxi.hudongtui.dao.cj.ActGiftboxPrizeDAO;
import com.yoxi.hudongtui.dao.cj.ActGiftboxRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.ActGiftboxConf;
import com.yoxi.hudongtui.model.cj.ActGiftboxPrize;
import com.yoxi.hudongtui.model.cj.ActGiftboxRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActGiftboxConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.giftbox.GiftboxAwardsVo;
import com.yoxi.hudongtui.vo.cj.giftbox.GiftboxJoinVo;
import com.yoxi.hudongtui.vo.cj.giftbox.GiftboxRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActGiftboxConfServiceImpl implements IActGiftboxConfService{

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ActGiftboxConfDAO actGiftboxConfDAO;
	@Autowired
	private ActGiftboxRecordDAO actGiftboxRecordDAO;
	@Autowired
	private ActGiftboxPrizeDAO actGiftboxPrizeDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Override
	@Transactional
	public Integer save(ActGiftboxConf actGiftboxConf, Integer userPluginId) throws Exception{
		
		//保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actGiftboxConf.getName());
		pluginAct.setStartTime(actGiftboxConf.getStartTime());
		pluginAct.setEndTime(actGiftboxConf.getEndTime());
		pluginAct.setIcon(actGiftboxConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actGiftboxConf.setActivityId(activityId);
			ret = actGiftboxConfDAO.add(actGiftboxConf).intValue();
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
	public Integer update(ActGiftboxConf actGiftboxConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actGiftboxConf.getActivityId());
		infoVo.setIcon(actGiftboxConf.getShareImgUrl());
		infoVo.setStartTime(actGiftboxConf.getStartTime());
		infoVo.setEndTime(actGiftboxConf.getEndTime());
		infoVo.setTitle(actGiftboxConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actGiftboxConfDAO.update(actGiftboxConf);
		return ret;
	}

	@Override
	public ActGiftboxConf findByActId(Integer activityId) {
		ActGiftboxConf actGiftboxConf = actGiftboxConfDAO.findByActivityId(activityId);
		return actGiftboxConf;
	}

	@Override
	public List<GiftboxAwardsVo> parseJson(String content) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<GiftboxAwardsVo>>(){}.getType();
		List<GiftboxAwardsVo> giftboxAwardsVoList = gson.fromJson(content, type);
		return giftboxAwardsVoList;
	}

	@Override
	public Pagination<ActGiftboxRecord> getRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actGiftboxRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActGiftboxRecord> recordVos = actGiftboxRecordDAO.getRecord(activityId, startRow, pageSize);
		return new Pagination<ActGiftboxRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActGiftboxRecord> list = actGiftboxRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖项", "奖品名称", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		String u[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		List<GiftboxRecordVo> listVo = new ArrayList<GiftboxRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActGiftboxRecord record : list){
				GiftboxRecordVo recordVo = new GiftboxRecordVo();
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
		ExportExcel<GiftboxRecordVo> ex = new ExportExcel<GiftboxRecordVo>();
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
		return actGiftboxRecordDAO.countAllPrizeRecord(activityId);
	}
	
	@Override
	public String changeOpStatus(Integer id) {
		String op = actGiftboxRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actGiftboxRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actGiftboxRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

	@Override
	public String findAwards(Integer activityId) {
		return actGiftboxConfDAO.findAwards(activityId);
	}

	@Override
	public int addRecord(Integer activityId, GiftboxRecordVo recordVo) {
		ActGiftboxRecord giftboxRecord = new ActGiftboxRecord();
		giftboxRecord.setActivityId(activityId);
		giftboxRecord.setMpOpenId("");
		giftboxRecord.setPrizeName(recordVo.getPrizeName());
		giftboxRecord.setPrizeType(Integer.parseInt(recordVo.getPrizeType()));
		giftboxRecord.setTel(recordVo.getTel());
		giftboxRecord.setExchangeTime(new Date());
		giftboxRecord.setOpState("1");//已兑换
		return actGiftboxRecordDAO.add(giftboxRecord).intValue();
	}

	@Override
	public Pagination<ActGiftboxRecord> getJoinPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actGiftboxRecordDAO.getJoinCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActGiftboxRecord> recordVos = actGiftboxRecordDAO.getJoin(activityId, startRow, pageSize);
		return new Pagination<ActGiftboxRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportJoin(Integer activityId, Invocation inv,
			Integer startRow, Integer pageSize) {
		List<ActGiftboxRecord> list = actGiftboxRecordDAO.findJoinAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "邮寄地址", "qq", "微信号", "其他信息", "手机号 ", "用户名称", "是否中奖"};
		List<GiftboxJoinVo> listVo = new ArrayList<GiftboxJoinVo>();
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActGiftboxRecord record : list){
				GiftboxJoinVo recordVo = new GiftboxJoinVo();
				recordVo.setId(i++);
				recordVo.setMailAddress(record.getMailAddress());
				recordVo.setQq(record.getQq());
				recordVo.setWechatId(record.getWechatId());
				recordVo.setOtherInfo(record.getOtherInfo());
				recordVo.setTel(record.getTel());
				recordVo.setUsername(record.getUsername());
				if(record.getPrizeType() == null || record.getPrizeType() == 0){
					recordVo.setIsPrize("否");
				}else{
					recordVo.setIsPrize("是");
				}
				listVo.add(recordVo);
			}
		}
		ExportExcel<GiftboxJoinVo> ex = new ExportExcel<GiftboxJoinVo>();
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
		List<GiftboxAwardsVo> giftboxAwardsVoList = parseJson(awards);
		for (GiftboxAwardsVo giftboxAwardsVo : giftboxAwardsVoList) {
			ActGiftboxPrize actGiftboxPrize = actGiftboxPrizeDAO.findByActivityId(activityId, 
					giftboxAwardsVo.getPrizeType());
			if(actGiftboxPrize == null){
				Integer deliverPrizeNum = actGiftboxRecordDAO.countPrizeNumber(activityId, giftboxAwardsVo.getPrizeType());
				if(deliverPrizeNum == null)deliverPrizeNum = 0;
				
				actGiftboxPrize = new ActGiftboxPrize();
				actGiftboxPrize.setActivityId(activityId);
				actGiftboxPrize.setPrizeType(giftboxAwardsVo.getPrizeType());
				actGiftboxPrize.setRealNum(giftboxAwardsVo.getRealNum());
				actGiftboxPrize.setDeliverNum(deliverPrizeNum);
				actGiftboxPrize.setVersion(1);
				actGiftboxPrize.setFinalTime(new Date());
				actGiftboxPrizeDAO.add(actGiftboxPrize);
			}else{
				actGiftboxPrizeDAO.upByStr(actGiftboxPrize, " deliverNum = " 
				+ actGiftboxPrize.getDeliverNum() + ",realNum = " + giftboxAwardsVo.getRealNum());
			}
		}
	}
}
