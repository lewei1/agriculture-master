package org.zcy.agriculture.entity;


//import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.sql.Time;

/**
 * 监控中心--白天报警时间段表 tb_alarm_day_time
 * 
 * @author zh
 * @date 2019-06-28
 */
public class TbAlarmDayTime extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 阈值ID */
	private Long id;
	/** 农场ID */
	private String farmId;
	/** 地块ID */
	private Long plotId;
	/** 白天开始时间 */
	private Time dayStartTime;
	/** 白天结束时间 */
	private Time dayEndTime;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
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
	public void setDayStartTime(Time dayStartTime)
	{
		this.dayStartTime = dayStartTime;
	}

	public Time getDayStartTime()
	{
		return dayStartTime;
	}
	public void setDayEndTime(Time dayEndTime)
	{
		this.dayEndTime = dayEndTime;
	}

	public Time getDayEndTime()
	{
		return dayEndTime;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("farmId", getFarmId())
            .append("plotId", getPlotId())
            .append("dayStartTime", getDayStartTime())
            .append("dayEndTime", getDayEndTime())
            .toString();
    }
}
