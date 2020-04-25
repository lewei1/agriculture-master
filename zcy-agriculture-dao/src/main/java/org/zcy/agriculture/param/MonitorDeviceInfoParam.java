package org.zcy.agriculture.param;

import java.io.Serializable;

public class MonitorDeviceInfoParam implements Serializable {
    /**
     * 地块id
     */
    private Long plotId;
    /**
     * 开始日期字符串
     */
    private String startDateStr;
    /**
     * 结束日期字符串
     */
    private String endDateStr;

    private String attributes;

    /**时间间隔（秒级）*/
    private Long intervalSecond;

    /**
     * 是否是获取实时数据
     */
    private int isNow;

    /**
     * 设备序列号
     */
    private String devNum;

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getDevNum() {
        return devNum;
    }

    public void setDevNum(String devNum) {
        this.devNum = devNum;
    }

    public int getIsNow() {
        return isNow;
    }

    public void setIsNow(int isNow) {
        this.isNow = isNow;
    }

    public Long getIntervalSecond() {
        return intervalSecond;
    }

    public void setIntervalSecond(Long intervalSecond) {
        this.intervalSecond = intervalSecond;
    }
}
