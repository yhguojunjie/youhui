package com.yoxi.hudongtui.dao.youhui;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.yoxi.hudongtui.model.content.SuperhuiGoods;

/**
 * 超级优惠商品DAO
 * 
 * @author guojunjie 2015-8-9
 */
@DAO
public interface SuperhuiGoodsDAO {

	/**
	 * 查询超级优惠商品列表信息
	 * 
	 * @return List<Goodsclassificat>
	 */
	@SQL("SELECT * from t_superhui_goods a order by a.seq asc")
	public List<SuperhuiGoods> findSuperhuiGoods() throws Exception;

}
