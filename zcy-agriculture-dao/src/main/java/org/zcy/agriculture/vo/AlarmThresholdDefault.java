package org.zcy.agriculture.vo;

public class AlarmThresholdDefault {
    Float dayMinValue;
    Float dayMaxValue;
    Float nightMinValue;
    Float nightMaxValue;

    public AlarmThresholdDefault(Float dayMinValue,Float dayMaxValue,Float nightMinValue, Float nightMaxValue){
        this.dayMinValue = dayMinValue;
        this.dayMaxValue = dayMaxValue;
        this.nightMinValue = nightMinValue;
        this.nightMaxValue = nightMaxValue;
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
}
