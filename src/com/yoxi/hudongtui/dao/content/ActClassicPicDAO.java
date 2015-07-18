package com.yoxi.hudongtui.dao.content;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.yoxi.hudongtui.model.content.ActClassicPic;

/**
 * 
 * 经典活动图片
 * 
 * @author wql
 *
 * @Date 2015年3月26日
 *
 */
@DAO
public interface ActClassicPicDAO {

	/**
	 * 查找
	 * @param classicId
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT * FROM t_act_classic_pic WHERE classicId = :1 ORDER BY seq DESC")
	public List<ActClassicPic> findList(Integer classicId)throws Exception;
}
