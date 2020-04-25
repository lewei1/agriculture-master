package org.zcy.agriculture.enums;

public enum ThingsboardDeviceEnum {

    EXIST(0,"存在"),
    NOT_EXIST(1,"不存在"),
    TOKEN_ERR(2,"待机");

    private Integer code;
    private String val;

    ThingsboardDeviceEnum(Integer code , String val) {
        this.code = code;
        this.val = val;
    }

    public static String getDescByCode(Integer code) {
        for (ThingsboardDeviceEnum status : ThingsboardDeviceEnum.values()) {
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
