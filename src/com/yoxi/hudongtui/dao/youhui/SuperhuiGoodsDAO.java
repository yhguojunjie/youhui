package com.yoxi.hudongtui.dao.youhui;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.model.content.SuperhuiGoods;
import com.yoxi.hudongtui.model.content.SuperhuiGoodsVo;

/**
 * 超级优惠商品DAO
 * 
 * @author guojunjie 2015-8-17
 */
@DAO
public interface SuperhuiGoodsDAO {
	/**
	 * 查询超级优惠商品列表信息全部
	 * 
	 * @return List<WebShop>
	 */

	@SQL("SELECT * FROM t_superhui_goods ")
	public List<SuperhuiGoods> findAllSuperhuiGoods() throws Exception;

	/**
	 * 查询超级优惠商品列表信息（更新分类查询）
	 * 
	 * @param condition
	 *            查询条件
	 * @return List<WebShop>
	 */
	@SQL("SELECT * FROM t_superhui_goods a LEFT JOIN t_goods_superhuigoods b on a.id=b.superhuiGoods_id ##(:condition)")
	public List<SuperhuiGoodsVo> findSuperhuiGoods(
			@SQLParam("condition") String condition) throws Exception;
}
