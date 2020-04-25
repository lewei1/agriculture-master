package org.zcy.agriculture.param.registerlogin;

import org.zcy.agriculture.constants.VerifyTypeEnum;

import java.io.Serializable;

public class PasswordRetrieveParam implements Serializable {

    private Long userId;
    //第几步
    private Integer retrieveType;

    private String phone;

    private String email;

    private String verificationCode;

    private VerifyTypeEnum verificationType;

    private String msgVerificationCode;

    private String newPassword;

    private String repeatPassword;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public VerifyTypeEnum getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(VerifyTypeEnum verificationType) {
        this.verificationType = verificationType;
    }

    public Integer getRetrieveType() {
        return retrieveType;
    }

    public void setRetrieveType(Integer retrieveType) {
        this.retrieveType = retrieveType;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getMsgVerificationCode() {
        return msgVerificationCode;
    }

    public void setMsgVerificationCode(String msgVerificationCode) {
        this.msgVerificationCode = msgVerificationCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
