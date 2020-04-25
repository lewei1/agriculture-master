package org.zcy.agriculture.enums;

public enum DevTypeEnum {

    MONITOR_DEV(0,"监测设备",600),
    WEATHER_STATION(1,"气象站",900),
    AUTOMATION_DEV(2,"自动化设备",600),
    //CONTROLL_DEV(3,"控制设备"),
    CAMERA(4,"摄像头",600);

    private Integer code;
    private String val;
    private Integer disconnects; //多长时间(秒)没上传数据则为掉线

    DevTypeEnum(Integer code , String val,Integer disconnects) {
        this.code = code;
        this.val = val;
        this.disconnects = disconnects;
    }

    public static String getDescByCode(Integer code) {
        for (DevTypeEnum status : DevTypeEnum.values()) {
            if (status.getCode() == code) {
                return status.getVal();
            }
        }
        return null;
    }

    public static Integer getDisconnectsByCode(Integer code) {
        for (DevTypeEnum status : DevTypeEnum.values()) {
            if (status.getCode() == code) {
                return status.getDisconnects();
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Integer getDisconnects() {
        return disconnects;
    }

    public void setDisconnects(Integer disconnects) {
        this.disconnects = disconnects;
    }
}
