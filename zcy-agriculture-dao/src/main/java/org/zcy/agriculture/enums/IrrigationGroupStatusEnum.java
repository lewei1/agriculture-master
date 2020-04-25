package org.zcy.agriculture.enums;

public enum IrrigationGroupStatusEnum {

    GROUP_CLOSE(0,"分组关闭"),
    GROUP_OPEN(1,"分组打开"),
    GROUP_DELETE(2,"分组删除"),
    ;

    private Integer code;
    private String val;

    IrrigationGroupStatusEnum(Integer code , String val) {
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
