package com.yoxi.hudongtui.service.cj;

import java.util.List;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.receipt.ActReceiptConf;
import com.yoxi.hudongtui.model.cj.receipt.ActReceiptRecord;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.receipt.ReceiptAwardsVo;

/**
 * 刮发票
 * 
 * @author jjb
 *
 * 2015-4-21
 *
 */
public interface IActReceiptConfService {

	public Integer save(ActReceiptConf actReceiptConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActReceiptConf actReceiptConf);
	
	public ActReceiptConf findByActId(final Integer activityId);
	
	//分页获取记录
	public Pagination<ActReceiptRecord> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRecord(final Integer activityId);
	
	public String changeOpStatus(final Integer id);
	
	public List<ReceiptAwardsVo> parseJson(final String awards);
	
	public void savePrize(final Integer activityId, final String awards);
}
