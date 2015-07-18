package com.yoxi.hudongtui.service.cj.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.rmi.ServerException;
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
import com.yoxi.hudongtui.dao.cj.ActGivegiftConfDAO;
import com.yoxi.hudongtui.dao.cj.ActGivegiftRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.ActGivegiftConf;
import com.yoxi.hudongtui.model.cj.ActGivegiftRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActGivegiftConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.givegift.GiftContentVo;
import com.yoxi.hudongtui.vo.cj.givegift.GivegiftRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActGivegiftConfServiceImpl implements IActGivegiftConfService {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ActGivegiftConfDAO actGivegiftConfDAO;
	@Autowired
	private ActGivegiftRecordDAO actGivegiftRecordDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Override
	@Transactional
	public Integer save(ActGivegiftConf actGivegiftConf, Integer userPluginId) throws Exception{
		
		//保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actGivegiftConf.getTitle());
		pluginAct.setStartTime(actGivegiftConf.getStartTime());
		pluginAct.setEndTime(actGivegiftConf.getEndTime());
		pluginAct.setIcon(actGivegiftConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actGivegiftConf.setActivityId(activityId);
			ret = actGivegiftConfDAO.add(actGivegiftConf).intValue();
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
	public Integer update(ActGivegiftConf actGivegiftConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actGivegiftConf.getActivityId());
		infoVo.setIcon(actGivegiftConf.getShareImgUrl());
		infoVo.setStartTime(actGivegiftConf.getStartTime());
		infoVo.setEndTime(actGivegiftConf.getEndTime());
		infoVo.setTitle(actGivegiftConf.getTitle());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actGivegiftConfDAO.update(actGivegiftConf);
		return ret;
	}
	
	@Override
	public ActGivegiftConf findByActId(Integer activityId) throws ServerException{
		ActGivegiftConf actGivegiftConf = actGivegiftConfDAO.findByActivityId(activityId);
		if(actGivegiftConf == null){
			throw new ServerException("活动配置获取失败");
		}
		return actGivegiftConf;
	}

	@Override
	public List<GiftContentVo> parseJson(String content) {
		if(content == null){
			return null;
		}
		Gson gson = new Gson();
		Type type = new TypeToken<List<GiftContentVo>>(){}.getType();
		List<GiftContentVo> giftContentVoList = gson.fromJson(content, type);
		return giftContentVoList;
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActGivegiftRecord> list = actGivegiftRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖品名称", "用户信息", "中奖时间", "兑换时间", "操作"};
		List<GivegiftRecordVo> listVo = new ArrayList<GivegiftRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActGivegiftRecord record : list){
				GivegiftRecordVo recordVo = new GivegiftRecordVo();
				recordVo.setId(i++);
				recordVo.setPrizeName(record.getPrizeName());
				recordVo.setTel(record.getTel());
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
		ExportExcel<GivegiftRecordVo> ex = new ExportExcel<GivegiftRecordVo>();
		String ioTmpdir = System.getProperty("java.io.tmpdir");
		String crruentTime = DateUtils.getCurrentTime();
		String tempFilePath = ioTmpdir+"//"+crruentTime+".xls";
		System.out.println(tempFilePath);
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
		return actGivegiftRecordDAO.countAllPrizeRecord(activityId);
	}

	@Override
	public Pagination<ActGivegiftRecord> getRecordPage(Integer activityId, int currPage,
			int pageSize) {
		// 获取总数
		int totalCount = actGivegiftRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActGivegiftRecord> recordVos = actGivegiftRecordDAO.getRecord(activityId, startRow, pageSize);
		return new Pagination<ActGivegiftRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public String changeOpStatus(Integer id) {
		String op = actGivegiftRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actGivegiftRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actGivegiftRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

}
