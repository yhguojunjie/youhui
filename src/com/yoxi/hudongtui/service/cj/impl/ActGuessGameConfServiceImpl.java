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

import com.yoxi.hudongtui.dao.cj.guessgame.ActGuessGameAdminDAO;
import com.yoxi.hudongtui.dao.cj.guessgame.ActGuessGameBetRecordDAO;
import com.yoxi.hudongtui.dao.cj.guessgame.ActGuessGameConfDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.guessgame.ActGuessGameAdmin;
import com.yoxi.hudongtui.model.cj.guessgame.ActGuessGameConf;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActGuessGameConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ExportExcel;
import com.yoxi.hudongtui.utils.common.ExportUtil;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.guessgame.ActGuessGameBetRecordVo;
import com.yoxi.hudongtui.vo.cj.guessgame.ActGuessGamePrizeRecordVo;
import com.yoxi.hudongtui.vo.cj.guessgame.ActGuessGameRecordVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActGuessGameConfServiceImpl implements IActGuessGameConfService {

	@Autowired
	private ActGuessGameConfDAO actGuessGameConfDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private ActGuessGameBetRecordDAO actGuessGameBetRecordDAO;
	@Autowired
	private ActGuessGameAdminDAO actGuessGameAdminDAO;
	
	@Override
	@Transactional
	public Integer save(ActGuessGameConf actGuessGameConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actGuessGameConf.getName());
		pluginAct.setStartTime(actGuessGameConf.getStartTime());
		pluginAct.setEndTime(actGuessGameConf.getEndTime());
		pluginAct.setIcon(actGuessGameConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actGuessGameConf.setActivityId(activityId);
			ret = actGuessGameConfDAO.add(actGuessGameConf).intValue();
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
	public Integer update(ActGuessGameConf actGuessGameConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actGuessGameConf.getActivityId());
		infoVo.setIcon(actGuessGameConf.getShareImgUrl());
		infoVo.setStartTime(actGuessGameConf.getStartTime());
		infoVo.setEndTime(actGuessGameConf.getEndTime());
		infoVo.setTitle(actGuessGameConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actGuessGameConfDAO.update(actGuessGameConf);
		return ret;
	}

	@Override
	public ActGuessGameConf findByActId(Integer activityId) {
		ActGuessGameConf actGuessGameConf = actGuessGameConfDAO.findByActivityId(activityId);
		return actGuessGameConf;
	}
	

	@Override
	public Pagination<ActGuessGameAdmin> getGameAdminPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actGuessGameAdminDAO.getGameAdminCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActGuessGameAdmin> recordVos = actGuessGameAdminDAO.getGameAdmin(activityId, startRow, pageSize);
		return new Pagination<ActGuessGameAdmin>(totalCount, pageSize, currPage, recordVos);
	}
	
	@Override
	public Pagination<ActGuessGameRecordVo> getBetRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actGuessGameBetRecordDAO.getBetRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActGuessGameRecordVo> recordVos = actGuessGameBetRecordDAO.getBetRecord(activityId, startRow, pageSize);
		for (ActGuessGameRecordVo record : recordVos) {
		
			ActGuessGameAdmin admin = actGuessGameAdminDAO.findById(record.getMatchId());
			if(admin == null)continue;
			record.setGameName(admin.getName()+"："+admin.getLeftTeam()+" VS "+admin.getRightTeam());
			if(record.getbJoinType().equals("1")){//猜比分
				record.setResult(record.getLeftScore()+":"+record.getRightScore());
			}else{
				if(record.getLeftScore() > record.getRightScore()){
					record.setResult("左赢");
				}else if(record.getLeftScore() < record.getRightScore()){
					record.setResult("右赢");
				}else{
					record.setResult("平");
				}
			}
		}
		return new Pagination<ActGuessGameRecordVo>(totalCount, pageSize, currPage, recordVos);
	}

	@Override
	public void exportBetRecord(Integer activityId, Invocation inv, Integer startRow,
			Integer pageSize) {
		List<ActGuessGameRecordVo> list = actGuessGameBetRecordDAO.getBetRecord(activityId, startRow, pageSize);
		String[] headers = {"序号", "比赛", "投注", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "投注时间"};
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		List<ActGuessGameBetRecordVo> listVo = new ArrayList<ActGuessGameBetRecordVo>();
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActGuessGameRecordVo record : list){
				ActGuessGameAdmin admin = actGuessGameAdminDAO.findById(record.getMatchId());
				if(admin == null)continue;
				ActGuessGameBetRecordVo recordVo = new ActGuessGameBetRecordVo();
				recordVo.setId(i++);
				recordVo.setName(admin.getName()+"："+admin.getLeftTeam()+" VS "+admin.getRightTeam());
				if(record.getbPrize() != null){//不为空说明已经出结果了
					if(record.getbJoinType().equals("1")){//猜比分
						recordVo.setResult(record.getLeftScore()+":"+record.getRightScore());
					}else{
						if(record.getLeftScore() > record.getRightScore()){
							recordVo.setResult("左赢");
						}else if(record.getLeftScore() < record.getRightScore()){
							recordVo.setResult("右赢");
						}else{
							recordVo.setResult("平局");
						}
					}
				}
				recordVo.setMailAddress(record.getMailAddress());
				recordVo.setQq(record.getQq());
				recordVo.setWechatId(record.getWechatId());
				recordVo.setOtherInfo(record.getOtherInfo());
				recordVo.setTel(record.getTel());
				recordVo.setUsername(record.getUsername());
				recordVo.setBetTime(ft.format(record.getBetTime()));
				listVo.add(recordVo);
			}
		}
		ExportExcel<ActGuessGameBetRecordVo> ex = new ExportExcel<ActGuessGameBetRecordVo>();
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
			ex.exportExcel("投注记录",headers, listVo, out);
			ExportUtil.ExcelFileExport(inv, tempFilePath,crruentTime+".xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int countAllBetRecord(Integer activityId) {
		return actGuessGameBetRecordDAO.getBetRecordCount(activityId);
	}
	
	@Override
	public Pagination<ActGuessGameRecordVo> getPrizeRecordPage(Integer activityId,
			int currPage, int pageSize) {
		// 获取总数
		int totalCount = actGuessGameBetRecordDAO.getPrizeRecordCount(activityId);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<ActGuessGameRecordVo> recordVos = actGuessGameBetRecordDAO.getPrizeRecordPage(activityId, startRow, pageSize);
		for (ActGuessGameRecordVo record : recordVos) {
			ActGuessGameAdmin admin = actGuessGameAdminDAO.findById(record.getMatchId());
			if(admin == null)continue;
			record.setGameName(admin.getName()+"："+admin.getLeftTeam()+"VS"+admin.getRightTeam());
			if(record.getbPrize() != null){//不为空说明已经出结果了
				if(record.getbJoinType().equals("1")){//猜比分
					record.setResult(record.getLeftScore()+":"+record.getRightScore());
				}else{
					if(record.getLeftScore() > record.getRightScore()){
						record.setResult("左赢");
					}else if(record.getLeftScore() < record.getRightScore()){
						record.setResult("右赢");
					}else{
						record.setResult("平");
					}
				}
			}
		}
		return new Pagination<ActGuessGameRecordVo>(totalCount, pageSize, currPage, recordVos);
	}
	
	@Override
	public void exportPrizeRecord(Integer activityId, Invocation inv,
			Integer startRow, Integer pageSize) {
		List<ActGuessGameRecordVo> list = actGuessGameBetRecordDAO.getPrizeRecordPage(activityId, startRow, pageSize);
		String[] headers = {"序号", "比赛", "投注", "qq", "微信号", "其他信息", 
				"手机号 ", "用户名称", "投注时间", "兑换时间", "操作"};
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		List<ActGuessGamePrizeRecordVo> listVo = new ArrayList<ActGuessGamePrizeRecordVo>();
		if(list != null && list.size() != 0){
			int i = startRow + 1;
			for(ActGuessGameRecordVo record : list){
				ActGuessGameAdmin admin = actGuessGameAdminDAO.findById(record.getMatchId());
				if(admin == null)continue;
				ActGuessGamePrizeRecordVo recordVo = new ActGuessGamePrizeRecordVo();
				recordVo.setId(i++);
				recordVo.setName(admin.getName()+"："+admin.getLeftTeam()+"VS"+admin.getRightTeam());
				if(record.getbPrize() != null){//不为空说明已经出结果了
					if(record.getbJoinType().equals("1")){//猜比分
						recordVo.setResult(record.getLeftScore()+":"+record.getRightScore());
					}else{
						if(record.getLeftScore() > record.getRightScore()){
							recordVo.setResult("左赢");
						}else if(record.getLeftScore() < record.getRightScore()){
							recordVo.setResult("右赢");
						}else{
							recordVo.setResult("平");
						}
					}
				}
				recordVo.setMailAddress(record.getMailAddress());
				recordVo.setQq(record.getQq());
				recordVo.setWechatId(record.getWechatId());
				recordVo.setOtherInfo(record.getOtherInfo());
				recordVo.setTel(record.getTel());
				recordVo.setUsername(record.getUsername());
				recordVo.setBetTime(ft.format(record.getBetTime()));
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
		ExportExcel<ActGuessGamePrizeRecordVo> ex = new ExportExcel<ActGuessGamePrizeRecordVo>();
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
		return actGuessGameBetRecordDAO.getPrizeRecordCount(activityId);
	}

	@Override
	public String changeOpStatus(Integer id) {
		String op = actGuessGameBetRecordDAO.findOpStatus(id);
		if(op == null || op.equals("0")){//没有兑换过
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int ret = actGuessGameBetRecordDAO.updateOpStatus(id, "1", date);
			if(ret == 0){
				return null;
			}else{
				return ft.format(date);
			}
		}else{//兑换过
			int ret = actGuessGameBetRecordDAO.updateOpStatus(id, "0", null);
			if(ret == 0){
				return null;
			}else{
				return "";
			}
		}
	}

	@Override
	public int delete(Integer id) {
		return actGuessGameAdminDAO.remove(id);
	}

	@Override
	public ActGuessGameAdmin editGame(Integer id) {
		return actGuessGameAdminDAO.findById(id);
	}

	@Override
	public int saveGame(ActGuessGameAdmin actGuessGameAdmin) {
		String bJoinType = actGuessGameConfDAO.findJoinType(actGuessGameAdmin.getActivityId());
		//如果有比分，要判断用户是否中奖
		if(actGuessGameAdmin.getLeftScore() != null && actGuessGameAdmin.getRightScore() != null &&
				actGuessGameAdmin.getLeftScore() >= 0 && actGuessGameAdmin.getRightScore() >= 0){
			if(bJoinType.equals("1")){//猜比分
				actGuessGameBetRecordDAO.updatePrizeByScore(actGuessGameAdmin.getActivityId(), actGuessGameAdmin.getId(), 
						actGuessGameAdmin.getLeftScore(), actGuessGameAdmin.getRightScore());
				actGuessGameBetRecordDAO.updatePrizeByScore2(actGuessGameAdmin.getActivityId(), actGuessGameAdmin.getId(), 
						actGuessGameAdmin.getLeftScore(), actGuessGameAdmin.getRightScore());
			}else{
				actGuessGameBetRecordDAO.updatePrizeByWinLose(actGuessGameAdmin.getActivityId(), actGuessGameAdmin.getId(), 
						actGuessGameAdmin.getLeftScore(), actGuessGameAdmin.getRightScore());
				actGuessGameBetRecordDAO.updatePrizeByWinLose2(actGuessGameAdmin.getActivityId(), actGuessGameAdmin.getId(), 
						actGuessGameAdmin.getLeftScore(), actGuessGameAdmin.getRightScore());
			}
		}
		return actGuessGameAdminDAO.update(actGuessGameAdmin);
	}

	@Override
	public int addGame(ActGuessGameAdmin actGuessGameAdmin) {
		String bJoinType = actGuessGameConfDAO.findJoinType(actGuessGameAdmin.getActivityId());
		//如果有比分，要判断用户是否中奖
		if(actGuessGameAdmin.getLeftScore() != null && actGuessGameAdmin.getRightScore() != null &&
				actGuessGameAdmin.getLeftScore() >= 0 && actGuessGameAdmin.getRightScore() >= 0){
			if(bJoinType.equals("1")){//猜比分
				actGuessGameBetRecordDAO.updatePrizeByScore(actGuessGameAdmin.getActivityId(), actGuessGameAdmin.getId(), 
						actGuessGameAdmin.getLeftScore(), actGuessGameAdmin.getRightScore());
				actGuessGameBetRecordDAO.updatePrizeByScore2(actGuessGameAdmin.getActivityId(), actGuessGameAdmin.getId(), 
						actGuessGameAdmin.getLeftScore(), actGuessGameAdmin.getRightScore());
			}else{
				actGuessGameBetRecordDAO.updatePrizeByWinLose(actGuessGameAdmin.getActivityId(), actGuessGameAdmin.getId(), 
						actGuessGameAdmin.getLeftScore(), actGuessGameAdmin.getRightScore());
				actGuessGameBetRecordDAO.updatePrizeByWinLose2(actGuessGameAdmin.getActivityId(), actGuessGameAdmin.getId(), 
						actGuessGameAdmin.getLeftScore(), actGuessGameAdmin.getRightScore());
			}
		}
		return actGuessGameAdminDAO.add(actGuessGameAdmin).intValue();
	}

	@Override
	public String getJoinType(Integer activityId) {
		return actGuessGameConfDAO.findJoinType(activityId);
	}

}
