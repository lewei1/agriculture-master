package org.zcy.agriculture.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FarmingPlanVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5215425151516368821L;

	/** 农事计划类型名称 */
	private String planTypeName;
	/** 农事计划状态 */
	private Integer planStatus;
	/** 开始时间 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
	private Date startTime;
	/** 结束时间 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
	private Date endTime;
	/** 子地块ID */
	private Long subPlotId;
	public String getPlanTypeName() {
		return planTypeName;
	}
	public void setPlanTypeName(String planTypeName) {
		this.planTypeName = planTypeName;
	}
	public Integer getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getSubPlotId() {
		return subPlotId;
	}
	public void setSubPlotId(Long subPlotId) {
		this.subPlotId = subPlotId;
	}
	
	
}
