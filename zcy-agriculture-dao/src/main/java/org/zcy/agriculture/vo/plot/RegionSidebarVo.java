package org.zcy.agriculture.vo.plot;

import org.zcy.agriculture.vo.BaseVo;

public class RegionSidebarVo extends BaseVo {

    private Long regionId;

    private String regionName;

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
