package com.yoxi.hudongtui.vo.wx;

import com.yoxi.hudongtui.vo.user.ThirdVO;

/**
 * 
 * 微信公众平台登录返回
 * 
 * @author wql
 *
 */
public class MpThirdVO extends ThirdVO{

	//昵称 初始为微信昵称
	private String nickName;
	//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	private String headimgurl;
	//微信openId
	private String openId;
	//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private Integer subscribe;
	/**用户所在城市*/
	private java.lang.Integer sex;
	/**用户所在城市*/
	private java.lang.String city;
	/**省份*/
	private java.lang.String province;
	//用户统一标识
	private String unionid;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public java.lang.Integer getSex() {
		return sex;
	}
	public void setSex(java.lang.Integer sex) {
		this.sex = sex;
	}
	public java.lang.String getCity() {
		return city;
	}
	public void setCity(java.lang.String city) {
		this.city = city;
	}
	public java.lang.String getProvince() {
		return province;
	}
	public void setProvince(java.lang.String province) {
		this.province = province;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
}
