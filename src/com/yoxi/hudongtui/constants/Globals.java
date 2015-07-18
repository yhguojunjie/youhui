package com.yoxi.hudongtui.constants;

/**
 * 全局常量
 * 
 * @author wql
 * 
 *         2014-10-14
 * 
 */
public class Globals {
	/**
	 * 用户SESSION
	 */
	public static String SESSION_USER = "SESSION_USER";
	public static Integer SESSION_INTERVAL = 3600; // session会话过期时间
	public static String SESSION_LASTURL = "SESSION_LASTURL";
	/**
	 * 代理商信息 session
	 */
	public static String SESSION_ANGENTINFO = "SESSION_ANGENTINFO";
	public static Integer SESSION_ANGENTINFO_INTERVAL = 180; // session会话过期时间

	/** 最近访问的域名 */
	public static String SESSION_LASTDOMAIN = "SESSION_LASTDOMAIN";
	public static Integer SESSION_LASTDOMAIN_INTERVAL = 180;

	/**
	 * cookie名称
	 */
	public static String COOKIENAME = "hudongtui2015cookiehudongtui";

	/**
	 * 产品种类(购买对象)
	 */
	public static String PRODUCTTYPE_PLUGINBUY = "1"; // 新购买插件
	public static String PRODUCTTYPE_PLUGINRENEWAL = "2"; // 插件续费

	/**
	 * 第三方支付交易状态
	 */
	public static String ORDERSTATUS_WAIT = "0"; // 未支付
	public static String ORDERSTATUS_DONE = "1";// 已支付
	public static String ORDERSTATUS_FAIL = "2";// 支付失败
	/**
	 * 定单交易状态
	 */
	public static String ORDERTRADESTATE_WAIT = "0"; // 等待支付
	public static String ORDERTRADESTATE_CLOSE = "1"; // 交易关闭
	public static String ORDERTRADESTATE_DONE = "2"; // 交易成功

	/**
	 * 下单状态
	 */
	public static String ORDERDOWNSTATUS_OK = "0"; // 下单成功
	public static String ORDERDOWNSTATUS_SYSTEMERROR = "1"; // 系统错误
	public static String ORDERDOWNSTATUS_AUTHFAIL = "2"; // 授权失败
	public static String ORDERDOWNSTATUS_NOCOIN = "3"; // 代币不足
	public static String ORDERDOWNSTATUS_BUYED = "4"; // 已购买产品

	/**
	 * 支付宝交易状态
	 */
	public static String ALIPAY_TRADE_FINISHED = "TRADE_FINISHED"; // 交易完成
	public static String ALIPAY_TRADE_SUCCESS = "TRADE_SUCCESS";// 支付成功
	public static String ALIPAY_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";// 交易创建
	public static String ALIPAY_TRADE_CLOSED = "TRADE_CLOSED";// 交易关闭

	/**
	 * 插件类型
	 */
	public static String PLUGINTYPE_PAP = "1"; // 即插即用
	public static String PLUGINTYPE_CUSTOMER = "2";// 用户定制

	/**
	 * 插件试用天数
	 */
	public static Integer PLUGIN_TRYOU_LIMITDAY = 1;

	/**
	 * 每个插件创建活动个数
	 */
	public static Integer PLUGIN_ACT_LIMITNUM = 1;

	/**
	 * 用户插件使用类型
	 */
	public static String PLUGINUSER_USETYPE_TRYOUT = "0"; // 试用使用
	public static String PLUGINUSER_USETYPE_BUY = "1"; // 购买使用
	public static String PLUGINUSER_USETYPE_FREE = "2";// 免费使用

	/**
	 * 支付类型
	 */
	public static String PURCHASETYPE_REPRECOIN = "1";// 代币
	public static String PURCHASETYPE_ALIPAYDERIC = "2";// 支付宝限时到账
	public static String PURCHASETYPE_ALIPAYWAP = "3";// 支付宝WAP
	public static String PURCHASETYPE_UNIPAYWEB = "4";// 银联WEB
	public static String PURCHASETYPE_UNIPAYWAP = "5";// 银联WAP
	public static String PURCHASETYPE_WENXINPAY = "6";// 微信支付
	public static String PURCHASETYPE_COIN_PAY = "7";// 代币与第三方支付
	public static String PURCHASETYPE_OFFLINE = "8";// 线下交易
	/**
	 * 用户交易类型
	 */
	public static String TRADETYPE_BUY = "1";// 购买消费
	public static String TRADETYPEDESC_BUY = "购买插件";
	public static String TRADETYPE_GETDAIPI = "2";// 获得代币
	public static String TRADETYPEDESC_GETDAIPI = "获得代币";
	public static String TRADETYPE_CONSUMEDAIPI = "3";// 消费代币
	public static String TRADETYPEDESC_CONSUMEDAIPI = "消费代币";
	public static String TRADETYPE_OFFLINE = "4";// 线下消费
	public static String TRADETYPEDESC_OFFLINE = "线下消费";
	
	/**
	 * 用户来源
	 */
	public static String USERSOURCE_REGISTER = "1";// 购买消费
	public static String USERSOURCE_QQ = "2";// QQ
	public static String USERSOURCE_WEIXIN = "3";// 微信WEB
	public static String USERSOURCE_SINAWEIBO = "4";// 新浪微博
	public static String USERSOURCE_TENTXUNWEIBO = "5";// 腾讯微博

	/**
	 * 用户类型
	 */

	public static String USERTYPE_SELLER = "1";// 商家
	public static String USERTYPE_DEVELOPER = "2";// 开发者

	/**
	 * 产品来源
	 */
	public static String SOURCETYPE_PC = "1"; // PC
	public static String SOURCETYPE_WX = "2"; // 微信
	public static String SOURCETYPE_MOBILE = "3";// 手机wap

	// 分页处理相关常量
	public static final String PAGE_TOAGE_CURRENT = "currentpage";
	public static final String PAGE_CURRENT = "currentPage";
	public static final String PAGE_COUNT = "count";
	public static final String PAGE_NUMBER = "page.number";
	public static final String PAGE_FIRST = "page.first";
	public static final String PAGE_LAST = "page.end";
	public static final String PAGE_PRE = "page.pre";
	public static final String PAGE_NEXT = "page.next";

	// 分页显示限制数
	public static final int WXPAGE_SHOW_NUMBER = 5;
	public static final int PAGE_SHOW_NUMBER = 5;
	public static final int HOMEPAGE_SHOW_NUMBER = 5;
	public static final int ACT_BROWSERNUM_LIMIT = 0;

	// 上传图片格式要求
	public static final String EXT_IMAGE = "gif,jpg,jpeg,png,bmp";
	public static final String EXT_MEDIA = "mp3,wav";

	// 快过期时间定义
	public static final int OVERDUE_TIME = -6;

	// excel最大导出条数
	public static final int EXCEL_MAX_EXPORT_NUMBER = 10000;

	// 首页Banner个数
	public static final int PC_INDEX_BANNER_NUM = 6;

	// pc版首页活动推荐个数
	public static final int PC_INDEX_ACTREC_NUM = 3;
	// pc版首页经典个数
	public static final int PC_INDEX_CLASSIC_NUM = 3;
	// pc版首页模板推荐个数
	public static final int PC_INDEX_PLUGINREC_NUM = 3;
	// pc版首页模板预告个数
	public static final int PC_INDEX_PLUGINPRV_NUM = 3;
	// pc版首页渠道推荐个数
	public static final int PC_INDEX_CHANNELREC_NUM = 3;
	// pc版首页渠道推荐个数4
	public static final int PC_INDEX_CHANNELREC_NUM4 = 4;
	// PC版模板库列每页个数
	public static final int PC_PLUGINLIST_NUM = 10;
	// PC版本活动圈活动推荐个数
	public static final int PC_ACTZONE_RECNUM = 3;
	// PC版本活动圈活动一次取的数量
	public static final int PC_ACTZONE_NUM = 8;
	// 发布渠道列表个数
	public static final int PC_INDEX_CHANNE_NUM = 8;
	// PC版经典案例每页取个数
	public static final int PC_ACTCLASSIC_NUM = 8;
	// PC版个人中心我的活动列表个数
	public static final int PC_MY_ACTLIST_NUM = 10;

}
