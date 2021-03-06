package com.yoxi.hudongtui.service.youhui;

import java.util.List;

import com.yoxi.hudongtui.model.content.SuperhuiGoods;
import com.yoxi.hudongtui.model.content.SuperhuiGoodsVo;

public interface ISuperhuiGoodsService {

	public List<SuperhuiGoodsVo> findSuperhuiGoodsList(int id, int startRow,
			int pageSize) throws Exception;

	public List<SuperhuiGoods> findAllSuperhuiGoods(int startRow, int pageSize)
			throws Exception;
}
