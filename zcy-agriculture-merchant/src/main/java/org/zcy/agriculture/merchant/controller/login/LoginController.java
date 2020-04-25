package org.zcy.agriculture.merchant.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.Constants;
import org.zcy.agriculture.constants.ErrorCodeEnum;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.TbMerchant;
import org.zcy.agriculture.exception.AuthException;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.ServletUtils;
import org.zcy.agriculture.param.registerlogin.LoginParam;
import org.zcy.agriculture.param.registerlogin.PasswordRetrieveParam;
import org.zcy.agriculture.param.registerlogin.RegisterParam;
import org.zcy.agriculture.redis.JedisClient;
import org.zcy.agriculture.service.ICurrencyService;
import org.zcy.agriculture.service.ITbMerchantService;
import org.zcy.agriculture.service.ITbUserService;
import org.zcy.agriculture.util.*;
import org.zcy.agriculture.vo.registerlogin.LoginInfoVo;
import org.zcy.agriculture.vo.registerlogin.LoginUserVo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登录和注册 信息操作处理
 *
 * @author numberone
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/api")
public class LoginController extends BaseController {

    @Autowired
    private ICurrencyService currencyService;

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private ITbUserService tbUserService;

    @Autowired
    private ITbMerchantService tbMerchantService;


    @Value("${login.info.timeout}")
    private int userTimeout ;

    @Value("${login.auto.timeout}")
    private int autoLoginTimeout ;

    /**
     * 注册
     * @param registerParam
     * @param request
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public AjaxResult register(@RequestBody RegisterParam registerParam, HttpServletRequest request) {
        AjaxResult validation = validationWithSession(registerParam, request);
        if(AjaxResult.getResultStatus(validation) != RequestStatus.SUCCESS.getStatus())
            return validation;
        //注册用户编码
        registerParam.setMerchantCode(UUIDUtils.getCode());
        //默认密码加密
        registerParam.setPassword(Md5Utils.hash(Constants.DEFAULT_PASSWORD));
        //系统默认头像
        registerParam.setHeadUrl(Constants.DEFAULT_HEADIMG_PATH);
        //默认超管
        registerParam.setRoleId(1L);
        return toAjax(tbMerchantService.insertTbMerchant(registerParam));
    }

    /**
     * 登录，前端对密码已经加密
     * @param loginParam
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(@RequestBody LoginParam loginParam, HttpServletRequest request, HttpServletResponse response) {

        AjaxResult validation = validationWithSession(loginParam, request);
        if(AjaxResult.getResultStatus(validation) != RequestStatus.SUCCESS.getStatus())
            return validation;

        LoginInfoVo infoVo = AjaxResult.getResultRows(validation, LoginInfoVo.class);

        //不管存在于成员表或者商户表中
        LoginUserVo userVo  = infoVo.getLoginUserVoList().get(0);
        //key
        String loginUserKey = RedisKeyUtils.getLoginUserKey(userVo.getPhone());
        //把用户信息key放在cookie中
        CookieUtils.addCookie(response, Constants.LOGIN_USER_KEY, loginUserKey, userTimeout);
        //商户信息缓存到redis
        jedisClient.setEntityExpire(loginUserKey, userVo, userTimeout);

        //自动登录
        Boolean autoLogin = loginParam.getAutoLogin();
        if(!ValidationUtil.isEmpty(autoLogin)) {
            if(autoLogin) {

                CookieUtils.addCookie(response, Constants.LOGIN_USERNAME, loginParam.getAccount(), autoLoginTimeout);
                CookieUtils.addCookie(response, Constants.LOGIN_PASSWORD, loginParam.getPassword(), autoLoginTimeout);
            }else {
                //清除cookie
                CookieUtils.addCookie(response, Constants.LOGIN_USERNAME, null, 0);
                CookieUtils.addCookie(response, Constants.LOGIN_PASSWORD, null, 0);
            }
        }

        return success(userVo);
    }

    /**
     * 跳转到登录界面之前，调用该接口判断cookie
     * @return
     */
    @GetMapping("/cookie")
    @ResponseBody
    public AjaxResult parseCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie usernameCookie = CookieUtils.getCookieByName(request, Constants.LOGIN_USERNAME);
        Cookie pwdCookie = CookieUtils.getCookieByName(request, Constants.LOGIN_PASSWORD);
        LoginInfoVo loginInfoVo ;

        if(!ValidationUtil.isEmpty(usernameCookie) && !ValidationUtil.isEmpty(pwdCookie)
                    && !ValidationUtil.isEmpty(usernameCookie.getValue()) && !ValidationUtil.isEmpty(pwdCookie.getValue())) {
            String username = usernameCookie.getValue();
            String password = pwdCookie.getValue();

            loginInfoVo = getLoginUserList(username, password);
            if(ValidationUtil.isEmpty(loginInfoVo)) {
                //清除cookie
                CookieUtils.addCookie(response, Constants.LOGIN_USERNAME, null, 0);
                CookieUtils.addCookie(response, Constants.LOGIN_PASSWORD, null, 0);
                return error("cookie的用户名或者密码" + ValidationConstants.SUFFIX_NOT_RIGHT);
            }
        }else {
            //清除cookie
            CookieUtils.addCookie(response, Constants.LOGIN_USERNAME, null, 0);
            CookieUtils.addCookie(response, Constants.LOGIN_PASSWORD, null, 0);
            return error("cookie" + ValidationConstants.SUFFIX_NOT_EXIST_PARAMS);
        }

        //判断登录的是商户还是农场用户
        List<LoginUserVo> userVoList = loginInfoVo.getLoginUserVoList();
        if(!ValidationUtil.isEmpty(userVoList) && userVoList.size() == 1)
            return success(userVoList.get(0));

        return error();
    }


    /**
     * 密码找回-确认账号
     * @return
     */
    @PostMapping("/password/retrieve/conform")
    @ResponseBody
    public AjaxResult conform(@RequestBody PasswordRetrieveParam param, HttpServletRequest request) {
        //电话
        if(ValidationUtil.isEmpty(param.getPhone()))
            return error("电话"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        else {
            LoginInfoVo loginInfoVo = getLoginUserList(param.getPhone(), null);
            if(ValidationUtil.isEmpty(loginInfoVo))
                return error("该账号"+ ValidationConstants.SUFFIX_NOT_REGISTER);
        }
        //验证码校验
        AjaxResult codeValidate = verificationCodeValidate(param.getVerificationCode(), request);
        if(AjaxResult.getResultStatus(codeValidate) != RequestStatus.SUCCESS.getStatus())
            return codeValidate;

        return success();
    }


    /**
     * 密码找回-重置密码
     * @return
     */
    @PostMapping("/password/retrieve/reset")
    @ResponseBody
    public AjaxResult resetPassword(@RequestBody PasswordRetrieveParam param, HttpServletRequest request) {
        int result = -1;
        AjaxResult validation = validationWithSession(param, request);
        if(AjaxResult.getResultStatus(validation) != RequestStatus.SUCCESS.getStatus())
            return validation;

        try {
            LoginInfoVo infoVo = AjaxResult.getResultRows(validation, LoginInfoVo.class);

            if(Constants.MERCHANT.equals(infoVo.getType())) {
                result = tbMerchantService.updateMerchantPasswordByParam(param);
            }else if(Constants.USER.equals(infoVo.getType())) {
                result = tbUserService.updateUserPasswordByParam(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toAjax(result);
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @GetMapping("/logout")
    @ResponseBody
    public AjaxResult logout(HttpServletRequest request) {
        Cookie keyCookie = CookieUtils.getCookieByName(request, Constants.LOGIN_USER_KEY);
        if(!ValidationUtil.isEmpty(keyCookie)) {
            String loginKey = keyCookie.getValue();
            jedisClient.del(loginKey);
            return success();
        }
        logger.error("用户登录失效");
        //清除当前农场id的cookie
        CookieUtils.addCookie(ServletUtils.getResponse(), Constants.FARM_ID, null, 0);
        return error(-101,"登录状态信息失效，请重新登录");
    }

    /**
     * 校验
     * @param object
     * @param request
     * @return
     */
    protected AjaxResult validationWithSession(Object object, HttpServletRequest request) {
        if(object instanceof RegisterParam) {
            //注册校验

            RegisterParam param = (RegisterParam)object;
            if(ValidationUtil.isEmpty(param.getPhone()))
                return error("手机号"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            if(ValidationUtil.isEmpty(param.getNickName()))
                return error("姓名"+ ValidationConstants.SUFFIX_NOT_EMPTY);

            //验证码校验
            AjaxResult codeValidate = verificationCodeValidate(param.getVerificationCode(), request);
            if(AjaxResult.getResultStatus(codeValidate) != RequestStatus.SUCCESS.getStatus())
                return codeValidate;

            //手机号是否存在
            List<TbMerchant> merchantList = getRegisterMerchantList(param.getPhone());
            if(!ValidationUtil.isEmpty(merchantList))
                return error("手机号"+ ValidationConstants.SUFFIX_HAS_EXIST);

        }else if(object instanceof PasswordRetrieveParam) {
            //密码找回（只针对第三步校验）

            PasswordRetrieveParam param = (PasswordRetrieveParam)object;
            if(ValidationUtil.isEmpty(param.getPhone()))
                return error("手机号" + ValidationConstants.SUFFIX_NOT_EMPTY);

            //短信验证码
            AjaxResult msgVerificationCode = validateMsgVerificationCode(param.getPhone(), param.getVerificationType(), param.getMsgVerificationCode());
            if(AjaxResult.getResultStatus(msgVerificationCode) != RequestStatus.SUCCESS.getStatus())
                return msgVerificationCode;

            //新老密码
            if(ValidationUtil.isEmpty(param.getNewPassword()))
                return error("新密码"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            if(ValidationUtil.isEmpty(param.getRepeatPassword()))
                return error("重复密码"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            else {
                if(!param.getNewPassword().equals(param.getRepeatPassword()))
                    return error("两次密码"+ ValidationConstants.SUFFIX_NOT_MATCH);
            }

            //判断电话号码属于tb_user还是tb_merchant表
            LoginInfoVo infoVo = getLoginUserList(param.getPhone(), null);
            if(ValidationUtil.isEmpty(infoVo))
                return error("手机号"+ ValidationConstants.SUFFIX_NOT_MATCH);

            return success(infoVo);

        }else if(object instanceof LoginParam) {
            //登录校验

            LoginParam param = (LoginParam)object;
            if(ValidationUtil.isEmpty(param.getAccount()))
                return error("用户名/手机号"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            if(ValidationUtil.isEmpty(param.getPassword()))
                return error("密码"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            //图片验证码
            String verifyCode = param.getVerificationCode();
            if(ValidationUtil.isEmpty(verifyCode))
                return error("验证码"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            else {
                //验证码校验
                AjaxResult codeValidate = verificationCodeValidate(verifyCode, request);
                if(AjaxResult.getResultStatus(codeValidate) != RequestStatus.SUCCESS.getStatus())
                    return codeValidate;
            }

            //判断用户名和密码是否正确
            LoginInfoVo loginInfoVo = getLoginUserList(param.getAccount(), param.getPassword());
            if(ValidationUtil.isEmpty(loginInfoVo))
                return error("用户名或者密码" + ValidationConstants.SUFFIX_NOT_RIGHT);

            return success(loginInfoVo);
        }

        return success();
    }

    /**
     * 校验字符验证码
     * @param code
     * @return
     */
    private AjaxResult verificationCodeValidate(String code, HttpServletRequest request) {
        if(ValidationUtil.isEmpty(code))
            return error("验证码"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        else {
            //验证图片验证码
            Object attribute = request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            if(!ValidationUtil.isEmpty(attribute)) {
                if(attribute instanceof String) {
                    String verificationCode = (String)attribute;
                    if(!verificationCode.equalsIgnoreCase(code)) {
                        return error("验证码"+ ValidationConstants.SUFFIX_NOT_RIGHT);
                    }
                }
            }else {
                return error("验证码"+ ValidationConstants.SUFFIX_HAS_EXPIRED);
            }
        }
        return success();
    }



}
