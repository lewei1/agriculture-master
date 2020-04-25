package org.zcy.agriculture.vo.plot;

import org.zcy.agriculture.vo.BaseVo;

import java.util.List;

public class RegionPlotSidebarVo extends BaseVo {

    private String regionName;

    private List<PlotSidebarVo> plotList;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<PlotSidebarVo> getPlotList() {
        return plotList;
    }

    public void setPlotList(List<PlotSidebarVo> plotList) {
        this.plotList = plotList;
    }
}
