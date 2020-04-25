package org.zcy.agriculture.constants;

/**
 * 字典名称枚举
 */
public enum FarmingDictEnum {

    PLANT_STANDARD(0,"种植标准"),
    PLANT_ENVIRONMENT(1,"种植环境");

    private Integer code;
    private String val;

    FarmingDictEnum(Integer code , String val) {
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
