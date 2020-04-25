package org.zcy.agriculture.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 权限表 tb_res_power
 * 
 * @author zh
 * @date 2019-07-02
 */
public class TbResPower extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long powerId;
	/** 权限编码 */
	private String powerCode;
	/** 农场id,UUID */
	private String farmId;
	/** 中文名 */
	private String name;
	/** 状态（0有效，1无效） */
	private Integer status;
	/** 父类ID */
	private Long parentId;
	/** 接口uri */
	private String uri;
	/** (1-第一层级(分类作用),2-第二层级(菜单),3-第三层级(按钮),4-第四层级(接口),接口是挂在按钮下) */
	private Integer level;


	public void setPowerId(Long powerId) 
	{
		this.powerId = powerId;
	}

	public Long getPowerId() 
	{
		return powerId;
	}
	public void setPowerCode(String powerCode) 
	{
		this.powerCode = powerCode;
	}

	public String getPowerCode() 
	{
		return powerCode;
	}
	public void setFarmId(String farmId) 
	{
		this.farmId = farmId;
	}

	public String getFarmId() 
	{
		return farmId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("powerId", getPowerId())
            .append("powerCode", getPowerCode())
            .append("farmId", getFarmId())
            .append("name", getName())
            .append("status", getStatus())
			.append("parentId", getParentId())
			.append("uri", getUri())
			.append("level", getLevel())
            .toString();
    }
}
