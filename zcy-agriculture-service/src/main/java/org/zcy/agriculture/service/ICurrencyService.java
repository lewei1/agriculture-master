package org.zcy.agriculture.service;

import org.zcy.agriculture.constants.VerifyTypeEnum;

public interface ICurrencyService {

    Integer checkCode(String phoneNumber, VerifyTypeEnum type, String verifyCode);

    Boolean isVerificationCodeTypeIllegal(VerifyTypeEnum verifyType);
}
