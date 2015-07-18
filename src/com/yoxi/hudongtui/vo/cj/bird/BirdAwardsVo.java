package com.yoxi.hudongtui.vo.cj.bird;

public class BirdAwardsVo {

	private Integer prizeType;

	private String prizeName;
	
	private Integer realNum;
	
	private Integer level;//关数
	
	private String picUrl;
	
	private String exchangeState;

	public Integer getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(Integer prizeType) {
		this.prizeType = prizeType;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public Integer getRealNum() {
		return realNum;
	}

	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getExchangeState() {
		return exchangeState;
	}

	public void setExchangeState(String exchangeState) {
		this.exchangeState = exchangeState;
	}
	
}
