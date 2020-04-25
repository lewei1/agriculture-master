package org.zcy.agriculture.base;

public class OftenInfo {
	private Long time = System.currentTimeMillis();
	private int cou = 0;

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public int getCou() {
		return cou;
	}

	public void setCou(int cou) {
		this.cou = cou;
	}

}
