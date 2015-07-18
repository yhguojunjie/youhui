package com.yoxi.hudongtui.dao.cj;

import com.yoxi.hudongtui.model.cj.ActRunmanPrize;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

/**
 * 奔跑吧兄弟
 * 
 * @author jjb
 *
 *2015-2-25
 *
 */
@DAO
public interface ActRunmanPrizeDAO {

	/**
	 * 
	 * @param actRunmanPrize
	 * @return
	 */
	@SQL("insert into t_act_runman_prize (activityId, prizeType,realNum,deliverNum,version,finalTime) " +
			"values (:1.activityId, :1.prizeType, :1.realNum,:1.deliverNum,:1.version,:1.finalTime)")
	public Identity add(ActRunmanPrize actRunmanPrize);
	
	/**
	 * 采用乐观锁更新数据版本
	 * @param actRunmanPrize
	 * @param upstr
	 * @return
	 */
	@SQL("update t_act_runman_prize set finalTime = NOW(), version = version + 1, ##(:upstr) " +
			"where id = :1.id and version = :1.version")
	public int upByStr(ActRunmanPrize actRunmanPrize, @SQLParam("upstr") String upstr);
	
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_runman_prize where activityId = :1 and prizeType = :2")
	public ActRunmanPrize findByActivityId(Integer activityId, Integer prizeType);
}
