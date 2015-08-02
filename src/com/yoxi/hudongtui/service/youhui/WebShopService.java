package com.yoxi.hudongtui.service.youhui;

import java.util.List;

import com.yoxi.hudongtui.model.content.WebShop;

public interface WebShopService {
	public List<WebShop> findWebShopList(int id) throws Exception;
}
