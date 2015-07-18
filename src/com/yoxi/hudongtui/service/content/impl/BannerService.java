package com.yoxi.hudongtui.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.content.BannerDAO;
import com.yoxi.hudongtui.model.content.Banner;
import com.yoxi.hudongtui.service.content.IBannerService;

/**
 * 
 * 品牌
 * 
 * @author wql
 * 
 * @Date 2015年3月19日
 * 
 */
@Service
public class BannerService implements IBannerService {

	@Autowired
	private BannerDAO bannerDAO;

	@Override
	public List<Banner> getBannerList(String findStr) throws Exception {
		// TODO Auto-generated method stub
		List<Banner> bannerList = bannerDAO.findByStr(findStr);
		return bannerList;
	}

	@Override
	public List<Banner> getAgentBannerList(String findStr) throws Exception {
		// TODO Auto-generated method stub
		List<Banner> bannerList = bannerDAO.findAgentByStr(findStr);
		return bannerList;
	}
}
