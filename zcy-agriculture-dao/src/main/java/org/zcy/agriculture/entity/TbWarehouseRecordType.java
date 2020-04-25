package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 出入库类型表 tb_warehouse_record_type
 * 
 * @author linlq
 * @date 2019-06-28
 */
public class TbWarehouseRecordType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 出入库类型ID */
	private Long inOutTypeId;
	/** 出入库操作类型,(O - 出库，I - 入库) */
	private String inOutType;
	/** 名称 */
	private String name;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long createBy;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private Long updateBy;
	/** 农场id,UUID */
	private String farmId;

	public void setInOutTypeId(Long inOutTypeId) 
	{
		this.inOutTypeId = inOutTypeId;
	}

	public Long getInOutTypeId() 
	{
		return inOutTypeId;
	}
	public void setInOutType(String inOutType) 
	{
		this.inOutType = inOutType;
	}

	public String getInOutType() 
	{
		return inOutType;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
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
            .append("inOutTypeId", getInOutTypeId())
            .append("inOutType", getInOutType())
            .append("name", getName())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("farmId", getFarmId())
            .toString();
    }
}
