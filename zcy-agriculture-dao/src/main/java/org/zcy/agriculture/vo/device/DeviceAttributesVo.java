package org.zcy.agriculture.vo.device;

public class DeviceAttributesVo {
    private String attributesName;
    /** 属性名中文名 */
    private String thingsboardKey;
    /** 设备类型列表(参考tb_alarm_threshold_types表) */
    private Integer typesIndex;
    private String unit;//单位

    public String getAttributesName() {
        return attributesName;
    }

    public void setAttributesName(String attributesName) {
        this.attributesName = attributesName;
    }

    public String getThingsboardKey() {
        return thingsboardKey;
    }

    public void setThingsboardKey(String thingsboardKey) {
        this.thingsboardKey = thingsboardKey;
    }

    public Integer getTypesIndex() {
        return typesIndex;
    }

    public void setTypesIndex(Integer typesIndex) {
        this.typesIndex = typesIndex;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
