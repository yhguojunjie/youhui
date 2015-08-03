package com.yoxi.hudongtui.model.content;

/**
 * 商品分类
 * 
 */

public class Goodsclassificat implements java.io.Serializable {
	/** 创建时间 */
	private java.util.Date createTime;

	/** 分类图标 */
	private java.lang.String icon;

	/** id */
	private java.lang.Integer id;

	/** 分类名 */
	private java.lang.String name;

	/** 顺序 */
	private java.lang.Integer seq;

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public java.lang.String getIcon() {
		return icon;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public java.lang.String getName() {
		return name;
	}

	public java.lang.Integer getSeq() {
		return seq;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}

}
