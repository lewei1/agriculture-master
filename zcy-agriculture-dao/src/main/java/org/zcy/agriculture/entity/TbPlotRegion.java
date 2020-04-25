package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 地块区域表 tb_plot_region
 * 
 * @author numberone
 * @date 2019-06-25
 */
public class TbPlotRegion extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long regionId;
	/** 类型名称 */
	private String regionName;
	/** 状态 */
	private Integer regionStatus;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long createBy;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private Long updateBy;
	/** 农场id */
	private String farmId;

	public void setRegionId(Long regionId) 
	{
		this.regionId = regionId;
	}

	public Long getRegionId() 
	{
		return regionId;
	}
	public void setRegionName(String regionName) 
	{
		this.regionName = regionName;
	}

	public String getRegionName() 
	{
		return regionName;
	}
	public void setRegionStatus(Integer regionStatus) 
	{
		this.regionStatus = regionStatus;
	}

	public Integer getRegionStatus() 
	{
		return regionStatus;
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
	public void setFarmId(String farmId) 
	{
		this.farmId = farmId;
	}

	public String getFarmId() 
	{
		return farmId;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("regionId", getRegionId())
            .append("regionName", getRegionName())
            .append("regionStatus", getRegionStatus())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("farmId", getFarmId())
            .toString();
    }
}
