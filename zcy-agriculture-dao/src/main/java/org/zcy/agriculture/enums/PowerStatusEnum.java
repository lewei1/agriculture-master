package org.zcy.agriculture.enums;

public enum PowerStatusEnum {

    VALID(0,"有效"),
    INVALID(1,"无效");

    private Integer code;
    private String val;

    PowerStatusEnum(Integer code , String val) {
        this.code = code;
        this.val = val;
    }

    public static String getDescByCode(Integer code) {
        for (PowerStatusEnum status : PowerStatusEnum.values()) {
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
