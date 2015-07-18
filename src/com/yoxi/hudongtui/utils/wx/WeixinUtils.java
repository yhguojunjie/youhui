package com.yoxi.hudongtui.utils.wx;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoxi.hudongtui.constants.wx.MpConstants;
import com.yoxi.hudongtui.vo.wx.api.AccessToken;
import com.yoxi.hudongtui.vo.wx.api.JsApiTicket;
import com.yoxi.hudongtui.vo.wx.api.menu.Button;
import com.yoxi.hudongtui.vo.wx.api.menu.CommonButton;
import com.yoxi.hudongtui.vo.wx.api.menu.ComplexButton;
import com.yoxi.hudongtui.vo.wx.api.menu.Menu;
import com.yoxi.hudongtui.vo.wx.api.menu.ViewButton;


/**
 * 
 * 微信工具类
 * 
 * @author wangql 
 * 
 * 2013-01-01
 *
 */
public class WeixinUtils{
  
	  private static Logger log = LoggerFactory.getLogger(WeixinUtils.class);
	 
	  private static String accessTokenStr;//微信全局accessToken
	  
	  private static Long accessTokenGetLastTime;//上次获取微信全局accessToken的时间
	
	  
	  /**
	   * 从静态变量里获取accessToken(适用单机情况)，30分钟更新一次
	   * @param appId 公众号appid
	   * @param appsecret 公众号 appsecret
	   * @return accessToken
	   */
	  public static AccessToken getAccessToken(String appId,String appsecret)
	  {
	    AccessToken accessToken = null;
	
	    if ((accessTokenStr == null) && (accessTokenGetLastTime == null)) {
	      accessToken = getTokenFromWx(appId,appsecret);
	      accessTokenStr = accessToken.getAccessToken();
	      accessTokenGetLastTime = Long.valueOf(System.currentTimeMillis());
	    }
	    else {
	      if (System.currentTimeMillis() - accessTokenGetLastTime.longValue() > 1800000L) {
	        accessToken = getTokenFromWx(appId,appsecret);
	        accessTokenStr = accessToken.getAccessToken();
	        accessTokenGetLastTime = Long.valueOf(System.currentTimeMillis());
	        return accessToken;
	      }
	      accessToken = new AccessToken();
	      accessToken.setAccessToken(accessTokenStr);
	      return accessToken;
	    }
	    return accessToken;
	  }

	  /**
	   * 从微信服务器获取accessToken
	   * @param appId 公众号appid
	   * @param appsecret 公众号 appsecret
	   * @return AccessToken accessToken实体
	   */
	  public static AccessToken getTokenFromWx(String appId,String appsecret)
	  {
	    AccessToken accessToken = null;
	    String fullURL = MpConstants.getGetaccesstokeurl() + "?grant_type=client_credential&" + 
	      "&appid=" + appId + "&secret=" + appsecret;
	
	    JSONObject jsonObject = httpsRequest(fullURL, "GET", null);
	
	    if (jsonObject != null) {
	      try {
	        accessToken = new AccessToken();
	        accessToken.setAccessToken(jsonObject.getString("access_token"));
	        accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
	      }
	      catch (JSONException e) {
	        log.error("获取微信token失败 errcode:{} errmsg:{}", Integer.valueOf(jsonObject.getInt("errcode")), jsonObject.getString("errmsg"));
	      }
	    }
	    log.error("从微信服务器获取的accessToken=" + accessToken.getAccessToken() + ",ExpiresIn=" + accessToken.getExpiresIn());
	    return accessToken;
	  }

	  
	  /**
	   * 从微信服务器获取 js api ticket
	   * @param accessToken 微信全局accessToken
	   * @return
	   */
	  public static JsApiTicket getJsApiTicketFromWx(String accessToken){
		
		JsApiTicket jsApiTicket = null;
		String fullURL = MpConstants.getJsapiTicket().replace("ACCESS_TOKEN", accessToken);
	    JSONObject jsonObject = httpsRequest(fullURL, "GET", null);
	    if (jsonObject != null) {
		    try {
		    	jsApiTicket = new JsApiTicket();
		    	jsApiTicket.setErrcode(jsonObject.getString("errcode"));
		    	jsApiTicket.setErrmsg(jsonObject.getString("errmsg"));
		    	jsApiTicket.setTicket(jsonObject.getString("ticket"));
		    	jsApiTicket.setExpires_in(jsonObject.getString("expires_in"));
		    }
		    catch (JSONException e) {
		        log.error("获取微信jsapi ticket失败 errcode:{} errmsg:{}", Integer.valueOf(jsonObject.getInt("errcode")), jsonObject.getString("errmsg"));
		    }
	    }
	    log.error("从微信服务器获取的jsapi ticket=" + jsApiTicket.getTicket() + ",ExpiresIn=" + jsApiTicket.getExpires_in());
	    return jsApiTicket;
	  }
	  
	  
	  
	  /**
	   * 发送https请求工具类
	   * @param requestUrl
	   * @param requestMethod
	   * @param outputStr
	   * @return
	   */
	  public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr){
	    JSONObject jsonObject = null;
	    StringBuffer buffer = new StringBuffer();
	    try
	    {
	      TrustManager[] tm = { new MyX509TrustManager() };
	      SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	      sslContext.init(null, tm, new SecureRandom());
	
	      SSLSocketFactory ssf = sslContext.getSocketFactory();
	
	      URL url = new URL(requestUrl);
	      HttpsURLConnection httpUrlConn = (HttpsURLConnection)url.openConnection();
	      httpUrlConn.setSSLSocketFactory(ssf);
	
	      httpUrlConn.setDoOutput(true);
	      httpUrlConn.setDoInput(true);
	      httpUrlConn.setUseCaches(false);
	
	      httpUrlConn.setRequestMethod(requestMethod);
	
	      if ("GET".equalsIgnoreCase(requestMethod)) {
	        httpUrlConn.connect();
	      }
	
	      if (outputStr != null) {
	        OutputStream outputStream = httpUrlConn.getOutputStream();
	
	        outputStream.write(outputStr.getBytes("UTF-8"));
	        outputStream.close();
	      }
	
	      InputStream inputStream = httpUrlConn.getInputStream();
	      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	
	      String str = null;
	      while ((str = bufferedReader.readLine()) != null) {
	        buffer.append(str);
	      }
	      bufferedReader.close();
	      inputStreamReader.close();
	
	      inputStream.close();
	      inputStream = null;
	      httpUrlConn.disconnect();
	      jsonObject = JSONObject.fromObject(buffer.toString());
	    } catch (ConnectException ce) {
	      log.error("Weixin server connection timed out.");
	    } catch (Exception e) {
	      log.error("https request error:{}", e);
	    }
	    return jsonObject;
	  }

	  /**
	   * 
	   * @param requestUrl
	   * @param requestMethod
	   * @param outputStr
	   * @return
	   */
	  public static InputStream httpsRequestStream(String requestUrl, String requestMethod, String outputStr){
		    
		  try
		    {
		      TrustManager[] tm = { new MyX509TrustManager() };
		      SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		      sslContext.init(null, tm, new SecureRandom());
		
		      SSLSocketFactory ssf = sslContext.getSocketFactory();
		
		      URL url = new URL(requestUrl);
		      HttpsURLConnection httpUrlConn = (HttpsURLConnection)url.openConnection();
		      httpUrlConn.setSSLSocketFactory(ssf);
		
		      httpUrlConn.setDoOutput(true);
		      httpUrlConn.setDoInput(true);
		      httpUrlConn.setUseCaches(false);
		
		      httpUrlConn.setRequestMethod(requestMethod);
		
		      if ("GET".equalsIgnoreCase(requestMethod)) {
		        httpUrlConn.connect();
		      }
		
		      if (outputStr != null) {
		        OutputStream outputStream = httpUrlConn.getOutputStream();
		        outputStream.write(outputStr.getBytes("UTF-8"));
		        outputStream.close();
		      }
		
		      InputStream inputStream = httpUrlConn.getInputStream();
		      
		      return inputStream;
		      
		    } catch (ConnectException ce) {
		    	
		      log.error("Weixin server connection timed out.");
		      
		    } catch (Exception e) {
		      
		    	log.error("https request error:{}", e);
		    }
		  	return null;
		  }
	  
	  /**
	   * 获取微信菜单
	   * @return
	   */
	  public static Menu getMenu(){
		  
		  ViewButton btn11 = new ViewButton();
		    btn11.setName("");
		    btn11.setType("view");
		    btn11.setUrl("");
	
		    CommonButton btn12 = new CommonButton();
		    btn12.setName("");
		    btn12.setType("click");
		    btn12.setKey("");
	
		    ViewButton btn13 = new ViewButton();
		    btn13.setName("");
		    btn13.setType("view");
		    btn13.setUrl("");
	
		    ViewButton btn14 = new ViewButton();
		    btn14.setName("");
		    btn14.setType("view");
		    btn14.setUrl("");
		    ComplexButton mainBtn1 = new ComplexButton();
		    mainBtn1.setName("");
		//    mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14 });
		    List<Button> buttons = new ArrayList<Button>();
		    buttons.add(btn11);
		    buttons.add(btn12);
		    buttons.add(btn13);
		    buttons.add(btn14);
		    mainBtn1.setSub_button(buttons);
	    
		    ViewButton btn21 = new ViewButton();
		    btn21.setName("");
		    btn21.setType("view");
		    btn21.setUrl("");
	
		    ViewButton btn22 = new ViewButton();
		    btn22.setName("");
		    btn22.setType("view");
		    btn22.setUrl("");
	
		    ViewButton btn23 = new ViewButton();
		    btn23.setName("");
		    btn23.setType("view");
		    btn23.setUrl("");
	
		    ViewButton btn24 = new ViewButton();
		    btn24.setName("");
		    btn24.setType("view");
		    btn24.setUrl("");
	
		    ViewButton btn25 = new ViewButton();
		    btn25.setName("");
		    btn25.setType("view");
		    btn25.setUrl("");
	
		    ComplexButton mainBtn2 = new ComplexButton();
		    mainBtn2.setName("");
		//    mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24, btn25 });
		    List<Button> buttons2 = new ArrayList<Button>();
		    buttons2.add(btn21);
		    buttons2.add(btn22);
		    buttons2.add(btn23);
		    buttons2.add(btn24);
		    buttons2.add(btn25);
		    mainBtn2.setSub_button(buttons2);
	
		    ViewButton btn31 = new ViewButton();
		    btn31.setName("");
		    btn31.setType("view");
		    btn31.setUrl("");
	
		    ViewButton btn32 = new ViewButton();
		    btn32.setName("");
		    btn32.setType("view");
		    btn32.setUrl("");
	
		    ViewButton btn33 = new ViewButton();
		    btn33.setName("");
		    btn33.setType("view");
		    btn33.setUrl("");
	
		    ViewButton btn34 = new ViewButton();
		    btn34.setName("");
		    btn34.setType("view");
		    btn34.setUrl("");
	
		    ComplexButton mainBtn3 = new ComplexButton();
		    mainBtn3.setName("");
		//    mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33, btn34,btn35 });
		    List<Button> buttons3 = new ArrayList<Button>();
		    buttons3.add(btn31);
		    buttons3.add(btn32);
		    buttons3.add(btn33);
		    buttons3.add(btn34);
		    mainBtn3.setSub_button(buttons3);
	
	 
	//    ViewButton mainBtn1 = new ViewButton();
	//    mainBtn1.setName("");
	//    mainBtn1.setType("view");
	//    mainBtn1.setUrl("");
	//    
	//    ViewButton mainBtn2 = new ViewButton();
	//    mainBtn2.setName("");
	//    mainBtn2.setType("view");
	//    mainBtn2.setUrl("");
	//    
	//    
	//    ViewButton btn31 = new ViewButton();
	//    btn31.setName("");
	//    btn31.setType("view");
	//    btn31.setUrl("http://www.leread.com");
	//    
	//    ComplexButton mainBtn3 = new ComplexButton();
	//    
	//    mainBtn3.setName("");
	    
	//    List<Button> buttons3 = new ArrayList<Button>();
	//    buttons3.add(btn31);
	//    mainBtn3.setSub_button(buttons3);
	    
	    
	    Menu menu = new Menu();
	    menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
	
	    return menu;
	  }
  
  
	  /**
	   * 处理头像
	   * @param headimgurl
	   * @param type 有0、46、64、96、132数值可选，0代表640*640正方形头像
	   * http://wx.qlogo.cn/mmopen/Q3auHgzwzM6K0dWe1mP0vYuJo8FvBhIsFAbjKxfoV6SkPRUXEUGFJXoWcD4DmamCNrhXmjDQjr34KOlOfOgb9Ybd0ibD7EErqXrko9LyCBS0/0
	   * @return
	   */
	  public static  String processHeadimgurl(String headimgurl,int type){
		  
		  int lastIndex = headimgurl.lastIndexOf("/");
		  
		  String url = headimgurl.substring(0, lastIndex)+"/"+ type;
		  
		  return url;
	  }
  
	  
	/**
	 * 上传多媒体文件到微信服务器
	 * 
	 * @param fileType 文件类型
	 * @param filePath 文件路径
	 * @return JSONObject
	 * @throws Exception
	 */
	public static JSONObject uploadFile2Winxin(String fileType, String filePath,
						AccessToken accessToken)throws Exception {
		String result = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		/**
		 * 第一部分 http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
		 */
		String url = MpConstants.getMediaupload().replace("ACCESS_TOKEN",accessToken.getAccessToken())
					.replace("TYPE", fileType);
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存
		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
				+ BOUNDARY);
		// 请求正文信息
		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
				+ file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);
		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		JSONObject jsonObj = JSONObject.fromObject(result); // new
		return jsonObj;
	}
	
  
  
}