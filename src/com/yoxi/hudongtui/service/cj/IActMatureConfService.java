package com.yoxi.hudongtui.service.cj;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.ActMatureConf;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.mature.MatureRecordExVo;

/**
 * 
 * 红包熟了
 * 
 * @author jjb
 *
 * 2015-1-11
 */
public interface IActMatureConfService {

	public Integer save(ActMatureConf actMatureConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActMatureConf actMatureConf);
	
	public ActMatureConf findByActId(final Integer activityId);
	
	//分页获取记录
	public Pagination<MatureRecordExVo> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRecord(final Integer activityId);
	
	public String changeOpStatus(final Integer id);
}
