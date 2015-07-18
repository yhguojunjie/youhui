package com.yoxi.hudongtui.vo.cj.tree;

public class TreeAwardsVo {

	private Integer prizeType;

	private String prizeName;
	
	private Integer showNum;
	
	private Integer realNum;
	
	private Integer probability;
	
	private String picUrl;
	
	private String exchangeState;
	
	//是否兑换过
	private boolean isExchange;

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

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}

	public Integer getRealNum() {
		return realNum;
	}

	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}

	public Integer getProbability() {
		return probability;
	}

	public void setProbability(Integer probability) {
		this.probability = probability;
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

	public boolean isExchange() {
		return isExchange;
	}

	public void setExchange(boolean isExchange) {
		this.isExchange = isExchange;
	}
	
}
