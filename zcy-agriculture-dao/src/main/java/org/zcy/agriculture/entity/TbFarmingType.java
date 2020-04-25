package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 农事类型表 tb_farming_type
 * 
 * @author numberone
 * @date 2019-06-26
 */
public class TbFarmingType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long farmingTypeId;
	/** 农事类型名称 */
	private String farmingTypeName;
	/** 类型状态 */
	private Integer farmingTypeStatus;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long createBy;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private Long updateBy;
	/** 农场id */
	private String farmId;
	private Integer tpyeRecord;

	public void setFarmingTypeId(Long farmingTypeId) 
	{
		this.farmingTypeId = farmingTypeId;
	}

	public Long getFarmingTypeId() 
	{
		return farmingTypeId;
	}
	public void setFarmingTypeName(String farmingTypeName) 
	{
		this.farmingTypeName = farmingTypeName;
	}

	public String getFarmingTypeName() 
	{
		return farmingTypeName;
	}
	public void setFarmingTypeStatus(Integer farmingTypeStatus) 
	{
		this.farmingTypeStatus = farmingTypeStatus;
	}

	public Integer getFarmingTypeStatus() 
	{
		return farmingTypeStatus;
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

    public Integer getTpyeRecord() {
		return tpyeRecord;
	}

	public void setTpyeRecord(Integer tpyeRecord) {
		this.tpyeRecord = tpyeRecord;
	}

	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("farmingTypeId", getFarmingTypeId())
            .append("farmingTypeName", getFarmingTypeName())
            .append("farmingTypeStatus", getFarmingTypeStatus())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("farmId", getFarmId())
            .toString();
    }
}
