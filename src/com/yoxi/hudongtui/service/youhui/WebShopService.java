package com.yoxi.hudongtui.service.youhui;

import java.util.List;

import com.yoxi.hudongtui.model.content.WebShopVO;

public interface WebShopService {
	public List<WebShopVO> findWebShopList(int id) throws Exception;
}
