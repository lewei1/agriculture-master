package org.zcy.agriculture.entity;


/**
 * 计划使用的物品表 tb_plan_material
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbPlanMaterial extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long planMaterialId;
	/** 仓库id */
	private Long warehouseId;
	/** 物品id */
	private Long materialId;
	/** 数量 */
	private Integer materialAmount;
	/** 物品单位 */
	private String materialUnit;
	/** 计划id */
	private Long planId;

	public void setPlanMaterialId(Long planMaterialId) 
	{
		this.planMaterialId = planMaterialId;
	}

	public Long getPlanMaterialId() 
	{
		return planMaterialId;
	}
	public void setWarehouseId(Long warehouseId) 
	{
		this.warehouseId = warehouseId;
	}

	public Long getWarehouseId() 
	{
		return warehouseId;
	}
	public void setMaterialId(Long materialId) 
	{
		this.materialId = materialId;
	}

	public Long getMaterialId() 
	{
		return materialId;
	}
	public void setMaterialAmount(Integer materialAmount) 
	{
		this.materialAmount = materialAmount;
	}

	public Integer getMaterialAmount() 
	{
		return materialAmount;
	}
	public void setMaterialUnit(String materialUnit) 
	{
		this.materialUnit = materialUnit;
	}

	public String getMaterialUnit() 
	{
		return materialUnit;
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
