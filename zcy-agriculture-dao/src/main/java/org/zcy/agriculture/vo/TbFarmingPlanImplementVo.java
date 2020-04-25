package org.zcy.agriculture.vo;

import java.util.Date;

import org.zcy.agriculture.entity.TbFarmingPlanImplement;

/**
 * 智能计划执行表 tb_farming_plan_implement
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbFarmingPlanImplementVo extends TbFarmingPlanImplement {
	private static final long serialVersionUID = 6704283913610452006L;
	private Date toDay;

	private Integer planRepeat;// 是否重复
	private Integer planRepeatData;// 重复天数
	private Date startTime;// 计划开始时间
	private Date endTime;// 计划结束时间

	public Date getToDay() {
		return toDay;
	}

	public void setToDay(Date toDay) {
		this.toDay = toDay;
	}

	public Integer getPlanRepeat() {
		return planRepeat;
	}

	public void setPlanRepeat(Integer planRepeat) {
		this.planRepeat = planRepeat;
	}

	public Integer getPlanRepeatData() {
		return planRepeatData;
	}

	public void setPlanRepeatData(Integer planRepeatData) {
		this.planRepeatData = planRepeatData;
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

}
