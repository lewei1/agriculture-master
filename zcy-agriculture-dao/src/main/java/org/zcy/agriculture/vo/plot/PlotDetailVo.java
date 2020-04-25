package org.zcy.agriculture.vo.plot;

import org.zcy.agriculture.entity.TbPlot;
import org.zcy.agriculture.entity.TbPlotCoordinate;

import java.util.List;

public class PlotDetailVo extends TbPlot {
    /** 类型名 */
    private String plotTypeName;

    //负责人
    private String responser;
    //地块坐标信息
    private List<TbPlotCoordinate> coordinateList;

    public String getResponser() {
        return responser;
    }

    public void setResponser(String responser) {
        this.responser = responser;
    }

    public List<TbPlotCoordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<TbPlotCoordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    public String getPlotTypeName() {
        return plotTypeName;
    }

    public void setPlotTypeName(String plotTypeName) {
        this.plotTypeName = plotTypeName;
    }
}
