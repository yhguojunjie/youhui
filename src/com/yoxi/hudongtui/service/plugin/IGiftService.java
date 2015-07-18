package com.yoxi.hudongtui.service.plugin;

import java.util.List;

import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.vo.plugin.GiftVo;

public interface IGiftService {

	/**
	 * 获取礼品及送出情况
	 * @param condition TODO
	 * @throws Exception
	 */
	public List<GiftVo> findGifteInfo(@SQLParam("condition") String userId, String condition) throws Exception;

}
