package org.zcy.agriculture.vo;

import org.zcy.agriculture.entity.TbResDevice;

public class TbResDeviceExtraVo extends TbResDevice {
    /** 地块名称 */
    private String plotName;

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }
}
