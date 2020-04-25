package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 摄像头拍照记录表 tb_camera_image
 * 
 * @author zh
 * @date 2019-06-21
 */
public class TbCameraImage extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 图像ID */
	private Long picId;
	/** 对应设备id */
	private Long devId;
	/** 存储位置 */
	private String loc;
	/** 创建时间 */
	private Date createTime;
	/** 农场id,UUID */
	private String farmId;

	public void setPicId(Long picId) 
	{
		this.picId = picId;
	}

	public Long getPicId() 
	{
		return picId;
	}
	public void setDevId(Long devId) 
	{
		this.devId = devId;
	}

	public Long getDevId() 
	{
		return devId;
	}
	public void setLoc(String loc) 
	{
		this.loc = loc;
	}

	public String getLoc() 
	{
		return loc;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setFarmId(String farmId) 
	{
		this.farmId = farmId;
	}

	public String getFarmId() 
	{
		return farmId;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("picId", getPicId())
            .append("devId", getDevId())
            .append("loc", getLoc())
            .append("createTime", getCreateTime())
            .append("farmId", getFarmId())
            .toString();
    }
}
