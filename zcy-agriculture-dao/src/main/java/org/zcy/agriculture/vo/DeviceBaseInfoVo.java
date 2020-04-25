package org.zcy.agriculture.vo;

public class DeviceBaseInfoVo extends BaseVo{
    /** 设备ID */
    private Long devId;
    /** 设备名 */
    private String devName;
    /** 设备序列号 */
    private String devNum;

    private Integer devStatus;

    /** （0检测设备，1气象站，2灌溉设备，3控制设备，4摄像头） */
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

    public Integer getDevType() {
        return devType;
    }

    public void setDevType(Integer devType) {
        this.devType = devType;
    }

    public Integer getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(Integer devStatus) {
        this.devStatus = devStatus;
    }
}
