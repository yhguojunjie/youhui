package com.yoxi.hudongtui.vo.wx.api.user;

import java.util.List;

/**
 * 
 * @author wangql
 * 
 * 2014-03-16
 * 
 * 微信关注者列表实体
 *
 */
public class Subscribe {
		
	private Integer total;
	private Integer count;
	private String next_openid;
	private List<String> openIdList;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	public List<String> getOpenIdList() {
		return openIdList;
	}
	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}
	
	
}
