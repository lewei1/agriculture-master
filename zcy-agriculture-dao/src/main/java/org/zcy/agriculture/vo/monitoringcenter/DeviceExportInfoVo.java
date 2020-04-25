package org.zcy.agriculture.vo.monitoringcenter;

import org.zcy.agriculture.constants.Excel;

public class DeviceExportInfoVo {
    /** 设备序列号 */
    @Excel(name = "设备序列号")
    private String devNum;
    @Excel(name = "时间")
    private String ts;
    @Excel(name = "属性名称")
    private String attributesName;
    @Excel(name = "数值")
    private String value;

    public String getDevNum() {
        return devNum;
    }

    public void setDevNum(String devNum) {
        this.devNum = devNum;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAttributesName() {
        return attributesName;
    }

    public void setAttributesName(String attributesName) {
        this.attributesName = attributesName;
    }
}
