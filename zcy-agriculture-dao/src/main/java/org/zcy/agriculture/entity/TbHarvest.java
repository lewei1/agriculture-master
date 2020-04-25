package org.zcy.agriculture.entity;

import java.util.Date;

import org.zcy.agriculture.constants.Excel;
import org.zcy.agriculture.util.DateTimeJsonSerializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 作物采收表 tb_harvest
 * 
 * @author numberone
 * @date 2019-06-27
 */
public class TbHarvest extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 主键id */
	private Long harvestId;
	/** 地块id */
	private Long plotId;
	/** 子地块id */
	private Long subPlotId;
	/** 主键id */
	private Long harvestSpecId;
	/** 采收时间 */
	@Excel(name = "采收时间",dateFormat = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
	private Date harvestTime;
	/** 采收总量（KG） */
	@Excel(name = "采收重量（KG）")
	private Double harvestAmount;
	/** 作物等级 */
	@Excel(name = "作物等级")
	private String cropLevel;
	/** 保鲜期(天) */
	private Double freshDay;
	/** 成员id，多个已逗号隔开 */
	private String userId;
	/** 成员名称，多个已逗号隔开 */
	@Excel(name = "采收人员")
	private String userName;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date createTime;
	/** 创建人 */
	private Long createBy;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private Long updateBy;
	/** 农场id,UUID */
	private String farmId;
	/** 采集时当前的种植ID */
	private Long plantingId;
	/** 采收状态（0正常，1删除） */
	private Integer status;

	public void setHarvestId(Long harvestId) {
		this.harvestId = harvestId;
	}

	public Long getHarvestId() {
		return harvestId;
	}

	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}

	public Long getPlotId() {
		return plotId;
	}

	public void setSubPlotId(Long subPlotId) {
		this.subPlotId = subPlotId;
	}

	public Long getSubPlotId() {
		return subPlotId;
	}

	public void setHarvestSpecId(Long harvestSpecId) {
		this.harvestSpecId = harvestSpecId;
	}

	public Long getHarvestSpecId() {
		return harvestSpecId;
	}

	public void setHarvestTime(Date harvestTime) {
		this.harvestTime = harvestTime;
	}

	public Date getHarvestTime() {
		return harvestTime;
	}

	public Double getHarvestAmount() {
		return harvestAmount;
	}

	public void setHarvestAmount(Double harvestAmount) {
		this.harvestAmount = harvestAmount;
	}

	public void setCropLevel(String cropLevel) {
		this.cropLevel = cropLevel;
	}

	public String getCropLevel() {
		return cropLevel;
	}

	public Double getFreshDay() {
		return freshDay;
	}

	public void setFreshDay(Double freshDay) {
		this.freshDay = freshDay;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

	public String getFarmId() {
		return farmId;
	}

	public void setPlantingId(Long plantingId) {
		this.plantingId = plantingId;
	}

	public Long getPlantingId() {
		return plantingId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
