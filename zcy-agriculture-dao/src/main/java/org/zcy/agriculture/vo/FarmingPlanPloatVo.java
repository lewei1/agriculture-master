package org.zcy.agriculture.vo;

import java.io.Serializable;
import java.util.List;

public class FarmingPlanPloatVo implements Serializable{

	private static final long serialVersionUID = -5215425151516368821L;

	/** 地块ID */
	private Long plotId;
	/** 地块名称 */
	private String plotName;
	/** 子地块Id */
	private Long subPlotId;
	/** 子地块名称 */
	private String subPlotName;
	/** 农事计划列表 */
	private List<FarmingPlanVo> plans;
	public Long getPlotId() {
		return plotId;
	}
	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}
	public String getPlotName() {
		return plotName;
	}
	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}
	public String getSubPlotName() {
		return subPlotName;
	}
	public void setSubPlotName(String subPlotName) {
		this.subPlotName = subPlotName;
	}
	public List<FarmingPlanVo> getPlans() {
		return plans;
	}
	public void setPlans(List<FarmingPlanVo> plans) {
		this.plans = plans;
	}
	public Long getSubPlotId() {
		return subPlotId;
	}
	public void setSubPlotId(Long subPlotId) {
		this.subPlotId = subPlotId;
	}
	
	
}
