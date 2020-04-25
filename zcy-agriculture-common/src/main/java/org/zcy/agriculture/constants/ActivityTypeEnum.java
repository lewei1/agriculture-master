package org.zcy.agriculture.constants;

public enum ActivityTypeEnum {

    GGL("GGL","刮刮乐"),
    DZP("DZP","大转盘");

    private String code;
    private String val;

    ActivityTypeEnum(String code , String val) {
        this.code = code;
        this.val = val;
    }

    public static String getDescByCode(String code) {
        for (NormalOrDeleteEnum status : NormalOrDeleteEnum.values()) {
            if (status.getCode().equals(code)) {
                return status.getVal();
            }
        }
        return null;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
