package com.yoxi.hudongtui.model.content;

/**
 * 商家-分类关系表
 * 
 */

public class WebShopGoodsclassificat implements java.io.Serializable {
	/** 创建时间 */
	private java.util.Date createTime;

	/** goodsclassificat */
	private Goodsclassificat goodsclassificat;

	/** id */
	private java.lang.Integer id;

	/** webShopid */
	private WebShop webShop;

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public Goodsclassificat getGoodsclassificat() {
		return goodsclassificat;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public WebShop getWebShop() {
		return webShop;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setGoodsclassificat(Goodsclassificat goodsclassificat) {
		this.goodsclassificat = goodsclassificat;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setWebShop(WebShop webShop) {
		this.webShop = webShop;
	}

}
