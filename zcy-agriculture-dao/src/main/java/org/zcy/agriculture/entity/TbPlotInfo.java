package org.zcy.agriculture.entity;

import org.zcy.agriculture.vo.TbPlantingVo;

import java.util.List;

public class TbPlotInfo extends TbPlot {
    /** 类型 */
    private String plotTypeName;

    private List<TbPlantingVo> plantingVoList;

    private List<TbPlotCoordinate> coordinateList;

    public List<TbPlantingVo> getPlantingVoList() {
        return plantingVoList;
    }

    public void setPlantingVoList(List<TbPlantingVo> plantingVoList) {
        this.plantingVoList = plantingVoList;
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
