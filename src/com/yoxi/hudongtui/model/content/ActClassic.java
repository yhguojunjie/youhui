package com.yoxi.hudongtui.model.content;

/**
 * 经典活动
 * @Description
 *
 * @author wql
 *
 * @Date 2015年3月22日
 *
 */
public class ActClassic  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1296805731437080906L;
	/**id*/
	private java.lang.Integer id;
	/**活动id*/
	private java.lang.Integer actId;
	/**顺序*/
	private java.lang.Integer seq;
	/**品牌名(此字段为空，则使用活动默认)*/
	private java.lang.String bannerName;
	/**品牌logo(此字段为空，则使用活动默认)*/
	private java.lang.String bannerLogo;
	/**创建时间*/
	private java.util.Date createTime;
	/**修改时间*/
	private java.util.Date updateTime;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  活动id
	 */
	public java.lang.Integer getActId(){
		return this.actId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  活动id
	 */
	public void setActId(java.lang.Integer actId){
		this.actId = actId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  顺序
	 */
	public java.lang.Integer getSeq(){
		return this.seq;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  顺序
	 */
	public void setSeq(java.lang.Integer seq){
		this.seq = seq;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改时间
	 */
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.lang.String getBannerName() {
		return bannerName;
	}

	public void setBannerName(java.lang.String bannerName) {
		this.bannerName = bannerName;
	}

	public java.lang.String getBannerLogo() {
		return bannerLogo;
	}

	public void setBannerLogo(java.lang.String bannerLogo) {
		this.bannerLogo = bannerLogo;
	}
	
}
