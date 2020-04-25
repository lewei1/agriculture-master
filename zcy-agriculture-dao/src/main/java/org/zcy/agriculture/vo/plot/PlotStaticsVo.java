package org.zcy.agriculture.vo.plot;

import org.zcy.agriculture.vo.BaseVo;

public class PlotStaticsVo extends BaseVo {

    private Integer plotQuantity;

    private Double muQuantity;

    public Integer getPlotQuantity() {
        return plotQuantity;
    }

    public void setPlotQuantity(Integer plotQuantity) {
        this.plotQuantity = plotQuantity;
    }

    public Double getMuQuantity() {
        return muQuantity;
    }

    public void setMuQuantity(Double muQuantity) {
        this.muQuantity = muQuantity;
    }
}
