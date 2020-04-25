package org.zcy.agriculture.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 农作物种类表 tb_crop_category
 * 
 * @author numberone
 * @date 2019-07-19
 */
public class TbCropCategory extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long cropCategoryId;
	/** 作物名称 */
	private String categoryName;
	/** 作物状态 */
	private Integer categoryStatus;
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
	/** 农作物样本图片地址 */
	private String samplePicture;

	public void setCropCategoryId(Long cropCategoryId) 
	{
		this.cropCategoryId = cropCategoryId;
	}

	public Long getCropCategoryId() 
	{
		return cropCategoryId;
	}
	public void setCategoryName(String categoryName) 
	{
		this.categoryName = categoryName;
	}

	public String getCategoryName() 
	{
		return categoryName;
	}
	public void setCategoryStatus(Integer categoryStatus) 
	{
		this.categoryStatus = categoryStatus;
	}

	public Integer getCategoryStatus() 
	{
		return categoryStatus;
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
	public void setSamplePicture(String samplePicture) 
	{
		this.samplePicture = samplePicture;
	}

	public String getSamplePicture() 
	{
		return samplePicture;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cropCategoryId", getCropCategoryId())
            .append("categoryName", getCategoryName())
            .append("categoryStatus", getCategoryStatus())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("farmId", getFarmId())
            .append("samplePicture", getSamplePicture())
            .toString();
    }
}
