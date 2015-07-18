package com.yoxi.hudongtui.service.cj;

import java.util.List;

import com.yoxi.hudongtui.model.cj.ActMagazineConf;
import com.yoxi.hudongtui.vo.cj.magazine.MagazineContentVo;

/**
 * 
 * 微场景
 * 
 * @author jjb
 *
 * 2014-10-13
 */
public interface IActMagazineConfService {

	/**
	 * 保存
	 * @param actMagazineConf
	 * @return
	 */
	public Integer save(ActMagazineConf actMagazineConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActMagazineConf actMagazineConf);
	
	public ActMagazineConf findByActId(final Integer activityId);
	
	public List<MagazineContentVo> parseJson(final String content);
	
}
