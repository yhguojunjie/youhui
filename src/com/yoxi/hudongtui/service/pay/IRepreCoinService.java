package com.yoxi.hudongtui.service.pay;

import com.yoxi.hudongtui.vo.order.OrderDownVO;
import com.yoxi.hudongtui.vo.pay.daipi.BuyResp;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;

/**
 * 
 * 代币支付
 * 
 * @author wql
 *
 * 2014-11-22
 *
 */
public interface IRepreCoinService {

	/**
	 * 代币购买
	 * @param orderDown
	 * @return
	 */
	public BuyResp buy(OrderDownVO orderDown,PluginDetailVO plugin)throws Exception;
}
