package org.zcy.agriculture.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 农机使用记录表 tb_use_record
 * 
 * @author zenghao
 * @date 2019-06-21
 */
public class TbUseRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 农机使用记录ID */
	private Long machineRecordId;
	/** 计划 */
	private String plan;
	/** 人员 */
	private String person;
	/** 耗时 */
	private String costTime;
	/** 农机id */
	private Long machineId;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/** 更新时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;

	public void setMachineRecordId(Long machineRecordId) 
	{
		this.machineRecordId = machineRecordId;
	}

	public Long getMachineRecordId() 
	{
		return machineRecordId;
	}
	public void setPlan(String plan) 
	{
		this.plan = plan;
	}

	public String getPlan() 
	{
		return plan;
	}
	public void setPerson(String person) 
	{
		this.person = person;
	}

	public String getPerson() 
	{
		return person;
	}
	public void setCostTime(String costTime) 
	{
		this.costTime = costTime;
	}

	public String getCostTime() 
	{
		return costTime;
	}
	public void setMachineId(Long machineId) 
	{
		this.machineId = machineId;
	}

	public Long getMachineId() 
	{
		return machineId;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("machineRecordId", getMachineRecordId())
            .append("plan", getPlan())
            .append("person", getPerson())
            .append("costTime", getCostTime())
            .append("machineId", getMachineId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
