package org.zcy.agriculture.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zcy.agriculture.constants.Excel;
import org.zcy.agriculture.util.DateJsonSerializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FarmingRecordVo implements Serializable {
	private static final long serialVersionUID = 2092225924165605361L;
	@Excel(name = "农事类型名称")
	private String farmingTypeName;
	private Long plotId;
	@Excel(name = "地块名称")
	private String plotName;
	private Long subPlotId;
	@Excel(name = "子地块名称")
	private String subPlotName;
	@JsonSerialize(using = DateJsonSerializer.class)
	private Date startTime;
	@JsonSerialize(using = DateJsonSerializer.class)
	private Date endTime;
	private Long planId;
	@JsonSerialize(using = DateJsonSerializer.class)
	private Date updateTime;
	@Excel(name = "记录时间",dateFormat = "yyyy-MM-dd")
	@JsonSerialize(using = DateJsonSerializer.class)
	private Date createTime;
	private Integer planStatus;
	@Excel(name = "使用物品")
	private String name;
	@Excel(name = "劳作记录",readConverterExp = "1=施肥记录,2=用药记录,3=劳作记录")
	private Integer tpyeRecord;
	@Excel(name = "参与者")
	private String userName;
	private Integer msgCou;
	@Excel(name = "花费工时")
	private String workingHours;
	private List<HashMap<String, Object>> users;
	private Integer pageNo;// 数据库查询开始条数（分组的时候不建议用自带的分页）
	private Integer pageSize;// 数据库查询条数
	private String createTimeStr;// 查询开始时间
	private String createTimeEnd;// 查询结束时间
	private String farmId;
	
	private Integer conditionVar;//传感器表达式
	private String dataVar;//传感器的值
	private String devName;//智能计划设备名称
	
	private Long plantingId;//种植ID
	private String nsTimeStr;// 种植查询开始时间
	private String nsTimeEnd;// 种植查询结束时间

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

	public String getFarmingTypeName() {
		return farmingTypeName;
	}

	public void setFarmingTypeName(String farmingTypeName) {
		this.farmingTypeName = farmingTypeName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTpyeRecord() {
		return tpyeRecord;
	}

	public void setTpyeRecord(Integer tpyeRecord) {
		this.tpyeRecord = tpyeRecord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMsgCou() {
		return msgCou;
	}

	public void setMsgCou(Integer msgCou) {
		this.msgCou = msgCou;
	}

	public List<HashMap<String, Object>> getUsers() {
		return users;
	}

	public void setUsers(List<HashMap<String, Object>> users) {
		this.users = users;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

	public Long getPlotId() {
		return plotId;
	}

	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}

	public Integer getConditionVar() {
		return conditionVar;
	}

	public void setConditionVar(Integer conditionVar) {
		this.conditionVar = conditionVar;
	}

	public String getDataVar() {
		return dataVar;
	}

	public void setDataVar(String dataVar) {
		this.dataVar = dataVar;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Long getSubPlotId() {
		return subPlotId;
	}

	public void setSubPlotId(Long subPlotId) {
		this.subPlotId = subPlotId;
	}

	public String getNsTimeStr() {
		return nsTimeStr;
	}

	public void setNsTimeStr(String nsTimeStr) {
		this.nsTimeStr = nsTimeStr;
	}

	public String getNsTimeEnd() {
		return nsTimeEnd;
	}

	public void setNsTimeEnd(String nsTimeEnd) {
		this.nsTimeEnd = nsTimeEnd;
	}

	public Long getPlantingId() {
		return plantingId;
	}

	public void setPlantingId(Long plantingId) {
		this.plantingId = plantingId;
	}
	
}
