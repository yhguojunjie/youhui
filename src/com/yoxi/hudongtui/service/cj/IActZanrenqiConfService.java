package com.yoxi.hudongtui.service.cj;

import java.util.List;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.zanrenqi.ActZanrenqiConf;
import com.yoxi.hudongtui.model.cj.zanrenqi.ActZanrenqiRecord;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.zanrenqi.ZanrenqiAwardsVo;
import com.yoxi.hudongtui.vo.cj.zanrenqi.ZanrenqiRecordVo;

/**
 * 攒人气
 * 
 * @author jjb
 *
 * 2015-1-29
 *
 */
public interface IActZanrenqiConfService {

	public Integer save(ActZanrenqiConf actZanrenqiConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActZanrenqiConf actZanrenqiConf);
	
	public ActZanrenqiConf findByActId(final Integer activityId);
	
	//分页获取记录
	public Pagination<ActZanrenqiRecord> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRecord(final Integer activityId);
	
	public String changeOpStatus(final Integer id);
	
	public List<ZanrenqiAwardsVo> parseJson(final String awards);
	
	public int addRecord(final Integer activityId, ZanrenqiRecordVo recordVo);
	
	public String findAwards(final Integer activityId);
	
	public void savePrize(final Integer activityId, final String awards);
}
