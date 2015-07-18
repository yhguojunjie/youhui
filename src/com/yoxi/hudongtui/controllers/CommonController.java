package com.yoxi.hudongtui.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.utils.common.FastDFSUtils;
import com.yoxi.hudongtui.utils.common.QCodeUtil;
import com.yoxi.hudongtui.utils.common.ReadProperties;

/**
 * 通用工具相关controller
 * 
 * @author wql
 * 
 * 
 * 2014-11-24
 *
 */
public class CommonController {
	
	/**
	 * 上传到文件服务器 ,返回的路径如：group1/M00/00/00/eSktdlRsC0SAYEReAAB--LrPTbo905.jpg
	 * @param inv
	 * @param Filedata
	 * @return
	 */
	@Post("toServer")
	public String toServer(Invocation inv,@Param("Filedata") MultipartFile file, @Param("uploadType") String uploadType){
		 String pictureUrl = null;
		//小于3M的文件才能上传
		 if(file.getSize()/1024 < 1024*3){
			//检查扩展名
			String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
			 if(uploadType.equals("mp3") && !Globals.EXT_MEDIA.contains(fileExt)){
				 return "@json:" + "{\"message\":\""+"上传文件扩展名是不允许的扩展名。只允许"+Globals.EXT_MEDIA+"格式。"+"\",\"error\":1}";
			 }else if(uploadType.equals("image") && !Globals.EXT_IMAGE.contains(fileExt)){
				 return "@json:" + "{\"message\":\""+"上传文件扩展名是不允许的扩展名。只允许"+Globals.EXT_IMAGE+"格式。"+"\",\"error\":1}";
			 }
			 pictureUrl = FastDFSUtils.uploadFile(file);
		 }else{
			 return "@json:" + "{\"message\":\""+"上传文件大小超过限制"+"\",\"error\":1}";
		 }
		 return "@json:" + "{\"url\":\""+pictureUrl+"\",\"filename\":\""+file.getOriginalFilename()+"\",\"error\":0}";
	}
	
	/**
	 * 上传到项目某个目录 下
	 * @param inv
	 * @param file
	 * @return
	 */
	@Post("toLocal")
	public String toLocal(Invocation inv,@Param("Filedata") MultipartFile file){
		
		String ctxDir = inv.getRequest().getSession().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
			ctxDir = ctxDir + File.separatorChar;
		}
		
		String realyDirPath = ReadProperties.getPara("upload.file.dir");
		
		File destFile = new File(ctxDir+ realyDirPath);
		if (!destFile.exists()) {
			destFile.mkdir();
		}
		destFile = new File(ctxDir + realyDirPath +File.separatorChar+ file.getOriginalFilename());
		try {
			file.transferTo(destFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(new Runnable(){
			@Override
			public void run() {
			}
			
		}).start();
		
		return "@json:" + "{\"url\":\""+file.getOriginalFilename()+"\",\"error\":0}";
		//return "@json:" + "{\"fileUrl\":"+file.getOriginalFilename()+"}";
	}
	
	
	/**
	 * 获取二维码，返回输入流
	 * @param inv
	 * @param url
	 * @throws Exception
	 */
	@Get("getQcode")
	public void getQcode(Invocation inv, @Param("url")String url)throws Exception{
		BufferedImage bufImg = QCodeUtil.qRCodeCommon(url, "JPEG", 8);
		ImageIO.write(bufImg, "JPEG", inv.getResponse().getOutputStream());
	}
	
	/**
	 * http路径的图片转图片流
	 * @param inv
	 * @param url
	 */
	@Get("getImgStream")
	public void getImgStream(Invocation inv, @Param("url")String urlstr) throws Exception{
		
		String GIF = "image/gif";
	    String JPG = "image/jpeg";
	    String PNG = "image/png";
	    
		URL url = new URL(urlstr);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        int extnum = urlstr.lastIndexOf('.');
        String ext = urlstr.substring(extnum+1,urlstr.length());
        BufferedImage bufImg = ImageIO.read(connection.getInputStream());
        if(ext.equalsIgnoreCase("gif")){
        	inv.getResponse().setContentType(GIF);
        }else if(ext.equalsIgnoreCase("png")){
        	inv.getResponse().setContentType(PNG);
        }else{
        	inv.getResponse().setContentType(JPG);
        }
		ImageIO.write(bufImg, ext, inv.getResponse().getOutputStream());
	}

	/**
	 * 验证码检查（使用中的）
	 * @param inv
	 * @param randomCode
	 * @return
	 * @throws Exception
	 */
	@Post("checkIdentifyCodeU")
	public String checkIdentifyCodeU(Invocation inv,ServletContext servletContext,HttpSession session,
			@NotBlank @Param("randomCode") String randomCode)throws Exception{
		Boolean state;
		if (!(randomCode.equalsIgnoreCase(session
				.getAttribute("RANDOMVALIDATECODEKEY").toString()))) { // 忽略验证码大小写
			state=false;
		} else {
			state=true;
		}
		return "@json:" + "{\"state\":"+state+"}";
	}
	
	
}
