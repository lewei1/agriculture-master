package org.zcy.agriculture.param;

import java.io.Serializable;

/**
 * 视频直播 参数
 * 
 */
public class LiveStreamingParam implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/** 设备ID */
	private Long devId;
	/** 设备名 */
	private String devName;
	/** 设备序列号 */
	private String devNum;
	/** 地块id  */
	private Long plotId;
	/** 设备类型 4是摄像头 */
	private static final Integer devType  = 4 ;
	/** 状态（0正报警，1运行中，2断开，3待机，4其它） */
	private Integer status;
	/** 农场ID */
	private String farmId;



	public static Integer getDevType() {
		return devType;
	}

	public String getDevNum() {
		return devNum;
	}

	public void setDevNum(String devNum) {
		this.devNum = devNum;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Long getPlotId() {
		return plotId;
	}

	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

}
