package com.yoxi.hudongtui.dao.cj;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

/**
 * 红包熟了
 * 
 * @author jjb
 *
 *2015-1-12
 *
 */
@DAO
public interface ActMatureCountDAO {
	
	@SQL("select totalExNum from t_act_mature_count where activityId = :1 and mpOpenId = :2 LIMIT 0,1")
	public Integer getTotalExNum(Integer activityId, String openId);
}
