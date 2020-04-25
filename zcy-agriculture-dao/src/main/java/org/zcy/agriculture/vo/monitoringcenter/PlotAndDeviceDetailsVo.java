package org.zcy.agriculture.vo.monitoringcenter;

import org.zcy.agriculture.vo.TbPlantingVo;

import java.util.HashMap;
import java.util.List;

public class PlotAndDeviceDetailsVo {
    List<TbPlantingVo> listPlanting;
    HashMap<String, Object> todayHarvest;
    HashMap<String, Object> farmingPlan;
    AlarmInfo alarmInfo;
    /**子地块数量*/
    Long subPlotNum;
    /**管理人员数量*/
    Long subPlotPersonNum;
    /**检测设备数量*/
    Long monitorDevNum;

    public HashMap<String, Object> getTodayHarvest() {
        return todayHarvest;
    }

    public void setTodayHarvest(HashMap<String, Object> todayHarvest) {
        this.todayHarvest = todayHarvest;
    }

    public AlarmInfo getAlarmInfo() {
        return alarmInfo;
    }

    public void setAlarmInfo(AlarmInfo alarmInfo) {
        this.alarmInfo = alarmInfo;
    }

    public Long getSubPlotNum() {
        return subPlotNum;
    }

    public void setSubPlotNum(Long subPlotNum) {
        this.subPlotNum = subPlotNum;
    }

    public Long getSubPlotPersonNum() {
        return subPlotPersonNum;
    }

    public void setSubPlotPersonNum(Long subPlotPersonNum) {
        this.subPlotPersonNum = subPlotPersonNum;
    }

    public Long getMonitorDevNum() {
        return monitorDevNum;
    }

    public void setMonitorDevNum(Long monitorDevNum) {
        this.monitorDevNum = monitorDevNum;
    }

    public List<TbPlantingVo> getListPlanting() {
        return listPlanting;
    }

    public void setListPlanting(List<TbPlantingVo> listPlanting) {
        this.listPlanting = listPlanting;
    }

    public HashMap<String, Object> getFarmingPlan() {
        return farmingPlan;
    }

    public void setFarmingPlan(HashMap<String, Object> farmingPlan) {
        this.farmingPlan = farmingPlan;
    }
}
