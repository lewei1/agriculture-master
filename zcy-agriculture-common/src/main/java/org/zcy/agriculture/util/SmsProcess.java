package org.zcy.agriculture.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SmsProcess {
	
	public static final String host = "http://sms.yllmob.com";
	public static final String appid = "zhongchenyuan";
	public static final String key = "004325d3b03ee829c21c9bc30beeba4f";


	/**
	 * 发送手机验证码
	 * @param phoneNumber
	 * @param verifyCode
	 * @param code
	 * @return
	 */
	public static int sendVerifyCode(String phoneNumber , String verifyCode 
			, String code) {
		JSONArray data = new JSONArray();
		String content = "【中琛魔方】您的验证码为："+verifyCode+"，5分钟内有效，请勿向他人泄露，若非本人操作请忽视";
		data.add(content);
		return send(data , phoneNumber , code);
	}
	
	/**
	 * 发送短信
	 */ 
	private static int send(JSONArray data ,String to , String tempid){
		JSONObject obj = new JSONObject();
		String type="0";
		String url = "";
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(date);
		
		try {
			url = URLEncoder.encode(url,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String sign="";
		try {
			sign = Md5Utils.hash(appid + timestamp + key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		obj.put("appid", appid);
		obj.put("sid", UUIDUtils.getRandomPwd());
		obj.put("type", type);
		obj.put("to", to);
		obj.put("tempid", tempid);
		obj.put("data", data);
		obj.put("timestamp", timestamp);
		obj.put("url", url);
		obj.put("sign", sign);
		
		try {
			String reponse = HttpHelper.submitPost(host+"/verify/send", obj.toString());
			JSONObject reponseJson = JSONObject.parseObject(reponse);
			if(null != reponseJson && reponseJson.get("result").equals("0000")) {
				return 200 ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1 ;
	}
	



}
