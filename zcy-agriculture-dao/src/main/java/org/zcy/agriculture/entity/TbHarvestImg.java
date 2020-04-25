package org.zcy.agriculture.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 采收对应图片表 tb_harvest_img
 * 
 * @author numberone
 * @date 2019-06-27
 */
public class TbHarvestImg extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long harvestImgId;
	/** 图片url */
	private String imgUrl;
	/** 序列 */
	private Integer imgSequence;
	/** 采收id */
	private Long harvestId;

	public void setHarvestImgId(Long harvestImgId) 
	{
		this.harvestImgId = harvestImgId;
	}

	public Long getHarvestImgId() 
	{
		return harvestImgId;
	}
	public void setImgUrl(String imgUrl) 
	{
		this.imgUrl = imgUrl;
	}

	public String getImgUrl() 
	{
		return imgUrl;
	}
	public void setImgSequence(Integer imgSequence) 
	{
		this.imgSequence = imgSequence;
	}

	public Integer getImgSequence() 
	{
		return imgSequence;
	}
	public void setHarvestId(Long harvestId) 
	{
		this.harvestId = harvestId;
	}

	public Long getHarvestId() 
	{
		return harvestId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("harvestImgId", getHarvestImgId())
            .append("imgUrl", getImgUrl())
            .append("imgSequence", getImgSequence())
            .append("harvestId", getHarvestId())
            .toString();
    }
}
