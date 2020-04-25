package org.zcy.agriculture.constants;

public enum PlanTypeEnum {

	ORDINARY("0","普通"),
	STANDARD("1","标准"),
	INTELLIGENCE("2","智能"),
    ;

    private String code;
    private String val;

    PlanTypeEnum(String code , String val) {
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
