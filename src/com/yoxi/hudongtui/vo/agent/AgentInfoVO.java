package com.yoxi.hudongtui.vo.agent;

import java.io.Serializable;

import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;

/**
 * 
 * 代理商VO
 *
 * @author wql
 *
 * @Date 2015年3月19日
 *
 */
public class AgentInfoVO implements Serializable{
	
	private static final long serialVersionUID = -1402907324628659869L;
	/**代理商用id*/
	private java.lang.Integer id;
	/**地址*/
	private java.lang.String address;
	/**logoURL*/
	private java.lang.String logo;
	/**logo描述*/
	private java.lang.String logoDesc;
	/**公众号二维码URL*/
	private java.lang.String wxqrcode;
	/**客服电话*/
	private java.lang.String servicePhone;
	/**客服QQ*/
	private java.lang.String serviceqq;
	/**QQ群*/
	private java.lang.String qqGroup;
	/**客服email*/
	private java.lang.String serviceEmail;
	/**网站icon*/
	private java.lang.String websiteIcon;
	/**网站标示*/
	private java.lang.String websiteTitle;
	/**网站描述*/
	private java.lang.String websiteDesc;
	/**网站关键字*/
	private java.lang.String websiteKeyword;
	/**网站备案信息*/
	private java.lang.String webRecord;
	/**分享跳转的域名*/
	private java.lang.String redirecDomain;
	/**版权信息*/
	private java.lang.String version;
	/**公司名称*/
	private java.lang.String companyName;
	/**域名*/
	private java.lang.String mydomain;
	/**转发域名*/
	private java.lang.String forwardDomain;
	
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.String getAddress() {
		return address;
	}
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	public java.lang.String getServicePhone() {
		return servicePhone;
	}
	public void setServicePhone(java.lang.String servicePhone) {
		this.servicePhone = servicePhone;
	}
	public java.lang.String getServiceqq() {
		return serviceqq;
	}
	public void setServiceqq(java.lang.String serviceqq) {
		this.serviceqq = serviceqq;
	}
	public java.lang.String getLogoDesc() {
		
		return logoDesc;
	}
	public void setLogoDesc(java.lang.String logoDesc) {
		this.logoDesc = logoDesc;
	}
	public java.lang.String getWxqrcode() {
		if(!StringUtils.isNullBlank(wxqrcode)){
			return ConvertUtil.procImgPath(wxqrcode);
		}
		return wxqrcode;
	}
	public void setWxqrcode(java.lang.String wxqrcode) {
		this.wxqrcode = wxqrcode;
	}
	public java.lang.String getLogo() {
		if(!StringUtils.isNullBlank(logo)){
			return ConvertUtil.procImgPath(logo);
		}
		return logo;
	}
	public void setLogo(java.lang.String logo) {
		this.logo = logo;
	}
	public java.lang.String getWebsiteIcon() {
		return websiteIcon;
	}
	public void setWebsiteIcon(java.lang.String websiteIcon) {
		this.websiteIcon = websiteIcon;
	}
	public java.lang.String getWebsiteTitle() {
		return websiteTitle;
	}
	public void setWebsiteTitle(java.lang.String websiteTitle) {
		this.websiteTitle = websiteTitle;
	}
	public java.lang.String getWebsiteDesc() {
		return websiteDesc;
	}
	public void setWebsiteDesc(java.lang.String websiteDesc) {
		this.websiteDesc = websiteDesc;
	}
	public java.lang.String getWebsiteKeyword() {
		return websiteKeyword;
	}
	public void setWebsiteKeyword(java.lang.String websiteKeyword) {
		this.websiteKeyword = websiteKeyword;
	}
	public java.lang.String getQqGroup() {
		return qqGroup;
	}
	public void setQqGroup(java.lang.String qqGroup) {
		this.qqGroup = qqGroup;
	}
	public java.lang.String getRedirecDomain() {
		return redirecDomain;
	}
	public void setRedirecDomain(java.lang.String redirecDomain) {
		this.redirecDomain = redirecDomain;
	}
	public java.lang.String getServiceEmail() {
		return serviceEmail;
	}
	public void setServiceEmail(java.lang.String serviceEmail) {
		this.serviceEmail = serviceEmail;
	}
	public java.lang.String getVersion() {
		return version;
	}
	public void setVersion(java.lang.String version) {
		this.version = version;
	}
	public java.lang.String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(java.lang.String companyName) {
		this.companyName = companyName;
	}
	public java.lang.String getMydomain() {
		return mydomain;
	}
	public void setMydomain(java.lang.String mydomain) {
		this.mydomain = mydomain;
	}
	public java.lang.String getWebRecord() {
		return webRecord;
	}
	public void setWebRecord(java.lang.String webRecord) {
		this.webRecord = webRecord;
	}
	public java.lang.String getForwardDomain() {
		return forwardDomain;
	}
	public void setForwardDomain(java.lang.String forwardDomain) {
		this.forwardDomain = forwardDomain;
	}
	
}
