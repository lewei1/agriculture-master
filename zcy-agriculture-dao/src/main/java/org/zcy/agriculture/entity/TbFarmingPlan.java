package org.zcy.agriculture.entity;

import java.util.Date;

import org.zcy.agriculture.util.DateTimeJsonSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 农事计划（普通计划，智能计划）表 tb_farming_plan
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbFarmingPlan extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 主键id */
	private Long planId;
	/** 计划类型(普通计划，智能计划) */
	private String planType;
	/** 农事类型id */
	private Long farmingTypeId;
	/** 投入工时 */
	private Double workHours;
	/** 开始时间 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date startTime;
	/** 结束时间 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date endTime;
	/** 是否重复 */
	private Integer planRepeat;
	/** 重复天数 */
	private Integer planRepeatData;
	/** 如果选中了重复，此农事计划就是最初的 农事计划ID */
	private Long planRepeatPlanId;
	/** 农机id */
	private String machineId;
	/** 备注 */
	private String remark;
	/** 计划状态 */
	private Integer planStatus;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long createBy;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private Long updateBy;
	/** 农场id,UUID */
	private String farmId;
	/** 是否智能计划 0 不是 1是 */
	private Integer isLntelligence;
	/**  */
	private Long dataId;

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getPlanType() {
		return planType;
	}

	public void setFarmingTypeId(Long farmingTypeId) {
		this.farmingTypeId = farmingTypeId;
	}

	public Long getFarmingTypeId() {
		return farmingTypeId;
	}

	public void setWorkHours(Double workHours) {
		this.workHours = workHours;
	}

	public Double getWorkHours() {
		return workHours;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setPlanRepeat(Integer planRepeat) {
		this.planRepeat = planRepeat;
	}

	public Integer getPlanRepeat() {
		return planRepeat;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
	}

	public Integer getPlanStatus() {
		return planStatus;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

	public String getFarmId() {
		return farmId;
	}

	public void setIsLntelligence(Integer isLntelligence) {
		this.isLntelligence = isLntelligence;
	}

	public Integer getIsLntelligence() {
		return isLntelligence;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public Long getDataId() {
		return dataId;
	}

	public Integer getPlanRepeatData() {
		return planRepeatData;
	}

	public void setPlanRepeatData(Integer planRepeatData) {
		this.planRepeatData = planRepeatData;
	}

	public Long getPlanRepeatPlanId() {
		return planRepeatPlanId;
	}

	public void setPlanRepeatPlanId(Long planRepeatPlanId) {
		this.planRepeatPlanId = planRepeatPlanId;
	}

}
