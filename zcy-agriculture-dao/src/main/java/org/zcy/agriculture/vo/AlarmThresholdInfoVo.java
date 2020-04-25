package org.zcy.agriculture.vo;

import java.sql.Time;
import java.util.List;

public class AlarmThresholdInfoVo extends BaseVo{
    private String farmId;
    /** 地块ID */
    private Long plotId;
    /** 白天开始时间 */
    private Time dayStartTime;
    /** 白天结束时间 */
    private Time dayEndTime;

    List<AlarmThresholdVo> list;

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }

    public Time getDayStartTime() {
        return dayStartTime;
    }

    public void setDayStartTime(Time dayStartTime) {
        this.dayStartTime = dayStartTime;
    }

    public Time getDayEndTime() {
        return dayEndTime;
    }

    public void setDayEndTime(Time dayEndTime) {
        this.dayEndTime = dayEndTime;
    }

    public List<AlarmThresholdVo> getList() {
        return list;
    }

    public void setList(List<AlarmThresholdVo> list) {
        this.list = list;
    }
}
