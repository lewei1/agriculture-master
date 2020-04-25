package org.zcy.agriculture.entity;


/**
 * 计划对应的地块表 tb_plan_plot
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbPlanPlot extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long planPlotId;
	/** 地块id */
	private Long plotId;
	/** 子地块id */
	private Long subPlotId;
	/** 计划id */
	private Long planId;

	public void setPlanPlotId(Long planPlotId) 
	{
		this.planPlotId = planPlotId;
	}

	public Long getPlanPlotId() 
	{
		return planPlotId;
	}
	public void setPlotId(Long plotId) 
	{
		this.plotId = plotId;
	}

	public Long getPlotId() 
	{
		return plotId;
	}
	public void setSubPlotId(Long subPlotId) 
	{
		this.subPlotId = subPlotId;
	}

	public Long getSubPlotId() 
	{
		return subPlotId;
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
