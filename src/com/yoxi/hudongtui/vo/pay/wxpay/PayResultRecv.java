package com.yoxi.hudongtui.vo.pay.wxpay;

/**
 * 
 * 微信支付成功微信端返回数据
 * 
 * @author wql 
 * 
 * 2014-11-22
 *
 */
public class PayResultRecv {
	private String sign_type;//签名类型
	private String service_version; //版本号，默认为1.0
	private String input_charset;// 字符编码
	private String sign;//签名
	private Integer sign_key_index;//多密钥支持的序号，默认 1
	
	//以下为业务参数
	private Integer trade_mode;// 1-即时到账
	private Integer trade_state;//支付结果：0—成功 其他保留
	private String pay_info;//支付结果信息，支付成功时为空
	private String partner;//商户号,partnerid
	private String bank_type;//银行类型
	private String bank_billno;//银行订单号
	private Integer total_fee;//支付金额 单位为分
	private Integer fee_type;//现金支付币种
	private String notify_id;//支付结果通知id，
	private String transaction_id;//订单号
	private String out_trade_no;//商户系统的订单号
	private String attach;//商户数据包，原样返回
	private String time_end;//支付完成时间
	private Integer transport_fee;//物流费用，单位分，默认0
	private Integer product_fee;//物品费用，单位分
	private Integer discount;//折扣价格,单位分
	private String buyer_alias;//对应买家账号的一个加密串
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getService_version() {
		return service_version;
	}
	public void setService_version(String service_version) {
		this.service_version = service_version;
	}
	public String getInput_charset() {
		return input_charset;
	}
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public Integer getSign_key_index() {
		return sign_key_index;
	}
	public void setSign_key_index(Integer sign_key_index) {
		this.sign_key_index = sign_key_index;
	}
	public Integer getTrade_mode() {
		return trade_mode;
	}
	public void setTrade_mode(Integer trade_mode) {
		this.trade_mode = trade_mode;
	}
	public Integer getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(Integer trade_state) {
		this.trade_state = trade_state;
	}
	public String getPay_info() {
		return pay_info;
	}
	public void setPay_info(String pay_info) {
		this.pay_info = pay_info;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public String getBank_billno() {
		return bank_billno;
	}
	public void setBank_billno(String bank_billno) {
		this.bank_billno = bank_billno;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public Integer getFee_type() {
		return fee_type;
	}
	public void setFee_type(Integer fee_type) {
		this.fee_type = fee_type;
	}
	public String getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	public Integer getTransport_fee() {
		return transport_fee;
	}
	public void setTransport_fee(Integer transport_fee) {
		this.transport_fee = transport_fee;
	}
	public Integer getProduct_fee() {
		return product_fee;
	}
	public void setProduct_fee(Integer product_fee) {
		this.product_fee = product_fee;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public String getBuyer_alias() {
		return buyer_alias;
	}
	public void setBuyer_alias(String buyer_alias) {
		this.buyer_alias = buyer_alias;
	}
	
}
