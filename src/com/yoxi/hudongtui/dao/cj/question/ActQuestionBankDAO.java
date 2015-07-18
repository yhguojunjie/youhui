package com.yoxi.hudongtui.dao.cj.question;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.question.ActQuestionBank;

/**
 * 一战到底
 * 
 * @author jjb
 *
 * 2015-2-28
 *
 */
@DAO
public interface ActQuestionBankDAO {
	
	@SQL("insert into t_act_question_bank (activityId, question, finalTime) " +
			"values (:1.activityId, :1.question, :1.finalTime)")
	public Identity add(ActQuestionBank actQuestionBank);
	
	@SQL("update t_act_question_bank set finalTime = NOW(), ##(:upstr) where id = :1.id")
	public int upByStr(ActQuestionBank actQuestionBank, @SQLParam("upstr") String upstr);
	
	@SQL("delete FROM t_act_question_bank where id = :1")
	public int remove(Integer id);

	@SQL("SELECT count(*) FROM t_act_question_bank where activityId = :1")
	public int getBankCount(Integer activityId);
	
	@SQL("SELECT * FROM t_act_question_bank where activityId = :1 order by id asc LIMIT :2,:3")
	public List<ActQuestionBank> getBank(Integer activityId, int startRow, int pageSize);
}
