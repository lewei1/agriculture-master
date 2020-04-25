package org.zcy.agriculture.enums;

import org.zcy.agriculture.entity.TbResDeviceAttributes;
import org.zcy.agriculture.vo.monitoringcenter.AlarmThresholdKeyNameVo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 设备传感器属性列表
 * */
public enum
AlarmThresholdTypes {
    AIR_TEMPERATURE(1,"空气温度","℃"),
    AIR_HUMIDITY(2,"空气湿度","%rh"),
    SOIL_TEMPERATURE(3,"土壤温度","℃"),
    SOIL_HUMIDITY(4,"土壤湿度","%rh"),
    FOLIAR_TEMPERATURE(5,"叶面温度","℃"),
    FOLIAR_HUMIDITY(6,"叶面湿度","%rh"),
    LIGHT_INTENSITY(7,"光照强度","Lux"),
    LUMINOUS_FLUX_DENSITY(8,"光通量密度","nm"),
    PH(9,"pH","PH"),
    //CAN_ALARM_MAX(9,"可统计的最大值"),
    EC_VALUE(10,"EC值","mS/cm"),
    CO2_CONCENTRATION(11,"二氧化碳浓度","ppm"),
    SIGNAL_STRENGTH(12,"信号强度","dB"),
    CHARGING_VOLTAGE(13,"充电压","V"),
    WATERDATA_COND(14,"水数据电导力","us/cm"),
    WATERDATA_ZS(15,"水浊度","NTU"),
    WATERDATA_PH(16,"水PH值","PH"),
    WATERDATA_ORP(17,"水氧化还原电位","ORP"),
    WATERDATA_TEMP(18,"水温","℃"),
    WATERDATA_DOVAL(19,"水-溶解氧","mg/L"),
   // WATERDATA_COD(20,"水COD"),
    WATERDATA_NH4(21,"氨氮","mg/L"),

	WEATHERDATA_WINDS(22,"风速","m/s"),
	WEATHERDATA_WINDD(23,"风向",""),
	WEATHERDATA_HUMI(24,"空气湿度","%rh"),
	WEATHERDATA_TEMP(25,"空气温度","℃"),
	WEATHERDATA_PM2_5(26,"pm2.5","μg/m3"),
	WEATHERDATA_PM10(27,"pm1.0","μg/m3"),
	WEATHERDATA_LLUM(28,"光照度","Lux"),
	WEATHERDATA_RAIN(29,"降雨量","mm"),
	WEATHERDATA_PRESS(30,"大气压","KPa");
	
    private Integer code;
    private String val;
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    AlarmThresholdTypes(Integer code , String val, String unit) {
        this.code = code;
        this.val = val;
        this.unit = unit;
    }

    public static String getDescByCode(Integer code) {
        for (AlarmThresholdTypes status : AlarmThresholdTypes.values()) {
            if (status.getCode() == code) {
                return status.getVal();
            }
        }
        return null;
    }

    public static String getValByKey(String key) {
        for (AlarmThresholdTypes status : AlarmThresholdTypes.values()) {
            if (status.name() == key) {
                return status.getVal();
            }
        }
        return null;
    }

    public static List<AlarmThresholdKeyNameVo> getAllKeyVal() {
        List<AlarmThresholdKeyNameVo> list = new ArrayList<>();
        for (AlarmThresholdTypes alarm : AlarmThresholdTypes.values()) {
            AlarmThresholdKeyNameVo alarmThreshold = new AlarmThresholdKeyNameVo();
            alarmThreshold.setThingsboardKey(alarm.name());
            alarmThreshold.setCnName(alarm.val);
            list.add(alarmThreshold);
        }
        return list;
    }

    public static String getUnitByCode(Integer code) {
        for (AlarmThresholdTypes status : AlarmThresholdTypes.values()) {
            if (status.getCode() == code) {
                return status.getUnit();
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

    /**
     * 根据方法名称  获取相应的值
     * @param coinCode 方法名称
     * @param type 获取类型（getVal：名称，getUnit：获取单位）
     * @return
     */
	public static String getCoinAddress(String coinCode,String type) {
		// 获取Class对象
		Class<?> clzz = AlarmThresholdTypes.class;
		Object[] objects = clzz.getEnumConstants();
		// 获取指定方法
		Method coinAddressAddress;
		try {
			coinAddressAddress = clzz.getMethod(type);
			for (Object obj : objects) {
				if (coinCode.equals(obj.toString())) {
					return (String) coinAddressAddress.invoke(obj);
				}
			}
		} catch (Exception e) {
		}
		return "";
	}
}
