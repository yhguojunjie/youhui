package com.yoxi.hudongtui.service.cj;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.bottle.ActBottleConf;
import com.yoxi.hudongtui.model.cj.bottle.ActBottleContent;
import com.yoxi.hudongtui.model.cj.bottle.ActBottleRecord;
import com.yoxi.hudongtui.utils.common.Pagination;

/**
 * 漂流瓶
 * 
 * @author jjb
 *
 * 2015-4-27
 *
 */
public interface IActBottleConfService {

	public Integer save(ActBottleConf actBottleConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActBottleConf actBottleConf);
	
	public ActBottleConf findByActId(final Integer activityId);
	
	//内容管理
	public Pagination<ActBottleContent> getContentPage(final Integer activityId, int currPage, int pageSize);
	
	public ActBottleContent editContent(final Integer id);
	
	public int saveContent(ActBottleContent actBottleContent);
	
	public int addContent(ActBottleContent actBottleContent);
	
	//获得中奖记录
	public Pagination<ActBottleRecord> getPrizeRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportPrizeRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllPrizeRecord(final Integer activityId);
	
	public String changeOpStatus(final Integer id);
	
}
