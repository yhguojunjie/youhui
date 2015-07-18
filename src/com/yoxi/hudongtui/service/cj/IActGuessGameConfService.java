package com.yoxi.hudongtui.service.cj;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.guessgame.ActGuessGameAdmin;
import com.yoxi.hudongtui.model.cj.guessgame.ActGuessGameConf;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.guessgame.ActGuessGameRecordVo;

/**
 * 猜比赛
 * 
 * @author jjb
 *
 * 2015-3-25
 *
 */
public interface IActGuessGameConfService {

	public Integer save(ActGuessGameConf actGuessGameConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActGuessGameConf actGuessGameConf);
	
	public ActGuessGameConf findByActId(final Integer activityId);
	
	//比赛管理
	public Pagination<ActGuessGameAdmin> getGameAdminPage(final Integer activityId, int currPage, int pageSize);
	
	//获取押注记录
	public Pagination<ActGuessGameRecordVo> getBetRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportBetRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllBetRecord(final Integer activityId);
	
	//获得中奖记录
	public Pagination<ActGuessGameRecordVo> getPrizeRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportPrizeRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllPrizeRecord(final Integer activityId);
	
	public String changeOpStatus(final Integer id);
	
	public int delete(final Integer id);
	
	public ActGuessGameAdmin editGame(final Integer id);
	
	public int saveGame(ActGuessGameAdmin actGuessGameAdmin);
	
	public int addGame(ActGuessGameAdmin actGuessGameAdmin);
	
	public String getJoinType(final Integer activityId);
	
}
