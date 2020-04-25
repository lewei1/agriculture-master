package org.zcy.agriculture.entity;


/**
 * 智能计划执行表 tb_farming_plan_implement
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbFarmingPlanImplement extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**  */
	private Long id;
	/** 农事计划ID */
	private Long planId;
	/**  */
	private Long devId;
	/**  */
	private String thingsboardKey;
	/**  */
	private Integer conditionVar;
	/**  */
	private String dataVar;
	/**  */
	private Integer status;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public Long getDevId() {
		return devId;
	}

	public String getThingsboardKey() {
		return thingsboardKey;
	}

	public void setThingsboardKey(String thingsboardKey) {
		this.thingsboardKey = thingsboardKey;
	}

	public void setConditionVar(Integer conditionVar) {
		this.conditionVar = conditionVar;
	}

	public Integer getConditionVar() {
		return conditionVar;
	}

	public void setDataVar(String dataVar) {
		this.dataVar = dataVar;
	}

	public String getDataVar() {
		return dataVar;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

}
