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
import com.yoxi.hudongtui.dao.cj.fingerprint.ActFingerprintConfDAO;
import com.yoxi.hudongtui.dao.cj.fingerprint.ActFingerprintPrizeDAO;
import com.yoxi.hudongtui.dao.cj.fingerprint.ActFingerprintRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.fingerprint.ActFingerprintConf;
import com.yoxi.hudongtui.model.cj.fingerprint.ActFingerprintPrize;
import com.yoxi.hudongtui.model.cj.fingerprint.ActFingerprintRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActFingerprintConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.fingerprint.FingerprintAwardsVo;
import com.yoxi.hudongtui.vo.cj.fingerprint.FingerprintRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActFingerprintConfServiceImpl implements IActFingerprintConfService {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ActFingerprintConfDAO actFingerprintConfDAO;
	@Autowired
	private ActFingerprintRecordDAO actFingerprintRecordDAO;
	@Autowired
	private ActFingerprintPrizeDAO actFingerprintPrizeDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Override
	@Transactional
	public Integer save(ActFingerprintConf actFingerprintConf, Integer userPluginId) throws Exception{
		
		//保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actFingerprintConf.getName());
		pluginAct.setStartTime(actFingerprintConf.getStartTime());
		pluginAct.setEndTime(actFingerprintConf.getEndTime());
		pluginAct.setIcon(actFingerprintConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actFingerprintConf.setActivityId(activityId);
			ret = actFingerprintConfDAO.add(actFingerprintConf).intValue();
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
	public Integer update(ActFingerprintConf actFingerprintConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actFingerprintConf.getActivityId());
		infoVo.setIcon(actFingerprintConf.getShareImgUrl());
		infoVo.setStartTime(actFingerprintConf.getStartTime());
		infoVo.setEndTime(actFingerprintConf.getEndTime());
		infoVo.setTitle(actFingerprintConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actFingerprintConfDAO.update(actFingerprintConf);
		return ret;
	}

	@Override
	public ActFingerprintConf findByActId(Integer activityId) {
		ActFingerprintConf actFingerprintConf = actFingerprintConfDAO.findByActivityId(activityId);
		return actFingerprintConf;
	}

	@Override
	public List<FingerprintAwardsVo> parseJson(String content) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<FingerprintAwardsVo>>(){}.getType();
		List<FingerprintAwardsVo> fingerprintAwardsVoList = gson.fromJson(content, type);
		return fingerprintAwardsVoList;
	}

	@Override
	public Pagination<ActFingerprintRecord> getRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actFingerprintRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActFingerprintRecord> recordVos = actFingerprintRecordDAO.getRecord(activityId, startRow, pageSize);
		return new Pagination<ActFingerprintRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActFingerprintRecord> list = actFingerprintRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖品名称", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		List<FingerprintRecordVo> listVo = new ArrayList<FingerprintRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActFingerprintRecord record : list){
				FingerprintRecordVo recordVo = new FingerprintRecordVo();
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
		ExportExcel<FingerprintRecordVo> ex = new ExportExcel<FingerprintRecordVo>();
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
		return actFingerprintRecordDAO.countAllPrizeRecord(activityId);
	}
	
	@Override
	public String changeOpStatus(Integer id) {
		String op = actFingerprintRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actFingerprintRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actFingerprintRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

	@Override
	public String findAwards(Integer activityId) {
		return actFingerprintConfDAO.findAwards(activityId);
	}

	@Override
	public int addRecord(Integer activityId, FingerprintRecordVo recordVo) {
		ActFingerprintRecord fingerprintRecord = new ActFingerprintRecord();
		fingerprintRecord.setActivityId(activityId);
		fingerprintRecord.setMpOpenId("");
		fingerprintRecord.setPrizeName(recordVo.getPrizeName());
		fingerprintRecord.setTel(recordVo.getTel());
		fingerprintRecord.setPrizeTime(new Date());
		fingerprintRecord.setExchangeTime(new Date());
		fingerprintRecord.setOpState("1");//已兑换
		return actFingerprintRecordDAO.add(fingerprintRecord).intValue();
	}

	@Override
	public void savePrize(Integer activityId, String awards) {
		List<FingerprintAwardsVo> fingerprintAwardsVoList = parseJson(awards);
		for (FingerprintAwardsVo fingerprintAwardsVo : fingerprintAwardsVoList) {
			ActFingerprintPrize actFingerprintPrize = actFingerprintPrizeDAO.findByActivityId(activityId);
			if(actFingerprintPrize == null){
				Integer deliverPrizeNum = actFingerprintRecordDAO.countPrizeNumber(activityId);
				if(deliverPrizeNum == null)deliverPrizeNum = 0;
				
				actFingerprintPrize = new ActFingerprintPrize();
				actFingerprintPrize.setActivityId(activityId);
				actFingerprintPrize.setRealNum(fingerprintAwardsVo.getRealNum());
				actFingerprintPrize.setDeliverNum(deliverPrizeNum);
				actFingerprintPrize.setVersion(1);
				actFingerprintPrize.setFinalTime(new Date());
				actFingerprintPrizeDAO.add(actFingerprintPrize);
			}else{
				actFingerprintPrizeDAO.upByStr(actFingerprintPrize, " deliverNum = " 
				+ actFingerprintPrize.getDeliverNum() + ",realNum = " + fingerprintAwardsVo.getRealNum());
			}
		}
	}
}
