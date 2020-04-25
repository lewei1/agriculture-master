package org.zcy.agriculture.constants;

public enum ErrorCodeEnum {

    NOT_LOGIN(-101, "登录状态信息失效，请重新登录"),
    NOT_AUTH(-102, "用户未授权"),
    ;

    private Integer code;
    private String val;

    ErrorCodeEnum(Integer code , String val) {
        this.code = code;
        this.val = val;
    }

    public static String getDescByCode(Integer code) {
        for (FarmingDictEnum status : FarmingDictEnum.values()) {
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
