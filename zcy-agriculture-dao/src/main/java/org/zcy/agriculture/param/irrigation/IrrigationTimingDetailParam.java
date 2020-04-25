package org.zcy.agriculture.param.irrigation;

import org.zcy.agriculture.entity.TbIrrigationTiming;

import java.io.Serializable;
import java.util.List;

public class IrrigationTimingDetailParam implements Serializable {

    private Long groupId;

    private List<TbIrrigationTiming> timingList;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<TbIrrigationTiming> getTimingList() {
        return timingList;
    }

    public void setTimingList(List<TbIrrigationTiming> timingList) {
        this.timingList = timingList;
    }
}
