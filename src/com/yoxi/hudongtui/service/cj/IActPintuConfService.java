package com.yoxi.hudongtui.service.cj;

import java.util.List;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.ActPintuConf;
import com.yoxi.hudongtui.model.cj.ActPintuRecord;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.pintu.PintuAwardsVo;

/**
 * 
 * 拼图
 * 
 * @author jjb
 *
 * 2015-1-5
 */
public interface IActPintuConfService {

public Integer save(ActPintuConf actPintuConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActPintuConf actPintuConf);
	
	public ActPintuConf findByActId(final Integer activityId);
	
	public List<PintuAwardsVo> parseJson(final String content);
	//分页获取记录
	public Pagination<ActPintuRecord> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public Pagination<ActPintuRecord> getRankPage(final Integer activityId, int currPage, int pageSize);
	
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
