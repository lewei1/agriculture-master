package org.zcy.agriculture.merchant.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 记录调用Controller的日志
 */
@Component
@Aspect
public class AuthAspect {

//	@Autowired
//	private JedisClient redis;
//
//	@Pointcut("execution(* org.zcy.agriculture.merchant.controller.*.*(..)) && !@annotation(org.zcy.agriculture.merchant.annotation.NoNeedCheck)")
//	public void before() {
//	}
//
//	@Before("before()")
//	public void beforeLogin(JoinPoint joinPoint) throws ServiceException {
//		try {
//			// 接收到请求，记录请求内容
//			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//			// 获取请求的request
//			HttpServletRequest request = attributes.getRequest();
//			Object attribute = request.getSession().getAttribute(Constants.user);
//
//			if (null == attribute) {
//				throw new ServiceException(RequestStatus.NOT_LOGIN.getMessage(), RequestStatus.NOT_LOGIN.getStatus());
//			}
//
//			TbOss user = (TbOss) attribute;
//			//判断是否强制退出
//			if(!ValidationUtil.isEmpty(user) && !ValidationUtil.isEmpty(user.getOssNumber())) {
//				String key = RedisKeyUtils.getOutOssKey(user.getOssNumber());
//				if(!ValidationUtil.isEmpty(redis.get(key))) {
//					throw new ServiceException(RequestStatus.NOT_LOGIN.getMessage(), RequestStatus.NOT_LOGIN.getStatus());
//				}
//			}
//
//
//		} catch (ServiceException e) {
//			throw e;
//		}
//	}
}
