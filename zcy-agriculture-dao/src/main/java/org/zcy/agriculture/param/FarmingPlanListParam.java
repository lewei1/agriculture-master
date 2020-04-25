package org.zcy.agriculture.param;

import java.io.Serializable;
import java.util.Date;

public class FarmingPlanListParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1363068271277721030L;
	
	//地块ID
	private Long plotId;

	//开始时间
	private Date StartTime;
	
	//结束时间
	private Date endTime;
	
	//农场ID
	private String farmId; 

	public Long getPlotId() {
		return plotId;
	}

	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}
	
	
}
