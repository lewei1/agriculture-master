package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 农机类型表 tb_am_type
 * 
 * @author linlq
 * @date 2019-06-21
 */
public class TbAmType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 农机类型ID */
	private Long machineTypeId;
	/** 农场id,UUID */
	private String farmId;
	/** 类型名称 */
	private String typeName;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;

	public void setMachineTypeId(Long machineTypeId) 
	{
		this.machineTypeId = machineTypeId;
	}

	public Long getMachineTypeId() 
	{
		return machineTypeId;
	}
	public void setFarmId(String farmId) 
	{
		this.farmId = farmId;
	}

	public String getFarmId() 
	{
		return farmId;
	}
	public void setTypeName(String typeName) 
	{
		this.typeName = typeName;
	}

	public String getTypeName() 
	{
		return typeName;
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
            .append("machineTypeId", getMachineTypeId())
            .append("farmId", getFarmId())
            .append("typeName", getTypeName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
