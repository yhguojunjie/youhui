package com.yoxi.hudongtui.service.cj.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.paoding.rose.web.Invocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.hudongtui.dao.cj.bottle.ActBottleConfDAO;
import com.yoxi.hudongtui.dao.cj.bottle.ActBottleContentDAO;
import com.yoxi.hudongtui.dao.cj.bottle.ActBottleHelpDAO;
import com.yoxi.hudongtui.dao.cj.bottle.ActBottleRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.bottle.ActBottleConf;
import com.yoxi.hudongtui.model.cj.bottle.ActBottleContent;
import com.yoxi.hudongtui.model.cj.bottle.ActBottleRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActBottleConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.bottle.ActBottleRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;
import com.yoxi.hudongtui.vo.plugin.PluginShowActInfoVo;

@Service
public class ActBottleConfServiceImpl implements IActBottleConfService {

	@Autowired
	private ActBottleConfDAO actBottleConfDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private ActBottleRecordDAO actBottleRecordDAO;
	@Autowired
	private ActBottleContentDAO actBottleContentDAO;
	@Autowired
	private ActBottleHelpDAO actBottleHelpDAO;
	
	@Override
	@Transactional
	public Integer save(ActBottleConf actBottleConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actBottleConf.getName());
		pluginAct.setStartTime(actBottleConf.getStartTime());
		pluginAct.setEndTime(actBottleConf.getEndTime());
		pluginAct.setIcon(actBottleConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actBottleConf.setActivityId(activityId);
			ret = actBottleConfDAO.add(actBottleConf).intValue();
			//更新用户插件表活动数
			if(ret != 0){
				String upstr = " actNum = actNum + 1 ";
				userPluginDAO.upByStr(userPluginId, upstr);
			}
			
			PluginShowActInfoVo showActInfoVo = pluginActDAO.findShowActId(activityId);
			if(showActInfoVo != null && showActInfoVo.getShowActId() != null){
				List<ActBottleContent> contentList =  actBottleContentDAO.getContentList(showActInfoVo.getShowActId(), 0, 10);
				for (ActBottleContent actBottleContent : contentList) {
					actBottleContent.setActivityId(activityId);
					actBottleContent.setUpdateTime(new Date());
					actBottleContentDAO.add(actBottleContent);
				}
			}
		}
		return ret;
	}

	@Override
	@Transactional
	public Integer update(ActBottleConf actBottleConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actBottleConf.getActivityId());
		infoVo.setIcon(actBottleConf.getShareImgUrl());
		infoVo.setStartTime(actBottleConf.getStartTime());
		infoVo.setEndTime(actBottleConf.getEndTime());
		infoVo.setTitle(actBottleConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actBottleConfDAO.update(actBottleConf);
		return ret;
	}

	@Override
	public ActBottleConf findByActId(Integer activityId) {
		ActBottleConf actBottleConf = actBottleConfDAO.findByActivityId(activityId);
		return actBottleConf;
	}
	

	@Override
	public Pagination<ActBottleContent> getContentPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actBottleContentDAO.getContentCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActBottleContent> recordVos = actBottleContentDAO.getContent(activityId, startRow, pageSize);
		return new Pagination<ActBottleContent>(totalCount, pageSize, currPage, recordVos);
	}
	
	@Override
	public ActBottleContent editContent(Integer id) {
		return actBottleContentDAO.findById(id);
	}

	@Override
	public int saveContent(ActBottleContent actBottleContent) {
		return actBottleContentDAO.update(actBottleContent);
	}

	@Override
	public int addContent(ActBottleContent actBottleContent) {
		return actBottleContentDAO.add(actBottleContent).intValue();
	}
	
	@Override
	public Pagination<ActBottleRecord> getPrizeRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actBottleRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActBottleRecord> recordVos = actBottleRecordDAO.getRecord(activityId, startRow, pageSize);
		for (ActBottleRecord actBottleRecord : recordVos) {
			int number = actBottleHelpDAO.countHelpNumber(actBottleRecord.getMpOpenId(), 
					actBottleRecord.getActivityId(), actBottleRecord.getCount());
			actBottleRecord.setCount(number);
		}
		return new Pagination<ActBottleRecord>(totalCount, pageSize, currPage, recordVos);
	}
	
	@Override
	public void exportPrizeRecord(Integer activityId, Invocation inv,
			Integer startRow, Integer pageSize) {
		List<ActBottleRecord> list = actBottleRecordDAO.getRecord(activityId, startRow, pageSize);
		String[] headers = {"序号", "记录", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		List<ActBottleRecordVo> listVo = new ArrayList<ActBottleRecordVo>();
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActBottleRecord record : list){
				
				int number = actBottleHelpDAO.countHelpNumber(record.getMpOpenId(), 
						record.getActivityId(), record.getCount());
				
				ActBottleRecordVo recordVo = new ActBottleRecordVo();
				recordVo.setId(i++);
				recordVo.setCount(""+number);
				recordVo.setMailAddress(record.getMailAddress());
				recordVo.setQq(record.getQq());
				recordVo.setWechatId(record.getWechatId());
				recordVo.setOtherInfo(record.getOtherInfo());
				recordVo.setTel(record.getTel());
				recordVo.setUsername(record.getUsername());
				recordVo.setSubmitTime(ft.format(record.getSubmitTime()));
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
		ExportExcel<ActBottleRecordVo> ex = new ExportExcel<ActBottleRecordVo>();
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
	public int countAllPrizeRecord(Integer activityId) {
		return actBottleRecordDAO.countAllPrizeRecord(activityId);
	}

	@Override
	public String changeOpStatus(Integer id) {
		String op = actBottleRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actBottleRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actBottleRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}


}
