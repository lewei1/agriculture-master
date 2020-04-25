package org.zcy.agriculture.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * 对文件进行压缩和加密 对文件进行解压和解密
 * 
 * @author fenghao
 * 
 */
public class ZipUtils {
	public static Logger logger = LogManager.getLogger(ZipUtils.class);

	/**
	 * 解压加密的压缩文件
	 * 
	 * @param zipfile
	 * @param dest
	 * @param passwd
	 * @throws ZipException
	 */
	public void unZip(File zipfile, String dest, String passwd) throws ZipException {
		ZipFile zfile = new ZipFile(zipfile);
		if (!zfile.isValidZipFile()) {
			throw new ZipException("压缩文件不合法，可能已经损坏！");
		}
		File file = new File(dest);
		if (file.isDirectory() && !file.exists()) {
			file.mkdirs();
		}
		if (zfile.isEncrypted()) {
			zfile.setPassword(passwd.toCharArray());
		}
		zfile.extractAll(dest);
	}

	/**
	 * 压缩文件且加密
	 * 
	 * @param src
	 * @param dest
	 * @param is
	 * @param passwd
	 */
	public static int zip(String src, String dest, boolean is, String passwd) {
		File srcfile = new File(src);
		// 创建目标文件
		ZipParameters par = new ZipParameters();
		par.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		par.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		if (passwd != null) {
			par.setEncryptFiles(true);
			par.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
			par.setPassword(passwd.toCharArray());
		}
		try {
			ZipFile zipfile = new ZipFile(dest);
			if (srcfile.isDirectory()) {
				if (!is) {
					File[] listFiles = srcfile.listFiles();
					ArrayList<File> temp = new ArrayList<File>();
					Collections.addAll(temp, listFiles);
					zipfile.addFiles(temp, par);
				} else {
					zipfile.addFolder(srcfile, par);
				}
			} else {
				zipfile.addFile(srcfile, par);
			}
			return 1;
		} catch (Exception e) {
			logger.error("创建码包csv 并压缩文件且加密异常！", e);
		}
		return 0;
	}

	public static void main(String[] args) throws FileNotFoundException {

//		String src = "D:\\logs\\ss";
//		String dest = "D:\\logs\\log.zip";
//		zip(src, dest, true, "123456");

	}
}
