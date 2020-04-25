package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 子地块表 tb_sub_plot
 * 
 * @author numberone
 * @date 2019-06-25
 */
public class TbSubPlot extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long subPlotId;
	/** 子地块名称 */
	private String subPlotName;
	/** 负责人 */
	private Long subPlotPerson;
	/** 子地块状态 */
	private Integer subPlotStatus;
	/** 子地块面积(亩) */
	private Double subPlotAcreage;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long createBy;
	/** 地块id */
	private Long plotId;

	public void setSubPlotId(Long subPlotId) 
	{
		this.subPlotId = subPlotId;
	}

	public Long getSubPlotId() 
	{
		return subPlotId;
	}
	public void setSubPlotName(String subPlotName) 
	{
		this.subPlotName = subPlotName;
	}

	public String getSubPlotName() 
	{
		return subPlotName;
	}
	public void setSubPlotPerson(Long subPlotPerson) 
	{
		this.subPlotPerson = subPlotPerson;
	}

	public Long getSubPlotPerson() 
	{
		return subPlotPerson;
	}
	public void setSubPlotStatus(Integer subPlotStatus) 
	{
		this.subPlotStatus = subPlotStatus;
	}

	public Integer getSubPlotStatus() 
	{
		return subPlotStatus;
	}
	public void setSubPlotAcreage(Double subPlotAcreage) 
	{
		this.subPlotAcreage = subPlotAcreage;
	}

	public Double getSubPlotAcreage() 
	{
		return subPlotAcreage;
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
	public void setPlotId(Long plotId) 
	{
		this.plotId = plotId;
	}

	public Long getPlotId() 
	{
		return plotId;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("subPlotId", getSubPlotId())
            .append("subPlotName", getSubPlotName())
            .append("subPlotPerson", getSubPlotPerson())
            .append("subPlotStatus", getSubPlotStatus())
            .append("subPlotAcreage", getSubPlotAcreage())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("plotId", getPlotId())
            .toString();
    }
}
