package com.yoxi.hudongtui.dao.cj.guessgame;

import java.util.Date;
import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.yoxi.hudongtui.vo.cj.guessgame.ActGuessGameRecordVo;

/**
 * 
 * 猜比赛
 * 
 * @author jjb
 *
 * 2015-3-24
 * 
 */
@DAO
public interface ActGuessGameBetRecordDAO {

	//查询所有押注记录
	@SQL("SELECT * FROM t_act_guessgame_betrecord where activityId = :1 order by betTime desc LIMIT :2,:3")
	public List<ActGuessGameRecordVo> getBetRecord(Integer activityId, int startRow, int pageSize);
	
	@SQL("SELECT count(*) FROM t_act_guessgame_betrecord where activityId = :1")
	public int getBetRecordCount(Integer activityId);
	
	//查询中奖记录
	@SQL("SELECT * FROM t_act_guessgame_betrecord where activityId = :1 and bPrize = 1 order by betTime desc LIMIT :2,:3")
	public List<ActGuessGameRecordVo> getPrizeRecordPage(Integer activityId, int startRow, int pageSize);
	
	@SQL("SELECT count(*) FROM t_act_guessgame_betrecord where activityId = :1 and bPrize = 1")
	public int getPrizeRecordCount(Integer activityId);
	
	@SQL("update t_act_guessgame_betrecord set opState = :2 #if(:3){,exchangeTime = :3}#else{,exchangeTime = NULL} where id = :1")
	public int updateOpStatus(Integer id, String status, Date date);
	
	@SQL("select opState from t_act_guessgame_betrecord where id = :1")
	public String findOpStatus(Integer id);
	
	//以比赛分数更新中奖状态
	@SQL("update t_act_guessgame_betrecord set bPrize = 1 where activityId = :1 and matchId = :2 and leftScore = :3 and rightScore = :4")
	public int updatePrizeByScore(Integer activityId, Integer matchId, Integer leftScore, Integer rightScore);
	
	//以比赛分数更新中奖状态，置空没有中奖的
	@SQL("update t_act_guessgame_betrecord set bPrize = NULL where activityId = :1 and matchId = :2 and (leftScore != :3 or rightScore != :4)")
	public int updatePrizeByScore2(Integer activityId, Integer matchId, Integer leftScore, Integer rightScore);
	
	//以左右赢更新中奖状态
	@SQL("update t_act_guessgame_betrecord set bPrize = 1 "
			+ "where activityId = :1 and matchId = :2 and "
			+ "((:3 > :4 and leftScore > rightScore) "
			+ "or (:3 < :4 and leftScore < rightScore) "
			+ "or (:3 = :4 and leftScore = rightScore and leftScore != -1 and rightScore != -1))")
	public int updatePrizeByWinLose(Integer activityId, Integer matchId, Integer leftScore, Integer rightScore);
	
	@SQL("update t_act_guessgame_betrecord set bPrize = NULL "
			+ "where activityId = :1 and matchId = :2 and "
			+ "!((:3 > :4 and leftScore > rightScore) "
			+ "or (:3 < :4 and leftScore < rightScore) "
			+ "or (:3 = :4 and leftScore = rightScore and leftScore != -1 and rightScore != -1))")
	public int updatePrizeByWinLose2(Integer activityId, Integer matchId, Integer leftScore, Integer rightScore);
}
