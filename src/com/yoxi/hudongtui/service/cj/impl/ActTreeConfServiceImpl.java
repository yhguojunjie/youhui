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
import com.yoxi.hudongtui.dao.cj.ActTreeConfDAO;
import com.yoxi.hudongtui.dao.cj.ActTreePrizeDAO;
import com.yoxi.hudongtui.dao.cj.ActTreeRecordDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.ActTreeConf;
import com.yoxi.hudongtui.model.cj.ActTreePrize;
import com.yoxi.hudongtui.model.cj.ActTreeRecord;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActTreeConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.tree.TreeAwardsVo;
import com.yoxi.hudongtui.vo.cj.tree.TreeRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActTreeConfServiceImpl implements IActTreeConfService {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ActTreeConfDAO actTreeConfDAO;
	@Autowired
	private ActTreeRecordDAO actTreeRecordDAO;
	@Autowired
	private ActTreePrizeDAO actTreePrizeDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Override
	@Transactional
	public Integer save(ActTreeConf actTreeConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actTreeConf.getName());
		pluginAct.setStartTime(actTreeConf.getStartTime());
		pluginAct.setEndTime(actTreeConf.getEndTime());
		pluginAct.setIcon(actTreeConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actTreeConf.setActivityId(activityId);
			ret = actTreeConfDAO.add(actTreeConf).intValue();
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
	public Integer update(ActTreeConf actTreeConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actTreeConf.getActivityId());
		infoVo.setIcon(actTreeConf.getShareImgUrl());
		infoVo.setStartTime(actTreeConf.getStartTime());
		infoVo.setEndTime(actTreeConf.getEndTime());
		infoVo.setTitle(actTreeConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actTreeConfDAO.update(actTreeConf);
		return ret;
	}

	@Override
	public ActTreeConf findByActId(Integer activityId) {
		ActTreeConf actTreeConf = actTreeConfDAO.findByActivityId(activityId);
		return actTreeConf;
	}

	@Override
	public List<TreeAwardsVo> parseJson(String content) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<TreeAwardsVo>>(){}.getType();
		List<TreeAwardsVo> treeAwardsVoList = gson.fromJson(content, type);
		return treeAwardsVoList;
	}

	@Override
	public Pagination<ActTreeRecord> getRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actTreeRecordDAO.getRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActTreeRecord> recordVos = actTreeRecordDAO.getRecord(activityId, startRow, pageSize);
		return new Pagination<ActTreeRecord>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActTreeRecord> list = actTreeRecordDAO.findAll(activityId, startRow, pageSize);
		String[] headers = {"序号", "奖项", "奖品名称", "邮寄地址", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "中奖时间", "兑换时间", "操作"};
		String u[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		List<TreeRecordVo> listVo = new ArrayList<TreeRecordVo>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActTreeRecord record : list){
				TreeRecordVo recordVo = new TreeRecordVo();
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
		ExportExcel<TreeRecordVo> ex = new ExportExcel<TreeRecordVo>();
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
	public String changeOpStatus(Integer id) {
		String op = actTreeRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actTreeRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actTreeRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}
	
	@Override
	public int countAllRecord(Integer activityId) {
		return actTreeRecordDAO.countAllPrizeRecord(activityId);
	}

	@Override
	public String findAwards(Integer activityId) {
		return actTreeConfDAO.findAwards(activityId);
	}

	@Override
	public int addRecord(Integer activityId, TreeRecordVo recordVo) {
		ActTreeRecord treeRecord = new ActTreeRecord();
		treeRecord.setActivityId(activityId);
		treeRecord.setMpOpenId("");
		treeRecord.setPrizeName(recordVo.getPrizeName());
		treeRecord.setPrizeType(Integer.parseInt(recordVo.getPrizeType()));
		treeRecord.setTel(recordVo.getTel());
		treeRecord.setExchangeTime(new Date());
		treeRecord.setOpState("1");//已兑换
		return actTreeRecordDAO.add(treeRecord).intValue();
	}

	@Override
	public void savePrize(Integer activityId, String awards) {
		List<TreeAwardsVo> treeAwardsVoList = parseJson(awards);
		for (TreeAwardsVo treeAwardsVo : treeAwardsVoList) {
			ActTreePrize actTreePrize = actTreePrizeDAO.findByActivityId(activityId, 
					treeAwardsVo.getPrizeType());
			if(actTreePrize == null){
				Integer deliverPrizeNum = actTreeRecordDAO.countPrizeNumber(activityId, treeAwardsVo.getPrizeType());
				if(deliverPrizeNum == null)deliverPrizeNum = 0;
				
				actTreePrize = new ActTreePrize();
				actTreePrize.setActivityId(activityId);
				actTreePrize.setPrizeType(treeAwardsVo.getPrizeType());
				actTreePrize.setRealNum(treeAwardsVo.getRealNum());
				actTreePrize.setDeliverNum(deliverPrizeNum);
				actTreePrize.setVersion(1);
				actTreePrize.setFinalTime(new Date());
				actTreePrizeDAO.add(actTreePrize);
			}else{
				actTreePrizeDAO.upByStr(actTreePrize, " deliverNum = " 
				+ actTreePrize.getDeliverNum() + ",realNum = " + treeAwardsVo.getRealNum());
			}
		}
	}
}
