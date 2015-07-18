package com.yoxi.hudongtui.utils.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * Bean工具类
 * 
 * @author wangql
 *
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	private static final Logger logger = Logger.getLogger(BeanUtils.class);

	private BeanUtils() {

	}

	/**
	 * 将post参数转为JavaBean对象
	 * @param javabean
	 * @param request
	 * @return
	 */
	public static <T> T toJavaBean(HttpServletRequest request,T javabean) {
		try {
			BeanUtils.populate(javabean, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return javabean;
	}

	/**
	 * 拷贝源对象的属性，到指定目标类的对象，只复制相同的属性
	 * 
	 * @param src
	 * @param des
	 */
	public static void copyPropertysWithoutNull(Object src, Object des) {
		if (src == null) {
			return;
		}
		Class<?> clazz = des.getClass();
		Field[] srcfields = src.getClass().getDeclaredFields();
		Field[] desfields = des.getClass().getDeclaredFields();// 获取不到父类对象属性
		Map<String, String> filesMap = new HashMap<String, String>();
		for (Field field : desfields) {
			filesMap.put(field.getName(), field.getName());
		}
		for (Field field : srcfields) {
			if (field.getName().equals("serialVersionUID")) {
				continue;
			}
			if (filesMap.containsKey(field.getName())) {
				Field f;
				try {
					f = clazz.getDeclaredField(field.getName());
					f.setAccessible(true);
					field.setAccessible(true);
					Object obj = field.get(src);
					if (obj != null)
						f.set(des, obj);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据字段映射，拷贝源对象的属性，到指定目标类的对象
	 * 
	 * @param destClass
	 *            目标类
	 * @param srcObj
	 *            源对象
	 * @param fieldMap
	 *            字段映射 Map<源对象字段名，目标对象字段名>
	 * @return 目标类的对象
	 */
	public static <T> T copyProperties(Class<T> destClass, Object srcObj, Map<String, String> fieldMap) {
		if (destClass == null || srcObj == null || fieldMap == null) {
			return null;
		}
		T destObj = null;
		try {
			destObj = destClass.newInstance();
			Set<Entry<String, String>> entrySet = fieldMap.entrySet();
			for (Entry<String, String> entry : entrySet) {
				String srcField = entry.getKey();
				String destFieldStr = entry.getValue();
				if (StringUtils.isNotBlank(srcField) && StringUtils.isNotBlank(destFieldStr)) {
					String getterName = "get" + StringUtils.capitalize(srcField.toLowerCase());
					Method getter = srcObj.getClass().getMethod(getterName);
					Object result = getter.invoke(srcObj);
					if (result != null) {
						String[] destFields = destFieldStr.split(",");
						for (String destField : destFields) {
							String setterName = "set" + StringUtils.capitalize(destField.toLowerCase());
							Method setter = destClass.getMethod(setterName, result.getClass());
							setter.invoke(destObj, result);
						}
					}
				}
			}
		} catch (Exception e) {
			String msgPattern = "属性拷贝失败! destClass：{0}，srcObj：{1}， fieldMap: {2}";
			logger.error(MessageFormat.format(msgPattern, destClass, srcObj, fieldMap), e);
		}
		return destObj;
	}

}
