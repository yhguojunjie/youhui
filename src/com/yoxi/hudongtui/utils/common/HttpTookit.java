package com.yoxi.hudongtui.utils.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import net.paoding.rose.web.Invocation;

import org.apache.commons.collections.MapUtils;

import sun.misc.BASE64Encoder;

/**
 * Http工具类
 * 
 * 
 */
public class HttpTookit {

	/**
	 * 单纯模拟表单提交，未带附件
	 * 
	 * @param urlAddr
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static boolean send(String urlAddr, Map<String, String> map) throws Exception{
		HttpURLConnection conn = null;
		boolean isSuccess = false;
		StringBuffer params = new StringBuffer();
		if (MapUtils.isNotEmpty(map)) {
			for (Entry<String, String> entry : map.entrySet()) {
				params.append(entry.getKey());
				params.append("=");
				params.append(entry.getValue());
				params.append("&");
			}
		}
		if (params.length() > 0) {
			params.deleteCharAt(params.length() - 1);
		}
		try {
			URL url = new URL(urlAddr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(params.length()));
			conn.setDoInput(true);
			conn.connect();
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(params.toString());
			out.flush();
			out.close();
			int code = conn.getResponseCode();
			if (code != 200) {
				System.out.println("ERROR===" + code);
			} else {
				isSuccess = true;
				System.out.println("Success!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			conn.disconnect();
		}
		return isSuccess;
	}

	/**
	 * 
	 * @param urlAddr
	 * @param map
	 * @param uploadFile
	 * @return
	 * @throws Exception
	 */
	public static boolean sendFile(String urlAddr, Map<String, String> map, String uploadFile) throws Exception{
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "---------------------------7d4a6d158c9";
		String newName = "image.jpg";
		HttpURLConnection con = null;
		boolean isSuccess = false;
		try {
			URL url = new URL(urlAddr);
			con = (HttpURLConnection) url.openConnection();
			/* 允许Input、Output，不使用Cache */
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			/* 设置传送的method=POST */
			con.setRequestMethod("POST");
			/* setRequestProperty */
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

			StringBuffer params = new StringBuffer();
			// 上传的表单参数部分，格式请参考文章
			for (Map.Entry<String, String> entry : map.entrySet()) {// 构建表单字段内容
				params.append("--");
				params.append(boundary);
				params.append("\r\n");
				params.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n");
				params.append(entry.getValue());
				params.append("\r\n");
			}
			params.append("--");
			params.append(boundary);
			params.append("\r\n");

			/* 设置DataOutputStream */
			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			ds.write(params.toString().getBytes());
			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes("Content-Disposition: form-data; " + "name=\"file1\";filename=\"" + newName + "\"" + end);
			ds.writeBytes(end);
			/* 取得文件的FileInputStream */
			FileInputStream fStream = new FileInputStream(uploadFile);
			/* 设置每次写入1024bytes */
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int length = -1;
			/* 从文件读取数据至缓冲区 */
			while ((length = fStream.read(buffer)) != -1) {
				/* 将资料写入DataOutputStream中 */
				ds.write(buffer, 0, length);
			}
			ds.writeBytes(end);
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
			/* close streams */
			fStream.close();
			ds.flush();
			ds.close();
			int code = con.getResponseCode();
			if (code != 200) {
				System.out.println("ERROR===" + code);
			} else {
				isSuccess = true;
				System.out.println("Success!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			con.disconnect();
		}
		return isSuccess;
	}

	/**
	 * 发送HTTP请求，并获取取得的数据
	 * 
	 * @param urlAddr 请求地址
	 * @param map 请求参数
	 * @param type 请求类型 POST或GET
	 * @return 服务器返回结果串
	 * @throws Exception
	 */
	public static String send(String urlAddr, Map<String, String> map, String type) throws Exception{
		HttpURLConnection conn = null;
		BufferedReader br = null;
		OutputStreamWriter out = null;
		StringBuffer params = new StringBuffer();
		StringBuffer result = new StringBuffer();
		// 参数拼接
		if (MapUtils.isNotEmpty(map)) {
			for (Entry<String, String> entry : map.entrySet()) {
				params.append(entry.getKey());
				params.append("=");
				params.append(entry.getValue());
				params.append("&");
			}
		}
		if (params.length() > 0) {
			params.deleteCharAt(params.length() - 1);
		}
		try {
			if ("POST".equalsIgnoreCase(type)) {
				URL url = new URL(urlAddr);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod(type);
				conn.setDoOutput(true);
				conn.setUseCaches(false);
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				conn.setRequestProperty("Content-Length", String.valueOf(params.length()));
				conn.setDoInput(true);
				conn.connect();
				out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
				out.write(params.toString());
				out.flush();
				out.close();
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String r = br.readLine();
				while (r != null) {
					result.append(r);
					r = br.readLine();
				}
			} else {
				URL url = null;
				if (params.length() > 0) {
					url = new URL(urlAddr + "?" + params.toString());
				} else {
					url = new URL(urlAddr);
				}
				conn = (HttpURLConnection) url.openConnection();
				conn.setUseCaches(false);
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				conn.connect();
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String r = br.readLine();
				while (r != null) {
					result.append(r);
					r = br.readLine();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (br != null) {
				br.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return result.toString();
	}

	/**
	 * 
	 * 发送Post请求
	 * 
	 * @param urlAddr 请求地址
	 * @param map 参数对
	 * @return 结果字符串
	 */
	public static String sendPost(String urlAddr, Map<String, String> map) throws Exception{
		HttpURLConnection conn = null;
		BufferedReader br = null;
		OutputStreamWriter out = null;
		StringBuffer params = new StringBuffer();
		StringBuffer result = new StringBuffer();
		// 参数拼接
		if (MapUtils.isNotEmpty(map)) {
			for (Entry<String, String> entry : map.entrySet()) {
				if (entry.getValue() != null) {
					params.append(entry.getKey());
					params.append("=");
					params.append(entry.getValue());
					params.append("&");
				}
			}

			if (params.length() > 0) {
				params.deleteCharAt(params.length() - 1);
			}
		}

		try {
			URL url = new URL(urlAddr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(params.length()));
			conn.setDoInput(true);
			conn.connect();
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(params.toString());
			out.flush();
			out.close();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String r = br.readLine();
			while (r != null) {
				result.append(r);
				r = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (br != null) {
				br.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return result.toString();
	}

	/**
	 * 
	 * 发送Get请求
	 * 
	 * @param urlAddr 请求地址
	 * @param map 参数对
	 * @return 结果字符串
	 */
	public static String sendGet(String urlAddr, Map<String, String> map) throws Exception{
		HttpURLConnection conn = null;
		BufferedReader br = null;
		OutputStreamWriter out = null;
		StringBuffer params = new StringBuffer();
		StringBuffer result = new StringBuffer();
		// 参数拼接
		if (MapUtils.isNotEmpty(map)) {
			for (Entry<String, String> entry : map.entrySet()) {
				if (entry.getValue() != null) {
					params.append(entry.getKey());
					params.append("=");
					params.append(entry.getValue());
					params.append("&");
				}
			}
			if (params.length() > 0) {
				params.deleteCharAt(params.length() - 1);
			}
		}

		try {
			URL url = null;
			if (params.length() > 0) {
				url = new URL(urlAddr + "?" + params.toString());
			} else {
				url = new URL(urlAddr);
			}
			conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.connect();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String r = br.readLine();
			while (r != null) {
				result.append(r);
				r = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (br != null) {
				br.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return result.toString();
	}

	/**
	 * 发送Post请求
	 * 
	 * @param username
	 * @param password
	 * @param urlAddr
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String sendPostWithBaseAuthenticate(String username, String password, String urlAddr, Map<String,String> map) throws Exception{
		return sendWithType("POST", username, password, urlAddr, map);
	}

	/**
	 * 
	 * 发送Get请求
	 * 
	 * @param urlAddr 请求地址
	 * @param map 参数对
	 * @return 结果字符串
	 */
	public static String sendGetWithBaseAuthenticate(String username, String password, String urlAddr, Map<String,String> map) throws Exception{
		HttpURLConnection conn = null;
		BufferedReader br = null;
		OutputStreamWriter out = null;
		StringBuffer params = new StringBuffer();
		StringBuffer result = new StringBuffer();
		// 参数拼接
		if (MapUtils.isNotEmpty(map)) {
			for (Entry<String, String> entry : map.entrySet()) {
				if (entry.getValue() != null) {
					params.append(entry.getKey());
					params.append("=");
					params.append(entry.getValue());
					params.append("&");
				}
			}
			if (params.length() > 0) {
				params.deleteCharAt(params.length() - 1);
			}
		}

		try {
			// Authenticator.setDefault(new BaseAuthenticator(username,
			// password));
			// Properties prop = System.getProperties();
			// 设置http访问要使用的代理服务器的地址
			// prop.setProperty("http.proxyHost", "localhost");
			// 设置http访问要使用的代理服务器的端口
			// prop.setProperty("http.proxyPort", "8082");
			URL url = null;
			if (params.length() > 0) {
				url = new URL(urlAddr + "?" + params.toString());
			} else {
				url = new URL(urlAddr);
			}
			conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Authorization", genAuthorization(username, password));
			conn.connect();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String r = br.readLine();
			while (r != null) {
				result.append(r);
				r = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (br != null) {
				br.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return result.toString();
	}

	/**
	 * 发送Post请求
	 * 
	 * @param username
	 * @param password
	 * @param urlAddr
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String sendPutWithBaseAuthenticate(String username, String password, String urlAddr, Map<String,String> map) throws Exception{
		return sendWithType("PUT", username, password, urlAddr, map);
	}

	/**
	 * 发送Post请求
	 * 
	 * @param username
	 * @param password
	 * @param urlAddr
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String sendDeleteWithBaseAuthenticate(String username, String password, String urlAddr, Map<String,String> map) throws Exception{
		HttpURLConnection conn = null;
		BufferedReader br = null;
		OutputStreamWriter out = null;
		StringBuffer params = new StringBuffer();
		StringBuffer result = new StringBuffer();
		// 参数拼接
		if (MapUtils.isNotEmpty(map)) {
			for (Entry<String, String> entry : map.entrySet()) {
				if (entry.getValue() != null) {
					params.append(entry.getKey());
					params.append("=");
					params.append(entry.getValue());
					params.append("&");
				}
			}
			if (params.length() > 0) {
				params.deleteCharAt(params.length() - 1);
			}
		}

		try {
			URL url = new URL(urlAddr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");
			//conn.setUseCaches(false);
			//conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			//conn.setRequestProperty("Content-Length", String.valueOf(params.length()));
			conn.setRequestProperty("Authorization",genAuthorization(username, password));
			//conn.setDoInput(true);
			//conn.connect();
			//屏蔽掉的代码是错误的，java.net.ProtocolException: HTTP method DELETE doesn't support output
			//out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			//out.write(params.toString());
			//out.flush();
			//out.close();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String r = br.readLine() ;
			while(r  != null){
				result.append(r);
				r = br.readLine();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (br != null) {
				br.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return result.toString();
	}

	/**
	 * 发送Post请求
	 * 
	 * @param username
	 * @param password
	 * @param urlAddr
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String sendWithType(String type, String username, String password, String urlAddr, Map<String,String> map) throws Exception{
		HttpURLConnection conn = null;
		BufferedReader br = null;
		OutputStreamWriter out = null;
		StringBuffer params = new StringBuffer();
		StringBuffer result = new StringBuffer();
		// 参数拼接
		if (MapUtils.isNotEmpty(map)) {
			for (Entry<String, String> entry : map.entrySet()) {
				if (entry.getValue() != null) {
					params.append(entry.getKey());
					params.append("=");
					params.append(entry.getValue());
					params.append("&");
				}
			}
			if (params.length() > 0) {
				params.deleteCharAt(params.length() - 1);
			}
		}

		try {
			URL url = new URL(urlAddr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(type);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(params.length()));
			conn.setRequestProperty("Authorization",genAuthorization(username, password));
			conn.setDoInput(true);
			conn.connect();
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(params.toString());
			out.flush();
			out.close();			
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String r = br.readLine() ;
			while(r  != null){
				result.append(r);
				r = br.readLine();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (br != null) {
				br.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return result.toString();
	}

	/**
	 * 
	 * 添加response header
	 * 
	 * @param Invocation 调用信息类
	 * @param headerName header名
	 * @param headerName header值
	 * @return
	 */
	public static void addResponseHeader(Invocation inv, String headerName, String headerValue){
		inv.getResponse().addHeader(headerName, headerValue);
	}

	/**
	 * 
	 * 添加response header
	 * 
	 * @param Invocation 调用信息类
	 * @param headerName header名
	 * @param headerName header值
	 * @return
	 */
	public static void addResponseIntHeader(Invocation inv, String headerName, int headerValue){
		inv.getResponse().addIntHeader(headerName, headerValue);
	}

	/**
	 * 生成基本认证串
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static String genAuthorization(String username, String password){
		String authorization = "";
		try {
			byte[] token = (username + ":" + password).getBytes("utf-8");
			BASE64Encoder encoder = new BASE64Encoder();
			authorization = "Basic " + new String(encoder.encode(token));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return authorization;
	}

	public static void main(String[] args){
		String authorization = HttpTookit.genAuthorization("admin", "e10adc3949ba59abbe56e057f20f883e");
		System.out.println(authorization);// Basic
											// YWRtaW46ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U=
	}

	/**
	 * 发送Post请求
	 * @param urlAddr
	 * @param xmlstr
	 * @return
	 */
	public static String sendPost(String urlAddr, String xmlstr) {
		HttpURLConnection conn = null;
		BufferedReader br = null;
		OutputStreamWriter out = null;
		StringBuffer result = new StringBuffer();
		try {
			URL url = new URL(urlAddr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "text/xml");
			conn.setRequestProperty("Content-Length", String.valueOf(xmlstr.length()));
			conn.setDoInput(true);
			conn.connect();
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(xmlstr);
			out.flush();
			out.close();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String r = br.readLine();
			while (r != null) {
				result.append(r);
				r = br.readLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();
	}
}
