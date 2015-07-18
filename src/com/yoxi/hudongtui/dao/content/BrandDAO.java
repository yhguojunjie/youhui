package com.yoxi.hudongtui.dao.content;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.model.content.Brand;

/**
 * 
 * 品牌DAO
 * 
 * @author wql
 * 
 * @Date 2015年3月19日
 * 
 */

@DAO
public interface BrandDAO {
	/**
	 * 按拼凑字符串来查, 返回值为列表
	 * 
	 * @param findStr
	 * @return
	 */
	@SQL("SELECT a.logo,a.userId,b.nickName,b.headimgUrl  FROM t_agent_brand a left join t_user b on a.userId=b.userId WHERE ##(:findStr)")
	public List<Brand> findByStr(@SQLParam("findStr") String findStr);

	/**
	 * 计算总人数
	 * 
	 * @return
	 */
	@SQL("SELECT COUNT(*) FROM t_user WHERE ##(:findStr)")
	public int getTotalCount(@SQLParam("findStr") String findStr);
	/*@SQL("SELECT COUNT(*) FROM t_user WHERE ##(:findStr)")
	public int getTotalCount(@SQLParam("findStr") String findStr);*/
	

}
