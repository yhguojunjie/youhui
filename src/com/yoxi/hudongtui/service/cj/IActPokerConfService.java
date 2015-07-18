package com.yoxi.hudongtui.service.cj;

import java.util.List;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.poker.ActPokerConf;
import com.yoxi.hudongtui.model.cj.poker.ActPokerRecord;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.poker.PokerAwardsVo;
import com.yoxi.hudongtui.vo.cj.poker.PokerRecordVo;

/**
 * 我坐庄
 * 
 * @author jjb
 *
 * 2015-1-22
 *
 */
public interface IActPokerConfService {

	public Integer save(ActPokerConf actPokerConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActPokerConf actPokerConf);
	
	public ActPokerConf findByActId(final Integer activityId);
	
	public List<PokerAwardsVo> parseJson(final String content);
	//分页获取记录
	public Pagination<ActPokerRecord> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRecord(final Integer activityId);
	
	public Pagination<ActPokerRecord> getJoinPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportJoin(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public String changeOpStatus(final Integer id);
	
	public String findAwards(final Integer activityId);
	
	public int addRecord(final Integer activityId, PokerRecordVo recordVo);
	
	public void savePrize(final Integer activityId, final String awards);
}
