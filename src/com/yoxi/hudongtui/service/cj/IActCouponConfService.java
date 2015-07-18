package com.yoxi.hudongtui.service.cj;

import com.yoxi.hudongtui.model.cj.coupon.ActCouponConf;

/**
 * 礼券
 * 
 * @author jjb
 *
 * 2015-4-1
 *
 */
public interface IActCouponConfService {

	public Integer save(ActCouponConf actCouponConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActCouponConf actCouponConf);
	
	public ActCouponConf findByActId(final Integer activityId);
}
