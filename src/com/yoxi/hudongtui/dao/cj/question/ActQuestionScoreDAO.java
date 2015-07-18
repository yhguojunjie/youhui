package com.yoxi.hudongtui.dao.cj.question;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.yoxi.hudongtui.vo.cj.question.QuestionRankVo;

/**
 * 一战到底
 * 
 * @author jjb
 *
 * 2015-2-28
 *
 */

@DAO
public interface ActQuestionScoreDAO {

	@SQL("SELECT count(*) FROM t_act_question_score where activityId = :1")
	public int getRankCount(Integer activityId);
	
	@SQL("SELECT distinct a.id, a.playTime, a.rightNum, b.mailAddress, b.qq, b.wechatId, b.otherInfo, b.tel, b.username " +
			"FROM t_act_question_score a left join t_act_question_record b on a.mpOpenId = b.mpOpenId " +
			"where a.activityId = :1 and b.activityId = :1 group by a.id order by a.rightNum desc, a.playTime asc LIMIT :2,:3")
	public List<QuestionRankVo> getRank(Integer activityId, int startRow, int pageSize);
	
	@SQL("update t_act_question_score set playTime = :2,rightNum = :3 where id = :1")
	public int updateBestScore(Integer id, Integer playTime, Integer rightNum);
}
