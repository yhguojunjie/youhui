package com.yoxi.hudongtui.utils.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

import com.yoxi.hudongtui.exception.ServiceException;

/**
 *
 * fastdfs 上传工具类
 * 
 * @author wql
 *
 * 2014-11-16
 * 
 */
public class FastDFSUtils {
	
	public static String CONF_FILENAME = FileUtils.getClassPath(FastDFSUtils.class)+File.separatorChar+"fdfs_client.conf";

	/**
	 * 根据文件路径地址上传文件
	 * @param conf_filename(配置文件地址：src\\com\\lectek\\fdfs_client.conf)
	 * @param uploadFileName(上传文件地址：D:\\social\\11.jpg)
	 * @return
	 */
	public static String uploadFile(MultipartFile file){
		
		String uploadFileName = file.getOriginalFilename();
		
		//返回保存文件ID
		String fileId = "";
		//获取文件类型
		String fileExtName = "";
		if (uploadFileName.contains(".")) {  
	        fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
	    } else {
	    	System.out.println("Fail to upload file, because the format of filename is illegal.");  
	        return fileId; 
	    }
		//设置元信息
		NameValuePair []metaList = new  NameValuePair[2];
	    metaList[0] = new NameValuePair("fileName", uploadFileName);  
	    metaList[1] = new NameValuePair("fileExtName", fileExtName);  
	    byte[] imageBytes;
		try
		{
			imageBytes = file.getBytes();
			//建立连接
			ClientGlobal.init(CONF_FILENAME);
			//System.out.println("charset=" + ClientGlobal.g_charset);
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer,storageServer);
			//上传（group1/M00/00/00/wKgC3lDhQu-ARM9qAABIAZ6-5bk963.jpg）
			fileId = client.upload_file1(imageBytes, fileExtName, metaList);
			System.out.println("upload success. file id is: " + fileId);
	        trackerServer.close();
		}catch(Exception io){
			io.printStackTrace();
		}
		return fileId;
	}
	
	
	/**
	 * 根据字节流生成文件
	 * @param b
	 * @param fileExtName
	 * @return
	 */
	public static String uploadFile(byte[] b,String fileExtName)throws ServiceException{
		//返回保存文件ID
		String fileId = "";
		//设置元信息
		NameValuePair []metaList = new  NameValuePair[1];
	    metaList[0] = new NameValuePair("fileExtName", fileExtName);  
		try
		{
			//建立连接
			ClientGlobal.init(CONF_FILENAME);
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer,storageServer);
			fileId = client.upload_file1(b, fileExtName, metaList);
	        trackerServer.close();
		}catch(Exception io){
			io.printStackTrace();
			throw new ServiceException(10004,"fastdfs文件上传异常");
		}
		return fileId;
	}
	
	
	/**
	 * 根据文件路径地址上传文件
	 * @param conf_filename(配置文件地址：src\\com\\lectek\\fdfs_client.conf)
	 * @param uploadFileName(上传文件地址：D:\\social\\11.jpg)
	 * @return
	 */
	public static String uploadFile(String uploadFileName){
		//返回保存文件ID
		String fileId = "";
		//获取文件类型
		String fileExtName = "";
		if (uploadFileName.contains(".")) {  
	        fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
	    } else {
	    	System.out.println("Fail to upload file, because the format of filename is illegal.");  
	        return fileId; 
	    }
		//设置元信息
		NameValuePair []metaList = new  NameValuePair[2];
	    metaList[0] = new NameValuePair("fileName", uploadFileName);  
	    metaList[1] = new NameValuePair("fileExtName", fileExtName);  
		
		try
		{
			//建立连接
			ClientGlobal.init(CONF_FILENAME);
			//System.out.println("charset=" + ClientGlobal.g_charset);
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer,storageServer);
			//上传（group1/M00/00/00/wKgC3lDhQu-ARM9qAABIAZ6-5bk963.jpg）
			fileId = client.upload_file1(uploadFileName, fileExtName, metaList);
			System.out.println("upload success. file id is: " + fileId);
	        trackerServer.close();
		}catch(Exception io){
			io.printStackTrace();
		}
		return fileId;
	}	
	
	public static String uploadFile(InputStream in,String uploadFileName){
		//返回保存文件ID
		String fileId = "";
		String fileExtName = "";
		if (uploadFileName.contains(".")) {  
	        fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
	    } else {
	    	System.out.println("Fail to upload file, because the format of filename is illegal.");  
	        return fileId; 
	    }
		//设置元信息
		NameValuePair []metaList = new  NameValuePair[2];
	    metaList[0] = new NameValuePair("fileName", uploadFileName);  
	    metaList[1] = new NameValuePair("fileExtName", fileExtName);  
		
		try
		{
			//建立连接
			ClientGlobal.init(CONF_FILENAME);
			//System.out.println("charset=" + ClientGlobal.g_charset);
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer,storageServer);
			//上传（group1/M00/00/00/wKgC3lDhQu-ARM9qAABIAZ6-5bk963.jpg）
			fileId = client.upload_file1(FileUtils.InputStreamToByte(in), fileExtName, metaList);
			System.out.println("upload success. file id is: " + fileId);
	        trackerServer.close();
		}catch(Exception io){
			io.printStackTrace();
		}
		return fileId;
	}	
	
	public static void downloadFile(String uploadFileName){
		
	}
	
	public static void deleteFile(String fileId){
		try
		{
			int errno;
			//建立连接
			ClientGlobal.init(CONF_FILENAME);
			//System.out.println("charset=" + ClientGlobal.g_charset);
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer,storageServer);
			if ((errno=client.delete_file1(fileId)) == 0)
  			{
  				System.err.println("Delete file success");
  			}
  			else
  			{
  				System.err.println("Delete file fail, error no: " + errno);
  			}
	        trackerServer.close();
		}catch(Exception io){
			io.printStackTrace();
		}
	}
	
	/**
	 * 获取上传的base64文件流最终生成的图片地址
	 * @param coverPicBase64 base64码
	 * @param fileType 文件类型，1 图片，2音频，3视频
	 * @return
	 * @throws ServiceException 
	 */
	public static String getFastDfsPath(String coverPicBase64,String fileExtName) throws ServiceException{
		String imgPath = null;
		if(StringUtils.isNotBlank(coverPicBase64)){
			String[] strArray = coverPicBase64.split(",");
			if(strArray != null){
				if(strArray[1] != null){
					byte[] b = FastDFSUtils.getBASE64DecoderImg(strArray[1]);
					imgPath = FastDFSUtils.uploadFile(b, fileExtName);
				}
			}
		}
		return imgPath;
	}
	
	
	/**
	 * 解码base64字符串
	 * 
	 * @return
	 */
	public static byte[] getBASE64DecoderImg(String imgStr){
		
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = null;
		//Base64解码
		try {
		   b = decoder.decodeBuffer(imgStr);
	       for(int i=0;i<b.length;++i)
	        {
	            if(b[i]<0)
	            {//调整异常数据
	                b[i]+=256;
	            }
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;

	}
	
	
	public static void main(String[] args)throws Exception {
		String local_file_name ="D:\\social\\rjy.jpg";
//		String fileId = FastDFSUtils.uploadFile(local_file_name);
//		System.out.println("fileId:"+fileId);
		//FastDFSUtils.deleteFile(fileId);
		//InputStream in = new FileInputStream(local_file_name);
		//String fileId1 =FastDFSUtils.uploadFile(in, "11.jpg");
		//System.out.println("fileId1:"+fileId1);
		//FastDFSUtils.deleteFile("group1/M00/00/01/wKgC3lFU8jqAbS2QAABIAZ6-5bk775.jpg");
		File file = new File(local_file_name);
		System.out.println(file.length());
		System.out.println(Long.valueOf(file.length()).intValue());
		System.out.println(file.getName());
		System.out.println(local_file_name.substring(local_file_name.lastIndexOf(".") + 1));
		System.out.println(file.exists());
	}
	
}
