package org.zcy.agriculture.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 农事计划返回参数
 * @author hp
 *
 */
public class FarmingPlanListVo implements Serializable{

	private static final long serialVersionUID = -8598837523902059227L;

	/** 全部计划数量 */
	private Integer allPlansNum = 0;
	
	/** 待指派数量 */
	private Integer assignPlansNum = 0;
	
	/** 完成计划数量 */
	private Integer overPlansNum = 0;
	
	/** 逾期计划数量 */
	private Integer overduePlansNum = 0;
	
	/** 地块计划数据列表 */
	private List<FarmingPlanPloatVo> ploats;

	public Integer getAllPlansNum() {
		return allPlansNum;
	}

	public void setAllPlansNum(Integer allPlansNum) {
		this.allPlansNum = allPlansNum;
	}

	public Integer getAssignPlansNum() {
		return assignPlansNum;
	}

	public void setAssignPlansNum(Integer assignPlansNum) {
		this.assignPlansNum = assignPlansNum;
	}

	public Integer getOverPlansNum() {
		return overPlansNum;
	}

	public void setOverPlansNum(Integer overPlansNum) {
		this.overPlansNum = overPlansNum;
	}

	public Integer getOverduePlansNum() {
		return overduePlansNum;
	}

	public void setOverduePlansNum(Integer overduePlansNum) {
		this.overduePlansNum = overduePlansNum;
	}

	public List<FarmingPlanPloatVo> getPloats() {
		return ploats;
	}

	public void setPloats(List<FarmingPlanPloatVo> ploats) {
		this.ploats = ploats;
	}
	
	
}
