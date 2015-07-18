package com.yoxi.hudongtui.utils.common;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;

/**
 * json工具类
 * 
 */
public class JsonUtils {
	private static Logger logger = Logger.getLogger(JsonUtils.class);

	/**
	 * 通用的json处理工具
	 */
	private static final ObjectMapper NORMAL_OBJECT_MAPPER;
	private static final ObjectMapper WRAP_ROOT_NAME_OBJECT_MAPPER;
	/**
	 * 日期格式化
	 */
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final DateFormat ROOT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	static {
		NORMAL_OBJECT_MAPPER = new ObjectMapper();
		// 日期格式化为 yyyy-MM-dd HH:mm:ss
		NORMAL_OBJECT_MAPPER.setDateFormat(DATE_FORMAT);
		// 只包含属性不为空的值，去掉为null和""的数据
		NORMAL_OBJECT_MAPPER.setSerializationInclusion(Inclusion.NON_EMPTY);
		NORMAL_OBJECT_MAPPER.configure(Feature.WRITE_NULL_MAP_VALUES, true);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		NORMAL_OBJECT_MAPPER.configure(
				org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		NORMAL_OBJECT_MAPPER.configure(Feature.FAIL_ON_EMPTY_BEANS, true);

		WRAP_ROOT_NAME_OBJECT_MAPPER = new ObjectMapper();
		// 日期格式化为 yyyy-MM-dd HH:mm:ss
		WRAP_ROOT_NAME_OBJECT_MAPPER.setDateFormat(ROOT_DATE_FORMAT);
		// 只包含属性不为空的值，去掉为null和""的数据
		WRAP_ROOT_NAME_OBJECT_MAPPER.setSerializationInclusion(Inclusion.NON_EMPTY);
		WRAP_ROOT_NAME_OBJECT_MAPPER.configure(Feature.WRITE_NULL_MAP_VALUES, true);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		WRAP_ROOT_NAME_OBJECT_MAPPER.configure(
				org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		WRAP_ROOT_NAME_OBJECT_MAPPER.configure(Feature.FAIL_ON_EMPTY_BEANS, true);
		WRAP_ROOT_NAME_OBJECT_MAPPER.configure(Feature.WRAP_ROOT_VALUE, true);
		WRAP_ROOT_NAME_OBJECT_MAPPER.configure(
				org.codehaus.jackson.map.DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);
		

	}

	public static <T> T toObject(String json, Class<T> clazz) {
		try {
			return NORMAL_OBJECT_MAPPER.readValue(json, clazz);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> String toJson(T entity) {
		try {
			return NORMAL_OBJECT_MAPPER.writeValueAsString(entity);
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> T toCollection(String json, TypeReference<T> typeReference) {
		try {
			return NORMAL_OBJECT_MAPPER.readValue(json, typeReference);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> T toObjectWithRootName(String json, Class<T> clazz) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				return WRAP_ROOT_NAME_OBJECT_MAPPER.readValue(json, clazz);
			}
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> String toJsonWithRootName(T entity) {
		try {
			return WRAP_ROOT_NAME_OBJECT_MAPPER.writeValueAsString(entity);
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> T toCollectionWithRootName(String json, TypeReference<T> typeReference) {
		try {
			return WRAP_ROOT_NAME_OBJECT_MAPPER.readValue(json, typeReference);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
