package org.zcy.agriculture.enums;

public enum PasswordRetrieveTypeEnum {

    STEP_1(1,"确认账号"),
    STEP_2(2,"安全验证"),
    STEP_3(3,"重置密码")
    ;

    private Integer code;
    private String val;

    PasswordRetrieveTypeEnum(Integer code , String val) {
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
