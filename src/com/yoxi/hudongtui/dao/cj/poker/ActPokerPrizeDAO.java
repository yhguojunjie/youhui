package com.yoxi.hudongtui.dao.cj.poker;

import com.yoxi.hudongtui.model.cj.poker.ActPokerPrize;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

/**
 * 我坐庄
 * 
 * @author jjb
 *
 * 2015-2-25
 *
 */
@DAO
public interface ActPokerPrizeDAO {

	/**
	 * 
	 * @param actPokerPrize
	 * @return
	 */
	@SQL("insert into t_act_poker_prize (activityId, realNum,deliverNum,version,finalTime) " +
			"values (:1.activityId,:1.realNum,:1.deliverNum,:1.version,:1.finalTime)")
	public Identity add(ActPokerPrize actPokerPrize);
	
	/**
	 * 采用乐观锁更新数据版本
	 * @param actPokerPrize
	 * @param upstr
	 * @return
	 */
	@SQL("update t_act_poker_prize set finalTime = NOW(), version = version + 1, ##(:upstr) " +
			"where id = :1.id and version = :1.version")
	public int upByStr(ActPokerPrize actPokerPrize, @SQLParam("upstr") String upstr);
	
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_poker_prize where activityId = :1")
	public ActPokerPrize findByActivityId(Integer activityId);
}
