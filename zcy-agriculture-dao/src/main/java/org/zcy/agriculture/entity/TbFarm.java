package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 农场(基础)表 tb_farm
 * 
 * @author numberone
 * @date 2019-06-25
 */
public class TbFarm extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 农场id,UUID */
	private String farmId;
	/** 名称 */
	private String farmName;
	/** 国家 */
	private String country;
	/** 地区 */
	private String region;
	/** 序列 */
	private Integer farmSequence;
	/** 状态 */
	private Integer farmStatus;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long createBy;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private Long updateBy;

	/**
	 * 纬度
	 */
	private BigDecimal latitude;
	/**
	 * 经度
	 */
	private BigDecimal longitude;



	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public void setFarmId(String farmId)
	{
		this.farmId = farmId;
	}

	public String getFarmId() 
	{
		return farmId;
	}
	public void setFarmName(String farmName) 
	{
		this.farmName = farmName;
	}

	public String getFarmName() 
	{
		return farmName;
	}
	public void setCountry(String country) 
	{
		this.country = country;
	}

	public String getCountry() 
	{
		return country;
	}
	public void setRegion(String region) 
	{
		this.region = region;
	}

	public String getRegion() 
	{
		return region;
	}
	public void setFarmSequence(Integer farmSequence) 
	{
		this.farmSequence = farmSequence;
	}

	public Integer getFarmSequence() 
	{
		return farmSequence;
	}
	public void setFarmStatus(Integer farmStatus) 
	{
		this.farmStatus = farmStatus;
	}

	public Integer getFarmStatus() 
	{
		return farmStatus;
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

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("farmId", getFarmId())
            .append("farmName", getFarmName())
            .append("country", getCountry())
            .append("region", getRegion())
            .append("farmSequence", getFarmSequence())
            .append("farmStatus", getFarmStatus())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
