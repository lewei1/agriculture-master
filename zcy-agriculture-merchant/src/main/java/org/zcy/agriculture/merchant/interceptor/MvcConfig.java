package org.zcy.agriculture.merchant.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截器
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/captcha/captchaImage",
                        "/api/currency/sendVerificationCode",
                        "/api/cookie",
                        "/api/login",
                        "/api/register",
                        "/api/logout",
                        "/api/system/tbSaleTemplet/getTempletInfo",
                        "/api/home/getWeatherStation",
                        "/api/currency/checkCode",
                        "/api/password/retrieve/conform",
                        "/api/password/retrieve/reset");

        registry.addInterceptor(authInterceptor);
    }


}
