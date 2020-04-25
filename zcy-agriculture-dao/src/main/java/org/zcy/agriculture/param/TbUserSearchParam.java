package org.zcy.agriculture.param;

import org.zcy.agriculture.entity.TbUser;

public class TbUserSearchParam extends TbUser {
    private String searchWords;

    private String roleId;

    private String farmId;

    public String getSearchWords() {
        return searchWords;
    }

    public void setSearchWords(String searchWords) {
        this.searchWords = searchWords;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }
}
