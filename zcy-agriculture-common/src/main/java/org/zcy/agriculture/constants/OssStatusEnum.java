package org.zcy.agriculture.constants;

public enum OssStatusEnum {
	NO_DETAIL(0,"未填写商户信息") , NO_PACKAGE(1,"未选择套餐") , NO_ORDER(2,"未审核支付"), NARMAL(3,"正常")
	,FROZEN(4,"以冻结")
	,OVER(6,"以到期");
	private int code;
	private String val;

	OssStatusEnum(int code , String val) {
		this.code = code;
		this.val = val;
	}
	
	public static String getDescByCode(int code) {
		for (OssStatusEnum orderStatus : OssStatusEnum.values()) {
			if (orderStatus.getCode() == code) {
				return orderStatus.getVal();
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	
}
