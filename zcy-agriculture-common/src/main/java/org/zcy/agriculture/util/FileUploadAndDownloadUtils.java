package org.zcy.agriculture.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Date: 2019/3/28 10:21
 * @Description:上传和下载通用类
 */
public class FileUploadAndDownloadUtils {

	public static Logger logger = LogManager.getLogger(FileUploadAndDownloadUtils.class);

	/**
	 * @param file     文件
	 * @param realPath 文件存储路径(例如：/usr/java/image/merchant/recharge/)
	 * @param hostPort 文件访问ip:port（例如：172.16.5.112:8089）
	 * @return
	 */
	public static String uploadImage(MultipartFile file, String realPath, String hostPort, String suffix) throws IOException {
		if (file == null || StringUtils.isEmpty(realPath) || StringUtils.isEmpty(hostPort)) {
			return null;
		}
		int random = UUIDUtils.random(8);
		String filePath = System.currentTimeMillis() + (random + suffix);
		File files = new File(realPath + filePath);
		// 判断这个文件（saveFile）是否存在
		if (!files.getParentFile().exists()) {
			// 如果不存在就创建这个文件夹
			files.getParentFile().mkdirs();
		}
		file.transferTo(files);
		String fileFlag = realPath.substring(realPath.lastIndexOf("image") + 5);
		return hostPort + fileFlag + filePath;
	}

	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @param url
	 * @return 1下载成功  -1下载异常
	 */
	public static int downloadFile(HttpServletRequest request, HttpServletResponse response, String url) {
		String name = url.replaceAll("\\\\", "/");
		name = url.substring(url.lastIndexOf("/") + 1);
		// 文件名编码，解决中文乱码问题
		String userAgent = request.getHeader("User-Agent").toLowerCase();
		try {
			if (userAgent.contains("msie") || userAgent.contains("trident") || userAgent.contains("like gecko") || userAgent.contains("edge")) {// IE浏览器
				name = URLEncoder.encode(name, "UTF-8");
				name = name.replaceAll("\\+", "%20");// 处理文件名多余的加号（+）
			} else {// 其它浏览器
				name = new String(name.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("下载中文名称异常！", e);
		}
		// 设置输出的格式
		response.setHeader("P3P", "CP=CAO PSA OUR");
		response.setContentType("application/x-msdownload;charset=UTF-8");
		response.setHeader("Content-Type", "application/octet-stream");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[4000];
		int len;
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(url);// 文件的存放路径
			while ((len = inStream.read(b)) > 0) {
				response.getOutputStream().write(b, 0, len);
			}
			return 1;
		} catch (IOException e) {
			logger.error("下载文件异常！ ", e);
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (Exception e) {
				logger.error("关闭下载流异常！", e);
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		String url = "easdf/asdf\\asdf\\adsr.ee";
		url = url.replaceAll("\\\\", "/");
		System.out.println(url.substring(url.lastIndexOf("/") + 1));
	}
	
	
	 /**
     * 上传文件接口
     * @param pic
     * @param baseFilePath 
     * @param savePath 保存的文件名  如 /base/t.jpg 传空时默认问 /imgsrv/时间戳.文件后缀名
     * @throws IOException 
     * @throws IllegalStateException 
     */
    public static String uploadFile(MultipartFile pic , String baseFilePath , String savePath) throws IllegalStateException, IOException {
    	if(null == savePath || savePath.equals("")) {
    		// 获取图片原始文件名
    		String originalFilename = pic.getOriginalFilename();
    		// 文件名使用当前时间
    		String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    		// 获取上传文件的扩展名(jpg/png/...)
    		String extension = FilenameUtils.getExtension(originalFilename);
    		// 图片上传的相对路径（因为相对路径放到页面上就可以显示图片）
    		savePath = name + "." + extension;
    	}
    	//linux or windows os
        String os = System.getProperty("os.name");
            //判断文件夹是否存在然后新增
    	String url = baseFilePath + savePath;
    	String dirUrl = url.substring(0, url.lastIndexOf("/"));
    	File dir = new File(dirUrl);
		if (!dir.exists()) {
			dir.mkdirs();
			//给予权限
            if(!os.toLowerCase().startsWith("win")){
                Runtime.getRuntime().exec("chmod 755 " + dir.getPath());
            }
		}
		//上传文件
		pic.transferTo(new File(url));
		//给予权限
        if(!os.toLowerCase().startsWith("win")){
            Runtime.getRuntime().exec("chmod 644 " + url);
        }
		return savePath;
    }
    /**
     * 根据服务器所在地址，删除文件
     * @param url
     */
    public static void deletedFile(String url) {
    	if (StringUtils.isNotEmpty(url)) {
    		File f = new File(url);
    		if (f.exists()) {
    			f.delete();
    		}
    	}
    }
}
