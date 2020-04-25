package org.zcy.agriculture.enums;

public enum ModelTypeEnum {

    MODEL_FARM("F","农场模型"),
    MODEL_SYS("S","系统模型"),
    MODEL_MY("M", "我的模型")
    ;

    private String code;
    private String val;

    ModelTypeEnum(String code , String val) {
        this.code = code;
        this.val = val;
    }

    public static String getDescByCode(String code) {
        for (ModelTypeEnum status : ModelTypeEnum.values()) {
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
