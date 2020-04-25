package org.zcy.agriculture.merchant.controller;

import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.compress.utils.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.zcy.agriculture.constants.Constants;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.constants.VerifyTypeEnum;
import org.zcy.agriculture.entity.TbMerchant;
import org.zcy.agriculture.entity.TbUser;
import org.zcy.agriculture.enums.MerchantStatusEnum;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.PageDomain;
import org.zcy.agriculture.page.ServletUtils;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.page.TableSupport;
import org.zcy.agriculture.redis.JedisClient;
import org.zcy.agriculture.service.ICurrencyService;
import org.zcy.agriculture.service.ITbMerchantService;
import org.zcy.agriculture.service.ITbUserService;
import org.zcy.agriculture.util.BeanUtils;
import org.zcy.agriculture.util.CookieUtils;
import org.zcy.agriculture.util.StringUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.registerlogin.LoginInfoVo;
import org.zcy.agriculture.vo.registerlogin.LoginUserVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * web层通用数据处理
 *
 * @author numberone
 */
@Controller
public class BaseController {

	public static Logger logger = LogManager.getLogger(BaseController.class);

	@Value("${login.info.timeout}")
	private int userTimeout;

	@Value("${login.farm.timeout}")
	private int farmTimeout;

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private ITbUserService tbUserService;

	@Autowired
	private ICurrencyService currencyService;

	@Autowired
	private ITbMerchantService tbMerchantService;
	/**
	 * 设置请求分页数据
	 */
	protected void startPage() {
		PageDomain pageDomain = TableSupport.buildPageRequest();
		Integer pageNum = pageDomain.getPageNum();
		//默认值
		if(ValidationUtil.isEmpty(pageNum)) {
			pageNum = 1;
		}
		Integer pageSize = pageDomain.getPageSize();
		//默认值
		if(ValidationUtil.isEmpty(pageSize)) {
			pageSize = 10;
		}
		String orderBy = pageDomain.getOrderBy();
		PageHelper.startPage(pageNum, pageSize, orderBy);
	}

	/**
	 * 响应请求分页数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected TableDataInfo getDataTable(List<?> list) {
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(list);
		rspData.setTotal(new PageInfo(list).getTotal());
		return rspData;
	}
	protected TableDataInfo getDataTable(Integer code,String msg) {
		TableDataInfo rspData = new TableDataInfo(code,msg);
		return rspData;
	}
	/**
	 * 响应返回结果
	 *
	 * @param rows 影响行数
	 * @return 操作结果
	 */
	protected AjaxResult toAjax(int rows) {
		return rows > 0 ? success() : error();
	}

	/**
	 * 响应返回结果
	 *
	 * @param result 结果
	 * @return 操作结果
	 */
	protected AjaxResult toAjax(boolean result) {
		return result ? success() : error();
	}

	/**
	 * 返回成功
	 */
	public AjaxResult success() {
		return AjaxResult.success();
	}

	/**
	 * 返回失败消息
	 */
	public AjaxResult error() {
		return AjaxResult.error();
	}

	/**
	 * 返回成功消息
	 */
	public AjaxResult success(String message) {
		return AjaxResult.success(message);
	}

	/**
	 * 返回成功对象
	 */
	public AjaxResult success(Object obj) {
		return AjaxResult.success(obj);
	}
	 public AjaxResult success(Object obj,String msg) {
		 return AjaxResult.success(obj,msg);
	 }

	/**
	 * 返回失败消息
	 */
	public AjaxResult error(String message) {
		return AjaxResult.error(message);
	}

	/**
	 * 返回错误码消息
	 */
	public AjaxResult error(int code, String message) {
		return AjaxResult.error(code, message);
	}

	/**
	 * 页面跳转
	 */
	public String redirect(String url) {
		return StringUtils.format("redirect:{}", url);
	}

	/**
	 * 获取农场用户
	 * @return
	 */
	public LoginUserVo getFarmUser() {
		LoginUserVo vo = null;
		String key = getLoginUserKey();
		if(!ValidationUtil.isEmpty(key) && jedisClient.exists(key)) {
			vo = jedisClient.getEntity(key, LoginUserVo.class);
		}
		return vo;
	}

	/**
	 * 从cookie中获取登录用户的key
	 * @return
	 */
	public static String getLoginUserKey() {
		Cookie cookie = CookieUtils.getCookieByName(ServletUtils.getRequest(), Constants.LOGIN_USER_KEY);
		if(!ValidationUtil.isEmpty(cookie) && !ValidationUtil.isEmpty(cookie.getValue())) {
			return cookie.getValue();
		}
		return null;
	}

	/**
	 * 必须在进入农场之后，此时农场id才不为空，
	 * 获取农场UUID
	 * @return
	 */
	public static String getFarmUUID() {
		Cookie farmIdCookie = CookieUtils.getCookieByName(ServletUtils.getRequest(), Constants.FARM_ID);
		if(!ValidationUtil.isEmpty(farmIdCookie) && !ValidationUtil.isEmpty(farmIdCookie.getValue()))
			return farmIdCookie.getValue();
		return null;
	}

	/**
	 * 获取用户code 用户唯一 编码  之前是getFarmUserID,(多张表的话 ID有可能重复，故选择 code)
	 * @return
	 */
	public Long getFarmUserCode() {
		LoginUserVo userVo = getFarmUser();
		if(!ValidationUtil.isEmpty(userVo))
			return userVo.getCode();
		return null;
	}

	/**
	 * 校验方法
	 * @return
	 */
	protected AjaxResult validation(Object object) {
		return success();
	}




	/**
	 * 在私有化部署的前提下，一个手机号只可能存在商户表(tb_merchant)或者用户表(tb_user)其中之一的表
	 * 通过手机号和md5加密后的密码，判断登录用户是否存在（用户表和商户表）
	 * @param phone
	 * @param md5Password
	 * @return
	 */
	protected LoginInfoVo getLoginUserList(String phone, String md5Password) {
		LoginInfoVo infoVo = null;
		List<LoginUserVo> loginUserVoList = Lists.newArrayList();

		//情形一：登录者是商户
		TbMerchant merchant = new TbMerchant();
		merchant.setStatus(MerchantStatusEnum.ACTIVE.getCode());
		merchant.setPhone(phone);
		if(!ValidationUtil.isEmpty(md5Password))
			merchant.setPassword(md5Password);

		List<TbMerchant> merchantList = tbMerchantService.selectTbMerchantList(merchant);
		if(!ValidationUtil.isEmpty(merchantList)) {
			infoVo = new LoginInfoVo();
			for(TbMerchant tbMerchant : merchantList) {
				LoginUserVo vo = new LoginUserVo();

				BeanUtils.copyBeanProp(vo, tbMerchant);
				vo.setId(tbMerchant.getMerchantId());
				vo.setCode(tbMerchant.getMerchantCode());
				loginUserVoList.add(vo);
			}
			infoVo.setType(Constants.MERCHANT);
		}else {

			//情形二：登录者是农场用户（非超级管理员）,那么他必然有连接的农场
			TbUser tbUser = new TbUser();
			tbUser.setStatus(NormalOrDeleteEnum.NORMAL.getCode());
			tbUser.setPhone(phone);
			if(!ValidationUtil.isEmpty(md5Password))
				tbUser.setPassword(md5Password);

			List<TbUser> userList = tbUserService.selectTbUserList(tbUser);
			if(!ValidationUtil.isEmpty(userList)) {
				infoVo = new LoginInfoVo();
				for(TbUser user : userList) {
					LoginUserVo vo = new LoginUserVo();

					BeanUtils.copyBeanProp(vo, user);
					vo.setId(user.getUserId());
					vo.setCode(user.getUserCode());
					loginUserVoList.add(vo);
				}
				infoVo.setType(Constants.USER);
			}
		}

		if(!ValidationUtil.isEmpty(loginUserVoList))
			infoVo.setLoginUserVoList(loginUserVoList);

		return infoVo;
	}

	/**
	 * 通过手机号，获取注册商户列表
	 * @param phone
	 * @return
	 */
	protected List<TbMerchant> getRegisterMerchantList(String phone) {

		TbMerchant merchant = new TbMerchant();
		merchant.setPhone(phone);
		return tbMerchantService.selectTbMerchantList(merchant);
	}



	/**
	 * 校验手机号和短信验证码
	 * @param phone
	 * @param verificationType
	 * @param msgVerificationCode
	 * @return
	 */
	protected AjaxResult validateMsgVerificationCode(String phone, VerifyTypeEnum verificationType, String msgVerificationCode) {
		if(ValidationUtil.isEmpty(verificationType))
			return error("短信验证码类型"+ ValidationConstants.SUFFIX_NOT_EMPTY);
		else {
			//判断验证码类型是否不合法
			if(currencyService.isVerificationCodeTypeIllegal(verificationType))
				return error("短信验证码类型"+ ValidationConstants.SUFFIX_ILLEGAL_PARAMS);
		}

		if(ValidationUtil.isEmpty(msgVerificationCode))
			return error("短信验证码" + ValidationConstants.SUFFIX_NOT_EMPTY);

		Integer rs = currencyService.checkCode(phone, verificationType, msgVerificationCode);
		if(rs < 0)
			return error("短信验证码"+ ValidationConstants.SUFFIX_NOT_MATCH);
		return success();
	}

	/**
	 * 刷新redis缓存的用户信息
	 */
	protected void refreshLoginUser() {

		LoginUserVo userVo = getFarmUser();

		if(ValidationUtil.isEmpty(userVo)) {
			logger.error("用户"+ValidationConstants.SUFFIX_NOT_LOGIN);
			return;
		}

		String key = getLoginUserKey();
		if(ValidationUtil.isEmpty(key)) {
			logger.error("用户key"+ValidationConstants.SUFFIX_NOT_EXIST_PARAMS);
			return;
		}
		//刷新用户缓存
		jedisClient.expire(key, userTimeout);
		//刷新用户key
		CookieUtils.addCookie(ServletUtils.getResponse(), Constants.LOGIN_USER_KEY, key, userTimeout);
		//如果有农场cookie存在，刷新
		Cookie farmIdCookie = CookieUtils.getCookieByName(ServletUtils.getRequest(), Constants.FARM_ID);
		if(!ValidationUtil.isEmpty(farmIdCookie) && !ValidationUtil.isEmpty(farmIdCookie.getValue()))
			CookieUtils.addCookie(ServletUtils.getResponse(), Constants.FARM_ID, farmIdCookie.getValue(), farmTimeout);
	}
}
