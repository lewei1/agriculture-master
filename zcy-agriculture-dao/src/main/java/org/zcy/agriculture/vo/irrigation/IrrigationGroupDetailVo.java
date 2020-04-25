package org.zcy.agriculture.vo.irrigation;

import org.zcy.agriculture.entity.TbIrrigationGroup;
import org.zcy.agriculture.entity.TbResDevice;

import java.util.List;

public class IrrigationGroupDetailVo extends TbIrrigationGroup {

    private List<TbResDevice> resDeviceList;

    public List<TbResDevice> getResDeviceList() {
        return resDeviceList;
    }

    public void setResDeviceList(List<TbResDevice> resDeviceList) {
        this.resDeviceList = resDeviceList;
    }
}
