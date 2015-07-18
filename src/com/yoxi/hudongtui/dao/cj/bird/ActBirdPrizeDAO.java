package com.yoxi.hudongtui.dao.cj.bird;

import com.yoxi.hudongtui.model.cj.bird.ActBirdPrize;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

/**
 * 小鸟
 * 
 * @author jjb
 *
 * 2015-3-10
 *
 */
@DAO
public interface ActBirdPrizeDAO {

	/**
	 * 
	 * @param actBirdPrize
	 * @return
	 */
	@SQL("insert into t_act_bird_prize (activityId, prizeType,realNum,deliverNum,version,finalTime) " +
			"values (:1.activityId, :1.prizeType, :1.realNum,:1.deliverNum,:1.version,:1.finalTime)")
	public Identity add(ActBirdPrize actBirdPrize);
	
	/**
	 * 采用乐观锁更新数据版本
	 * @param actBirdPrize
	 * @param upstr
	 * @return
	 */
	@SQL("update t_act_bird_prize set finalTime = NOW(), version = version + 1, ##(:upstr) " +
			"where id = :1.id and version = :1.version")
	public int upByStr(ActBirdPrize actBirdPrize, @SQLParam("upstr") String upstr);
	
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_bird_prize where activityId = :1 and prizeType = :2")
	public ActBirdPrize findByActivityId(Integer activityId, Integer prizeType);
}
