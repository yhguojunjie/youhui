package com.yoxi.hudongtui.dao.cj;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.ActPintuPrize;

/**
 * 拼图
 * 
 * @author jjb
 *
 *2015-2-25
 *
 */
@DAO
public interface ActPintuPrizeDAO {

	/**
	 * 
	 * @param actPintuPrize
	 * @return
	 */
	@SQL("insert into t_act_pintu_prize (activityId, prizeType,realNum,deliverNum,version,finalTime) " +
			"values (:1.activityId, :1.prizeType, :1.realNum,:1.deliverNum,:1.version,:1.finalTime)")
	public Identity add(ActPintuPrize actPintuPrize);
	
	/**
	 * 采用乐观锁更新数据版本
	 * @param actPintuPrize
	 * @param upstr
	 * @return
	 */
	@SQL("update t_act_pintu_prize set finalTime = NOW(), version = version + 1, ##(:upstr) " +
			"where id = :1.id and version = :1.version")
	public int upByStr(ActPintuPrize actPintuPrize, @SQLParam("upstr") String upstr);
	
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_pintu_prize where activityId = :1 and prizeType = :2")
	public ActPintuPrize findByActivityId(Integer activityId, Integer prizeType);
}
