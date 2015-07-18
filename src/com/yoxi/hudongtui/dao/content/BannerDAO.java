package com.yoxi.hudongtui.dao.content;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.model.content.Banner;

/**
 * 
 * Banner
 * 
 * @author gjj
 * 
 *         2015-4-20
 */
@DAO
public interface BannerDAO {

	/**
	 * 按拼凑字符串来查, 返回值为列表
	 * 
	 * @param findStr
	 * @return
	 */
	@SQL("SELECT * FROM t_banner WHERE ##(:findStr)")
	public List<Banner> findByStr(@SQLParam("findStr") String findStr);

	/**
	 * 按拼凑字符串来查, 返回值为列表
	 * 
	 * @param findStr
	 * @return
	 */
	@SQL("SELECT *,(select linkType FROM t_banner b where b.id=a.sbannerId) as linkType,(select objId FROM t_banner b where b.id=a.sbannerId) as objId FROM t_agent_banner a WHERE ##(:findStr)")
	public List<Banner> findAgentByStr(@SQLParam("findStr") String findStr);

}
