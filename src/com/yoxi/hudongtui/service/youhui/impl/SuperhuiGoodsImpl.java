package com.yoxi.hudongtui.service.youhui.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.youhui.SuperhuiGoodsDAO;
import com.yoxi.hudongtui.model.content.SuperhuiGoods;
import com.yoxi.hudongtui.model.content.SuperhuiGoodsVo;
import com.yoxi.hudongtui.service.youhui.ISuperhuiGoodsService;

@Service
public class SuperhuiGoodsImpl implements ISuperhuiGoodsService {
	@Autowired
	private SuperhuiGoodsDAO superhuiGoodsDAO;

	/**
	 * 超级优惠商品列表
	 * 
	 * @return List<SuperhuiGoods>
	 */
	@Override
	public List<SuperhuiGoods> findAllSuperhuiGoods(int startRow, int pageSize)
			throws Exception {

		List<SuperhuiGoods> superhuiGoods = superhuiGoodsDAO
				.findAllSuperhuiGoods(startRow, pageSize);
		return superhuiGoods;
	}

	/**
	 * 超级优惠商品列表
	 * 
	 * @return List<SuperhuiGoods>
	 */
	@Override
	public List<SuperhuiGoodsVo> findSuperhuiGoodsList(int id, int startRow,
			int pageSize) throws Exception {
		// 根据台排序字段组织语句
		String condition = "where b.goods_id='" + id + "'";
		List<SuperhuiGoodsVo> superhuiGoods = superhuiGoodsDAO
				.findSuperhuiGoods(condition, startRow, pageSize);
		return superhuiGoods;
	}

}
