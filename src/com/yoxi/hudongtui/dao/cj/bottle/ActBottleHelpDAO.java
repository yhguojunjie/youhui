package com.yoxi.hudongtui.dao.cj.bottle;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

/**
 * 漂流瓶
 * 
 * @author jjb
 *
 * 2015-4-27
 *
 */
@DAO
public interface ActBottleHelpDAO {

	@SQL("select count(*) from t_act_bottle_help where activityId = :2 and helpedOpenId = :1 and contentId = :3")
	public Integer countHelpNumber(String openId, Integer activityId, Integer contentid);
}
