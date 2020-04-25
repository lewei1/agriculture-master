package org.zcy.agriculture.vo.plot;

import org.zcy.agriculture.vo.BaseVo;

public class PlotSidebarVo extends BaseVo {

    private String plotName;

    private Long plotId;

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }
}
