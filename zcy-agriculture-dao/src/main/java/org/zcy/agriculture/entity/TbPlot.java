package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 地块表 tb_plot
 * 
 * @author numberone
 * @date 2019-07-05
 */
public class TbPlot extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long plotId;
	/** 地块图片 */
	private String plotImg;
	/** 地块名称 */
	private String plotName;
	/** 地区id */
	private Long regionId;
	/** 地块面积 */
	private Double plotAcreage;
	/** 类型 */
	private String plotType;
	/** 状态 */
	private Integer plotStatus;
	/** 序列 */
	private Integer plotSequence;
	/** 地块颜色 */
	private String plotColor;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long createBy;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private Long updateBy;
	/**  */
	private String farmId;

	public void setPlotId(Long plotId) 
	{
		this.plotId = plotId;
	}

	public Long getPlotId() 
	{
		return plotId;
	}
	public void setPlotImg(String plotImg) 
	{
		this.plotImg = plotImg;
	}

	public String getPlotImg() 
	{
		return plotImg;
	}
	public void setPlotName(String plotName) 
	{
		this.plotName = plotName;
	}

	public String getPlotName() 
	{
		return plotName;
	}
	public void setRegionId(Long regionId) 
	{
		this.regionId = regionId;
	}

	public Long getRegionId() 
	{
		return regionId;
	}
	public void setPlotAcreage(Double plotAcreage) 
	{
		this.plotAcreage = plotAcreage;
	}

	public Double getPlotAcreage() 
	{
		return plotAcreage;
	}
	public void setPlotType(String plotType) 
	{
		this.plotType = plotType;
	}

	public String getPlotType() 
	{
		return plotType;
	}
	public void setPlotStatus(Integer plotStatus) 
	{
		this.plotStatus = plotStatus;
	}

	public Integer getPlotStatus() 
	{
		return plotStatus;
	}
	public void setPlotSequence(Integer plotSequence) 
	{
		this.plotSequence = plotSequence;
	}

	public Integer getPlotSequence() 
	{
		return plotSequence;
	}
	public void setPlotColor(String plotColor) 
	{
		this.plotColor = plotColor;
	}

	public String getPlotColor() 
	{
		return plotColor;
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
            .append("plotId", getPlotId())
            .append("plotImg", getPlotImg())
            .append("plotName", getPlotName())
            .append("regionId", getRegionId())
            .append("plotAcreage", getPlotAcreage())
            .append("plotType", getPlotType())
            .append("plotStatus", getPlotStatus())
            .append("plotSequence", getPlotSequence())
            .append("plotColor", getPlotColor())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("farmId", getFarmId())
            .toString();
    }
}
