package org.zcy.agriculture.enums;

public enum DevStatusEnum {

    ALARMING(0,"正报警"),
    RUNING(1,"运行中"),
    DISCONNECT(2,"断开"),
    READY(3,"待机"),
    OTHER(4,"其它"),
    MAX_TYPE(4,"最大值");

    private Integer code;
    private String val;

    DevStatusEnum(Integer code , String val) {
        this.code = code;
        this.val = val;
    }

    public static String getDescByCode(Integer code) {
        for (DevStatusEnum status : DevStatusEnum.values()) {
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
