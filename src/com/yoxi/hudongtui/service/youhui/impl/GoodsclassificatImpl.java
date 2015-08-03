package com.yoxi.hudongtui.service.youhui.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.youhui.GoodsclassificatDAO;
import com.yoxi.hudongtui.model.content.Goodsclassificat;
import com.yoxi.hudongtui.service.youhui.IGoodsclassificatService;

@Service
public class GoodsclassificatImpl implements IGoodsclassificatService {
	@Autowired
	private GoodsclassificatDAO goodsclassificatDao;

	/**
	 * 查询分类
	 * 
	 * @return List<Goodsclassificat>
	 */
	@Override
	public List<Goodsclassificat> findGoodsclassificat() throws Exception {
		List<Goodsclassificat> goodsclassificatList = goodsclassificatDao
				.findGoodsclassificat();
		return goodsclassificatList;
	}

}
