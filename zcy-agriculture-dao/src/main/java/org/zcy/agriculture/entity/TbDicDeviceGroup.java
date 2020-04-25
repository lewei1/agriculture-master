package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备与分组关联表 tb_dic_device_group
 * 
 * @author zh
 * @date 2019-06-21
 */
public class TbDicDeviceGroup extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long devGroupId;
	/** 设备id */
	private Long devId;
	/** 所在分组id */
	private Long groupId;
	/** 是否有效（0有效，1无效） */
	private Integer status;

	public void setDevGroupId(Long devGroupId) 
	{
		this.devGroupId = devGroupId;
	}

	public Long getDevGroupId() 
	{
		return devGroupId;
	}
	public void setDevId(Long devId) 
	{
		this.devId = devId;
	}

	public Long getDevId() 
	{
		return devId;
	}
	public void setGroupId(Long groupId) 
	{
		this.groupId = groupId;
	}

	public Long getGroupId() 
	{
		return groupId;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("devGroupId", getDevGroupId())
            .append("devId", getDevId())
            .append("groupId", getGroupId())
            .append("status", getStatus())
            .toString();
    }
}
