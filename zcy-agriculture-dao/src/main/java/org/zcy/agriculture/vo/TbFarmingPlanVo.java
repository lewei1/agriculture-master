package org.zcy.agriculture.vo;

import org.zcy.agriculture.entity.TbFarmingPlan;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 农事计划（普通计划，智能计划）表 tb_farming_plan
 * 
 * @author numberone
 * @date 2019-07-01
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TbFarmingPlanVo extends TbFarmingPlan {
	private static final long serialVersionUID = -1770662776082701251L;
	private String farmingTypeName;// 农事类型名称
	private Long plotId;//地块ID
	private String plotName;// 地块名称
	private String subPlotId;//子地块ID
	private String subPlotName;// 子地块名称
	private Integer hangAir;// 是否未指派 0未指派，大于0 已经指派
	private String today;// 当前日期
	private String [] machineIds;//农机ID  多个
	private String [] machineNames;//农机名称 过个
	private Long currentUserCode;//当前登录的用户编码
	
	public String getFarmingTypeName() {
		return farmingTypeName;
	}

	public void setFarmingTypeName(String farmingTypeName) {
		this.farmingTypeName = farmingTypeName;
	}

	public Integer getHangAir() {
		return hangAir;
	}

	public void setHangAir(Integer hangAir) {
		this.hangAir = hangAir;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getPlotName() {
		return plotName;
	}

	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}

	public String getSubPlotName() {
		return subPlotName;
	}

	public void setSubPlotName(String subPlotName) {
		this.subPlotName = subPlotName;
	}

	public Long getPlotId() {
		return plotId;
	}

	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}

	public String[] getMachineIds() {
		return machineIds;
	}

	public void setMachineIds(String[] machineIds) {
		this.machineIds = machineIds;
	}

	public String[] getMachineNames() {
		return machineNames;
	}

	public void setMachineNames(String[] machineNames) {
		this.machineNames = machineNames;
	}

	public Long getCurrentUserCode() {
		return currentUserCode;
	}

	public void setCurrentUserCode(Long currentUserCode) {
		this.currentUserCode = currentUserCode;
	}

	public String getSubPlotId() {
		return subPlotId;
	}

	public void setSubPlotId(String subPlotId) {
		this.subPlotId = subPlotId;
	}
	
}
