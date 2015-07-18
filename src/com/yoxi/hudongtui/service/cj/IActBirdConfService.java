package com.yoxi.hudongtui.service.cj;

import java.util.List;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.bird.ActBirdConf;
import com.yoxi.hudongtui.model.cj.bird.ActBirdRecord;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.bird.BirdAwardsVo;

/**
 * 小鸟
 * 
 * @author jjb
 *
 * 2015-3-10
 *
 */
public interface IActBirdConfService {

public Integer save(ActBirdConf actBirdConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActBirdConf actBirdConf);
	
	public ActBirdConf findByActId(final Integer activityId);
	
	//分页获取记录
	public Pagination<ActBirdRecord> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRecord(final Integer activityId);
	
	public String changeOpStatus(final Integer id);
	
	public List<BirdAwardsVo> parseJson(final String awards);
	
	public void savePrize(final Integer activityId, final String awards);
}
