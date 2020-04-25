package org.zcy.agriculture.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 采收规格表 tb_harvest_spec
 * 
 * @author numberone
 * @date 2019-06-27
 */
public class TbHarvestSpec extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long harvestSpecId;
	/** 规格名称 */
	private String specName;
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

	public void setHarvestSpecId(Long harvestSpecId) 
	{
		this.harvestSpecId = harvestSpecId;
	}

	public Long getHarvestSpecId() 
	{
		return harvestSpecId;
	}
	public void setSpecName(String specName) 
	{
		this.specName = specName;
	}

	public String getSpecName() 
	{
		return specName;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("harvestSpecId", getHarvestSpecId())
            .append("specName", getSpecName())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("farmId", getFarmId())
            .toString();
    }
}
