package org.zcy.agriculture.enums;

public enum UserEditTypeEnum {

    PERSONAL_INFO(0,"个人资料"),
    PASSWORD_SECURITY(1,"密码安全"),
    ACCOUNT_BONDING1(2,"账号绑定-第一步"),
    ACCOUNT_BONDING2(3,"账号绑定-第二步")
    ;

    private Integer code;
    private String val;

    UserEditTypeEnum(Integer code , String val) {
        this.code = code;
        this.val = val;
    }

    public static String getDescByCode(Integer code) {
        for (DevTypeEnum status : DevTypeEnum.values()) {
            if (status.getCode() == code) {
                return status.getVal();
            }
        }
        return null;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

}
