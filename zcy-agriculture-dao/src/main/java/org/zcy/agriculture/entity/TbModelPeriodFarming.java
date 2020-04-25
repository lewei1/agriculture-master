package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 作物模型-阶段农事表 tb_model_period_farming
 * 
 * @author numberone
 * @date 2019-06-27
 */
public class TbModelPeriodFarming extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long farmingId;
	/** 定植类型 */
	private String plantType;
	/** 定植天数 */
	private Integer plantDay;
	/** 农事类型id */
	private Long farmingTypeId;
	/** 农事要求 */
	private String farmingRequirements;
	/** 模型阶段id */
	private Long periodId;

	//农事时间，需要解析
	private String farmingTime;
	//农事类型
	private String farmingTypeName;

	public String getFarmingTypeName() {
		return farmingTypeName;
	}

	public void setFarmingTypeName(String farmingTypeName) {
		this.farmingTypeName = farmingTypeName;
	}

	public String getFarmingTime() {
		return farmingTime;
	}

	public void setFarmingTime(String farmingTime) {
		this.farmingTime = farmingTime;
	}

	public void setFarmingId(Long farmingId) 
	{
		this.farmingId = farmingId;
	}

	public Long getFarmingId() 
	{
		return farmingId;
	}
	public void setPlantType(String plantType) 
	{
		this.plantType = plantType;
	}

	public String getPlantType() 
	{
		return plantType;
	}
	public void setPlantDay(Integer plantDay) 
	{
		this.plantDay = plantDay;
	}

	public Integer getPlantDay() 
	{
		return plantDay;
	}
	public void setFarmingTypeId(Long farmingTypeId) 
	{
		this.farmingTypeId = farmingTypeId;
	}

	public Long getFarmingTypeId() 
	{
		return farmingTypeId;
	}
	public void setFarmingRequirements(String farmingRequirements) 
	{
		this.farmingRequirements = farmingRequirements;
	}

	public String getFarmingRequirements() 
	{
		return farmingRequirements;
	}
	public void setPeriodId(Long periodId) 
	{
		this.periodId = periodId;
	}

	public Long getPeriodId() 
	{
		return periodId;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("farmingId", getFarmingId())
            .append("plantType", getPlantType())
            .append("plantDay", getPlantDay())
            .append("farmingTypeId", getFarmingTypeId())
            .append("farmingRequirements", getFarmingRequirements())
            .append("periodId", getPeriodId())
            .toString();
    }
}
