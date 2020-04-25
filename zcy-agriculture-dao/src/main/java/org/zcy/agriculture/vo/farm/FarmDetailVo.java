package org.zcy.agriculture.vo.farm;

import org.zcy.agriculture.entity.TbFarm;

public class FarmDetailVo extends TbFarm {
    //当前报警
    private Integer currentAlarm;
    //我的农事
    private Integer myFarming;
    //角色名称
    private String name;
    //角色id
    private Long roleId;

    public Integer getCurrentAlarm() {
        return currentAlarm;
    }

    public void setCurrentAlarm(Integer currentAlarm) {
        this.currentAlarm = currentAlarm;
    }

    public Integer getMyFarming() {
        return myFarming;
    }

    public void setMyFarming(Integer myFarming) {
        this.myFarming = myFarming;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
