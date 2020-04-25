package org.zcy.agriculture.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 农作物品种表 tb_crop_type
 * 
 * @author numberone
 * @date 2019-06-26
 */
public class TbCropType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long cropTypeId;
	/** 作物名称 */
	private String cropName;
	/** 作物状态 */
	private Integer cropStatus;
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
	/** 农作物种类表ID */
	private Long cropCategoryId;

	public void setCropTypeId(Long cropTypeId) 
	{
		this.cropTypeId = cropTypeId;
	}

	public Long getCropTypeId() 
	{
		return cropTypeId;
	}
	public void setCropName(String cropName) 
	{
		this.cropName = cropName;
	}

	public String getCropName() 
	{
		return cropName;
	}
	public void setCropStatus(Integer cropStatus) 
	{
		this.cropStatus = cropStatus;
	}

	public Integer getCropStatus() 
	{
		return cropStatus;
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
	public void setCropCategoryId(Long cropCategoryId) 
	{
		this.cropCategoryId = cropCategoryId;
	}

	public Long getCropCategoryId() 
	{
		return cropCategoryId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cropTypeId", getCropTypeId())
            .append("cropName", getCropName())
            .append("cropStatus", getCropStatus())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("farmId", getFarmId())
            .append("cropCategoryId", getCropCategoryId())
            .toString();
    }
}
