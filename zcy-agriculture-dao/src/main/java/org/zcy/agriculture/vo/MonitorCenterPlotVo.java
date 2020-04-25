package org.zcy.agriculture.vo;

import java.util.List;

public class MonitorCenterPlotVo {

    /** 主键id */
    private Long plotId;
    /** 地块名称 */
    private String plotName;
    /** 地区id */
    private Long regionId;
    /** 地块面积 */
    private Double plotAcreage;
    /** 地块颜色 */
    private String plotColor;
    /** 类型 */
    private String plotType;
    /** 类型对应名称 */
    private String plotTypeName;
    /** 子地块数 */
    private Long subPlotNum;

    /** 设备数量 */
    private Long devNum;

    /** 设备名列表 */
    private List<String> devNameList;

    private List<CoordinateVo> list;

    /** 种植列表 */
    private List<TbPlantingVo> plantingList;

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public Double getPlotAcreage() {
        return plotAcreage;
    }

    public void setPlotAcreage(Double plotAcreage) {
        this.plotAcreage = plotAcreage;
    }

    public String getPlotColor() {
        return plotColor;
    }

    public void setPlotColor(String plotColor) {
        this.plotColor = plotColor;
    }

    public String getPlotType() {
        return plotType;
    }

    public void setPlotType(String plotType) {
        this.plotType = plotType;
    }

    public Long getSubPlotNum() {
        return subPlotNum;
    }

    public void setSubPlotNum(Long subPlotNum) {
        this.subPlotNum = subPlotNum;
    }

    public List<CoordinateVo> getList() {
        return list;
    }

    public void setList(List<CoordinateVo> list) {
        this.list = list;
    }

    public Long getDevNum() {
        return devNum;
    }

    public void setDevNum(Long devNum) {
        this.devNum = devNum;
    }

    public List<TbPlantingVo> getPlantingList() {
        return plantingList;
    }

    public void setPlantingList(List<TbPlantingVo> plantingList) {
        this.plantingList = plantingList;
    }

    public List<String> getDevNameList() {
        return devNameList;
    }

    public void setDevNameList(List<String> devNameList) {
        this.devNameList = devNameList;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getPlotTypeName() {
        return plotTypeName;
    }

    public void setPlotTypeName(String plotTypeName) {
        this.plotTypeName = plotTypeName;
    }
}
