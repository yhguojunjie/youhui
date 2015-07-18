package com.yoxi.hudongtui.service.plugin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.plugin.giftDAO;
import com.yoxi.hudongtui.service.plugin.IGiftService;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.vo.plugin.GiftVo;

@Service
public class GiftServiceImpl implements IGiftService {
	@Autowired
	private giftDAO giftDAO;

	/**
	 * 获取礼品及送出情况
	 * @throws Exception
	 */
	public List<GiftVo> findGifteInfo(String userId, String condition) throws Exception {
		List<GiftVo> giftVos = giftDAO.findGifteInfo(userId);
		if (!ValidateUtil.isEmpty(giftVos)) {
			for (GiftVo giftVo : giftVos) {
				if (giftVo.getDrawUserName() == null) {
					giftVo.setDrawUserName("");
				}
			}
		}
		return giftVos;
	}
}
