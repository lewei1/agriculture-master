package org.zcy.agriculture.param.registerlogin;

import org.zcy.agriculture.entity.TbMerchant;

public class RegisterParam extends TbMerchant {

    /** 验证码 */
    private String verificationCode;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
