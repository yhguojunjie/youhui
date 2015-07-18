package com.yoxi.hudongtui.utils.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import net.paoding.rose.web.Invocation;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 导出工具类
 * 
 * @author wql
 *
 */
public class ExportUtil {

	/**
	 * 
	    * 处理Excel类文件下载  
		* @param       
		* @return
		* @exception
	 */
	public static void ExcelFileExport(Invocation inv,String tempFilePath,String fileName){
		try{
			if(StringUtils.isEmpty(tempFilePath)){
				return;
			}
			File file = new File(tempFilePath);
			HttpServletResponse response = inv.getResponse();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentLength(( int ) file.length()); //  设置下载内容大小 
		
			byte [] buffer  =   new   byte [ 4096 ]; //  缓冲区 
			BufferedOutputStream output  =   null ;
			BufferedInputStream input  =   null ;
			output = new  BufferedOutputStream(response.getOutputStream());
	        input =  new  BufferedInputStream( new  FileInputStream(file));
	        try  {
	            int  n  =  ( - 1 );
	            while  ((n  =  input.read(buffer,  0 ,  4096 ))  >   - 1 ) {
	               output.write(buffer,  0 , n);
	           }
	           response.flushBuffer();
	       }
	        catch  (Exception e) {
	       }  //  用户可能取消了下载 
	        finally {
	            if  (input  !=   null ) input.close();
	            if  (output  !=   null ) output.close();
	            file.delete();
	       }
	        
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
