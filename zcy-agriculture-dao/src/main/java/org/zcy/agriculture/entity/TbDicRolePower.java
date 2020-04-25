package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色权限关联表 tb_dic_role_power
 * 
 * @author zh
 * @date 2019-07-02
 */
public class TbDicRolePower extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 角色ID */
	private Long roleId;
	/** 权限ID */
	private Long powerId;

	public void setRoleId(Long roleId) 
	{
		this.roleId = roleId;
	}

	public Long getRoleId() 
	{
		return roleId;
	}
	public void setPowerId(Long powerId) 
	{
		this.powerId = powerId;
	}

	public Long getPowerId() 
	{
		return powerId;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("powerId", getPowerId())
            .toString();
    }
}
