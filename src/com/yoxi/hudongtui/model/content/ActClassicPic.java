package com.yoxi.hudongtui.model.content;

import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;


/**
 * 
 * 经典案例图片url
 *
 * @author wql
 *
 * @Date 2015年3月23日
 *
 */
public class ActClassicPic implements java.io.Serializable {
	
	private static final long serialVersionUID = -639510827174507063L;
	/**id*/
	private java.lang.Integer id;
	/**经典案件id*/
	private java.lang.Integer classicId;
	/**图片url*/
	private java.lang.String url;
	/**顺序*/
	private java.lang.Integer seq;
	/**创建时间*/
	private java.util.Date createTime;
	
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
	 *@return: java.lang.Integer  经典案件id
	 */
	public java.lang.Integer getClassicId(){
		return this.classicId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  经典案件id
	 */
	public void setClassicId(java.lang.Integer classicId){
		this.classicId = classicId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片url
	 */
	public java.lang.String getUrl(){
		if(!StringUtils.isNullBlank(url)){
			return ConvertUtil.procImgPath(url);
		}
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片url
	 */
	public void setUrl(java.lang.String url){
		this.url = url;
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

	public java.lang.Integer getSeq() {
		return seq;
	}

	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}

	
}
