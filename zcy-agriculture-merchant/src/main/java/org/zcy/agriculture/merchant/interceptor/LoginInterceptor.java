package org.zcy.agriculture.merchant.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.zcy.agriculture.constants.Constants;
import org.zcy.agriculture.constants.ErrorCodeEnum;
import org.zcy.agriculture.exception.AuthException;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.ServletUtils;
import org.zcy.agriculture.util.CookieUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.registerlogin.LoginUserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
@Component
@Order(1)
public class LoginInterceptor extends BaseController implements HandlerInterceptor  {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AuthException{
        //判断是否登录
        LoginUserVo user = getFarmUser();
        if(ValidationUtil.isEmpty(user)) {
            logger.error("用户未登录：{}", user);
            //清除当前农场id的cookie
            CookieUtils.addCookie(ServletUtils.getResponse(), Constants.FARM_ID, null, 0);
            throw new AuthException(ErrorCodeEnum.NOT_LOGIN);
        }
        //刷新redis
        refreshLoginUser();

        return true;
    }


}
