package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 作物模型-阶段表 tb_model_period
 * 
 * @author numberone
 * @date 2019-06-27
 */
public class TbModelPeriod extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long periodId;
	/** 阶段名称 */
	private String periodName;
	/** 开始时间-字符串类型 */
	private String startTime;
	/** 结束时间-字符串类型 */
	private String endTime;
	/** 作物模型id */
	private Long modelId;

	public void setPeriodId(Long periodId) 
	{
		this.periodId = periodId;
	}

	public Long getPeriodId() 
	{
		return periodId;
	}
	public void setPeriodName(String periodName) 
	{
		this.periodName = periodName;
	}

	public String getPeriodName() 
	{
		return periodName;
	}
	public void setStartTime(String startTime) 
	{
		this.startTime = startTime;
	}

	public String getStartTime() 
	{
		return startTime;
	}
	public void setEndTime(String endTime) 
	{
		this.endTime = endTime;
	}

	public String getEndTime() 
	{
		return endTime;
	}
	public void setModelId(Long modelId) 
	{
		this.modelId = modelId;
	}

	public Long getModelId() 
	{
		return modelId;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("periodId", getPeriodId())
            .append("periodName", getPeriodName())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("modelId", getModelId())
            .toString();
    }
}
