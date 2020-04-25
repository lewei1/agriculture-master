package org.zcy.agriculture.merchant.aspect;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zcy.agriculture.base.OftenInfo;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.page.AjaxResult;

import com.alibaba.fastjson.JSONObject;

public class HttpServletRequestReplacedFilter implements Filter {
	public static Logger logger = LogManager.getLogger(HttpServletRequestReplacedFilter.class);
	private static ConcurrentHashMap<String, OftenInfo> sessionMap = new ConcurrentHashMap<>();
	private final String removeConcurrency = "/api/tbArticle/warehouseIdList";// 针对某些接口需要 忽略并发，业务需求而取消并发判断（允许请求量很大）

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ServletRequest requestWrapper = null;
		HttpServletRequest httpRequest = null;
		if (request instanceof HttpServletRequest) {
			requestWrapper = new HttpServletRequestWrapper((HttpServletRequest) request);
			httpRequest = (HttpServletRequest) request;
		} // 获取请求中的流如何，将取出来的字符串，再次转换成流，然后把它放入到新request对象中。 //

		if (httpRequest != null) {
			if (removeConcurrency.indexOf(httpRequest.getServletPath()) >= 0 || isChick(httpRequest, (HttpServletResponse) response)) {
				// 在chain.doFiler方法中传递新的request对象
				if (requestWrapper == null) {
					chain.doFilter(request, response);
				} else {
					chain.doFilter(requestWrapper, response);
				}
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	public static boolean isChick(HttpServletRequest requet, HttpServletResponse response) {
		String sessionAndPath = requet.getSession().getId() + requet.getServletPath();
		OftenInfo info = sessionMap.get(sessionAndPath);
		if (info == null) {
			sessionMap.put(sessionAndPath, new OftenInfo());
		} else {
			long currTime = System.currentTimeMillis();
			if (info.getCou() > 10 && (info.getTime() + 3000) >= currTime) {
				response.setContentType("application/json;charset=utf-8");
				// 指定允许其他域名访问
				response.setHeader("Access-Control-Allow-Origin", requet.getHeader("Origin"));
				response.setHeader("Access-Control-Expose-Headers", "X-My-Custom-Header, X-Another-Custom-Header");
				response.setHeader("Access-Control-Allow-Credentials", "true");
				PrintWriter pw = null;
				try {
					pw = response.getWriter();
					pw.write(JSONObject.toJSONString(AjaxResult.error(RequestStatus.DUPLICATE_REQUEST.getStatus(), RequestStatus.DUPLICATE_REQUEST.getMessage())));
					pw.flush();
					return false;
				} catch (IOException e) {
				} finally {
					try {
						if (pw != null) {
							pw.close();
						}

					} catch (Exception e2) {
					}
				}
			} else if ((info.getTime() + 3000) >= currTime) {
				info.setCou(info.getCou() + 1);
			} else {
				info.setCou(0);
				info.setTime(System.currentTimeMillis());
			}
		}
		return true;
	}

}
