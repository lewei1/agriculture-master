package org.zcy.agriculture.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.zcy.agriculture.entity.TbSubPlot;
import org.zcy.agriculture.entity.TbUser;
import java.util.List;

/**
 * 用户（成员）表 tb_user
 * 
 * @author zh
 * @date 2019-07-12
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TbUserWithSubPlotInfoVo extends TbUser {
	private static final long serialVersionUID = 3334147925787346496L;
	List<TbSubPlot> listSubPlot;

	private String roleName;

	private Long roleId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<TbSubPlot> getListSubPlot() {
		return listSubPlot;
	}

	public void setListSubPlot(List<TbSubPlot> listSubPlot) {
		this.listSubPlot = listSubPlot;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
