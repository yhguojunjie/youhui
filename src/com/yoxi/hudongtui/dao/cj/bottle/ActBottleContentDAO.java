package com.yoxi.hudongtui.dao.cj.bottle;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.bottle.ActBottleContent;

/**
 * 漂流瓶
 * 
 * @author jjb
 *
 * 2015-4-27
 *
 */
@DAO
public interface ActBottleContentDAO {

	/**
	 * 
	 * @param actBottleContent
	 * @return
	 */
	@SQL("insert into t_act_bottle_content (activityId, #if(:1.name){name,}#if(:1.picUrl){picUrl,}#if(:1.descs){descs,}updateTime) " +
			"values (:1.activityId,#if(:1.name){:1.name,}#if(:1.picUrl){:1.picUrl,}#if(:1.descs){:1.descs,}:1.updateTime)")
	public Identity add(ActBottleContent actBottleContent);
	
	/**
	 * 
	 * @param actBottleContent
	 * @return
	 */
	@SQL("update t_act_bottle_content set activityId = :1.activityId, name = :1.name, " +
			"picUrl = :1.picUrl,descs = :1.descs,updateTime = :1.updateTime where id = :1.id")
	public int update(ActBottleContent actBottleContent);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@SQL("select * from t_act_bottle_content where id = :1")
	public ActBottleContent findById(Integer id);
	
	/**
	 * 
	 * @param activityId
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	@SQL("SELECT * FROM t_act_bottle_content where activityId = :1 order by id desc LIMIT :2,:3")
	public List<ActBottleContent> getContent(Integer activityId, int startRow, int pageSize);
	
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@SQL("SELECT count(*) FROM t_act_bottle_content where activityId = :1")
	public int getContentCount(Integer activityId);
	
	@SQL("select * from t_act_bottle_content where activityId = :1 LIMIT :2,:3")
	public List<ActBottleContent> getContentList(Integer activityId, int startRow, int pageSize);
}
