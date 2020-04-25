package org.zcy.agriculture.vo;

import org.zcy.agriculture.entity.TbPlanMaterial;

/**
 * 计划使用的物品表 tb_plan_material
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbPlanMaterialVo extends TbPlanMaterial {
	private static final long serialVersionUID = 4838083364407408316L;
	private String warehouseName;// 仓库名称
	private String materialName;// 物品名称

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

}
