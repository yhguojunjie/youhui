package com.yoxi.hudongtui.service.youhui.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.youhui.WebShopDAO;
import com.yoxi.hudongtui.model.content.WebShopVO;
import com.yoxi.hudongtui.service.youhui.WebShopService;

@Service
public class WebShopImpl implements WebShopService {
	@Autowired
	private WebShopDAO webShopDAO;

	/**
	 * 查找商城列表
	 * 
	 * @throws Exception
	 */
	@Override
	public List<WebShopVO> findWebShopList(int id) throws Exception {
		// 根据台排序字段组织语句
		String condition = "where b.goods_id='" + id + "'";
		List<WebShopVO> WebShops = webShopDAO.findwebShops(condition);
		return WebShops;
	}

}
