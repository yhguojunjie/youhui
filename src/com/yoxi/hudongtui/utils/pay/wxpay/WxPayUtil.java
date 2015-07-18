package com.yoxi.hudongtui.utils.pay.wxpay;

import java.util.HashMap;

import com.yoxi.hudongtui.constants.wx.WxPayConstants;
import com.yoxi.hudongtui.exception.SDKRuntimeException;
import com.yoxi.hudongtui.vo.pay.wxpay.PayResultRecv;


public class WxPayUtil {

	/**
	 * 处理签名
	 * 
	 * @param payResultRecv
	 * @return
	 */
	public static boolean processSign(PayResultRecv payResultRecv) {

		// 后台签名
		HashMap<String, String> paramsMap = new HashMap<String, String>();

		if (payResultRecv.getAttach() != null) {
			paramsMap.put("attach", payResultRecv.getAttach()); // 必填 否
		}

		if (payResultRecv.getBank_billno() != null) {
			paramsMap.put("bank_billno", payResultRecv.getBank_billno()); // 必填
																			// 否
		}
		paramsMap.put("bank_type", payResultRecv.getBank_type());

		if (payResultRecv.getBuyer_alias() != null && !"".equals(payResultRecv.getBuyer_alias())) {
			paramsMap.put("buyer_alias", payResultRecv.getBuyer_alias());// 必填 否
		}

		if (payResultRecv.getDiscount() != null) {
			paramsMap.put("discount", String.valueOf(payResultRecv.getDiscount())); // 必填
																					// 否
		}
		paramsMap.put("fee_type", String.valueOf(payResultRecv.getFee_type()));

		if (payResultRecv.getInput_charset() != null) {
			paramsMap.put("input_charset", payResultRecv.getInput_charset());// 必填
																				// 否
																				// 默认GBK
		}

		paramsMap.put("notify_id", payResultRecv.getNotify_id());
		paramsMap.put("out_trade_no", payResultRecv.getOut_trade_no());
		paramsMap.put("partner", payResultRecv.getPartner());

		if (payResultRecv.getPay_info() != null) {
			paramsMap.put("pay_info", payResultRecv.getPay_info());// 必填 否
		}

		if (payResultRecv.getProduct_fee() != null) {
			paramsMap.put("product_fee", String.valueOf(payResultRecv.getProduct_fee()));// 必填
																							// 否
		}

		if (payResultRecv.getService_version() != null) {
			paramsMap.put("service_version", payResultRecv.getService_version());// 必填
																					// 否
		}

		if (payResultRecv.getSign_key_index() != null) {
			paramsMap.put("sign_key_index", String.valueOf(payResultRecv.getSign_key_index()));// 必填
																								// 否
		}

		if (payResultRecv.getSign_type() != null) {
			paramsMap.put("sign_type", payResultRecv.getSign_type()); // 必填 否
																		// 默认MD5
		}
		paramsMap.put("time_end", payResultRecv.getTime_end());
		paramsMap.put("total_fee", String.valueOf(payResultRecv.getTotal_fee()));
		paramsMap.put("trade_mode", String.valueOf(payResultRecv.getTrade_mode()));
		paramsMap.put("trade_state", String.valueOf(payResultRecv.getTrade_state()));
		paramsMap.put("transaction_id", payResultRecv.getTransaction_id());

		if (payResultRecv.getTransport_fee() != null) {
			paramsMap.put("transport_fee", String.valueOf(payResultRecv.getTransport_fee()));// 必填
																								// 否
		}

		WxPayHelper wxPayHelper = new WxPayHelper();

		try {
			String string1 = wxPayHelper.GetQueryStringStyle(paramsMap);
			String signValue = MD5Util.MD5(string1 + "&key=" + WxPayConstants.getPARTNERKEY()).toUpperCase();

			if (payResultRecv.getSign() != null && !"".equals(payResultRecv.getSign())) {
				return signValue.equals(payResultRecv.getSign());
			}

		} catch (SDKRuntimeException e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}
}
