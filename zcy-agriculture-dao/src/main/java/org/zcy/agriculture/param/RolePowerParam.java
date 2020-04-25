package org.zcy.agriculture.param;

import org.zcy.agriculture.entity.TbReportImg;

import java.util.List;

/**
 * 角色与权限参数
 */
public class RolePowerParam extends BaseParam {
    private Long roleId;
    private String name;
    private List<Long> powerIdList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getPowerIdList() {
        return powerIdList;
    }

    public void setPowerIdList(List<Long> powerIdList) {
        this.powerIdList = powerIdList;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
