package com.yoxi.hudongtui.dao.cj.question;

import com.yoxi.hudongtui.model.cj.question.ActQuestionPrize;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

/**
 * 一战到底
 * 
 * @author jjb
 *
 * 2015-2-28
 *
 */
@DAO
public interface ActQuestionPrizeDAO {

	/**
	 * 
	 * @param actQuestionPrize
	 * @return
	 */
	@SQL("insert into t_act_question_prize (activityId, prizeType,realNum,deliverNum,version,finalTime) " +
			"values (:1.activityId, :1.prizeType, :1.realNum,:1.deliverNum,:1.version,:1.finalTime)")
	public Identity add(ActQuestionPrize actQuestionPrize);
	
	/**
	 * 采用乐观锁更新数据版本
	 * @param actQuestionPrize
	 * @param upstr
	 * @return
	 */
	@SQL("update t_act_question_prize set finalTime = NOW(), version = version + 1, ##(:upstr) " +
			"where id = :1.id and version = :1.version")
	public int upByStr(ActQuestionPrize actQuestionPrize, @SQLParam("upstr") String upstr);
	
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_question_prize where activityId = :1 and prizeType = :2")
	public ActQuestionPrize findByActivityId(Integer activityId, Integer prizeType);
}
