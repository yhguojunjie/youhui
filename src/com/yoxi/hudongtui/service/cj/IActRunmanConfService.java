package com.yoxi.hudongtui.service.cj;

import java.util.List;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.ActRunmanConf;
import com.yoxi.hudongtui.model.cj.ActRunmanRecord;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.runman.RunmanAwardsVo;

/**
 * 
 * 奔跑吧兄弟
 * 
 * @author jjb
 *
 * 2014-12-5
 */
public interface IActRunmanConfService {

	public Integer save(ActRunmanConf actRunmanConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActRunmanConf actRunmanConf);
	
	public ActRunmanConf findByActId(final Integer activityId);
	
	public List<RunmanAwardsVo> parseJson(final String content);
	//分页获取记录
	public Pagination<ActRunmanRecord> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public Pagination<ActRunmanRecord> getRankPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public void exportRank(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRecord(final Integer activityId);
	
	public int countAllScore(final Integer activityId);
	
	public String changeOpStatus(final Integer id);
	
	public int updateBestScore(final Integer id, final Integer score);
	
	public void savePrize(final Integer activityId, final String awards);
}
