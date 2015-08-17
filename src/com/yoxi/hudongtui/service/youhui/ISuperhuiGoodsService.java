package com.yoxi.hudongtui.service.youhui;

import java.util.List;

import com.yoxi.hudongtui.model.content.SuperhuiGoods;

public interface ISuperhuiGoodsService {

	public List<SuperhuiGoods> findSuperhuiGoodsList(int id) throws Exception;

	public List<SuperhuiGoods> findAllSuperhuiGoods() throws Exception;
}
