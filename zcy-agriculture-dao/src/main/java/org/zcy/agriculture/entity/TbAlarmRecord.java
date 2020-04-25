package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 报警记录表 tb_alarm_record
 * 
 * @author zh
 * @date 2019-07-03
 */
public class TbAlarmRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 农场ID */
	private String farmId;
	/** 地块ID */
	private Long plotId;
	/** 设备ID */
	private Long devId;
	/** 设备类型（0监测设备，1气象站，2灌溉设备，3控制设备，4摄像头） */
	private Integer devType;
	/** 报警阈值类型Index */
	private Integer typesIndex;
	/** 监测到的数值 */
	private Float realValue;
	/** 小于或者大于阈值（小于0，大于1） */
	private Integer lessOrMore;
	/** 创建时间 */
	private Date createTime;

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
	public void setDevId(Long devId) 
	{
		this.devId = devId;
	}

	public Long getDevId() 
	{
		return devId;
	}
	public void setDevType(Integer devType) 
	{
		this.devType = devType;
	}

	public Integer getDevType() 
	{
		return devType;
	}
	public void setTypesIndex(Integer typesIndex) 
	{
		this.typesIndex = typesIndex;
	}

	public Integer getTypesIndex() 
	{
		return typesIndex;
	}
	public void setRealValue(Float realValue) 
	{
		this.realValue = realValue;
	}

	public Float getRealValue() 
	{
		return realValue;
	}
	public void setLessOrMore(Integer lessOrMore) 
	{
		this.lessOrMore = lessOrMore;
	}

	public Integer getLessOrMore() 
	{
		return lessOrMore;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("farmId", getFarmId())
            .append("plotId", getPlotId())
            .append("devId", getDevId())
            .append("devType", getDevType())
            .append("typesIndex", getTypesIndex())
            .append("realValue", getRealValue())
            .append("lessOrMore", getLessOrMore())
            .append("createTime", getCreateTime())
            .toString();
    }
}
