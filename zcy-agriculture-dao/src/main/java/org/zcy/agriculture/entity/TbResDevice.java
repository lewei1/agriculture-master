package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 设备表 tb_res_device
 * 
 * @author numberone
 * @date 2019-07-22
 */
public class TbResDevice extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 设备ID */
	private Long devId;
	/** 设备名 */
	private String devName;
	/** 设备序列号 */
	private String devNum;
	/** 地块id */
	private Long plotId;
	/** 经度 */
	private Double lng;
	/** 纬度 */
	private Double lat;
	/** 剩余电量（比如0.32表示还有32%的电量） */
	private Float electricity;
	/** （0监测设备，1气象站，2自动化设备，4摄像头） */
	private Integer devType;
	/** 上次拍照时间（针对摄像头类型） */
	private Date lastPhotoTime;
	/** 拍摄间隔（以分钟计）--仅仅针对设备类型为摄像头类型 */
	private Float shootingInterval;
	/** 状态（0正报警，1运行中，2断开，3待机，4其它） */
	private Integer status;
	/** 农场ID */
	private String farmId;
	/** 创建时间 */
	private String createTime;

	public void setDevId(Long devId) 
	{
		this.devId = devId;
	}

	public Long getDevId() 
	{
		return devId;
	}
	public void setDevName(String devName) 
	{
		this.devName = devName;
	}

	public String getDevName() 
	{
		return devName;
	}
	public void setDevNum(String devNum) 
	{
		this.devNum = devNum;
	}

	public String getDevNum() 
	{
		return devNum;
	}
	public void setPlotId(Long plotId) 
	{
		this.plotId = plotId;
	}

	public Long getPlotId() 
	{
		return plotId;
	}
	public void setLng(Double lng) 
	{
		this.lng = lng;
	}

	public Double getLng() 
	{
		return lng;
	}
	public void setLat(Double lat) 
	{
		this.lat = lat;
	}

	public Double getLat() 
	{
		return lat;
	}
	public void setElectricity(Float electricity) 
	{
		this.electricity = electricity;
	}

	public Float getElectricity() 
	{
		return electricity;
	}
	public void setDevType(Integer devType) 
	{
		this.devType = devType;
	}

	public Integer getDevType() 
	{
		return devType;
	}
	public void setLastPhotoTime(Date lastPhotoTime) 
	{
		this.lastPhotoTime = lastPhotoTime;
	}

	public Date getLastPhotoTime() 
	{
		return lastPhotoTime;
	}
	public void setShootingInterval(Float shootingInterval) 
	{
		this.shootingInterval = shootingInterval;
	}

	public Float getShootingInterval() 
	{
		return shootingInterval;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setFarmId(String farmId) 
	{
		this.farmId = farmId;
	}

	public String getFarmId() 
	{
		return farmId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("devId", getDevId())
            .append("devName", getDevName())
            .append("devNum", getDevNum())
            .append("plotId", getPlotId())
            .append("lng", getLng())
            .append("lat", getLat())
            .append("electricity", getElectricity())
            .append("devType", getDevType())
            .append("lastPhotoTime", getLastPhotoTime())
            .append("shootingInterval", getShootingInterval())
            .append("status", getStatus())
            .append("farmId", getFarmId())
			.append("createTime", getCreateTime())
            .toString();
    }


}
