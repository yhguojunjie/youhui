package com.yoxi.hudongtui.vo.content;

import java.io.Serializable;

/**
 *
 * 模板预告VO
 *
 * @author wql
 *
 * @Date 2015年3月22日
 *
 */
public class PluginPrevVO implements Serializable {
	private static final long serialVersionUID = -1982585149446347729L;
	/**id*/
	private java.lang.Integer id;
	/**模板名称*/
	private java.lang.String name;
	/**简介*/
	private java.lang.String description;
	/**预计上架时间*/
	private java.util.Date shelTime;
	/**顺序*/
	private java.lang.Integer seq;
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getDescription() {
		return description;
	}
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	public java.util.Date getShelTime() {
		return shelTime;
	}
	public void setShelTime(java.util.Date shelTime) {
		this.shelTime = shelTime;
	}
	public java.lang.Integer getSeq() {
		return seq;
	}
	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}
	
	
}
