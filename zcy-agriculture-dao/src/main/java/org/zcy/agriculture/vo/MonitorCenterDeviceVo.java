package org.zcy.agriculture.vo;

/**
 * 监控中心设备信息
 * */
public class MonitorCenterDeviceVo {
    /** 设备ID */
    private Long devId;
    /** 设备名 */
    private String devName;
    /** 设备序列号 */
    private String devNum;
    /** 经度 */
    private Double lng;
    /** 纬度 */
    private Double lat;
    /** 状态（0正报警，1运行中，2断开，3待机，4其它） */
    private Integer status;

    private Integer devType;

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevNum() {
        return devNum;
    }

    public void setDevNum(String devNum) {
        this.devNum = devNum;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDevType() {
        return devType;
    }

    public void setDevType(Integer devType) {
        this.devType = devType;
    }
}
