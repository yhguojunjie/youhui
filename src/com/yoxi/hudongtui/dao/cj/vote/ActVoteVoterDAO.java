package com.yoxi.hudongtui.dao.cj.vote;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.yoxi.hudongtui.model.cj.vote.ActVoteVoter;
import com.yoxi.hudongtui.vo.cj.vote.VoterVo;

/**
 * 投票
 * 
 * @author jjb
 *
 * 2015-3-15
 *
 */
@DAO
public interface ActVoteVoterDAO {

	@SQL("SELECT count(*) FROM t_act_vote_voter where activityId = :1")
	public int getRankCount(Integer activityId);
	
	@SQL("SELECT * FROM t_act_vote_voter where activityId = :1 order by voteNum desc LIMIT :2,:3")
	public List<ActVoteVoter> getRank(Integer activityId, int startRow, int pageSize);
	
	@SQL("select id, voterId, tel, username, mailAddress, qq, wechatId, otherInfo4 as otherInfo,voteNum from "
			+ "t_act_vote_voter where activityId = :1 order by voteNum desc LIMIT :2,:3")
	public List<VoterVo> findAll(Integer activityId, int startRow, int pageSize);
	
	@SQL("update t_act_vote_voter set voteNum = :2 where id = :1")
	public int updateVoteNum(Integer id, Integer voteNum);
	
	@SQL("delete from t_act_vote_voter where id = :1")
	public int delete(Integer id);
}
