package org.zcy.agriculture.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;

public class HttpHelper {
	public static String getBodyString(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = request.getInputStream();

			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	public static String submitPost(String url, String params) throws Exception {
		return submitPost(url, params, "utf-8");
	}

	public static String submitPost(String url, String params, String charset) throws Exception {
		return submitPost(url, params, charset, "");
	}

	public static String submitPost(String url, String params, String charset, String contentType) throws Exception {
		StringBuffer responseMessage = null;
		java.net.HttpURLConnection connection = null;
		java.net.URL reqUrl = null;
		OutputStreamWriter reqOut = null;
		InputStream in = null;
		BufferedReader br = null;
		int charCount = -1;
		try {
			responseMessage = new StringBuffer(128);
			reqUrl = new java.net.URL(url);

			connection = (java.net.HttpURLConnection) reqUrl.openConnection();
			connection.setReadTimeout(50000);
			connection.setConnectTimeout(10000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			if ("xml".equals(contentType)) {
				connection.setRequestProperty("Content-Type", "application/xml; charset=" + charset);
			} else if ("json".equals(contentType)) {
				connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);
			} else {
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			}
			reqOut = new OutputStreamWriter(connection.getOutputStream(), charset);
			reqOut.write(params);
			reqOut.flush();

			in = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(in, charset));
			while ((charCount = br.read()) != -1) {
				responseMessage.append((char) charCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (reqOut != null) {
					reqOut.close();
				}
			} catch (Exception e) {

			}
		}

		return responseMessage.toString();

	}

	public static String submitGet(String url) throws Exception {
		return submitGet(url, "utf-8");
	}

	public static String submitGet(String url, String charset) throws Exception {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);

			URLConnection connection = realUrl.openConnection();

			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();

			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static Map<String, String> getMapParamter(String paramter) {
		Map<String, String> map = new HashMap<String, String>();
		String[] splitparam = paramter.split("\\&");
		for (int i = 0; i < splitparam.length; i++) {
			String[] splitpm = splitparam[i].split("=");
			if (splitpm.length < 2) {
				map.put(splitpm[0], "");
			} else {
				map.put(splitpm[0], splitpm[1]);
			}
		}
		return map;
	}

	// 获取http请求参数
	public static Map<String, String> getReqParamter(HttpServletRequest request) {
		// 保存参数值
		Map<String, String> paramMap = new HashMap<String, String>();
		if (request != null) {
			Enumeration<?> en = request.getParameterNames();
			while (en.hasMoreElements()) {
				String paramName = (String) en.nextElement();
				paramMap.put(paramName, request.getParameter(paramName));
			}
		}
		return paramMap;
	}

	/**
	 * 获取天气预报信息
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String getWeather(String url) throws UnsupportedEncodingException {

		URL realUrl = null;
		ByteArrayOutputStream out = null;

		try {
			// 真实地址
			realUrl = new URL(url);

			// 打开连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

			// 设置连接属性
			connection.setRequestProperty("accept", "application/xhtml+xml,application/json,application/xml;charset=utf-8, text/javascript, */*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("contentType", "utf-8");
			connection.setRequestMethod("GET");
			connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");

			// FileOutputStream out = new FileOutputStream("e:/text.txt");
			// 这里获取的数据时压缩格式的数据所以用gzip进行解压缩
			GZIPInputStream gip = new GZIPInputStream(connection.getInputStream());
			out = new ByteArrayOutputStream();
			// 缓冲
			byte[] buffer = new byte[1024];
			int len;
			while ((len = gip.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			// 关闭流
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// 把字节数据转化为字符串返回回去
		return (new String(out.toByteArray(), "utf-8"));
	}
}
