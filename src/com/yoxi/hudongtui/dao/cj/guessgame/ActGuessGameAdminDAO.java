package com.yoxi.hudongtui.dao.cj.guessgame;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.guessgame.ActGuessGameAdmin;

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
public interface ActGuessGameAdminDAO {

	/**
	 * 
	 * @param actGuessGameAdmin
	 * @return
	 */
	@SQL("insert into t_act_guessgame_admin (activityId, #if(:1.name){name,}#if(:1.leftTeam){leftTeam,}"
			+ "#if(:1.leftPicUrl){leftPicUrl,}#if(:1.rightTeam){rightTeam,}#if(:1.rightPicUrl){rightPicUrl,}"
			+ "#if(:1.startTime){startTime,}#if(:1.endTime){endTime,}#if(:1.leftScore){leftScore,}#if(:1.rightScore){rightScore,}"
			+ "#if(:1.leftPeople){leftPeople,}#if(:1.rightPeople){rightPeople,}updateTime) " +
			"values (:1.activityId,#if(:1.name){:1.name,}#if(:1.leftTeam){:1.leftTeam,}"
			+ "#if(:1.leftPicUrl){:1.leftPicUrl,}#if(:1.rightTeam){:1.rightTeam,}#if(:1.rightPicUrl){:1.rightPicUrl,}"
			+ "#if(:1.startTime){:1.startTime,}#if(:1.endTime){:1.endTime,}#if(:1.leftScore){:1.leftScore,}#if(:1.rightScore){:1.rightScore,}"
			+ "#if(:1.leftPeople){:1.leftPeople,}#if(:1.rightPeople){:1.rightPeople,}:1.updateTime)")
	public Identity add(ActGuessGameAdmin actGuessGameAdmin);
	
	/**
	 * 
	 * @param actGuessGameAdmin
	 * @return
	 */
	@SQL("update t_act_guessgame_admin set activityId = :1.activityId, name = :1.name, " +
			"leftTeam = :1.leftTeam,#if(:1.leftPicUrl){leftPicUrl = :1.leftPicUrl,}rightTeam = :1.rightTeam," +
			"#if(:1.rightPicUrl){rightPicUrl = :1.rightPicUrl,}startTime = :1.startTime, endTime = :1.endTime,  " +
			"#if(:1.leftScore != NULL){leftScore = :1.leftScore,}#if(:1.rightScore != NULL){rightScore = :1.rightScore,}"
			+ "#if(:1.leftPeople){leftPeople = :1.leftPeople,}#if(:1.rightPeople){rightPeople = :1.rightPeople,}" +
			"updateTime = :1.updateTime where id = :1.id")
	public int update(ActGuessGameAdmin actGuessGameAdmin);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@SQL("select * from t_act_guessgame_admin where id = :1")
	public ActGuessGameAdmin findById(Integer id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@SQL("delete FROM t_act_guessgame_admin where id = :1")
	public int remove(Integer id);
	
	/**
	 * 
	 * @param activityId
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	@SQL("SELECT * FROM t_act_guessgame_admin where activityId = :1 order by id desc LIMIT :2,:3")
	public List<ActGuessGameAdmin> getGameAdmin(Integer activityId, int startRow, int pageSize);
	
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@SQL("SELECT count(*) FROM t_act_guessgame_admin where activityId = :1")
	public int getGameAdminCount(Integer activityId);
}
