package com.yoxi.hudongtui.dao.cj.zanrenqi;


import com.yoxi.hudongtui.model.cj.zanrenqi.ActZanrenqiPrize;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

/**
 * 指纹配对
 * 
 * @author jjb
 *
 * 2015-2-13
 *
 */
@DAO
public interface ActZanrenqiPrizeDAO {

	/**
	 * 
	 * @param actZanrenqiPrize
	 * @return
	 */
	@SQL("insert into t_act_zanrenqi_prize (activityId, prizeType,realNum,deliverNum,version,finalTime) " +
			"values (:1.activityId, :1.prizeType, :1.realNum,:1.deliverNum,:1.version,:1.finalTime)")
	public Identity add(ActZanrenqiPrize actZanrenqiPrize);
	
	/**
	 * 采用乐观锁更新数据版本
	 * @param actZanrenqiPrize
	 * @param upstr
	 * @return
	 */
	@SQL("update t_act_zanrenqi_prize set finalTime = NOW(), version = version + 1, ##(:upstr) " +
			"where id = :1.id and version = :1.version")
	public int upByStr(ActZanrenqiPrize actZanrenqiPrize, @SQLParam("upstr") String upstr);
	
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_zanrenqi_prize where activityId = :1 and prizeType = :2")
	public ActZanrenqiPrize findByActivityId(Integer activityId, Integer prizeType);
}
