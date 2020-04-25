package org.zcy.agriculture.param.irrigation;

import org.zcy.agriculture.entity.TbIrrigationFixQuantity;

import java.io.Serializable;
import java.util.List;

public class IrrigationFixQuantityDetailParam implements Serializable {

    private Long groupId;

    private List<TbIrrigationFixQuantity> fixQuantityList;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<TbIrrigationFixQuantity> getFixQuantityList() {
        return fixQuantityList;
    }

    public void setFixQuantityList(List<TbIrrigationFixQuantity> fixQuantityList) {
        this.fixQuantityList = fixQuantityList;
    }
}
