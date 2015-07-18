package com.yoxi.hudongtui.service.cj;

import java.util.List;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.ripbrand.ActRipBrandConf;
import com.yoxi.hudongtui.model.cj.ripbrand.ActRipBrandRecord;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.ripbrand.RipBrandAwardsVo;

/**
 * 撕名牌
 * 
 * @author jjb
 *
 * 2015-5-4
 *
 */
public interface IActRipBrandConfService {

	public Integer save(ActRipBrandConf actRipBrandConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActRipBrandConf actRipBrandConf);
	
	public ActRipBrandConf findByActId(final Integer activityId);
	
	//分页获取记录
	public Pagination<ActRipBrandRecord> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRecord(final Integer activityId);
	
	public String changeOpStatus(final Integer id);
	
	public List<RipBrandAwardsVo> parseJson(final String awards);
	
	public void savePrize(final Integer activityId, final String awards);
}
