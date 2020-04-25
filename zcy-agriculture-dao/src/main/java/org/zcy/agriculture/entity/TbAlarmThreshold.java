package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 报警阈值表 tb_alarm_threshold
 * 
 * @author zh
 * @date 2019-06-28
 */
public class TbAlarmThreshold extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 阈值ID */
	private Long alarmThresholdId;
	/** 阈值类型index */
	private Integer typesIndex;
	/** 农场ID */
	private String farmId;
	/** 地块ID */
	private Long plotId;
	/** 白天阈值小的那个值 */
	private Float dayMinValue;
	/** 白天阈值大的那个值 */
	private Float dayMaxValue;
	/** 晚上阈值小的值 */
	private Float nightMinValue;
	/** 夜晚阈值大的那个值 */
	private Float nightMaxValue;

	public void setAlarmThresholdId(Long alarmThresholdId) 
	{
		this.alarmThresholdId = alarmThresholdId;
	}

	public Long getAlarmThresholdId() 
	{
		return alarmThresholdId;
	}
	public void setTypesIndex(Integer typesIndex) 
	{
		this.typesIndex = typesIndex;
	}

	public Integer getTypesIndex() 
	{
		return typesIndex;
	}
	public void setFarmId(String farmId) 
	{
		this.farmId = farmId;
	}

	public String getFarmId() 
	{
		return farmId;
	}
	public void setPlotId(Long plotId) 
	{
		this.plotId = plotId;
	}

	public Long getPlotId() 
	{
		return plotId;
	}
	public void setDayMinValue(Float dayMinValue) 
	{
		this.dayMinValue = dayMinValue;
	}

	public Float getDayMinValue() 
	{
		return dayMinValue;
	}
	public void setDayMaxValue(Float dayMaxValue) 
	{
		this.dayMaxValue = dayMaxValue;
	}

	public Float getDayMaxValue() 
	{
		return dayMaxValue;
	}
	public void setNightMinValue(Float nightMinValue) 
	{
		this.nightMinValue = nightMinValue;
	}

	public Float getNightMinValue() 
	{
		return nightMinValue;
	}
	public void setNightMaxValue(Float nightMaxValue) 
	{
		this.nightMaxValue = nightMaxValue;
	}

	public Float getNightMaxValue() 
	{
		return nightMaxValue;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("alarmThresholdId", getAlarmThresholdId())
            .append("typesIndex", getTypesIndex())
            .append("farmId", getFarmId())
            .append("plotId", getPlotId())
            .append("dayMinValue", getDayMinValue())
            .append("dayMaxValue", getDayMaxValue())
            .append("nightMinValue", getNightMinValue())
            .append("nightMaxValue", getNightMaxValue())
            .toString();
    }
}
