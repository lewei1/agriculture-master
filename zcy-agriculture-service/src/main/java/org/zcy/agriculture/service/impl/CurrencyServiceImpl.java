package org.zcy.agriculture.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.constants.VerifyTypeEnum;
import org.zcy.agriculture.redis.JedisClient;
import org.zcy.agriculture.service.ICurrencyService;
import org.zcy.agriculture.util.RedisKeyUtils;
import org.zcy.agriculture.util.ValidationUtil;

@Service
public class CurrencyServiceImpl implements ICurrencyService {

    @Autowired
    private JedisClient redis;

    @Override
    public Integer checkCode(String phoneNumber, VerifyTypeEnum type, String verifyCode) {
        String redisKey = RedisKeyUtils.getVerificationCode(phoneNumber, type.getName());
        String oldVerifyCode = redis.get(redisKey);
        if(ValidationUtil.isEmpty(oldVerifyCode) || !oldVerifyCode.equals(verifyCode)) {
            return -1;
        }
        return 1;
    }

    @Override
    public Boolean isVerificationCodeTypeIllegal(VerifyTypeEnum verifyType) {
        boolean isIllegal = true;
        VerifyTypeEnum[] typeEnums = VerifyTypeEnum.values();
        for(VerifyTypeEnum e : typeEnums) {
            if(e.getName().equals(verifyType.getName())) {
                isIllegal = false;
                break;
            }
        }
        return isIllegal;
    }
}
