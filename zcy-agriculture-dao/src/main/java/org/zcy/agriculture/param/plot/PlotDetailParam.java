package org.zcy.agriculture.param.plot;

import org.zcy.agriculture.entity.TbPlot;
import org.zcy.agriculture.entity.TbPlotCoordinate;

import java.io.Serializable;
import java.util.List;

public class PlotDetailParam extends TbPlot {

    private List<TbPlotCoordinate> coordinateList;

    public List<TbPlotCoordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<TbPlotCoordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }
}
