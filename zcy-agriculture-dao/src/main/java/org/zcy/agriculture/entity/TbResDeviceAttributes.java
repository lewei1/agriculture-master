package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备属性（每个设备包含的属性记录）表 tb_res_device_attributes
 * 
 * @author numberone
 * @date 2019-07-08
 */
public class TbResDeviceAttributes extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 设备ID */
	private Long devId;
	/** 属性名 */
	private String attributesName;
	/** 属性名中文名 */
	private String thingsboardKey;
	/** 设备类型列表(参考tb_alarm_threshold_types表) */
	private Integer typesIndex;
	private String unit;//单位
	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setDevId(Long devId) 
	{
		this.devId = devId;
	}

	public Long getDevId() 
	{
		return devId;
	}
	public void setAttributesName(String attributesName) 
	{
		this.attributesName = attributesName;
	}

	public String getAttributesName() 
	{
		return attributesName;
	}
	public void setThingsboardKey(String thingsboardKey) 
	{
		this.thingsboardKey = thingsboardKey;
	}

	public String getThingsboardKey() 
	{
		return thingsboardKey;
	}
	public void setTypesIndex(Integer typesIndex) 
	{
		this.typesIndex = typesIndex;
	}

	public Integer getTypesIndex() 
	{
		return typesIndex;
	}

    public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("devId", getDevId())
            .append("attributesName", getAttributesName())
            .append("thingsboardKey", getThingsboardKey())
            .append("typesIndex", getTypesIndex())
            .toString();
    }
}
