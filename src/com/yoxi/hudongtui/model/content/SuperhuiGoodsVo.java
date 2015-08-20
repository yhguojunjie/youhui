package com.yoxi.hudongtui.model.content;

/**
 * 超级优惠商品
 * 
 */

public class SuperhuiGoodsVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8073996850903180601L;
	/** 专场还是抢购(1 是专场，2是抢购) */
	private java.lang.String allbuyoronly;
	/** 几折包邮 */
	private java.lang.String baoyou;
	/** 已购买人数 */
	private java.lang.Integer buyNum;

	/** 商品促销价 */
	private java.lang.Double chuprice;

	/** 访问人数 */
	private java.lang.Integer clickNum;

	/** 版权信息 */
	private java.lang.String copyright;

	/*	*//** createTime */

	private java.lang.String createTime;

	/** 商品描述 */
	private java.lang.String description;
	/** 所属类别 */
	private java.lang.Integer goods_classificat_id;

	/** goods_id */
	private java.lang.Integer goods_id;

	/** 商品图标 */
	private java.lang.String goodsicon;

	/** 商品名称 */
	private java.lang.String goodsname;

	/** 商品优惠价（帮you惠价） */
	private java.lang.Double huirice;

	/** id */
	private java.lang.Integer id;
	/** 是否是商家做活动(1 是活动，2不是活动) */
	private java.lang.String isactive;
	/** 限量数量 */
	private java.lang.Integer limitbuyNum;

	/** 商品原价 */
	private java.lang.Double normalprice;
	/** 发布时间 */
	private java.util.Date publishTime;
	/** 排序 */
	private java.lang.Integer seq;

	/** 商品地址 */
	private java.lang.String showUrl;

	/** superhuiGoods_id */
	private java.lang.Integer superhuiGoods_id;

	/** 修改时间 */
	private java.util.Date updateTime;
	/** 上传时间 */
	private java.util.Date uploadTime;
	/** 版本信息 */
	private java.lang.String version;

	/** 商品所属商城 */
	private java.lang.Integer web_shop_id;
	public java.lang.String getAllbuyoronly() {
		return allbuyoronly;
	}

	public java.lang.String getBaoyou() {
		return baoyou;
	}
	public java.lang.Integer getBuyNum() {
		return buyNum;
	}
	public java.lang.Double getChuprice() {
		return chuprice;
	}
	public java.lang.Integer getClickNum() {
		return clickNum;
	}

	public java.lang.String getCopyright() {
		return copyright;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public java.lang.Integer getGoods_classificat_id() {
		return goods_classificat_id;
	}

	public java.lang.Integer getGoods_id() {
		return goods_id;
	}

	public java.lang.String getGoodsicon() {
		return goodsicon;
	}

	public java.lang.String getGoodsname() {
		return goodsname;
	}

	public java.lang.Double getHuirice() {
		return huirice;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public java.lang.String getIsactive() {
		return isactive;
	}

	public java.lang.Integer getLimitbuyNum() {
		return limitbuyNum;
	}

	public java.lang.Double getNormalprice() {
		return normalprice;
	}

	public java.util.Date getPublishTime() {
		return publishTime;
	}

	public java.lang.Integer getSeq() {
		return seq;
	}

	public java.lang.String getShowUrl() {
		return showUrl;
	}

	public java.lang.Integer getSuperhuiGoods_id() {
		return superhuiGoods_id;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public java.util.Date getUploadTime() {
		return uploadTime;
	}

	public java.lang.String getVersion() {
		return version;
	}

	public java.lang.Integer getWeb_shop_id() {
		return web_shop_id;
	}

	public void setAllbuyoronly(java.lang.String allbuyoronly) {
		this.allbuyoronly = allbuyoronly;
	}

	public void setBaoyou(java.lang.String baoyou) {
		this.baoyou = baoyou;
	}

	public void setBuyNum(java.lang.Integer buyNum) {
		this.buyNum = buyNum;
	}

	public void setChuprice(java.lang.Double chuprice) {
		this.chuprice = chuprice;
	}

	public void setClickNum(java.lang.Integer clickNum) {
		this.clickNum = clickNum;
	}

	public void setCopyright(java.lang.String copyright) {
		this.copyright = copyright;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public void setGoods_classificat_id(java.lang.Integer goods_classificat_id) {
		this.goods_classificat_id = goods_classificat_id;
	}

	public void setGoods_id(java.lang.Integer goods_id) {
		this.goods_id = goods_id;
	}

	public void setGoodsicon(java.lang.String goodsicon) {
		this.goodsicon = goodsicon;
	}

	public void setGoodsname(java.lang.String goodsname) {
		this.goodsname = goodsname;
	}

	public void setHuirice(java.lang.Double huirice) {
		this.huirice = huirice;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setIsactive(java.lang.String isactive) {
		this.isactive = isactive;
	}

	public void setLimitbuyNum(java.lang.Integer limitbuyNum) {
		this.limitbuyNum = limitbuyNum;
	}

	public void setNormalprice(java.lang.Double normalprice) {
		this.normalprice = normalprice;
	}

	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}

	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}

	public void setShowUrl(java.lang.String showUrl) {
		this.showUrl = showUrl;
	}

	public void setSuperhuiGoods_id(java.lang.Integer superhuiGoods_id) {
		this.superhuiGoods_id = superhuiGoods_id;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUploadTime(java.util.Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public void setVersion(java.lang.String version) {
		this.version = version;
	}

	public void setWeb_shop_id(java.lang.Integer web_shop_id) {
		this.web_shop_id = web_shop_id;
	}

}
