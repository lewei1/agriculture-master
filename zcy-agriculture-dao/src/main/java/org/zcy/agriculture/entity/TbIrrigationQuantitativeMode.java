package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 分组-量化模式表 tb_irrigation_quantitative_mode
 * 
 * @author zh
 * @date 2019-06-21
 */
public class TbIrrigationQuantitativeMode extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 所属分组ID */
	private Long groupId;
	/** 打开时间 */
	private Date openTime;
	/** 打开分钟数 */
	private Integer openMinute;
	/** 打开秒数 */
	private Integer openSecond;
	/** 循环次数 */
	private Integer column6;
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
	public void setColumn6(Integer column6) 
	{
		this.column6 = column6;
	}

	public Integer getColumn6() 
	{
		return column6;
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
            .append("column6", getColumn6())
            .append("intervalMinute", getIntervalMinute())
            .append("intervalSecend", getIntervalSecend())
            .toString();
    }
}
