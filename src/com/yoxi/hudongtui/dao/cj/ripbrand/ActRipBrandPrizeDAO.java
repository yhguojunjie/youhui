package com.yoxi.hudongtui.dao.cj.ripbrand;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.ripbrand.ActRipBrandPrize;

/**
 * 撕名牌
 * 
 * @author jjb
 *
 * 2015-5-4
 *
 */
@DAO
public interface ActRipBrandPrizeDAO {


	/**
	 * 
	 * @param actRipBrandPrize
	 * @return
	 */
	@SQL("insert into t_act_ripbrand_prize (activityId, prizeType,realNum,deliverNum,version,finalTime) " +
			"values (:1.activityId, :1.prizeType, :1.realNum,:1.deliverNum,:1.version,:1.finalTime)")
	public Identity add(ActRipBrandPrize actRipBrandPrize);
	
	/**
	 * 采用乐观锁更新数据版本
	 * @param actRipBrandPrize
	 * @param upstr
	 * @return
	 */
	@SQL("update t_act_ripbrand_prize set finalTime = NOW(), version = version + 1, ##(:upstr) " +
			"where id = :1.id and version = :1.version")
	public int upByStr(ActRipBrandPrize actRipBrandPrize, @SQLParam("upstr") String upstr);
	
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_ripbrand_prize where activityId = :1 and prizeType = :2")
	public ActRipBrandPrize findByActivityId(Integer activityId, Integer prizeType);
}
