package com.yoxi.hudongtui.service.youhui.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.youhui.SuperhuiGoodsDAO;
import com.yoxi.hudongtui.model.content.SuperhuiGoods;
import com.yoxi.hudongtui.service.youhui.ISuperhuiGoodsService;

@Service
public class SuperhuiGoodsImpl implements ISuperhuiGoodsService {
	@Autowired
	private SuperhuiGoodsDAO superhuiGoodsDAO;

	/**
	 * 超级优惠商品
	 * 
	 * @return List<SuperhuiGoods>
	 */
	@Override
	public List<SuperhuiGoods> findSuperhuiGoods() throws Exception {
		List<SuperhuiGoods> superhuiGoodsList = superhuiGoodsDAO
				.findSuperhuiGoods();
		return superhuiGoodsList;
	}

}
