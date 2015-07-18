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

import com.yoxi.hudongtui.dao.cj.ActMatureConfDAO;
import com.yoxi.hudongtui.dao.cj.ActMatureCountDAO;
import com.yoxi.hudongtui.dao.cj.ActMatureRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.ActMatureConf;
import com.yoxi.hudongtui.model.cj.ActMatureRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActMatureConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.mature.MatureRecordExVo;
import com.yoxi.hudongtui.vo.cj.mature.MatureRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActMatureConfServiceImpl implements IActMatureConfService {

	@Autowired
	private ActMatureConfDAO actMatureConfDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private ActMatureRecordDAO actMatureRecordDAO;
	@Autowired
	private ActMatureCountDAO actMatureCountDAO;
	
	@Override
	@Transactional
	public Integer save(ActMatureConf actMatureConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actMatureConf.getTitle());
		pluginAct.setStartTime(actMatureConf.getStartTime());
		pluginAct.setEndTime(actMatureConf.getEndTime());
		pluginAct.setIcon(actMatureConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actMatureConf.setActivityId(activityId);
			ret = actMatureConfDAO.add(actMatureConf).intValue();
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
	public Integer update(ActMatureConf actMatureConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actMatureConf.getActivityId());
		infoVo.setIcon(actMatureConf.getShareImgUrl());
		infoVo.setStartTime(actMatureConf.getStartTime());
		infoVo.setEndTime(actMatureConf.getEndTime());
		infoVo.setTitle(actMatureConf.getTitle());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actMatureConfDAO.update(actMatureConf);
		return ret;
	}

	@Override
	public ActMatureConf findByActId(Integer activityId) {
		ActMatureConf actMatureConf = actMatureConfDAO.findByActivityId(activityId);
		return actMatureConf;
	}
	
	@Override
	public Pagination<MatureRecordExVo> getRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actMatureRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<MatureRecordExVo> recordVos = actMatureRecordDAO.getRecord(activityId, startRow, pageSize);
		for (MatureRecordExVo matureRecordVo : recordVos) {
			int total = actMatureCountDAO.getTotalExNum(activityId, matureRecordVo.getMpOpenId());
			matureRecordVo.setTotalExNum(total);
		}
		return new Pagination<MatureRecordExVo>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActMatureRecord> list = actMatureRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖品名称", "累计兑换", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		List<MatureRecordVo> listVo = new ArrayList<MatureRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActMatureRecord record : list){
				MatureRecordVo recordVo = new MatureRecordVo();
				recordVo.setId(i++);
				recordVo.setCount(record.getCount()+"个红包");
				int total = actMatureCountDAO.getTotalExNum(activityId, record.getMpOpenId());
				recordVo.setTotalExNum(total+"个红包");
				recordVo.setMailAddress(record.getMailAddress());
				recordVo.setQq(record.getQq());
				recordVo.setWechatId(record.getWechatId());
				recordVo.setOtherInfo(record.getOtherInfo());
				recordVo.setTel(record.getTel());
				recordVo.setUsername(record.getUsername());
				if(record.getSubmitTime() != null){
					recordVo.setSubmitTime(ft.format(record.getSubmitTime()));
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
		ExportExcel<MatureRecordVo> ex = new ExportExcel<MatureRecordVo>();
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
		return actMatureRecordDAO.countAllPrizeRecord(activityId);
	}

	@Override
	public String changeOpStatus(Integer id) {
		String op = actMatureRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actMatureRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actMatureRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

}
