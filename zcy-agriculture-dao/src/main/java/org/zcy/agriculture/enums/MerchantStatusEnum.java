package org.zcy.agriculture.enums;

public enum MerchantStatusEnum {

    INACTIVE(0,"未开通"),
    ACTIVE(1,"已开通"),
    DELETE(2,"删除"),
    ;

    private Integer code;
    private String val;

    MerchantStatusEnum(Integer code , String val) {
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
