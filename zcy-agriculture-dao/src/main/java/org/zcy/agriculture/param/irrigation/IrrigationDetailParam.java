package org.zcy.agriculture.param.irrigation;

import org.zcy.agriculture.entity.TbIrrigationDevice;
import org.zcy.agriculture.entity.TbIrrigationGroup;

import java.util.List;

public class IrrigationDetailParam extends TbIrrigationGroup {

    //设备列表
    private List<TbIrrigationDevice> deviceList;

    public List<TbIrrigationDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<TbIrrigationDevice> deviceList) {
        this.deviceList = deviceList;
    }
}

