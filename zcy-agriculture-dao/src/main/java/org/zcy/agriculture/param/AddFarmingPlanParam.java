package org.zcy.agriculture.param;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.zcy.agriculture.entity.TbFarmingPlanImplement;
import org.zcy.agriculture.entity.TbPlanImg;
import org.zcy.agriculture.entity.TbPlanMaterial;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AddFarmingPlanParam implements Serializable{
	private static final long serialVersionUID = -3485350394871011046L;
	private Long planId;
	/** 农事类型id */
	private Long farmingTypeId;
	/** 开始时间 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date startTime;
	/** 结束时间 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date endTime;
	/** 智能开始时间 */
	private String startTimeZn;
	/** 智能结束时间 */
	private String endTimeZn;
	/** 是否重复 */
	private Integer planRepeat;
	/** 重复天数 */
	private Integer planRepeatData;
	/** 使用物品值  */
	private List<TbPlanMaterial> materials;
	/** 农机id */
	private String machineId;
	/** 备注 */
	private String remark;
	/** 图片 */
	private List<TbPlanImg> imgs;
	/** 地块对应人员 */
	private List<AddPloatUserParam> pantUsers;
	/** 添加人 */
	private Long createBy;
	private Long updateBy;
	private Long updateType;
	/** 农场Id */
	private String farmId; 
	/** 投入工时 */
	private Double workHours;
	/** 执行计划 */
	private TbFarmingPlanImplement implement;
	
	private Integer planStatus;
	
	private Long id;
	
	public Long getFarmingTypeId() {
		return farmingTypeId;
	}
	public void setFarmingTypeId(Long farmingTypeId) {
		this.farmingTypeId = farmingTypeId;
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
	public Integer getPlanRepeat() {
		return planRepeat;
	}
	public void setPlanRepeat(Integer planRepeat) {
		this.planRepeat = planRepeat;
	}
	public List<TbPlanMaterial> getMaterials() {
		return materials;
	}
	public void setMaterials(List<TbPlanMaterial> materials) {
		this.materials = materials;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<TbPlanImg> getImgs() {
		return imgs;
	}
	public void setImgs(List<TbPlanImg> imgs) {
		this.imgs = imgs;
	}
	
	public List<AddPloatUserParam> getPantUsers() {
		return pantUsers;
	}
	public void setPantUsers(List<AddPloatUserParam> pantUsers) {
		this.pantUsers = pantUsers;
	}
	public String getFarmId() {
		return farmId;
	}
	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}
	public Double getWorkHours() {
		return workHours;
	}
	public void setWorkHours(Double workHours) {
		this.workHours = workHours;
	}
	public TbFarmingPlanImplement getImplement() {
		return implement;
	}
	public void setImplement(TbFarmingPlanImplement implement) {
		this.implement = implement;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPlanRepeatData() {
		return planRepeatData;
	}
	public void setPlanRepeatData(Integer planRepeatData) {
		this.planRepeatData = planRepeatData;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Integer getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
	}
	public Long getUpdateType() {
		return updateType;
	}
	public void setUpdateType(Long updateType) {
		this.updateType = updateType;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public String getStartTimeZn() {
		return startTimeZn;
	}
	public void setStartTimeZn(String startTimeZn) {
		this.startTimeZn = startTimeZn;
	}
	public String getEndTimeZn() {
		return endTimeZn;
	}
	public void setEndTimeZn(String endTimeZn) {
		this.endTimeZn = endTimeZn;
	}
	
}
