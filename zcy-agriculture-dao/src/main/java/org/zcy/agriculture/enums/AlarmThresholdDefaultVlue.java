package org.zcy.agriculture.enums;

import org.zcy.agriculture.vo.AlarmThresholdDefault;

/**
 * 各类传感器默认报警阈值
 */
public enum
AlarmThresholdDefaultVlue {
    AIR_TEMPERATURE(1, new AlarmThresholdDefault(10F, 40F, 10F, 30F)),
    AIR_HUMIDITY(2, new AlarmThresholdDefault(10F, 40F, 10F, 30F)),
    SOIL_TEMPERATURE(3, new AlarmThresholdDefault(10F, 40F, 10F, 30F)), //"土壤温度（℃）"
    SOIL_HUMIDITY(4, new AlarmThresholdDefault(10F, 40F, 10F, 41F)),     //"土壤湿度（%）"),
    FOLIAR_TEMPERATURE(5, new AlarmThresholdDefault(5F, 50F, 0F, 50F)), //"叶面温度(℃)"
    FOLIAR_HUMIDITY(6, new AlarmThresholdDefault(4F, 50F, 0F, 50F)), //"叶面湿度（%"
    LIGHT_INTENSITY(7, new AlarmThresholdDefault(0F, 100000F, 0F, 100000F)), //"光照强度(Lux)"
    LUMINOUS_FLUX_DENSITY(8, new AlarmThresholdDefault(0F, 600F, 0F, 600F)), //"光通量密度(nm)"
    PH(9, new AlarmThresholdDefault(6F, 10F, 6F, 10F)),//"pH"
    EC_VALUE(10, new AlarmThresholdDefault(0F, 3F, 0F, 3F)), //"EC值(mS/cm )"
    CO2_CONCENTRATION(11, new AlarmThresholdDefault(0F, 1000F, 0F, 1000F)); //"二氧化碳浓度(ppm)"

    private Integer code;
    private AlarmThresholdDefault val;

    AlarmThresholdDefaultVlue(Integer code, AlarmThresholdDefault val) {
        this.code = code;
        this.val = val;
    }

    public static AlarmThresholdDefault getDescByCode(Integer code) {
        for (AlarmThresholdDefaultVlue status : AlarmThresholdDefaultVlue.values()) {
            if (status.getCode() == code) {
                return status.getVal();
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

    public AlarmThresholdDefault getVal() {
        return val;
    }

    public void setVal(AlarmThresholdDefault val) {
        this.val = val;
    }
}
