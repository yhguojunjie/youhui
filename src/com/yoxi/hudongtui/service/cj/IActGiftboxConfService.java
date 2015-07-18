package com.yoxi.hudongtui.service.cj;

import java.util.List;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.ActGiftboxConf;
import com.yoxi.hudongtui.model.cj.ActGiftboxRecord;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.giftbox.GiftboxAwardsVo;
import com.yoxi.hudongtui.vo.cj.giftbox.GiftboxRecordVo;

public interface IActGiftboxConfService {

	public Integer save(ActGiftboxConf actGiftboxConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActGiftboxConf actGiftboxConf);
	
	public ActGiftboxConf findByActId(final Integer activityId);
	
	public List<GiftboxAwardsVo> parseJson(final String content);
	//分页获取记录
	public Pagination<ActGiftboxRecord> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRecord(final Integer activityId);
	
	public Pagination<ActGiftboxRecord> getJoinPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportJoin(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public String changeOpStatus(final Integer id);
	
	public String findAwards(final Integer activityId);
	
	public int addRecord(final Integer activityId, GiftboxRecordVo recordVo);
	
	public void savePrize(final Integer activityId, final String awards);
	
}
