package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 分组模式下-定时模式表 tb_irrigation_timing_mode
 * 
 * @author zh
 * @date 2019-06-21
 */
public class TbIrrigationTimingMode extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 分组ID */
	private Long groupId;
	/** 打开时间 */
	private Date openTime;
	/** 分钟 */
	private Integer openMinute;
	/** 打开多少秒 */
	private Integer openSecond;
	/** 循环次数 */
	private Integer cycles;
	/** 间隔分钟数 */
	private Integer intervalMinute;
	/** 间隔秒数 */
	private Integer intervalSecend;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setGroupId(Long groupId) 
	{
		this.groupId = groupId;
	}

	public Long getGroupId() 
	{
		return groupId;
	}
	public void setOpenTime(Date openTime) 
	{
		this.openTime = openTime;
	}

	public Date getOpenTime() 
	{
		return openTime;
	}
	public void setOpenMinute(Integer openMinute) 
	{
		this.openMinute = openMinute;
	}

	public Integer getOpenMinute() 
	{
		return openMinute;
	}
	public void setOpenSecond(Integer openSecond) 
	{
		this.openSecond = openSecond;
	}

	public Integer getOpenSecond() 
	{
		return openSecond;
	}
	public void setCycles(Integer cycles) 
	{
		this.cycles = cycles;
	}

	public Integer getCycles() 
	{
		return cycles;
	}
	public void setIntervalMinute(Integer intervalMinute) 
	{
		this.intervalMinute = intervalMinute;
	}

	public Integer getIntervalMinute() 
	{
		return intervalMinute;
	}
	public void setIntervalSecend(Integer intervalSecend) 
	{
		this.intervalSecend = intervalSecend;
	}

	public Integer getIntervalSecend() 
	{
		return intervalSecend;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("groupId", getGroupId())
            .append("openTime", getOpenTime())
            .append("openMinute", getOpenMinute())
            .append("openSecond", getOpenSecond())
            .append("cycles", getCycles())
            .append("intervalMinute", getIntervalMinute())
            .append("intervalSecend", getIntervalSecend())
            .toString();
    }
}
