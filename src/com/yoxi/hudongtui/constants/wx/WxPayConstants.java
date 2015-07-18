package com.yoxi.hudongtui.constants.wx;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.yoxi.hudongtui.utils.common.ReadProperties;


public class WxPayConstants {

	// 商户号
	private static String PARTNERID;
	//初始密钥
	private static String PARTNERKEY;
	// appid
	private static String APPID;
	
	private static String APPSECRET;
	
	private static String PAYSIGNKEY;
	
	private static String NOTIFYURL;
	
	private static String AUNOTIFYURL;
	

	public static String PATH = "/wxpay.properties";
	private static Properties properties;
	static {
		InputStream is = null;
		try {
			is = ReadProperties.class.getResourceAsStream(PATH);
			properties = new Properties();
			properties.load(is);
			PARTNERID = properties.getProperty("PartnerID");
			PARTNERKEY = properties.getProperty("PartnerKey");
			APPID = properties.getProperty("AppID");
			APPSECRET = properties.getProperty("AppSecret");
			PAYSIGNKEY = properties.getProperty("PaySignKey");
			NOTIFYURL = properties.getProperty("notifyurl");
			AUNOTIFYURL = properties.getProperty("AuNotifyurl");
		} catch (Exception e) {
			System.out.println(e + "file  not found");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	

	/*public static String getRSA_PUBLIC() {
		return RSA_PUBLIC;
	}*/

	public static String getPARTNERID() {
		return PARTNERID;
	}

	public static String getPARTNERKEY() {
		return PARTNERKEY;
	}

	public static String getAPPID() {
		return APPID;
	}

	public static String getAPPSECRET() {
		return APPSECRET;
	}


	public static String getPAYSIGNKEY() {
		return PAYSIGNKEY;
	}

	public static String getNOTIFYURL() {
		return NOTIFYURL;
	}

	public static String getAUNOTIFYURL() {
		return AUNOTIFYURL;
	}

	public static Properties getProperties() {
		return properties;
	}
}