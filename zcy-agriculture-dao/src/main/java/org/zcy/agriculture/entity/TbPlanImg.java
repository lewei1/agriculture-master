package org.zcy.agriculture.entity;


/**
 * 计划对应图片表 tb_plan_img
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbPlanImg extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long planImgId;
	/** 图片url */
	private String imgUrl;
	/** 序列 */
	private Integer imgSequence;
	/** 计划id */
	private Long planId;

	public void setPlanImgId(Long planImgId) 
	{
		this.planImgId = planImgId;
	}

	public Long getPlanImgId() 
	{
		return planImgId;
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
	public void setPlanId(Long planId) 
	{
		this.planId = planId;
	}

	public Long getPlanId() 
	{
		return planId;
	}

}
