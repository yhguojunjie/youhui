package com.yoxi.hudongtui.base;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import sun.misc.BASE64Encoder;

/**
 * 测试基类
 * </p>
 * 用法：
 * <pre>
 * String url = serverAddress+"book/{bookId}";
 * int bookId = 1000000001;
 * ResponseEntity<ErrorResult> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ErrorResult.class,bookId);
 * ErrorResult errorResult = responseEntity.getBody();
 * </pre>
 * 用例参考：
 * </br>
 * 1、正常输入：a、存在记录的情况：对象不为空，并且输入参数与输出参数要一致；b、不存在记录的情况：对象为空；
 * </br>
 * 2、非法输入：包括越界、为空、类型、长度4种情况，报错信息不为空并且验证错误码是否正确；
 * </p>
 * 
 * @author yangwc
 * 
 */
public class BaseControllerTest {

	public Log log = LogFactory.getLog(getClass());

	public RestTemplate restTemplate;

	public HttpEntity<?> requestEntity;

	public static final String serverAddress = "http://localhost:8082/school/";

	@Before
	public void setup() throws Exception {
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(
				new StringHttpMessageConverter());
		restTemplate.getMessageConverters().add(
				new MappingJackson2HttpMessageConverter());
		String USERNAME = "admin";
		String PASSWORD = "e10adc3949ba59abbe56e057f20f883e";// 123456
		requestEntity = new HttpEntity<Object>(
				createHeaders(USERNAME, PASSWORD));
	}

	@After
	public void tearDown() throws Exception {
		restTemplate = null;
	}

	private HttpHeaders createHeaders(final String username,
			final String password) throws UnsupportedEncodingException {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				String authHeader = "Basic "+new String((new BASE64Encoder()).encode(auth.getBytes("utf-8")));
				set("Authorization", authHeader);
				// set("Accept-Encoding", "gzip");
			}
		};
	}
}
