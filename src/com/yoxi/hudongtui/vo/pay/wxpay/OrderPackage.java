package com.yoxi.hudongtui.vo.pay.wxpay;

/**
 * 提交给微信订单信息
 * 
 * @author wql
 * 
 * 2014-11-22
 * 
 *
 */
public class OrderPackage {

	private String bank_type;//银行通道类型
	private String body; //商品描述
	private String attach; //附加信息
	private String partner;//商户号
	private String out_trade_no;//商户订单号
	private String total_fee;//订单总金额
	private String fee_type;//支付币种
	private String notify_url;//通知URL
	private String spbill_create_ip;//订单生成的机器 IP
	private String time_start;//交易起始时间
	private String time_expire;//交易结束时间;
	private String transport_fee;//物流费用
	private String product_fee;//商品费用
	private String goods_tag;//商品标记
	private String input_charset;//传入参数字符编码
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public String getTransport_fee() {
		return transport_fee;
	}
	public void setTransport_fee(String transport_fee) {
		this.transport_fee = transport_fee;
	}
	public String getProduct_fee() {
		return product_fee;
	}
	public void setProduct_fee(String product_fee) {
		this.product_fee = product_fee;
	}
	public String getGoods_tag() {
		return goods_tag;
	}
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	public String getInput_charset() {
		return input_charset;
	}
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}
	
	
}
