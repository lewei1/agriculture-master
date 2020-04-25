package org.zcy.agriculture.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 种植表 tb_planting
 * 
 * @author numberone
 * @date 2019-06-25
 */
public class TbPlanting extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long plantingId;
	/** 作物种类id */
	private Long cropCategoryId;
	/** 作物品种 */
	private Long cropTypeId;
	/** 定植时间 */
	@JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
	private Date plantTime;
	/** 预计结束时间 */
	@JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
	private Date endTime;
	/** 种植标准(无公害，有机，绿色) */
	private String plantStandard;
	/** 种植状态(0-正常，1-删除，2-结束种植) */
	private Integer plantingStatus;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long createBy;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private Long updateBy;
	/** 地块id */
	private Long plotId;
	/** 子地块id */
	private Long subPlotId;
	/** 农场id */
	private String farmId;

	public void setPlantingId(Long plantingId) 
	{
		this.plantingId = plantingId;
	}

	public Long getPlantingId() 
	{
		return plantingId;
	}
	public void setCropCategoryId(Long cropCategoryId) 
	{
		this.cropCategoryId = cropCategoryId;
	}

	public Long getCropCategoryId() 
	{
		return cropCategoryId;
	}
	public void setCropTypeId(Long cropTypeId) 
	{
		this.cropTypeId = cropTypeId;
	}

	public Long getCropTypeId() 
	{
		return cropTypeId;
	}
	public void setPlantTime(Date plantTime) 
	{
		this.plantTime = plantTime;
	}

	public Date getPlantTime() 
	{
		return plantTime;
	}
	public void setEndTime(Date endTime) 
	{
		this.endTime = endTime;
	}

	public Date getEndTime() 
	{
		return endTime;
	}
	public void setPlantStandard(String plantStandard) 
	{
		this.plantStandard = plantStandard;
	}

	public String getPlantStandard() 
	{
		return plantStandard;
	}
	public void setPlantingStatus(Integer plantingStatus) 
	{
		this.plantingStatus = plantingStatus;
	}

	public Integer getPlantingStatus() 
	{
		return plantingStatus;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setCreateBy(Long createBy) 
	{
		this.createBy = createBy;
	}

	public Long getCreateBy() 
	{
		return createBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setUpdateBy(Long updateBy) 
	{
		this.updateBy = updateBy;
	}

	public Long getUpdateBy() 
	{
		return updateBy;
	}
	public void setPlotId(Long plotId) 
	{
		this.plotId = plotId;
	}

	public Long getPlotId() 
	{
		return plotId;
	}
	public void setSubPlotId(Long subPlotId) 
	{
		this.subPlotId = subPlotId;
	}

	public Long getSubPlotId() 
	{
		return subPlotId;
	}
	public void setFarmId(String farmId) 
	{
		this.farmId = farmId;
	}

	public String getFarmId() 
	{
		return farmId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("plantingId", getPlantingId())
            .append("cropCategoryId", getCropCategoryId())
            .append("cropTypeId", getCropTypeId())
            .append("plantTime", getPlantTime())
            .append("endTime", getEndTime())
            .append("plantStandard", getPlantStandard())
            .append("plantingStatus", getPlantingStatus())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("plotId", getPlotId())
            .append("subPlotId", getSubPlotId())
            .append("farmId", getFarmId())
            .toString();
    }
}
