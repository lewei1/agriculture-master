package org.zcy.agriculture.entity;


/**
 * 农事计划参与者(包括负责人和成员)表 tb_plan_participant
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbPlanParticipant extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long planUserId;
	/** 责任人 */
	private Long responserId;
	/** 参与人id */
	private Long participantId;
	/** 计划id */
	private Long planId;
	/** 子地块ID */
	private Long subPlotId;

	public void setPlanUserId(Long planUserId) 
	{
		this.planUserId = planUserId;
	}

	public Long getPlanUserId() 
	{
		return planUserId;
	}
	
	public Long getResponserId() {
		return responserId;
	}

	public void setResponserId(Long responserId) {
		this.responserId = responserId;
	}

	public Long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}

	public void setPlanId(Long planId) 
	{
		this.planId = planId;
	}

	public Long getPlanId() 
	{
		return planId;
	}

	public Long getSubPlotId() {
		return subPlotId;
	}

	public void setSubPlotId(Long subPlotId) {
		this.subPlotId = subPlotId;
	}

	
}
