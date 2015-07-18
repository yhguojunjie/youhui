package com.yoxi.hudongtui.service.content.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.content.BrandDAO;
import com.yoxi.hudongtui.dao.user.UserDAO;
import com.yoxi.hudongtui.model.content.Brand;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.content.IBrandService;
import com.yoxi.hudongtui.utils.common.ConvertUtil;

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
public class BrandService implements IBrandService {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private BrandDAO brandDAO;

	@Override
	public List<Brand> getTopBrand(String findStr) throws Exception {
		List<Brand> brandList = null;

		List<User> userList = userDAO.findByStr(findStr);
		if (CollectionUtils.isNotEmpty(userList)) {
			brandList = new ArrayList<Brand>();
			for (User user : userList) {
				Brand brand = new Brand();
				if (StringUtils.isNotBlank(user.getHeadimgUrl())) {
					brand.setLogo(ConvertUtil.procImgPath(user.getHeadimgUrl()));
				}
				brand.setUserId(user.getUserId());
				brand.setNickName(user.getNickName());
				brandList.add(brand);
			}
		}
		return brandList;
	}

	@Override
	public List<Brand> getAgentTopBrand(String findStr) throws Exception {
		List<Brand> brandList = null;
		brandList = brandDAO.findByStr(findStr);

		List<Brand> brandList1 = new ArrayList<Brand>();
		for (Brand brand : brandList) {
			Brand brand1 = new Brand();
			if (StringUtils.isNotBlank(brand.getLogo())) {
				brand1.setLogo(ConvertUtil.procImgPath(brand.getLogo()));
			} else {
				brand1.setLogo(ConvertUtil.procImgPath(brand.getHeadimgUrl()));
			}
			brand1.setUserId(brand.getUserId());
			brand1.setNickName(brand.getNickName());
			brandList1.add(brand1);
		}
		return brandList1;
	}

	@Override
	public int getTotalCount() throws Exception {
		return userDAO.getTotalCount();
	}

	@Override
	public int getAgentTotalCount(String findStr) throws Exception {
		return brandDAO.getTotalCount(findStr);
	}

}
