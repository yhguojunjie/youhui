package com.yoxi.hudongtui.dao.content;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.model.content.ContactUs;

/**
 * 
 * 联系我们
 *
 * @author wql
 *
 * @Date 2015年3月19日
 *
 */
@DAO
public interface ContactUsDAO {

	/**
	 * 按拼凑字符串来查，确保返回记录为唯一
	 * @param getStr
	 * @return
	 */
	@SQL("SELECT * FROM t_contactus WHERE ##(:getStr) LIMIT 0,1")
	public ContactUs getByStr(@SQLParam("getStr") String getStr);
	
	
}
