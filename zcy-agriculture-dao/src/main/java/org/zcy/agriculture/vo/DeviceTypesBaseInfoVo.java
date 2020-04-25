package org.zcy.agriculture.vo;

import java.util.List;

/**
 * 设备各个类型数量、列表
 *
 * @author zh
 * @date 2019-06-24
 */

public class DeviceTypesBaseInfoVo extends BaseVo {
    /** 监测设备*/
    private Long monitorNum;
    List<DeviceBaseInfoVo> listMonitor;

    /** 气象站*/
    private Long weatherStationNum;
    List<DeviceBaseInfoVo> listWeatherStation;

    /** 灌溉设备*/
    private Long automationNum;
    List<DeviceBaseInfoVo> listAutomation;

//    /** 控制设备*/
//    private Long controllNum;
//    List<DeviceBaseInfoVo> listControll;

    private Long cameraNum;
    List<DeviceBaseInfoVo> listCamera;

    /** 总计*/
    private Long totalNum;

    public Long getWeatherStationNum() {
        return weatherStationNum;
    }

    public void setWeatherStationNum(Long weatherStationNum) {
        this.weatherStationNum = weatherStationNum;
    }

    public List<DeviceBaseInfoVo> getListWeatherStation() {
        return listWeatherStation;
    }

    public void setListWeatherStation(List<DeviceBaseInfoVo> listWeatherStation) {
        this.listWeatherStation = listWeatherStation;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getMonitorNum() {
        return monitorNum;
    }

    public void setMonitorNum(Long monitorNum) {
        this.monitorNum = monitorNum;
    }

    public List<DeviceBaseInfoVo> getListMonitor() {
        return listMonitor;
    }

    public void setListMonitor(List<DeviceBaseInfoVo> listMonitor) {
        this.listMonitor = listMonitor;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public Long getCameraNum() {
        return cameraNum;
    }

    public void setCameraNum(Long cameraNum) {
        this.cameraNum = cameraNum;
    }

    public List<DeviceBaseInfoVo> getListCamera() {
        return listCamera;
    }

    public void setListCamera(List<DeviceBaseInfoVo> listCamera) {
        this.listCamera = listCamera;
    }

    public Long getAutomationNum() {
        return automationNum;
    }

    public void setAutomationNum(Long automationNum) {
        this.automationNum = automationNum;
    }

    public List<DeviceBaseInfoVo> getListAutomation() {
        return listAutomation;
    }

    public void setListAutomation(List<DeviceBaseInfoVo> listAutomation) {
        this.listAutomation = listAutomation;
    }
}
