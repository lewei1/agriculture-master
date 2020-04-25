package org.zcy.agriculture.vo.device;

import org.zcy.agriculture.entity.TbResDeviceAttributes;

public class TbResDeviceAttributesExtraVo extends TbResDeviceAttributes {
    private String ts;
    private String value;

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
}
