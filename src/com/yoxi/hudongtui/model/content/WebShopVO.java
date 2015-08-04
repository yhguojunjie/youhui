package com.yoxi.hudongtui.model.content;

/**
 * 商家
 * 
 */
public class WebShopVO implements java.io.Serializable {
	/** 创建时间 */
	private java.util.Date createTime;

	/** 优惠比例 */
	private java.lang.String discountrate;

	/** 入驻时间 */
	private java.util.Date enterTime;

	/** goods_id */
	private java.lang.Integer goods_id;
	/** id */
	private java.lang.Integer id;

	/** 二级分类名称 */
	private java.lang.String secondname;

	/** 顺序 */
	private java.lang.Integer seq;

	/** 商城链接 */
	private java.lang.String shophref;

	/** 商城图片 */
	private java.lang.String shopimge;

	/** 商城名称 */
	private java.lang.String shopname;

	/** webshop_id */
	private java.lang.Integer webshop_id;

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public java.lang.String getDiscountrate() {
		return discountrate;
	}

	public java.util.Date getEnterTime() {
		return enterTime;
	}

	public java.lang.Integer getGoods_id() {
		return goods_id;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public java.lang.String getSecondname() {
		return secondname;
	}

	public java.lang.Integer getSeq() {
		return seq;
	}

	public java.lang.String getShophref() {
		return shophref;
	}

	public java.lang.String getShopimge() {
		return shopimge;
	}

	public java.lang.String getShopname() {
		return shopname;
	}

	public java.lang.Integer getWebshop_id() {
		return webshop_id;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setDiscountrate(java.lang.String discountrate) {
		this.discountrate = discountrate;
	}

	public void setEnterTime(java.util.Date enterTime) {
		this.enterTime = enterTime;
	}

	public void setGoods_id(java.lang.Integer goods_id) {
		this.goods_id = goods_id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setSecondname(java.lang.String secondname) {
		this.secondname = secondname;
	}

	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}

	public void setShophref(java.lang.String shophref) {
		this.shophref = shophref;
	}

	public void setShopimge(java.lang.String shopimge) {
		this.shopimge = shopimge;
	}

	public void setShopname(java.lang.String shopname) {
		this.shopname = shopname;
	}

	public void setWebshop_id(java.lang.Integer webshop_id) {
		this.webshop_id = webshop_id;
	}

}
