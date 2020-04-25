package org.zcy.agriculture.merchant.aspect;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zcy.agriculture.entity.TbPayLog;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.ServletUtils;
import org.zcy.agriculture.redis.JedisClient;
import org.zcy.agriculture.service.TbPayLogService;
import org.zcy.agriculture.util.IpUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.registerlogin.LoginUserVo;

import com.alibaba.fastjson.JSONObject;

/**
 * 接口参数签名类 统一签名
 */
@Component
@Aspect
public class LogAspect {

	public static Logger LOGGER = LogManager.getLogger(LogAspect.class);
	/**
	 * 开始时间
	 */
	private long startTime = 0L;
	/**
	 * 结束时间
	 */
	private long endTime = 0L;
	@Autowired
	private TbPayLogService payLogService;
	@Autowired
	private JedisClient jedisClient;

	@Pointcut("execution(* org.zcy.agriculture.merchant.controller.*.*(..))")
	public void before() {
	}

	@Pointcut("execution(* org.zcy.agriculture.merchant.controller.*.*(..))")
	public void around() {
	}

	@Before("before()")
	public void doBeforeInServiceLayer(JoinPoint joinPoint) {
		LOGGER.debug("doBeforeInServiceLayer");
		startTime = System.currentTimeMillis();
	}

	@Around("around()")
	public Object recordSysLog(ProceedingJoinPoint joinPoint) throws Throwable {
		// 请求的方法名
		String strMethodName = joinPoint.getSignature().getName();
		// 请求的类名
		String strClassName = joinPoint.getTarget().getClass().getSimpleName();
		HttpServletRequest request = ServletUtils.getRequest();
		StringBuffer bfParams = new StringBuffer();
		String param = "";
		try {
			Object[] obj = joinPoint.getArgs();
			if (obj != null && obj.length > 0) {
				for (Object object : obj) {
					if (ValidationUtil.isJson(object)) {
						param += JSONObject.toJSONString(object);
					}
				}
			}
		} catch (Exception e) {
		}
		Enumeration<String> paraNames = request.getParameterNames();
		String key;
		String value;
		while (paraNames.hasMoreElements()) {
			// 遍历请求参数
			key = paraNames.nextElement();
			value = request.getParameter(key);
			bfParams.append(key).append("=").append(value).append("&");
		}
		if (StringUtils.isEmpty(bfParams)) {
			// 如果请求参数为空,返回url路径后面的查询字符串
			bfParams.append(request.getQueryString());
		} else {
			bfParams.deleteCharAt(bfParams.length() - 1);
		}
		bfParams.append(" ，json参数：" + param);

		Object result = joinPoint.proceed();
		String strMessage = String.format("[类名]:%s,[方法]:%s,[参数]:%s", strClassName, strMethodName, bfParams);
		LOGGER.info(strMessage);
		// 环绕通知 ProceedingJoinPoint执行proceed方法的作用是让目标方法执行，这也是环绕通知和前置、后置通知方法的一个最大区别。
		endTime = System.currentTimeMillis();

		LoginUserVo vo = null;
		String userKey = BaseController.getLoginUserKey();
		if (!ValidationUtil.isEmpty(userKey) && jedisClient.exists(userKey)) {
			vo = jedisClient.getEntity(userKey, LoginUserVo.class);
		}

		TbPayLog sysLog = new TbPayLog();
		sysLog.setCreateTime(new Date());
		sysLog.setFarmId(BaseController.getFarmUUID());
		sysLog.setUserId("merchant:" + (vo != null ? vo.getCode() : "1"));
		sysLog.setOptContent(strMessage);
		sysLog.setUserIp(IpUtils.getIPAddr(request));
		sysLog.setUrl(request.getRequestURI());
		sysLog.setMethod(request.getMethod());
		sysLog.setUserAgent(request.getHeader("User-Agent"));
		sysLog.setSpendTime((int) (endTime - startTime));
		if (result != null) {
			sysLog.setOptResult(JSONObject.toJSON(result).toString());
		}
		payLogService.insertTbPayLog(sysLog);
		return result;
	}
}