package com.yoxi.hudongtui.service.cj;

import java.util.List;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.fingerprint.ActFingerprintConf;
import com.yoxi.hudongtui.model.cj.fingerprint.ActFingerprintRecord;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.fingerprint.FingerprintAwardsVo;
import com.yoxi.hudongtui.vo.cj.fingerprint.FingerprintRecordVo;

/**
 * 指纹配对
 * 
 * @author jjb
 *
 * 2015-2-4
 *
 */
public interface IActFingerprintConfService {

	public Integer save(ActFingerprintConf actFingerprintConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActFingerprintConf actFingerprintConf);
	
	public ActFingerprintConf findByActId(final Integer activityId);
	
	public List<FingerprintAwardsVo> parseJson(final String content);
	//分页获取记录
	public Pagination<ActFingerprintRecord> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRecord(final Integer activityId);
	
	public String changeOpStatus(final Integer id);
	
	public String findAwards(final Integer activityId);
	
	public int addRecord(final Integer activityId, FingerprintRecordVo recordVo);
	
	public void savePrize(final Integer activityId, final String awards);
}
