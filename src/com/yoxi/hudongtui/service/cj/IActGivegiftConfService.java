package com.yoxi.hudongtui.service.cj;

import java.rmi.ServerException;
import java.util.List;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.ActGivegiftConf;
import com.yoxi.hudongtui.model.cj.ActGivegiftRecord;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.givegift.GiftContentVo;

public interface IActGivegiftConfService {

	public Integer save(ActGivegiftConf actGivegiftConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActGivegiftConf actGivegiftConf);
	
	public ActGivegiftConf findByActId(final Integer activityId)throws ServerException;
	
	public List<GiftContentVo> parseJson(final String content);
	
	public void exportRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRecord(final Integer activityId);
	//分页获取记录
	public Pagination<ActGivegiftRecord> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public String changeOpStatus(final Integer id);
}
