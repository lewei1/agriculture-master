package org.zcy.agriculture.vo;

/**
 * 设备类型统计信息
 *
 * @author zh
 * @date 2019-06-25
 */
public class DeviceTypeStatisticsVo extends BaseVo {
    /** 检测设备*/
    private Long MonitoringNum;
    /**气象站*/
    private Long WeatherStationNum;
    /**灌溉设备*/
    private Long IrrigationNum;
    /**控制设备*/
    private Long ControllingNum;
    /**摄像头*/
    private Long CameraNum;

    public Long getMonitoringNum() {
        return MonitoringNum;
    }

    public void setMonitoringNum(Long monitoringNum) {
        MonitoringNum = monitoringNum;
    }

    public Long getWeatherStationNum() {
        return WeatherStationNum;
    }

    public void setWeatherStationNum(Long weatherStationNum) {
        WeatherStationNum = weatherStationNum;
    }

    public Long getIrrigationNum() {
        return IrrigationNum;
    }

    public void setIrrigationNum(Long irrigationNum) {
        IrrigationNum = irrigationNum;
    }

    public Long getControllingNum() {
        return ControllingNum;
    }

    public void setControllingNum(Long controllingNum) {
        ControllingNum = controllingNum;
    }

    public Long getCameraNum() {
        return CameraNum;
    }

    public void setCameraNum(Long cameraNum) {
        CameraNum = cameraNum;
    }
}
