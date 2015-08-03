package com.yoxi.hudongtui.dao.youhui;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.yoxi.hudongtui.model.content.Goodsclassificat;

/**
 * 商城分类DAO
 * 
 * @author guojunjie 2015-8-2
 */
@DAO
public interface GoodsclassificatDAO {

	/**
	 * 查询分类列表信息
	 * 
	 * @return List<Goodsclassificat>
	 */
	@SQL("SELECT * from t_goods_classificat a order by a.seq asc")
	public List<Goodsclassificat> findGoodsclassificat() throws Exception;

}
