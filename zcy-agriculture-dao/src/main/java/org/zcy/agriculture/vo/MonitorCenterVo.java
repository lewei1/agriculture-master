package org.zcy.agriculture.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 监控中心设备信息
 * */
public class MonitorCenterVo {
    /** 经度 */
    private BigDecimal longitude;
    /** 纬度 */
    private BigDecimal latitude;

    /**地块列表*/
    private List<MonitorCenterPlotVo> plotList;
    /**检测设备列表*/
    private List<MonitorCenterDeviceVo> monitorDeviceList;
    /**自动化设备列表*/
    private List<MonitorCenterDeviceVo> automationDeviceList;
    /**气象站列表*/
    private List<MonitorCenterDeviceVo> weatherDeviceList;

    public List<MonitorCenterPlotVo> getPlotList() {
        return plotList;
    }

    public void setPlotList(List<MonitorCenterPlotVo> plotList) {
        this.plotList = plotList;
    }

    public List<MonitorCenterDeviceVo> getMonitorDeviceList() {
        return monitorDeviceList;
    }

    public void setMonitorDeviceList(List<MonitorCenterDeviceVo> monitorDeviceList) {
        this.monitorDeviceList = monitorDeviceList;
    }

    public List<MonitorCenterDeviceVo> getAutomationDeviceList() {
        return automationDeviceList;
    }

    public void setAutomationDeviceList(List<MonitorCenterDeviceVo> automationDeviceList) {
        this.automationDeviceList = automationDeviceList;
    }

    public List<MonitorCenterDeviceVo> getWeatherDeviceList() {
        return weatherDeviceList;
    }

    public void setWeatherDeviceList(List<MonitorCenterDeviceVo> weatherDeviceList) {
        this.weatherDeviceList = weatherDeviceList;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
