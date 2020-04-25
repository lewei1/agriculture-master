package org.zcy.agriculture.merchant.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.zcy.agriculture.constants.Constants;
import org.zcy.agriculture.constants.ErrorCodeEnum;
import org.zcy.agriculture.entity.TbResPower;
import org.zcy.agriculture.exception.AuthException;
import org.zcy.agriculture.page.ServletUtils;
import org.zcy.agriculture.redis.JedisClient;
import org.zcy.agriculture.service.ITbDicRolePowerService;
import org.zcy.agriculture.service.ITbResPowerService;
import org.zcy.agriculture.util.CookieUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.registerlogin.LoginUserVo;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author hezt
 * @ClassName: AuthInterceptor
 * @Description: 权限拦截器
 * @date 2019年7月16日
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private ITbResPowerService iTbResPowerService;

    /**
     * 允许的url列表
     */
    private List<String> allowedUrlList = new ArrayList<>();

    /**
     * 公共权限
     */
    private List<String> commonPowerList = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            logger.info("读取url过滤配置文件");
            Resource resource = resourceLoader.getResource("classpath:urlFilter.properties");
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            String url = properties.getProperty("url");
            String[] splitUrl = url.split(",");
            allowedUrlList.addAll(Arrays.asList(splitUrl));
        } catch (Exception e) {
            logger.error("读取url过滤配置文件失败");
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestPath = getRequestPath(request);
        if(!requestPath.startsWith("/")) {
            requestPath = "/" + requestPath;
        }
        // 拦截器允许的放行
        if (allowedUrlList.contains(requestPath)) {
            return true;
        }
        Cookie cookie = CookieUtils.getCookieByName(ServletUtils.getRequest(), Constants.LOGIN_USER_KEY);
        LoginUserVo loginUserVo = null;
        if(!ValidationUtil.isEmpty(cookie) && !ValidationUtil.isEmpty(cookie.getValue())) {
            loginUserVo = jedisClient.getEntity(cookie.getValue(), LoginUserVo.class);
        }
        if(ValidationUtil.isEmpty(loginUserVo)) {
            logger.error("用户未登录：{}", loginUserVo);
            response.sendError(ErrorCodeEnum.NOT_LOGIN.getCode(), ErrorCodeEnum.NOT_LOGIN.getVal());
            return false;
        }
        Long roleId = loginUserVo.getRoleId();
        //判断当前用户是否管理员
        if(!ValidationUtil.isEmpty(roleId) && roleId==1){
            return true;
        }
        //获取公共权限,放过公共的挂在第一,二层菜单下的接口
        if(CollectionUtils.isEmpty(commonPowerList)){
            commonPowerList = iTbResPowerService.getCommonPowerList();
        }

        // 拦截器允许的放行
        if(commonPowerList.contains(requestPath)){
            return true;
        }

        //获取当前用户权限信息
        List<String> powerUri = new ArrayList<>();
        List<TbResPower> powerList = iTbResPowerService.getPowerListByRoleId(roleId);
        if(CollectionUtils.isEmpty(powerList)){
            logger.error("用户权限资源为空：{}", requestPath);
            response.sendError(403, "用户权限资源为空");
            return false;
        }
        powerList.forEach(tbResPower -> {
            powerUri.add(tbResPower.getUri());
        });
        if(!powerUri.contains(requestPath)){
            logger.error("用户无权限访问该资源：{}", requestPath);
            response.sendError(403, "无权限访问该资源");
            return false;
        }
        return true;
    }

    private String getRequestPath(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        //去掉其他参数
        if (requestPath.indexOf("?") > -1) {
            requestPath = requestPath.substring(0, requestPath.indexOf("?"));
        }
        if (requestPath.indexOf("&") > -1) {
            requestPath = requestPath.substring(0, requestPath.indexOf("&"));
        }
        //去掉项目路径
        requestPath = requestPath.substring(request.getContextPath().length() + 1);
        return requestPath;
    }
}
