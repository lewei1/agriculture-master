package org.zcy.agriculture.merchant.controller;

import com.google.common.collect.Maps;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zcy.agriculture.constants.SmsConstans;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.constants.VerifyTypeEnum;
import org.zcy.agriculture.merchant.annotation.NoNeedCheck;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.redis.JedisClient;
import org.zcy.agriculture.service.ICurrencyService;
import org.zcy.agriculture.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * 通用方法
 *
 * @author hp
 */
@Controller
@RequestMapping("/api/currency")
public class CurrencyController extends BaseController {

    @Autowired
    private JedisClient redis;

    @Autowired
    private ICurrencyService currencyService;

    @Value("${file.img}")
    private String imgPath;

//	@Autowired
//	private ITbOssService tbOssService;

    /**
     * 发送验证码
     *
     * @param phoneNumber
     * @param type
     * @return
     */
    @NoNeedCheck
    @GetMapping("/sendVerificationCode")
    @ResponseBody
    public AjaxResult sendVerificationCode(String phoneNumber, VerifyTypeEnum type) {

        if (ValidationUtil.isEmpty(phoneNumber)) {
            return error("手机号不能为空");
        }
        if (ValidationUtil.isEmpty(type)) {
            return error("验证码类型不能为空");
        }

        //如果是注册应该判断账号是否存在，可以少发一条短信
//    	if(type == VerifyTypeEnum.REGISTER ||type == VerifyTypeEnum.UPDATE_ACCOUNT) {
//    		Integer count = tbOssService.queryCountByPhoneNumber(phoneNumber);
//    		if(null != count && count > 0) {
//    			return error("账户已存在");
//    		}
//    	}

        //如果是找回密码，判断是否存在用户，不存在就报错
//    	if(type == VerifyTypeEnum.UPDATEPWD ||
//    			type == VerifyTypeEnum.FORGETPWD) {
//    		Integer count = tbOssService.queryCountByPhoneNumber(phoneNumber);
//    		if(null == count || count == 0) {
//    			return error("账户不存在");
//    		}
//    	}

        //1分钟内不能重复发送验证码
        String redisKey = RedisKeyUtils.getVerificationCode(phoneNumber, type.getName());

        Long v = redis.ttl(redisKey);
        //判断1分种内不能重复发送
        if (!ValidationUtil.isEmpty(v) && v > (1 * 60)) {
            return error("请不要重复发送验证码");
        }
        // 新生成验证码
        String newVerifyCode = RandomUtils.random(6) + "";
        int res = SmsProcess.sendVerifyCode(phoneNumber, newVerifyCode, SmsConstans.VERIFICATION_CODE);
        if (res != 200) {
            return error("发送验证码失败");
        }
        //验证码5分钟后失效
        redis.setEntityExpire(redisKey, newVerifyCode, 60);
        return success("发送成功");
    }


    /**
     * 校验验证码
     *
     * @param phoneNumber
     * @param type
     * @param verifyCode
     * @return
     */
    @NoNeedCheck
    @GetMapping("/checkCode")
    @ResponseBody
    public AjaxResult checkCode(String phoneNumber, VerifyTypeEnum type, String verifyCode) {

        if (ValidationUtil.isEmpty(phoneNumber)) {
            return error("手机号不能为空");
        }
        if (ValidationUtil.isEmpty(type)) {
            return error("验证码类型不能为空");
        } else {
            if (currencyService.isVerificationCodeTypeIllegal(type)) {
                return error("验证码类型" + ValidationConstants.SUFFIX_ILLEGAL_PARAMS);
            }
        }
        if (ValidationUtil.isEmpty(verifyCode)) {
            return error("验证码不能为空");
        }
        Integer result = currencyService.checkCode(phoneNumber, type, verifyCode);
        return toAjax(result);
    }

    @NoNeedCheck
    @RequestMapping("/uploadImg")
    @ResponseBody
    public AjaxResult uploadPic(@RequestParam("file") MultipartFile pic, HttpServletRequest request,
                                HttpServletResponse response, @RequestParam("typeName") String typeName) throws IllegalStateException, IOException {
        try {
            String originalFilename = pic.getOriginalFilename();
            // 获取上传图片的扩展名(jpg/png/...)
            String extension = FilenameUtils.getExtension(originalFilename);
            if (!extension.equalsIgnoreCase("png") && !extension.equalsIgnoreCase("jpg")
                    && !extension.equalsIgnoreCase("jpeg") && !extension.equalsIgnoreCase("gif")
                        && !extension.equalsIgnoreCase("bmp")) {
                return error("图片格式错误");
            }
            if (!ValidationUtil.isEmpty(typeName)) {
                // 获取图片原始文件名
                // 文件名使用当前时间
                String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                // 图片上传的相对路径（因为相对路径放到页面上就可以显示图片）
                typeName = typeName + "/" + name + "." + extension;
            }
            String path = FileUploadAndDownloadUtils.uploadFile(pic, imgPath, typeName);
            Map<String, String> map = Maps.newHashMap();
            map.put("path", path);
            return success(map);
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return error("上传失败！");
        }
    }



}
