package org.zcy.agriculture.param;

import org.zcy.agriculture.constants.VerifyTypeEnum;
import org.zcy.agriculture.entity.TbUser;

public class UserEditParam extends TbUser {

    private Integer editType;

    /*重置密码*/
    private String newPassword;

    private String repeatPassword;

    //账户解绑
    private Integer untiedType;

    private VerifyTypeEnum verificationType;

    private String msgVerificationCode;
    //下一步之后
    private String newPhone;

    private String newVerificationCode;

    public VerifyTypeEnum getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(VerifyTypeEnum verificationType) {
        this.verificationType = verificationType;
    }

    public String getMsgVerificationCode() {
        return msgVerificationCode;
    }

    public void setMsgVerificationCode(String msgVerificationCode) {
        this.msgVerificationCode = msgVerificationCode;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public String getNewVerificationCode() {
        return newVerificationCode;
    }

    public void setNewVerificationCode(String newVerificationCode) {
        this.newVerificationCode = newVerificationCode;
    }

    public Integer getUntiedType() {
        return untiedType;
    }

    public void setUntiedType(Integer untiedType) {
        this.untiedType = untiedType;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Integer getEditType() {
        return editType;
    }

    public void setEditType(Integer editType) {
        this.editType = editType;
    }
}
