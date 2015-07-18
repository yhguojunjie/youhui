package com.yoxi.hudongtui.vo.pay.daipi;

import com.yoxi.hudongtui.vo.ResponseCode;

public class BuyResp extends ResponseCode{

	private static final long serialVersionUID = 7048054693588821953L;
	
	/** 是否已支付 */
	private Boolean isPayed;
	/** 订单号 */
	private Integer orderId;
	/** 代币数 */
	private Double daipis;
	/** 扣减的代币数 */
	private Double deductionDaipis;
	/** 缺少的代币数 */
	private Double lackDaipis;
	
	public Boolean getIsPayed() {
		return isPayed;
	}
	public void setIsPayed(Boolean isPayed) {
		this.isPayed = isPayed;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Double getDaipis() {
		return daipis;
	}
	public void setDaipis(Double daipis) {
		this.daipis = daipis;
	}
	public Double getDeductionDaipis() {
		return deductionDaipis;
	}
	public void setDeductionDaipis(Double deductionDaipis) {
		this.deductionDaipis = deductionDaipis;
	}
	public Double getLackDaipis() {
		return lackDaipis;
	}
	public void setLackDaipis(Double lackDaipis) {
		this.lackDaipis = lackDaipis;
	}

}
