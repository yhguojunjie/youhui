package com.yoxi.hudongtui.service.content;

import java.util.List;

import com.yoxi.hudongtui.model.content.Brand;

/**
 * 
 * 品牌
 * 
 * @author wql
 * 
 * @Date 2015年3月19日
 * 
 */
public interface IBrandService {

	/**
	 * 获取品牌列表
	 * 
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<Brand> getTopBrand(String findStr) throws Exception;

	public List<Brand> getAgentTopBrand(String findStr) throws Exception;

	/**
	 * 品牌总数
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getTotalCount() throws Exception;

	public int getAgentTotalCount(String findStr) throws Exception;
}
