package com.yoxi.hudongtui.service.cj;

import java.util.List;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.ActTreeConf;
import com.yoxi.hudongtui.model.cj.ActTreeRecord;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.tree.TreeAwardsVo;
import com.yoxi.hudongtui.vo.cj.tree.TreeRecordVo;

/**
 * 
 * 圣诞树
 * 
 * @author jjb
 *
 * 2014-12-5
 */
public interface IActTreeConfService {

	public Integer save(ActTreeConf actTreeConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActTreeConf actTreeConf);
	
	public ActTreeConf findByActId(final Integer activityId);
	
	public List<TreeAwardsVo> parseJson(final String content);
	
	//分页获取记录
	public Pagination<ActTreeRecord> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRecord(final Integer activityId);
	
	public String changeOpStatus(final Integer id);
	
	public String findAwards(final Integer activityId);
	
	public int addRecord(final Integer activityId, TreeRecordVo recordVo);
	
	public void savePrize(final Integer activityId, final String awards);

}
