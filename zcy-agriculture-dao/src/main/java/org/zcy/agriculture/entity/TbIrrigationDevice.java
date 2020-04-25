package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 灌溉分组设备中间表 tb_irrigation_device
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbIrrigationDevice extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 设备id */
	private Long deviceId;
	/** 灌溉分组id */
	private Long groupId;

	public void setDeviceId(Long deviceId) 
	{
		this.deviceId = deviceId;
	}

	public Long getDeviceId() 
	{
		return deviceId;
	}
	public void setGroupId(Long groupId) 
	{
		this.groupId = groupId;
	}

	public Long getGroupId() 
	{
		return groupId;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("deviceId", getDeviceId())
            .append("groupId", getGroupId())
            .toString();
    }
}
