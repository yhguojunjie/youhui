package com.yoxi.hudongtui.utils.common;

import java.io.InputStream;
import java.util.Properties;

/**
 * 获取properties配置文件属性值
 * 
 * @author wql
 * 
 * 2014-11-12
 * 
 */
public class ReadProperties {

	public static String PATH = "/hudt.properties";
	private static Properties properties;
	static {
		try {
			InputStream is = ReadProperties.class.getResourceAsStream(PATH);
			properties = new Properties();
			properties.load(is);
			if (is != null)
				is.close();
		} catch (Exception e) {
			System.out.println(e + "file  not found");
		}
	}

	/**
	 * getPara 获取properties文件里值
	 * 
	 * @param propertiesPath
	 * @param key
	 * @return
	 */
	public static String getPara(String propertiesPath, String key) {
		return properties.getProperty(key);
	}

	public static String getPara(String key) {
		return properties.getProperty(key);
	}

	public static void main(String[] args) {

	}
}
