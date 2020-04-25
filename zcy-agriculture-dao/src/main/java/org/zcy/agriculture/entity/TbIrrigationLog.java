package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 灌溉操作日志表 tb_irrigation_log
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbIrrigationLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long actionLogId;
	/** 操作类型，M-手动，A-自动 */
	private String actionType;
	/** 操作内容 */
	private String actionContent;
	/** 操作分组名 */
	private String groupName;
	/** 操作时间 */
	private Date actionTime;
	/** 操作人 */
	private Long actionBy;
	/** 农场id */
	private String farmId;

	public Long getActionLogId() {
		return actionLogId;
	}

	public void setActionLogId(Long actionLogId) {
		this.actionLogId = actionLogId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getActionContent() {
		return actionContent;
	}

	public void setActionContent(String actionContent) {
		this.actionContent = actionContent;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public Long getActionBy() {
		return actionBy;
	}

	public void setActionBy(Long actionBy) {
		this.actionBy = actionBy;
	}

	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}
}
