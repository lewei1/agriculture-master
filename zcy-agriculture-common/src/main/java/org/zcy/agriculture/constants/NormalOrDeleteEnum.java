package org.zcy.agriculture.constants;

public enum NormalOrDeleteEnum {

    NORMAL(0,"正常"),
    DELETE(1,"删除"),
	CLOSE(2,"结束");

    private Integer code;
    private String val;

    NormalOrDeleteEnum(Integer code , String val) {
        this.code = code;
        this.val = val;
    }

    public static String getDescByCode(Integer code) {
        for (NormalOrDeleteEnum status : NormalOrDeleteEnum.values()) {
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
