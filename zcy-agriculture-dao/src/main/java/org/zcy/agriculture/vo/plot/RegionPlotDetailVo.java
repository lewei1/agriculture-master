package org.zcy.agriculture.vo.plot;

import org.zcy.agriculture.entity.TbPlotInfo;
import org.zcy.agriculture.vo.BaseVo;

import java.util.List;

public class  RegionPlotDetailVo extends BaseVo {

    private String regionName;

    private List<TbPlotInfo> plotList;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<TbPlotInfo> getPlotList() {
        return plotList;
    }

    public void setPlotList(List<TbPlotInfo> plotList) {
        this.plotList = plotList;
    }
}
