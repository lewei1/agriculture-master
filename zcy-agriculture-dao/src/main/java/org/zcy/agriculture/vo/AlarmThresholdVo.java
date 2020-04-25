package org.zcy.agriculture.vo;

import java.sql.Time;

public class AlarmThresholdVo extends BaseVo{

    /** 阈值类型index */
    private Integer typesIndex;

    private String name;

    private String unit;

    /** 白天阈值小的那个值 */
    private Float dayMinValue;
    /** 白天阈值大的那个值 */
    private Float dayMaxValue;
    /** 晚上阈值小的值 */
    private Float nightMinValue;
    /** 夜晚阈值大的那个值 */
    private Float nightMaxValue;

    public Integer getTypesIndex() {
        return typesIndex;
    }

    public void setTypesIndex(Integer typesIndex) {
        this.typesIndex = typesIndex;
    }

    public Float getDayMinValue() {
        return dayMinValue;
    }

    public void setDayMinValue(Float dayMinValue) {
        this.dayMinValue = dayMinValue;
    }

    public Float getDayMaxValue() {
        return dayMaxValue;
    }

    public void setDayMaxValue(Float dayMaxValue) {
        this.dayMaxValue = dayMaxValue;
    }

    public Float getNightMinValue() {
        return nightMinValue;
    }

    public void setNightMinValue(Float nightMinValue) {
        this.nightMinValue = nightMinValue;
    }

    public Float getNightMaxValue() {
        return nightMaxValue;
    }

    public void setNightMaxValue(Float nightMaxValue) {
        this.nightMaxValue = nightMaxValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
